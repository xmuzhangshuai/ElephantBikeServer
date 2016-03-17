package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tencent.WXPay;
import com.tencent.common.Signature;
import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.HttpsRequest;
import com.xxn.butils.XMLUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.PayReqData;
import com.xxn.entity.PayResData;

/**
 * Servlet implementation class WXPayOrder
 * 请求生成支付订单
 */
@WebServlet("/api/pay/wxpayorder")
public class WXPayOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WXPayOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/pay/wxpayorder");
		Map<String, Object> resultMap = new HashMap<>();
		
		String url = BikeConstants.WX_PAY_ORDER;
		String xmlString = "";
		String key = BikeConstants.WX_KEY;
		String appID = BikeConstants.WX_APP_ID;
		String mchID = BikeConstants.WX_MCH_ID;
		String certPassword = BikeConstants.WX_CERTPASSWORD;
		
		String body = "elephant bike";
		String outTradeNo = DateTool.date2String(new Date());
		int totalFee = 1;
		String spBillCreateIP = "192.168.0.103";
		String notify_url = "http://www.xxnnet.cn";
		String trade_type = "APP";
		String sdbMchID = "";
		String certLocalPath = "";
		String attach = "", deviceInfo = "", timeStart = "", timeExpire = "", goodsTag = "";

		WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath,
				certPassword);
		PayReqData data = new PayReqData(notify_url, body, attach, outTradeNo,
				totalFee, deviceInfo, spBillCreateIP, timeStart, timeExpire,
				goodsTag, trade_type);

		Map<String, Object> map = data.toMap();
		xmlString = XMLUtil.maptoXml(map);
		
		String result = HttpsRequest.sendPost(url,xmlString);
		//拿到返回结果
		Map<String, Object> res = XMLUtil.xmltoMap(result);
		String signRes="";
		for (String obj : res.keySet()) {
			if(obj.equals("sign")){
				signRes = String.valueOf(res.get(obj));
				res.put(obj, "");
			}
		}
		String resSign = Signature.getSign(res);
		if(resSign.equals(signRes)){
			System.out.println("sign验证通过");
			//返回客户端需要的参数值
			String prepayid = (String) res.get("prepay_id");
			PayResData resData = new PayResData(prepayid);
			resultMap = resData.toMap();
			if(!resultMap.isEmpty()){
				resultMap.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			}
			else{
				resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
				resultMap.put(BikeConstants.MESSAGE, "返回参数为空");
			}
		}
		else{
			resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
			resultMap.put(BikeConstants.MESSAGE, "sign验证不通过");
		}
		
		System.out.println(FastJsonTool.createJsonString(resultMap));
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
	}

}

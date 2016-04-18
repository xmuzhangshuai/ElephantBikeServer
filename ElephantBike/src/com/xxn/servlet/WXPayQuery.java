package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tencent.WXPay;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.HttpClientUtil;
import com.xxn.butils.XMLUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.PayReqData;
import com.xxn.entity.PayResData;
import com.xxn.iservice.IOrderService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class WXPayOrder 查询支付结果
 */
@WebServlet("/api/pay/wxorderquery")
public class WXPayQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXPayQuery() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/pay/wxorderquery");
		Map<String, Object> resultMap = new HashMap<>();

		String orderid = request.getParameter("out_trade_no");
		System.out.println(orderid);
		
		String url = BikeConstants.WX_PAY_ORDER_QUERY;
		String xmlString = "";
		String key = BikeConstants.WX_KEY;
		String appID = BikeConstants.WX_APP_ID;
		String mchID = BikeConstants.WX_MCH_ID;
		String certPassword = BikeConstants.WX_CERTPASSWORD;
		String sdbMchID = "";
		String certLocalPath = "";
		String nonce_str=RandomStringGenerator.getRandomStringByLength(32);
		
		ServletContext application = this.getServletContext();
		String out_trade_no = (String) application.getAttribute(orderid);
		
		WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath,
				certPassword);
		
		Map<String, Object> query = new HashMap<>();
		query.put("appid", appID);
		query.put("mch_id", mchID);
		query.put("out_trade_no", out_trade_no);
		query.put("nonce_str", nonce_str);
		String sign = Signature.getSign(query);
		query.put("sign", sign);
		xmlString = XMLUtil.maptoXml(query);
		String result = new HttpClientUtil().doPost(url, xmlString);
		// 拿到返回结果
		resultMap = XMLUtil.xmltoMap(result);
		if(resultMap.get("result_code").equals("SUCCESS")&&resultMap.get("return_code").equals("SUCCESS")){
			resultMap.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			application.setAttribute(orderid, null);
		}
		else{
			resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
		}
		System.out.println(FastJsonTool.createJsonString(resultMap));
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tencent.WXPay;
import com.tencent.common.Signature;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.HttpClientUtil;
import com.xxn.butils.XMLUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.PayReqData;
import com.xxn.entity.PayResData;
import com.xxn.entity.Token;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.ITokenService;
import com.xxn.service.OrderService;
import com.xxn.service.TokenService;

/**
 * Servlet implementation class WXPayOrder 请求生成支付订单
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
		System.out.println("/api/pay/wxpayorder");
		IOrderService iOrderService = new OrderService();
		Map<String, Object> resultMap = new HashMap<>();

		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		ServletContext application = this.getServletContext();
		String access_token = request.getParameter("access_token");
		String servertoken = (String) application.getAttribute("token" + phone);
		if (null == servertoken) {
			ITokenService iTokenService = new TokenService();
			Token token = new Token(phone, "", "");
			servertoken = iTokenService.getToken(token);
		}
		System.out.println("phone:" + phone);
		System.out.println("access_token:" + access_token);
		System.out.println("servertoken:" + servertoken);
		if (null != access_token && servertoken.equals(access_token)) {
			String orderid = "", notify_url = "", fee = "";
			int totalFee = 0;
			// 订单支付功能
			notify_url = BikeConstants.APP_URL
					+ "/ElephantBike/api/pay/response";
			Map<String, String> val = new HashMap<>();
			Map<String, String> query = new HashMap<>();
			Map<String, String> resmap = new HashMap<>();
			val.put("cost", "");
			val.put("orderid", "");
			query.put("phone", phone);
			query.put("bikeid", bikeid);
			query.put("paymode", null);
			resmap = iOrderService.getOrderInfo(val, query);
			if (resmap.containsKey("orderid")) {
				orderid = resmap.get("orderid");
				fee = resmap.get("cost");
				totalFee = (int) (Float.parseFloat(fee) * 100);
			}
			System.out.println("orderid:" + orderid + "--phone:" + phone
					+ "--bikeid:" + bikeid + "--fee:" + totalFee);
			String url = BikeConstants.WX_PAY_ORDER;
			String xmlString = "";
			String key = BikeConstants.WX_KEY;
			String appID = BikeConstants.WX_APP_ID;
			String mchID = BikeConstants.WX_MCH_ID;
			String certPassword = BikeConstants.WX_CERTPASSWORD;
			String body = "elephantbike order";
			String outTradeNo = orderid;
			String spBillCreateIP = "192.168.0.123";
			String trade_type = "APP";
			String sdbMchID = "";
			String certLocalPath = "";

			WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID,
					certLocalPath, certPassword);
			PayReqData data = new PayReqData(notify_url, body, outTradeNo,
					totalFee, spBillCreateIP, trade_type);

			Map<String, Object> map = data.toMap();
			xmlString = XMLUtil.maptoXml(map);
			String result = new HttpClientUtil().doPost(url, xmlString);
			// 拿到返回结果
			Map<String, Object> res = XMLUtil.xmltoMap(result);
			String signRes = "";
			if (!res.isEmpty()) {
				for (String obj : res.keySet()) {
					if (obj.equals("sign")) {
						signRes = String.valueOf(res.get(obj));
						res.put(obj, "");
					}
				}
				String resSign = Signature.getSign(res);
				if (resSign.equals(signRes)) {
					System.out.println("sign验证通过");
					// 返回客户端需要的参数值
					String prepayid = (String) res.get("prepay_id");
					PayResData resData = new PayResData(prepayid);
					resultMap = resData.toMap();
					if (!resultMap.isEmpty()) {
						resultMap.put("out_trade_no", outTradeNo);
						resultMap.put(BikeConstants.STATUS,
								BikeConstants.SUCCESS);
					} else {
						resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
						resultMap.put(BikeConstants.MESSAGE, "返回参数为空");
					}
				} else {
					resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
					resultMap.put(BikeConstants.MESSAGE, "sign验证不通过");
				}
			} else {
				resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
				resultMap.put(BikeConstants.MESSAGE, res + "\n" + result);
			}
		} else {
			resultMap.put(BikeConstants.STATUS, BikeConstants.FAIL);
			resultMap.put(BikeConstants.MESSAGE, BikeConstants.INVALID_TOKEN);
		}
		System.out.println(FastJsonTool.createJsonString(resultMap));
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.constants.ALiPayConfig;
import com.xxn.constants.BikeConstants;

/**
 * Servlet implementation class ALiPayOrder
 */
@WebServlet("/api/pay/alipayorder")
public class ALiPayOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ALiPayOrder() {
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
		Map<String, Object> result = new HashMap<>();
		
		String phone = request.getParameter("phone");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String month = request.getParameter("month");
		
		String service = "mobile.securitypay.pay";
		String partner = ALiPayConfig.Partner;
		String _input_charset = "utf-8";
		String notify_url =BikeConstants.APP_URL+"/ElephantBike/api/pay/rsanotifyreceiver";
		String out_trade_no = phone+"_"+System.currentTimeMillis()+"_"+month;
		String payment_type = "1";
		String seller_id = ALiPayConfig.Seller_ID;
		String total_fee = "0.01";
		
		Map<String, Object> obj = new HashMap<>();
		obj.put("service", service);
		obj.put("partner", partner);
		obj.put("_input_charset", _input_charset);
		obj.put("notify_url", notify_url);
		obj.put("out_trade_no", out_trade_no);
		obj.put("subject", subject);
		obj.put("payment_type", payment_type);
		obj.put("seller_id", seller_id);
		obj.put("total_fee", total_fee);
		obj.put("body", body);
		String sign="",signData="";
		
		for (Object object : obj.keySet()) {
			String key = object.toString();
			String value = obj.get(key).toString();
			signData += String.format("%s=\"%s\"", key, value);
			signData +="&";
		}
		signData = signData.substring(0,signData.length()-1);
		
		try {
			sign = AlipaySignature.rsaSign(signData, ALiPayConfig.privateKey, "utf-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		sign = URLEncoder.encode(sign,"utf-8");
		String sign_type = "RSA";
		
		result.put("param", signData);
		result.put("sign", sign);
		result.put("sign_type", sign_type);
		
		System.out.println(FastJsonTool.createJsonString(result));
		out.print(FastJsonTool.createJsonString(result));
		out.close();
	}

}

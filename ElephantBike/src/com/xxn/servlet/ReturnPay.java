package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.FastJsonTool;
import com.xxn.iservice.IOrderService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class ReturnPay
 */
@WebServlet("/api/money/returnpay")
public class ReturnPay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnPay() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/money/returnpay");
		Map<String, String> map = new HashMap<>();
		IOrderService iOrderService = new OrderService();

		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String paymode = request.getParameter("paymode");
		System.out.println("paymode:" + paymode);
		if (paymode.equals("大象钱包")) {
			
		}
		if (paymode.equals("微信支付")) {

		}
		if (paymode.equals("支付宝")) {

		}
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

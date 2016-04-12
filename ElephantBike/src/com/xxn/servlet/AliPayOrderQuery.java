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
@WebServlet("/api/pay/alipayquery")
public class AliPayOrderQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AliPayOrderQuery() {
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
		System.out.println("/api/pay/alipayquery");
		Map<String, Object> map = new HashMap<>();

		String trade_no = request.getParameter("out_trade_no");
		
		System.out.println("out_trade_no:"+trade_no);
		
		ServletContext application = this.getServletContext();
		String out_trade_no = (String) application.getAttribute(trade_no);
		if(out_trade_no.equals(trade_no)){
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			map.put(BikeConstants.MESSAGE, "支付宝查询:支付成功");
		}
		else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "支付宝查询:支付失败");
		}
		
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

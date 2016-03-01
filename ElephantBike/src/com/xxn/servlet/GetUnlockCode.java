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

import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Order;
import com.xxn.iservice.IOrderService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class GetUnlockCode
 */
@WebServlet("/api/pass/unlockcode")
public class GetUnlockCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUnlockCode() {
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
		System.out.println("/api/pass/unlockcode");

		Map<String, String> map = new HashMap<>();
		IOrderService iOrderService = new OrderService();
		String bikeid = request.getParameter("bikeid");
		String phone = request.getParameter("phone");

		String pass = "";
		if (NormalUtil.isStringLegal(bikeid) && NormalUtil.isStringLegal(phone)) {
			// TODO 根据bikeid获取解锁密码
			pass = BikeConstants.UNLOCK_CODE;

			// 写入订单---获取解锁密码后开始订单
			Order order = new Order(phone, bikeid, DateTool.dateToString(new Date()));
			if (iOrderService.createOrder(order) > 0) {
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("pass", pass);
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE,"订单写入失败，请重新获取解锁密码");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "获取解锁密码:手机号码为空或者单车编码为空");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

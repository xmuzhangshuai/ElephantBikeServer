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

import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Order;
import com.xxn.iservice.IOrderService;
import com.xxn.service.BikeService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class GetBikeidAndPass
 */
@WebServlet("/api/bike/bikeidandpass")
public class GetBikeidAndPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBikeidAndPass() {
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
		System.out.println("/api/bike/bikeidandpass");
		Map<String, String> map = new HashMap<>();
		IOrderService iOrderService = new OrderService();

		String phone = request.getParameter("phone");
		// ServletContext application = this.getServletContext();
		// String access_token = request.getParameter("access_token");
		// String servertoken = (String) application.getAttribute("token" +
		// phone);
		// System.out.println("phone:"+phone);
		// System.out.println("access_token:"+access_token);
		// System.out.println("servertoken:"+servertoken);
		// if (null != access_token && null != servertoken &&
		// servertoken.equals(access_token)) {
		if (NormalUtil.isStringLegal(phone)) {
			Order order = new Order(phone, null, null);
			String bikeid = iOrderService.getBikeid(order);
			if (!bikeid.equals("")) {
				// TODO 获取单车解锁密码
				String pass = BikeConstants.UNLOCK_CODE;
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("bikeid", bikeid);
				map.put("pass", pass);
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "查找不到该订单");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码不合法");
		}
		// } else {
		// map.put(BikeConstants.STATUS, BikeConstants.FAIL);
		// map.put(BikeConstants.MESSAGE, BikeConstants.INVALID_TOKEN);
		// }

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

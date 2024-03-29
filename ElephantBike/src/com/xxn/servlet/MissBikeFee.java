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
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Bike;
import com.xxn.entity.User;
import com.xxn.iservice.IBikeService;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.IUserService;
import com.xxn.service.BikeService;
import com.xxn.service.OrderService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class MissBikeFee
 */
@WebServlet("/api/bike/missbikefee")
public class MissBikeFee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MissBikeFee() {
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
		System.out.println("/api/bike/missbikefee");
		Map<String, String> map = new HashMap<>();

		IOrderService iOrderService = new OrderService();
		IBikeService iBikeService = new BikeService();
		IUserService iUserService = new UserService();

		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		System.out.println(phone+bikeid);
		Map<String, String> val = new HashMap<>();

		Date finish = new Date();
		val.put("finishtime", DateTool.dateToString(finish));
		val.put("cost", BikeConstants.MISSFEE + "");
		Map<String, String> query = new HashMap<>();
		query.put("phone", phone);
		query.put("bikeid", bikeid);
		query.put("finishtime", null);
		Bike bike = new Bike(bikeid, 1, "", "");
		if (iOrderService.updateOrder(val, query) > 0
				&& iBikeService.updateBikeState(bike) > 0) {
			// 做用户冻结操作
//			User user = new User(phone, "-1");
//			iUserService.updateUserState(user);
//			System.out.println("非正常还车冻结用户...");
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			map.put("fee", String.valueOf(BikeConstants.MISSFEE));
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "订单修改失败");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

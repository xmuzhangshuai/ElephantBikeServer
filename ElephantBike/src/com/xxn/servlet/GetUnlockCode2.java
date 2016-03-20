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
import com.xxn.butils.PassWordTool;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Bike;
import com.xxn.entity.Order;
import com.xxn.iservice.IBikeService;
import com.xxn.iservice.IOrderService;
import com.xxn.service.BikeService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class GetUnlockCode
 */
@WebServlet("/api/pass/unlockcode2")
public class GetUnlockCode2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUnlockCode2() {
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
		System.out.println("/api/pass/unlockcode2");

		Map<String, String> map = new HashMap<>();
		boolean canused = false;
		IOrderService iOrderService = new OrderService();
		IBikeService iBikeService = new BikeService();
		String bikeid = request.getParameter("bikeid");
		String phone = request.getParameter("phone");

		String pass = "";
		if (NormalUtil.isStringLegal(bikeid) && NormalUtil.isStringLegal(phone)) {
			//TODO 判断单车是否在使用-->在使用的话是否是同一个人
			Bike bike = new Bike(bikeid, 0, "", "");
			if(iBikeService.isCanUsed(bike))
				canused = true;
			else {
				canused = false;
			}
			
			if(canused){
				// TODO 根据bikeid获取解锁密码
				pass = PassWordTool.getUnlockPass(bikeid);

				// 写入订单---获取解锁密码后开始订单
				Order order = new Order(phone, bikeid, DateTool.dateToString(new Date()));
				
				if (iOrderService.createOrder(order) > 0 && iBikeService.updateBikeState(bike) > 0) {
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put("pass", pass);
				}
				else{
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE,"订单写入失败，请重新获取解锁密码");
				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE,"该车处于使用中...");
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

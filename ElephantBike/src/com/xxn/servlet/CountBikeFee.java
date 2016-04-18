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
import com.xxn.entity.Bike;
import com.xxn.entity.Order;
import com.xxn.entity.User;
import com.xxn.iservice.IBikeService;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.IUserService;
import com.xxn.service.BikeService;
import com.xxn.service.OrderService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class CountBikeFee ss
 */
@WebServlet("/api/money/bikefee")
public class CountBikeFee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountBikeFee() {
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
		System.out.println("/api/money/bikefee");
		
		Map<String, String> map = new HashMap<>();
		IOrderService iOrderService = new OrderService();
		IBikeService iBikeService = new BikeService();
		IUserService iUserService = new UserService();
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String isfinish = request.getParameter("isfinish");
		String isnatural = request.getParameter("isnatural");
		int mins = 0;
		float fee = 0.0f;
		if (NormalUtil.isStringLegal(phone) && NormalUtil.isStringLegal(bikeid) && NormalUtil.isStringLegal(isfinish)) {
			// 先去数据查找订单未finish的starttime---计算时长和费用
			Order order = new Order(phone, bikeid, null);
			String starttime = iOrderService.getOrderInfo(order);
			if (NormalUtil.isStringLegal(starttime)) {
				Date start = DateTool.stringToDate(starttime);
				Date finish = new Date();
				long seconds = (finish.getTime() - start.getTime()) / 1000;

				// 根据秒数计算分钟数，获取费用
				mins = (int) (seconds / 60) + 1 ;
//				if (seconds % 60 != 0) {
//					mins += 1;
//				}
				Map<String, String> valisvip = new HashMap<>();
				valisvip.put("isvip", "");
				Map<String, String> queryphone = new HashMap<>();
				queryphone.put("phone", phone);
				String isvip = "0";
				Map<String, String> resMap = iUserService.getUserInfo(valisvip, queryphone);
				if(resMap.containsKey("isvip"))
					isvip = resMap.get("isvip");
				fee = NormalUtil.countFee(mins, isvip);
				
				// 计算使用时长
				String usedtime = DateTool.calcUsedTime(seconds);

				if (isfinish.equals("0")) {
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put("fee", String.valueOf(fee));
					map.put("time", usedtime);
				}
				if (isfinish.equals("1")) {
					// 写入数据库订单  1 还车成功
					Map<String, String> val = new HashMap<>();
					val.put("finishtime", DateTool.dateToString(finish));
					val.put("usedtime", usedtime);
					val.put("cost", fee + "");
					Map<String, String> query = new HashMap<>();
					query.put("phone", phone);
					query.put("bikeid", bikeid);
					query.put("finishtime", null);
					Bike bike = new Bike(bikeid, 1, "", "");
					if (iOrderService.updateOrder(val, query) > 0 && iBikeService.updateBikeState(bike) > 0) {
						if(null != isnatural)
							if(isnatural.equals("0")){
								//做用户冻结操作
								User user = new User(phone, "-1");
								iUserService.updateUserState(user);
								System.out.println("非正常还车冻结用户...");
							}
						map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
						map.put("fee", String.valueOf(fee));
						map.put("time", usedtime);
					} else {
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "订单修改失败");
					}
				}

			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "查找不到该未完成订单");
			}

		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码或者单车编号不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}
}

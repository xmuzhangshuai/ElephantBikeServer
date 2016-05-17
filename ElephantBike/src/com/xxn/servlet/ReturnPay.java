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

import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.NewOrder;
import com.xxn.entity.Token;
import com.xxn.entity.Wallet;
import com.xxn.iservice.INewOrderService;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.ITokenService;
import com.xxn.iservice.IWalletService;
import com.xxn.service.NewOrderService;
import com.xxn.service.OrderService;
import com.xxn.service.TokenService;
import com.xxn.service.WalletService;

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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
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
		System.out.println("/api/money/returnpay");
		Map<String, String> map = new HashMap<>();
		IOrderService iOrderService = new OrderService();
		IWalletService iWalletService = new WalletService();

		String phone = request.getParameter("phone");
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
			String bikeid = request.getParameter("bikeid");
			String paymode = request.getParameter("paymode");
			String ismissing = request.getParameter("ismissing");
			System.out.println("paymode:" + paymode + "--phone:" + phone
					+ "--bikeid:" + bikeid);
			// ismissing = "0";
			if (NormalUtil.isStringLegal(phone)
					&& NormalUtil.isStringLegal(ismissing)
					&& NormalUtil.isStringLegal(paymode)) {
				// 先去请求费用多少
				float fee = 0.0f;
				Map<String, String> val = new HashMap<>();
				Map<String, String> query = new HashMap<>();
				if (ismissing.equals("0")) {
					val.put("cost", "");
					query.put("phone", phone);
					query.put("bikeid", bikeid);
					query.put("paymode", null);
					map = iOrderService.getOrderInfo(val, query);
					if (map.containsKey("cost"))
						fee = Float.parseFloat(map.get("cost"));
					// 清空map
					map.clear();
				} else {
					fee = BikeConstants.MISSFEE;
				}
				if (fee > 0.0f) {
					Wallet wallet1 = new Wallet(phone, -fee, 0);
					Wallet wallet2 = new Wallet(phone, -fee,
							DateTool.dateToString(new Date()));
					if (ismissing.equals("0")) {
						// 正常还车-->请求先扣费，再进行订单写入
						if (iWalletService.rechargeWallet(wallet1) > 0) {
							val.clear();
							val.put("paymode", paymode);
							if (iWalletService.addWalletList(wallet2) > 0
									&& iOrderService.updateOrder(val, query) > 0) {
								//
								INewOrderService iNewOrderService = new NewOrderService();
								String date = DateTool.dateToStringYMD(new Date());
								NewOrder newOrder = new NewOrder(date, 1, fee);
								if(iNewOrderService.getNewOrderCount(date) == 1){
									iNewOrderService.updateNewOrder(newOrder);
								}
								else{
									iNewOrderService.addNewOrder(newOrder);
								}
								
								map.put(BikeConstants.STATUS,
										BikeConstants.SUCCESS);
								map.put(BikeConstants.MESSAGE, "支付成功");
							} else {
								map.put(BikeConstants.STATUS,
										BikeConstants.FAIL);
								map.put(BikeConstants.MESSAGE, "扣费成功，订单修改失败");
							}
						} else {
							map.put(BikeConstants.STATUS, BikeConstants.FAIL);
							map.put(BikeConstants.MESSAGE, "扣费失败");
						}

					} else {
						// 丢车-->先扣费，再写入丢车列表
						if (iWalletService.rechargeWallet(wallet1) > 0) {
							val.clear();
							val.put("finishtime",
									DateTool.dateToString(new Date()));
							val.put("cost", fee + "");
							val.put("paymode", paymode);
							query.clear();
							query.put("phone", phone);

							if (iWalletService.addWalletList(wallet2) > 0
									&& iOrderService.updateOrder(val, query) > 0) {
								map.put(BikeConstants.STATUS,
										BikeConstants.SUCCESS);
								map.put(BikeConstants.MESSAGE, "支付成功");
							} else {
								map.put(BikeConstants.STATUS,
										BikeConstants.FAIL);
								map.put(BikeConstants.MESSAGE, "扣费成功，订单修改失败");
							}
						} else {
							map.put(BikeConstants.STATUS, BikeConstants.FAIL);
							map.put(BikeConstants.MESSAGE, "扣费失败");
						}
						// TODO 写入丢车列表
					}
				} else {
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE, "支付失败");
				}
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "参数不合法");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, BikeConstants.INVALID_TOKEN);
		}
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

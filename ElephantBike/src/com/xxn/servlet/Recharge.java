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
import com.xxn.entity.Wallet;
import com.xxn.iservice.IWalletService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class Recharge
 */
@WebServlet("/api/money/recharge")
public class Recharge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recharge() {
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
		System.out.println("/api/money/recharge");
		Map<String, String> map = new HashMap<>();
		IWalletService iWalletService = new WalletService();

		String phone = request.getParameter("phone");
		String value = request.getParameter("value");
		
		if (NormalUtil.isStringLegal(phone) && NormalUtil.isStringLegal(value)) {
			// 先增加个人余额
			Wallet wallet = new Wallet(phone, Float.valueOf(value), 0);
			Wallet walletlist = new Wallet(phone, Float.valueOf(value),
					DateTool.dateToString(new Date()));
			if (iWalletService.rechargeWallet(wallet) > 0
					&& iWalletService.addWalletList(walletlist) > 0) {
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				if (Float.valueOf(value) > 0)
					map.put(BikeConstants.MESSAGE, "充值成功");
				else
					map.put(BikeConstants.MESSAGE, "扣费成功");
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "充值失败");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "充值失败，手机号码不合法或者充值余额不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

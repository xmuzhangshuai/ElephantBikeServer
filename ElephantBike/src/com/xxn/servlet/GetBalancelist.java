package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Wallet;
import com.xxn.iservice.IWalletService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class GetBalancelist
 */
@WebServlet("/api/money/balancelist")
public class GetBalancelist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBalancelist() {
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
		System.out.println("/api/money/balancelist");
		Map<String, Object> map = new HashMap<>();
		IWalletService iWalletService = new WalletService();

		String phone = request.getParameter("phone");
		String countStr = request.getParameter("count");
		int count = 0;
		
		if (NormalUtil.isStringLegal(phone)
				&& NormalUtil.isStringInteger(countStr)) {
			count = Integer.parseInt(countStr);
			Wallet wallet = new Wallet(phone, 0.0f, "");
			List<Map<String, Object>> list = iWalletService.getBalancelist(
					wallet, count);
			if (list.size() > 0) {
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("data", list);
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "没有更多数据了...");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

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
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;

/**
 * Servlet implementation class MatchRestoreCode
 */
@WebServlet("/api/pass/restorecode")
public class MatchRestoreCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchRestoreCode() {
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
		System.out.println("/api/pass/returncode");
		Map<String, String> map = new HashMap<>();

		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String pass = request.getParameter("pass");

		if (NormalUtil.isStringLegal(phone) && NormalUtil.isStringLegal(bikeid)) {
			// TODO 计算恢复密码 进行匹配
			if (pass.equals(BikeConstants.RESTORE_CODE)) {
				// 恢复密码正确
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put(BikeConstants.MESSAGE, "恢复成功");
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "恢复密码错误");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "匹配恢复密码:手机号码为空或者单车编码为空");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import com.xxn.entity.Activity;
import com.xxn.entity.Message;
import com.xxn.iservice.IActivityService;
import com.xxn.iservice.IMessageService;
import com.xxn.service.ActivityService;
import com.xxn.service.MessageService;

/**
 * Servlet implementation class GetUserMessage
 */
@WebServlet("/api/user/message")
public class GetUserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserMessage() {
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
		System.out.println("/api/user/message");
		Map<String, Object> map = new HashMap<>();

		IMessageService iMessageService = new MessageService();

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
			Message message = new Message(phone, "", "", "", 0);
			List<Message> list = iMessageService.getAllMessage(message);
			if (list.size() > 0) {
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("data", list);
				iMessageService.updateUserMessage(message);
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "无该用户消息" + phone);
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

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
import com.xxn.entity.Token;
import com.xxn.entity.User;
import com.xxn.iservice.ITokenService;
import com.xxn.iservice.IUserService;
import com.xxn.service.TokenService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class Authentication
 */
/**
 * 
 * @ClassName: Authentication
 * @Description: 上传信息 进行身份认证
 * @author kunsen-lee
 * @date 2016年3月22日 下午3:46:28
 * 
 */
@WebServlet("/api/user/authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
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
		PrintWriter out = response.getWriter();
		System.out.println("/api/user/authentication");
		Map<String, String> map = new HashMap<>();
		IUserService iUserService = new UserService();

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
			String stucardaddr = request.getParameter("stucard");
			String college = request.getParameter("college");
			String stunum = request.getParameter("stunum");
			String name = request.getParameter("name");
			System.out.println("name:" + name + "college:" + college);
			// 数据写入校验
			if (null == stucardaddr)
				stucardaddr = "";
			if (null == college)
				college = "";
			if (null == stunum)
				stunum = "";
			if (null == name)
				name = "";

			if (NormalUtil.isStringLegal(phone)) {
				// 参数均合法
				User user = new User(phone, stunum, stucardaddr,
						BikeConstants.UNCENTIFY, college, name);
				if (iUserService.completeUserInfo(user) > 0) {
					// 信息提交成功后 将用户状态置为待认证状态2
					iUserService.updateUserState(user);
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put(BikeConstants.MESSAGE, "信息提交成功，请等待审核...");
				} else {
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE, "信息提交失败，请重新上传...");
				}
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "手机号码不合法");
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

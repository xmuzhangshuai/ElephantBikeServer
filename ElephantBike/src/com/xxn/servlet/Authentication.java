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
import com.xxn.entity.User;
import com.xxn.iservice.IUserService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class Authentication
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/user/authentication");
		Map<String, String> map = new HashMap<>();
		IUserService iUserService = new UserService();

		String phone = request.getParameter("phone");
		String idcardaddr = request.getParameter("idcard");
		String stucardaddr = request.getParameter("stucard");

		if (NormalUtil.isStringLegal(phone)) {
			if(NormalUtil.isStringLegal(idcardaddr)&&NormalUtil.isStringLegal(stucardaddr)){
				//参数均合法
				User user = new User(phone, null, idcardaddr, stucardaddr, null, null, null);
				if(iUserService.completeUserInfo(user) > 0){
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put(BikeConstants.MESSAGE, "证件上传成功，请等待审核...");
				}
				else {
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE, "证件上传失败，请重新上传...");
				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "图片丢失，请重新上传...");
			}
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

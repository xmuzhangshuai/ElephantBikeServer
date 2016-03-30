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
import com.xxn.iservice.IUserService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class GetUserVipDate
 */
/**
 * 
* @ClassName: GetUserVipDate 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author kunsen-lee
* @date 2016年3月30日 下午8:39:02 
*
 */
@WebServlet("/api/user/vipdate")
public class GetUserVipDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserVipDate() {
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
		System.out.println("/api/user/frozen");
		Map<String, String> map = new HashMap<>();
		IUserService iUserService = new UserService();
		
		String phone = request.getParameter("phone");
		if(NormalUtil.isStringLegal(phone)){
			Map<String, String> val = new HashMap<>();
			val.put("isvip","");
			val.put("vipdate","");
			Map<String, String> query = new HashMap<>();
			query.put("phone", phone);
			map = iUserService.getUserInfo(val, query);
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE,"手机号码不合法");
		}
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

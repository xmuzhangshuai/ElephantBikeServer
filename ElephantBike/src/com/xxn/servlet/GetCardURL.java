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
 * Servlet implementation class GetCardURL
 */
/**
 * 
* @ClassName: GetCardURL 
* @Description: TODO没有缓存图片  获取地址 
* @author kunsen-lee
* @date 2016年4月1日 上午9:39:24 
*
 */
@WebServlet("/api/user/cardurl")
public class GetCardURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCardURL() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/user/cardurl");
		Map<String, String> map = new HashMap<>();
		IUserService iUserService = new UserService();
		
		String phone = request.getParameter("phone");
		if(NormalUtil.isStringLegal(phone)){
			User user = new User(phone, "");
			map = iUserService.getCardURL(user);
			if(map.containsKey("stucard")){
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "无该用户证件信息");
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

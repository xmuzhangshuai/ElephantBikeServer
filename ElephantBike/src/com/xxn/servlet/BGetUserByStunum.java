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
import com.xxn.entity.Message;
import com.xxn.iservice.IMessageService;
import com.xxn.iservice.IUserService;
import com.xxn.service.MessageService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class BGetUserByStunum
 */
@WebServlet("/getuserbystunum")
public class BGetUserByStunum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BGetUserByStunum() {
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
		System.out.println("/getuserbystunum");
		IUserService iUserService = new UserService();
		
		String stunum = request.getParameter("stunum");
		int count = iUserService.getUserExistByStunum(stunum);
		if(count > 0)
			out.print(1);
		else 
			out.print(0); 
		
	}

}

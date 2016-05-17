package com.xxn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tencent.common.MD5;
import com.xxn.dao.AdminDao;
import com.xxn.entity.Admin;
import com.xxn.idao.IAdminDao;

/**
 * Servlet implementation class LogEnter
 */
@WebServlet("/LogEnter")
public class LogEnter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogEnter() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = new Admin(account, password);
		IAdminDao iAdminDao = new AdminDao();
		int count = iAdminDao.login(admin);
		if(count > 0){
			HttpSession session =request.getSession();
			session.setAttribute("user", admin);
			response.sendRedirect("./dxdc/main.jsp");
		}
		else{
			response.sendRedirect("./admin.html");
		}
	}

}

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

import com.xxn.butils.PassDemo;
import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.iservice.IOrderService;
import com.xxn.service.BikeService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class AAAUnlockDemo
 */
@WebServlet("/unlock")
public class AAAUnlockDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AAAUnlockDemo() {
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
		IBikeService iBikeService = new BikeService();
		
		String bikeid = request.getParameter("bikeid");
		Bike bike = new Bike(bikeid,"", 0, 0);
		Bike mn = iBikeService.getBikeMN(bike);
		if(mn!= null){
			int m = mn.getM();
			int length = bikeid.length();
			String z = bikeid.substring(length-2, length);
			System.out.println("后三位"+z);
			String unlockpass = PassDemo.getUnlockPass(m, Integer.parseInt(z));
			System.out.println(unlockpass);
			out.print(unlockpass+"");
		}else{
			out.print("查找不到该编号的单车");
			System.out.println("查找不到该编号的单车");
		}
		
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.service.BikeService;

/**
 * Servlet implementation class AAAResetDemo
 */
@WebServlet("/reset")
public class AAAResetDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AAAResetDemo() {
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
		int result = iBikeService.resetBikeMN(bike);
		if(result == 1){
			out.print("重置单车"+bikeid+"成功");
		}
		else{
			out.print("重置单车"+bikeid+"失败");
		}
	}

}

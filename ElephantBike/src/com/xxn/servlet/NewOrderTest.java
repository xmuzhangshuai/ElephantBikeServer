package com.xxn.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.entity.NewOrder;
import com.xxn.iservice.INewOrderService;
import com.xxn.service.NewOrderService;

/**
 * Servlet implementation class NewOrderTest
 */
@WebServlet("/NewOrderTest")
public class NewOrderTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrderTest() {
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
		// TODO Auto-generated method stub
		
		
//		String date = DateTool.dateToStringYMD(new Date());
		String date = request.getParameter("date");
		System.err.println(date);
		INewOrderService iNewOrderService = new NewOrderService();
		
		int count = 1;
		float totalfee = 1.1f;
		NewOrder newOrder = new NewOrder(date, count, totalfee);
		if(iNewOrderService.getNewOrderCount(date) == 1){
			iNewOrderService.updateNewOrder(newOrder);
		}
		else{
			iNewOrderService.addNewOrder(newOrder);
		}
		
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.FastJsonTool;
import com.xxn.butils.LocationTool;
import com.xxn.entity.Message;
import com.xxn.entity.BikeData;
import com.xxn.iservice.IMessageService;
import com.xxn.iservice.IOrderService;
import com.xxn.service.MessageService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//LocationTool.gouzao();
		String url = this.getServletContext().getRealPath("");
		System.out.println(url);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		System.out.println(phone);
//		IMessageService iMessageService = new MessageService();
//		Message message = new Message(phone, "", "",1);
//		int count = iMessageService.getUnreadMessageCount(message);
//		System.out.println(count);
//		IOrderService iOrderService = new OrderService();
//		String today = "2016-05-03";
//		List<OrderData> result = iOrderService.getOrderData(today);
//		out.print(FastJsonTool.createJsonString(result));
		
	}

}

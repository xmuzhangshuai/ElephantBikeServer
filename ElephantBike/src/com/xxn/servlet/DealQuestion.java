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
import com.xxn.butils.FastJsonTool;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Bike;
import com.xxn.entity.Question;
import com.xxn.entity.User;
import com.xxn.iservice.IBikeService;
import com.xxn.iservice.IQuestionService;
import com.xxn.iservice.IUserService;
import com.xxn.service.BikeService;
import com.xxn.service.QuestionService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class DealQuestion
 */
@WebServlet("/api/question/dealquestion")
public class DealQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealQuestion() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		IQuestionService iQuestionService = new QuestionService();
		
		IBikeService iBikeService = new BikeService();
		String id = request.getParameter("id");
		String bikeid = request.getParameter("bikeid");
		if(iQuestionService.dealQuestion(id) > 0 )
		{
			if(null != bikeid){
				Bike bike = new Bike(bikeid, 1, "", "");
				iBikeService.updateBikeState(bike);
			}
			out.print(1);
		}
		else{
			out.print(-1);
		}  
		out.close();
	}

}

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
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Question;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.IQuestionService;
import com.xxn.service.OrderService;
import com.xxn.service.QuestionService;

/**
 * Servlet implementation class CreateQuestion
 */
@WebServlet("/api/question/ques")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestion() {
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
		System.out.println("/api/question/ques");
		Map<String, String> map = new HashMap<>();
		IQuestionService iQuestionService = new QuestionService();
			
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String type = request.getParameter("type");
		String voiceurl = request.getParameter("voiceurl");
		String ismissing = request.getParameter("ismissing");
		Question question = null;
		if(null == ismissing){
			question = new Question(phone, bikeid, type, voiceurl);
		}
		else{
			if(ismissing.equals("1")){
				//TODO 扣费
				question = new Question(phone, bikeid, type, "");
			}
		}
		
		if(iQuestionService.addQuestion(question) > 0){
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			map.put(BikeConstants.MESSAGE, "问题提交成功,谢谢");
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "问题提交失败，请重新提交一次");
		}
		
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
		
	}

}

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
import com.xxn.entity.Question;
import com.xxn.entity.User;
import com.xxn.iservice.IQuestionService;
import com.xxn.iservice.IUserService;
import com.xxn.service.QuestionService;
import com.xxn.service.UserService;

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
		IUserService iUserService = new UserService();
			
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String type = request.getParameter("type");
		String voiceurl = request.getParameter("voiceurl");
		String ismissing = request.getParameter("ismissing");
		String needfrozen = request.getParameter("needfrozen");
		Question question = null;
		if(null != needfrozen){
			if(null == ismissing){
				question = new Question(phone, bikeid, type, voiceurl,
						0, DateTool.dateToString(new Date()), needfrozen);
			}
			else{
				if(ismissing.equals("1")){
					//TODO 扣费
					question = new Question(phone, bikeid, type, "", 
							0, DateTool.dateToString(new Date()), needfrozen);
				}
			}
			if(iQuestionService.addQuestion(question) > 0){
				if(needfrozen.equals("1"))
				{
					//TODO 冻结操作
					User user = new User(phone, "3");
					if(iUserService.updateUserState(user)>0)
						map.put(BikeConstants.MESSAGE, "问题提交成功,冻结成功");
					else map.put(BikeConstants.MESSAGE, "问题提交成功,冻结失败");
				}
				else map.put(BikeConstants.MESSAGE, "问题提交成功");
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "问题提交失败，请重新提交一次");
			}
		}
		else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "参数不符合，请看api");
		}
		
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
		
	}

}

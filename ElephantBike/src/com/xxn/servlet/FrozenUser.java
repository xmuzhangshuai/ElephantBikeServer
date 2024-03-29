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
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Message;
import com.xxn.entity.User;
import com.xxn.iservice.IMessageService;
import com.xxn.iservice.IUserService;
import com.xxn.service.MessageService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class FrozenUser
 */
@WebServlet("/api/user/frozen")
public class FrozenUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrozenUser() {
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
		System.out.println("/api/user/frozen");
		Map<String, String> map = new HashMap<>();
		IUserService iUserService = new UserService();
		IMessageService iMessageService = new MessageService();
		
		String phone = request.getParameter("phone");
		String state = request.getParameter("state");
		if(null == state){
			state = BikeConstants.FROZEN;
		}
		if(NormalUtil.isStringLegal(phone)){
			User user = new User(phone, state);
			if(iUserService.updateUserState(user) > 0){
				if(state.equals(BikeConstants.FROZEN)||state.equals(BikeConstants.UNNORMAL)){
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put(BikeConstants.MESSAGE, "冻结用户成功");
					
					String title = "冻结通知";
					String createtime = DateTool.dateToString(new Date());
					String content = "你已经被冻结";
					Message message = new Message(phone,title, createtime, content, 1);
					iMessageService.createUserMessage(message);
				}
				if(state.equals(BikeConstants.CENTIFY)){
					String title = "审核通过通知";
					String createtime = DateTool.dateToString(new Date());
					String content = "你已经通过认证";
					Message message = new Message(phone,title,createtime, content, 1);
					iMessageService.createUserMessage(message);
					out.print(1);
				}
				if(state.equals(BikeConstants.NORMAL)){
//					map.put(BikeConstants.MESSAGE, "用户认证成功");
					String title = "审核不通过通知";
					String createtime = DateTool.dateToString(new Date());
					String content = "你审核不通过";
					Message message = new Message(phone, title, createtime, content, 1);
					iMessageService.createUserMessage(message);
					out.print(1);
				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "用户状态变更失败");
			}
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码不合法");
		}
		System.out.println(FastJsonTool.createJsonString(map));
		if(!map.isEmpty())
			out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

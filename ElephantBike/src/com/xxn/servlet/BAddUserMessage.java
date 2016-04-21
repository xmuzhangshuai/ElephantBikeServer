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
import com.xxn.iservice.IMessageService;
import com.xxn.service.MessageService;

/**
 * Servlet implementation class BAddUserMessage
 */
/**
 * 
* @ClassName: BAddUserMessage 
* @Description: 消息中心 
* @author kunsen-lee
* @date 2016年4月12日 下午8:12:18 
*
 */
@WebServlet("/addusermessage")
public class BAddUserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BAddUserMessage() {
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
		System.out.println("/addusermessage");
		Map<String, Object> map = new HashMap<>();
		IMessageService iMessageService = new MessageService();

		String phone = request.getParameter("phone");
		
		if (NormalUtil.isStringLegal(phone)) {
			
			String title = "审核通过通知";
			String createtime = DateTool.dateToString(new Date());
			String content = "你已经通过审核";
			int state = 1;
			Message message = new Message(phone, title,createtime, content, state);
			int result = iMessageService.createUserMessage(message);
			if(result > 0){
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put(BikeConstants.MESSAGE, "用户消息创建成功");
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "用户消息创建失败");
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

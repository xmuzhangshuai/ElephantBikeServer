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

import com.xxn.butils.NormalUtil;
import com.xxn.iservice.IAnnounceService;
import com.xxn.service.AnnounceService;

/**
 * Servlet implementation class BAddAnnounce
 */
@WebServlet("/publishcontent")
public class BAddAnnounce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BAddAnnounce() {
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
		IAnnounceService iAnnounceService = new AnnounceService();
		
		String content = request.getParameter("n_content");
		System.out.println(content);
		String type = request.getParameter("type"); 
		System.out.println(type);
		if(NormalUtil.isStringLegal(content)){
			Map<String, String> val = new HashMap<>();
			Map<String, String> query = new HashMap<>();
			if(type.equals("helps")){
				val.put("helpcontent", content);
			}
			if(type.equals("protocol")){
				val.put("protocolcontent", content);
			}
			int result = iAnnounceService.updateAnnounce(val, query);
			if(result == 1)
				out.print("发布成功");
			else out.print("发布失败，请重新发布");
			
		}
		else{
			out.print("发布内容为空，请重新发布");
		}
		
	}

}

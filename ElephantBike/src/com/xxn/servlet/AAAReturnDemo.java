package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.NormalUtil;
import com.xxn.butils.PassDemo;
import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.service.BikeService;

/**
 * Servlet implementation class AAAReturnDemo
 */
@WebServlet("/return")
public class AAAReturnDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AAAReturnDemo() {
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
		String clientpass = request.getParameter("pass");
//		String t = request.getParameter("t");
		int t = DateTool.getT();
		Bike resBike = iBikeService.getBikeMN(bike);
		if(resBike!=null){
			int n = resBike.getN();
			int length = bikeid.length();
			String z = bikeid.substring(length-2, length);
			String serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), t);
			System.out.println("服务器密码:"+serverpass);
			out.println("n:"+n);
			//135位和上一次的相同，提示不让还车，必须恢复单车
			String lasspass = resBike.getLastpass();
			System.out.println("lasspass:"+lasspass+"--clientpass:"+clientpass);
			if(NormalUtil.isStringLegal(lasspass)){
				if(clientpass.charAt(0)==lasspass.charAt(0)&&clientpass.charAt(2)==lasspass.charAt(2)
						&&clientpass.charAt(4)==lasspass.charAt(4)){
					System.out.println("你已经有了新的解锁密码，请先恢复单车，不能直接执行还车操作");
					out.println("你已经有了新的解锁密码，请先恢复单车，不能直接执行还车操作");
				}
				else{
					if(serverpass.equals(clientpass)){
						int result = iBikeService.addBikeMN(bike);
						if(result == 1){
							out.print("z"+z+"\n serverpass:"+serverpass);
							out.print("m , n 各加1 成功");
						}
					}
					else{
						out.print("还车密码错误");
						out.print("z"+z+"\n serverpass:"+serverpass);
					}
				}
			}
			else{
				if(serverpass.equals(clientpass)){
					int result = iBikeService.addBikeMN(bike);
					if(result == 1){
						out.print("z"+z+"\n serverpass:"+serverpass);
						out.print("m , n 各加1 成功");
					}
				}
				else{
					out.print("还车密码错误");
					out.print("z"+z+"\n serverpass:"+serverpass);
				}
			}
		}
		else{
			out.print("查找不到该编号的单车");
		}
	}

}

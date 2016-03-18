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
@WebServlet("/restore")
public class AAARestoreDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AAARestoreDemo() {
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
			if(serverpass.equals(clientpass)){
				//设置密码，写入数据库
				bike.setLastpass(serverpass);
				int result = iBikeService.addBikeMN(bike);
				if(result == 1){
					out.println("z"+z+"\n serverpass:"+serverpass);
					out.println("---恢复密码正确------m , n 各加1 ---已经记录这一次还车密码---成功");
					String unlockpass = PassDemo.getUnlockPass(resBike.getM()+1, Integer.parseInt(z));
					out.println("提供的解锁密码是:"+unlockpass);
				}
			}
			else{
				String lasspass = resBike.getLastpass();
				System.out.println("client:"+clientpass+"--lasspass:"+lasspass);
				if(NormalUtil.isStringLegal(lasspass)){
					//数据库的上一次正确恢复密码不为空
					if(clientpass.charAt(0)==lasspass.charAt(0)&&clientpass.charAt(2)==lasspass.charAt(2)
							&&clientpass.charAt(4)==lasspass.charAt(4)){
						System.out.println("135位相同");
						String unlockpass = PassDemo.getUnlockPass(resBike.getM(), Integer.parseInt(z));
						out.println("提供的解锁密码是:"+unlockpass);
					}
					else{
						out.print("恢复密码错误");
						out.print("z"+z+"\n serverpass:"+serverpass);	
					}
				}
				else{
					out.print("恢复密码错误");
					out.print("z"+z+"\n serverpass:"+serverpass);
				}
			}
		}
		else{
			out.print("查找不到该编号的单车");
		}
	}

}

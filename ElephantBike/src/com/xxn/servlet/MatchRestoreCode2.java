package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.butils.PassDemo;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.service.BikeService;

/**
 * Servlet implementation class MatchRestoreCode
 */
@WebServlet("/api/pass/restorecode2")
public class MatchRestoreCode2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchRestoreCode2() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/pass/returncode");
		Map<String, String> map = new HashMap<>();
		IBikeService iBikeService = new BikeService();
		
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String pass = request.getParameter("pass");

		if (NormalUtil.isStringLegal(phone) && NormalUtil.isStringLegal(bikeid)) {
			// 计算恢复密码 进行匹配
			Bike bike = new Bike(bikeid,"", 0, 0);
			String clientpass = request.getParameter("pass");
			
			//获取两个时间t
			List<Integer> tlist = DateTool.getT();
			
			Bike resBike = iBikeService.getBikeMN(bike);
			if(resBike!=null){
				int n = resBike.getN();
				int length = bikeid.length();
				String z = bikeid.substring(length-2, length);
				String serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), tlist.get(0));
				String _serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), tlist.get(1));
				System.out.println("服务器密码:"+serverpass);
				if(serverpass.equals(clientpass)||_serverpass.equals(clientpass)){
					//设置密码，写入数据库
					if(serverpass.equals(clientpass))
					{
						bike.setLastpass(serverpass);
						System.out.println("使用正常密码");
					}
					if(_serverpass.equals(clientpass))
					{
						bike.setLastpass(_serverpass);
						System.out.println("使用备案密码");
					}
					
					int result = iBikeService.addBikeMN(bike);
					if(result == 1){
						String unlockpass = PassDemo.getUnlockPass(resBike.getM()+1, Integer.parseInt(z));
						map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
						map.put("pass", unlockpass);
					}
					else{
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "恢复密码正确，恢复密码写入数据库失败，请重试");
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
							map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
							map.put("pass", unlockpass);
						}
						else{
							map.put(BikeConstants.STATUS, BikeConstants.FAIL);
							map.put(BikeConstants.MESSAGE, "恢复密码错误");
						}
					}
					else{
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "恢复密码错误");
					}
				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "查找不到该单车编号");
			}
		} 
		else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "匹配恢复密码:手机号码为空或者单车编码为空");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

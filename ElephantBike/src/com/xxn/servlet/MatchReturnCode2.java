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
 * Servlet implementation class MatchReturnCode
 */
@WebServlet("/api/pass/returncode2")
public class MatchReturnCode2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchReturnCode2() {
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
		System.out.println("/api/pass/returncode");
		Map<String, String> map = new HashMap<>();
		
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String clientpass = request.getParameter("pass");
		IBikeService iBikeService = new BikeService();
		
		if(NormalUtil.isStringLegal(phone)&&NormalUtil.isStringLegal(bikeid)){
			Bike bike = new Bike(bikeid,"", 0, 0);
			//错时验证两个t
			List<Integer> tlist = DateTool.getT();
			
			Bike resBike = iBikeService.getBikeMN(bike);
			if(resBike!=null){
				int n = resBike.getN();
				int length = bikeid.length();
				String z = bikeid.substring(length-2, length);
				String serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), tlist.get(0));
				String _serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), tlist.get(1));
				
				System.out.println("服务器密码:"+serverpass+"--备用密码:"+_serverpass);
				
				//135位和上一次的相同，提示不让还车，必须恢复单车
				String lasspass = resBike.getLastpass();
				System.out.println("lasspass:"+lasspass+"--clientpass:"+clientpass);
				if(NormalUtil.isStringLegal(lasspass)){
					if(clientpass.charAt(0)==lasspass.charAt(0)&&clientpass.charAt(2)==lasspass.charAt(2)
							&&clientpass.charAt(4)==lasspass.charAt(4)){
						System.out.println("你已经有了新的解锁密码，请先恢复单车，不能直接执行还车操作");
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "你已经有了新的解锁密码，请先恢复单车，不能直接执行还车操作");
					}
					else{
						if(serverpass.equals(clientpass)||_serverpass.equals(clientpass)){
							int result = iBikeService.addBikeMN(bike);
							if(result == 1){
								if(true){
									//还车成功，计算各种费用
									map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
									map.put(BikeConstants.MESSAGE, "还车成功,m,n各加1");
								}
							}
							else{
								map.put(BikeConstants.STATUS, BikeConstants.FAIL);
								map.put(BikeConstants.MESSAGE, "还车密码正确,但m,n各加1失败，请重试");
							}
						}
						else{
							map.put(BikeConstants.STATUS, BikeConstants.FAIL);
							map.put(BikeConstants.MESSAGE, "还车密码错误");
						}
					}
				}
				else{
					if(serverpass.equals(clientpass)||_serverpass.equals(clientpass)){
						int result = iBikeService.addBikeMN(bike);
						if(result == 1){
							map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
							map.put(BikeConstants.MESSAGE, "还车成功");
						}
					}
					else{
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "还车密码错误");
					}
				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "查找不到该编号的单车");
			}
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "匹配还车密码:手机号码为空或者单车编码为空");
		}
		
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();

	}

}

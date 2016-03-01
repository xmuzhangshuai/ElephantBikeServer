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
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;

/**
 * Servlet implementation class MatchReturnCode
 */
@WebServlet("/api/pass/returncode")
public class MatchReturnCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchReturnCode() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/pass/returncode");
		Map<String, String> map = new HashMap<>();
		
		String phone = request.getParameter("phone");
		String bikeid = request.getParameter("bikeid");
		String pass = request.getParameter("pass");
		
		if(NormalUtil.isStringLegal(phone)&&NormalUtil.isStringLegal(bikeid)){
			//TODO 计算还车密码 进行匹配
			if(pass.equals(BikeConstants.RETURN_CODE)){
				//还车密码正确之后，需要判定位置是否正确
				//TODO 根据单车所属学校判定是否还车位置正确
				if(true){
					//还车成功，计算各种费用
					
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put(BikeConstants.MESSAGE, "还车成功");
				}
//				else{
//					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
//					map.put(BikeConstants.MESSAGE, "单车未停放在校园");
//				}
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "还车密码错误");
			}
			
		}else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "匹配还车密码:手机号码为空或者单车编码为空");
		}
		
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();

	}

}

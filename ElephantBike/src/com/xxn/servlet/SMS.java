package com.xxn.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;

/**
 * Servlet implementation class SMS
 */
@WebServlet("/api/msg/sms")
public class SMS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMS() {
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
		
		HashMap result = null;
		Map<String, String> map = new HashMap<>();
		
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		// 初始化服务器地址和端口，沙盒环境配置成sandboxapp.cloopen.com，
		//生产环境配置成app.cloopen.com，端口都是8883.
		restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.setAccount("8a48b55152f73add0152ff0804121100", 
				"1cda62228f8040d4a0b0929eaf00b96b");
		restAPI.setAppId("8a48b55152f73add0152ff0a3c4d1109");
		
		String phone = request.getParameter("phone");
		
		String randomNumer = NormalUtil.generateRandom();
		//randomNumer随机产生
		result = restAPI.sendTemplateSMS(phone, "1"
				, new String[]{randomNumer,"60"});
		
		
		System.out.println("SDKTestGetSubAccounts result="+result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息(Map)
			ServletContext application = this.getServletContext();
			application.setAttribute(phone, randomNumer);
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			map.put(BikeConstants.MESSAGE, "短信发送成功,请查收");
		}else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "短信发送失败,请重新获取");
		}
	}

}

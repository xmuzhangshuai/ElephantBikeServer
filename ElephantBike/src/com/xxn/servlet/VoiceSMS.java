package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;

/**
 * Servlet implementation class VoiceSMS
 */
@WebServlet("/VoiceSMS")
public class VoiceSMS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoiceSMS() {
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
		HashMap result = null;
		Map<String, String> map = new HashMap<>();
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");
		restAPI.setAccount(BikeConstants.YUN_ACCOUNT_SID,
				BikeConstants.YUN_AUTH_TOKEN);
		restAPI.setAppId(BikeConstants.YUN_APP_ID);

		String phone = request.getParameter("phone");
		String randomNumer = NormalUtil.generateRandom();
		result = restAPI.voiceVerify(randomNumer, phone, "18046175915", "3",
				"状态通知回调地址", "zh", "第三方私有数据");
		System.out.println("SDKTestVoiceVerify result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息(Map)
			ServletContext application = this.getServletContext();
			application.setAttribute(phone, randomNumer);
			map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
			map.put(BikeConstants.MESSAGE, "短信发送成功,请查收");
		}else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "短信发送失败,请重新获取\n"+"错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}
}

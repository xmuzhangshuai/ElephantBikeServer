package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.XMLUtil;
import com.xxn.iservice.IUserService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class WXOrderResponse
 */
@WebServlet("/api/pay/vipresponse")
public class WXPayVipResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXPayVipResponse() {
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
		PrintWriter out = response.getWriter();
		Map<String, String> result = new HashMap<>();
        IUserService iUserService = new UserService();
		String inputLine = "";
		String notityXml = "";

		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!notityXml.isEmpty()){
			Map<String, Object> xmlMap = XMLUtil.xmltoMap(notityXml);
			if(xmlMap.get("return_code").equals("SUCCESS")){
				String orderid = xmlMap.get("out_trade_no").toString();
				String phone = orderid.split("_")[0];
				String month = orderid.split("_")[2];
				//两步操作，写入钱包余额和明细表
				int m = Integer.parseInt(month);
    			Map<String, String> val = new HashMap<>();
    			val.put("isvip","");
    			val.put("vipdate","");
    			Map<String, String> query = new HashMap<>();
    			query.put("phone", phone);
    			result = iUserService.getUserInfo(val, query);
    			val.clear();
    			query.clear();
    			Date nowdate = null, date=null;
    			GregorianCalendar gc = new GregorianCalendar();
    			//获取是否会员
    			if(result.get("isvip").equals("1")){
    				try {
    					date = new SimpleDateFormat("yyyy-MM-dd").parse(result.get("vipdate"));
    				} catch (ParseException e) {
    					e.printStackTrace();
    				}
    			}
    			else {
    				nowdate = new Date();
    				date = DateTool.getYMDDate(nowdate);
    			}
    			//时间增加
    			gc.setTime(date);
    			gc.add(2, m);
    			String vipdate = DateTool.dateToStringYMD(gc.getTime());
    			
    			val.put("isvip", "1");
    			val.put("vipdate", vipdate);
    			query.put("phone", phone);
    			int updateresult = iUserService.updateUser(val, query);
				if(updateresult > 0){
					ServletContext application = this.getServletContext();
					application.setAttribute(orderid, orderid);
					String res = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
					out.write(res);
				}
				else{
					String res = "<xml><return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[FAIL]]></return_msg></xml>";
					out.write(res);
				}
			}
		}
	}

}

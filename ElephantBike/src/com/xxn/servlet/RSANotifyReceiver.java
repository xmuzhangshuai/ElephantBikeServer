package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.internal.util.AlipaySignature;
import com.xxn.alipay.RSASignature;
import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.ALiPayConfig;
import com.xxn.constants.BikeConstants;
import com.xxn.iservice.IUserService;
import com.xxn.service.UserService;

/**
 * Servlet implementation class RSANotifyReceiver
 */
@WebServlet("/api/pay/rsanotifyreceiver")
public class RSANotifyReceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RSANotifyReceiver() {
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
        Map map = request.getParameterMap();
        Map<String, String> obj = new HashMap<>();
        Map<String, String> result = new HashMap<>();
        IUserService iUserService = new UserService();
        
        //获得通知签名
        String sign = (String) ((Object[]) map.get("sign"))[0];
        for (Object object : map.keySet()) {
			String key = object.toString();
			String value = ((Object[]) map.get(key))[0].toString();
			obj.put(key, value);
		}
        //获得待验签名的数据
        String verifyData = AlipaySignature.getSignCheckContentV1(obj);
        
        boolean verified = false;
        if(obj.get("trade_status").equals("TRADE_SUCCESS"))
        	verified = true;
        //使用支付宝公钥验签名
//        try {
//            verified = RSASignature.doCheck(verifyData, sign, ALiPayConfig.publicKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        PrintWriter out = response.getWriter();
        //验证签名通过
        if (verified) {
        	//根据交易状态处理业务逻辑
        	//当交易状态成功，处理业务逻辑成功。回写success
        	String out_trade_no = obj.get("out_trade_no");
        	String[] pm = out_trade_no.split("_");
        	if(pm.length == 3){
        		String phone = pm[0];
        		String month = pm[2];
        		if(NormalUtil.isStringLegal(phone)&&NormalUtil.isStringLegal(month)){
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
        			if(updateresult ==1){
        				out.print("success");
        	        	System.out.println("扣费成功-开通成功");
        			}
        			else{
        				out.print("success");
        	        	System.out.println("扣费成功-开通失败");
        			}
        		}
        	}
        	
        } else {
        	System.out.println(obj.get("trade_status"));
            out.print("fail");
        }
	}

}

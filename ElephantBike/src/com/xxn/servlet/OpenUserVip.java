//package com.xxn.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.xxn.butils.DateTool;
//import com.xxn.butils.FastJsonTool;
//import com.xxn.butils.NormalUtil;
//import com.xxn.constants.BikeConstants;
//import com.xxn.iservice.IUserService;
//import com.xxn.service.UserService;
//
///**
// * Servlet implementation class OpenUserVip
// */
///**
// * 
//* @ClassName: OpenUserVip 
//* @Description: TODO(这里用一句话描述这个类的作用)  会员开通测试类
//* @author kunsen-lee
//* @date 2016年3月30日 下午8:42:40 
//*
// */
//@WebServlet("/api/user/openvip")
//public class OpenUserVip extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public OpenUserVip() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		Map<String, String> map = new HashMap<>();
//		PrintWriter out = response.getWriter();
//		IUserService iUserService = new UserService();
//		
//		String phone = request.getParameter("phone");
//		String month = request.getParameter("month");
//		if(NormalUtil.isStringLegal(phone)&&NormalUtil.isStringLegal(month)){
//			int m = Integer.parseInt(month);
//			
//			Map<String, String> val = new HashMap<>();
//			val.put("isvip","");
//			val.put("vipdate","");
//			Map<String, String> query = new HashMap<>();
//			query.put("phone", phone);
//			map = iUserService.getUserInfo(val, query);
//			val.clear();
//			query.clear();
//			
//			Date nowdate = null, date=null;
//			GregorianCalendar gc = new GregorianCalendar();
//			//获取是否会员
//			if(map.get("isvip").equals("1")){
//				try {
//					date = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("vipdate"));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			else {
//				nowdate = new Date();
//				date = DateTool.getYMDDate(nowdate);
//			}
//			//时间增加
//			gc.setTime(date);
//			gc.add(2, m);
//			String vipdate = DateTool.dateToStringYMD(gc.getTime());
//			
//			val.put("isvip", "1");
//			val.put("vipdate", vipdate);
//			query.put("phone", phone);
//			int result = iUserService.updateUser(val, query);
//			if(result ==1){
//				map.clear();
//				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
//				map.put("vipdate", vipdate);
//			}
//			else{
//				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
//				map.put(BikeConstants.MESSAGE, "会员开通失败");
//			}
//		}
//		else{
//			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
//			map.put(BikeConstants.MESSAGE, "参数有错");
//		}
//		System.out.println(FastJsonTool.createJsonString(map));
//		out.print(FastJsonTool.createJsonString(map));
//		out.close();
//	}
//
//}

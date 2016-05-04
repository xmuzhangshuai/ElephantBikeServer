package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tencent.common.MD5;
import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Message;
import com.xxn.entity.Token;
import com.xxn.entity.User;
import com.xxn.entity.Wallet;
import com.xxn.iservice.IMessageService;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.ITokenService;
import com.xxn.iservice.IUserService;
import com.xxn.iservice.IWalletService;
import com.xxn.service.MessageService;
import com.xxn.service.OrderService;
import com.xxn.service.TokenService;
import com.xxn.service.UserService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/api/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
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
		System.out.println("/api/user/login");
		Map<String, Object> map = new HashMap<>();
		IUserService iUserService = new UserService();
		IOrderService iOrderService = new OrderService();
		IWalletService iWalletService = new WalletService();
		IMessageService iMessageService = new MessageService();
		ITokenService iTokenService = new TokenService();
		
		String phone = request.getParameter("phone");
		String islogin = request.getParameter("islogin");
		String verify_code = request.getParameter("verify_code");
		
		String isfrozen = "0", isfinish = "0", ispay = "0";
		String name = "", isvip = "0",college="";
		String vipdate = "";
		// 登录情况分大两种 1.已经登录过 2.退出登录，清空缓存，第一次登录，卸载app等--需要验证码
		if (NormalUtil.isStringLegal(phone)
				&& NormalUtil.isStringLegal(islogin)) {
			
			//-------------------------token---------------------------------- 
			ServletContext application = this.getServletContext();
			String tokenString = MD5.MD5Encode(phone+DateTool.dateToString(new Date()));
			application.setAttribute("token"+phone, tokenString);
			//token实体
			Token token = new Token(phone, tokenString, DateTool.dateToString(new Date()));
			
			User user = new User(phone, isfrozen,
					DateTool.dateToString(new Date()));
			// 电话号码不为空
			Message message = new Message(phone, "","", "",1);
			int messagecount = iMessageService.getUnreadMessageCount(message);
			
			if (islogin.equals("1")) {
				// 已经登录---需要拿到3个变量
				iTokenService.updateToken(token);
				Map<String, String> valUserInfo = new HashMap<>();
				Map<String, String> queryUserInfo = new HashMap<>();
				// 需要get的信息
				valUserInfo.put("isvip", "");
				valUserInfo.put("vipdate", "");
				valUserInfo.put("name", "");
				valUserInfo.put("userstate", "");
				valUserInfo.put("college", "");
				queryUserInfo.put("phone", phone);

				Map<String, String> resMap = iUserService.getUserInfo(valUserInfo,
						queryUserInfo);
				if (resMap.containsKey("userstate"))
					isfrozen = resMap.get("userstate");
				if (resMap.containsKey("name"))
					name = resMap.get("name");
				if (resMap.containsKey("isvip")){
					isvip = resMap.get("isvip");
					if(isvip.equals("1")){
						vipdate = resMap.get("vipdate");
						if(DateTool.compareDate(vipdate))
							isvip = "1";
						else isvip = "0";
					}
				}
					
				if (resMap.containsKey("college"))
					college = resMap.get("college");
				Map<String, String> queryisfinish = new HashMap<>();
				queryisfinish.put("phone", phone);
				queryisfinish.put("finishtime", null);
				if (iOrderService.getOrderCount(queryisfinish) > 0)
					isfinish = "1";

				Map<String, String> queryispay = new HashMap<>();
				queryispay.put("phone", phone);
				queryispay.put("paymode", null);
				if (iOrderService.getOrderCount(queryispay) > 0)
					ispay = "1";

				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("isfrozen", isfrozen);
				map.put("isfinish", isfinish);
				map.put("ispay", ispay);
				map.put("name", name);
				map.put("isvip", isvip);
				map.put("college", college);
				map.put("ismessage", messagecount);
				map.put("access_token", tokenString);
			} else {
				// 先判断验证码是否正确
				String yzm = (String) application.getAttribute(phone);
				System.out.println("验证码:" + yzm);
				if (verify_code.equals(yzm)) {
					// 先去判断是否已经有该用户
					int res = iUserService.getUserExist(user);
					if (res == 1) {
						iTokenService.updateToken(token);
						// 已经有该用户--则不写入新增用户表---获取用户状态 是否结束 是否付款
						Map<String, String> valUserInfo = new HashMap<>();
						Map<String, String> queryUserInfo = new HashMap<>();
						// 需要get的信息
						valUserInfo.put("isvip", "");
						valUserInfo.put("name", "");
						valUserInfo.put("userstate", "");
						valUserInfo.put("college", "");
						
						queryUserInfo.put("phone", phone);
						Map<String, String> resMap = iUserService.getUserInfo(valUserInfo,
								queryUserInfo);
						if (resMap.containsKey("userstate"))
							isfrozen = resMap.get("userstate");
						if (resMap.containsKey("name"))
							name = resMap.get("name");
						if (resMap.containsKey("isvip")){
							isvip = resMap.get("isvip");
							if(isvip.equals("1")){
								vipdate = resMap.get("vipdate");
								if(DateTool.compareDate(vipdate))
									isvip = "1";
								else isvip = "0";
							}
						}
						if (resMap.containsKey("college"))
							college = resMap.get("college");
						Map<String, String> queryisfinish = new HashMap<>();
						queryisfinish.put("phone", phone);
						queryisfinish.put("finishtime", null);
						if (iOrderService.getOrderCount(queryisfinish) > 0)
							isfinish = "1";

						Map<String, String> queryispay = new HashMap<>();
						queryispay.put("phone", phone);
						queryispay.put("paymode", null);
						if (iOrderService.getOrderCount(queryispay) > 0)
							ispay = "1";

						map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
						map.put("isfrozen", isfrozen);
						map.put("isfinish", isfinish);
						map.put("ispay", ispay);
						map.put("name", name);
						map.put("isvip", isvip);
						map.put("college", college);
						map.put("ismessage", messagecount);
						map.put("access_token", tokenString);

					} else if (res == 0) {
						// 该用户未注册
						//未注册 则采用插入方式记录token
						iTokenService.createToken(token);
						
						Wallet wallet = new Wallet(phone, 0.0f, 0);
						if (iUserService.addUser(user) > 0
								&& iWalletService.createWallet(wallet) > 0) {
							// 注册写入成功 --创建个人钱包
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd");
							String joindate = sdf.format(new Date());
							if (iUserService.getNewUserCount(joindate) == 0) {
								iUserService.addNewUserCount(joindate);
							} else {
								iUserService.updateNewUser(joindate);
							}
							System.out.println("注册写入成功--钱包创建成功");
							map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
							map.put("isfrozen", isfrozen);
							map.put("isfinish", isfinish);
							map.put("ispay", ispay);
							map.put("name", name);
							map.put("isvip", isvip);
							map.put("college", college);
							map.put("ismessage", messagecount);
							map.put("access_token", tokenString);
						} else {
							System.out.println("注册写入失败");
							map.put(BikeConstants.STATUS, BikeConstants.FAIL);
							map.put(BikeConstants.MESSAGE, "用户注册失败");
						}
					}
				} else {
					// 验证码不正确
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE, "验证码不正确");
				}
			}
		} else {
			// 电话号码为空
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "手机号码为空或者是否登录参数不合法");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

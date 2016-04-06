package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.XMLUtil;
import com.xxn.entity.Wallet;
import com.xxn.iservice.IWalletService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class WXOrderResponse
 */
@WebServlet("/api/pay/rechargeresponse")
public class WXRechargeResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXRechargeResponse() {
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
		IWalletService iWalletService = new WalletService();
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
				String total_fee = xmlMap.get("total_fee").toString();
				String phone = orderid.split("-")[0];
				//两步操作，写入钱包余额和明细表
				Wallet wallet = new Wallet(phone, Float.parseFloat(total_fee), 0);
				if(iWalletService.rechargeWallet(wallet) > 0){
					Wallet wallet2 = new Wallet(phone, Float.parseFloat(total_fee),
							DateTool.dateToString(new Date()));
					iWalletService.addWalletList(wallet2);
					ServletContext application = this.getServletContext();
					application.setAttribute("query"+phone, xmlMap.get("transaction_id"));
					
					String res = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
					out.write(res);
				}
				else{
					System.out.println("充值过程钱包写入失败");
				}
				System.out.println(notityXml);
			}
		}
	}

}

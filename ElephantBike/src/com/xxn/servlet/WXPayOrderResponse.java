package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.xxn.entity.NewOrder;
import com.xxn.entity.Wallet;
import com.xxn.iservice.INewOrderService;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.IWalletService;
import com.xxn.service.NewOrderService;
import com.xxn.service.OrderService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class WXOrderResponse
 */
@WebServlet("/api/pay/response")
public class WXPayOrderResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXPayOrderResponse() {
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
		IOrderService iOrderService = new OrderService();
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
				//TODO 签名校验
				String orderid = xmlMap.get("out_trade_no").toString();
				String total_fee = xmlMap.get("total_fee").toString();
				Map<String, String> val = new HashMap<>();
				Map<String, String> query = new HashMap<>();
				val.put("paymode", "微信支付");
				query.put("orderid", orderid);
				
				Map<String, String> _phone = new HashMap<>();
				Map<String, String> _orderid = new HashMap<>();
				Map<String, String> _resPhone = new HashMap<>();
				_phone.put("phone", "");
				_orderid.put("orderid",orderid);
				_resPhone = iOrderService.getOrderInfo(_phone, _orderid);
				String phone = "";
				if(_resPhone.containsKey("phone")){
					phone = _resPhone.get("phone");
				}
				
				ServletContext application = this.getServletContext();
				application.setAttribute(orderid, orderid);
				
//				Wallet wallet2 = new Wallet(phone, -Float.parseFloat(total_fee)/100,
//						DateTool.dateToString(new Date()));
				
				if(iOrderService.updateOrder(val, query) > 0){
					
					String res = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
					out.write(res);
//					iWalletService.addWalletList(wallet2);
					//写入新增订单表
					INewOrderService iNewOrderService = new NewOrderService();
					String date = DateTool.dateToStringYMD(new Date());
					NewOrder newOrder = new NewOrder(date, 1, Float.parseFloat(total_fee)/100);
					if(iNewOrderService.getNewOrderCount(date) == 1){
						iNewOrderService.updateNewOrder(newOrder);
					}
					else{
						iNewOrderService.addNewOrder(newOrder);
					}
				}
//				System.out.println(notityXml);
			}
		}
	}

}

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
import com.xxn.butils.NormalUtil;
import com.xxn.entity.Wallet;
import com.xxn.iservice.IOrderService;
import com.xxn.iservice.IWalletService;
import com.xxn.service.OrderService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class RSANotifyReceiver
 */
/**
 * 
* @ClassName: ALiPay 
* @Description: TODO 订单支付 异步回调 
* @author kunsen-lee
* @date 2016年4月11日 下午12:05:19 
*
 */
@WebServlet("/api/pay/alipay")
public class ALiPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ALiPay() {
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
        
		IWalletService iWalletService = new WalletService();
		IOrderService iOrderService = new OrderService();
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> obj = new HashMap<>();
        for (Object object : map.keySet()) {
			String key = object.toString();
			String value = ((Object[]) map.get(key))[0].toString();
			obj.put(key, value);
		}
        
        boolean verified = false;
        if(obj.get("trade_status").equals("TRADE_SUCCESS"))
        	verified = true;
        PrintWriter out = response.getWriter();
        //验证签名通过
        if (verified) {
        	//根据交易状态处理业务逻辑
        	//当交易状态成功，处理业务逻辑成功。回写success
        	String out_trade_no = obj.get("out_trade_no");
        	String total_fee = obj.get("total_fee");
        	System.out.println("充值:out_trade_no"+out_trade_no+"== total_fee:"+total_fee);
        	
        	String[] pm = out_trade_no.split("_");
        	if(pm.length == 2){
        		String phone = pm[0];
        		if(NormalUtil.isStringLegal(phone)){
        			//两步操作，写入明细表和订单完成
        			ServletContext application = this.getServletContext();
					application.setAttribute(out_trade_no, out_trade_no);
    				
//    				Wallet wallet2 = new Wallet(phone, -Float.parseFloat(total_fee),
//    						DateTool.dateToString(new Date()));
    				Map<String, String> val = new HashMap<>();
    				Map<String, String> query = new HashMap<>();
    				val.put("paymode", "支付宝支付");
    				query.put("orderid", out_trade_no);
    				if(iOrderService.updateOrder(val, query) > 0){
    					//iWalletService.addWalletList(wallet2);
    					System.out.println("扣费成功-订单修改成功");
    					out.print("success");
    				}
    				else{
    					out.print("success");
    					System.out.println("扣费成功-订单修改失败");
    				}
        		}
        		else{
        			out.print("success");
					System.out.println("扣费成功-订单修改失败--phone:"+phone);
        		}
        	}
        } else {
        	out.print("fail");
        	System.out.println(obj.get("trade_status"));
        }
	}

}

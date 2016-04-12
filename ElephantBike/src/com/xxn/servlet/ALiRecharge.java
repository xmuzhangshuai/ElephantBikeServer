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
import com.xxn.iservice.IWalletService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class RSANotifyReceiver
 */
/**
 * 
* @ClassName: ALiRecharge 
* @Description:  支付宝 充值 异步回调 
* @author kunsen-lee
* @date 2016年4月11日 下午12:06:03 
*
 */
@WebServlet("/api/pay/alirecharge")
public class ALiRecharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ALiRecharge() {
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
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> obj = new HashMap<>();
        //获得通知签名
//        String sign = (String) ((Object[]) map.get("sign"))[0];
        for (Object object : map.keySet()) {
			String key = object.toString();
			String value = ((Object[]) map.get(key))[0].toString();
			obj.put(key, value);
		}
        //获得待验签名的数据
//        String verifyData = AlipaySignature.getSignCheckContentV1(obj);
        
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
        			//两步操作，写入钱包余额和明细表
    				Wallet wallet = new Wallet(phone, Float.parseFloat(total_fee), 0);
    				if(iWalletService.rechargeWallet(wallet) > 0){
    					Wallet wallet2 = new Wallet(phone, Float.parseFloat(total_fee),
    							DateTool.dateToString(new Date()));
    					iWalletService.addWalletList(wallet2);
    					
    					ServletContext application = this.getServletContext();
    					application.setAttribute(out_trade_no, out_trade_no);
    					out.print("success");
        	        	System.out.println("扣费成功-充值成功");
    				}
    				else{
    					out.print("success");
    					System.out.println("扣费成功-充值失败");
    				}
        		}
        	}
        } else {
        	out.print("fail");
        	System.out.println(obj.get("trade_status"));
        }
	}

}

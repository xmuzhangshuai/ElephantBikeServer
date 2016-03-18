package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Wallet;
import com.xxn.iservice.IWalletService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class BUserBatchBalance
 */
@WebServlet("/batchbalance")
public class BUserBatchBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BUserBatchBalance() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		String resString = "0";
		IWalletService iWalletService = new WalletService();

		String value = request.getParameter("val");
		if(NormalUtil.isStringLegal(value)&&NormalUtil.isStringFloat(value)){
			float val = Float.parseFloat(value);
			if(val > 0.0f){
				int result = iWalletService.batchRecharge(val);
				if(result > 0)
					resString = "1";
				else resString = "批量充值失败";
			}
			else{
				resString = "充值金额不合法";
			}
		}
		else{
			resString = "充值金额不合法";
		}
		System.out.println(resString);
		out.print(resString);
		out.close();
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.FastJsonTool;
import com.xxn.butils.GridDataModel;
import com.xxn.butils.NormalUtil;
import com.xxn.entity.BikeData;
import com.xxn.iservice.IOrderService;
import com.xxn.service.OrderService;

/**
 * Servlet implementation class BGetBikeData
 */
@WebServlet("/getbikedata")
public class BGetBikeData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BGetBikeData() {
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
		Map<String, String> queryParams = new HashMap<String, String>();
		IOrderService iOrderService = new OrderService();
		GridDataModel<BikeData> model = new GridDataModel<BikeData>();
		// pageStr页码
		String pageStr = request.getParameter("page");
		if (null == pageStr)
			pageStr = "1";
		// rowsStr页显示多少条纪录
		String rowsStr = request.getParameter("rows");
		if (null == rowsStr)
			rowsStr = "10";
		// sortStr排序的字段
		String sortStr = request.getParameter("sort");
		if (null == sortStr)
			sortStr = "id";
		// orderStr排序的规则
		String orderStr = request.getParameter("order");
		if (null == orderStr)
			orderStr = "desc";
		// 相关的条件限制
		String keyword=request.getParameter("keyword");
		String type=request.getParameter("search_type");
		if(NormalUtil.isStringLegal(type)){
			queryParams.put(type, keyword);
		}

		int total = iOrderService.getBikeDataCount(queryParams);
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		int end = rows;
		end = end > total ? total : end;
		
		model.setTotal(total);
		model.setRows(iOrderService.getBikeData(start, end, sortStr, orderStr,
				queryParams));
		System.out.println(FastJsonTool.createJsonString(model));
		out.print(FastJsonTool.createJsonString(model));
		out.close();
		
	}

}

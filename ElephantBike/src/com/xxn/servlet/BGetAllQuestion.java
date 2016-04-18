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
import com.xxn.entity.Question;
import com.xxn.iservice.IQuestionService;
import com.xxn.service.QuestionService;

/**
 * Servlet implementation class BGetCertiUser
 */
@WebServlet("/getallquestion")
public class BGetAllQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BGetAllQuestion() {
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
		IQuestionService iQuestionService = new QuestionService();
		GridDataModel<Question> model = new GridDataModel<Question>();
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
		
		String issolved = request.getParameter("issolved");
		System.out.println("issolved:"+issolved);
		queryParams.put("issolved", issolved);
		
		String keyword=request.getParameter("keyword");
		String type=request.getParameter("search_type");
		if(NormalUtil.isStringLegal(type)){
			queryParams.put(type, keyword);
		}
		int total = iQuestionService.getQuestionCount(queryParams);
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		int end = rows;
		end = end > total ? total : end;
		
		model.setTotal(total);
		model.setRows(iQuestionService.findForPage(start, end, sortStr, orderStr,
				queryParams));
		System.out.println(FastJsonTool.createJsonString(model));
		out.print(FastJsonTool.createJsonString(model));
		out.close();
	}

}

package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.DateTool;
import com.xxn.butils.NormalUtil;
import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.service.BikeService;

/**
 * Servlet implementation class BAddBike
 */
@WebServlet("/addbike")
public class BAddBike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BAddBike() {
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
		Map<String, Object> map = new HashMap<>();
		String res = "";
		IBikeService iBikeService = new BikeService();

		String fid = request.getParameter("id");
		String lid = request.getParameter("b_id");
		String name = request.getParameter("name");
		if (NormalUtil.isStringLegal(fid) && NormalUtil.isStringLegal(lid)
				&& NormalUtil.isStringLegal(name)) {
			String bikeid = fid + lid;
			System.out.println(fid + lid);
			System.out.println(bikeid);
			String usedtime = DateTool.dateToString(new Date());
			Bike bike = new Bike(bikeid, 1, name, usedtime);
			int result = iBikeService.addBike(bike);
			if (result > 0) {
				res = "添加自行车成功";
				response.sendRedirect("./dxdc/qrcode.jsp?id="+fid+lid);
				// TODO 加入新增自行车表
			} else {
				res = "添加自行车失败，已经存在该单车编号";
			}
		}
		System.out.println(res);
		out.close();
	}

}

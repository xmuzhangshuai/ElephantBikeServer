package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxn.butils.FastJsonTool;
import com.xxn.butils.NormalUtil;
import com.xxn.entity.College;
import com.xxn.iservice.ICollegeService;
import com.xxn.iservice.IWalletService;
import com.xxn.service.CollegeService;
import com.xxn.service.WalletService;

/**
 * Servlet implementation class Map
 */
@WebServlet("/map")
public class AddDistinct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDistinct() {
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
		System.out.println("map");
		Map<String, String> map = new HashMap<>();
		ICollegeService iCollegeService = new CollegeService();
		String latlng = request.getParameter("data");
		if(NormalUtil.isStringLegal(latlng)){
			//合法经纬度
			College college = new College(latlng, "厦门大学");
			if(iCollegeService.addArea(college) > 0){
				System.out.println("添加区域成功");
			}
		}
		
//		List<Map<String, Object>> map = FastJsonTool.getObjectMap(a);
//		Map<String, Object> aa = new HashMap<>();
//		map.add(aa);
//		System.out.println(map);
	}

}

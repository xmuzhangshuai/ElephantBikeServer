package com.xxn.servlet;

import java.awt.geom.Point2D;
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

import com.alibaba.fastjson.JSONObject;
import com.xxn.butils.FastJsonTool;
import com.xxn.butils.LocationTool;
import com.xxn.butils.NormalUtil;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.College;
import com.xxn.iservice.ICollegeService;
import com.xxn.service.CollegeService;

/**
 * Servlet implementation class BikeLocation
 */
@WebServlet("/api/bike/bikelocation")
public class BikeLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BikeLocation() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/api/bike/bikelocation");
		Map<String, String> map = new HashMap<>();
		ICollegeService iCollegeService = new CollegeService();

		boolean legal = false;
		String bikeid = request.getParameter("bikeid");
		String location = request.getParameter("location");
		if (NormalUtil.isStringLegal(bikeid)
				&& NormalUtil.isStringLegal(location)) {
			// TODO 根据单车编号去获取大学
			String name = "厦门大学";
			College college = new College("", name);
			List<String> result = iCollegeService.getAddrByCollege(college);
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> local = JSONObject
							.parseObject(location);
					double x = Double.parseDouble(local.get("lng") + "");
					double y = Double.parseDouble(local.get("lat") + "");
					Point2D.Double point = new Point2D.Double(x, y);

					List<Point2D.Double> polygon = new ArrayList<>();
					String res = result.get(i);

					List<Map<String, Object>> list = FastJsonTool
							.getObjectMap(res);
					for (int j = 0; j < list.size(); j++) {
						Map<String, Object> locallist = list.get(j);
						polygon.add(new Point2D.Double(Double
								.parseDouble(locallist.get("lng") + ""), Double
								.parseDouble(locallist.get("lat") + "")));
					}
					if (LocationTool.inSchool(point, polygon)) {
						legal = true;
						break;
					}
				}
				if (legal) {
					map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
					map.put(BikeConstants.MESSAGE, "还车位置合法");
				} else {
					map.put(BikeConstants.STATUS, BikeConstants.FAIL);
					map.put(BikeConstants.MESSAGE, "还车位置不合法");
				}
			} else {
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "未找到该车所属学校");
			}
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "参数不符合要求");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

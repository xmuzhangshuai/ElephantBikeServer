package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Activity;
import com.xxn.iservice.IActivityService;
import com.xxn.service.ActivityService;

/**
 * Servlet implementation class GetHotTopic
 */
@WebServlet("/api/act/topic")
public class GetHotTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHotTopic() {
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
		System.out.println("/api/act/topic");
		Map<String, String> map = new HashMap<>();
		IActivityService iActivityService = new ActivityService();
		String type = request.getParameter("type");
		if(NormalUtil.isStringInteger(type)){
			Activity activity = new Activity("", "", Integer.parseInt(type), 0);
			List<Activity> result = iActivityService.getHotActivty(activity);
			if(result.size() > 0){
				map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
				map.put("imageurl", result.get(0).getImageurl());
				map.put("linkurl", result.get(0).getLinkurl());
			}
			else{
				map.put(BikeConstants.STATUS, BikeConstants.FAIL);
				map.put(BikeConstants.MESSAGE, "暂无热点活动");
			}
		}
		else{
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "type参数类型错误");
		}
		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

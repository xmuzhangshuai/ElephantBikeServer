package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.File;
import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

import com.xxn.constants.BikeConstants;
import com.xxn.entity.Activity;
import com.xxn.iservice.IActivityService;
import com.xxn.iservice.IWebToolService;
import com.xxn.service.ActivityService;
import com.xxn.service.WebToolService;

/**
 * Servlet implementation class BAddActivity
 */
@WebServlet("/publishactivity")
public class BAddActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BAddActivity() {
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
		
		IWebToolService iWebToolService = new WebToolService();
		IActivityService iActivityService = new ActivityService();
		
		String imgurl = iWebToolService.getURL();
		String image_url = "/activity/";
		
		System.out.println(imgurl+image_url);
		java.io.File fpath1 = new java.io.File(imgurl+image_url);
		if (!fpath1.exists()) {
			fpath1.mkdirs();
		}
		
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), request, response);
		su.setMaxFileSize(10 * 1024 * 1024);
		su.setAllowedFilesList("gif,png,jpg,jpeg,JPEG");

		try {
			su.upload();
			// 标题
			String type = su.getRequest().getParameter("type");
			// 概要
			String linkurl = su.getRequest().getParameter("url");
			
			for (int i = 0; i < su.getFiles().getCount(); i++) {
				
					System.out.println("新闻小图");
					if (su.getFiles().getFile(i).getFileExt().equals("jpg")
							|| su.getFiles().getFile(i).getFileExt().equals("jpeg")
							|| su.getFiles().getFile(i).getFileExt().equals("png")
							|| su.getFiles().getFile(i).getFileExt().equals("gif")) {
						File image_file = su.getFiles().getFile(i);
						if (image_file.isMissing()) {
							System.out.println("图片丢失");
							out.print("error");
						} else {
							image_url = image_url + new Date(System.currentTimeMillis())
									+ (new Random().nextInt(900) + 100) + "."
									+ image_file.getFileExt();
						image_file.saveAs(imgurl + image_url);
							System.out.println("存储成功");
						}
				}
			}
			
			Activity activity = new Activity(BikeConstants.WEB_IMAGR_URL+image_url, linkurl, Integer.parseInt(type), 0);
			int result = iActivityService.createActivty(activity);
			if(result == 1)out.print("创建成功...");
			else out.print("创建失败...");
		} catch (SmartUploadException e) {
			e.printStackTrace();
			out.print("创建失败...");
		}
	}

}

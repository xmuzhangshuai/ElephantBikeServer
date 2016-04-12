package com.xxn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.File;
import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

import com.xxn.butils.FastJsonTool;
import com.xxn.constants.BikeConstants;
import com.xxn.iservice.IWebToolService;
import com.xxn.service.WebToolService;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/api/file/uploadvoice")
public class UploadVoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadVoice() {
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
		System.out.println("/api/file/uploadvoice");
		Map<String, String> map = new HashMap<>();
		IWebToolService iWebToolService = new WebToolService();

		String imgurl = iWebToolService.getURL()+"/voice";
		// 图片地址正确
		if (!imgurl.equals("")) {
			System.out.println(imgurl);
			java.io.File fpath1 = new java.io.File(imgurl);
			if (!fpath1.exists()) {
				fpath1.mkdirs();
			}
			SmartUpload su = new SmartUpload();
			su.initialize(this.getServletConfig(), request, response);
			su.setMaxFileSize(10 * 1024 * 1024);
			su.setAllowedFilesList("mp3,wma,cd,mpeg-4");
			try {
				su.upload();
				for (int i = 0; i < su.getFiles().getCount(); i++) {
					System.out.println("格式:"
							+ su.getFiles().getFile(i).getFileExt());
					File image_file = su.getFiles().getFile(i);
					if (image_file.isMissing()) {
						System.out.println("录音文件丢失");
						out.print("error");
						map.put(BikeConstants.STATUS, BikeConstants.FAIL);
						map.put(BikeConstants.MESSAGE, "录音文件上传失败");
					} else {
						String imgurl_name = "/"
								+ new Date(System.currentTimeMillis())
								+ (new Random().nextInt(900) + 100) + "."
								+ image_file.getFileExt();
//						imgurl = imgurl + "/voice";
						image_file.saveAs(imgurl + imgurl_name);
						map.put(BikeConstants.STATUS, BikeConstants.SUCCESS);
						map.put("url", BikeConstants.WEB_IMAGR_URL + "voice/"
								+ imgurl_name);
					}
				}

			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		} else {
			map.put(BikeConstants.STATUS, BikeConstants.FAIL);
			map.put(BikeConstants.MESSAGE, "获取文件服务器地址错误");
		}

		System.out.println(FastJsonTool.createJsonString(map));
		out.print(FastJsonTool.createJsonString(map));
		out.close();
	}

}

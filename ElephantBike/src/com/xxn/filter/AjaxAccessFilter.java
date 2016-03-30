package com.xxn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxAccessFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		// 对request和response进行一些预处理
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		filterChain.doFilter(request, response); // 让目标资源执行，放行
		System.out.println("filter之后");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}

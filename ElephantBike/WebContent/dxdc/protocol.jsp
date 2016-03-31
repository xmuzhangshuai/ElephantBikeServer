<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布协议内容</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>


</head>
<body>
	
	<form action="<%=basePath%>publishcontent" method="post">
		<article class="module width_full"> <header>
		<h3 style="font-size: 13pt">协议内容管理</h3>
		</header>
		<div class="module_content">
			<fieldset>
				<label style='color:#F00'>协议内容</label> 
				<textarea rows="5" name="n_content" placeholder="输入文字内容"></textarea>
				<input type="text" name="type" value="protocol" style="display: none;">
			</fieldset>
			<div class="clear"></div>
		</div>
		<footer>
		<div class="submit_link" style="float: left;margin-left: 3%">
			<input type="submit" value="保存并更新" class="alt_btn"> <input
				type="reset" value="重置">
		</div>
		</footer> 
		</article>
	</form>
	
	    
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大象单车</title>
</head>
<body>
	大象欢迎页测试成功
	<form action="<%=basePath%>api/file/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="imgurl">
		<input type="submit" value="上传">
	</form>
</body>
</html>
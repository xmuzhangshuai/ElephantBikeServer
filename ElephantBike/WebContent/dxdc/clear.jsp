<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>清空数据表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

<script type="text/javascript">
	function clear(){
		$.post("<%=basePath%>cleardata",
				function (data, textStatus){
				if(data > 0){
					alert('已经清空');
				} 
				else{
					alert('清空失败，请重试');
				}
		}, "text");
	}
</script>

</head>

<body onload="clear()">

</body>
</html>
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
<title>后台管理系统</title>
</head>
    <frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=basePath%>dxdc/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    	<frame src="<%=basePath%>dxdc/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    	<frame src="<%=basePath%>dxdc/myInfo.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
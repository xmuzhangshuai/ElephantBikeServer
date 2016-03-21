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
<title>发布消息</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/kindeditor/kindeditor-min.js" charset="UTF-8"></script>


</head>
<body>
	
	<form action="<%=basePath%>settime" method="post">
		<article class="module width_full"> <header>
		<h3 style="font-size: 13pt">时间设置</h3>
		</header>
		<div class="module_content">
			
			<fieldset style="width: 48%; float: left; ">
				<label style='color:#F00'>设置时间</label><input  id="d121" name = "time" type="text" placeholder="请选择时间" onfocus="WdatePicker({isShowWeek:true})"/>
			</fieldset>

			<div class="clear"></div>
		</div>
		<footer>
		<div class="submit_link" style="float: left; ">
			<input type="submit" value="确认" class="alt_btn"> <input
				type="reset" value="重置">
		</div>
		</footer> </article>
	</form>
	
	    
</body>
</html>
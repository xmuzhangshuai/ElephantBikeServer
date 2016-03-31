<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTML5上传图片预览</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
</head>
<body>
	<h3 style="margin-top: 3%; margin-left: 5%">本地图片 （800*600）</h3>
	<form action="<%=basePath%>publishactivity" method="post" enctype="multipart/form-data">
		<div style="width: 20%; float: left; margin-top: 1%; margin-left: 5%">
			<fieldset>
				<input type="file" name="file" id="file" multiple="multiple" /><br>
				<br> <input type="text" name="url" placeholder="输入网址" /><br>
				<input type="text" name="type" value="1" style="display: none;">
			</fieldset>
		</div>
		<div style="float: right; margin-right: 50%">
			<fieldset>
				<img src="" id="img0">
			</fieldset>
			<input type="submit" value="保存并更新" class="alt_btn">
		</div>
	</form>
	<script>
		$("#file").change(function() {
			var objUrl = getObjectURL(this.files[0]);
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img0").attr("src", objUrl);
			}
		});
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null;
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
	</script>
</body>
</html>
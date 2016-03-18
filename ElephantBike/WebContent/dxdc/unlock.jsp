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
<title>生成二维码</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

<script type="text/javascript">
	function generatecode(){
		var content = $('#content').val();
		if(content.length==0)alert("单车编号为空");
		else{
			$.ajax({
	        	url: '<%=basePath%>unlock',
				data : {
					bikeid : content,
				},
				dataType : 'json',
				async : true,
				success : function(r) {
					alert(r);
					$("#pass").val(r); 
				}
			});
		}
		
	}
</script>

</head>

<body>
	<article class="module width_full"> <header>
	<h3 style="font-size: 13pt">获取解锁密码</h3>
	</header>
	<div>
		<fieldset
			style="width: 35%; height: 100px; float: left; margin-right: 3%;">
			<label>单车6位编号</label> <input type="text" id="content" name="content"
				placeholder="请输入单车编号">
		</fieldset>
		<fieldset
			style="width: 35%; height: 100px; float: left; margin-right: 3%;">
			<label>解锁密码</label> <input type="text" id="pass" name="pass">
		</fieldset>
		<div class="clear"></div>
	</div>
	
	<footer>
	<div class="submit_link" style="float: left; margin-right: 3%;">
		<button class="alt_btn" onclick="generatecode()">生成解锁密码</button>
	</div>
	</footer> </article>

</body>
</html>
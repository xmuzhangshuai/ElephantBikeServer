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
<title></title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>


<script type="text/javascript">
	function getcollege() {
		
		var id = document.getElementById('college');
		$.ajax({
        	url: '<%=basePath%>allcollege',
			dataType : 'json',
			async : true,
			success : function(r) {
				var colleges = r.college ;
				for (o in colleges) {
					id.options.add(new Option(colleges[o].name, colleges[o].collegeid));
				}
			}
		});
	}
	function next(){
		var name = $('#college').find("option:selected").text();
		var id = $('#college').find("option:selected").val();
		window.location.href="./addbike.jsp?name="+name+"&&id="+id;
	}
	
</script>

</head>
<body onload="getcollege()">
	<header>
	<h3 style="font-size: 13pt">添加单车-->选择学校</h3>
	</header>
	<div class="module_content">
		<fieldset style="width: 48%; float: left; margin-right: 3%;">
			<label>选择学校</label> <select name="college" id="college"
				style="width: 92%;">
			</select>
		</fieldset>
		<div class="clear"></div>
	</div>


	<footer>
		<button class="alt_btn" style="float: left; width: 100px;margin-left:5%" 
			id="next" onclick="next()">下一步</button>
	</footer>



</body>
</html>
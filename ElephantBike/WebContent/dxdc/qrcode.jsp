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
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}
	
	function generatecode(){
		var Request = new Object();
		Request = GetRequest();
		var content = Request['id'];
		$.ajax({
        	url: '<%=basePath%>generateqrcode',
			data : {
				data : content,
			},
			dataType : 'json',
			async : true,
			success : function(r) {
				$("#img").attr('src',r.url); 
			}
		});
	}
</script>

</head>

<body onload="generatecode()">
	<article class="module width_full" style="width:15%;"> <header>
	<h3 style="font-size: 13pt">单车二维码</h3>
	</header>
	<div>
		<fieldset style="width: 10%;margin:0 auto">
			<img id="img" alt=""
				src="http://api.wwei.cn//Uploads//apiqrcode//2016//03//16//c97326ba5aa6798affdc3c2e30221b4c0.png">
		</fieldset>
		<div class="clear"></div>
	</div>
	 </article>

</body>
</html>
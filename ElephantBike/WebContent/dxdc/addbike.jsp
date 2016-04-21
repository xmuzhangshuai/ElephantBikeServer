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
<title></title>
<link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>


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
	
	function getcollegeinfo(){
		var Request = new Object();
		Request = GetRequest();
		var id = Request['id'];
		var name = decodeURI(Request['name']);
		$("#id").val(id);
		$("#name").val(name);
	}
	
</script>
 
<script type="text/javascript">

$(document).ready(function(){
	getcollegeinfo();
});

function keyPress() {  
    var keyCode = event.keyCode;  
    if ((keyCode >= 48 && keyCode <= 57))  
   {  
        event.returnValue = true;  
    } else {  
          event.returnValue = false;  
   }  
}  

</script>

</head>
<body>
	<form action="<%=basePath%>addbike" method="post">
		<article class="module width_full"> <header>
		<h3 style="font-size: 13pt">添加单车</h3>
		</header>
		<div class="module_content">
			<fieldset>
				<label>单车前两位编号</label> 
				<input type="text" name="id" id="id" readonly="readonly">
				<label>单车后四位编号</label> 
				<input type="text" name="b_id" id="b_id" maxlength="4"onkeypress="keyPress()">
				<input type="text" name="name" id="name" style="display:none">
			</fieldset>
			<div class="clear"></div>
		</div>
		<footer>
		<div class="submit_link" style="float:left;margin-left:3%">
			<input type="submit" value="添加" class="alt_btn"> <input
				type="reset" value="重置">
		</div>
		</footer> </article>
	</form>
    
</body>
</html>
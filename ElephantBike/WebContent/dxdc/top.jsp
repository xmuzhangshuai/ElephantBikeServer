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
<title>后台上侧页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	});
	//$(".logout").click(function(){
//	});
	
})	
</script>
</head>
<body>
    <div class="topbg">
    <div class="topleft">
    <a href="main.jsp" target="_parent">
    <h2>瑞士麟瑜后台管理</h2>
    </a>
    </div>
        
    <ul class="nav">
    <li><a href="myInfo.jsp" target="rightFrame"><img src="../backimg/i07.png" title="我的信息" />我的信息</a></li>
    <li><a href="main.jsp"  target="#"><img src="../backimg/i02.png" title="宣传管理" />宣传管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../backimg/icon01.png" title="用户管理" />用户管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../backimg/i09.png" title="通知管理" />通知管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../backimg/i08.png" title="栏目管理" />栏目管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../backimg/i06.png" title="日志管理" />日志管理</a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="../backimg/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a class = "logout" href="<%=basePath%>/admin.html" target="_parent">退出</a></li>
    </ul>
    <div class="user">
    <span></span>
    <i>消息</i>
    <b>XX</b>
    </div>    
    
    </div>
    </div>
</body>
</html>

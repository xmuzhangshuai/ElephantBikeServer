<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台主页的左侧栏</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	
</script>


</head>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span></div>
    
    <!-- 后台主页左侧菜单  -->
    <dl class="leftmenu">
        
    <dd>
    <div class="title"><span></span><a href="shouye.jsp" target="rightFrame">首页</a>
    </div>
    </dd>
        
    <dd><div class="title"><span></span>统计</div>
    <ul class="menuson">
        <li><cite></cite><a href="alldata.jsp"  target="rightFrame">全部数据</a><i></i></li>
        <li><cite></cite><a href="myInfo.jsp"  target="rightFrame">用户分析</a><i></i></li>
        <li><cite></cite><a href="myInfo.jsp"  target="rightFrame">车辆分析</a><i></i></li>
        <li><cite></cite><a href="myInfo.jsp"  target="rightFrame">丢失车辆</a><i></i></li>
    </ul>    
    </dd>  

    <dd><div class="title"><span></span>用户管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">用户列表</a>
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">身份认证审核</a>
      </ul>
    </dd>   
    
    <dd><div class="title"><span></span>车辆管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">单车列表</a>
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">添加单车</a>
      </ul>
    </dd>  
    <dd><div class="title"><span></span>内容管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">帮助内容管理</a>
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">协议内容管理</a>
        <li><cite></cite><a href="myInfo.jsp" target="rightFrame">通知内容管理</a>
      </ul>
    </dd>  
    
     <dd><div class="title"><span></span><a href="myInfo.jsp" target="rightFrame">系统设置</a></div>
    </dd>  
    </dl>
    
</body>
</html>

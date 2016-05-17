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
        
    <dd><div class="title"><span></span>数据分析</div>
    <ul class="menuson">
        <li><cite></cite><a href="totaldata.jsp"  target="rightFrame">整体数据</a><i></i></li>
        <li><cite></cite><a href="orderdata.jsp"  target="rightFrame">订单数据</a><i></i></li>
        <li><cite></cite><a href="userdata.jsp"  target="rightFrame">用户数据</a><i></i></li>
        <li><cite></cite><a href="bikedata.jsp"  target="rightFrame">单车数据</a><i></i></li>
        
    </ul>    
    </dd>  

    <dd><div class="title"><span></span>用户管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="centiusermanage.jsp" target="rightFrame">已认证列表</a>
        <li><cite></cite><a href="uncentiusermanage.jsp" target="rightFrame">待认证列表</a>
        <li><cite></cite><a href="frozenuser.jsp" target="rightFrame">冻结列表</a>
      </ul>
    </dd>   
    <dd><div class="title"><span></span>车辆管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="bikemanage.jsp" target="rightFrame">单车列表</a>
        <li><cite></cite><a href="choosecollege.jsp" target="rightFrame">添加单车</a>
        <li><cite></cite><a href="map.jsp" target="rightFrame">添加学校</a>
      <!--   <li><cite></cite><a href="../qrcode.jsp" target="rightFrame">生成二维码</a>
        <li><cite></cite><a href="./unlock.jsp" target="rightFrame">解锁密码</a>
        <li><cite></cite><a href="./settime.jsp" target="rightFrame">时间设置</a> -->
      </ul>
    </dd>  
    <dd><div class="title"><span></span>问题管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="question.jsp" target="rightFrame">未解决问题列表</a>
        <li><cite></cite><a href="dealedquestion.jsp" target="rightFrame">已解决问题列表</a>
      </ul>
    </dd>
    <dd><div class="title"><span></span>内容管理</div>
      <ul class="menuson">
        <li><cite></cite><a href="helps.jsp" target="rightFrame">帮助内容管理</a>
        <li><cite></cite><a href="protocol.jsp" target="rightFrame">协议内容管理</a>
        <li><cite></cite><a href="notice.jsp" target="rightFrame">弹窗公告栏</a>
        <li><cite></cite><a href="charging.jsp" target="rightFrame">计费页面公告</a>
        <li><cite></cite><a href="personal.jsp" target="rightFrame">个人中心公告</a>
      </ul>
    </dd>  
    
     <dd><div class="title"><span></span>系统设置</div>
      <ul class="menuson">
        <li><cite></cite><a href="clear.jsp" target="rightFrame">一键清空数据表</a>
      </ul>
    </dd>  
    </dl>
    
</body>
</html>

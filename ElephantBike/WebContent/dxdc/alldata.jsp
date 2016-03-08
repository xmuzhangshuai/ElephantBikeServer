<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>个人信息</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/alldata.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>

</head>

<body>
	<div class="place">
		<span>&nbsp;&nbsp;&nbsp;</span>
		<ul class="placeul">
			<li><span>统计>全部数据</span></li>
		</ul>
	</div>
	<div class="xline"></div>
	<div style="width: 100%; min-width:1300px;" >
		<div style="margin: 1% 0 0 5%">
			<label>请选择学校:&nbsp;&nbsp;</label>
			<select>
				<option value="厦门大学">厦门大学</option>
				<option value="厦门大学">厦门大学</option>
				<option value="厦门大学">厦门大学</option>
			</select>
		</div>
		<div style="margin: 1% 0 0 5%">
			<label>统计时间段:&nbsp;&nbsp;</label>
			<input type="text" style="width:100px" class="testTxt green"  onfocus="WdatePicker({isShowWeek:true})">至
			<input type="text" style="width:100px" class="testTxt green"  onfocus="WdatePicker({isShowWeek:true})">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button green">&nbsp;确定&nbsp;</button>
			<button type="button" class="button green">&nbsp;&nbsp;导出数据&nbsp;&nbsp;</button>
		</div>
		<div class="clear"></div>
		<div id="news">
        <ul>                
            <li class="bt">订单编号</li>
            <li class="bt">用户ID</li>
            <li class="bt">车辆ID</li>
            <li class="bt">开始时间</li>
            <li class="bt">使用时长</li>
            <li class="bt">费用</li>
            <li class="bt">支付方式</li>
            <li class="bt">结束地点</li>
            
            <li><input type="text" class="testTxt green"></li>
            <li><input type="text" class="testTxt green"></li>
            <li><input type="text" class="testTxt green"></li>
            <li><input type="text" class="testTxt green" disabled="true"></li>
            <li><input type="text" class="testTxt green" disabled="true"></li>
            <li><input type="text" class="testTxt green" disabled="true"></li>
            <li><input type="text" class="testTxt green"></li>
            <li><input type="text" class="testTxt green" disabled="true"></li>
            
            
            <li>000010</li>
            <li>000214</li>
            <li>010054</li>
            <li>2015-12-18 20:15:47</li>
            <li>00:00:02:32</li>
            <li>0.24</li>
            <li>支付宝</li>
            <li>查看</li>
           
           <li>000010</li>
            <li>000214</li>
            <li>010054</li>
            <li>2015-12-18 20:15:47</li>
            <li>00:00:02:32</li>
            <li>0.24</li>
            <li>支付宝</li>
            <li>查看</li>
           
           
           <li>000010</li>
            <li>000214</li>
            <li>010054</li>
            <li>2015-12-18 20:15:47</li>
            <li>00:00:02:32</li>
            <li>0.24</li>
            <li>支付宝</li>
            <li>查看</li>
           
           
           
           
           
           
           
        </ul>
    </div>
		
	</div>

</body>

</html>

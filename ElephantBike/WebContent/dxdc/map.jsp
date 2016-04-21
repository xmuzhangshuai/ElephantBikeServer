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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	width: 100%;
	height: 80%;
	overflow: hidden;
}

#result {
	width: 100%;
	font-size: 12px;
}

dl,dt,dd,ul,li {
	margin: 0;
	padding: 0;
	list-style: none;
}

p {
	font-size: 12px;
}

dt {
	font-size: 14px;
	font-family: "微软雅黑";
	font-weight: bold;
	border-bottom: 1px dotted #000;
	padding: 5px 0 5px 5px;
	margin: 5px 0;
}

dd {
	padding: 5px 0 0 5px;
}

li {
	line-height: 28px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=bq0k85ilSnwObHdGjOPGQ6A6"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<!--加载检索信息窗口-->
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
	
<title>鼠标绘制工具</title>
</head>
<body onload="drawCollege()">
	<div id="allmap" style="overflow: hidden; zoom: 1; position: relative;">
		<div id="map"
			style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
		<div>
			<input type="hidden" id="points" name="points">
		</div>
	</div>
	<div class="module_content" style="float: left;margin-left: 3%">
		<label>定位区域</label> 
		<input type="text" name="locationname" id="locationname">
		<input type="button" value="定位" class="alt_btn" onclick="locationAddr()" /> 
		<br/><br/>
		<label>学校名称</label> 
		<input type="text" name="name" id="name">
		<label>学校编号</label> 
		<input type="text" name="collegeid" id="collegeid" maxlength="2">
		<input type="button" value="确认" class="alt_btn" onclick="addArea()" /> 
		<input type="button" value="清除所有覆盖物" class="alt_btn" onclick="clearAll()" />
	</div>

	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map('map');
		var poi = new BMap.Point(118.112225,24.447508);
		map.centerAndZoom(poi, 16);
		map.enableScrollWheelZoom();
		var overlays = [];
		var drawPoints;
		var overlaycomplete = function(e) {
			overlays.push(e.overlay);
			drawPoints = e.overlay.getPath();
			pointsJSON = [];
			//将多边形顶点存入pointsJson中
			for (var i = 0; i < drawPoints.length; i++) {
				var pointJson = {
					'lng' : drawPoints[i].lng,
					'lat' : drawPoints[i].lat
				};
				pointsJSON.push(pointJson);
			}
			//将json转成string，存入
			document.getElementById("points").value = JSON
					.stringify(pointsJSON);
		};
		var styleOptions = {
			strokeColor : "red", //边线颜色。
			fillColor : "red", //填充颜色。当参数为空时，圆形将没有填充效果。
			strokeWeight : 3, //边线的宽度，以像素为单位。
			strokeOpacity : 0.8, //边线透明度，取值范围0 - 1。
			fillOpacity : 0.6, //填充的透明度，取值范围0 - 1。
			strokeStyle : 'solid' //边线的样式，solid或dashed。
		};
		
		//实例化鼠标绘制工具
		var drawingManager = new BMapLib.DrawingManager(map, {
			isOpen : false, //是否开启绘制模式
			enableDrawingTool : true, //是否显示工具栏
			drawingToolOptions : {
				anchor : BMAP_ANCHOR_TOP_RIGHT, //位置
				offset : new BMap.Size(5, 5), //偏离值
			},
			circleOptions : styleOptions, //圆的样式
			polylineOptions : styleOptions, //线的样式
			polygonOptions : styleOptions, //多边形的样式
			rectangleOptions : styleOptions
		//矩形的样式
		});
		//添加鼠标绘制工具监听事件，用于获取绘制结果
		drawingManager.addEventListener('overlaycomplete', overlaycomplete);
		function clearAll() {
			for (var i = 0; i < overlays.length; i++) {
				map.removeOverlay(overlays[i]);
			}
			overlays.length = 0;
		}
		//将选中的区域划入
		function addArea() {
			var id = document.getElementById("collegeid").value;
			var name = document.getElementById("name").value;
			var addr = document.getElementById("points").value;
			if(name==""){
				alert("学校名称为空");
				return ;
			}
			if(id==""){
				alert("学校编号为空");
				return ;
			}
			if(addr==""){
				alert("没有绘制学校区域");
				return ;
			}
			$.post("<%=basePath%>map", {data: addr,name:name,collegeid:id},
					function (data, textStatus){
					if(data == '1'){
						alert('添加学校成功');
						window.location.reload();
					} 
					else{
						alert('添加学校失败');
					}
			}, "text");
		}
		
		function drawCollege(){
			$.post("<%=basePath%>allcollegeaddr",function (data, textStatus){
				ajaxobj=eval("("+data+")");  
				if(ajaxobj.status == 'success'){
					alladdr = eval(ajaxobj.college);
					for(var i=0;i<alladdr.length;i++)
					{
						addr = eval(alladdr[i]);
						var arrayObj = [];
						for(var j=0;j<addr.length;j++)
						{
							var ppp = new BMap.Point(addr[j].lng,addr[j].lat);
							arrayObj[j] = ppp;
						}
						var polygon = new BMap.Polygon(arrayObj, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
						map.addOverlay(polygon);           
						//增加多边形
					}
				} 
				else{
					alert("绘制高校区域失败，请刷新重试");
				}
			}, "text");	
		}
		
		function locationAddr(){
			var name = document.getElementById("locationname").value;
			map.centerAndZoom(name,15);      // 初始化地图,用城市名设置地图中心点
		};
		
		$(document).ready(function(){
			$('#locationname').bind('keyup', function(event) {
		        if (event.keyCode == "13") {
		            //回车执行查询
		            //$('#search_button').click();
		            locationAddr();
		        }
		    });
			
		});
	</script>
</body>
</html>

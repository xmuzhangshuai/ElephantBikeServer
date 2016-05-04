<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>查看单车数据</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>

<style type="text/css">
	#searchDialog{
		background-color: white;
		border: 1px solid black;
		margin: 0 auto;
		display: none;
	}
</style>


<script>
	var flag =1;//加载变量
    
    $(function(){
		//获取数据的查询参数----过滤数据
		var queryParams;
		queryParams = {"userstate":"1"};
		getData(queryParams);
	});
    
	function getData(queryParams){
		$('#grid').datagrid({
			url: '<%=basePath%>getallorder',
			sortName: 'id',
			sortOrder: 'desc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			pageSize: 15,//每页显示的记录条数，默认为15  
	        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
			frozenColumns:[[
			    {field: 'ck', checkbox: true},          
			]],
			columns: [[
				{field:'orderid',title:'订单编号',sortable:true,width:'15%',},
				{field:'phone',title:'手机号',sortable:true,width:'10%',},
				{field:'bikeid',title:'车辆编号',sortable:true,width:'5%',},
				{field:'starttime',title:'开始时间',sortable:true,width:'15%',},
				{field:'finishtime',title:'结束时间',sortable:true,width:'15%',},
				{field:'usedtime',title:'使用时长',sortable:true,width:'10%',},
				{field:'cost',title:'费用',sortable:true,width:'5%',},
				{field:'paymode',title:'支付方式',sortable:true,width:'10%',},
				{field:'finishlocation',title:'结束地点',sortable:true,width:'10%',},
			]],
			
			pagination: true,
			rownumbers:true,
		});
		
		
		//页面属性设置
		var p = $('#grid').datagrid('getPager');
		$(p).pagination({
			
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	        BeforeRefresh:function(){
				$(this).datagrid('reload'); 
				//获取数据库全部数据
			},
			
		});
		
		//查询
		$("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true })
	    .click(function () {
	        $('#grid').datagrid({ queryParams: form2Json("searchform") });   //点击搜索
	    });
		
		//批量增加金额
		$("#submit_add").linkbutton({ iconCls: 'icon-add', plain: true })
	    .click(function () {
	        //执行增加金额
	    	var balance = $('#balance').val();
	        $.post("<%=basePath%>batchbalance", {val: balance},
					function (data, textStatus){
					if(data == '1'){
						$.messager.alert('提示','批量增加成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示',data,'fail');
						}
					}, "text");
	        $('#balance').val("");
	    });
	};
	
	//------------------------------------反选数据-----------------------------------
	function _invertSelect(){
		
		var rows = $('#grid').datagrid('getSelections');
		$('#grid').datagrid('selectAll');
		for(var i=0;i<rows.length;i++){
			var rowIndex = $('#grid').datagrid('getRowIndex', rows[i]);
			$('#grid').datagrid('unselectRow',rowIndex);
		}
	};
	
	//------------------------------------编辑行数据-----------------------------------
	function _editRow(){
		
		var row = $('#grid').datagrid('getSelected');
		if(row){
			var id = row.id;
		}
		else{
			$.messager.alert('警告','您没有选择','error');
		}
	};
	//----------------------------------批量增加金额-------------------------------------
	function frozen(phone){
		 $.post("<%=basePath%>/api/user/frozen", {phone: phone,state:-1},
					function (data, textStatus){
			 		 ajaxobj=eval("("+data+")");  
					if(ajaxobj.status == 'success'){
						$.messager.alert('提示','冻结成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示','冻结失败','fail');
						}
					}, "text");
	}
	//----------------------------------批量增加金额-------------------------------------
	function _batch_add(){
		
	}
	  //将表单数据转为json
    function form2Json(id) {
        var arr = $("#" + id).serializeArray();
        var jsonStr = "";
        jsonStr += '{';
        for (var i = 0; i < arr.length; i++) {
            jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
        }
        jsonStr += '"' + "userstate" + '":"' + "1" + '",';
        jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
        jsonStr += '}';
       // alert(jsonStr);
        var json = JSON.parse(jsonStr);
       
        return json;
    }

</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
<div style="margin: auto;">
	<form name="searchform" method="post" action="" id ="searchform">
	<strong>用户检索:</strong>
     	<select name="search_type" id="search_type" >
            <option value="-1">请选择搜索类型</option>
            <option value="phone" >手机号</option>
            <option value="college">学校</option>
        </select>
        <input type="text" name="keyword" size=20 >
        <a id="submit_search">搜索</a>
</form>
	<table id="grid" style="margin-left: 2%;margin-top: 2%"></table>
</div>
</body>
</html>








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

<title>查看认证用户列表</title>
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
		queryParams = {"userstate":"0"};
		getData(queryParams);
	});
    
	function getData(queryParams){
		$('#grid').datagrid({
			url: '<%=basePath%>certiuser',
			sortName: 'id',
			sortOrder: 'asc',
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
				{field:'id',title:'用户ID',sortable:true,width:'5%',},
				{field:'registerdate',title:'注册时间',sortable:true,width:'10%',},
				{field:'name',title:'姓名',sortable:true,width:'6%',},
				{field:'phone',title:'手机号',sortable:true,width:'6%',},
				{field:'college',title:'学校',sortable:true,width:'6%',},
				{field:'stunum',title:'学号',sortable:true,width:'10%',},
				{field:'stucardaddr',title:'学生卡',sortable:true,width:'20%',
					formatter:function(value, row){//使用formatter格式化刷子
						var url = '<%=basePath%>';
						url = url.replace('ElephantBike/','');
						return '<img src='+url+value+' style=width:100%;height:200px >';
					},
				},
				{field:'userstate',title:'用户状态',sortable:true,width:'8%',
					formatter:function(value, rec){//使用formatter格式化刷子
						if(value == 0)return "未认证";
						if(value == -1)return "冻结";
						if(value == 2)return "提交未审核";
					}
				},
				{field:'ma',title:'操作',sortable:true,width:'10%',
					formatter:function(value, row){//使用formatter格式化刷子
						return "<a href='javascript:pass("+row.phone+","+row.stunum+")'>通过</a>"+"&nbsp;&nbsp;&nbsp;"+
						"<a href='javascript:unpass("+row.phone+")'>不通过</a>";
					}
				},
				
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
	//----------------------------------通过-------------------------------------
	function pass(phone,stunum){
		 $.post("<%=basePath%>/getuserbystunum", {stunum:stunum},
					function (data, textStatus){
					if(data == '1'){
						$.messager.confirm("操作警告", "已经存在该学号用户，是否继续通过", function(data2){
							if(data2){
								 $.post("<%=basePath%>/api/user/frozen", {phone: phone,stunum:stunum,state:1},
											function (data, textStatus){
											if(data == '1'){
												$.messager.alert('提示','认证成功','info');
												$('#grid').datagrid('reload'); 
												} 
												else{
												$.messager.alert('提示','认证失败','fail');
												}
											}, "text");
								//原来代码结束位置
							}
						});
						$('#grid').datagrid('reload'); 
					} 
					else{
						 $.post("<%=basePath%>/api/user/frozen", {phone: phone,stunum:stunum,state:1},
									function (data, textStatus){
									if(data == '1'){
										$.messager.alert('提示','认证成功','info');
										$('#grid').datagrid('reload'); 
										} 
										else{
										$.messager.alert('提示','认证失败','fail');
										}
									}, "text");
					}
		 }, "text");
		
	}
	//-------------------------------------不通过----------------------------------
	function unpass(phone){
		 $.post("<%=basePath%>/api/user/frozen", {phone: phone,state:0},
					function (data, textStatus){
					if(data == '1'){
						$.messager.alert('提示','操作成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示','操作失败','fail');
						}
					}, "text");
	}
	  //将表单数据转为json
    function form2Json(id) {
        var arr = $("#" + id).serializeArray();
        var jsonStr = "";
        jsonStr += '{';
        for (var i = 0; i < arr.length; i++) {
            jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
        }
        jsonStr += '"' + "userstate" + '":"' + "0" + '",';
        jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
        jsonStr += '}';
       // alert(jsonStr);
        var json = JSON.parse(jsonStr);
       
        return json;
    }

</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
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


<table id="grid"></table>
</body>
</html>








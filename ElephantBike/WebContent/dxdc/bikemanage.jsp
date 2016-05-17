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

<title>单车列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>


<script>
	var flag =1;//加载变量
    
    $(function(){
		//获取数据的查询参数----过滤数据
		getData();
	});
    
	function getData(){
		$('#grid').datagrid({
			url: '<%=basePath%>allbike',
			sortName: 'id',
			sortOrder: 'asc',
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			pageSize: 15,//每页显示的记录条数，默认为15  
	        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
			frozenColumns:[[
			    {field: 'ck', checkbox: true},          
			]],
			columns: [[
				{field:'id',title:'ID',sortable:true,width:'5%',},
				{field:'bikeid',title:'单车编号',sortable:true,width:'5%',},
				{field:'college',title:'学校',sortable:true,width:'6%',
					//如果要实现编辑功能，需要添加下面的属性
					//editor: { type: 'validatebox',  }
				},
				{field:'usedtime',title:'投入时间',sortable:true,width:'10%',},
				{field:'lastusedtime',title:'最近使用时间',sortable:true,width:'6%',
				},
				{field:'state',title:'状态',sortable:true,width:'5%',
					formatter:function(value, row){//使用formatter格式化刷子
						if(value==1)return '可用';
						else return '不可用';
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
	

</script>

</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid"></table>
</body>
</html>








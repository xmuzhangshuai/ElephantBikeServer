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

<title>问题列表</title>
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
		queryParams = {"issolved":"0"};
		getData(queryParams);
	});
    
	function getData(queryParams){
		$('#grid').datagrid({
			url: '<%=basePath%>getallquestion',
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
				{field:'phone',title:'手机号',sortable:true,width:'10%',
					//如果要实现编辑功能，需要添加下面的属性
					//editor: { type: 'validatebox',  }
				},
				{field:'bikeid',title:'单车编号',sortable:true,width:'6%',
				},
				{field:'type',title:'问题类型',sortable:true,width:'15%',},
				{field:'voiceproof',title:'录音',sortable:true,width:'25%',
					formatter:function(value, row){//使用formatter格式化刷子
						var url = '<%=basePath%>';
						url = url.replace('ElephantBike/','');
						return "<audio controls='controls'><source src='"+
						url+row.voiceproof+"' type='audio/mpeg'></audio>";
					}
				},
				{field:'createtime',title:'提交时间',sortable:true,width:'15%',
				},
				{field:'ma',title:'操作',sortable:true,width:'5%',
					formatter:function(value, row){//使用formatter格式化刷子
						if(row.needfrozen==1)
							return "<a href='javascript:frozen("+row.phone+","+row.id+")'>解冻</a>";
						else return "<a href='javascript:deal("+row.id+")'>处理</a>";
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
	
	
	
	//---------------------------------解冻用户-------------------------------------
	function frozen(phone,id){
		 $.post("<%=basePath%>/api/user/frozen", {phone: phone,state:1},
					function (data, textStatus){
					if(data == '1'){
						$.messager.alert('提示','解冻成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示','解冻失败','fail');
						}
					}, "text");
		 $.post("<%=basePath%>/api/question/dealquestion", {id: id},
					function (data, textStatus){
					if(data == '1'){
						$.messager.alert('提示','处理问题成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示','处理问题失败','fail');
						}
					}, "text");
	}
	//------------------------------------问题解决-------------------------------------
	function deal(id){
		 $.post("<%=basePath%>/api/question/dealquestion", {id: id},
					function (data, textStatus){
					if(data == '1'){
						$.messager.alert('提示','处理问题成功','info');
						$('#grid').datagrid('reload'); 
						} 
						else{
						$.messager.alert('提示','处理问题失败','fail');
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


<table id="grid"></table>
</body>
</html>








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
<title>发布消息</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/kindeditor/kindeditor-min.js" charset="UTF-8"></script>

<script type="text/javascript">
		KE.show({
			 id : "editor",
		     width : "100%",
		     height : "200px",		    
		     resizeMode : 1,
		     allowFileManager : true,
		     /*图片上传的SERVLET路径*/
		     imageUploadJson : "${pageContext.request.contextPath}/uploadImage.html", 
		     /*图片管理的SERVLET路径*/     
		     fileManagerJson : "${pageContext.request.contextPath}/uploadImgManager.html",
		     /*允许上传的附件类型*/
		     accessoryTypes : "doc|xls|pdf|txt|ppt|rar|zip",
		     /*附件上传的SERVLET路径*/
		     accessoryUploadJson : "${pageContext.request.contextPath}/uploadaccessoryover"
		});
	</script>
 
<script type="text/javascript">


function addFile(value){
	if(value=='是'){
		var file = $("'<label style='color:#F00'>热点新闻长图,建议1400*450</label>'<input type='file' name='UplodeName'/><br>");
		$("#fileUplodeDiv").html(file);	
	}
	else if(value=='否'){
		$("#fileUplodeDiv").html("");
	}

}

</script>

</head>
<body>
	
	<form action="<%=basePath%>publishnews" method="post" enctype="multipart/form-data">
		<article class="module width_full"> <header>
		<h3 style="font-size: 13pt">新闻发布</h3>
		</header>
		<div class="module_content">
			<fieldset style="width: 48%; float: left; margin-right: 3%;">
				<!-- to make two field float next to one another, adjust values accordingly -->
				<label>中英文</label> <select name="version" style="width: 92%;">
					<option>中文</option>
					<option>English</option>
				</select>
			</fieldset>
			<fieldset style="width: 48%; float: left; ">
				<!-- to make two field float next to one another, adjust values accordingly -->
				<label>是否热点新闻</label> <select name="n_ishot" id="n_ishot" onchange="addFile(this.options[this.options.selectedIndex].value)" style="width: 92%;" >
					<option>是</option>
					<option selected="selected">否</option>
				</select>
			</fieldset>
			<fieldset style="width: 48%; float: left; margin-right: 3%;">
				<label style='color:#F00'>新闻标题</label> <input type="text" name="n_titles" placeholder="请填写新闻标题">
			</fieldset>
			<fieldset style="width: 48%; float: left; ">
				<label style='color:#F00'>选择发布时间</label><input  id="d121" name = "n_publishtime" type="text" placeholder="请选择时间" onfocus="WdatePicker({isShowWeek:true})"/>
			</fieldset>
			<fieldset>
				<label >新闻作者</label> <input type="text" name="n_author" placeholder="请填写新闻作者">
			</fieldset>
			
			<fieldset>
				<label style='color:#F00'>新闻概要</label> <textarea rows="5" name="n_summary" placeholder="请填写新闻摘要"></textarea>
			</fieldset>
			<fieldset>
				<label style='color:#F00'>新闻内容</label>
				<textarea id="editor" name="n_content"></textarea>
			</fieldset>
			
			<fieldset><div id="fileUplodeDiv"></div></fieldset>

			<div class="clear"></div>
		</div>
		<footer>
		<div class="submit_link">
			<input type="submit" value="Publish" class="alt_btn"> <input
				type="reset" value="Reset">
		</div>
		</footer> </article>
	</form>
	
	    
</body>
</html>
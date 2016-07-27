<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../commons.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>简历添加</title>
<style type="text/css">
body {
	margin: 5 5 5 5;
	overflow: hidden;
}
</style>



</head>
<body>

	<div class="easyui-panel" title="用户添加" style="width: 400px">
		<div style="">
			<form id="ff" class="easyui-form" method="post" data-options="novalidate:true" action="">
				<table cellpadding="5">
					<tr>
						<td>招聘题目:</td>
						<td><input class="easyui-textbox" type="text" name="resumeName"  value=""></input></td>
					</tr>
					<tr>
						<td>类型:</td>
						<td><input class="easyui-textbox" type="text" name="type"  value=""></input></td>
					</tr>
					<tr>
						<td>发布人:</td>
						<td><input class="easyui-textbox" type="text" name="publisher"  value=""></input></td>
					</tr>
					<tr>
						<td>发布时间:</td>
						<td><input class="easyui-textbox" type="text" name="publisherTime"  value=""></input></td>
					</tr>
					<tr>
						<td>发布状态:</td>
						<td><input class="easyui-textbox" type="text" name="publisherStatus"  value=""></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateform()">提交</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#updateRecruit').dialog('close');">关闭</a>
			</div>
		</div>
	</div>

</body>


</html>
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

<title>用户管理</title>
<style type="text/css">
body {
	margin: 5 5 5 5;
	overflow: hidden;
}
</style>
</head>
<body>

	<div class="easyui-panel" title="用户编辑" style="width: 400px">
		<div style="">
			<form id="ff" class="easyui-form" method="post" data-options="novalidate:true" action="<%=basePath%>userController/save.do">
				<input type="hidden" name="id" value="${user.id }">
				<table cellpadding="5">
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${user.name }"></input></td>
					</tr>
					<tr>
						<td>充值金额:</td>
						<td><input class="easyui-textbox" type="text" name="amount" data-options="required:true" value="${user.amount}"></input></td>
					</tr>
					<tr>
						<td>权限:</td>
						<td>
						普通用户<input type="radio" name="userRole" value="1" ${user.userRole == 1 ? "checked" : ""} ><br> 
						企业用户<input type="radio" name="userRole" value="2" ${user.userRole == 2 ? "checked" : ""}>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitform()">提交</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.top.closePanel();">关闭</a>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	function submitform() {
		$.ajax({
			type : "POST",
			url : "<%=basePath%>userController/update.do",
			data : $("#ff").serialize(),
			success : function(msg) {
				if(msg == "1"){
					alert("保存成功!");
				}else{
					alert("保存出错!"+msg);
				}
				window.top.closePanel(1);
			}
		});
	}
</script>


</html>
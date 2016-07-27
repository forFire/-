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

<title>客户添加</title>
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
			<form id="addCustomer" class="easyui-form" method="post" data-options="novalidate:true" action="">
				<table cellpadding="5">
					<tr>
						<td>客户名称:</td>
						<td><input class="easyui-textbox" type="text" name="name"  value=""></input></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input class="easyui-textbox" type="text" name="address"  value=""></input></td>
					</tr>
					<tr>
						<td>性别:</td>
						<td>
						  <select class="sex" >
						     <option value="1">男</option>
						      <option value="0">女</option>
						  </select>
						  </td>
<!-- 						<td><input class="easyui-textbox" type="text" name="sex"  value=""></input></td> -->
					</tr>
					<tr>
						<td>电话:</td>
						<td><input class="easyui-textbox" type="text" name="phone"  value=""></input></td>
					</tr>
					
					<tr>
						<td>QQ:</td>
						<td><input class="easyui-textbox" type="text" name="qq"  value=""></input></td>
					</tr>
					
					<tr>
						<td>Email:</td>
						<td><input class="easyui-textbox" type="text" name="email"  value=""></input></td>
					</tr>
				
					<tr>
						<td>备注:</td>
						<td><input class="easyui-textbox" type="text" name="memo"  value=""></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitform()">提交</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#addCustomer').dialog('close');">关闭</a>
			</div>
		</div>
	</div>

</body>


</html>
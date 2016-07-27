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
<meta charset="UTF-8">
<title>用户管理</title>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
</head>
<body>
	<div style="padding: 8px; height: auto">
		账户名称: <input class="easyui-validatebox" type="text" id="name" name="name" data-options="required:true"> <a href="javascript:datasearch();" class="easyui-linkbutton"
			data-options="iconCls:'icon-search'">查找</a>
	</div>
	<table id="userGrid" class="easyui-datagrid" style="width: 910px; height: 500px; overflow-y: hidden">
	</table>

	<script type="text/javascript">
		var name = $("#name").val();
		$('#userGrid').datagrid({
			title : "账户列表",
			url : '../../userController/getUsers.do',
			pagination : true,
			pageSize : 15,//每页显示的记录条数，默认为10  
			showPageList : true,
			pageList : [ 5, 10, 15, 20 ],//可以设置每页记录条数的列表
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'id',
				title : 'ID',
				width : 180
			}, {
				field : 'name',
				title : '名称',
				width : 180
			}, {
				field : 'lastlogin',
				title : '最后登录时间',
				width : 180
			} ] ],
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : true,
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					newUser();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editUser();
				}
			}, '-', {
				text : '重置密码',
				iconCls : 'icon-edit',
				handler : function() {
					resetpassword();
				}
			}, '-', {
				text : '绑定北斗卡',
				iconCls : 'icon-edit',
				handler : function() {
					bindrdcard();
				}
			} ]
		});

		function newUser() {
			window.top.openPanel("<%=basePath %>pages/user/useradd.jsp", 410, 275, this, callback);
		}
		function editUser() {
			var row = $('#userGrid').datagrid('getSelected');
			if(row){
				window.top.openPanel("<%=basePath %>userController/forUpdateUser.do?id="+row.id, 410, 205, this, callback);
			}else{
				alert("请先选择!");
			}
		}
		function resetpassword(){
			var row = $('#userGrid').datagrid('getSelected');
			if(row){
				window.top.openPanel("<%=basePath %>userController/forResetpassword.do?id="+row.id, 410, 223, this, callback);
			}else{
				alert("请先选择!");
			}
		}
		function bindrdcard(){
			var row = $('#userGrid').datagrid('getSelected');
			if(row){
				window.top.openPanel("<%=basePath %>userController/forBindrdcard.do?id="+row.id, 503, 490, this, callback);
			}else{
				alert("请先选择!");
			}
		}
		function callback(){
			$('#userGrid').datagrid('reload');
		}
		function datasearch() {
			var name = $("#name").val();
			var queryParams = {
				'name' : name
			};
			$('#userGrid').datagrid('options').queryParams = queryParams;
			$('#userGrid').datagrid('reload');
		}
	</script>
</body>
</html>
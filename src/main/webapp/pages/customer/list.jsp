<%@ page language="java" import="java.util.*,com.model.User" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/pages/commons.jsp"></jsp:include>
<head>
<title>客户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 100%; height: 660px;">
		
<!-- 		<div data-options="region:'north'" style="height: 150px"> -->
		
<!-- 		</div> -->
<!-- 		<div data-options="region:'south',split:true" style="height: 50px;"></div> -->
<!-- 		<div data-options="region:'east',split:true" title="East" style="width: 100px;"></div> -->
	
<!-- 		<div data-options="region:'west',split:true" title="左侧" style="width: 200px;"> -->
<!-- 			<a href="/pages/resume/list.jsp" style="border-left: 3">订单页面</a> -->
<!-- 		</div> -->
    	
    	<div id="addCustomer"></div>
		<div id="updateCustomer"></div>
		<div data-options="region:'center',title:'客户列表',iconCls:'icon-ok'"
			style="width: 100px;">
			<table id="customerTable" style="height: 100%; width: 100%;"></table>
		</div>
		<input type="text" id="fname" />
		
	</div>

	<script type="text/javascript">
		$(document).ready(function() {//datagrid设置
				$('#customerTable').datagrid(
									{ // title : '招聘列表', //表格标题
										iconCls : 'icon-list', //表格图标
										nowrap : false, //是否只显示一行，即文本过多是否省略部分。
										striped : true,
										fitColumns : true, //防止水平滚动
										scrollbarSize : 0, //去掉右侧滚动条列
										url : "/customers/list.do", //action地址
										idField : 'id',
										collapsible : false,//是否可折叠的 
										singleSelect : true,//只能单选
										frozenColumns : [ [ {
											field : 'ck',
											checkbox : true
										} ] ],
										pagination : true, //包含分页
										pageSize : 10,
										pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表 
										rownumbers : true,
										singleSelect : false,//只能单选
										columns : [ [{
											field : 'id',
											title : 'ID',
											align : 'center',
											hidden : true
										}, {
											field : 'name',
											title : '客户名称',
											width : 100,
											align : 'center'
										},{
											field : 'address',
											title : '地址',
											width : 100,
											align : 'center'
										},{
											field : 'phone',
											title : '电话',
											width : 100,
											align : 'center'
										},{
											field : 'email',
											title : '邮箱',
											width : 100,
											align : 'center'
										},{
											field : 'sex',
											title : '性别',
											width : 100,
											align : 'center',
											formatter : function(value, row,index) {
												if (value == "0") {
													return '女';
												} else {
													return '男';
												}
											}
										}]],
										
										toolbar : [
												{
													text : '添加',
													iconCls : 'icon-add',
													handler : function() {
														//显示上传界面
														$('#addCustomer').dialog(
															{
																title : '客户管理|添加客户信息',
																width : 500,
																iconCls : 'icon-add',
																height : 300,
																closed : false,
																cache : false,
																href : '/pages/customer/add.jsp',
																modal : true
															});

													}

												},
												'-',
												{
													text : "修改",
													iconCls : "icon-edit",
													handler : function() {
														//选中要修改删除的行
														var rows = $("#customerTable").datagrid('getSelections'); //返回所有选中的行
// 														 var row = $('#customerTable').datagrid('getSelected');
														if (rows.length > 0) {//选中几行的话触发事件
															//获得编号
															var id = rows[0].id;
															 
															//显示修改界面
															$('#updateCustomer').dialog(
																{
																	title : '房产管理|修改房屋信息',
																	width : 500,
																	iconCls : 'icon-edit',
																	height : 300,
																	closed : false,
																	cache : false,
																	href : 'edit.jsp',
																	modal : true,
																   
																	onLoad:function(){
																		console.log('222');
																	   $('#id').val("赋值"); 
																	   $("#name").val("赋值2"); 
																	},onLoadSuccess:function(data){ 
																		 $("#name").val("赋值2"); 
											                        }
											                        
																	
																});
															  var row = $("#customerTable").datagrid("getSelected");
														        if(row){
															 		$("#ff").form("load",row);
														        }
// 															 $("#test").val("1231"); 
															 $("#name").val("赋值2"); 
														}else {
															alert("提示, 请选中要编辑的行！")
// 												            $.messager.alert("提示, 请选中要编辑的行！");
												        }
// 														$('#updateCustomer').attr('data-id','id1111');
// 														$("#id").show();
// 														$("#id").attr('data-id',$('#updateCustomer').attr('data-id')); 
// 														console.log($('#updateCustomer').attr('data-id'), 111,$("#id"))
													}
												},
												
												'-',
												{
													text : "删除",
													iconCls : "icon-cut",
													handler : function() {
														//选中要修改删除的行
														var rows = $("#customerTable").datagrid('getSelections'); //返回所有选中的行
														if (rows.length > 0) {//选中几行的话触发事件
															$.messager.confirm("提示","您确定要删除此行么？",
																function(res) {//提示是否删除
																	if (res) {
																		//获得编号
																		var id = rows[0].id;
																		// 获得删除行索引  
																		var tableindex = $("#customerTable").datagrid('getRowIndex',id);$.post('/customers/delete.do',{"id" : id},
																			function(data) {
																				if (data.message == "2") {
																					$.messager.alert('温馨提示','删除失败!','error');
																				} else {
																					//删除选中的行
																					$("#customerTable").datagrid("deleteRow",tableindex);
																				 }
																				});
																		}
																});
														}

													}

												},
												'-',
												{
													text : "刷新列表",
													iconCls : "icon-reload",
													handler : function() {
														$("#customerTable").datagrid('reload');
													}
												} ],
										onLoadError : function() {
											$.messager.alert('温馨提示',
													'数据加载失败!', 'error');
										}
									});
					displayMsg();
				});

		//改变分页显示
		function displayMsg() {
			$('#customerTable').datagrid('getPager').pagination({displayMsg : '当前显示<font color="red"> {from} - {to} </font>条记录   共 <font color="red">{total}</font> 条记录'});
		}
		
		
		
		function submitform() {
			$.ajax({
				type : "POST",
				url : "/customers/save.do",
				data : $("#ff").serialize(),
				success : function(date) {
					
					if(date.ret == "0"){
						alert("保存成功!");
						$("#customerTable").datagrid('reload');
					}else{
						alert("保存出错!"+msg);
					}
					$('#addCustomer').dialog('close');
				}
			});
		};
			
		
		
		function updateform() {
			$.ajax({
				type : "POST",
				url : "/customers/update.do",
				data : $("#ff").serialize(),
				success : function(date) {
					
					if(date.ret == "0"){
						alert("更新成功!");
						$("#customerTable").datagrid('reload');
					}else{
						alert("更新出错!"+msg);
					}
					$('#updateCustomer').dialog('close');
				}
			});
		};
		
		
		
		
	</script>
</body>

</html>
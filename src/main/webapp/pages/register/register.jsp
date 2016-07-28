<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="../commons.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css" />
</head>
<body>
	<div style="background: url(images/loginbgimg.jpg) no-repeat 0 0;background-size:cover; width: 100%; height: 100%;">
		<div class="rd_login_box loginWrap">
			<div class="rd_login_box_t">
				<div class="login_box">
				
				
						<label for="">用户名：</label> <input type="text" id="username" class="login_value login_username" placeholder="请输入用户名">
						
						<br/>
						<label for="">密码：</label> <input type="password" id="password" class="login_value login_passwd" placeholder="请输入密码">
						<br/>
					
					<div class="login_remember">
						<input type="button" id="registerBtn" value="注  册" class="register_btn"></input>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		
// 		var data = {
// 				'username' : me.username.val(),
// 				'password' : me.password.val()
// 			};
		
		$("#registerBtn").click(function() {
			$.ajax({
				type : 'post',
				url : '<%=basePath%>register/register.do',
				data:{userName:$("#username").val(),password:$.md5($("#password").val())},
				dataType:'json',
				success : function(obj) {
					console.log(obj);
					if(obj.code == 1){
						window.top.location = '<%=basePath%>' + "index.jsp";
					}else{
						$.messager.alert("提示",obj.desc);
					}
				}
			});
		});
		
		
	});
</script>


</html>
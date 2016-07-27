<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

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
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/bindcard.css" />
<style type="text/css">
body {
	/* 	margin: 50 50 50 50; */
	overflow: hidden;
}
</style>
</head>
<body>
	<div id='dialogDevGroup'>
		<div class="dialog">
			<div class="head">
				<div class="headInner">
					<div class="btns">
						<button class="cancel" onclick="window.top.closePanel();">关闭</button>
						<button class="ok" onclick="saveForm();">确定</button>
					</div>
				</div>
			</div>
			<div class="content">
				<div class="group-left">
					<div class="searchWrap">
						<form name="searchForm" id="searchForm">
							<input type="text" name="seachTxt" class="seachTxt"> <input type="hidden" name="userid" value="${user.id}">
							<input type="button" onclick="searchCards();" value="确定">
						</form>
					</div>
					<div id="noselectedDiv" class="group-inf">
						<c:forEach items="${noBindsns }" var="sn">
							<label><input id="${sn}" type="checkbox" value="${sn}" onchange="on_checked(this);" />${sn}</label>
						</c:forEach>
					</div>
				</div>
				<div class="group-right">
					<form name="cardform" id="cardform">
						<input type="hidden" name="userid" value="${user.id}">
						<div class="rightWrapCon">已选北斗卡</div>
						<div id="selectedDiv" class="group-imei">
							<c:forEach items="${userSns}" var="sn">
								<label><input id="${sn}" name="cards" type="checkbox" checked="checked" value="${sn}" onchange="on_unchecked(this);" />${sn}</label>
							</c:forEach>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function on_checked(obj) {
		if (obj.checked) {
			var size = $("#selectedDiv").find("input[id='" + obj.value + "']").size();
			if (size < 1) {
				$("#selectedDiv").append('<label><input id="' + obj.value + '" name="cards"  type="checkbox" onchange="on_unchecked(this);" checked="checked" value="' + obj.value + '"/>' + obj.value + '</label>');
			}
		} else {
			var objs = $("#selectedDiv").find("input[id='" + obj.value + "']");
			var size = objs.size();
			if (size > 0) {
				objs.parent().remove();
			}
		}
	}
	function on_unchecked(obj) {
		if (!obj.checked) {
			$(obj).parent().remove();
			var objs = $("#noselectedDiv").find("input[id='" + obj.value + "']");
			objs.removeAttr("checked");
		}
	}
	function saveForm() {
		$.ajax({
			type : "POST",
			url : "<%=basePath%>userController/saveBindCard.do",
			data : $("#cardform").serialize(),
			success : function(msg) {
				if(msg == "1"){
					alert("保存成功!");
				}else{
					alert("保存出错!"+msg);
				}
				window.top.closePanel();
			}
		});
	}
	
	function searchCards(){
		$("#noselectedDiv").empty();
		$.ajax({
			type : "POST",
			url : "<%=basePath%>userController/findCard.do",
			data : $("#searchForm").serialize(),
			success : function(msg) {
				var result = JSON.parse(msg);
				for(var i=0;i<result.length;i++){
					$("#noselectedDiv").append('<label><input id="'+result[i]+'" type="checkbox" value="'+result[i]+'" onchange="on_checked(this);" />'+result[i]+'</label>');
				}
			}
		});
	}
	
</script>


</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery_dialog.js"></script>
<script type="text/javascript" src="<%=basePath%>js/datepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.md5.js"></script>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>


<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.4.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.4.2/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery_dialog.css">




<script type="text/javascript">
	var swindow;
	var fname;
	function openPanel(iframeSrc, iframeWidth, iframeHeight, sw, fn) {
		swindow = sw;
		fname = fn;
		JqueryDialog.openPanel(iframeSrc, iframeWidth, iframeHeight);
	}
	function closePanel(o1, o2, o3) {
		JqueryDialog.closePanel();
		if (swindow && fname) {
			try {
				fname(o1, o2, o3);
			} catch (err) {
			}
		}
	}
</script>
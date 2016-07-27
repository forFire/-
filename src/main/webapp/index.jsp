<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="${ctx}/pages/commons.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <title>demo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/WEB-RES/lib/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/WEB-RES/lib/themes/icon.css">
    <script type="text/javascript" src="${ctx}/WEB-RES/lib/jquery.easyui.min.js"></script>
    <script type="text/javascript" src='${ctx}/WEB-RES/lib/outlook2.js'> </script>
    <script type="text/javascript" src="${ctx}/WEB-RES/lib/easyui-lang-zh_CN.js"></script>
    <link rel="shortcut icon" href="${ctx}/WEB-RES/styles/images/favicon.ico" type="image/x-icon" />
    <style type="text/css">

.z_header {
	height: 85px;
	background: #024A9E  ; 
	overflow: hidden;
	position: relative;	
	border-bottom:2px solid #f2b759;
}
.indexBJ{
	width:100%;
	height:250px;
	position:absolute;
	top:50%;
	left:0;
	margin-top:-175px;
}
    </style>
</head>
<body class="easyui-layout z_layout">


    <div data-options="region:'north'" class="z_header">
	    <div class="z_header-nav">
			<ul>
				<li><span class="z_logintxt">您好：<small></small>，欢迎登录!
				</span></li>
				<li><a class=" z_button" href="#"><span
						class="z_buttontxt">进入论坛</span></a></li>
				<li><a class=" z_button" href="javascript:edit()"><span
						class="z_buttontxt">密码修改</span></a></li>
				<li><a class="z_button" href="javascript:quit()"><span
						class="z_buttontxt">退出系统</span></a></li>
			</ul>		
		</div>
		<!-- 用户重置密码-->
		<div class="easyui-dialog" id="editPassword" style="overflow: hidden" closed="true">
			<iframe id="iframe_editPassword" width="100%" height="99%" scrolling="no"
				style="overflow: hidden" frameborder="0"></iframe>
		</div>
    </div>
    <div data-options="region:'west',split:true,headerCls:'z_nav-header' "
		title="导航菜单" class="z_nav">
        <div id="nav" class="easyui-accordion"  style="list-style: none;" fit="true" border="false">
		</div>
	</div>
    <div data-options="region:'center'" class="z_main">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
           	 <div title="首页"  class="z_welcomewrap">
           	 	<div class="indexBJ">
           	 		什么鸟
           	 	</div>	
           	  </div>
        </div>
    </div>
    
<script type="text/javascript">
var _menus;
	<% if("admin".equals("admin")){ %>
	 	_menus ={"menus":[
				{"menuid":"a2","icon":"icon-collect","menuname":"产品管理",
					"menus":[
							{"menuid":"20","menuname":"添加产品","icon":"tree-file","url":"jsp/product/addProduct.jsp"},
							{"menuid":"21","menuname":"产品列表","icon":"tree-file","url":"jsp/product/productList.jsp"}
						]
				}

        ]};
	<%}%>

</script>        
</body>
</html>
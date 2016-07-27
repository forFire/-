/***********************************************************************


**********************************************************/
$(function(){
	//客户 产品
	$('#customer_product').datagrid({
		url : 'queryCustomerProductList.action?random='+new Date().getTime(),
		queryParams : {
			productName: '',
			startTime:'',
			endTime:''
		},
		width : $(window).width()*0.71,
		height : $(window).height()*0.55,
		method:'post',
		//singleSelect : true,
		fitColumns : true,
		nowrap : false,
		pagination : true,
		rownumbers : true,
		columns : [ [
		             {field : 'id',title : 'ID',width : $(this).width() * 0.1,height : '50px',align : 'center',hidden:true},
		             {field : 'contractNumber',title : '合同编号',width : $(this).width() * 0.1,height : 100,align : 'center'},
		             {field : 'productName',title : '产品名称',width : $(this).width() * 0.2,align : 'center'},
		             {field : 'startTime',title : '服务时间',width : $(this).width() * 0.2,align : 'center'},
		             {field : 'clientUser',title : '客户签订人',width : $(this).width() * 0.2,align : 'center'},
		             {field : 'remark',title : '备注',width : $(this).width() * 0.2,align : 'center'}
		             ] ],
		             rowStyler:function(rowIndex,rowData){
		 					return 'line-height:100px;'
		 			}
	});

	InitLeftMenu();
	tabClose();
	tabCloseEven();
	})

	function shownotice(){ 
	//客户通知
	var notice_table=$("#notice_table"); 
	$.getJSON("queryNoticeByCustomer.action",{tempDate : new Date()},function(datas){
			//每次查询都先清空原来的数据
			notice_table.html("");
			if(datas != null ){
			for(var n in datas){
				      var $info_i = "<tr><td class='td_left' >"+datas[n].title+"</td><tr>";
					notice_table.append($info_i);
				}
			}else{
						notice_table.append("<tr><td align='center' height='28' ><div id='nodata_userManager' style='color: red'>暂时没有通知！</div>");
			}
			
	  });// end $.getJSON()     
	}
//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});
    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<div class="z_menu"><ul class="z_header-nav">';
		//alert(n.icon);
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="javascript:addTab(\''+o.menuname+'\',\''+o.url+'\')" rel="' + o.url + '" ><span class="'+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
		menulist += '</ul></div>';
		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid,icon);

		addTab(tabTitle,url,icon);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true
			//icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	refreshTab({tabTitle:subtitle,url:url});
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99.5%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
function refreshTab(cfg){  
    var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
    var _refresh_ifram = refresh_tab.find('iframe')[0];  
    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
    //_refresh_ifram.src = refresh_url;  
    _refresh_ifram.contentWindow.location.href=refresh_url;  
    }  
}  
function quit() {
	window.location.href = "login.jsp";
}
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>功能角色列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listFuncRole.do?method=listFuncRole',
			rowNumber: true,
// 			checkbox: true,
// 			singleCheck: false,
			columns: [
				{header:'角色名称', field:'roleName', width:200},
				{header:'描述', field:'roleDesc', width:500},
			    {header:'操作', css:'operation',tip:false, type: 'html', width:300, render: 
					function(v,r){
						var buff = [];
						buff.push($.format('<button class="btn_add handel_float_button" onclick="update({roleId})">修改</button>', r.data));
						buff.push($.format('<button class="btn_ok handel_float_button" onclick="show({roleId})">查看</button>', r.data));
						buff.push($.format('<button class="btn_delete handel_float_button" onclick="delete1({roleId})">删除</button>', r.data));
						return buff.join('');
					}
				}  
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		},
		{
			xtype: 'Toolbar',//属性对应的构造函数
			xname: 'tbar',
			tbar: '#fr_tbar',
			buttons: {
				'add': function(){
					Widget.openContent('goAddFuncRole.do?method=goAddFuncRole');
				},
			}
		}
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- worktop.form.el.parent().outerHeight( true) //form 没有高度
			- (worktop.tbar.ct ? worktop.tbar.ct.outerHeight(true) : 0)
			- 48
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
} );
function update(roleId){
	if(roleId){
		Widget.openContent('goUpdateFuncRole.do?method=goUpdateFuncRole&roleId='+roleId,null,{width:null});
		//location.href='goUpdateFuncRole.do?method=goUpdateFuncRole&roleId='+roleId+"&goBackUrl="+goBackUrl;
		}
}
function show(roleId){
	if(roleId){
		Widget.openContent('showFuncRoleWorktop.do?method=showFuncRoleWorktop&roleId='+roleId,null,{width:null});
	}
	
}
function delete1(roleId){
	if(roleId){
		MessageBox.yes('提示','请确认是否删除?', function(){
			$.ajax({
				url : 'deleteFuncRole.do?method=deleteFuncRole',
				data : {roleId:roleId},
				dataType : 'json',
				type:'POST',
				error : function(){
					MessageBox.alert("提示","系统异常！");
				},
				success : function(data) {
					if(data.success==true){
						worktop.form.goQuery();
					}else{
						MessageBox.alert("提示","刪除失敗！");
					}
				}
			});
		})
		
	}
}
</script>
</head>
<body style="overflow-x: hidden;">
<div>
	<div class="sitemap" style="padding-bottom: 10px;">
		<ul>
			<li>当前位置：</li>
			<li>系统管理<span class="spanColor"> > </span></li>
			<li>系统安全<span class="spanColor"> > </span></li>
			<li>功能权限角色维护</li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="search">
		<form id="fr_wsid" action="#" method="post">
			<dl class="search-horizontal">
				<dt>角色名称：</dt>
				<dd>
					<label><input type="text" name="roleName" /></label>
				</dd>
			</dl>
			<button style="float: left" class="btn_search" button-click="query">查询</button>
		</form>
		<div style="clear: both"></div>
	</div>
	<!-- 操作按钮 -->
	<div class="handel" id="fr_tbar">
		<button class="btn_add" button-click="add">新增</button>
	</div>
	<!-- 列表内容展示-->
	<table class="x-table sortable ellipsis striped hover"></table>
	<!--浮动操作列表-->
	<!-- <div id="fr_fbar" style="display: none;" class="handel_float">
		<em></em><span></span>
		<button class="btn_add" button-click="update">修改</button>
		<button class="btn_ok" button-click="show">查看</button>
		<button class="btn_delete" button-click="delete">删除</button>
	</div> -->
</div>
</body>
</html>
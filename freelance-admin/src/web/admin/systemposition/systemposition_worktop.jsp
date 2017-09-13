<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>

<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listPosition.do?method=listPosition',
			rowNumber: true,
 			checkbox: true,
 			start : '${param.pageNo}'==''?1:'${param.pageNo}',
 			iPageLength: '${param.limit}'==''?30:'${param.limit}',
// 			singleCheck: false,
			columns: [
				{header:'系统岗位名称', field:'systemPositionName', width:200},
				{header:'描述', field:'systemPositionDesc', width:200},
				{header:'功能权限角色', field:'functionRoleName', width:200},
				{header:'数据权限角色', field:'dataRoleName', width:200}
		/* 		{header:'岗位参与者', field:'', width:200} */
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#pt_wsid'
		},
		{
			xtype: 'Toolbar',//属性对应的构造函数
			xname: 'tbar',
			tbar: '#sp_tbar',
			fbar: '#sp_fbar',
			buttons: {
				'add': function(){
					Widget.openContent("goAddSystemPoistion.do?method=goAddSystemPoistion",function(){
						worktop.form.goQuery();
					});
				},
				'update': function(grid,record,worktop){
					var values = worktop.form.getValues();
					var pageNo={};
					var start = {};
					pageNo.name='pageNo';
					pageNo.value=worktop.grid.page.page+1;
					start.name='limit';
					start.value=worktop.grid.page.limit;
					values.push(pageNo);
					values.push(start);
					var params = {};
					$.each(values, function() {
						params[this.name] = this.value;
					});
					HistoryRegister.set("goBackUrl","goSystemPoistionList.do?method=goSystemPoistionList",values);
					var systemPositionOid=record.data.systemPositionOid;
					window.location.href="goUpdateSystemPoistion.do?method=goViewSystemPoistion&systemPositionOid="+systemPositionOid;
				},
				'show': function(grid,record,worktop){
					var values = worktop.form.getValues();
					var pageNo={};
					var start = {};
					pageNo.name='pageNo';
					pageNo.value=worktop.grid.page.page+1;
					start.name='limit';
					start.value=worktop.grid.page.limit;
					values.push(pageNo);
					values.push(start);
					var params = {};
					$.each(values, function() {
						params[this.name] = this.value;
					});
					HistoryRegister.set("goBackUrl","goSystemPoistionList.do?method=goSystemPoistionList",values);
					var systemPositionOid=record.data.systemPositionOid;
					window.location.href="goViewSystemPoistion.do?method=goViewPoistionRole&systemPositionOid="+systemPositionOid;
				},
				'delete': function(grid,record,worktop){
					var systemPositionOid=record.data.systemPositionOid;
					MessageAction.yeah('你确认删除吗?', function(){
						$.get("deleteSystemPoistion.do?method=deleteSystemPoistion&systemPositionOid="+systemPositionOid,function(data){
							if (data.message) {
								MessageBox.alert('提示', data.message, function(){
									worktop.form.goQuery();
								})
							}
						},'json')
					})
				}
			}
		}
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- worktop.form.el.parent().outerHeight(true) //form 没有高度
			- (worktop.tbar.ct ? worktop.tbar.ct.outerHeight(true) : 0)
			- 48
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
} );
</script>
</head>
<body style="overflow-x: hidden;">
<div>
	<div class="sitemap" style="padding-bottom: 10px;">
		<ul>
			<li>当前位置：</li>
			<li>系统管理<span class="spanColor"> > </span></li>
			<li>系统安全<span class="spanColor"> > </span></li>
			<li>系统岗位维护</li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="search">
		<form id="pt_wsid" action="#" method="post">
			<dl class="search-horizontal">
				<dt>岗位名称：</dt>
				<dd>
					<label><input type="text" name="systemPositionName" value="${param.systemPositionName}" /></label>
				</dd>
			</dl>
			<button style="float: left" class="btn_search" onclick="worktop.form.goQuery();">查询</button>
		</form>
		<div style="clear: both"></div>
	</div>
	<!-- 操作按钮 -->
	<div class="handel" id="sp_tbar">
		<button class="btn_add" button-click="add">新增</button>
	</div>
	<!-- 列表内容展示-->
	<table class="x-table sortable ellipsis striped hover"></table>
	<!--浮动操作列表-->
	<div id="sp_fbar" style="display: none;" class="handel_float">
		<em></em><span></span>
		<button class="btn_add" button-click="update">修改</button>
		<button class="btn_ok" button-click="show">查看</button>
		<button class="btn_delete" button-click="delete">删除</button>
	</div>
</div>
</body>
</html>
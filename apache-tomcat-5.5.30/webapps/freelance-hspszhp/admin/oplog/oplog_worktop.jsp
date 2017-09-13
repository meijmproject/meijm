<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>用户列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
<script type="text/javascript">
var worktop = null;
$(document).ready(function(){
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'showOpLogList.do?method=showOpLogList',
			rowNumber: true,
// 			checkbox: true,
// 			singleCheck: false,
			columns: [
				{header:'操作人ID', field:'userId', width:100},
				{header:'操作人', field:'userName', width:80},
				{header:'IP地址', field:'ipAddress', width:80},
				{header:'内容', field:'functionName', width:100},
				{header:'日期', field:'logDate', width:80,tip: false},
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
			fbar: '#fr_fbar',
			buttons: {
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
});
</script>
</head>
<body style="overflow-x: hidden;">
<div>
	<div class="sitemap" style="padding-bottom: 10px;">
		<ul>
			<li>当前位置：</li>
			<li>系统管理<span class="spanColor"> > </span></li>
			<li>日志查询<span class="spanColor"></span></li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="search search-horizontal-small">
		<form id="fr_wsid" action="#" method="post">
			<dl class="search-horizontal">
	            <dt>操作人：</dt>
	            <dd><label><input type="text" name="userName" value="${param.userName}"/></label></dd>
	        </dl>
	        <dl class="search-horizontal">
	            <dt>日期从：</dt>
	            <dd>
	            	<label> 
	            	   <input type="text" id="beginDate" name="beginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
	            	</label>
	            </dd>
	        </dl>
	        <dl class="search-horizontal">
	            <dt>至 ：</dt>
	            <dd>
	            	<label> 
	            	   <input type="text" id="endDate" name="endDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
	            	</label>
	            </dd>
	        </dl>
			<button style="float: left" class="btn_search" button-click="query">查询</button>
		</form>
		<div style="clear: both"></div>
	</div>
	<!-- 操作按钮 -->
	<!-- <div class="handel" id="fr_tbar">
		<button class="btn_add" button-click="add">新增</button>
	</div> -->
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

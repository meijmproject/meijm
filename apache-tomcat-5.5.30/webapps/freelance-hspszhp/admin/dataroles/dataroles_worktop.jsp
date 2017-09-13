<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>数据角色列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>

<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listDataRole.do?method=listDataRole',
			rowNumber: true,
			start : '${param.pageNo}'==''?1:'${param.pageNo}',
			iPageLength: '${param.limit}'==''?30:'${param.limit}',
// 			checkbox: true,
// 			singleCheck: false,
			columns: [
				{header:'角色名称', field:'roleName', width:100},
				{header:'描述', field:'roleDesc', width:100},
				/* {header:'操作', css:'operation', type: 'html', width:200, render: 
					function(v,r){
						return $.format('<button class="btn_add" onclick="javascript: alert(\'{roleId}\');">按钮</button>', r.data);
					}
				} */
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
				'add': function(){
					Widget.openContent('goCreateDataRole.do?method=goCreateDataRole');
					//location.href='goCreateDataRole.do?method=goCreateDataRole';
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
					HistoryRegister.set("dataRoleUpdateGoBackUrl","goDataRoleList.do?method=goDataRoleList",values);
					var roleId=record.data.roleId;
					if(roleId){
						//Widget.openContent('goUpdateFuncRole.do?method=goUpdateFuncRole&roleId='+roleId);
						//HistoryRegister.set("dataRoleUpdateGoBackUrl",'goDataRoleList.do?method=goDataRoleList');
						location.href='goUpdateDataRoleMain.do?method=goUpdateDataRoleMain&roleId='+roleId;
						}
				},
				'show': function(grid,record,worktop){
					var roleId=record.data.roleId;
					Widget.openContent('showDataRoleMains.do?method=showDataRole&roleId='+roleId,null,{width:null});
					/* 
					var goBackUrl='goDataRoleList.do?method=goDataRoleList';
					if(roleId){
						location.href='showDataRoleMains.do?method=goUpdateDataRoleMain&roleId='+roleId+"&goBackUrl="+goBackUrl;
					} */
					
				},
				'delete' : function(grid,record,worktop){
					var roleId=record.data.roleId;
					if(roleId){
						MessageBox.yes('提示','请确认是否删除?', function(){
							$.ajax({
								url : 'deleteDataRole.do?method=deleteDataRole',
								data : {roleId:roleId},
								dataType : 'json',
								type:'POST',
								error : function(){
									MessageBox.alert('提示',"删除失败，请重新操作！");
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
			<li>数据权限角色维护</li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="search">
		<form id="fr_wsid" action="#" method="post">
			<dl class="search-horizontal">
				<dt>角色名称：</dt>
				<dd>
					<label><input type="text" name="roleName" value="${param.roleName}" /></label>
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
	<div id="fr_fbar" style="display: none;" class="handel_float">
		<em></em><span></span>
		<button class="btn_add" button-click="update">修改</button>
		<button class="btn_ok" button-click="show">查看</button>
		<button class="btn_delete" button-click="delete">删除</button>
	</div>
</div>
</body>
</html>
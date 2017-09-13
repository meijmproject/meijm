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
			url: 'listUsersInfo.do?method=listUsersInfo',
			rowNumber: true,
			start : '${param.pageNo}'==''?1:'${param.pageNo}',
			iPageLength: '${param.limit}'==''?30:'${param.limit}',
// 			checkbox: true,
// 			singleCheck: false,
			columns: [
				{header:'用户姓名', field:'userName', width:80},
				{header:'用户ID', field:'userId', width:80},
				{header:'用户状态', field:'userStatus', width:80},
				{header:'单位名称', field:'unitName', width:120},
				{header:'注册日期', field:'registDate', width:80,tip: false},
				{header:'生效日期', field:'effectiveDate', width:80, tip: false},
				{header:'失效日期', field:'expiredDate', width:80, tip: false},
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
					 Widget.openContent("goUsersCreatePage.do?method=goCreateUsersPage",
							function(){
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
					HistoryRegister.set("goBackUrl","goUsersList.do?method=goUsersList",values);
					var userId=record.data.userId;
					var userOid = record.data.userOid;
					if (userOid&&userId) {
						location.href = "goUsersPositionPage.do?method=goUsersPositionPage&userOid="+userOid+"&userId="+userId;
					}
					
				},
				'delete': function(grid,record,worktop){
					var userOid = record.data.userOid;
					if(userOid){
						MessageAction.yeah('请确认是否删除?', function(){
							$.ajax({
								url : 'deleteUsers.do?method=deleteUsers',
								data : {userOid:userOid},
								dataType : 'json',
								type:'POST',
								error : function(){
									MessageBox.alert("error occured!!!");
								},
								success : function(data) {
									if(data.success==true){
									    worktop.form.goQuery();
									}else{
										alert("刪除失敗！");
									}
								}
							});
						})
						
					}
				},
				'resetps' : function(grid,record,worktop){
					var userOid=record.data.userOid;
					if(userOid){
						MessageAction.yeah('请确认是否重置密码?', function(){
							$.ajax({
								url : 'reSetPs.do?method=reSetPs',
								data : {userOid:userOid},
								dataType : 'json',
								type:'POST',
								error : function(){
									MessageBox.alert("error occured!!!");
								},
								success : function(data) {
									if(data.success==true){
										MessageBox.alert("提示","密码重置成功,默认密码为:666666");
									    worktop.form.goQuery();
									}else{
										MessageBox.alert("提示","密码重置失败！");
									}
								}
							});
						})
						
					}
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
					HistoryRegister.set("goBackUrl","goUsersList.do?method=goUsersList",values);
					var userId=record.data.userId;
					var userOid = record.data.userOid;
					//var goBackUrl='goUsersList.do?method=goUsersList';
					if (userOid&&userId) {
						location.href ="goShowUsersMain.do?method=goShowUsersMain&userOid="+userOid+"&userId="+userId;
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
});
</script>
</head>
<body style="overflow-x: hidden;">
<div>
	<div class="sitemap" style="padding-bottom: 10px;">
		<ul>
			<li>当前位置：</li>
			<li>系统管理<span class="spanColor"> > </span></li>
			<li>系统安全<span class="spanColor"> > </span></li>
			<li>用户管理</li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="search">
		<form id="fr_wsid" action="#" method="post">
			<dl class='search-horizontal' style='margin-right:10%'>
				<dt>单位名称：</dt>
				 <dd>
				 	<label>
				 		<input type="text" name="unitName" value="${param.unitName}"/>
				 	</label>
				 </dd>
			</dl>
			<dl class='search-horizontal' style='margin-right:10%'>
				<dt>用户ID：</dt>
				 <dd>
				 	<label>
				 		<input type="text" name="userId" value="${param.userId}"/>
				 	</label>
				 </dd>
			</dl>
			<button style="float: left" class="btn_search" >查询</button>
			<dl class='search-horizontal' style='margin-right:10%'>
				<dt>用户状态：</dt>
				 <dd>
				 	<label>
				 		<dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS9002%>" selected="${param.userStatus}" name="userStatus"  emptyText="<%=Constant.EMPTY_ALL %>"/>
				 	</label>
				 </dd>
			</dl>
			<dl class='search-horizontal' style='margin-right:10%'>
				<dt>用户姓名：</dt>
				 <dd>
				 	<label>
				 		<input type="text" name="userName" value="${param.userName}"/>
				 	</label>
				 </dd>
			</dl>
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
		<button class="btn_reset" button-click="resetps">密码重置</button>
	</div>
</div>
</body>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/include/jsp_headers.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内设机构列表</title>
</head>
<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listUbOrgByUnitOid.do?method=listUbOrgByUnitOid&unitOid=${unitOid}',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			el:'#verPersonId',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'工号', field:'personCode', width:80},
				{header:'姓名', field:'name', width:80},
				{header:'性别', field:'sexCodeDesc', width:100},
				{header:'科室', field:'hirDeptName', width:100},
				{header:'人员类别', field:'personTypeDesc', width:80},
				{header:'岗位', field:'hisPositionName', width:100},
				{header:'电话', field:'mobilePhone', width:100},
				{header:'外出类型', field:'goOutTypeDesc', width:100},
				{header:'开始日期', field:'startDate', width:100},
				{header:'截止日期', field:'endDate', width:100},
				{header:'外出天数', field:'dayCount', width:100},
				{header:'经费预算', field:'fundsBudget', width:100},
				{header:'业务状态', field:'taskItemStatusDesc', width:100}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		}
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.current-position').outerHeight(true)
			- $(".search-content").outerHeight(true)
			- $("#nav_tab").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			- 60
			);//48(分页48)
	}).resize();
	worktop.form.goQuery();
	hideExportBtn($('#export'));
})
</script>
</head>
<body style="overflow: hidden;">
	<div id="right" class="personBycp">
		<div class='handle-btn-group'>
			<!-- 操作按钮-->
			<div class="handle-btn clearfix" id="handel-button">
				<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="ver-add">新增</button>
	        	<button class="btn-delete btn-left-icon btn-default" button-click="ver-delete">删除</button>
	        	<button class="btn-declare btn-left-icon btn-default" button-click="biz-active">确认生效</button>
	        	<button class="btn-print btn-left-icon btn-default" button-click="contract-export" id="export">导出EXCEL</button>
			</div>
			<!-- 列表内容展示-->
			<table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
	</div>
</body>
</html>
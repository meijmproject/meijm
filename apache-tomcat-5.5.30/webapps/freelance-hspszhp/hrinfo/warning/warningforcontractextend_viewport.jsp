<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>用户列表</title>
<%-- <%@ include file="/include/js_css_admin_include.jsp"%> --%>
<script type="text/javascript">
var worktop = null;
$(document).ready(function(){
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'getBizWarningInfo.do?method=getBizWarningInfo&itemCode=${itemCode}',
			rowNumber: true,
			start : '1',
			iPageLength:'30',
			columns: [
				{header:'人员姓名', field:'name', width:80},
				{header:'人员性别', field:'sexCodeDesc', width:80},
				{header:'合同编号', field:'contractNo', width:80},
				{header:'合同截止时间', field:'contractEndStr', width:80},
				{header:'所在科室', field:'hireDeptName', width:120},
				{header:'剩余天数', field:'difference', width:80,tip: false}
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
			$('#i-tab-left-warning').height()
			-$('#pcs-search').height()-$('.page_foot').height()-48
			);//48(分页48)
	}).resize().unbind();
	worktop.form.goQuery();
	hideExportBtn($('#export'));
});

	function checkDays(){
		var days = $("#warningDays").val();
		var numTest = /^(\+|-)?\d+($|\.\d+$)/;
		if(days!=''&&!numTest.test(days)){
			MessageBox.alert("提示","请输入合法的预警天数!",function(){});
			$("#warningDays").val("");
			return false;
		}else{
			return true;
		}
	}

	function exportWarningList() {
		if(worktop.grid.tbody.find('tr').length==0){
			  MessageBox.alert("提示","暂无数据可以导出!");
			  return;
			}
		var url = 'exportWarningList.do?method=exportWarningList&itemCode=${itemCode}&limit=-1',
    params = worktop.form.getValues();
		params.forEach(function(v,i) {
		  url += '&'+v.name+'='+v.value;
		});
		location.href = url;
	}
</script>
</head>
<body style="overflow-x: hidden;">
<div>
	<div class="pcs-search">
		<form id="fr_wsid" action="#" method="post">
		 <span class="pcs-symbol">姓名</span>
         <input class="pcs-input" type="text" name="name">
         <span class="pcs-symbol">预警天数</span>
         <input class="pcs-input" type="text" name="warningDays" id="warningDays" onblur="checkDays()"  >
			<span class="pcs-symbol mr15">预警天数：<span>${bizWarningDays}</span></span>
         <button class="btn-search btn-search-icon btn-default">查询</button>
         <button class="btn-export-icon btn-search btn-default" onclick="exportWarningList()" id="export">导出</button>
		</form>
		<div style="clear: both"></div>
	</div>
	<!-- 列表内容展示-->
	<table class="x-table sortable ellipsis striped hover"></table>
</div>
</body>

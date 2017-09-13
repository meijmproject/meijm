<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
 </HEAD>
<BODY>
<div class="Tab_query">
<div class="search search-horizontal-small" id="query-condition">
	    <form id="verperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>人员姓名：</dt>
	            <dd><label><input type="text" name="name" value="${param.name}"/><input type="text" name="unitOid" hidden="hidden" value="${unitOid}"/></label></dd>
	        </dl>
	        <dl class="search-horizontal">
	            <dt>证件号码：</dt>
	            <dd><label><input type="text" name="idNo" value="${param.idNo}"/></label></dd>
	        </dl>
	        <dl class="search-horizontal">
	            <dt>人员状态：</dt>
	            <dd><label><dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS0009%>" selected="${param.personStatus}" name="personStatus"  emptyText="<%=Constant.EMPTY_ALL %>"/></label></dd>
	        </dl>
	        <button style="float: left" class="btn_search" onclick="worktop.form.goQuery();">查询</button>
	        </form>
	        <div style="clear: both"></div>
	    </div>
<!-- 操作按钮-->
<div class="handel" id="handel-button" style="height: 10px;">
</div>
<!-- 列表内容展示-->
<div class="main_list">
<div style="overflow:hidden;"><!--style="width: auto;overflow: auto" -->
<table id="unitList" class="x-table sortable ellipsis striped hover"></table>
<!--浮动操作列表-->
	<div id="fr_fbar" style="display: none;" class="handel_float">
		<em></em><span></span>
		<button class="btn_ok" button-click="show">查看</button>
	</div>
</div>
</div>
</div>
</BODY>
<script type="text/javascript">
var worktopUnit = null;
$(document).ready(function() {
	worktopUnit = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listVerPerson.do?method=listVerPerson',
			lengthMenu:[30,50,100],
			start : '${param.pageNo}'==''?1:'${param.pageNo}',
			iPageLength: '${param.limit}'==''?30:'${param.limit}',
			paginationType: 'input',
			el:'#unitList',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'单位名称', field:'unitName', width:180},
				{header:'人员姓名', field:'name', width:80},
				{header:'证件号码', field:'idNo', width:120},
				{header:'人员状态', field:'personStatusDesc', width:80},
				{header:'职务', field:'administrativeDuty', width:100},
				{header:'职务层次', field:'administrativeDutyLevelDesc', width:100},
				{header:'现职务层次开始时间', field:'administrativeStartDate', width:150, tip: false},
				{header:'校核完成标识', field:'isVerifiedDesc', width:120, tip: false},
				{header:'人员校核结果', field:'verifiedInfo', width:120},
				{header:'人员锁定标识', field:'isLockDesc', width:120, tip: false},
				{header:'单位锁定标识', field:'isUnitLockDesc', width:120, tip: false}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#verperson_wsid'
		},
		{
			xtype: 'Toolbar',//属性对应的构造函数
			xname: 'tbar',
			tbar: '#fr_tbar',
			fbar: '#fr_fbar',
			buttons: {
				'show': function(grid,record,worktop){
					var selectRow = grid.selectModel.getSelectRows();
				    var personOid = selectRow[0].data.personOid;
				    var pageNo=worktop.grid.page.page+1;
					var limit=worktop.grid.page.limit;
					var url = $("#unitOne").attr('url');
					url = url +"&limit="+ limit+"&pageNo="+ pageNo; 
					$(".nav_tab_li").attr('url',url);
					var unitKind='${unitKind}'; 
					if(unitKind=='104'){
						$(".query_criteria").load("offSyBusView.do?method=update&menuCode=syVerPersonView"+"&bizPersonOid="+personOid+"&isBiz="+"N",function(){
							$(".nav_tab_li").css("display","block");
						});
					}else {
						$(".query_criteria").load("offSyBusView.do?method=update&menuCode=jgVerPersonView"+"&bizPersonOid="+personOid+"&isBiz="+"N",function(){
							$(".nav_tab_li").css("display","block");
						});
					}
					  
					
				}
			}
		}
	]);
	$(window).resize(function(){
		worktopUnit.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $("#nav_tab").outerHeight(true)
			- $("#query-condition").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			- 48
			);//48(分页48)
	}).resize();
	worktopUnit.form.goQuery();
})
</script>

</HTML>
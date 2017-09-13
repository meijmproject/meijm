<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>非考勤人员设置列表页</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="js/comm/dictionary.js"></script>
<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	var pageNo = '${param.pageNo}'==''?1:'${param.pageNo}';
	var limit = '${param.limit}'==''?30:'${param.limit}';
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listExceptionEmpolyee.do?method=listExceptionEmpolyee',
			lengthMenu:[30,50,100],
			start : pageNo,
			iPageLength: limit,
			paginationType: 'input',
			el:'#verPersonId',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'工号', field:'personCode', width:80},
				{header:'姓名', field:'name', width:80},
				{header:'性别', field:'sexCodeStr', width:20},
				{header:'证件类型', field:'idCodeStr', width:80},
				{header:'证件号码', field:'idNo', width:80},
				{header:'科室', field:'hireDeptName', width:80},
				{header:'岗位', field:'hisPositionName', width:80}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		}, {
			xtype: 'Toolbar',//属性对应的构造函数
	          xname: 'tbar',
	          tbar: '#handel-button',
	          fbar: '#fr_fbar',
	          buttons: {
        	  		'atn_add':function(){
	        			var values = worktop.form.getValues();
						var pageNo = {};
						var start = {};
						pageNo.name = 'pageNo';
						pageNo.value = pageNo;
						start.name = 'limit';
						start.value = limit;
						values.push(pageNo);
						values.push(start);
						HistoryRegister.set("backToExceptionEmployeeConfigPage", "goToPersonAbsenteeismsetManageWorkbench.do?method=goToPersonAbsenteeismsetManageWorkbench", values);
	        			location.href = 'goCreateExceptionEmpolyeePage.do?method=goCreateExceptionEmpolyeePage&unitOid=${unitOid}';
	        		},
        	   		'atn_delete_one':function(grid,record,worktop){
        	   			var selectRow = worktop.grid.selectModel.getSelectRows();
        				var noAttendancePersonOids = new  Array();
        				var applyNames = new Array();
        				for(i=0;i<selectRow.length;i++)
        				{
        					noAttendancePersonOids[i] = selectRow[i].data.noAttendancePersonOid;
        					applyNames[i] = selectRow[i].data.name;
        				}
        				
						if(noAttendancePersonOids==null||noAttendancePersonOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
        				MessageBox.yes('提示','是否删除？',function() {
       						$.post("deleteExceptionEmployee.do?method=deleteExceptionEmployee&noAttendancePersonOids="+noAttendancePersonOids,{applyNames:applyNames.join(',')}, 
       						function(data){
       							if (data.message) 
       							{
       								MessageBox.message('提示', data.message,function()
   	    									{
   	    										worktop.form.goQuery();
   	    									}		
   	    								);
       							}
       						},'json');
       					});
        			},
        			'atn_delete':function(){
        				var selectRow = worktop.grid.selectModel.getSelectRows();
        				var noAttendancePersonOids = new  Array();
        				var applyNames = new Array();
        				for(i=0;i<selectRow.length;i++)
        				{
        					noAttendancePersonOids[i] = selectRow[i].data.noAttendancePersonOid;
        					applyNames[i] = selectRow[i].data.name;
        				}
						if(noAttendancePersonOids==null||noAttendancePersonOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
        				MessageBox.yes('提示','是否删除？',function() {
       						$.post("deleteExceptionEmployee.do?method=deleteExceptionEmployee&noAttendancePersonOids="+noAttendancePersonOids,{applyNames:applyNames.join(',')}, 
       						function(data){
       							if (data.message) 
       							{
       								MessageBox.message('提示', data.message,function()
   	    									{
   	    										worktop.form.goQuery();
   	    									}		
   	    								);
       							}
       						},'json');
       					});
        			}
    			
       	  }
         }
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
//			- $('.sitemap').outerHeight(true)
			- $('.current-position').outerHeight(true)
			- $(".search-content").outerHeight(true)
			- $("#nav_tab").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			- $("#ngv-tab").outerHeight(true)
			- 60
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
})
</script>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="personBycp">

	    <!-- 查询条件-->
	    <div class="search-content">
				<div class="search-include clearfix mrb-40">
					<form id="fr_wsid" action="#" method="post">
						<dl class='search-unit'>
							<dt class="search-unit-dt">工号：</dt>
							<dd class="search-unit-dd">
								<label> <input type="text" name="personCode" id="personCode" /> </label>
							</dd>
						</dl>
						<dl class='search-unit'>
							<dt class="search-unit-dt">姓名：</dt>
							<dd class="search-unit-dd">
								<label> <input type="text" name="name" id="name" /> </label>
							</dd>
						</dl>
						<dl class='search-unit'>
							<dt class="search-unit-dt">科室：</dt>
							<dd class="search-unit-dd">
									<input type="text" class="modal_iput" name="hireDeptName" id="hireDeptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,${unitOid},true,null,null,'true')" value="" /> 
									<input type="hidden" id="hiddenHireDeptOid" name="hireDeptOid" value="" /> 
									<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
							</dd>
						</dl>
						<dl class="search-unit search-btns">
							<dt>
								<button class="btn_search">查询</button>
							</dt>
						</dl>
					</form>
					<div style="clear: both"></div>
				</div>
			</div>
		<div class='handle-btn-group'>
			<!-- 操作按钮-->
			<div class="handle-btn clearfix" id="handel-button">
				<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="atn_add">新增</button>
	        	<button class="btn-delete btn-left-icon btn-default" button-click="atn_delete">删除</button>
			</div>
			<!-- 列表内容展示-->
			<table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
	</div>
	<!--浮动操作列表-->
	<div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
		<em></em>
	    <span></span>
	    <button class="btn-delete btn-left-icon btn-default" button-click="atn_delete_one">删除</button>
	</div>
</body>
</html>
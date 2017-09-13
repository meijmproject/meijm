<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>考勤参数设置管理列表页</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="js/comm/dictionary.js"></script>
<script type="text/javascript" src="hrcomponent/attendance/set/js/check_attendance.js"></script>
<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	var pageNo = '${param.pageNo}'==''?1:'${param.pageNo}';
	var limit = '${param.limit}'==''?30:'${param.limit}';
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listAttendance.do?method=listAttendance',
			lengthMenu:[30,50,100],
			start : pageNo,
			iPageLength: limit,
			paginationType: 'input',
			el:'#verPersonId',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'年度', field:'attendanceYear', width:10},
				{header:'周期', field:'attendancePeriod', width:5},
				{header:'起始日期', field:'startDateStr', width:30},
				{header:'结束日期', field:'endDateStr', width:30},
				{header:'日历天数', field:'calendarDays', width:15},
				{header:'应出勤天数', field:'attendanceDays', width:20},
				{header:'非出勤天数', field:'noAttendanceDays', width:20},
				{header:'非出勤日期', field:'noAttendanceDateStr', width:200},
				{header:'状态', field:'statusName', width:40}
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
        	  		'add':function(){
	        			Widget.openContent('goCreateAttendance.do?method=goCreateAttendance',function(){
	        				worktop.form.goQuery();
	        			});
	        		},
        	   		'delete-one':function(grid,record,worktop){
        				var selectRow = [record];
        				var parameterSetOids = new  Array();
        				var applyName = new Array();
        				for(i=0;i<selectRow.length;i++)
        				{
        					applyName[i] = selectRow[i].data.applyName;
        					parameterSetOids[i] = selectRow[i].data.parameterSetOid;
        				}
						if(parameterSetOids==null||parameterSetOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
        				MessageBox.yes('提示','请确认是否删除记录？',function() {

        						$.post("deleteAttendance.do?method=deleteAttendance&parameterSetOids="+parameterSetOids,{applyNames:applyName.join(',')}, 
        						function(data){
        							if (data.message) 
        							{
        								MessageBox.message('提示', data.message,function()
        										{
        											$.get('getYearList.do?method=getYearList',function(data) {
        					                        	$('#startYear').empty();
        					                        	$('#endYear').empty();
        					                        	$('#startYear').append('<option value="">--请选择--</option>');
        					                        	$('#endYear').append('<option value="">--请选择--</option>');
        					                        	$('#startMonth').val('');
        					                        	$('#endMonth').val('');
        					                        	data.forEach(function(v,i) {
        					                        		$('#startYear').append('<option value="'+v+'">'+v+'</option>');
        					                        		$('#endYear').append('<option value="'+v+'">'+v+'</option>');
        					                        	});
        					                        	worktop.form.goQuery();
        					                        },'json');
        										}		
        									);
        							}
        						},'json');
        					});
        			},
        			'delete':function(){
        				var selectRow = worktop.grid.selectModel.getSelectRows();
        				var parameterSetOids = new  Array();
        				var applyName = new Array();
        				for(i=0;i<selectRow.length;i++)
        				{
        					applyName[i] = selectRow[i].data.applyName;
        					parameterSetOids[i] = selectRow[i].data.parameterSetOid;
        				}
        					
						if(parameterSetOids==null||parameterSetOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
        				MessageBox.yes('提示','请确认是否删除记录？',function() {

        						$.post("deleteAttendance.do?method=deleteAttendance&parameterSetOids="+parameterSetOids,{applyNames:applyName.join(',')}, 
        						function(data){
        							if (data.message) 
        							{
        								MessageBox.message('提示', data.message,function()
        										{
        											$.get('getYearList.do?method=getYearList',function(data) {
        					                        	$('#startYear').empty();
        					                        	$('#endYear').empty();
        					                        	$('#startYear').append('<option value="">--请选择--</option>');
        					                        	$('#endYear').append('<option value="">--请选择--</option>');
        					                        	$('#startMonth').val('');
        					                        	$('#endMonth').val('');
        					                        	data.forEach(function(v,i) {
        					                        		$('#startYear').append('<option value="'+v+'">'+v+'</option>');
        					                        		$('#endYear').append('<option value="'+v+'">'+v+'</option>');
        					                        	});
        					                        	worktop.form.goQuery();
        					                        },'json');
        										}		
        									);
        							}
        						},'json');
        					});
        			},
        	  		'update':function(grid,record,worktop){
		      			var rows = [record];
	    				if (rows[0].data.status == '1') {
	    					MessageBox.alert('提示', '本周期考勤数据已生效，不能再修改');
	    					return;
	    				}
	    				
	    				var parameterSetOid = rows[0].data.parameterSetOid;
	    				Widget.openContent('goUpdateAttendance.do?method=goUpdateAttendance&parameterSetOid='+parameterSetOid,function(){
	        				worktop.form.goQuery();
	        			})
	    			},
	    			'view':function(grid,record,worktop){
		      			var rows = [record];
	    				var parameterSetOid = rows[0].data.parameterSetOid;
	    				Widget.openContent('viewAttendance.do?method=viewAttendance&parameterSetOid='+parameterSetOid,function(){
	        				worktop.form.goQuery();
	        			})
	    			},
	    			'active-one':function(grid,record,worktop){
	    				var selectRow = [record];
	    				var parameterSetOids = new  Array();
	    				var applyName = new Array();
	    				for(i=0;i<selectRow.length;i++)
	    				{
	    					if (selectRow[i].data.status == '1') {
	    						MessageBox.alert('提示', '存在已生效数据，无法再次进行确认生效操作');
	    						return;
	    					}
	    					parameterSetOids[i] = selectRow[i].data.parameterSetOid;
	    					applyName[i] = selectRow[i].data.applyName;
	    				}
    					if(parameterSetOids==null||parameterSetOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
	    				MessageBox.yes('提示','是否确认生效？',function() {

	    					$.post("goActiveAttendance.do?method=goActiveAttendance&parameterSetOids="+parameterSetOids,{applyNames:applyName.join(',')},
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
	    			'active':function(){
	    				var selectRow = worktop.grid.selectModel.getSelectRows();
	    				var parameterSetOids = new  Array();
	    				var applyName = new Array();
	    				for(i=0;i<selectRow.length;i++)
	    				{
	    					if (selectRow[i].data.status == '1') {
	    						MessageBox.alert('提示', '存在已生效数据，无法再次进行确认生效操作');
	    						return;
	    					}
	    					parameterSetOids[i] = selectRow[i].data.parameterSetOid;
	    					applyName[i] = selectRow[i].data.applyName;
	    				}
    					if(parameterSetOids==null||parameterSetOids==""){
    						MessageBox.alert('提示', "请至少选择一条记录！");
    						return;
    					} 
	    				MessageBox.yes('提示','是否确认生效？',function() {

	    					$.post("goActiveAttendance.do?method=goActiveAttendance&parameterSetOids="+parameterSetOids,{applyNames:applyName.join(',')},
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
						<dl class='search-unit' style="width: 20%">
							<dt class="search-unit-dt">开始年度：</dt>
							<dd class="search-unit-dd">
									<select id="startYear" class="modal_iput" name="startYear" onchange="changeStartYear(this.options[this.options.selectedIndex].value)">
					            	<option value="">--请选择--</option>
									<c:if test="${!empty listYear}">
										<c:forEach var="dto" items="${listYear}">
										<option value="${dto}">${dto}</option>
										</c:forEach>
									</c:if>
								</select>
							</dd>
						</dl>
						<dl class='search-unit' style="width: 10%">
							<dt class="search-unit-dt">开始周期：</dt>
							<dd class="search-unit-dd">
								<select id="startMonth" class="modal_iput" name="startMonth">
								</select>
							</dd>
						</dl>
						<dl class='search-unit' style="width: 20%">
							<dt class="search-unit-dt">截止年度：</dt>
							<dd class="search-unit-dd">
								<select id="endYear" class="modal_iput" name="endYear" onchange="changeEndYear(this.options[this.options.selectedIndex].value)">
					            	<option value="">--请选择--</option>
									<c:if test="${!empty listYear}">
										<c:forEach var="dto" items="${listYear}">
										<option value="${dto}">${dto}</option>
										</c:forEach>
									</c:if>
								</select>
							</dd>
						</dl>
						<dl class='search-unit' style="width: 10%">
							<dt class="search-unit-dt">截止周期：</dt>
							<dd class="search-unit-dd">
								<select id="endMonth" class="modal_iput" name="endMonth">
								</select>
							</dd>
						</dl>
						 <dl class="search-unit search-btns">
						 <dt><button  onclick="changeInitYear()">查询</button></dt>
						 </dl>
					</form>
					<div style="clear: both"></div>
				</div>
			</div>
		<div class='handle-btn-group'>
			<!-- 操作按钮-->
			<div class="handle-btn clearfix" id="handel-button">
				<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
	        	<button class="btn-delete btn-left-icon btn-default" button-click="delete">删除</button>
	        	<button class="btn-declare btn-left-icon btn-default" button-click="active">确认生效</button>
			</div>
			<!-- 列表内容展示-->
			<table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
	</div>
	<!--浮动操作列表-->
	<div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
     <button class="btn-look btn-left-icon btn-default" button-click="view">查看</button>
    <button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
    <button class="btn-delete btn-left-icon btn-default" button-click="delete-one">删除</button>
    <button class="btn-declare btn-left-icon btn-default" button-click="active-one">确认生效</button>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>月度考勤管理右侧科室管理工作台</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<!-- <script type="text/javascript" src="js/comm/dictionary.js"></script>
<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script> -->
<script type="text/javascript" src="hrinfo/management/attendance/workbeanch/js/attendance_common.js"></script>
<script type="text/javascript">
	var unitOid1='${unitOid}';
	var attendanceOrgWorktop = null;
	$(document).ready(
		function() {
			var pageNo = '${param.pageNo}' == '' ? 1 : '${param.pageNo}';
			var limit = '${param.limit}' == '' ? 30 : '${param.limit}';
			attendanceOrgWorktop = new Worktop([ {
				xtype : 'Xtable',
				xname : 'grid',
				url: 'listOrgAttendance.do?method=listOrgAttendance',
				lengthMenu : [ 30, 50, 100 ],
				start : pageNo,
				iPageLength : limit,
				paginationType : 'input',
				el : '#verPersonId1',
				rowNumber : true,
				checkbox : true,
				singleCheck : false,
				columns : [ {
					header : '科室',
					field : 'orgName',
					width : 80
				}, {
					header : '年度',
					field : 'attendanceYear',
					width : 80
				}, {
					header : '周期',
					field : 'attendancePeriod',
					width : 100
				}, {
					header : '起始日期',
					field : 'startDateStr',
					width : 100
				}, {
					header : '结束日期',
					field : 'endDateStr',
					width : 80
				}, {
					header : '日历天数',
					field : 'calendarDays',
					width : 100
				}, {
					header : '应出勤天数',
					field : 'attendanceDays',
					width : 100
				}, {
					header : '应出勤人数',
					field : 'attendanceTotal',
					width : 100
				}, {
					header : '正常出勤人数',
					field : 'normalTotal',
					width : 100
				},{
					header : '外出人数',
					field : 'gooutTotal',
					width : 100
				}, {
					header : '加班人数',
					field : 'overtimeTotal',
					width : 100
				}, {
					header : '其他出勤人数',
					field : 'otherAttendanceTotal',
					width : 100
				}, {
					header : '休假人数',
					field : 'vacationTotal',
					width : 100
				}, {
					header : '旷工人数',
					field : 'absenteeismTotal',
					width : 100
				}, {
					header : '调休人数',
					field : 'changeOffTotal',
					width : 100
				}, {
					header : '其他未出勤人数',
					field : 'otherNoAttendanceTotal',
					width : 100
				} , {
					header : '迟到人数',
					field : 'workLateTotal',
					width : 100
				} , {
					header : '脱岗人数',
					field : 'workOffTotal',
					width : 100
				} , {
					header : '早退人数',
					field : 'leaveEarlyTotal',
					width : 100
				} ]
			}, {
				xtype : 'QueryForm',//属性对应的构造函数
				xname : 'form',//属性名
				el : '#attendanceOrg_fr_wsid'
			}, {
				xtype: 'Toolbar',//属性对应的构造函数
		          xname: 'tbar',
		          tbar: '#org_fr_tbar',
		          fbar: '#org_fr_fbar',
		          buttons: {
		        	  'view':function(grid,record,worktop){
							var rows = [record];
							var parameterSetOid = rows[0].data.parameterSetOid;
							var orgOid = rows[0].data.orgOid;
							Widget.openContent('goViewPersonAttendance.do?method=goViewPersonAttendance&orgOid='+orgOid+'&parameterSetOid='+parameterSetOid,function(){
								//attendanceOrgWorktop.form.goQuery();
							});
							
						},'exportOrg':function(){
							 var startYear=$("#startYear").val();
							 var startMonth=$("#startMonth").val();
							 var endYear=$("#endYear").val();
							 var endMonth=$("#endMonth").val();
							 var orgOid=$("#showOid").val();
							 
							 var rows = attendanceOrgWorktop.grid.selectModel.getSelectRows();
						     var mainAccountOid = new Array();
							 for(i=0;i<rows.length;i++)
							 {
								mainAccountOid[i] = rows[i].data.mainAccountOid;
							 }
						     
							 if(''!=startYear&&''!=endYear&&null!=startYear&&null!=endYear){
									if(endYear-startYear<0){
										MessageBox.alert('提示', '截止年度不能小于开始年度');
										return;
									}
									else if(endYear-startYear==0){
										if(''!=startMonth&&''!=endMonth&&null!=startMonth&&null!=endMonth){
											if(endMonth-startMonth<0){
												MessageBox.alert('提示', '年度相同时，截止周期不能小于开始周期');
												return;
											}
										}
									}
							}
							MessageBox.openWindow('printByExcel.do?method=printByExcel&flag=org&startYear='+startYear+'&startMonth='
									+startMonth+'&endYear='+endYear+'&endMonth='+endMonth+'&orgOid='+orgOid+'&mainAccountOids='+mainAccountOid);
						}
	
			
		        	  }
		          }
		        	  
       	  ]);
	
			
			$(window).resize(
					
					function() {
						attendanceOrgWorktop.grid.wrap.find('.ct').height(
								$(window).height()
							    -$(".current-position").outerHeight(true)
								- $(".attendance-org-workbench ").outerHeight(true)
								-$(".excels-tab").outerHeight(true)
								 - 48-25);//48(分页48)
								attendanceOrgWorktop.grid.wrap.find('.ct tbody').height(attendanceOrgWorktop.grid.wrap.find('.ct').height()-51);//48(分页48)
					}).resize();

			attendanceOrgWorktop.form.goQuery();
			hideExportBtn($('#export'));
});
</script>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="search-content personBycp attendance-org-workbench">
	<!-- 查询条件-->
	<div class="search-include clearfix mrb-40 pd10-clear">
		<form id="attendanceOrg_fr_wsid" action="#" method="post">
		<dl class='search-unit' style="width: 20%">
				<dt class="search-unit-dt">开始年度：</dt>
				<dd class="search-unit-dd">
						<select id="startYear" class="modal_iput" name="startYear" onchange="changeOrgStartYear(this.options[this.options.selectedIndex].value)">
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
        <dl class="search-unit">
          <dt  class="search-unit-dt">科室：</dt>
          <dd class="search-unit-dd" style="position:relative;">
          <input type="text" class="modal_iput" name="orgName" id="orgOid" onclick="min_selOrg.min_selectOrg(this,unitOid1)">
          <input type="hidden" name="orgOid" id="showOid"/>
          <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
          </dd>
        </dl>
        <dl class="search-unit">
          <dt class="search-unit-dt">&nbsp;</dt>
          <dd class="search-unit-dd">&nbsp;</dd>
        </dl>
		<dl class='search-unit' style="width: 20%">
			<dt class="search-unit-dt">截止年度：</dt>
			<dd class="search-unit-dd">
				<select id="endYear" class="modal_iput" name="endYear" onchange="changeOrgEndYear(this.options[this.options.selectedIndex].value)">
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
        <div class="search-unit search-btns search-two-btns" id="org_fr_tbar">
            <button class="btn-search btn-default" onclick="changeInitYear()">查询</button>
            <button class="btn-like-search btn-default" button-click="exportOrg" id="export">导出excel</button>
        </div>
        
		</form>
	</div>
	</div>
	<div  style="background: #fff;">
		<!-- 列表内容展示-->
		<table id="verPersonId1"
			class="x-table sortable ellipsis striped hover personBycp"></table>
	</div>
	<!--浮动操作列表-->
	<div id="org_fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
    <button class="btn-look btn-left-icon btn-default" button-click="view">查看</button>
	</div>
</body>
</html>
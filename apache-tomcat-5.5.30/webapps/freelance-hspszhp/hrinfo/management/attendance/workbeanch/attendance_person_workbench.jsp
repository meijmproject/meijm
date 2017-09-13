<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>跳转到月度考勤管理右侧人员管理工作台</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet"
	type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="js/comm/dictionary.js"></script>
<script type="text/javascript" src="hrinfo/management/attendance/workbeanch/js/attendance_common.js"></script>
<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script>
<script type="text/javascript">
	var attendancePersonWorktop = null;
	var unitOid='${unitOid}';
	$(document).ready(
			function() {
				var pageNo = '${param.pageNo}' == '' ? 1 : '${param.pageNo}';
				var limit = '${param.limit}' == '' ? 30 : '${param.limit}';
				attendancePersonWorktop = new Worktop([ {
					xtype : 'Xtable',
					xname : 'grid',
					url: 'listPersonAttendance.do?method=listPersonAttendance',
					lengthMenu : [ 30, 50, 100 ],
					start : pageNo,
					iPageLength : limit,
					paginationType : 'input',
					el : '#verPersonId',
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
						width : 100
					}, {
						header : '周期',
						field : 'attendancePeriod',
						width : 80
					}, {
						header : '起始日期',
						field : 'startDateStr',
						width : 100
					}, {
						header : '结束日期',
						field : 'endDateStr',
						width : 100
					}, {
						header : '姓名',
						field : 'name',
						width : 80
					}, {
						header : '人员类别',
						field : 'personTypeDesc',
						width : 100
					} , {
						header : '工号',
						field : 'personCode',
						width : 100
					}, {
						header : '岗位',
						field : 'hisPositionName',
						width : 100
					}, {
						header : '应出勤天数',
						field : 'attendanceDays',
						width : 100
					},{
						header : '实际出勤天数',
						field : 'realDays',
						width : 100
					}, {
						header : '未出勤天数',
						field : 'noneAttendanceDays',
						width : 100
					}, {
						header : '正常出勤天数',
						field : 'normalTotal',
						width : 100
					}, {
						header : '外出天数',
						field : 'gooutTotal',
						width : 100
					}, {
						header : '加班天数',
						field : 'overtimeTotal',
						width : 100
					}, {
						header : '其他出勤天数',
						field : 'otherAttendanceTotal',
						width : 100
					}, {
						header : '休假天数',
						field : 'vacationTotal',
						width : 100
					}, {
			            header : '旷工天数',
			            field : 'absenteeismTotal',
			            width : 100
					}, {
					  header : '调休天数',
					  field : 'changeOffTotal',
					  width : 100
					}, {
					  header : '其他未出勤天数',
					  field : 'otherNoAttendanceTotal',
					  width : 100
					}, {
							header : '迟到次数',
							field : 'workLateTotal',
							width : 100
					} , {
						header : '脱岗次数',
						field : 'workOffTotal',
						width : 100
					} , {
						header : '早退次数',
						field : 'leaveEarlyTotal',
						width : 100
					} , {
						header : '考勤明细',
						field : 'attendanceDetails',
						width : 100
					}  , {
						header : '科室变动',
						field : 'departmentChange',
						width : 100
					}  , {
						header : '考勤项目',
						field : 'attendanceItemsDesc',
						width : 100
					}  , {
						header : '考勤项目天数',
						field : 'attendanceItemsDays',
						width : 100
					}  , {
						header : '备注',
						field : 'remark',
						width : 100
					} ]
				}, {
					xtype : 'QueryForm',//属性对应的构造函数
					xname : 'form',//属性名
					el : '#attendancePerson_fr_wsid'
				}, {
					xtype: 'Toolbar',//属性对应的构造函数
			          xname: 'tbar',
			          tbar: '#fr_tbar',
			          fbar: '#fr_fbar',
			          buttons: {
			        	  'viewPerson':function(grid,record,worktop){
								var rows = [record];
								if(rows.length==0) {
									MessageBox.alert('提示',"请至少选择一条记录再操作");
								}else if(rows.length>1){
									MessageBox.alert('提示',"只能选择一条记录操作");
								}else{
									var personParameterSetOid = rows[0].data.personParameterSetOid;
									var personOid = rows[0].data.personOid;
									Widget.openContent('goShowPersonAttendance.do?method=goShowPersonAttendance&personOid='+personOid+'&parameterSetOid='+personParameterSetOid,function(){
										//attendancePersonWorktop.form.goQuery();
									});
								}
								},'exportPerson':function(){
									
									var startYear=$("#startYear1").val();
									var startMonth=$("#startMonth1").val();
									var endYear=$("#endYear1").val();
									var endMonth=$("#endMonth1").val();
									var showPersonOid=$("#showPersonOid").val();
									var name=$("#name").val();
									var personCode=$("#personCode").val();
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
									var rows = attendancePersonWorktop.grid.selectModel.getSelectRows();
								     var individualAccountOid = new Array();
									 for(i=0;i<rows.length;i++)
									 {
										 individualAccountOid[i] = rows[i].data.individualAccountOid;
									 }
									/* MessageBox.openWindow('printByExcel.do?method=printByExcel&flag=person&startYear='+startYear+'&startMonth='+startMonth+
											'&endYear='+endYear+'&endMonth='+endMonth+'&orgPersonOid='+showPersonOid+'&name='+name+'&personCode='+personCode+'&individualAccountOids='+individualAccountOid); */
											window.location.href ='printByExcel.do?method=printByExcel&flag=person&'+($.param(attendancePersonWorktop.form.getValues()));
							}
			        		  
			        	  } 
			          }
	        	  ]);

				$(window).resize(
						function() {
							attendancePersonWorktop.grid.wrap.find('.ct').height(
									$(window).height()
									    -$(".current-position").outerHeight(true)
										- $("#attendance-person-workbench ").outerHeight(true)
										-$(".excels-tab").outerHeight(true)
										 - 48-20-10);//48(分页48)
										 attendancePersonWorktop.grid.wrap.find('.ct tbody').height(attendancePersonWorktop.grid.wrap.find('.ct').height()-51);//48(分页48)
						}).resize();

				attendancePersonWorktop.form.goQuery();
				hideExportBtn($('#export'));
			})
</script>
</head>
<body style="overflow: hidden;">
	<!-- 查询条件-->
	<div class="search-content " id="attendance-person-workbench">
		<div class="search-include clearfix mrb-40 pd10-clear">
			<form id="attendancePerson_fr_wsid" action="#" method="post">
				<dl class='search-unit' style="width: 20%">
				<dt class="search-unit-dt">开始年度：</dt>
				<dd class="search-unit-dd">
						<select id="startYear1" class="modal_iput" name="startYear" onchange="changePersonStartYear(this.options[this.options.selectedIndex].value)">
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
					<select id="startMonth1" class="modal_iput" name="startMonth">
					</select>
				</dd>
			</dl>
        <dl class="search-unit">
          <dt  class="search-unit-dt">科室：</dt>
          <dd class="search-unit-dd" style="position:relative;">
          <input type="text" class="modal_iput" name="orgPersonName" id="orgPersonOid" onclick="min_selOrg.min_selectOrg(this,unitOid)">
          <input type="hidden" name="orgPersonOid" id="showPersonOid"/>
          <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
          </dd>
        </dl>
		<dl class='search-unit'>
          <dt class="search-unit-dt">姓名：</dt>
          <dd class="search-unit-dd">
            <label> <input type="text" name="name" id="name" /> </label>
          </dd>
        </dl>
		<dl class='search-unit' style="width: 20%">
			<dt class="search-unit-dt">截止年度：</dt>
			<dd class="search-unit-dd">
				<select id="endYear1" class="modal_iput" name="endYear" onchange="changePersonEndYear(this.options[this.options.selectedIndex].value)">
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
				<select id="endMonth1" class="modal_iput" name="endMonth">
				</select>
			</dd>
		</dl>
        <dl class='search-unit'>
          <dt class="search-unit-dt">工号：</dt>
          <dd class="search-unit-dd">
            <label> <input type="text" name="personCode" id="personCode" /> </label>
          </dd>
        </dl>
        <dl class='search-unit'>
          <dt class="search-unit-dt">是否考勤异常：</dt>
          <dd class="search-unit-dd">
          	<label> <dictionary:dicItemSelect id="isAttendance" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="<%=DicConstants.YHRS0003_1%>" name="isAttendance" emptyText="--请选择--" /> </label>
          </dd>
        </dl>
        <div class="search-unit search-btns search-two-btns" id="fr_tbar">
            <button class="btn-search btn-default" onclick="changePersonYear()">查询</button>
            <button  class="btn-like-search btn-default" button-click="exportPerson" id="export">导出excel</button>
        </div>
			</form>
		</div>
	</div>
	<div  style="background: #fff;">
		<!-- 列表内容展示-->
		<table id="verPersonId"
			class="x-table sortable ellipsis striped hover personBycp"></table>
	</div>
	<!--浮动操作列表-->
	<div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
    <button class="btn-look btn-left-icon btn-default" button-click="viewPerson">查看</button>
	</div>
</body>
</html>
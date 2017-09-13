<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    人员考勤信息查看页面
 * @page name   hrinfo/management/attendance/atttendance_person_show_list.jsp
 * @author      chenjl
 * @created     2017/04/20
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>人员考勤信息查看页面</title>
</head>
<body>
<script>
$('.md-main-content').height($('body').height()-250);
$('.transaction_modal_box').width($('#popdown-opacity-cb').width()-100);
</script>
		<div class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					人员考勤信息&gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-main-content">

					<div class="infoshow-container md-infoshow-area md-infoshow-area">
						<div class="isc-title-box">
						  <h3 class="isc-title">人员考勤信息</h3>
						  </div>
						 <div class="data-units">
						<dl class="data-unit-dl">
					      <dt>科室：</dt>
					      <dd><label title="${personDTO.orgName }"> ${personDTO.orgName } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>年度：</dt>
					      <dd><label title="${personDTO.attendanceYear}"> ${personDTO.attendanceYear }&nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>周期：</dt>
					      <dd><label title="${personDTO.attendancePeriod }"> ${personDTO.attendancePeriod } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>开始日期：</dt>
					      <dd><label title="${personDTO.startDateStr }"> ${personDTO.startDateStr } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>结束日期：</dt>
					      <dd><label title="${personDTO.endDateStr }"> ${personDTO.endDateStr } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>姓名：</dt>
					      <dd><label title="${personDTO.name }"> ${personDTO.name } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>人员类别：</dt>
					      <dd><label title="${personDTO.personTypeDesc }"> ${personDTO.personTypeDesc} &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>工号：</dt>
					      <dd><label title="${personDTO.personCode }"> ${personDTO.personCode } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>岗位：</dt>
					      <dd><label title="${personDTO.hisPositionName }"> ${personDTO.hisPositionName } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>实际出勤天数：</dt>
					      <dd><label title="${personDTO.realDays }"> ${personDTO.realDays } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>未出勤天数：</dt>
					      <dd><label title="${personDTO.noneAttendanceDays }"> ${personDTO.noneAttendanceDays } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>正常出勤天数：</dt>
					      <dd><label title="${personDTO.normalTotal }"> ${personDTO.normalTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>外出天数：</dt>
					      <dd><label title="${personDTO.gooutTotal }"> ${personDTO.gooutTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>加班天数：</dt>
					      <dd><label title="${personDTO.overtimeTotal }"> ${personDTO.overtimeTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>其他出勤天数：</dt>
					      <dd><label title="${personDTO.otherAttendanceTotal }"> ${personDTO.otherAttendanceTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>休假天数：</dt>
					      <dd><label title="${personDTO.vacationTotal }"> ${personDTO.vacationTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>旷工天数：</dt>
					      <dd><label title="${personDTO.absenteeismTotal }"> ${personDTO.absenteeismTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>调休天数：</dt>
					      <dd><label title="${personDTO.changeOffTotal }"> ${personDTO.changeOffTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>其他未出勤天数：</dt> 
					      <dd><label title="${personDTO.otherNoAttendanceTotal }"> ${personDTO.otherNoAttendanceTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>迟到次数：</dt>
					      <dd><label title="${personDTO.workLateTotal }"> ${personDTO.workLateTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>脱岗次数：</dt>
					      <dd><label title="${personDTO.workOffTotal }"> ${personDTO.workOffTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>早退次数：</dt> 
					      <dd><label title="${personDTO.leaveEarlyTotal }"> ${personDTO.leaveEarlyTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>考勤明细：</dt> 
					      <dd><label title="${personDTO.attendanceDetails }"> ${personDTO.attendanceDetails } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>科室变动：</dt> 
					      <dd><label title="${personDTO.departmentChange }"> ${personDTO.departmentChange } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>考勤项目：</dt> 
					      <dd><label title="${personDTO.attendanceItemsDesc }"> ${personDTO.attendanceItemsDesc } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>考勤项目天数：</dt> 
					      <dd><label title="${personDTO.attendanceItemsDays }"> ${personDTO.attendanceItemsDays } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl remark-unit">
					      <dt>备注：</dt> 
					      <dd><label title="${personDTO.remark }"> ${personDTO.remark } &nbsp; </label></dd>
					    </dl>
						</div>
					</div>
				</div>
				<div class="md-btn">
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关 闭</button>
				</div>
			</div>
		</div>
</body>
</html>
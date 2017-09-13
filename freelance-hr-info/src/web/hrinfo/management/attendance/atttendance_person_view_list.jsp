<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    科室人员考勤信息查看页面
 * @page name   hrinfo/management/attendance/atttendance_person_view_list.jsp
 * @author      cheny
 * @created     2017/03/28
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>科室人员考勤信息查看页面</title>
 <script>
    var orgOid="${orgOid}";
    var parameterSetOid="${parameterSetOid}";
    $(document).ready(function(){
     /*  $(".btn-return").click(function(){
        HistoryRegister.go("goToGenerateMonthManage");
      }); */
      var pageNo = '${param.pageNo}' == '' ? 1 : '${param.pageNo}';
      var limit = '${param.limit}' == '' ? 30 : '${param.limit}';
      worktop = new Worktop([ {
        xtype : 'Xtable',
        xname : 'grid',
        url: 'listPersonAttendance.do?method=listPersonAttendance&orgPersonOid='+orgOid+'&parameterSetOid='+parameterSetOid,
        lengthMenu : [ 30, 50, 100 ],
        start : pageNo,
        iPageLength : limit,
        paginationType: 'input',
        el : '#atPersonId',
        rowNumber : true,
        checkbox : true,
        singleCheck : false,
        columns : [ {
			header : '姓名',
			field : 'name',
			width : 80
		},{
			header : '人员类别',
			field : 'personTypeDesc',
			width : 100
		} ,  {
			header : '工号',
			field : 'personCode',
			width : 100
		}, {
			header : '岗位',
			field : 'hisPositionName',
			width : 100
		},{
			header : '实际出勤天数',
			field : 'realDays',
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
			header : '未出勤天数',
			field : 'noneAttendanceDays',
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
        el : '#fr_wsid'
      } ]);
      
	
		   /*  worktop.grid.wrap.find('.ct').height(
					$(window).height() -  $('.isc-title-box').outerHeight(true)*2 -$('.data-units').outerHeight(true)
			);
			worktop.grid.wrap.find('.ct tbody').height(
					$(window).height() -  $('.isc-title-box').outerHeight(true)*2 -$('.data-units').outerHeight(true) -51
			);	 */

	worktop.form.goQuery();
	$('.md-main-content').height($('body').height()-250);
	//$('.transaction_modal_box').width(1000);
	$('.transaction_modal_box').width($('#popdown-opacity-cb').width()-100);
    });
    </script>
</head>
<body>
		<div class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					人员考勤信息&gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-main-content">

					<div class="infoshow-container md-infoshow-area md-infoshow-area">
						<div class="isc-title-box">
						  <h3 class="isc-title">科室考勤信息</h3>
						  </div>
						 <div class="data-units">
						<dl class="data-unit-dl">
					      <dt>科室</dt>
					      <dd><label title="${dto.orgName }"> ${dto.orgName } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>年度</dt>
					      <dd><label title="${dto.attendanceYear}"> ${dto.attendanceYear }&nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>周期</dt>
					      <dd><label title="${dto.attendancePeriod }"> ${dto.attendancePeriod } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>开始日期</dt>
					      <dd><label title="${dto.startDateStr }"> ${dto.startDateStr } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>结束日期</dt>
					      <dd><label title="${dto.endDateStr }"> ${dto.endDateStr } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>日历天数</dt>
					      <dd><label title="${dto.calendarDays }"> ${dto.calendarDays } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>应出勤天数</dt>
					      <dd><label title="${dto.attendanceDays }"> ${dto.attendanceDays } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>应出勤人数</dt>
					      <dd><label title="${dto.attendanceTotal }"> ${dto.attendanceTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>外出人数</dt>
					      <dd><label title="${dto.gooutTotal }"> ${dto.gooutTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>加班人数</dt>
					      <dd><label title="${dto.overtimeTotal }"> ${dto.overtimeTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>其他出勤人数</dt>
					      <dd><label title="${dto.otherAttendanceTotal }"> ${dto.otherAttendanceTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>休假人数</dt>
					      <dd><label title="${dto.vacationTotal }"> ${dto.vacationTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>旷工人数</dt>
					      <dd><label title="${dto.absenteeismTotal }"> ${dto.absenteeismTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>调休人数</dt>
					      <dd><label title="${dto.changeOffTotal }"> ${dto.changeOffTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>其他未出勤人数</dt> 
					      <dd><label title="${dto.otherNoAttendanceTotal }"> ${dto.otherNoAttendanceTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>迟到人数</dt>
					      <dd><label title="${dto.workLateTotal }"> ${dto.workLateTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>脱岗人数</dt>
					      <dd><label title="${dto.workOffTotal }"> ${dto.workOffTotal } &nbsp; </label></dd>
					    </dl>
					    <dl class="data-unit-dl">
					      <dt>早退人数</dt> 
					      <dd><label title="${dto.leaveEarlyTotal }"> ${dto.leaveEarlyTotal } &nbsp; </label></dd>
					    </dl>
						</div>
					</div>
					<div class="isc-title-box">
					   <h3 class="isc-title">人员考勤记录</h3>
					 </div>
					 <table id="atPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>  
				</div>
				<div class="md-btn">
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关 闭</button>
				</div>
			</div>
		</div>
</body>
</html>
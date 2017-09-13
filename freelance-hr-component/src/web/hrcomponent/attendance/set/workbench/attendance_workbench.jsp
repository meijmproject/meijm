<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>考勤参数设置页</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="personBycp">
	    <!-- 选项卡-->
	    <ul id="ngv-tab" class="c-nav-tab clearfix">
	    	<!-- 考勤周期设置 -->
	      	<li><a id="attendanceCycle" onclick="$('#nav-body').load('goToAttendanceCycleConfigPage.do?method=goToAttendanceCycleConfigPage');">考勤周期设置</a></li>
	      	<!-- 非考勤科室设置 -->
	      	<!-- <li><a id="exceptionDepartment" onclick="#">非考勤科室设置</a></li> -->
	    	<!-- 非考勤人员设置 -->
	    	<li><a id="exceptionEmpolyee" onclick="$('#nav-body').load('goToExceptionEmpolyeeConfigPage.do?method=goToExceptionEmpolyeeConfigPage');">非考勤人员设置</a></li>
	    </ul>
	    <div id="nav-body"></div>
	    <script>
		$('.c-nav-tab a').click(function(e) {
			$(this).parent().siblings().find('a').removeClass('c-navtab-selected');
			$(this).addClass('c-navtab-selected');
		});
		$('#attendanceCycle').click();
		</script>
	    
	</div>
</body>
</html>
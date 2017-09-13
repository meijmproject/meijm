<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title></title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body>
<div class="current-position">
      当前位置：
        <span>基础信息管理 > </span>
        <span>岗位管理 > </span>
        <span>事业单位岗位管理 </span>
    <div style="clear: both"></div>
</div>
<div id="right" class="personBycp">
  <ul class="c-nav-tab clearfix">
    <li><a id="gbPlan" onclick="$('#nav-body').load('goToGBPlanWorkbeanch.do?method=goToGBPlanWorkbeanch');">岗位下达</a></li>
    <li><a id="" onclick="$('#nav-body').load('goToGbDescriptionWorkBench.do?method=goToGbDescriptionWorkBench');">岗位说明书</a></li>
  </ul>
  <div id="nav-body"></div>
</div>
<script>
$('.c-nav-tab a').click(function(e) {
	$(this).parent().siblings().find('a').removeClass('c-navtab-selected');
  	$(this).addClass('c-navtab-selected');
});
$('#gbPlan').click();
</script>
</body>
</html>
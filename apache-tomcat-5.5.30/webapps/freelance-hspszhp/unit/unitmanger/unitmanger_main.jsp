<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
   	<%@ include file="/include/jsp_headers.jsp"%>
	<%@ include file="/include/js_css_base_include.jsp"%>	
	<link href="hspszhphtml/css/components/left_nav_nanoscroller.css" rel="stylesheet" type="text/css"/>
	<link href="hspszhphtml/css/components/left_nav.css" rel="stylesheet" type="text/css"/>
 </HEAD>
<BODY style="height: 100%;  overflow: hidden;    position: fixed;" >
<div class="current-position">
    	当前位置：
        <span>基础信息管理 > </span>
        <span>机构管理 > </span>
        <span>单位管理 </span>
    <div style="clear: both"></div>
</div>
<div class="main-container" >
</div>
<div style="clear: both"></div>
<script>
var unitCount = ${unitCount};
$(document).ready(function(){
	if(unitCount>0){
		$('.main-container').load('goToUnitWorkTop.do?method=goToAddUbUnit');
	}else{
		$('.main-container').load('goToAddUbUnit.do?method=goToAddUbUnit');
	}
})
</script>
</body>
</html>
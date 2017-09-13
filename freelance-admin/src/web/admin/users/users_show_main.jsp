<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户信息明细页面</title>
    <%@ include file="/include/js_css_admin_include.jsp"%>
</head>
<div class="sitemap">
    <ul style="float: left" >
    	<li>当前位置：</li>
        <li><a href="#">系统管理<span class="spanColor"> > </span></a></li>
        <li><a href="#">系统安全<span class="spanColor"> > </span></a></li>
        <li><a href="#">用户信息</a></li>
    </ul>
    <div class="go_back"><a href="javascript:void(0);" onclick="goback();"><span style="font-family: sans-serif;"><< </span>返回</a></div>
    <div style="clear: both"></div>
</div>
<body>
<!-- 左边导航  -->
<div class="left_nav" id="left_nav" url='showUserInfo.do?method=showUserInfo&userOid=${userOid }'>
</div>
<!--中间主体内容-->
<div class="right_content main_bg" id="showUsersPosition"  url='showUsersPosition.do?method=showUsersPosition&userId=${usersForm.userId }'></div>
<script type="text/javascript">
$(function(){
	Widget.load('#left_nav');
	Widget.load('#showUsersPosition');
})
//返回
function goback(){
	HistoryRegister.go('goBackUrl');
	/* var goBackUrl="${param.goBackUrl}";
	location.href=goBackUrl; */
}
</script>
</body>
</html>
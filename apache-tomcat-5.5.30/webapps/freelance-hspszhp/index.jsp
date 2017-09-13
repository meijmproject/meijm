<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.platform.core.web.UserContext"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
    <title>医院人事管理系统</title>
    <%@ include file="/include/js_css_base_include.jsp"%>
    <link rel="stylesheet" href="hspszhphtml/css/components/head_nav.css">
    <link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
    <script src="admin/js/jTopMenu.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
    	$.get("checkPasswordForUpdate.do?method=checkPsw",function(data){
    		if(data&&data=="OK") {
	 			Widget.openContent("goUsersDefaultPasswordUpdatePage.do?method=goUsersDefaultPasswordUpdatePage");
    			$("#headerMenuId ._jTopMenu,#headerMenuId #updatepassword,iframe[name=szghrsmain]").hide();
    		}
    	});
    	
    	$('[button-click=downDoc]').click(function(){
    		MessageBox.openWindow('downFile.do?method=downFile&fileName=help.rar');
    	});
    })
    
    </script>
 </head>
<body style="overflow: hidden;">
	<div id="headerMenuId" class="head-nav">
		<div class="index-logo"></div>
             <ul class="h-nav-ul h-nav-item _jTopMenu">
             </ul>
	         <ul class="h-nav-ul h-nav-icon">
	           <li class="h-nav-li h-person-info">当前用户：<%= UserContext.getLoginUserID()%></li>
	           <li class="h-nav-li">
	               <a class="h-nav-link hn-question" href="<%=request.getContextPath()%>/hspszhphtml/helpdoc/helpdoc.rar" title="帮助中心"></a>
	           </li>
	           <li class="h-nav-li">
	               <a class="h-nav-link hn-key" id="updatepassword" title="修改密码"></a>
	           </li>
	           <li class="h-nav-li">
	               <a class="h-nav-link hn-exit" id="logoutId"  title="退出"></a>
	           </li>
          </ul>
    </div>
   <div class="navigation-down _jChildMenu">
    </div>
<!--导航结束-->
<!--主体内容-->
<iframe name="szghrsmain" frameborder="0" width="100%" style="margin: 0;"></iframe>
<!-- <div style="height:40px;width:100%;background-color:#1fc7aa;color:#fff;text-align:center;line-height:40px;font-size:12px;">版权所有&copy;深圳市嘉德永丰开发科技股份有限公司   粤ICP备06063907号-1&nbsp;&nbsp;&nbsp;Copyright 2016 &copy;  Technology All rights reserved</div> -->
<script type="text/javascript">
    $('#logoutId').click(function(){
		MessageAction.yeah('是否要退出系统？', function(){
			window.location.href = 'logout.do?method=logout';
		})
	});
    $('#updatepassword').click(function(){
		Widget.openContent("goUsersPasswordUpdatePage.do?method=goUsersPasswordUpdatePage");
	});
   	var jViewer = (function($, window, document){
   		var $frame = $('iframe[name=szghrsmain]');
   		
   		$(window).resize(function(){
   			$frame.height($(window).height() - $('#headerMenuId ').outerHeight(true));//头部菜单
   		}).resize();
   		
   		return {
   			go: function(url) {
   				$frame.attr('src', url+"&_ts="+(new Date().getTime()));
   			}
   		}
   	})(jQuery, window, document);
   	
   	//初始化事件
   	$(function(){
   		jTopMenu.init('#headerMenuId', jViewer);
   	}); 
</script>
</body>
</html>
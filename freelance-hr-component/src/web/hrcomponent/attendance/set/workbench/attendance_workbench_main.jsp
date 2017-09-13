<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
   	<%@ include file="/include/jsp_headers.jsp"%>
	<%@ include file="/include/js_css_base_include.jsp"%>	
	<!-- <link href="css/szetyy/leftNav.css" rel="stylesheet" type="text/css"/> -->
	<link href="hspszhphtml/css/components/left_nav_nanoscroller.css" rel="stylesheet" type="text/css"/>
	<link href="hspszhphtml/css/components/left_nav.css" rel="stylesheet" type="text/css"/>
 </HEAD>
<BODY style="height: 100%;  overflow: hidden;    position: fixed;" >
<div class="current-position">
    	当前位置：
        <span>人事信息管理 > </span>
        <span>考勤管理 > </span>
        <span>考勤参数设置 </span>
    <div style="clear: both"></div>
</div>
<div class="main-container" >
<div class="right-content">
</div>
</div>
<div style="clear: both"></div>
<script>
var unitOid= '${unitOid}';
var unitName= '${unitName}';
$(document).ready(function(){
	expandNode();
})
function expandNode(){
    //默认显示工作台  
	$(".right-content").load("goToAttendanceManagePage.do?method=goToAttendanceManagePage");
}
$(window).resize(function(){
	$('.nano.left-nav-scroll').height(
		$(window).height() 
		- $('.sitemap').outerHeight(true)
		);
}).resize(); 
// 滚动条优化
$(".nano.left-nav-scroll").nanoScroller();
$(".nano.left-nav-scroll").nanoScroller({ stop: false });
//重新计算滚条的高度
$(".nano.left-nav-scroll").nanoScroller({ sliderMaxHeight: 300 });
$(".nano.left-nav-scroll").nanoScroller({ sliderMinHeight: 80 });
$(".nano.left-nav-scroll").nanoScroller({ flashDelay: 1000 });
//点击左导航收缩展开按钮
$('.leftnav-flod-icon').click(function () {
    //展开
    if ($(this).hasClass('leftnav-unflod-icon')){
        $(this).removeClass('leftnav-unflod-icon');
        $(this).parent('div').attr('class','leftnav-fold');
        $(this).parent('div').siblings('div').show();
        $('.left-content').css('width','320px');
    }else { /*收缩*/
        $(this).addClass('leftnav-unflod-icon');
        $(this).parent('div').siblings('div').hide();
        $(this).parent('div').attr('class','leftnav-unflod');
        $('.left-content').css('width','45px');
    }
})
//左部导航隐藏
function leftNavScrollHide() {
    //获取页面可视区域高度
    var windowHeight = $(window).height();
    var leftNavMaxHeight = windowHeight - $('.head-nav').outerHeight(true)-$('.leftnav-control').outerHeight(true);
    var leftNavRealHeight = $('.left-nav').outerHeight(true);
    // 左导航高度不需要滚动时滚动条隐藏
    if (leftNavRealHeight < leftNavMaxHeight){
        $(".nano.left-nav-scroll").nanoScroller({ stop: true });
    }else {
        $(".nano.left-nav-scroll").nanoScroller({ stop: false });
    }
} 
function workLocation(orgOid)
{
	$(".right-content").load("goToAttendanceManagePage.do?method=goToAttendanceManagePage&orgOid="+orgOid);
}
function workUnitLocation(organizationOid)
{
	$(".right-content").load("goToAttendanceManagePage.do?method=goToAttendanceManagePage&organizationOid="+organizationOid);
}
</script>
</body>
</html>
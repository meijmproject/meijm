<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
 </HEAD>
<BODY>
<!-- 已办/待办事项选项卡-->
<ul class="nav_tab nav_tabPer" id="nav_tab" style="background:#fff;">
    <li class="pitch" id="unitOne"><a href="javascript:void(0);" class="pitch_a">单位人员查询</a></li>
    <li id="unitTwo"><a href="javascript:void(0);" >单位信息 </a></li>
    <li style="float:right;" class="nav_tab_li"><img src="./img/DetailPages/ico07-left.png"/><span>返回</span></li>
</ul>
<!-- 查询条件-->
<div class="query_criteria">
</div>
<div style="clear: both"></div><!--清除浮动-->
</BODY>
<script type="text/javascript">
$(document).ready(function(){
	var unitOid= '${unitOid}';
	var menuCode= '${menuCode}';
	var index= '${index}';
	if(index==''){$(".nav_tab_li").css("display","none");}
	var pageNo = '${pageNo}';
	$(".query_criteria").load("goToUnitRight.do?method=goToUnitRight&dbflag=1&unitOid="+unitOid);
	$('#unitOne').attr('url','goToUnitRight.do?method=goToUnitRight&dbflag=1&unitOid='+unitOid);
	$('#unitTwo').attr('url','goToUnitRight.do?method=goToUnitRight&dbflag=2&unitOid='+unitOid); 
	$(".nav_tab").find("li:lt(2)").click(function (){
		$(".query_criteria").find("div").remove();
		var $this = $(this);
		$this.addClass("pitch");
		$this.siblings('li').removeClass("pitch");
		$(".query_criteria").load($this.attr('url')); 
	});
	$(".nav_tab_li").click(function(){
		$(".right_content").load("goToMainTabPanel.do?method=goToMainTabPanel&menuCode="+menuCode+"&pageNo="+pageNo,function(){
			$(".nav_tab").find("li:eq("+index+")").click();
		}); 
		$(".nav_tab_li").css("display","none");
	})
})
 $(window).resize(function(){
		$('.query_criteria').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.nav_tab').outerHeight(true)
			);
}).resize(); 
</script>

</HTML>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <%@ include file="/include/jsp_headers.jsp"%>
  <link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet"
	type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet"
	type="text/css" />
	     <script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
 </HEAD>
<BODY>
<div id='rightPage'>
    <!-- 已办/待办事项选项卡-->
    <ul class="c-nav-tab clearfix" id="c-nav-tab-ul">
      <li><a id="db" onclick="$('#bizViewportFrameId').load('goBizWorkViewport.do?method=goBizWorkViewport&dbflag=1&itemNodeCode=${itemNodeCode}&menuCode=${menuCode}');">待办业务</a></li>
      <li><a id="yb" onclick="$('#bizViewportFrameId').load('goBizWorkViewport.do?method=goBizWorkViewport&dbflag=2&itemNodeCode=${itemNodeCode}&menuCode=${menuCode}');">已办业务</a></li>
    <button class="btn-return-work" id="goBack" style="display:none"> &lt; 返回</button>
    </ul>
   
   
    <div id="bizViewportFrameId" style="overflow-x: hidden;overflow-y: scroll;"></div>
    <%-- <ul class="nav_tab" id="nav_tab">
        <li url="goBizWorkViewport.do?method=goBizWorkViewport&dbflag=1&itemNodeCode=${itemNodeCode}&menuCode=${menuCode}" class="nav_tab_checked"><a href="javascript:void(0);">待办业务</a></li>
        <li url="goBizWorkViewport.do?method=goBizWorkViewport&dbflag=2&itemNodeCode=${itemNodeCode}&menuCode=${menuCode}" ><a href="javascript:void(0);">已办业务 </a></li>
        <li style="float:right;display:none" class="nav_tab_li"><img src="./img/DetailPages/ico07-left.png"/><span>返回</span></li>
    </ul>
   <div id="bizViewportFrameId" ></div> <!-- style="overflow-x: hidden;overflow-y: scroll;" --> --%>
</div>

</BODY>
<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
<script type="text/javascript">
var menuCode='${menuCode}';
var itemNodeCode='${itemNodeCode}';
$('.c-nav-tab a').click(function(e) {
	$(this).parent().siblings().find('a').removeClass('c-navtab-selected');
	$(this).addClass('c-navtab-selected');
});
$('#goBack').click(function(e){
	$('#db').click();
	$('#goBack').hide();
	e.stopPropagation();
	e.preventDefault();
})
$('#db').click();
$(window).resize(function(){
	$('#bizViewportFrameId').height(
		$(window).height() 
		- $('.current-position').outerHeight(true)
		- $('.c-nav-tab').outerHeight(true)
		);
}).resize();
/*  $(document).ready(function(){
	$(".nav_tab").find("li:lt(2)").click(function (){
		$("#bizViewportFrameId").find("div").remove();
		var $this = $(this);
		$this.addClass("nav_tab_checked");
		$this.siblings('li').removeClass("nav_tab_checked");
		//$("#bizViewportFrameId").load($this.attr('url')); 
		var pageNo = '${pageNo}';
		var url = $(".nav_tab_checked").attr('url');
		url = url + "&pageNo="+ pageNo; 
		$("#bizViewportFrameId").load(url); 
	});
	
	$(".nav_tab").find("li:first").click();
});
 $(window).resize(function(){
		$('#bizViewportFrameId').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.nav_tab').outerHeight(true)
			);
	}).resize(); */
</script>
</HTML>
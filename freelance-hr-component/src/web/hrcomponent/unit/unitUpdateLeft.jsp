<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
   	<%@ include file="/include/jsp_headers.jsp"%>
	<%@ include file="/include/js_css_base_include.jsp"%>	
	<link href="css/business/leftNavBar.css" rel="stylesheet" type="text/css"/>
 </HEAD>
<BODY style="height: 100%;  overflow: hidden">
<script src="./js/uijs/business/leftNavBar.nanoscroller.js" type="text/javascript"></script>
<script src="./js/uijs/business/leftNavBar.js" type="text/javascript"></script>
<div class="sitemap">
    <ul style="float: left">
        <li>当前位置：</li>
        <li><a href="#">业务办理<span class="spanColor"></span></a></li>
    </ul>
    <div style="clear: both"></div>
</div>
<!--左导航-->
<div class="mainScrollBar" id="box">
<div class="left_NavBar left-nano" id="top"><!--left-nano-->
        <div class="left-nano-content"><!--left-nano-content-->
            
            
            
     <div class="left_nav_responsive_id left_nav_responsive_big  " id="left_nav">
    <div class="LeftNav_top_responsive_id LeftNav_top_responsive_big">
        <div class="LeftNav_top_responsive_div1_id LeftNav_top_responsive_div1_big">
            <ul class="tabPanel">
                <li class="tabpane_huanjie_2 dianji extended_menus" title="展开菜单">
                    <img src="img/icon/zhankai_03.png" class="shouqi04" alt=""/>
                    <div class="prompt_box_info prompt_box_info_4">
                        <span>展开菜单</span>
                        <div class="prompt_box_nav_4 prompt_box_nav_border"></div>
                        <div class="prompt_box_nav_4 prompt_box_nav_background"></div>
                    </div>
                </li>
                <div  class="clearFloat"></div><!--<!--清除浮动-->
            </ul>
        </div>
        <div class="LeftNav_top_responsive_div2_id LeftNav_top_responsive_div2_big">
            <input type="text" class="left_nav_topV1" id="unitName"/>
            <button class="left_nav_topV2">单位查询</button>
            <ul class="tabPanel">
                <li class="tabpane_huanjie dianji pack_up_menus">
                    <img src="img/icon/shouqi_03.png" class="shouqi04" alt=""/>
                    <div class="prompt_box_info prompt_box_info_3">
                        <span>收起菜单</span>
                        <div class="prompt_box_nav prompt_box_nav_border"></div>
                        <div class="prompt_box_nav prompt_box_nav_background"></div>
                    </div>
                </li>
                <div  class="clearFloat"></div><!--<!--清除浮动-->
            </ul>
            <div class="clearFloat"></div><!--<!--清除浮动-->
        </div>
    </div>
    <div class="left_nav_MIV2">
        <div class="left_nav_topV2-01">
            <select class="select_gd" id="select_gd_area" onchange="selectUnit()">
                <option value="">行政区域</option>
                <c:forEach items="${areaList}" var="dicItem">
                    <option value="${dicItem.dicItemCode}">${dicItem.viewName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="left_nav_topV2-02">
            <select class="select_gd" id="select_gd_sys" onchange="selectUnit()">
                <option value="">系统类别</option>
                <c:forEach items="${systemTypeList}" var="dicItem">
                    <option value="${dicItem.dicItemCode}">${dicItem.viewName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="left_nav_topV2-03">
            <select class="select_gd" id="select_gd_kind" onchange="selectUnit()">
                <option value="">单位性质</option>
                <c:forEach items="${unitKindList}" var="dicItem">
                    <option value="${dicItem.dicItemCode}">${dicItem.viewName}</option>
                </c:forEach>
            </select>
        </div>
        <h3 class="clearF"></h3><!--清除浮动-->
    </div>
    
    </div>
            
            
            
            
            
            
            <div class="leftNavBarPackUp">
                <img src="img/icon/zhankai_03.png" alt="">
            </div>
            <!--滚动条优化-->
            <div class="scrollBoxWrap" id="scrollBoxWrap">
            <!--展开，收缩-->
                <div class="leftNavPans scrollcontentBox">
                <!--展开的一级UL菜单-->
				   <ul id="firstUl" class="PansFirstUl">
				     </ul> 
				     
                          
                </div>
            </div>
        </div>
    </div>
<!--右边整体-->
<div class="right_content right_contentFloat" style="background:#f0f5fb;">
<div id="rightpage">
</div>
</div>
</div>
<div style="clear: both"></div><!--清除浮动-->
<!--主体内容结束-->
<!--脚部-->
<script>
function selectUnit(){
	expandNode(null,'1'); 
}
$(document).ready(function(){
	selectUnit();
	$('.left_nav_topV2').click(function(){
		selectUnit();
	})
})
function expandNode(unitOid,flag){
	var objS_area = document.getElementById("select_gd_area");
    var objS_sys = document.getElementById("select_gd_sys");
    var objS_kind = document.getElementById("select_gd_kind");
    var unitName = $("#unitName").val();
	var unitAreaCode = objS_area.options[objS_area.selectedIndex].value;
	var unitKind = objS_kind.options[objS_kind.selectedIndex].value;
	var unitCategory = objS_sys.options[objS_sys.selectedIndex].value;
	var params = {};
	params.unitName=unitName;
	params.unitAreaCode=unitAreaCode;
	params.unitKind=unitKind;
	params.unitCategory=unitCategory;
	params.unitOid = unitOid;
	$.ajax({
		url : 'findUnit.do?method=findUnitList',
		data :params,
		dataType : 'json',
		type:'POST',
		 beforeSend:function(XMLHttpRequest){ 
	    	Mask.showMask();
        },
		complete:function(XMLHttpRequest,textStatus){ 
	    	Mask.hideMask();
         }, 
		async : true,
		success : function(data) {
				if(flag=="1"){
					$("#firstUl li").remove();
					$.each(data,function(index,item){
						var firstLi='';
						if(item.hasPower){
							firstLi+="<li class='PansFirstLi'><div class='PansFirstLi_div1'><p class='PansFirstLi_p1'> <b class='PansFirstLi_span1'> <i></i> </b> <span class='NavBar_FirstLiImg NavBar_FirstLiImgJia'><i></i></span>";
							firstLi+="<a href='javascript:void(0);' title='"+item.unitName+"' onclick=workLocation('"+item.unitOid+"') class='PansFirstA_title'>"+item.unitName+"</a>";
						}else{
							firstLi+="<li class='PansFirstLi'><div class='PansFirstLi_div1' style='background-color:#eef1f4'><p class='PansFirstLi_p1'> <b class='PansFirstLi_span1'> <i></i> </b> <span class='NavBar_FirstLiImg NavBar_FirstLiImgJia'><i></i></span>";
							firstLi+="<a href='javascript:void(0);' title='"+item.unitName+"' style='backgroud'  class='PansFirstA_title'>"+item.unitName+"</a>";
						}
						
						firstLi+="<div class='clear'></div></p></div>";
						firstLi+="<ul style='display:none' class='PansSecondUl' id='SecondUl"+item.unitOid+"'></ul></li>";
						firstLi = $(firstLi);
						$(firstLi).find('.NavBar_FirstLiImg').click(function(){
							expandNode(item.unitOid,'2');
							if($(this).parent().parent().siblings('.PansSecondUl').is(":hidden")){
						        $('.PansSecondUl').hide();
						        $('.NavBar_FirstLiImg').addClass('NavBar_FirstLiImgJia');
						        $('.NavBar_FirstLiImg').removeClass('NavBar_FirstLiImgJian');
						        $('.NavBar_FirstLiImg').parents('p').parents('div').siblings('ul').hide();
						        $('.NavBar_FirstLiImg').siblings('b').css('background','#2fabff');
						        $('.NavBar_FirstLiImg').parents('p').css('background','#fff');
						        $('.NavBar_FirstLiImg').parents('p').css('width','100%');
						        $('.NavBar_FirstLiImg').children('i').removeClass('PansFirstLi_active');
						        $(this).removeClass('NavBar_FirstLiImgJia');
						        $(this).addClass('NavBar_FirstLiImgJian');
						        $(this).parents('p').parents('div').siblings('ul').show(1).children('li');
						        $(this).siblings('b').css('background','#1388d5');
						        /* $(this).parents('p').css('background','#eef1f4'); */
						        $(this).parents('p').css('width','100%');
						        $(this).children('i').addClass('PansFirstLi_active');
						    }else{
						        $(this).addClass('NavBar_FirstLiImgJia');
						        $(this).removeClass('NavBar_FirstLiImgJian');
						        $(this).parents('p').parents('div').siblings('ul').hide();
						        $(this).siblings('b').css('background','#2fabff');
						        $(this).parents('p').css('background','#fff');
						        $(this).parents('p').css('width','100%');
						        $(this).children('i').removeClass('PansFirstLi_active');
						    }
						})
					    firstLi.appendTo($("#firstUl"));
					})
				}else if(flag=="2"){
					if($("#SecondUl"+unitOid).find("li").length>0){
						return;
					}else{
						$.each(data,function(index,item)
						{
							var SecondLi="";
							if(!item.isLeaf)
							{
								if(item.hasPower){
									SecondLi+="<li><div class='PansSecondLi_div1'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansSecondLi_greenOffice' > <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansSecondLi_greenOffice'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansSecondLi_greenOffice'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}else{
									SecondLi+="<li><div class='PansSecondLi_div1' style='background-color:#eef1f4'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansSecondLi_greenOffice' > <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansSecondLi_greenOffice'> <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansSecondLi_greenOffice'> <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}
								
							}else{
								if(item.hasPower){
									SecondLi+="<li><div class='PansSecondLi_div1'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}else{
									SecondLi+="<li><div class='PansSecondLi_div1' style='background-color:#eef1f4'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''> </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										 SecondLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansSecondLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansSecondA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}
								
							}
							SecondLi+="<ul style='display: none' class='PansThirdUl' id='TrirdUl"+item.unitOid+"'></ul></li>";   
							SecondLi=$(SecondLi);  
							$(SecondLi).find('.NavBar_SecondLiImg').click(function(){
								expandNode(item.unitOid,'3');
								if($(this).parent().parent().siblings('.PansThirdUl').is(":hidden")){
							          $('.NavBar_SecondLiImg').parents('p').parents('div').siblings('ul').hide();
							          $('.NavBar_SecondLiImg').children('b').removeClass('NavBar_SecondLiImgJiaB');
							          $('.NavBar_SecondLiImg').addClass('NavBar_SecondLiImgJia');
							          $('.NavBar_SecondLiImg').removeClass('NavBar_SecondLiImgJian');
							          $(this).parents('p').parents('div').siblings('ul').show().children('li');
							          $(this).children('b').addClass('NavBar_SecondLiImgJiaB');
							          $(this).removeClass('NavBar_SecondLiImgJia');
							          $(this).addClass('NavBar_SecondLiImgJian');
							      }else{
							          $(this).parents('p').parents('div').siblings('ul').hide();
							          $(this).children('b').removeClass('NavBar_SecondLiImgJiaB');
							          $(this).addClass('NavBar_SecondLiImgJia');
							          $(this).removeClass('NavBar_SecondLiImgJian');
							      }
							}) 
							SecondLi.appendTo($("#SecondUl"+unitOid));
						})
					}
				}else if(flag=="3"){
					if($("#TrirdUl"+unitOid).find("li").length>0){
						return;
					}else{
						$.each(data,function(index,item){
							var TrirdLi="";
							if(!item.isLeaf)
							{
								if(item.hasPower){
									TrirdLi+="<li><div class='PansThirdLi_div1'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
									    TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}else{
									TrirdLi+="<li><div class='PansThirdLi_div1' style='background-color:#eef1f4'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
									    TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansThirdLi_greenOffice'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}
							}else{
								if(item.hasPower){
									TrirdLi+="<li><div class='PansThirdLi_div1'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansThirdLi_greenOffice'  style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansThirdLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansThirdLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' onclick=workLocation('"+item.unitOid+"') class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}else{
									TrirdLi+="<li><div class='PansThirdLi_div1' style='background-color:#eef1f4'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
									if(item.unitKind='104'){
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansThirdLi_blueThing.png' class='PansThirdLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else if(item.unitKind='103'){
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansFourthLi_bluejoin.png' class='PansThirdLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}else{
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' alt=''></i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
										TrirdLi+="<img src='img/leftNavBar/PansSecondLi_greenOffice.png' class='PansThirdLi_greenOffice' style='margin-left:15px;'> <a href='javascript:void(0);' class='PansThirdA_title'>"+item.unitName+"</a> <div class='clear'></div></p></div>";
									}
								}
							}
							TrirdLi+="<ul style='display: none' class='PansThirdUl' id='TrirdUl"+item.unitOid+"'></ul></li>";   
							TrirdLi=$(TrirdLi);  
							$(TrirdLi).find('.NavBar_SecondLiImg').click(function(){
								if($(this).parent().parent().siblings('.PansFourthUl').is(":hidden")){
						            $('.NavBar_ThirdLiImg').parents('p').parents('div').siblings('ul').hide();
						            $('.NavBar_ThirdLiImg').children('b').removeClass('NavBar_ThirdLiImgJiaB');
						            $('.NavBar_ThirdLiImg').addClass('NavBar_ThirdLiImgJia');
						            $('.NavBar_ThirdLiImg').removeClass('NavBar_ThirdLiImgJian');

						            $(this).parents('p').parents('div').siblings('ul').show().children('li');
						            $(this).children('b').addClass('NavBar_ThirdLiImgJiaB');

						            $(this).removeClass('NavBar_ThirdLiImgJia');
						            $(this).addClass('NavBar_ThirdLiImgJian');
						        }else{
						            $(this).parents('p').parents('div').siblings('ul').hide();
						            $(this).children('b').removeClass('NavBar_ThirdLiImgJiaB');

						            $(this).addClass('NavBar_ThirdLiImgJia');
						            $(this).removeClass('NavBar_ThirdLiImgJian');
						        }
							}) 
							TrirdLi.appendTo($("#TrirdUl"+unitOid));
						})
					}
		  }
		}
	});
}
function workLocation(unitOid)
{
	$("#rightpage").load("goUpdateUnitRight.do?method=goToViewUnit&unitOid="+unitOid);
}
$(window).resize(function(){
	$('.query_criteria').height(
		$(window).height() 
		- $('.sitemap').outerHeight(true)
		- $('.nav_tab').outerHeight(true)
		);
}).resize();


//屏幕高度自适应方法
/* jQuery.extend({
    aoutHeight:function(){
        var windowHeight=$(window).height();
        $('.left_nav').height(windowHeight-102);
        $('.right_content').height(windowHeight-102);
        $('.Tab_message').height(windowHeight- $('#nav_tab').height()- $('.sitemap').height()-$('.head-v3').height());
    }
}); */
//滚动条优化
</script>
</body>
</html>
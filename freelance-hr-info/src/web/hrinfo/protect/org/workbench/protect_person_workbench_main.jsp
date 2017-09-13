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
        <span>信息管理 > </span>
        <span>信息维护 > </span>
        <span>人员信息维护 </span>
    <div style="clear: both"></div>
</div>
<div class="main-container" >
  <div class="left-content" >
	
	 <div class="nano left-nav-scroll">
	 <div class="leftnav-fold"><a class="leftnav-flod-icon"></a>科室列表</div>
	 <div class="nano-content">
				   <ul id="firstUl" class="left-nav">
				   </ul> 
                </div>
            </div>
        </div>
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
	$.ajax({
		url : 'findOrg.do?method=findOrgList',
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
				$("#firstUl li").remove();
				$.each(data,function(index,item){
					var firstLi='';
					firstLi+="<li class='ln-firstli'>";
					firstLi+="<span class='ln-plus-icon'></span><a class='ln-firstli-a' href='javascript:void(0);' title='"+item.orgName+"' onclick=workUnitLocation('"+item.organizationOid+"')>"+item.orgName+"</a>";
					firstLi+="<ul class='ln-second-nav' id='SecondUl"+item.organizationOid+"'></ul></li>";
					firstLi = $(firstLi);
				    firstLi.appendTo($("#firstUl"));
				    if(item.children){
				    	$.each(item.children,function(index1,item1){
							var SecondLi="";
							if(!item1.leaf)
							{
								 SecondLi+="<li class='ln-secondli'><span class='ln-line'></span>";
								 if(index1==item.children.length-1){
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a>";
								 }
							}else{
								 SecondLi+="<li class='ln-secondli'>";
								 if(index1==item.children.length-1){
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a>";
								 }
							}
							SecondLi+="<ul class='ln-third-nav' id='TrirdUl"+item1.organizationOid+"'></ul></li>";   
							SecondLi=$(SecondLi);  
							SecondLi.appendTo($("#SecondUl"+item.organizationOid));
							if(item1.children){
								$.each(item1.children,function(index2,item2){
									var TrirdLi="";
									if(!item2.leaf)
									{
										 TrirdLi+="<li class='ln-thirdli'><span class='ln-line'></span>";
										 if(index2==item1.children.length-1){
											 TrirdLi+=" <span class='ln-plus-icon'></span><a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }else{
											 TrirdLi+=" <span class='ln-plus-icon'></span><a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }
									}else{
										TrirdLi+="<li class='ln-thirdli'><span class='ln-line'></span>";
										 if(index2==item1.children.length-1){
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }else{
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }
									}
									TrirdLi+="<ul class='ln-fourth-nav' id='ForthUl"+item2.organizationOid+"'></ul></li>";   
									TrirdLi=$(TrirdLi);  
									TrirdLi.appendTo($("#TrirdUl"+item1.organizationOid));
									if(!item2.leaf){
										$.each(item2.children,function(index3,item3){
											var ForthLi="";
											if(!item3.leaf)
											{
												 ForthLi+="<li class='ln-fourthli'><span class='ln-line'></span>";
												 if(index3==item2.children.length-1){
													 ForthLi+=" <span class='ln-plus-icon'><a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }else{
													 ForthLi+=" <span class='ln-plus-icon'><a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }
											}else{
												ForthLi+="<li class='ln-fourthli'><span class='ln-line'></span>";
												 if(index3==item2.children.length-1){
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }else{
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }
											}
											ForthLi+="</li>";   
											ForthLi=$(ForthLi);  
											ForthLi.appendTo($("#ForthUl"+item2.organizationOid));
										})
									}
					
								})
							}
				
						})
					}
				})
				$('.left-nav').find('ul').show();
		        $('.left-nav').find('.ln-plus-icon').attr('class','ln-minus-icon');
		        leftNavScrollHide();
		     // 点击左导航"+"号展开子菜单
		        $(document).on('click','.ln-plus-icon',function (e) {
		            e.stopPropagation();//防止冒泡
		            $(this).attr('class','ln-minus-icon');  //"+"号变"-"
		            $(this).siblings('ul').show();

		            leftNavScrollHide();
		        });
		        // 点击左导航"-"号收缩子菜单
		        $(document).on('click','.ln-minus-icon',function (e) {
		            e.stopPropagation();//防止冒泡
		            $(this).attr('class','ln-plus-icon');  //"-"号变"+"
		            $(this).siblings('ul').hide();

		            leftNavScrollHide();
		        });
		        //点击左导航中li选中状态显示
		        $('.left-nav li a').click(function (e) {
		            e.stopPropagation();//防止冒泡
		            // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
		            $('.left-nav').find('a').removeClass('left-nav-active');
		            $(this).addClass('left-nav-active');
		        });
		    //默认显示右侧工作台  
			$(".right-content").load("goToPersonProtectWorkBench.do?method=goTopersonWorkBench");
		}
	});
}
$(window).resize(function(){
	$('.nano.left-nav-scroll').height(
		$(window).height() 
		- $('.sitemap').outerHeight(true) - $('.current-position').outerHeight(true)
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
	$(".right-content").load("goToPersonProtectWorkBench.do?method=goTopersonWorkBench&orgOid="+orgOid);
}
function workUnitLocation(organizationOid)
{
	$(".right-content").load("goToPersonProtectWorkBench.do?method=goTopersonWorkBench&organizationOid="+organizationOid);
}
</script>
</body>
</html>
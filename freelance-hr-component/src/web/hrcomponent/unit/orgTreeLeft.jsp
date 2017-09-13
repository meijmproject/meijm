<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
   	<%@ include file="/include/jsp_headers.jsp"%>
	<%@ include file="/include/js_css_base_include.jsp"%>	
	<link href="css/szetyy/leftNav.css" rel="stylesheet" type="text/css"/>
 </HEAD>
<BODY style="height: 100%;  overflow: hidden;    position: fixed;" >
<div class="sitemap">
    <ul style="float: left">
        <li>当前位置：</li>
        <li>查询统计<span class="spanColor"> > </span></li>
			<li>基础信息查询<span class="spanColor"> > </span></li>
			<li>单位信息查询</li>
    </ul>
    <div style="clear: both"></div>
</div>
<div class="main-container" >
  <div class="left-content" >
	<div class="leftnav-fold"><a class="leftnav-flod-icon"></a>科室列表</div>
	 <div class="nano left-nav-scroll">
	 <div class="nano-content">
				   <ul id="firstUl" class="left-nav">
				   </ul> 
                </div>
            </div>
        </div>
<div class="right_content">
</div>
</div>
<div style="clear: both"></div>
<script>
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
					firstLi+="<a class='ln-firstli-a' href='javascript:void(0);' title='"+item.orgName+"' onclick=workLocation('"+item.organizationOid+"')><span class='ln-plus-icon'></span>"+item.orgName+"</a>";
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
									 SecondLi+=" <a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') ><span class='ln-plus-icon'></span>"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') ><span class='ln-plus-icon'></span>"+item1.orgName+"</a>";
								 }
							}else{
								 SecondLi+="<li class='ln-secondli'>";
								 if(index1==item.children.length-1){
									 SecondLi+=" <a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') ><span class='ln-plus-icon'></span>"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') ><span class='ln-plus-icon'></span>"+item1.orgName+"</a>";
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
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')><span class='ln-plus-icon'></span>"+item2.orgName+"</a>";
										 }else{
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')><span class='ln-plus-icon'></span>"+item2.orgName+"</a>";
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
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') ><span class='ln-plus-icon'></span>"+item3.orgName+"</a>";
												 }else{
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') ><span class='ln-plus-icon'></span>"+item3.orgName+"</a>";
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
		      //点击左导航中li展开收缩子菜单
		        $('.left-nav li').click(function (e) {
		            e.stopPropagation();//防止冒泡
		            // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
		            $('.left-nav').find('a').removeClass('left-nav-active');
		            $(this).children('a').addClass('left-nav-active');

		            if ($(this).children('ul').is(":hidden")){
		                // 子菜单显示时icon标签由“加号”变“减号”
		                $(this).children('a').children('span').attr('class','ln-minus-icon');
		                $(this).children('ul').show();
		            }else  if ($(this).children('ul').is(":visible")){
		                //隐藏点击li下所有子导航，“减号”变“加号”
		                $(this).find('a').children('span').attr('class','ln-plus-icon');
		                $(this).find('ul').hide();
		                //收缩子菜单时点击选中的样式去掉
		                $(this).children('a').removeClass('left-nav-active');
		            }
		            leftNavScrollHide();
		        });
		}
	});
}
function workLocation(unitOid,unitKind)
{
	$("#rightpage").load("goUnitRight.do?method=goToViewUnit&unitOid="+unitOid+"&unitKind="+unitKind);
}
$(window).resize(function(){
	$('.nano.left-nav-scroll').height(
		$(window).height() 
		- $('.sitemap').outerHeight(true)
		- $('.leftnav-fold').outerHeight(true)
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
        $('.left-content').css('width','300px');
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
</script>
</body>
</html>
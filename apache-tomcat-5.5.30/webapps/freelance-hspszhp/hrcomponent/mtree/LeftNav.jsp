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
<BODY style="background: #edf0f5;">
<div class="current-position">
    			当前位置：
        <span>业务办理 </span>
    <div style="clear: both"></div>
</div>
<div class="main-container" >
<div class='left-content'>
<!-- <div class="nano left-nav-scroll"> -->
<div class="tab-left-nav">
        <ul class="leftnav-fold">
            <a class="leftnav-flod-icon"></a>
            <li name="tab-left-nav1" class="leftnav-tab-selected" id="menuTypeTwo"><a  href="javascript:void(0)">环节办理</a></li>
            <li name="tab-left-nav2" id="menuTypeOne"><a href="javascript:void(0)" >事项办理</a></li>
        </ul>
        <div class="tab-div-cont">
            <div class="nano left-nav-scroll" name="tab-left-nav1" style="display: block;">
                <div class="nano-content" >
                    <ul class="left-nav" id="firstUl">
                    </ul>
                 </div>
            </div>
         </div>
</div>
</div>
<div class="right-content">
</div>
</div>
</BODY>
<script type="text/javascript">
$(document).ready(function(){
	reSetMainContainerHeight();
	$(".right-content").load('loadRightNav.do?method=goBusinessWorktop');
    $('#menuTypeOne').click(function(){
    	 expandNode("","1",'1');
    	 CookiesDataRegister.set("menuTypeIndex",'1',30);//设置会话有效期为30天
    })
    $('#menuTypeTwo').click(function(){
    	 expandNode("","1",'2');
    	 CookiesDataRegister.set("menuTypeIndex",'2',30);//设置会话有效期为30天
    })
    var menuTypeIndex=CookiesDataRegister.get("menuTypeIndex");
    if(menuTypeIndex!=null){
    	 expandNode("","1",menuTypeIndex);
    	 if(menuTypeIndex=='1'){
    		 $('#menuTypeOne').removeClass().addClass("leftnav-tab-selected");
    		 $('#menuTypeTwo').removeClass()
         }else if (menuTypeIndex=='2'){
        	 $('#menuTypeTwo').removeClass().addClass("leftnav-tab-selected");
    		 $('#menuTypeOne').removeClass()
         }
    }else{
    	expandNode("","1","1");
   }
});
function expandNode(menuCode,flag,menuType){
	$.ajax({
		url : 'findJhdMtMenu.do?method=findJhdMtMenu',
		data : {menuCode:menuCode,menuType:menuType},
		dataType : 'json',
		type:'POST',
		async : true,
		 beforeSend:function(XMLHttpRequest){ 
		    	Mask.showMask();
	        },
		complete:function(XMLHttpRequest,textStatus){ 
	    	Mask.hideMask();
         }, 
		success : function(data) {
			var menuList=data.list;
				if(flag=="1"){
					$("#firstUl li").remove();
					$.each(menuList,function(index,item){
						var firstLi="<li class='ln-firstli'><span class='ln-plus-icon'></span><a href='javascript:void(0);' title='"+item.menuTitle+"'   class='ln-firstli-a'>"+item.menuTitle+"</a> <span id='"+item.menuCode+"' class='tips-num'>"+item.count+"</span>";
						firstLi+="<ul class='ln-second-nav' id='SecondUl"+item.menuCode+"'></ul></li>";
						firstLi = $(firstLi);
						expandNode(item.menuCode,'2',menuType);
						firstLi.find('a').click(function(e){
						 	e.stopPropagation();//防止冒泡
						 	if($(this).siblings('span').not('.tips-num').attr('class')=='ln-minus-icon'){
						 		 $(this).siblings('span').not('.tips-num').attr('class','ln-plus-icon');  //"+"号变"-"
						         $(this).siblings('ul').hide();
							}else{
								
								$(this).siblings('span').not('.tips-num').attr('class','ln-minus-icon');  //"+"号变"-"
						        $(this).siblings('ul').show();
							}
					       
				        })
				       
						firstLi.appendTo($("#firstUl"));
						 if(item.count==0){
							  $('#'+item.menuCode).hide();  
							}
						$('.left-nav li a').click(function (e) {
						    e.stopPropagation();//防止冒泡
						    // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
						    $('.left-nav').find('a').removeClass('left-nav-active');
						    $(this).addClass('left-nav-active');
						});
					})
				}else if(flag=="2"){
					if($("#SecondUl"+menuCode).find("li").length>0){
						return;
					}else{
						$.each(menuList,function(index,item)
						{
							var SecondLi="<li class='ln-secondli'><span class='ln-line'></span><span></span><a href='javascript:void(0);' title='"+item.menuTitle+"' onclick=workLocation('"+item.location+"','"+item.menuCode+"') class='ln-secondli-a'>"+item.menuTitle+"</a><span id='"+item.menuCode+"' class='tips-num'>"+item.count+"</span></li>";
							$(SecondLi).appendTo($("#SecondUl"+menuCode));
							if(item.count==0){
								  $('#'+item.menuCode).hide();  
							}
						})
						$('.left-nav li a').click(function (e) {
						    e.stopPropagation();//防止冒泡
						    // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
						    $('.left-nav').find('a').removeClass('left-nav-active');
						    $(this).addClass('left-nav-active');
						});
					}
				}
		}
	});
}

/* $(window).resize(function(){
	$('.left-content').height(
		$(window).height() - $('.sitemap').outerHeight(true) 
		);
}).resize(); */

function reSetMainContainerHeight() {
    var windowHeight = $(window).height();
    $('.main-container').height(windowHeight - $('.current-position').outerHeight(true)- 2);
}
/**
 * 点击子节点，触发事件，显示右边工作台
 */
function workLocation(doUrl,menuCode)
{
	$(".right-content").load(doUrl+"&menuCode="+menuCode);
}
$(function () {
    $(window).load();
    //左导航全部展开
    function leftNavShow() {
        $('.left-nav').find('ul').show();
        $('.left-nav').find('.ln-plus-icon').attr('class','ln-minus-icon');
    }
    leftNavShow();

    //点击左导航收缩展开按钮
    $('.leftnav-flod-icon').click(function () {
        // 展开
        if ($(this).hasClass('leftnav-unflod-icon')){
            $(this).removeClass('leftnav-unflod-icon');
            $(this).parent().attr('class','leftnav-fold');
            $(this).parent().siblings('div').show();
            $('.left-content').css('width','257px');
        }else {
            /*收缩*/
            $(this).addClass('leftnav-unflod-icon');
            $(this).parent().siblings('div').hide();
            $(this).parent().attr('class','leftnav-unflod');
            $('.left-content').css('width','45px');
        }
    });

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


 // 滚动条优化
    $(".nano.left-nav-scroll").nanoScroller();
    $(".nano.left-nav-scroll").nanoScroller({ stop: false });
    //重新计算滚条的高度
    $(".nano.left-nav-scroll").nanoScroller({ sliderMaxHeight: 300 });
    $(".nano.left-nav-scroll").nanoScroller({ sliderMinHeight: 80 });
    $(".nano.left-nav-scroll").nanoScroller({ flashDelay: 1000 });

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


    //点击事项环节树tab切换
     $(document).on('click','.tab-left-nav .leftnav-fold li',function () {
        var tabLeftName = $(this).attr("name");
       $(this).siblings('li').removeClass("leftnav-tab-selected");
       $(this).addClass("leftnav-tab-selected");
        $(".nano.left-nav-scroll").nanoScroller();
    }); 

});

</script>
</HTML>
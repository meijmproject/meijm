<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<html>
<head>
<title></title>
<link rel="stylesheet" href="hspszhphtml/css/components/left_nav.css">
<link rel="stylesheet" href="hspszhphtml/css/components/left_nav_nanoscroller.css">
<link rel="stylesheet" href="hspszhphtml/css/layout/statistica_tables_show.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/common/modalstyle.css">
<style>
.tabs-titles {
	overflow-x:hidden;
	overflow-y:hidden;
	height:40px;
	width: calc(100% - 70px);
	float:left;
	background-color:#f5f5f5;
}
.top-group, .bottom-group {
	float:left;
	width:35px;
	height:40px;
	cursor:pointer;
}
.top-group {
	background: url(hspszhphtml/images/common/top-fold.png)no-repeat center transparent;
}
.bottom-group {
	background: url(hspszhphtml/images/common/bottom-fold.png)no-repeat center transparent;
}
.top-group:hover, .bottom-group:hover {
	background-color: #eaeaea;
    border-radius: 2px;
}
.excels-tab li {
	float: left;
}
</style>
</head>
<body class="b-overflow-hidden">
<div class="current-position">
	当前位置： <span>查询统计 > </span> <span>固定查询统计 > </span> <span>常用查询统计 </span>
	<div style="clear: both"></div>
</div>
<div class="main-container statistica-excels">
	<div class="left-content">
		<div class="nano left-nav-scroll">
			<div class="leftnav-fold"><a class="leftnav-flod-icon"></a>列表</div>
			<div class="nano-content" style="margin-right: 0;">
				<ul class="left-nav">

				</ul>
			</div>
		</div>
	</div>

	<div class="right-content right-excels-show clearfix" style="background-color:" id="tabs">
		<div class="tabs-header" style="background-color:#f5f5f5;border-bottom: 1px solid #e3e3e3;">
			<div class="tabs-titles">
				<ul class="excels-tab ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" style="width:100%;height:auto;" role="tablist">
				</ul>
			</div>
			<div style="float:left;background-color:#f5f5f5;">
				<div class="top-group" onclick="gotoPrev()"></div>
				<div class="bottom-group" onclick="gotoNext()"></div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
  renderLeftNav();
	//eachAreaHeight();
	resizeMainHeight();
});
//当前tab页
var tabIndex = 1;
function gotoPrev() {
	if(tabIndex>1) {
		var ulTabHeight = $('.excels-tab').outerHeight(true);
		var pages = parseInt((ulTabHeight+1)/40);
		$('.tabs-titles').animate({scrollTop: -40*(tabIndex-1)}, 200);
		tabIndex--;
	}
}
function gotoNext() {
	var ulTabHeight = $('.excels-tab').outerHeight(true);
	var pages = parseInt((ulTabHeight+1)/40);
	var currentTop = $('.excels-tab').offset().top-42;//当前距离父元素上部的位置
	if(pages>1&&ulTabHeight+currentTop>40) {
		$('.tabs-titles').animate({scrollTop: 40*tabIndex}, 200);
		tabIndex++;
	}
}
$(window).resize(function() {
	resizeMainHeight();
});
function resizeMainHeight() {
  var windowHeight = $('body.b-overflow-hidden').height();

  //主内容区高度
  $('.main-container').height(windowHeight -$('.current-position').outerHeight(true));
}

function showReportPage(el,id) {
	$(el).addClass('tab-selected');
	$(el).parent('li').siblings().find('a').removeClass('tab-selected');
	$('#'+id).siblings('iframe').hide();
	$('#'+id).show();
}
function gotoReportPage(el, id, name, location) {
	if($('#report'+id).length) {
	  $('#report-controller'+id).click();
	  $div = $('#report'+id);
	  $div.attr({src: location});
	}else {
		var $li = $('<li></li>'),
	      $a = $('<a id="report-controller'+id+'" onclick="showReportPage(this,\'report'+id+'\')">'+name+'</a>'),
	      $close = $('<i class="tab-li-close"></i>'),
	      $div = $('<iframe frameborder="0" id="report'+id+'" style="width:100%;" class="excel-content"></iframe>');
    $a.append($close);
    $li.append($a);
    	$div.attr({src: location});
		$('.excels-tab').append($li);
		$('#tabs').append($div);
		$a.click();
		$close.click(function() {
		  $li.remove();
		  $div.remove();
		  $('.excels-tab li:first a').click();
		});
	}
}
function renderLeftNav() {
	$.ajax({
    url: 'findReportsTree.do?method=findReportsTree',
    data: {statType:'3'},
    dataType: 'json',
    type: 'POST',
    beforeSend: function(XMLHttpRequest){ 
      Mask.showMask();
    },
    complete: function(XMLHttpRequest,textStatus){ 
      Mask.hideMask();
    }, 
    async: true,
    success: function(data) {
      data.forEach(function(first, i) {
        var firstObj = first.entry,
            children = first.children; 
        var $li = $('<div>');
       
        if(children&&children.length>0) {
        	 $li.append('<li class="ln-firstli"></li>');
        	 $li.append('<span class="ln-plus-icon"></span><a class="ln-firstli-a" href="'+firstObj.action+'">'+firstObj.name+'</a>');
          $ul = $('<ul class="ln-second-nav"></ul>');
        	children.forEach(function(second, j) {
        	  var secondObj = second.entry;
        	  $ul.append('<li class="ln-secondli"><span class="ln-line"></span><a class="ln-secondli-a" href="javascript:void(0)" onclick="gotoReportPage(this, \''+secondObj.id+'\',\''+secondObj.name+'\',\''+secondObj.location+'\')">'+secondObj.name+'</a></li>');
          });
          $li.append($ul);
        }
        $li.append('</div>');
        $('.left-nav').append($li);
      });
      function leftNavShow() {
        $('.left-nav').find('ul').show();
        $('.left-nav').find('.ln-plus-icon').attr('class','ln-minus-icon');
	    }
	    leftNavShow();
	
	    //点击左导航收缩展开按钮
	    $('.leftnav-flod-icon').click(function () {
	        //展开
	        if ($(this).hasClass('leftnav-unflod-icon')){
	            $(this).removeClass('leftnav-unflod-icon');
	            $(this).parent('div').attr('class','leftnav-fold');
	            $(this).parent('div').siblings('div').show();
	            $('.left-content').css('width','257px');
	        }else { /*收缩*/
	            $(this).addClass('leftnav-unflod-icon');
	            $(this).parent('div').siblings('div').hide();
	            $(this).parent('div').attr('class','leftnav-unflod');
	            $('.left-content').css('width','45px');
	        }
	    })
	
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
	  }
	});
}
</script>
</body>
</html>
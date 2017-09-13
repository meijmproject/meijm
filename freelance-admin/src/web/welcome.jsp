<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>医院人事管理系统</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<script src="hspszhphtml/js/plugin/jadeforturepull_top_bottom.js"></script>
<script type="text/javascript">
var MESSAGE_LIMIT = 10;
$(function() {
    // 页面加载后各区域的高度设置
    function eachAreaHeight() {
        var windowHeight = $(window).height();

        //主内容区高度
        $('.main-container').height(windowHeight - $('.head-nav').outerHeight(true) -$('.current-position').outerHeight(true) - 2);
    }
    eachAreaHeight();

    $(window).resize(
        function () {
            eachAreaHeight();
        }
    );
    $.drag_top_bottom("index-main-id","index-top-id","index-bottom-id","index-line-id");
    $.drag_left_right("messageTab","messageList","messageContent","messageDragLine");
    $.drag_left_right("messageTabWarning","i-tab-left-warning","warningRight","warningDragLine");
    listMessageBoard(0, MESSAGE_LIMIT);
    listWarningBoard(0,10);
});

function listMessageBoard(start, limit) {
	$.get('listMessageBoard.do?method=list&show=true', {start:start,limit:limit}, function(data) {
		var messageList = data.rows,
	      total = data.total,
	      currentPage = (start/limit)+1,
        totalPage = (''+total/limit).indexOf('.')!=-1 ? parseInt(''+total/limit)+1 : parseInt(''+total/limit);
		$('#messageTotal').html(total);
		$('#messageTabController').empty();
		messageList.forEach(function(v, i) {
			var $textarea = $('<textarea id="textarea-'+v.messageOid+'" style="display:none">'+v.content+'</textarea>');
			$('#messageTabController').append($textarea);
			$('#messageTabController').append('<li>'
          +'<a href="#message'+v.messageOid+'" title="'+v.title+'&nbsp;&nbsp;'+v.publisher+'&nbsp;&nbsp;'+v.effDateStr+'" onclick="changeMessageContent(this,\''+v.title+'\',\'textarea-'+v.messageOid+'\')">'
          +'<span class="i-tab-title">'+v.title+'</span>'
          +'<span class="i-tab-time">'+v.effDateStr+'</span>'
          +'<span class="i-tab-author">'+v.publisher+'</span>'
          +'</a>'
      +'</li>');
		});
		$('#messageCurrentPage').val(currentPage);
		$('#messageTotalPage').val(totalPage);
		$('#pageNum').html(currentPage+'/'+totalPage);
		$('#messageTabController').find('li').eq(0).find('a').click();
	}, 'json');
}
function changeMessageContent(el, title, content) {
	content = $('#'+content).html();
	$(el).addClass('i-tab-selected');
	$(el).parent('li').siblings().find('a').removeClass('i-tab-selected');
	$('#messageContent').empty();
	$('#messageContent').html('<h1>'+title+'</h1>'+'<pre style="font:normal 100% \'microsoft yahei\',Verdana,Arial,Helvetica,sans-serif;white-space: pre-wrap;">'+content+'</pre>');
}
function prevMessageList() {
  var currentPage = $('#messageCurrentPage').val(),
      limit = MESSAGE_LIMIT;
  if(currentPage>1) {
    listMessageBoard((currentPage-2)*limit, limit);
  }
}
function nextMessageList() {
  var currentPage = $('#messageCurrentPage').val(),
      totalPage = $('#messageTotalPage').val(),
      limit = MESSAGE_LIMIT;
  if(currentPage<totalPage) {
    listMessageBoard(currentPage*limit, limit);
  }
}

function listWarningBoard(start, limit) {
	  var currentPage = (start/limit)+1;
	  $.get('listBizWarningInfo.do?method=listBizWarningInfo', {start:start,limit:limit}, function(data) {
	      var messageList = data.rows,
	      total = data.total,
	      realTotalPage = ''+total/limit,
	      totalPage = (realTotalPage).indexOf('.')!=-1 ? parseInt(realTotalPage)+1 : parseInt(realTotalPage);
          $('#messageTabWarning #pageNumWarning').html(currentPage+'/'+totalPage);
	      $('#messageTabWarning #currentPageWarning').val(currentPage);
	      $('#messageTabWarning #totalPageWarning').val(totalPage);
	      $('#messageTabWarning #limitWarning').val(limit);
	    $('#warningUl').empty();
	    messageList.forEach(function(v, i) {
	      $('#warningUl').append('<li>'
	          +'<a href="javascript:void(0);" onclick=goToWarningInfo(this,"'+v.itemCode+'") >'
	          +'<span class="i-tab-title">'+v.itemName
	          +'<span >'+v.count+'</span></span>'
	          +'<span class="i-tab-num warning-level-3">'+v.lowLevelCount+'</span>'
	          +'<span class="i-tab-num warning-level-2">'+v.middleLevelCount+'</span>'
	          +'<span class="i-tab-num warning-level-1">'+v.highLevelCount+'</span>'
	          +'</a>'
	      +'</li>');
	    });
	    if($('#warningUl li').length>0){
	    	$('#warningUl li:first a').click();
		}
	}, 'json');
}

function goToWarningInfo(el, itemCode){
	$(el).addClass('i-tab-selected');
  $(el).parent('li').siblings().find('a').removeClass('i-tab-selected');
	//$.get("goToBizWarningInfo.do?method=goToBizWarningInfo&itemCode="+itemCode,function(){},'json');
	$("#warningRight").load("goToBizWarningInfo.do?method=goToBizWarningInfo&itemCode="+itemCode);
}
</script>
</head>
<body class="main-body">
<div id="head-nav"></div>
<div class="current-position">当前位置 : <span>首页</span></div>
<div class="main-container">
	<%--<div class="left-content">--%>
		<%--<div class="img-text">--%>
			<%--<img class="it-img" src="./hspszhphtml/images/index/portrait.png"/>--%>
			<%--<span class="p-name">张医师</span>--%>
			<%--<span class="p-position">主任医师<span class="arrow-down"></span></span>--%>
		<%--</div>--%>
		<%--<ul class="p-nav">--%>
			<%--<li><a class="p-set" href="#">设置</a></li>--%>
			<%--<li><a class="p-menu" href="#">定制菜单</a></li>--%>
		<%--</ul>--%>
	<%--</div>--%>
	<%----%>
  <div class="right-content" id="index-main-id">
    
    <!-- <div class="imain-list il-notice" id="index-top-id">
      <div class="img-text">
        <img class="il-it-img" src="./hspszhphtml/images/index/notice.png"/>
        <span class="il-it-title">通知</span>
        <span class="il-it-num" id="messageNum"></span>
      </div>
    </div> -->
    <div class="imain-list il-notice" id="index-top-id">
        <div class="img-text">
            <img class="il-it-img" src="./hspszhphtml/images/index/notice.png"/>
            <span class="il-it-title">通知</span>
            <span class="il-it-num" id="messageTotal"></span>
        </div>
        <div class="index-tabs clearfix" id="messageTab">
            <div class="i-tab-left" id="messageList">
                <ul class="i-tab-ul" id="messageTabController">
                    
                </ul>
                <div class="i-tab-page" id="messagePager">
                  <input type="hidden" id="messageCurrentPage"/>
                  <input type="hidden" id="messageTotalPage"/>
                  <span class="page-num" id="pageNum">0/0</span>
                  <span class="page-handle" id="prevMessagePage" onclick="prevMessageList()"> < </span>
                  <span class="page-handle" id="nextMessagePage" onclick="nextMessageList()"> > </span>
                </div>
            </div>
            <div class="tabs-line" id="messageDragLine"></div>
            <div id="messageContent" class="i-tab-content">
            </div>
        </div>
    </div>
    
    <div class="index-line" id="index-line-id"><img src="./hspszhphtml/images/index/drag-up-down.png"/></div>
    
    <div class="imain-list il-warning" id="index-bottom-id">
	    <div class="img-text" id="img-warning-id">
	      <img class="il-it-img" src="./hspszhphtml/images/index/warning.png"/>
	      <span class="il-it-title">预警</span>
	    </div>
	    <div class="index-tabs clearfix" id="messageTabWarning">
	      <div class="i-tab-left" id="i-tab-left-warning">
	        <ul class="i-tab-ul" id="warningUl">
					</ul>
					<div class="i-tab-page" >
					<input type="hidden" id="currentPageWarning" >
					<input type="hidden" id="totalPageWarning">
					<input type="hidden" id="limitWarning">
					<span class="page-num" id="pageNumWarning"></span><span class="page-handle"> &lt; </span><span class="page-handle"> &gt; </span>
					</div>
			  </div>
			  <div class="tabs-line" id="warningDragLine"></div>
	      <div id="warningRight">
	      </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
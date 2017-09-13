<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head >
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>人员导入页面</title>

<script type="text/javascript">
</script>
</head>
<body>
<div class="current-position">
    当前位置：
    <span>基础信息管理 &gt; </span>
    <span>机构管理 &gt; </span>
    <span>人员信息导入 </span>
</div>
<div class="main-container file-import-wrap">
    <div class="person-info-import">
        <div class="file-import-process">
            <ol class="progress progress-6 clearfix">
                <li class="active">
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">1</div>
                        <div class="process-cont-text">导入</div>
                    </div>
                </li>
                <li class="active">
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">2</div>
                        <div class="process-cont-text">采集项映射</div>
                    </div>
                </li>
                <li class="active">
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">3</div>
                        <div class="process-cont-text">数据检查</div>
                    </div>
                </li>
                <li class="active">
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">4</div>
                        <div class="process-cont-text">数据校核</div>
                    </div>
                </li>
                <li class="active">
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">5</div>
                        <div class="process-cont-text">人员入库</div>
                    </div>
                </li>
                <li class="active">
                    <div class="process-cont">
                        <div class="process-cont-number">6</div>
                        <div class="process-cont-text">完成</div>
                    </div>
                </li>
            </ol>
        </div>
    </div>
     <div class="person-info-loading">
        <div class="person-check-cont">
            <h2>计划导入人员总数<span id ='rowDicNum'>0</span>人</h2>
            <h2 class="present-check-num">当前已入库<span id='exportPerson'>0</span>人</h2>
        </div>
    </div>
</div>
</body>
<script>
$(document).ready(function(){
	$(window).resize(
            function(){
            	eachAreaHeight()
            }
        );
	eachAreaHeight()
    // 页面加载后主内容区域的高度设置
    function eachAreaHeight() {
        var windowHeight = $(window).height();
        //主内容区高度
        $('.main-container').height(windowHeight - $('.current-position').outerHeight(true));
    }
	function checkDicDataNum(){
		 $.post('checkDicDataNum.do?method=countExportPerson',function(data){
			 	var rowDicNum = data.rowDicNum;
			 	var exportPerson= data.exportPerson;
				if(exportPerson!=''&& exportPerson!='undefined'){
					$('#exportPerson').text(exportPerson);
				}
				if(rowDicNum!=''&& rowDicNum!='undefined'){
					$('#rowDicNum').text(rowDicNum);
				}
				if(exportPerson==rowDicNum){
					clearInterval(aa);
				}
		},'json')
	}
	var aa = setInterval(checkDicDataNum,1000); 
	$.ajax({
		url : 'transferPersonInfo.do?method=transferPersonInfo',
		dataType : 'json',
		type:'POST',
		 beforeSend:function(XMLHttpRequest){ 
	    	Mask1.showMask();
        },
		complete:function(XMLHttpRequest,textStatus){ 
	    	Mask1.hideMask();
         }, 
		async : true,
		success : function(data) {
			if(data.success){
				MessageBox.alert('提示:',data.message,function(){
					location.href = "goVerPbPersonWorkbench.do?method=goVerPbPersonWorkbench";
				});
			}
			
		}
	})
})
</script>
</html>
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
                <li>
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">4</div>
                        <div class="process-cont-text">数据校核</div>
                    </div>
                </li>
                <li>
                    <div class="process-line"></div>
                    <div class="process-cont">
                        <div class="process-cont-number">5</div>
                        <div class="process-cont-text">人员入库</div>
                    </div>
                </li>
                <li>
                    <div class="process-cont">
                        <div class="process-cont-number">6</div>
                        <div class="process-cont-text">完成</div>
                    </div>
                </li>
            </ol>
        </div>
    </div>
    <input type="hidden" name="formFile"  id="formFile" value="${formFile}"/>
    <div class="person-info-loading">
        <div class="person-check-cont">
            <h2>计划导入人员总数<span id ='rowDicNum'>0</span>人</h2>
            <h2 class="present-check-num" id='message'></h2>
            <ul class="person-info-check">
               <li class="person-check-item fourth-level">
                    <div class="person-check-result">未通过</div>
                    <div class="person-check-num" id='checkDicNum'>0人</div>
                    <div class="person-check-name">字典项</div>
                </li>
                <li class="person-check-item fifth-level">
                    <div class="person-check-result ">未通过</div>
                    <div class="person-check-num" id='checkRelationNum'>0人</div>
                    <div class="person-check-name">关联性</div>
                </li>
                <li class="person-check-item sixth-level">
                    <div class="person-check-result">未通过</div>
                    <div class="person-check-num" id='checkCompleteNum'>0人</div>
                    <div class="person-check-name">完整性</div>
                </li>
            </ul>
            <div class="person-check-btns">
            		<button class="btn-search btn-default download" button-click="exportInformanation"><a>采集信息表下载</a></button>
	               	<button class="btn-search btn-default" button-click="reUploadPersonList">重新导入</button>
	                <button href="goToDicMapped.do?method=goToDicMapped" class="popdown btn-search btn-default" button-click="dicDataMapping">字典映射</button>
                	<button class="btn-search btn-default" button-click="transferPersonList">人员入库</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
$(document).ready(function(){
	$('.popdown').popdown({
		width : 1200
	});
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
    
	$('[button-click=reUploadPersonList]').hide();
	$('[button-click=exportInformanation]').hide();
	$('[button-click=dicDataMapping]').hide();
	$('[button-click=transferPersonList]').hide();
	function checkDicDataNum(){
		 $.post('checkDicDataNum.do?method=checkDicDataNum',function(data){
			 var rowDicNum = data.rowDicNum;
			 var message= data.message;
			 var checkDicNum = data.checkDicNum;
			 var checkRelationNum = data.checkRelationNum;
			 var checkCompleteNum = data.checkCompleteNum;
			    if(message!='' && message!='undefined'){
					$('#message').text(message);
				}
				if(checkDicNum!=''&& checkDicNum!='undefined'){
					$('#checkDicNum').text(checkDicNum+'人');
				}
				if(checkRelationNum!=''&& checkRelationNum!='undefined'){
					$('#checkRelationNum').text(checkRelationNum+'人');
				}
				if(checkCompleteNum!=''&& checkCompleteNum!='undefined'){
					$('#checkCompleteNum').text(checkCompleteNum+'人');
				}
				if(rowDicNum!=''&& rowDicNum!='undefined'){
					$('#rowDicNum').text(rowDicNum);
				}
		},'json')
	}
	function checkDicDataNum1(){
		 $.post('checkDicDataNum.do?method=checkDicDataNum',function(data){
			 var rowDicNum = data.rowDicNum;
			 var checkDicNum = data.checkDicNum;
			 var checkRelationNum = data.checkRelationNum;
			 var checkCompleteNum = data.checkCompleteNum;
				if(checkDicNum!=''&& checkDicNum!='undefined'){
					$('#checkDicNum').text(checkDicNum+'人');
				}
				if(checkRelationNum!=''&& checkRelationNum!='undefined'){
					$('#checkRelationNum').text(checkRelationNum+'人');
				}
				if(checkCompleteNum!=''&& checkCompleteNum!='undefined'){
					$('#checkCompleteNum').text(checkCompleteNum+'人');
				}
				if(rowDicNum!=''&& rowDicNum!='undefined'){
					$('#rowDicNum').text(rowDicNum);
				}
		},'json')
	}
	checkDicDataNum();
	var a1 = setInterval(checkDicDataNum,1000); 
		$.ajax({
			url : 'checkPersonDic.do?method=checkPersonDic',
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
				    $('#message').text('校核已完成');
			    	clearInterval(a1);
			    	var a2 = setInterval(checkDicDataNum1,1000); 
					if(data.success){
						if(data.message=='0'){
					    	clearInterval(a2);
							location.href ='goTocheckPersonDicRepeat.do?method=goTocheckPersonDicRepeat';
						}else{
							 $('[button-click=reUploadPersonList]').show();
							 $('[button-click=exportInformanation]').show();
							 $('[button-click=dicDataMapping]').show();
							MessageBox.confirm('提示','字典项检查未通过，请选择是否进入字典映射？', function(yes) {
								 if(yes=='yes'){
									 $('[button-click=dicDataMapping]').click();
								 }
							 })
						}
					}else{
						 MessageBox.alert('提示','检查失败！');
					}
				
			}
		})
})
$('[button-click=reUploadPersonList]').click(function(){
	location.href = "goToUploadPersonListPage.do?method=goToUploadPersonListPage&isNewImport=1";
})
$('[button-click=exportInformanation]').click(function(){
	window.location.href ='exportDicPerson.do?method=exportDicPerson';
})
$('[button-click=transferPersonList]').click(function(){
	$.post('transferPersonInfo.do?method=transferPersonInfo',function(data){
		if(data.success){
			MessageBox.alert('提示:',data.message,function(){
				window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
			});
		}
  },'json');
})
</script>
</html>
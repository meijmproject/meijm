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
    <input type="hidden" name="unitOid" id="unitOid" value="${unitOid}"/>
    <input type="hidden" name="formFile"  id="formFile" value="${formFile}"/>
    <div class="person-info-loading">
        <div class="person-check-cont">
            <h2>计划导入人员总数<span id ='rowDicNum'>${rowDicNum }</span>人</h2>
            <h2 class="present-check-num" id='message'>校核已完成</h2>
            <ul class="person-info-check">
                <li class="person-check-item fifth-level">
                    <div class="person-check-result ">未通过</div>
                    <div class="person-check-num" id='checkRelationNum'>${checkRelationNum }人</div>
                    <div class="person-check-name">关联性</div>
                </li>
                <li class="person-check-item sixth-level">
                    <div class="person-check-result">未通过</div>
                    <div class="person-check-num" id='checkCompleteNum'>${checkCompleteNum }人</div>
                    <div class="person-check-name">完整性</div>
                </li>
            </ul>
            <div class="person-check-btns">
             		<button class="btn-search btn-default download" button-click="exportInformanation"><a>采集信息表下载</a></button>
	               	<button class="btn-search btn-default" button-click="reUploadPersonList">重新导入</button>
                	<button class="btn-search btn-default" button-click="transferPersonList">人员入库</button>
            </div>
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
		 $.post('checkDicDataNum.do?method=checkDicDataNum',function(data){
			 var rowDicNum = data.rowDicNum;
			 var checkRelationNum = data.checkRelationNum;
			 var checkCompleteNum = data.checkCompleteNum;
				if(checkRelationNum!=''&& checkRelationNum!='undefined'){
					$('#checkRelationNum').text(checkRelationNum+'人');
				}else{
					$('#checkRelationNum').text('0人');
				}
				if(checkCompleteNum!=''&& checkCompleteNum!='undefined'){
					$('#checkCompleteNum').text(checkCompleteNum+'人');
				}else{
					$('#checkCompleteNum').text('0人');
				}
				if(rowDicNum!=''&& rowDicNum!='undefined'){
					$('#rowDicNum').text(rowDicNum);
				}
				if(checkRelationNum=='0'&&checkCompleteNum=='0'){
					MessageBox.confirm('提示','采集信息校验通过，是否人员入库？', function(yes) {
						 if(yes=='yes'){
							 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
						 }else{
							 return;
						 }
					 })
				}
				else if(checkRelationNum!='0'&&checkCompleteNum!='0'){
					MessageBox.confirm('提示','存在关联性,完整性检查异常，是否继续人员入库？', function(yes) {
						 if(yes=='yes'){
							 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
						 }else{
							 return;
						 }
						
					 })
				}
				else if(checkRelationNum!='0'&&checkCompleteNum=='0'){
					MessageBox.confirm('提示','存在关联性检查异常，是否继续人员入库？', function(yes) {
						 if(yes=='yes'){
							 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
						 }else{
							 return;
						 }
						
					 })
				}
				else if(checkCompleteNum!='0'&&checkRelationNum=='0'){
					MessageBox.confirm('提示','存在完整性检查异常，是否继续人员入库？', function(yes) {
						 if(yes=='yes'){
							 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
						 }else{
							 return;
						 }
						
					 })
				}
		},'json')
	}
	checkDicDataNum();
})
$('[button-click=reUploadPersonList]').click(function(){
	location.href = "goToUploadPersonListPage.do?method=goToUploadPersonListPage&isNewImport=1";
})
$('[button-click=exportInformanation]').click(function(){
	window.location.href ='exportDicPerson.do?method=exportDicPerson';
})
$('[button-click=transferPersonList]').click(function(){
	if($('#checkRelationNum').text()!='0人'&&$('#checkCompleteNum').text()!='0人'){
		MessageBox.confirm('提示','存在关联性,完整性检查异常，是否继续人员入库？', function(yes) {
			 if(yes=='yes'){
				 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
			 }else{
				 return;
			 }
		 })
	}
	else if($('#checkRelationNum').text()!='0人'&&$('#checkCompleteNum').text()=='0人'){
		MessageBox.confirm('提示','存在关联性检查异常，是否继续人员入库？', function(yes) {
			 if(yes=='yes'){
				 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
			 }else{
				 return;
			 }
		 })
	}
	else if($('#checkCompleteNum').text()!='0人'&&$('#checkRelationNum').text()=='0人'){
		MessageBox.confirm('提示','存在完整性检查异常，是否继续人员入库？', function(yes) {
			 if(yes=='yes'){
				 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
			 }else{
				 return;
			 }
		 })
	}
	else{
		 window.location.href ='exportPersonSuc.do?method=exportPersonSuc';
	}
   
})
</script>
</html>
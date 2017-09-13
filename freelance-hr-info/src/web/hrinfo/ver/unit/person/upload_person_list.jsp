<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>人员导入页面</title>
<style>
.successed,.failed{
    /* top:0;
    left: 100%; */
    display: inline-block;
    padding-left: 25px;
    height: 30px;
    line-height: 30px;
    font-size: 14px;
    color: #7b7b7b;
    margin-left: 140px;
    margin-top:10px;
}
.successed{
    background: url("hspszhphtml/images/common/successed-circle.png")no-repeat left transparent;
}
.failed{
    background: url("hspszhphtml/images/common/failed-circle.png")no-repeat left transparent;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#uploadFile').change(function() {
		$('.show-fileName').html($(this).val());
		$('.show-fileName').attr("title",$(this).val());
	});
	$('#submitForm').click(function() {
		if(!$('#uploadFile').val()) {
			MessageBox.alert('提示',"请选择文件");
			return false;
		}else {
			$("#uploadPseronListForm").ajaxSubmit({
	            success: function (data) {
	                if(data.success) {
	                	if(data.fileName) {
	                		$('.failed').remove();
	                		$(".btn-upload-icon").after('<span class="failed">导入失败<a class="failed-data" href="downImportPersonExcel.do?method=downImportPersonExcel&fileName='+data.fileName+'">请查看失败数据</a></span>');
	                		MessageBox.yes('提示信息', '导入完成，成功数据：'+data.successCount+'条，错误数据：'+data.failCount+'条，是否导出错误数据 ?', function(){
	                			document.location.href ='downImportPersonExcel.do?method=downImportPersonExcel&fileName='+data.fileName;
	                		})
	                	}else {
	                		$('.failed').remove();
		                	MessageBox.alert('提示',"导入成功");
		                	$('#uploadFile').val('');
		                	$('.show-fileName').html('');
							return false;
	                	}
	                }else {
	                	MessageBox.alert('提示',data.message);
						return false;
	                }
	            },
	            error: function (error) {
	            	MessageBox.alert('提示',"导入出错");
					return false;
	            },
	            dataType: 'json'
	        });
		}
	});
});
</script>
</head>
<body>
<form  id="uploadPseronListForm" action="uploadPersonList.do?method=uploadPersonList"  method="POST" enctype="multipart/form-data" onsubmit="return false;">
	<div id="transaction_modal_box" class="transaction_modal_box">
		<div class="modal-dialog-container">
			<div class="md-title">人员信息校核 &gt;人员导入<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
			<div class="file-import md-file-import">
			    <div class="file-import-process">
			    		<span class="fi-title">导入文件指引</span>
			        <ol class="progress progress-4 clearfix">
			            <li>
			                <div class="process-line"></div>
			                <div class="process-cont">
			                    <div class="process-cont-number">1</div>
			                    <div class="process-cont-text" style="font-size:16px">下载模板</div>
			                </div>
			            </li>
			            <li>
			                <div class="process-line"></div>
			                <div class="process-cont">
			                    <div class="process-cont-number">2</div>
			                    <div class="process-cont-text" style="font-size:16px">填写数据</div>
			                </div>
			            </li>
			            <li>
			                <div class="process-line"></div>
			                <div class="process-cont">
			                    <div class="process-cont-number">3</div>
			                    <div class="process-cont-text" style="font-size:16px">导入</div>
			                </div>
			            </li>
			            <li>
		                <div class="process-cont">
		                    <div class="process-cont-number">4</div>
		                    <div class="process-cont-text" style="font-size:16px">查看导入状态</div>
		                </div>
		            </li>
			        </ol>
			    </div>
			    <br/>
			    <div class="file-import-cont">
			        <a class="btn-search btn-default import-download-btn" href="<%=request.getContextPath()%>/hrinfo/ver/unit/person/import_person.xls">下载模版</a>
			        <span class="fi-title">选择文件：</span>
			        <div class="show-fileName" type="text"></div>
			        <input type="hidden" name="unitOid" value="${unitOid}"/>
			        <input type="hidden" name="orgOid" value="${orgOid}"/>
			        <a class="btn-browse-icon btn-search btn-default">浏览<input type="file" class="file-choice" name="uploadFile" id="uploadFile"></a>
			        <button id="submitForm" class="btn-upload-icon btn-search btn-default">导入</button>
			    </div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
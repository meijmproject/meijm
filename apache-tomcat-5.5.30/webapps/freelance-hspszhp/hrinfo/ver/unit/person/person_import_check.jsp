<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html >
<head lang="en">
    <meta charset="UTF-8">
    <title>人员导入首页</title>   
	<link rel="stylesheet" href="hspszhphtml/css/common/reset.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/common/base.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/common/layout.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/components/head_nav.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/components/file_import.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/components/search_result_table.css"/>
	<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css"/>	
</head>
<body>
<div class="current-position">
    当前位置：
    <span>基础信息管理 &gt; </span>
    <span>人员信息校核 &gt; </span>
    <span>人员信息导入 </span>
</div>
<form  id="uploadPseronListForm" action="checkImportFile.do?method=checkImportFile"  method="POST" enctype="multipart/form-data" onsubmit="return false;">
		<div class="main-container file-import-wrap ">
			<!-- <div class="md-title">人员信息校核 &gt;人员导入<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div> -->
			   <div class="person-info-import">
			       <div class="file-import-process">
			           <ol class="progress progress-6 clearfix">
			               <li>
			                   <div class="process-line"></div>
			                   <div class="process-cont">
			                       <div class="process-cont-number">1</div>
			                       <div class="process-cont-text">导入</div>
			                   </div>
			               </li>
			               <li>
			                   <div class="process-line"></div>
			                   <div class="process-cont">
			                       <div class="process-cont-number">2</div>
			                       <div class="process-cont-text">采集项映射</div>
			                   </div>
			               </li>
			               <li>
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
			    <br/>
			    <div class="file-import-cont">
			        <a class="btn-search btn-default import-download-btn" href="<%=request.getContextPath()%>/hrinfo/ver/unit/person/import_template.rar">下载模版</a>
			        <span class="fi-title">选择文件：</span>
			        <div class="show-fileName" type="text"></div>
			        <input type="hidden" name="unitOid" value="${unitOid}"/>
			        <a class="btn-browse-icon btn-search btn-default">浏览<input type="file" class="file-choice" name="uploadFile" id="uploadFile"></a>
			        <button id="submitForm" class="btn-upload-icon btn-search btn-default">导入</button>
			    </div>
			</div>
		</div>
	<!-- <div class="person-info-loading"><img src="hspszhphtml/images/common/loading.gif"></div> -->
</form>
</body>
<script type="text/javascript">
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
						var formFile = $("#uploadFile").val();
						if(data.successCheckNum){
							MessageBox.alert('提示:','解析成功的采集项' + data.successCheckNum + '项',function(){
								location.href = "goToImCollectTemplate.do?method=goToImCollectTemplate&formFile="+formFile;
							});
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
	})
</script>
</html>
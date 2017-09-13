<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!--
 * @page name   hrinfo/dataimport/unit/unit_import_view.jsp
 * @function    
 * @author      chenjl
 * @created  	2017-03-23
 * @version     1.0
-->
<html:html lang="true">
<head>
<title>单位科室导入</title>
<script type="text/javascript">
function doImport()
{
	var uploadFileObj =document.getElementById('uploadFile');
	if(uploadFileObj==null)
	{
		return false;
	}
	
	if(uploadFileObj.value=='' || uploadFileObj.value.length==0)
	{
		MessageBox.alert("提示信息",'请选择导入Excel文件！');
		return false;
	}
	return true;
}



var fileName = '${fileName}';
var flag = '${flag}';
var message = '${message}';
function init()
{
	if('N' == flag){
		if('' != fileName){
        $(".btn-upload-icon").after("<span  class=\"failed\">导入失败<a class=\"failed-data\" href=\"downImportExcel.do?method=downImportExcel&fileName=${fileName}\">请查看失败数据</a></span>")
        MessageBox.yes('提示信息', '导入出错，是否导出错误数据 ?', function(){
			document.location.href ='downImportExcel.do?method=downImportExcel&fileName=${fileName}';
		})
		}
		if(''!=message){
	        $(".btn-upload-icon").after("<br/><span  class=\"failed\">导入失败,原因：${message}</span>")
		}
	}
	if('Y' == flag){
        $(".btn-upload-icon").after("<br/><span class=\"successed\">导入成功</span>")
	}
}

$('#download-template').click(function(){
	alert("aaa");
	//downloadMasterplate();
});


//下载单位和科室导入模版
function downloadMasterplate()
{
	var doUrl="downImportExcel.do?method=downImportExcel&filePath=<%=request.getContextPath()%>/hrinfo/dataimport/unit/import_template.xls";
	document.forms[1].action=doUrl;
	document.forms[1].submit();
}

$(function() {
    $(".btn-browse-icon").on("change", "input[type='file']", function () {
        var filePath = $(this).val();
        var arr = filePath.split('\\');
        var fileName = arr[arr.length - 1];
        $(".show-fileName").html(fileName);
    });

    $(document).on('click',".btn-upload-icon",function (e) {
        e.stopPropagation();
        $(".failed").remove();
        $(".successed").remove();
        
        var uploadFile=$("#uploadFile").val();
        if(''==uploadFile){
            $(".btn-upload-icon").after("<br/><span class=\"failed\">导入失败,原因：请选择文件后再导入！</span>")
        }else{
			document.forms[0].submit(); 
        }
    })
});
</script>
<style type="text/css">
.successed,.failed{
    top:0;
    left: 100%;
    display: inline-block;
    padding-left: 25px;
    height: 30px;
    line-height: 30px;
    font-size: 14px;
    color: #7b7b7b;
    margin-left: 10px;
}
.successed{
    background: url("hspszhphtml/images/common/successed-circle.png")no-repeat left transparent;
}
.failed{
    background: url("hspszhphtml/images/common/failed-circle.png")no-repeat left transparent;
}
</style>
</head>
<body onload="init();" style="background: #e3e3e3;">
<div class="current-position">
    	当前位置：
        <span>基础信息管理 > </span>
        <span>机构管理 > </span>
        <span>科室信息导入 </span>
    <div style="clear: both"></div>
</div>	
<form  id="importUnitForm" action="importUnit.do?method=importUnit"  method="POST" enctype="multipart/form-data">
	<div class="file-import">
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
	       <!--  <button class="btn-search btn-default" onclick="downloadMasterplate();">下载模板</button> -->
	    </div>
	    <br/>
	    <div class="file-import-cont">
	        <a class="btn-search btn-default import-download-btn" href="<%=request.getContextPath()%>/hrinfo/dataimport/unit/import_template.xls">下载模版</a>
	        <span class="fi-title">选择文件：</span>
	        <div class="show-fileName" type="text"></div>
	        <a class="btn-browse-icon btn-search btn-default">浏览<input type="file" class="file-choice" name="uploadFile" id="uploadFile"></a>
	        <button class="btn-upload-icon btn-search btn-default">导入</button>
	    </div>
	</div>
</form>
</body>
</html:html>

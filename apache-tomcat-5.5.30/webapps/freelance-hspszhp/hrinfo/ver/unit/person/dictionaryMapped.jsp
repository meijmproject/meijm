<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>人员导入页面</title>
<script type="text/javascript">
$(document).ready(function(){
	$.post('listNoDicMapped.do?method=listNoDicMapped',function(data){
		$('#dicUl li').remove();
		$.each(data,function(index,item){
			var li='<li class="ln-firstli"><a class="ln-firstli-a" href="javascript:void(0);">'+item.name+'</a>';
			if(item.count!=0){
				li+='<span class="tips-num">'+item.count+'</span>';
			  }
			li+='</li>';
			var $li = $(li);
			$li.click(function (){
				workLocation(item.dicTypeCode,item.dicTypeMappingOid);
				 $(this).siblings("li").removeClass("active");
		         $(this).addClass("active");
		     });
			$('#dicUl').append($li);
		})
	},'json')

})
//点击左边分别把右边没映射跟已映射的展现出来
function workLocation(dicTypeCode,dicTypeMappingOid){
	$("#right-content_dic").load('goToMainTabDicPanel.do?method=goToMainTabDicPanel&dicTypeCode='+dicTypeCode+'&dicTypeMappingOid='+dicTypeMappingOid);
} 
$('[button-click=widget-closes]').click(function(){
	MessageBox.confirm('提示','当前有字典映射数据未保存，如果继续本次操作内容将不会保存，是否继续操作？', function(yes) {
		 if(yes=='yes'){
			 $.fn.close_popdown();
		 }else{
			 return;
		 }
		
	 })
})
</script>
</head>
<body>
        <div id="transaction_modal_box" class="transaction_modal_box">
            <div class="modal-dialog-container">
                <div class="md-title">
                                                       字典映射
                    <a href="#" class="md-close" button-click="widget-closes"></a>
                </div>
                <div class="md-instructions"><span>使用说明 :</span>左侧为已导入的人员信息表中的字典项；右侧为系统当前已支持的字典项，请选择字典项进行字典映射。</div>
                <div class="md-main-content dictionary-map">
                    <div class="left-content">
                        <div class="leftnav-fold">字典类型</div>
                        <ul class="left-nav" id='dicUl'>
                        </ul>
                    </div>
                    <div class="right-content" id='right-content_dic' style="overflow-y:auto;">
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
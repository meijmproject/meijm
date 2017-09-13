<link href="css/base.css" rel="stylesheet" type="text/css"/>
<link href="css/modalstyle.css" rel="stylesheet" type="text/css"/>
<link href="css/messageCheckDetail.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/loading.css" rel="stylesheet" type="text/css"/>
<link href="css/files_upload.css" rel="stylesheet"/>
<link href="css/model-min.css" rel="stylesheet"/>
<!-- Ã¥ÂÂ¿Ã§Â«Â¥Ã¥ÂÂ»Ã©ÂÂ¢ -->
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
<link rel="stylesheet" href="hspszhphtml/css/dictionary.css">
<link rel="stylesheet" href="hspszhphtml/css/layout/people_column.css">
<link href="hspszhphtml/css/common/base.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/common/layout.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/layout/index.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/login.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/common/reset.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/cydefinied.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/info_check.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_result_table.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/file_import.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/modal_dialog_tips.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/left_nav.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/layout/business_handle.css" rel="stylesheet" type="text/css" />

<%-- Ã¦Â Â¸Ã¥Â¿Âjs--%>
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.leanModal.min.js"></script>
<script src="js/card.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.expand.js"></script>
<script src="js/messages_cn.js" type="text/javascript"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/flat.js"></script>
<script src="js/spark-md5.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>

<!-- input过滤字符 -->
<script src="js/comm/input_filter.js"></script>
<script>
function hideExportBtn($btn) {
	$.post('findCfgPropertiesValue.do?method=findCfgPropertiesValue', 
			{cfgPropertiesCode: 'IS_EXPORT_BUTTON_SHOW', cfgPropertiesType: 99}, 
			function(resp) {
		resp.cfgPropertiesValue==0&&$btn.hide();
	},'json');
}
function reSetMainContainerHeight() {
    var windowHeight = $(window).height();
    $('.main-container').height(windowHeight - $('.current-position').outerHeight(true)- 2);
}
$(window).resize(function() {
	reSetMainContainerHeight();
});
$(".md-main-content").scroll(function(){
	var obj = $(this).find(".prompt_box_info_dictionary:visible").siblings("input").offset();
	if(obj){
		if($(this).find('.prompt_box_info_dictionary').hasClass("top-dictionary")){
			var objTop = obj.top - $(this).find('.prompt_box_info_dictionary').height();
			$(this).find('.prompt_box_info_dictionary').css({'top':objTop});
		}else{
			$(this).find('.prompt_box_info_dictionary').css({'top':obj.top+40});
		}
		
		if($(this).find('.prompt_box_info_dictionary').hasClass("left-dictionary")){
			var objLeft = obj.left + $(this).find(".prompt_box_info_dictionary").siblings("input").width()-$(this).find('.prompt_box_info_dictionary').width();
			$(this).find('.prompt_box_info_dictionary').css({'left':objLeft});
		}else{
			$(this).find('.prompt_box_info_dictionary').css({'left':obj.left});
		}

	}
	
});
</script>

<%-- component --%>
<link href="css/jquery.popdown.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.popdown.js" type="text/javascript"></script>

<link href="js/component/tooltipster/css/tooltipster.css" rel="stylesheet" type="text/css"/>
<link href="js/component/tooltipster/css/themes/tooltipster-shadow.css" rel="stylesheet" type="text/css"/>
<script src="js/component/tooltipster/js/jquery.tooltipster.min.js" type="text/javascript"></script> 
<script src="js/component/tooltipster/js/toolipster-config.js" type="text/javascript"></script> 
<!-- Ã¨Â¡Â¨Ã¦Â Â¼ -->
<link href="js/component/xtable/css/xtable.css" rel="stylesheet" type="text/css"/>
<script src="js/component/xtable/jquery.xtable.all.js" type="text/javascript"></script>
<!-- Ã¦ÂÂ¸Ã¦ÂÂÃ¦Â¡Â -->
<script type="text/javascript" src="js/widgets/Dialog.js"></script>
<script type="text/javascript" src="js/widgets/Widget.js"></script>
<script type="text/javascript" src="js/comm/MessageBox.js"></script>
<script type="text/javascript" src="js/comm/form.js"></script>

<%-- worktop --%>
<script type="text/javascript" src="js/comm/Worktop.js"></script>
<script type="text/javascript" src="js/comm/TplQueryForm.js"></script>
<script type="text/javascript" src="js/comm/TplNamedTbar.js"></script>
<script type="text/javascript" src="js/comm/setting.js"></script>
<script type="text/javascript" src="js/comm/select.js"></script>
<!-- Ã¥ÂÂ¾Ã§ÂÂÃ¦ÂÂ¥Ã§ÂÂ -->
<script type="text/javascript" src="js/comm/albumUi.js"></script>
<link href="hspszhphtml/css/components/album.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/viewer/viewer.js"></script>
<link href="css/viewer/viewer.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="js/comm/DataRegisters.js"></script>
<script type="text/javascript" src="js/comm/load.js"></script>
<script type="text/javascript" src="js/comm/load1.js"></script>
<script type="text/javascript" src="js/uijs/drag.js"></script>
<%-- date --%>
<script type="text/javascript" src="js/component/My97DatePicker/WdatePicker.js"></script>
<%-- dictionary --%>
<!-- <script type="text/javascript" src="js/comm/dictionary.js"></script> -->
<script type="text/javascript" src="js/comm/Min_dictionary.js"></script>
<script type="text/javascript" src="js/comm/common.js"></script>
<%-- others --%>
<script type="text/javascript" src="js/uijs/jquery.nanoscroller.js"></script> 

<script src="js/uijs/LeftNav.js" type="text/javascript"></script>
<script src="js/uijs/messageCheck.js" type="text/javascript"></script>
<!-- <script src="js/uijs/messageCheckDetail.js" type="text/javascript"></script> -->
<!-- <script src="js/uijs/maintain_accredit.js" type="text/javascript"></script> -->
<link href="css/LeftNav2.css" rel="stylesheet" type="text/css"/>
<!-- Ztree -->
<link href="js/component/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/component/zTree_v3/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="js/component/zTree_v3/js/jquery.ztree.exhide-3.5.min.js" type="text/javascript"></script>

<script type="text/javascript" src="js/uijs/jquery.nanoscroller.js"></script> 

<!-- Kalendajs -->
<link href="js/component/Kalendajs/css/kalendae.css" rel="stylesheet" type="text/css"/>
<!-- <script src="js/component/Kalendajs/js/kalendae.js" type="text/javascript"></script> -->
<script src="js/component/Kalendajs/js/kalendae.standalone.js" type="text/javascript"></script>
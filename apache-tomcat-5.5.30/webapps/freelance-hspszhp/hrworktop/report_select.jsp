<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    上报审批环节选择页面
 * @page name   /freelance-hr-biz/src/web/hrbiz/standard/business/common/default_report.jsp
 * @author      wangx
 * @created     2017/05/06
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>上报审批环节选择页面</title> 
    <script type="text/javascript" src="js/comm/customJs.js"></script>
	<link href="css/business/agree_opinion.css" rel="stylesheet" type="text/css"/>
	<link href="css/business/check_agree.css" rel="stylesheet" type="text/css"/>
	<style>
	margin-top:5%;
	@media screen and (min-height:601px) and (max-height:768px){
	    #transaction_modal_box{margin-top:3%!important;}
	    #transaction_modal_box{margin-left:25%!important;}
	    #transaction_modal_box{margin-right:25%!important;}
	}
	</style>
</head>
<script type="text/javascript">
$(document).ready(function(){
	/* drag.drag_and_drop($(".transaction_theme_title"),$(".transaction_modal_box")); */
	var bizTaskOids = "${bizTaskOids }";
	var applyNames = "${applyNames }";
	var itemCodes = "${itemCodes }";
	var itemNodeCodes = "${itemNodeCodes }";
	//点击提交
	$(".md-btn-save").click(function(){
         
		 //校验
	    $("#formAgreeOpinion").validate({
		    rules:{
		        defFlowExpress:{
		        	required: true
			    }
		    },
		    messages: {
		    	defFlowExpress:{
		        	required: "请选择审批环节"
			    }
		    },
	        errorPlacement: function (error, element)
	        {
	            error.appendTo(element.parents("dl:first"));
	        },
	        errorLabelContainer: $("#formAgreeOpinion div.modal-wrong"),
	        wrapper: "li",
	        submitHandler: function (form)
	        {
	        	var defFlowExpress = $('#defFlowExpress').val();
				Mask.showMask();
	    		$.post("bizDefaultReport.do?method=submitReported&itemCodes="+itemCodes+"&itemNodeCodes="+itemNodeCodes+"&bizTaskOids="+bizTaskOids+"&applyNames="+applyNames+"&defFlowExpress="+defFlowExpress,function(data){
					Mask.hideMask();
	       			if (data.success) {
	       				Widget.close();
	       				MessageBox.message('提示', data.message, function(){
	       					BizDefaultTaskFlowAction.processInformation(data,worktop);
	       				});
	    			}
					else
					{
						MessageBox.message('提示', data.message);
					}
	       		},'json'); 
	        }
	    });

	    
		
	});
});
</script>
<body>

<div id="transaction_modal_box" class="transaction_modal_box">
    <!-- 头部-->
    <div class="modal-dialog-container">
        <div class="md-title">
            选择审批环节
            <a href="#" class="md-close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close"></a>
        </div>
         <form id="formAgreeOpinion"  action="#" enctype="multipart/form-data" method="post" onsubmit="return false">
        <div class="md-main-content">
            <div class="md-approval-select">
                <label><i class="required">*</i>审批环节</label>
                 <select type="text" name="defFlowExpress" id="defFlowExpress">
                        	<option value="">---请选择---</option>
                        	<c:forEach items="${nodeList }" var="node">
                        		<option value="${node.nodeCode }">${node.nodeName }</option>
                        	</c:forEach>
                        </select>
                <!-- <select type="text">
                    <option>==请选择==</option>
                    <option>科主任审批</option>
                    <option>护士长审批</option>
                    <option>工会审批</option>
                    <option>医务科审批</option>
                    <option>护理部审批</option>
                    <option>人事部审批</option>
                </select> -->
            </div>
        </div>
        <div class="md-btn">
            <button class="md-btn-save close-popdown" button_click="submit-agree">提交</button>
            <button class="md-btn-cancel close-popdown" button-click="widget-close" data-dismiss="modal">取消</button>
        </div>
         </form>
    </div>
</div>


	<%-- <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="transaction_theme_title">
        <button type="button" class="transaction_theme_title_close" data-dismiss="modal" aria-label="Close" button-click="widget-close"></button>
        <span>选择审批环节</span>
    </div>
    <form id="formAgreeOpinion"  action="#" enctype="multipart/form-data" method="post" onsubmit="return false">
    <div>
        <div style="clear: both"></div><!--清除浮动-->
        <div class="checkA_boxinput">
            <dl class="checkA_horizontal">
               <dt class="checkA_horizontal_left"><b class="Required">* </b>审批环节：</dt><!-- <b class="Required">* </b> -->
                <dd>
                    <label>
                        <select name="defFlowExpress" id="defFlowExpress">
                        	<option value="">---请选择---</option>
                        	<c:forEach items="${nodeList }" var="node">
                        		<option value="${node.nodeCode }">${node.nodeName }</option>
                        	</c:forEach>
                        </select>
                    </label>
                </dd>
            </dl>
        </div>
    </div>
    <div class="agree_opinion_fooder">
        <div class="agree_opinion_foodercont">
            <button class="agree_opinion_submit" button_click="submit-agree">提交</button>
            <button class="agree_opinion_cancel  close-popdown" button-click="widget-close" data-dismiss="modal">取消</button>
            <div style="clear: both"></div><!--清除浮动-->
        </div>
    </div>
    </form>
</div> --%>


</body>
</html>
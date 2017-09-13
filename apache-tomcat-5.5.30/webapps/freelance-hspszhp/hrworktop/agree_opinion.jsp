<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    录入审批意见页面
 * @page name   /freelance-hr-worktop/src/web/hrworktop/agree_opinion.jsp
 * @author      wangx
 * @created     2017-05-17
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>录入审批意见页面</title> 
    <script type="text/javascript" src="hrworktop/agree_opinion.js"></script>
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
	//$("#popdown-dialog-cb").css{width:100%};
	drag.drag_and_drop($(".transaction_theme_title"),$(".transaction_modal_box"));
	var bizTaskOids = "${bizTaskOids }";
	var applyNames = "${applyNames }";
	var itemCodes = "${itemCodes }";
	var itemNodeCodes = "${itemNodeCodes }";
	//点击提交
	$(".agree_opinion_submit").click(function(){
         
		 //校验
	    $("#formAgreeOpinion").validate({
		    rules:{
		        date:{
		        	required: true
			    },
		        content:{
		        	required: true
			    }
		    },
		    messages: {
		        date:{
		        	required: "请选择审批时间"
			    },
			    content:{
		        	required: "请填写审批意见"
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
	        	var opinion = $('textarea').val();
	    		var leader = $('#leder').val();
	    		var date = $('#date').val();
				Mask.showMask();
	    		$.post("bizDefaultRecheckAgree.do?method=submitRecheckAgree&itemCodes="+itemCodes+"&itemNodeCodes="+itemNodeCodes+"&approveLeader="+leader+"&approveDate="+date+"&opinion="+opinion+"&bizTaskOids="+bizTaskOids+"&applyNames="+applyNames,function(data){
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
   <!--  <div class="transaction_theme_title">
        <button type="button" class="transaction_theme_title_close" data-dismiss="modal" aria-label="Close" button-click="widget-close"></button>
        <span>录入办理意见</span>
    </div> -->
     <div class="md-title">
            录入办理意见
            <a href="#" class="md-close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close"></a>
        </div>
    <!-- 内容-->
    
    <form id="formAgreeOpinion"  action="#" enctype="multipart/form-data" method="post" onsubmit="return false">
    <div>
    
          <div class="modal-wrong" style="display: none">
	                <ol class="titwrong"><embed src="img/index/.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/> 以下信息有误，请重新输入！</ol>
	                <ol class="wroglist">
	                    <logic:messagesPresent>
	                        <html:messages id="error">
	                            <li><bean:write name="error" /></li>
	                        </html:messages>
	                    </logic:messagesPresent>
	                </ol>
          	  	</div>
          	  	
       <%-- <p class="agree_opinion_fontTop">本次业务涉及以下人员：</p>
        <ul id="person" class="agree_opinion_bigul">
            <li>
                ${applyNames }
            </li>
        </ul> --%>
        <div style="clear: both"></div><!--清除浮动-->
        <div class="checkA_boxinput">
            <dl class="checkA_horizontal">
               <dt class="checkA_horizontal_left">最终审批领导：</dt><!-- <b class="Required">* </b> -->
                <dd>
                    <label>
                        <input id="leder" type="text" name="leder"/>
                    </label>
                </dd>
            </dl>
            <dl class="checkA_horizontal">
                <dt><b class="Required">* </b>审批日期：</dt>
                <dd>
                    <label>
                        <input id="date" name="date" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    </label>
                </dd>
            </dl>
            <div style="clear: both"></div><!--清除浮动-->
        </div>
        <div class="agree_opinion_inputfont">
            <textarea id="opinion" name="content" class="z_text_id" maxlength="200" rows="13" cols="125%;" placeholder="请输入审核意见；(字数不能超过200字)">同意</textarea>
        </div>
        <p class="agree_opinion_alert">
            <span class="agree_opinion_alertred">*</span>
            <span class="agree_opinion_alertfont">你已经输入了 <span class="agree_opinion_red">2</span> 个字</span>
            <span class="agree_opinion_alertfont">，还可以输入 <span class="agree_opinion_red_p">200</span> 个字。</span>
        </p>
    </div>
    <!-- 脚部-->
    <div class="agree_opinion_fooder">
        <div class="agree_opinion_foodercont">
            <button class="agree_opinion_submit" button_click="submit-agree">提交</button>
            <button class="agree_opinion_cancel  close-popdown" button-click="widget-close" data-dismiss="modal">取消</button>
            <div style="clear: both"></div><!--清除浮动-->
        </div>
    </div>
    </form>
</div>
</body>
</html>
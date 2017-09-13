<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    录入审核意见页面（单位）
 * @page name   /freelance-hr-worktop/src/web/hrworktop/check_opinion_unit.jsp
 * @author      zhengdr
 * @created     2016年12月22日10:04:32
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>录入审核意见页面</title> 
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
	var itemCode = "${itemCodes }";
	var itemNodeCode = "${itemNodeCodes }";
	//点击提交
	$(".agree_opinion_submit").click(function(){
         
		 //校验
	    $("#formAgreeOpinion").validate({
		    rules:{
		        content:{
		        	required: true
			    }
		    },
		    messages: {
			    content:{
		        	required: "请填写办理意见"
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
	    		var taskOid = new Array();
	    		var itemCodes = new Array();
	    		var itemNodeCodes = new Array();
				var applyNames = new Array();
	    		var i=0;
	    		if($("li[id^='person_']").length==0)
				{
					$('.agree_opinion_submit').attr('disabled',true);
					return;
				}
	    		$("li[id^='person_']").each(function(oid){
	    			 var $this= $(this);
	    	   		 var oid = $this.attr('id');
	    	   		taskOid[i] = oid.substr(oid.lastIndexOf("_")+1);
	    	   		itemCodes[i] = itemCode;
	    	   		itemNodeCodes[i] = itemNodeCode;
					applyNames[i] = $("#"+oid).text();
	    	   		i++;
	    		});
	    		//alert(taskOid);
				Mask.showMask();
	    		$.post("bizDefaultRecheckAgree.do?method=submitRecheckAgree&itemCodes="+itemCodes+"&itemNodeCodes="+itemNodeCodes+"&opinion="+opinion+"&bizTaskOids="+taskOid+"&applyNames="+applyNames,function(data){
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
  <!--   <div class="transaction_theme_title">
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
          	  	
       <p class="agree_opinion_fontTop">本次业务涉及以下单位：</p>
        <ul id="person" class="agree_opinion_bigul">
        <c:forEach items="${unitDTOs}" var="rs" >
            <li id="person_${rs.taskOid }">
                ${rs.unitName }
                <span class="agree_opinion_bigspan" onClick="close(this);"></span>
            </li>
            </c:forEach>
             
        </ul>
        <div style="clear: both"></div><!--清除浮动-->
        <div class="agree_opinion_inputfont">
            <textarea id="opinion" name="content" class="z_text_id" maxlength="200" rows="13" cols="125%;" placeholder="请输入办理意见；(字数不能超过200字)">同意</textarea>
        </div>
        <p class="agree_opinion_alert">
            <span class="agree_opinion_alertred">*</span>
            <span class="agree_opinion_alertfont">你已经输入了 <span class="agree_opinion_red">2</span> 个字，</span>
            <span class="agree_opinion_alertfont">还可以输入 <span class="agree_opinion_red_p">200</span> 个字。</span>
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
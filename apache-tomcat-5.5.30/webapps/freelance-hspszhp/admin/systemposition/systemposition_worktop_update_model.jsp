<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/include/jsp_headers.jsp"%> --%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>
<body>
<div id="showmodal" class="modal-content">
<form id="systemPositionForm"  Class="form-inline" action="updateSystemPoistion.do?method=updateSystemPoistion" method="post">
    <div class="modal-header">
       <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
					<span aria-hidden="true">&times;</span>
				</button>
         <h4 class="modal-title">系统岗位信息&gt;修改</h4>
    </div>
    <div class="modal-body">
        <div id="query-condition">
			<input type="hidden" id="systemPositionOid" name="systemPositionOid" value="${systemPositionForm.systemPositionOid}" />
			<div class="modal-wrong" style="display: none">
	                <ol class="titwrong"><embed src="img/index/jg.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/> 以下信息有误，请重新输入！</ol>
	                <ol class="wroglist">
	                    <logic:messagesPresent>
	                        <html:messages id="error">
	                            <li><bean:write name="error" /></li>
	                        </html:messages>
	                    </logic:messagesPresent>
	                </ol>
          	  	</div>	
			<div class="modal-row01">   
				 <dl>
					 <dt><b class="Required">*</b>系统岗位名称：</dt>
                        <dd><input name="systemPositionName" type="text" class="modal_iput" required value="${systemPositionForm.systemPositionName}"></dd>
					<dt>系统岗位描述：</dt>
						<dd><input name="systemPositionDesc" type="text" class="modal_iput"  value="${systemPositionForm.systemPositionDesc}"></dd>
               	</dl>
           </div>
	  </div>
      <div style="clear: both"></div>
      </div>
    <div class="modal-footer">
				<button type="submit" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
</form>
</div>
<script type="text/javascript">
	$("#systemPositionForm").validate({
		rules:{
			systemPositionName:{
                 required: true
             },
             systemPositionDesc: {
                 required: true
             }
    	},
    	messages: {
    		systemPositionName: {
                required: "请输入系统岗位名称!"
            }
    	},
    	errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#systemPositionForm div.modal-wrong"),
        wrapper: "li",
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success: function(data){
					data = $.parseJSON(data);
					if(data.success){
						Widget.close();
						//worktop.form.goQuery();
						$("#left_nav").load('goUpdateSystemPoistionLeft.do?method=goViewPoistionRole',{"systemPositionOid":${systemPositionForm.systemPositionOid}});
					}else{
                  	  $("#systemPositionForm div.modal-wrong").css('display','block');
                  	  $(".wroglist li").html(data.message).css('color','#000000');
                    }
					return ;
				  }
				});
		}
	});
</script>
</body>
</html>
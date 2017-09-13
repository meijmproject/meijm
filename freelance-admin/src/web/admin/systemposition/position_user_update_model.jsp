<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>

<div id="showmodal" class="modal-content">
<html:form styleId="usersForm"  styleClass="form-inline" action="updatePoistionUserSuc.do?method=updatePoistionUserSuc" method="post">
    <div class="modal-header">
       <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
					<span aria-hidden="true">&times;</span>
				</button>
         <h4 class="modal-title">系统岗位用户信息&gt;修改</h4>
    </div>
    <div class="modal-body" >
    	<div id="query-condition">
			<input type="hidden" id="userOid" name="userOid" value="${usersForm.userOid}" />
			<input type="hidden" id="systemPositionOid" name="systemPositionOid" value="${usersForm.systemPositionOid}" />
			<div class="modal-wrong"  style="display: none;">
            <ol class="titwrong"><embed src="img/index/jg.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/>以下红框信息有误，请重新输入！</ol>
            <ol class="wroglist">
               <logic:messagesPresent>
                        <ul>
                            <html:messages id="error">
                                <li><bean:write name="error" /></li>
                            </html:messages>
                        </ul>
               </logic:messagesPresent>
            </ol>
        </div>
			<div class="modal-row02">  
          				<dl>
                            <dt>用户登陆ID：</dt>
                            <dd>  
                                    <input type="text" class="modal_iput" id="userId" readonly="readonly" name="userId" value="${usersForm.userId}"  />
                            </dd>
                            <dt>用户姓名：</dt>
                            <dd>
                                    <input type="text" class="modal_iput" id="userName" readonly="readonly" name="userName" value="${usersForm.userName}" />
                            </dd>
                        </dl>
          	</div>	
            <div class="modal-row02">  
          				<dl>
                            <dt>生效日期：</dt>
                            <dd>
                                    <input type="text" class="modal_iput" id="effectiveDate" name="effectiveDateStr" value="${usersForm.effectiveDateStr}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                            </dd>
                            <dt>失效日期：</dt>
                            <dd>
                                    <input type="text" class="modal_iput" id="expiredDate" name="expiredDateStr" value="${usersForm.expiredDateStr}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                            </dd>
                        </dl>
          	  
           </div>
      </div>
    </div>
    <div class="modal-footer">
				<button type="submit" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
	<div style="clear: both"></div>
</html:form>
</div>
<script type="text/javascript">
	$("#usersForm").validate({
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success: function(){
					Widget.close();
				  }
				});
		}
	});
	//defaults, form rules messages callback submitHandler
	//Validator.init({},"#systemPositionFormId",{},{}, null, function(form){});
</script>
</body>
</html>
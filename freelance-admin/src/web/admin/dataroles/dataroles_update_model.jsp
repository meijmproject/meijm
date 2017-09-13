<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改功能角色</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>
<script type="text/javascript">
/**
 * 
 */
//提交前数据校验
$(function(){
    $("#updateDataRoleForm").validate({
        rules:{
        	roleName:{
                required: true,
                maxlength: 50
            },
            roleDesc: {
    			maxlength: 100
    		}
        },   
        messages: {
        	roleName: {
                required: "请输入数据角色名称",
                maxlength: '角色名称不能超过50个字'
            },
            roleDesc: {
            	maxlength: '角色描述不能超过100个字'
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#updateDataRoleForm div.modal-wrong"),
        wrapper: "li",
        submitHandler: function( form) {
        	//alert("数据提交！");
                  var options = {
                      type : "POST" ,  
                      url:'updateDataRole.do?method=updateDataRole',
                      success : function(data) {
                    	  data = $.parseJSON(data);
                          if (data.success) {
                        	 //location.href='showDataRoleMain.do?method=showDataRoleMain&roleId='+data.message;
                        	  Widget.close();
                        	  Widget.load('#left_nav_datarole');
                              }
                          else
                          {
                              $("#updateDataRoleForm div.wrong").css('display','block');
                              $("#errorMsg").html(data.message);
                          }
                          return;
                      }
                  };
            $('#updateDataRoleForm').ajaxSubmit(options);
        }
    });
});


</script>
<body>
<div id="showmodal" class="modal-content" >
<form id="updateDataRoleForm" class="form-inline" action="" method="post" onsubmit="return false">
	<input hidden="hidden" name="roleId" value="${rolesForm.roleId}"/>
	<input hidden="hidden" name="createdByCode" value="${rolesForm.createdByCode}"/>
	<input hidden="hidden" name="createdDateStr" value="${rolesForm.createdDateStr}"/>
    <div class="modal-header">
       <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
       </button>
       <h4 class="modal-title">数据角色信息 > 修改</h4>
    </div>
    <div class="modal-body">
      <div class="sys_list  "> 
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
						<dt><b class="Required">* </b>角色名称：</dt>
						<dd>
							<input name="roleName" type="text" class="modal_iput" value="${rolesForm.roleName}" />
						</dd>
					</dl>
					<dl>
						<dt style="line-height: 120px;"><div>描述：</div></dt>
						<dd>
							<textarea name="roleDesc" class="modal_textarea" rows="5" cols="80">${rolesForm.roleDesc}</textarea>
						</dd>
					</dl>
			</div>
            <div style="clear: both"></div>
            <div class="modal-footer">
               <input  type="submit" class="btn btn-primary" value="保　存">
               <button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
         	</div>
         </div>
       </div>	
     </form>
  </div>
</body>
</html>
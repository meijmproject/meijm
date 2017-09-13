<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
 * @function    用户岗位修改页面
 * @page name   users_position_update.jsp
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户岗位修改页面</title>
    <script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript">


</script>
</head>
<body>
<div   id="showmodal"  class="modal-content">
<form class="form-inline"  id="formUsersPositionUpdate" action="updateUsersPosition.do?method=updateUsersPosition" method="post" onsubmit="return false">
     <input hidden="hidden" name="systemPositionOid" value="${userPositionInfo.systemPositionOid }"/> 
     <input hidden="hidden" name="userId" value="${userPositionInfo.userId }"/>                     
     <div class="modal-header">
            <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
            </button>
            <h4 class="modal-title">用户岗位信息&gt;修改</h4>
      </div>
      <div class="modal-body">
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
           <div class="sys_list  "> 
                <div class="col_float">    
                        <dl class="dl-horizontal">
                            <dt><b class="Required"> </b>岗位名称：</dt>
                            <dd>
                            	<label>
                                    <input type="text"  id="userId" name="userId" value="${userPositionInfo.systemPositionName }" readonly="readonly" class="readonly"/> 
                            	</label>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">    
                            <dt><b class="Required"></b>描述：</dt>
                            <dd>
                            	 <label>
                                    <input type="text"  id="userName" name="userName" value="${userPositionInfo.systemPositionDesc}" readonly="readonly" class="readonly" /> 
                            	 </label>
                            </dd>
                        </dl> 
                 </div> 
                 <div class="col_float">     
                        <dl class="dl-horizontal">
                            <dt>功能角色：</dt>
                            <dd>
                            	<label>
                                    <input type="text"  id="contactPhone" name="contactPhone" value="${userPositionInfo.functionRoleName}" readonly="readonly" class="readonly" /> 
                            	</label>
                            </dd>
                        </dl>   
                        <dl class="dl-horizontal">   
                            <dt>数据角色：</dt>
                            <dd>
                            	<label>
                                    <input type="text"  id="unitName" name="unitName" value="${userPositionInfo.dataRoleName}" readonly="readonly" class="readonly" />
                           		</label>
                            </dd>
                        </dl>
                 </div>
                 <div class="col_float">
                 	<dl class="dl-horizontal">  
                        	<dt>生效日期：</dt>
                            <dd>
                            	<label>
                                    <input type="text"  id="effectiveDate" name="effectiveDateStr" value="${userPositionInfo.effectiveDt}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                            	</label>
                            </dd>
                    </dl>
                 	<dl class="dl-horizontal">      
       	                <dt>失效日期：</dt>
                         <dd>
           	                  <label>
                                <input type="text"  id="expiredDate" name="expiredDateStr" value="${userPositionInfo.expiredDt}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
          	                  </label>
                         </dd>
                    </dl>
                 </div>    
       		</div>
       	    <div style="clear: both"></div>
       		<div class="modal-footer">
               		<input  type="submit" class="btn btn-primary" value="保　存">
                	<button type="button" class="btn btn-default close-login"  button-click="widget-close"
                		data-dismiss="modal"><a href="#" class="close-popdown">取　消</a></button>
         	</div>
         </div>		
    </form>
  </div>
</body>
<script>
$.validator.setDefaults({
    submitHandler: function( form) {
    	//alert("数据提交！");
    	var userId =$("#userId").val();
              var options = {
                  type : "POST" ,  
                  url:'updateUsersPosition.do?method=updateUsersPosition',
                  success : function(data) {
                	  data = $.parseJSON(data);
                      if (data.success) {
                  		Widget.close();
                  		Widget.load('#updateUSP');
                      }
                      else
                      {
                    	  $("#formUsersPositionUpdate div.modal-wrong").css('display','block');
                          $(".wroglist li").html(data.message).css('color','#000000');
                      }
                      return;
                  }
              };
        $('#formUsersPositionUpdate').ajaxSubmit(options);
    }
});
$(function(){
    $("#formUsersPositionUpdate").validate({
        errorPlacement: function (error, element)
        {
        error.appendTo(element.parents("dl:first"));
        },
    	errorLabelContainer: $("#formUsersCreate div.modal-wrong"),
    	wrapper: "li"
    });
});
</script>
</html>
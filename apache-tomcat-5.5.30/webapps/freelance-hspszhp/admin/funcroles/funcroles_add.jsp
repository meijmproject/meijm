<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
 * @function    新增功能角色
 * @page name   /freelance-admin/src/web/admin/funcroles/funcroles_add.jsp
 * @author      zhangqp
 * @created     2016/09/09
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>功能角色创建页面</title>
    <script type="text/javascript" src="js/comm/customJs.js"></script>
   	<script src="admin/funcroles/js/create_funcroles.js"></script>
</head>
<body>
<div id="showmodal" class="modal-content" >
  <form id="funcRoleFormId" class="form-inline" action="createUsers.do?method=createUsers" enctype="multipart/form-data" method="post" onsubmit="return false">
    <div class="modal-header">
       <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
       </button>
       <h4 class="modal-title">功能角色信息 > 新增</h4>
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
							<input name="roleName" type="text" class="modal_iput"  />
						</dd>
					</dl>
					<dl>
						<dt style="line-height: 120px;"><div>描述：</div></dt>
						<dd>
							<textarea name="roleDesc" class="modal_textarea" rows="5" cols="80"></textarea>
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
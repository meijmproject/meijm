<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!--
 * @function    数据角色修改综合页面
 * @page name   /freelance-admin/src/web/admin/dataroles/dataroles_update_main.jsp
 * @author      zhangqp
 * @created     2016/09/09
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
<!-- <link href="./css/jurisdiction_maintain.css" rel="stylesheet" type="text/css"/> -->
</head>
<body>
<div id="transaction_modal_box" class="transaction_modal_box">
    <!-- 头部-->
   <div class="transaction_theme_title">
    <span class="theme_poptit_RoleMaintain">&nbsp;&nbsp;角色维护 <span class="RoleMaintain_arrows"> >> </span> 角色授权</span>
        <button type="button" class="transaction_theme_title_close" data-dismiss="modal" aria-label="Close" button-click="widget-close"></button>
    </div>
    <!--内容-->
    <div class="theme-popbod dform">
        <div class="theme_popover_content">
            <!--头部内容-->
            <div class="Role_Bigbog">
             <form id="updateFuncRoleForm" method="post" class="Role_form">
                <div class="Role_nameSelect">
                         <span class="Role_nameSpan">角色名称 :</span>
                         <input type="text" name="roleName" disabled  value="${rolesDTo.roleName}">
                </div>
                <div class="Role_describe">
                    <span class="Role_describeSpan">描述 : &nbsp;</span>
                    <textarea name="roleDesc" id="" cols="40" rows="4" disabled maxlength="" >${rolesDTo.roleDesc}</textarea>
                </div>
                <div style="clear:both"></div><!--清除浮动-->
                </form>
            </div>
            <!--下边内容-->
            <div class="theme_popover_content_Main" style="display:block">
               <table class="table table_scroll table_first_per" width=100% id="units">
                    <thead class="table_project_thead">
                        <tr>
                           <th width=50%>本节点权限</th>
                           <th>单位名称</th>
                        </tr>
                   </thead>
                   <tbody >
                    <c:forEach items="${unitSelectList}" var="roleDataAuthDTO" >
		              <tr>
		                  <td style="padding-left:23.5%">
		                   <c:if test="${roleDataAuthDTO.isOnlyOwn=='Y'}">
		               		<label><input type="checkbox" checked name="powerUnit" class="checkbox_select"/> </label>
		               	</c:if>
		                 <c:if test="${roleDataAuthDTO.isOnlyOwn=='N'}">
		               		<label><input type="checkbox"  name="powerUnit" class="checkbox_select"/> </label>
		               	</c:if>
		                  </td>
		                  <td style="text-align: center">${roleDataAuthDTO.authCodeName}</td>
		              </tr>
		              </c:forEach>
                   </tbody>
               </table>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(function() {
} );
</script>
</html>
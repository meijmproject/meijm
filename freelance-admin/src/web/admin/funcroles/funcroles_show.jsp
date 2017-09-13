<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
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
                         <input type="text" name="roleName" disabled  value="${rolesForm.roleName}">
                </div>
                <div class="Role_describe">
                    <span class="Role_describeSpan">描述 : &nbsp;</span>
                    <textarea name="roleDesc" id="" cols="40" rows="4" disabled maxlength="" >${rolesForm.roleDesc}</textarea>
                </div>
                <div style="clear:both"></div><!--清除浮动-->
                </form>
            </div>
            <!--下边内容-->
            <div class="theme_popover_content_Main" style="display:block">
                <!--选项卡切换-->
                <div class="role_tabPanel">
                    <ul class="role_tabPanelUl">
                        <li class="role_hit">功能授权</li>
                        <li>事项授权</li>
                        <li>环节授权</li>
                    </ul>
                <div class="Role_panes">
                    <div class="Role_pane" style="display:block;">
                        <div style="clear: both"></div><!--清除浮动-->
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
var roleId=${rolesForm.roleId};
$(document).ready(function() {
    $('.role_tabPanel .role_tabPanelUl li').click(function(){
        $(this).addClass('role_hit').siblings().removeClass('role_hit');
        $('.Role_panes>div:eq('+$(this).index()+')').show().siblings().hide();
        var index=$(this).index();
        if(index=="0"){
   		 $(".Role_pane").load("goToViewFuncTree.do?method=goFuncRoleList&roleId="+roleId);
        }else if(index=="1"){
       	 $(".Role_pane").load("goToViewItemTree.do?method=goFuncRoleList&roleId="+roleId);
        }else if(index=="2"){
       	 $(".Role_pane").load("goToViewNodeTree.do?method=goFuncRoleList&roleId="+roleId);
        }
        CookiesDataRegister.set("menuIndex",index,30);//设置会话有效期为30天
    })
    var menuIndex=CookiesDataRegister.get("menuIndex");
    if(menuIndex!=null){
        var current= parseInt(menuIndex);
    	 $(".role_tabPanel .role_tabPanelUl li:eq("+current+")").addClass("role_hit").siblings().removeClass('role_hit');
    	 if(menuIndex=="0"){
    		 $(".Role_pane").load("goToViewFuncTree.do?method=goFuncRoleList&roleId="+roleId);
         }else if(menuIndex=="1"){
        	 $(".Role_pane").load("goToViewItemTree.do?method=goFuncRoleList&roleId="+roleId);
         }else if(menuIndex=="2"){
        	 $(".Role_pane").load("goToViewNodeTree.do?method=goFuncRoleList&roleId="+roleId);
         }
    }else{
    	$(".Role_pane").load("goToViewFuncTree.do?method=goFuncRoleList&roleId="+roleId);
   }
});
</script>
</html>
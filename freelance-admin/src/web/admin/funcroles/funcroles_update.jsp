<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/include/jsp_headers.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<script src="admin/funcroles/js/update_funcroles.js"></script>
</head>
<body>
<div id="transaction_modal_box" class="transaction_modal_box">
    <!-- 头部-->
   <div class="transaction_theme_title">
    <span class="theme_poptit_RoleMaintain">&nbsp;&nbsp;角色维护 <span class="RoleMaintain_arrows"> >> </span> 角色授权</span>
        <button type="button" class="transaction_theme_title_close" data-dismiss="modal" aria-label="Close" button-click="widget-close"></button>
    </div>
    <!--内容-->
    <div class="theme-popbod dform ">
        <div class="theme_popover_content">
            <!--头部内容-->
            <!-- 第一种样式 -->
             <div class="Role_Bigbog" id="Role_Bigbog_1" >
                <div class="Role_nameSelect">
                    <form action="" class="Role_form">
                         <span class="Role_nameSpan">角色名称 :</span>
                         <span>${rolesForm.roleName}</span>
                    </form>
                </div>
                <div class="Role_describe">
                    <span class="Role_describeSpan">描述 : &nbsp;</span>
                    <span class="Role_describe_text">${rolesForm.roleDesc}</span>
                </div>
                <div class="Role_savecCancel">
                        <a href='javascript:void(0);' onclick="updateFunHeader()"><span class="Role_savecCancel_revamp"></span></a>
                        <div style="clear:both"></div><!--清除浮动-->
                </div>
                <div style="clear:both"></div><!--清除浮动-->
            </div>
            <!-- 第二种样式 -->
            <div class="Role_Bigbog" style="display:none;" id="Role_Bigbog_2">
             <form id="updateFuncRoleForm" method="post" class="Role_form">
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
	            <input hidden="hidden" name="roleId" value="${rolesForm.roleId}"/>
				<input hidden="hidden" name="createdByCode" value="${rolesForm.createdByCode}"/>
				<input hidden="hidden" name="createdDateStr" value="${rolesForm.createdDateStr}"/>
                <div class="Role_nameSelect">
                         <span class="Role_nameSpan">角色名称 :</span>
                         <input type="text" name="roleName" required autofocus value="${rolesForm.roleName}">
                </div>
                <div class="Role_describe">
                    <span class="Role_describeSpan">描述 : &nbsp;</span>
                    <textarea name="roleDesc" id="" cols="40" rows="4" maxlength="" >${rolesForm.roleDesc}</textarea>
                </div>
                <div class="Role_savecCancel">
                        <button class="Role_save">保存</button>
                        <!-- <a href="javascript:;" class="Role_Cancel">取消</a> -->
                        <div style="clear:both"></div><!--清除浮动-->
                </div>
                <div style="clear:both"></div><!--清除浮动-->
                </form>
            </div>
            <!-- 第三种样式 -->
            <div class="Role_Bigbog Role_Bigbog_save" id="Role_Bigbog_3" style="display:none;">
                <div class="Role_crook_save">
                    <span class="Role_save_span"></span>
                    <div style="clear:both"></div>
                </div>
                <div class="Role_nameSelect">
                    <form action="" class="Role_form">
                         <span class="Role_nameSpan">角色名称 :</span>
                         <input type="text" name="roleName" disabled>
                    </form>
                </div>
                <div class="Role_describe">
                    <span class="Role_describeSpan">描述 : &nbsp;</span>
                    <textarea name="roleDesc" id="" cols="40" disabled rows="4" maxlength=""></textarea>
                </div>
                <div class="Role_savecCancel">
                         <a href='javascript:void(0);' onclick="updateFunroleHeader()"><span class="Role_savecCancel_revamp"></span></a>
                        <div style="clear:both"></div><!--清除浮动-->
                </div>
                <div style="clear:both"></div><!--清除浮动-->
            </div>
            <!--下边内容-->
            <div class="theme_popover_content_Main_func" style="display:block">
                <!--选项卡切换-->
                <div class="role_tabPanel">
                    <ul class="role_tabPanelUl">
                        <li class="role_hit" id="func">功能授权</li>
                        <li class="role_hit_lilfet" id="item">事项授权</li>
                        <li class="role_hit_lilfet" id="node">环节授权</li>
                        <div style="clear:both"></div><!--清除浮动-->
                    </ul>
                    <div class="Role_pane" style="display:block;">
                       
                        
                        <div style="clear: both"></div><!--清除浮动-->
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
        if(!$(this).hasClass("role_save_style")){
        	$(this).addClass('role_hit');
        }
        $(this).siblings().removeClass('role_hit');
       // $('.Role_panes>div:eq('+$(this).index()+')').show().siblings().hide();
        var index=$(this).index();
        if(index=="0"){
   		 $(".Role_pane").load("goToFuncTree.do?method=goFuncRoleList&roleId="+roleId);
        }else if(index=="1"){
       	 $(".Role_pane").load("goToItemTree.do?method=goFuncRoleList&roleId="+roleId);
        }else if(index=="2"){
       	 $(".Role_pane").load("goToNodeTree.do?method=goFuncRoleList&roleId="+roleId);
        }
        CookiesDataRegister.set("menuIndex",index,30);//设置会话有效期为30天
    })
    var menuIndex=CookiesDataRegister.get("menuIndex");
    if(menuIndex!=null){
        var current= parseInt(menuIndex);
    	 $(".role_tabPanel .role_tabPanelUl li:eq("+current+")").addClass("role_hit").siblings().removeClass('role_hit');
    	 if(menuIndex=="0"){
    		 $(".Role_pane").load("goToFuncTree.do?method=goFuncRoleList&roleId="+roleId);
         }else if(menuIndex=="1"){
        	 $(".Role_pane").load("goToItemTree.do?method=goFuncRoleList&roleId="+roleId);
         }else if(menuIndex=="2"){
        	 $(".Role_pane").load("goToNodeTree.do?method=goFuncRoleList&roleId="+roleId);
         }
    }else{
    	$(".Role_pane").load("goToFuncTree.do?method=goFuncRoleList&roleId="+roleId);
   }
});
function updateFunHeader(){
	$("#Role_Bigbog_1").hide();
	$("#Role_Bigbog_2").show();
}
$('.transaction_theme_title_close').click(function(){
	worktop.form.goQuery();
})
function updateFunroleHeader(){
	$("#Role_Bigbog_2").show();
	$("#Role_Bigbog_3").hide();
}
</SCRIPT>
</html>
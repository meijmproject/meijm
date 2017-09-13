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
<!-- <link href="./css/jurisdiction_maintain2.css" rel="stylesheet" type="text/css"/> -->
<!-- <script type="text/javascript" src="js/uijs/jurisdiction_maintain.js"></script> -->
</head>
<body style="overflow:hidden;">
<div class="sitemap" style="position:fixed;top:0;">
    <ul style="float: left">
        <li>当前位置：</li>
        <li><a href="#">系统管理 ></a></li>
        <li><a href="#">系统安全 ></a></li>
        <li><a href="#">数据权限角色维护</a></li>
    </ul>
    <div class="go_back"><a href="javascript:void(0);" onclick="goback();"><span style="font-family: sans-serif;">&lt;&lt; </span>返回</a></div>
    <div style="clear: both"></div>
</div>
<div class="accredit_scroll_auto" style="margin-top: 55px;overflow:hidden;"><!--包裹滚动条-->
    <!--左导航-->
    <div class="left_nav" id="left_nav_datarole" url="showDataRole.do?method=showDataRole&roleId=${roleId }">
       
    </div>
    <!-- 右边部分-->
    <!--中间主体内容-->
    <div class="right_content_data main_bg role_right_margin">
        <div class="area_System_all"><!--地区、系统类别、单位性质、仅包含主管单位-->
            <!--人员类别-->
            <%-- <div class="w_area_container">
                <div class="w_area_box margin_left_2">
                    <div class="w_area_dashed area_box_width">
                        <div class="w_area_inside">人员类别 <span>:</span></div>
                    </div>
                    <div class="w_area_select">
                        <div class="w_area_inside_container">
                            <div class="w_select_color" id="area">
                                <a href='javascript:void(0);' onclick="selectAll('area');"><span class="BlueBackground_GreyWords all_border leftall_radius">全选</span></a>
                                <a href='javascript:void(0);' onclick="clean('area');"><span class="GreyBackground_RedWords all_border">清除</span></a>
                                <a href='javascript:void(0);' onclick="selectInverse('area');"><span class="BlueBackground_GreyWords rightall_radius">反选</span></a>
                                <div style="clear: both"></div><!--清除浮动-->
                            </div>
                        </div>
                    </div>
                    <div class="SZ_area SZ_priv">
                        <div class="SZ_area_container">
                            <div class="">
                                <div class="w_area_full w_area_full_a">
                                    <ul class="w_area_full_ul" id="area_select">
                                     <c:forEach items="${personTypeList}" var="roleDataAuthDTO" >
                                     <c:if test="${roleDataAuthDTO.checked==true}">
                                     <li class=" w_area_full_blue" name="${roleDataAuthDTO.authCode}"><a href='javascript:void(0);' class='gray_1'>${roleDataAuthDTO.authCodeName}</a></li>
                                     </c:if>
                                     <c:if test="${roleDataAuthDTO.checked==false}">
                                     <li name="${roleDataAuthDTO.authCode}"><a href='javascript:void(0);' class='gray_1'>${roleDataAuthDTO.authCodeName}</a></li>
                                     </c:if>
                                      </c:forEach>
                                    </ul>
                                    <div style="clear: both;"></div><!--清除浮动-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="clear: both"></div><!--清除浮动-->
                </div>
            </div> --%>
            <!--仅包含主管单位-->
            <div class="w_competent_organization">
                <div class="w_area_box only_orga">
                    <div class="SZ_area Auth_confirm_all">
                        <div class="SZ_area_container">
                            <div class="">
                                <div class="w_area_full">
                                    <div class="w_inquire">
                                        <form id="orgNameForm" action="">
                                            <input type="text" name="orgName" id="orgName" class="w_inquire_input" placeholder="请输入机构名称">
                                        </form>
                                    </div>
                                    <div class="w_query_btn"><a href='javascript:void(0);' id="queryBtn" onclick="goQuery(null,'query')">查询</a></div>
                                    <div class=" Authorization_confirm">
                                        <div class="Auth_confirm_btn">
                                            <img src="img/jurisdiction_maintain/Auth_confirm_img.png" class="Auth_confirm_img" alt=""/> 授权确认
                                        </div>
                                    </div>
                                    <div style="clear: both;"></div><!--清除浮动-->
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div style="clear: both"></div><!--清除浮动-->
                </div>
            </div>
        </div><!--地区、系统类别、单位性质、仅包含主管单位-->
        <!-- 表格-->
        <div class="limits_box_wrap">
            <div class="limits_theme_popover">
                <div class="limits_theme-popbod">
                    <div class="limits_popover_content">
                        <div class="theme_popover_content_Main" style="display:block;width:100%;">
                            <!--主体内容左侧-->
                            <div class="theme_popover_content_Left">
                                <!-- 表格开始 -->
                                <div>
                                <table class="table table_scroll table_first_per" >
                                    <thead class="table_project_thead">
                                    <tr>
                                    	<th class="table_first_per_ONE1">
                                                <label><input type="checkbox" onclick="selectAllOnclick(this);" id="checkBox" class="checkbox_select"/> </label>
                                            </th>
                                        <th>机构名称</th>
                                        <th>隶属机构</th>
                                    </tr>
                                    </thead>
                                </table>
                                </div>
                                <div class="people_scroll">
                                    <table class="tbody_scroll" id="noPoworg">
                                  	</table>
                             </div>
                              <div class="theme_popover_content_Left_paging">
                                    <ul class="paging_pagination" id="ulPage">
                                    	<li><a class="active" href="#">10</a></li>
                                        <li><a href="javascript:void(0);" onclick="findPre()">上一页</a></li>
                                        <li><a class="active" href="#">1</a></li>
                                        <li><a href="javascript:void(0);" onclick="findNext()">下一页</a></li>
                                        <li>
                                            <input type="text" placeholder="页码" class="w_padding_input"/>
                                        </li>
                                        <li><a href="javascript:void(0);" onclick="gotoPage();" style="color:#4d4d4d;">跳转</a></li>
                                        <li><a href="javascript:void(0);" style="color:#4d4d4d;">总页数:</a></li>
                                        <li><a href="javascript:void(0);">0</a></li>
                                        <li><a href="javascript:void(0);" style="color:#4d4d4d;">总记录数:</a></li>
                                        <li><a href="javascript:void(0);">0</a></li>
                                    </ul>
                                </div>
                                <div style="clear: both"></div><!--清除浮动-->
                            </div>
                             
                            <!--弹出框中间-->
                            <div class="theme_popover_content_Middle">
                                    <ul>
                                        <li class="accredit_addElement">授权<span class="accredit_addElement_arrows"></span></li>
                                        <li class="accredit_removeElement"><span class="accredit_removeElement_arrows"></span>解除授权</li>
                                    </ul>
                            </div>
                            <!--体内容右侧-->
                            <div class="theme_popover_content_Right" style="margin-right:0;">
                                <!-- 表格开始 -->
                                <div>
                                <table class="table table_scroll table_first_per" id="orgs">
                                    <thead class="table_project_thead">
                                    <tr>
                                    	<th><input type="checkbox" id="selectAllOrg" class="checkbox_select" style='margin-left:125px;'/></th>
                                        <!-- <th>本节点权限</th> -->
                                        <th>内设机构名称</th>
                                    </tr>
                                    </thead>
                                   </table>
                                   </div>
                                    <div class="people_scroll" style="height:450px;">
                                    <table class="tbody_scroll" id="selectorg">
                                    <c:forEach items="${orgSelectList}" var="roleDataAuthDTO" >
                                    <tr>
                                        <td >
                                         <c:if test="${roleDataAuthDTO.isOnlyOwn=='Y'}">
                                     		<label><input type="checkbox" checked name="powerorg" class="checkbox_select" style='margin-left:125px;'/> </label>
                                     	</c:if>
                                       <c:if test="${roleDataAuthDTO.isOnlyOwn=='N'}">
                                     		<label><input type="checkbox"  name="powerorg" class="checkbox_select" style='margin-left:125px;'/> </label>
                                     	</c:if>
                                        </td>
                                        <td><input type="hidden" id="orgOid" value="${roleDataAuthDTO.orgOid}"/>${roleDataAuthDTO.authCodeName}</td>
                                    </tr>
                                    </c:forEach>
                                    </table>
                                    </div>
                                
                                <!-- 表格结束 -->
                                <div style="clear: both"></div><!--清除浮动-->
                            </div>
                            <div style="clear: both"></div><!--清除浮动-->
                        </div>
                    </div>
                    <!---->
                </div>
                <!--脚部-->
            </div>
        </div>
        <!-- -->
    </div>
    <div style="clear: both"></div><!--清除浮动-->
</div><!--包裹滚动条-->
</body>
<script type="text/javascript">
var roleId=${param.roleId};
$("#orgNameForm").keypress(function(e) {
	
	//禁用回车提交表单
	if (e.which == 13) 
	{
		$('#queryBtn').click();
	return false;   
	}
});
function updateDataRoles(){
	Widget.openContent('goUpdateDataleModel.do?method=goUpdateDataRoleModel&roleId=${roleId}');
} 
$(".w_select_YES span").click(function(){
	if(!$(this).hasClass("w_select_yes")){
		$(this).attr("class","w_select_yes");
		$(this).siblings().attr("class","w_select_no");
	}
})
function selectAll(flag){
	if(flag=='area'){
		$("#area_select li").addClass("w_area_full_blue");
	}else if(flag=='system'){
		$("#system_select li").addClass("w_area_full_blue");
	}else if(flag=='org'){
		$("#org_select li").addClass("w_area_full_blue");
	}
}
function clean(flag){
	if(flag=='area'){
		$("#area_select li").removeClass("w_area_full_blue");
	}else if(flag=='system'){
		$("#system_select li").removeClass("w_area_full_blue");
	}else if(flag=='org'){
		$("#org_select li").removeClass("w_area_full_blue");
	}
}
function selectInverse(flag){
	if(flag=='area'){
		$("#area_select li").each(function(){
			if($(this).hasClass("w_area_full_blue")){
				$(this).removeClass("w_area_full_blue");
			}else{
				$(this).addClass("w_area_full_blue");
			}
		})
	}else if(flag=='system'){
		$("#system_select li").each(function(){
			if($(this).hasClass("w_area_full_blue")){
				$(this).removeClass("w_area_full_blue");
			}else{
				$(this).addClass("w_area_full_blue");
			}
		})
	}else if(flag=='org'){
		$("#org_select li").each(function(){
			if($(this).hasClass("w_area_full_blue")){
				$(this).removeClass("w_area_full_blue");
			}else{
				$(this).addClass("w_area_full_blue");
			}
		})
	}
}
function queryCondition(condition){
	var str='';
	var strs='';
	$("#"+condition).find("li").each(function(){
		if($(this).hasClass("w_area_full_blue")){
			str=$(this).attr("name");
			strs+=str+",";
		}
    });
    strs=strs.substr(0,strs.lastIndexOf(","));
    return strs;
}
function selectAllOnclick(ele){
	$("input[name='orgRow']").prop("checked",ele.checked);
}
function queryorgOid(){
	var orgOid='';
	var orgOids='';
	$("#selectorg tr td:nth-child(2)").each(function(){
		orgOid=$(this).find("input").val();
		orgOids+=orgOid+",";
	});
	orgOids=orgOids.substr(0,orgOids.lastIndexOf(","));
    return orgOids;
}
function goQuery(pageNo,flag){
	var orgName=$("#orgName").val();
	var orgOids=queryorgOid();
	var params = {};
	var initpageNo=$("#ulPage li:nth-child(3)").children().text();
	if(flag=="query"){
		params.pageNo=pageNo==null?1:pageNo;
		$("#ulPage li:nth-child(3)").children().text(1)
	}else if(flag=="power"){
		params.pageNo=pageNo==null?initpageNo:pageNo;
	}
	params.pageSize=10;
	params.orgName=orgName;
	params.exOrgOids=orgOids;
	$.ajax({
		url : 'listDataAuthOrgList.do?method=listDataAuthOrgList',
		data : params,
		dataType : 'json',
		type:'POST',
		error : function(r,t) {
			alert(t);
		},
		async : true,
		success : function(data) {
			var item=eval(data.rows);
			var page = 1;
			if(eval(data.total) > 0)
			{
				page = Math.ceil(eval(data.total)/10);
			}	
			$("#ulPage li:nth-child(8)").children().text(page);
			$("#ulPage li:nth-child(10)").children().text(data.total);
			$("#noPoworg tr").remove();
			$.each(item,function(index,orgDTO){
				var parentOrgName=orgDTO.parentOrgName==undefined?"":orgDTO.parentOrgName;
				var tbTr ="<tr><th class='table_first_per_ONE1'><label><input type='hidden' id='orgOid' value='"+orgDTO.orgOid+"'/><input type='checkbox' name='orgRow' class='checkbox_select'/></label></th>"
   	 				+"<td title="+orgDTO.orgName+">"+orgDTO.orgName+"</td>"
   	 				+"<td>"+parentOrgName+"</td></tr>";
				$("#noPoworg").append(tbTr);
			})
		}
	});
	
}
$(".Auth_confirm_btn").click(function(){
	var orgOid="";
	var orgOids="";
	var onlyOwnStr="";
	var onlyOwnStrs="";
	$("input[name='powerorg']:checked").each(function(){
		var onlyOwnStr=$(this).parent().parent().parent().find("#orgOid").val();
		onlyOwnStrs+=onlyOwnStr+",";
	})
	$("input[name='powerorg']").each(function(){
		var orgOid=$(this).parent().parent().parent().find("#orgOid").val();
		orgOids+=orgOid+",";
	})
	var authAllorgCode;
	if($("input[name='powerorg']").length>0){
		authAllorgCode="N";
	}else{
		authAllorgCode="Y";
	}
	orgOids=orgOids.substr(0,orgOids.lastIndexOf(","));
	onlyOwnStrs=onlyOwnStrs.substr(0,onlyOwnStrs.lastIndexOf(","));
	$.ajax({
		url : 'orgAuthorized.do?method=orgAuthorized',
		data : {orgOids:orgOids,onlyOwnStrArray:onlyOwnStrs,authAllorgCode:authAllorgCode,roleId:roleId},
		dataType : 'json',
		error : function(r,t) {
			alert(t);
		},
		async : true,
		success : function(data) {
			MessageBox.alert('提示',data.message,function(){
				goback();
			});
		}
	})
})

$(".accredit_addElement").click(function(){
	var length = $("input[name='orgRow']:checked").length;
	if(length<=0){
		MessageBox.alert('提示', '请选择需要授权的单位!');
  		return;
  	}
	$("input[name='orgRow']:checked").each(function(i,ele){
		var orgOid=$(this).parent().find("#orgOid").val();
		var orgName=$(this).parent().parent().next().text();
		var trTr="<tr><td><label><input type='checkbox' name='powerorg'  class='checkbox_select' style='margin-left:125px;'/></label></td>"
			+"<td><input type='hidden' id='orgOid' value='"+orgOid+"'/>"+orgName+"</td></tr>"
		$("#selectorg").append(trTr);
	})
	var pageNo=$("#ulPage li:nth-child(3)").children().text();
	goQuery(pageNo,"power");
	$('#checkBox').attr('checked',false);
})
$(".accredit_removeElement").click(function(){
	var length = $("input[name='powerorg']:checked").length;
	if(length<=0){
		MessageBox.alert('提示', '请选择需要移除授权的单位!');
  		return;
  	}
	$("input[name='powerorg']:checked").each(function(i,ele){
		$(this).parent().parent().parent().remove();
	})
	var pageNo=$("#ulPage li:nth-child(3)").children().text();
	goQuery(pageNo,"power");
	$('#selectAllOrg').attr('checked',false);
})	
function findPre(){
	var pageNo=$("#ulPage li:nth-child(3)").children().text();
	if(eval(pageNo)<2){
		MessageBox.alert('提示', '本页为第一页!');
	}else{
		$("#ulPage li:nth-child(3)").children().text(Math.floor(pageNo)-1);
		goQuery(Math.floor(pageNo)-1,"power");
	}
}
function findNext(){
	var pageNo=$("#ulPage li:nth-child(3)").children().text();
	var totalPage=$("#ulPage li:nth-child(8)").children().text();
	if(eval(pageNo)>=eval(totalPage)){
		MessageBox.alert('提示', '本页为最后一页!');
    }else{
    	$("#ulPage li:nth-child(3)").children().text(Math.floor(pageNo)+1);
    	goQuery(Math.floor(pageNo)+1,"power");
    }
}
function gotoPage(){
	 var pageNo=$("#ulPage li:nth-child(5)").children().val();
	 var totalPage=$("#ulPage li:nth-child(8)").children().text();
	 var t=/^[0-9]*[1-9][0-9]*$/ ;  
	 if(!t.test(pageNo)){
		 MessageBox.alert('提示', '请输入正整数!');
	 }else if(eval(pageNo)>eval(totalPage)){
		 MessageBox.alert('提示', '页码不能大于总页数!');
	 }else{
		 $("#ulPage li:nth-child(3)").children().text(pageNo);
		 goQuery(pageNo,"power");
	 }
	 
}
$(".w_area_full_ul li").click(function(){
	if(!$(this).hasClass("w_area_full_blue")){
		$(this).addClass("w_area_full_blue");
	}else{
		$(this).removeClass("w_area_full_blue");
	}
})
$(document).ready(function() {
	 Widget.load('#left_nav_datarole');
	 var rheight=$('.right_content').height();
     $('#left_nav_datarole').css('height',rheight+'px');
     toggleColor($('#area span:eq(0)'),$('#area span:eq(1)'),$('#area span:eq(2)'));
     toggleColor($('#system span:eq(0)'),$('#system span:eq(1)'),$('#system span:eq(2)'));
     toggleColor($('#org span:eq(0)'),$('#org span:eq(1)'),$('#org span:eq(2)'));
});
function goback(){
	HistoryRegister.go("dataRoleUpdateGoBackUrl");
}
function toggleColor(obj1,obj2,obj3){
	    obj1.click(function(){
	        $(this).css('color','#fff');
	        $(this).css('background','#0084e2');
	        obj2.css('color','#ff0000');
	        obj2.css('background-color','#ddd');
	        obj3.css('color','#5e5e5e');
	        obj3.css('background-color','#e4e4e4');
	    });
	    obj2.click(function(){
	        obj1.css('color','#5e5e5e');
	        obj1.css('background','#e4e4e4');
	        obj2.css('color','#fff');
	        obj2.css('background-color','#ff3333');
	        obj3.css('color','#5e5e5e');
	        obj3.css('background-color','#e4e4e4');
	    });
	    obj3.click(function(){
	        obj1.css('color','#5e5e5e');
	        obj1.css('background','#e4e4e4');
	        obj2.css('color','#ff0000');
	        obj2.css('background','#e4e4e4');
	        obj3.css('color','#fff');
	        obj3.css('background-color','#339de8');
	    });
	}
$('#selectAllOrg').click(function() {
	$('#selectorg').find('[type=checkbox]').prop('checked', this.checked);
});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    特殊授权信息列表页面
 * @page name   /hrinfo/ver/unit/comm/pbspeciaauthinfo/ver_pbspeciaauthinfo_list.jsp
 * @author      huangyj
 * @created     2017/02/13
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>信息校核列表页</title>
	<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
	<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
	<!-- <script type="text/javascript" src="hrinfo/ver/unit/comm/pbeducationinfo/js/check_education_info.js"></script> -->
</head>
<logic:messagesPresent>
		<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
				
					<li>
						<bean:write name="error" />
					</li>
				</html:messages>
			</ul>
</logic:messagesPresent>
<body>
<div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">特殊授权信息</h3>
	       <div class="st-title-icon st-title-button">
	           <button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbSpeciaAuthPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
	           <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadSpeciaAuth('${param.personOid}')">上传附件</button>
	       </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>权限类型</th>
                	<th>授权级别</th>
                	<th>授权时间</th>
                	<th>授权状态</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                    <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0120%>" dicItemCode="${dto.authType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0120%>" dicItemCode="${dto.authType}" />&nbsp;</td>
                	<td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0119%>" dicItemCode="${dto.authLevel}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0119%>" dicItemCode="${dto.authLevel}" />&nbsp;</td>
                	<td title="${dto.authDateStr}">${dto.authDateStr }</td>
                	<td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0121%>" dicItemCode="${dto.authStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0121%>" dicItemCode="${dto.authStatus}" />&nbsp;</td>
                    <td>
                    <a class="st-handle-delete" href="javascript:void(0);" onclick="deleteSpeciaAuth('${dto.speciaAuthOid}')"></a>
                    <a class="st-handle-modify popdown btn" href="goToUpdatePbSpeciaAuthPage.do?method=goUpdate&urlId=${param.Id}&speciaAuthOid=${dto.speciaAuthOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>
 
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>特殊授权信息</span>
            <a style="float:right;margin-left: 20px;" href="goToCreatePbSpeciaAuthPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>权限类型</td>
                	<td>授权级别</td>
                	<td>授权时间</td>
                	<td>授权状态</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0120%>" dicItemCode="${dto.authType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0120%>" dicItemCode="${dto.authType}" />&nbsp;</td>
                	<td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0119%>" dicItemCode="${dto.authLevel}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0119%>" dicItemCode="${dto.authLevel}" />&nbsp;</td>
                	<td title="${dto.authDateStr}">${dto.authDateStr }</td>
                	<td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0121%>" dicItemCode="${dto.authStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0121%>" dicItemCode="${dto.authStatus}" />&nbsp;</td>
                    <td>
                    	<a href="javascript:void(0);" onclick="deleteSpeciaAuth('${dto.speciaAuthOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbSpeciaAuthPage.do?method=goUpdate&urlId=${param.Id}&speciaAuthOid=${dto.speciaAuthOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
</body>
<script type="text/javascript">


$(document).ready(function(){
    $('.popdown').popdown({width:1200});
})

function deleteSpeciaAuth(speciaAuthOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbSpeciaAuth.do?method=delete',
				data :  {speciaAuthOid:speciaAuthOid},
				dataType : 'json',
				error : function(x,t) {
					alert(t);
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
					}
					else
                    {
                        alert(data.message);
                    }
				}
			});
		}
	});
}
function uploadSpeciaAuth(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '14'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
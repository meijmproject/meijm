<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同变动历史信息查看列表页面
 * @page name   /hrinfo/ver/unit/comm/pbengageconhistinfo/ver_pbengageconhistinfo_list.jsp
 * @author      liul
 * @created     2017/02/16
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
<!-- 新增页面 -->
<%-- <%@ include file="/hrinfo/ver/unit/comm/pbengageconhistinfo/verpb_pbengageconhistinfo_create.jsp"%> --%>
<!-- 修改页面 -->
<%-- <%@ include file="/hrinfo/ver/unit/comm/pbengageconhistinfo/verpb_pbengageconhistinfo_update.jsp"%> --%>
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">合同变动历史信息</h3>
	       <div class="st-title-icon st-title-button">
		            <i>
		            	<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbEngageConHistInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
	           			<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPbEngageConHistInfo('${param.personOid}')">上传附件</button>
		            </i>
	       </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	 <th>合同编号</th>
                	<th>合同签订方式</th>
                	<th>合同类型</th>
                	<th>合同开始时间</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                 <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                 <td title="${dto.contractNo}">${dto.contractNo }</td>
                	<td>
                        <dictionary:viewDicItemName dicItemCode="${dto.contractType}" dicTypeCode="<%=DicConstants.YHRS0114%>"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.changeType}" dicTypeCode="<%=DicConstants.YHRS0115%>"/>&nbsp;
                    </td>
                	<td title="${dto.contractBeginStr}">${dto.contractBeginStr }</td>
                	 <td>
                    	<a class="st-handle-delete" href="javascript:void(0);" onclick="deletePbEngageConHistInfo('${dto.engageContractHistOid}')"></a>
                        <a class="st-handle-modify popdown btn" href="goToUpdatePbEngageConHistInfoPage.do?method=goUpdate&urlId=${param.Id}&engageContractHistOid=${dto.engageContractHistOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>
 
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>合同变动历史信息</span>
            <a style="float:right;margin-left: 20px;" href="goToCreatePbEngageConHistInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>合同编号</td>
                	<td>合同签订方式</td>
                	<td>合同类型</td>
                	<td>合同开始时间</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.contractNo}">${dto.contractNo }</td>
                	<td>
                        <dictionary:viewDicItemName dicItemCode="${dto.contractType}" dicTypeCode="<%=DicConstants.YHRS0114%>"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.changeType}" dicTypeCode="<%=DicConstants.YHRS0115%>"/>&nbsp;
                    </td>
                	<td title="${dto.contractBeginStr}">${dto.contractBeginStr }</td>
                	 <td>
                    	<a href="javascript:void(0);" onclick="deletePbEngageConHistInfo('${dto.engageContractHistOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbEngageConHistInfoPage.do?method=goUpdate&urlId=${param.Id}&engageContractHistOid=${dto.engageContractHistOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
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

function deletePbEngageConHistInfo(engageContractHistOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbEngageConHistInfo.do?method=delete',
				data :  {engageContractHistOid:engageContractHistOid},
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
function uploadPbEngageConHistInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '06'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
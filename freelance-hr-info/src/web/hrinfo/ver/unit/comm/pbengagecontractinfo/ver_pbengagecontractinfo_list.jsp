<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同信息列表页面
 * @page name   /hrinfo/ver/unit/comm/pbengagecontractinfo/ver_pbengagecontractinfo_list.jsp
 * @author      liul
 * @created     2016/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>信息校核列表页</title>
</head>
<logic:messagesPresent>
	<bean:message key="errors.header" />
	<ul>
		<html:messages id="error">
			<li><bean:write name="error" />
			</li>
		</html:messages>
	</ul>
</logic:messagesPresent>
<body>

	<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>合同信息</span>
            <a style="float:right;margin-left: 20px;" href="goToCreatePbEngageContractInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>合同编号</td>
                	<td>合同签订方式</td>
                	<td>合同类型</td>
                	<td>合同状态</td>
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
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.status}" dicTypeCode="<%=DicConstants.YHRS0116%>"/>&nbsp;
                    </td>
                	<td title="${dto.contractBeginStr}">${dto.contractBeginStr }</td>
                	<td>
                    	<a href="javascript:void(0);" onclick="deleteEngageContractInfo('${dto.engageContractOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbEngageContractInfoPage.do?method=goUpdate&urlId=${param.Id}&engageContractOid=${dto.engageContractOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
	<div class="infoshow-container padding-lrb">
		<div class="st-title-box">
			<h3 class="st-title-text">合同信息</h3>
			<div class="st-title-icon st-title-button">
				<c:if test="${'1' ne flag}">
					<c:if test="${ not empty pbEngageContractInfoForm.engageContractOid}">
						<i> <button class="btn-delete btn-left-icon btn-default btn-right"  onclick="deleteEngageContractInfo('${pbEngageContractInfoForm.engageContractOid}')">删除</button> </i>
                    	<i> <button class="popdown btn-modify btn-left-icon btn-default btn-right" href="goToUpdatePbEngageContractInfoPage.do?method=goUpdate&urlId=${param.Id}&engageContractOid=${pbEngageContractInfoForm.engageContractOid}" >修改</button> </i>
						<i> <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadEngageContractInfo('${param.personOid}')">上传附件</button></i>
					</c:if>
					<c:if test="${ empty pbEngageContractInfoForm.engageContractOid}">
						<i> 
							<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbEngageContractInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button> 
							<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadEngageContractInfo('${param.personOid}')">上传附件</button>
						</i>
					</c:if>
				</c:if>
			</div>
		</div>
		<form id="viewJhcPbEngageContractInfo" action="#" method="post">
			<input type="hidden" id="engageContractOid" value="${pbEngageContractInfoForm.engageContractOid}"/>
			<input type="hidden" id="personOid" value="${pbEngageContractInfoForm.personOid}"/>
			<div class="md-main-content">
				<div class="md-units md-three-column clearfix">
					<dl class="data-unit-dl">
						<dt>合同编号：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contractNo}'>${pbEngageContractInfoForm.contractNo}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同签订方式：</dt>
						<dd>
							<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${pbEngageContractInfoForm.contractType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${pbEngageContractInfoForm.contractType}" />&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同类型：</dt>
						<dd>
							<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${pbEngageContractInfoForm.changeType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${pbEngageContractInfoForm.changeType}" />&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同开始时间：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contractBeginStr}'>${pbEngageContractInfoForm.contractBeginStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同截止时间：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contractEndStr}'>${pbEngageContractInfoForm.contractEndStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同实际结束日期：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contractEndActualStr}'>${pbEngageContractInfoForm.contractEndActualStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同状态：</dt>
						<dd>
							<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${pbEngageContractInfoForm.status}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${pbEngageContractInfoForm.status}" />&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>是否存在试用期：</dt>
						<dd>
							<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbEngageContractInfoForm.probationFlag}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbEngageContractInfoForm.probationFlag}" />&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>试用期开始日期：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.probationBeginStr}'>${pbEngageContractInfoForm.probationBeginStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>试用期结束日期：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.probationEndStr}'>${pbEngageContractInfoForm.probationEndStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>合同签订时间：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.signDateStr}'>${pbEngageContractInfoForm.signDateStr}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>工作岗位：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contractJob}'>${pbEngageContractInfoForm.contractJob}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>常住住址：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.addressPermanant}'>${pbEngageContractInfoForm.addressPermanant}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>通信地址：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.addressCommunication}'>${pbEngageContractInfoForm.addressCommunication}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>联系电话：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.contactPhone}'>${pbEngageContractInfoForm.contactPhone}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>用人单位：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.unitName}'>${pbEngageContractInfoForm.unitName}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>用人单位主要负责人：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.unitMaster}'>${pbEngageContractInfoForm.unitMaster}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>用人单位地址：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.unitAddress}'>${pbEngageContractInfoForm.unitAddress}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>用人单位联系电话：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.unitPhone}'>${pbEngageContractInfoForm.unitPhone}&nbsp;</label>
						</dd>
					</dl>
					<dl class="data-unit-dl">
						<dt>其它事项：</dt>
						<dd>
							<label title='${pbEngageContractInfoForm.others}'>${pbEngageContractInfoForm.others}&nbsp;</label>
						</dd>
					</dl>
				</div>
			</div>
		</form>
		<%-- <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>合同编号</th>
                	<th>合同签订方式</th>
                	<th>合同类型</th>
                	<th>合同状态</th>
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
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.status}" dicTypeCode="<%=DicConstants.YHRS0116%>"/>&nbsp;
                    </td>
                	<td title="${dto.contractBeginStr}">${dto.contractBeginStr }</td>
                	 <td>
                    	<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteEngageContractInfo('${dto.engageContractOid}')"></a>
                        <a class="st-handle-modify popdown btn" href="goToUpdatePbEngageContractInfoPage.do?method=goUpdate&urlId=${param.Id}&engageContractOid=${dto.engageContractOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div> --%>

	</div>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('.popdown').popdown({
			width : 1200
		});

	})
	function deleteEngageContractInfo(engageContractOid) {
		MessageBox.confirm('提示', '确认删除？', function(action) {
			if (action == 'yes') {
				$.ajax({
					url : 'deletePbEngageContractInfo.do?method=delete',
					data : {
						engageContractOid : engageContractOid
					},
					dataType : 'json',
					error : function(x, t) {
						alert(t);
						alert("error occured!!!");
					},
					async : false,
					success : function(data) {
						if (data.success) {
							$('#${param.Id}').load(
									$('#${param.Id}').attr('url'), {
										personOid : '${param.personOid}',
										Id : '${param.Id}'
									});
						} else {
							alert(data.message);
						}
					}
				});
			}
		});
	}
	function uploadEngageContractInfo(personOid){
		
		var params = {
			personOid : personOid//字节 5MB
			,refType : '05'
		}
		BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
	}
</script>
</html>
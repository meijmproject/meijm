<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同信息列表页面-查看
 * @page name   view_ver_pbengagecontractinfo_list.jsp
 * @author      liul
 * @created     2016/02/18
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>信息校核列表页</title>
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
		<div class="st-title-box">
			<h3 class="st-title-text">合同信息</h3>
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
</body>

<script type="text/javascript">

$(document).ready(function(){
    $('.popdown').popdown({width:1200});
    
})
</script>
</html>
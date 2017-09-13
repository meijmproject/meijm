<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    院内中层职务任职信息新增页面
 * @page name   /hrinfo/ver/unit/comm/pbdutyinfo/ver_pbdutyinfo_create.jsp
 * @author		duxw
 * @Created   	2017-02-27
-->



<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院内中层职务任职信息查看页面</title>
</head>

<body>
	<form id="pbDutyInfoForm" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					院内中层职务任职信息&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
						<dl class="data-unit-dl">
							<dt>任职状态：</dt>
							<dd>
								<label> <label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbDutyInfoForm.dutyStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbDutyInfoForm.dutyStatus}" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>任职科室：</dt>
							<dd>
								<label> <label title='<bean:write name="pbDutyInfoForm" property="deptName" />'><bean:write name="pbDutyInfoForm" property="deptName" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>任职职务：</dt>
							<dd>
								<label> <label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0126%>" dicItemCode="${pbDutyInfoForm.dutyName}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0126%>" dicItemCode="${pbDutyInfoForm.dutyName}" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>任职时间：</dt>
							<dd>
								<label> <label title='<bean:write name="pbDutyInfoForm" property="startDateStr" />'><bean:write name="pbDutyInfoForm" property="startDateStr" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>离任时间：</dt>
							<dd>
								<label> <label title='<bean:write name="pbDutyInfoForm" property="endDateStr" />'><bean:write name="pbDutyInfoForm" property="endDateStr" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>是否主要职务：</dt>
							<dd>
								<label> <label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbDutyInfoForm.isMainDutyInfo}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbDutyInfoForm.isMainDutyInfo}" />&nbsp;</label> </label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>备注：</dt>
							<dd>
								<label title='<bean:write name="pbDutyInfoForm" property="remark" />'><bean:write name="pbDutyInfoForm" property="remark" />&nbsp;</label>
							</dd>
						</dl>
					</div>
				</div>
				<div class="md-btn">
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关 闭</button>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
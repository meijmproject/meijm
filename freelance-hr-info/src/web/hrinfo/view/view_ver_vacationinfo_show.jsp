<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    外出信息查看页面
 * @page name   /hrinfo/view/view_ver_gooutinfo_show.jsp
 * @author      chenjl
 * @created     2017/04/06
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请休假信息查看页面</title>
</head>

<body>
	<form id="formCreateFamily" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					请休假信息&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
							<dl class="data-unit-dl">
					<dt>
						请假类型：
					</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicItemCode="${verPbVacationInfoForm.vacationType}"  dicTypeCode="<%=DicConstants.YHRS0130%>"/>"> <dictionary:viewDicItemName dicItemCode="${verPbVacationInfoForm.vacationType}"  dicTypeCode="<%=DicConstants.YHRS0130%>"/>&nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						开始时间：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.startDateStr}">${verPbVacationInfoForm.startDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						截止时间：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.endDateStr}">${verPbVacationInfoForm.endDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						请假天数：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.vacationDays}">${verPbVacationInfoForm.vacationDays}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						含法定节假日天数：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.statutoryHolidayDays}">${verPbVacationInfoForm.statutoryHolidayDays}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						工作代理人：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.workAgent}">${verPbVacationInfoForm.workAgent}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						紧急联络人：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.emergContact}">${verPbVacationInfoForm.emergContact}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						紧急联络人电话：
					</dt>
					<dd>
						<label title="${verPbVacationInfoForm.emergPhone}"> ${verPbVacationInfoForm.emergPhone}&nbsp;</label>
					</dd>
				</dl>
						<dl class="data-unit-dl">
							<dt>请假事由：</dt>
							<dd>
								<label title="${verPbVacationInfoForm.reason}">${verPbVacationInfoForm.reason}&nbsp;</label>
							</dd>
						</dl>
						<dl class="data-unit-dl">
							<dt>
								工作交接：
							</dt>
							<dd>
								<label title="${verPbVacationInfoForm.workHandover}"> ${verPbVacationInfoForm.workHandover}&nbsp;</label>
							</dd>
						</dl>
						<dl class=" data-unit-dl">
							<dt>备注：</dt>
							<dd>
								<label title="${verPbVacationInfoForm.remark}">${verPbVacationInfoForm.remark}&nbsp;</label>
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
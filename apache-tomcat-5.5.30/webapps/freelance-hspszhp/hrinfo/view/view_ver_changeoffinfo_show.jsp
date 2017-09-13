<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    调休信息查看页面
 * @page name   /hrinfo/view/view_ver_changeoffinfo_show.jsp
 * @author      renp
 * @created     2017/04/07
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调休信息查看页面</title>
</head>

<body>
	<form id="formCreateFamily" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					调休信息&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
							<dl class="data-unit-dl">
					<dt>
						调休类型：
					</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0131%>" dicItemCode="${verChangeOffInfoForm.changeOffType}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0131%>" dicItemCode="${verChangeOffInfoForm.changeOffType}" />&nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						开始时间：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.startDateStr }">${verChangeOffInfoForm.startDateStr } &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl"> 
					<dt>
						截止时间：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.endDateStr }">${verChangeOffInfoForm.endDateStr } &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						调休天数：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.changeOffDays }">${verChangeOffInfoForm.changeOffDays } &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						工作代理人：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.workAgent }">${verChangeOffInfoForm.workAgent } &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						紧急联系人：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.emergContact }">${verChangeOffInfoForm.emergContact } &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						紧急联系人电话：
					</dt>
					<dd>
						<label title="${verChangeOffInfoForm.emergPhone }">${verChangeOffInfoForm.emergPhone } &nbsp; </label>
					</dd>
				</dl>
				<dl class=" data-unit-dl">
					<dt>调休事由：</dt>
					<dd>
						<label title="${verChangeOffInfoForm.reason }">${verChangeOffInfoForm.reason }&nbsp;</label>
					</dd>
				</dl><dl class=" data-unit-dl">
					<dt>工作交接事项：</dt>
					<dd>
						<label title="${verChangeOffInfoForm.workHandover }">${verChangeOffInfoForm.workHandover }&nbsp;</label>
					</dd>
				</dl><dl class=" data-unit-dl">
					<dt>备注：</dt>
					<dd>
						<label title="${verChangeOffInfoForm.remark}">${verChangeOffInfoForm.remark}&nbsp;</label>
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
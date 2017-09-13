<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    加班查看页面
 * @page name   /hrinfo/view/view_ver_familyinfo_show.jsp
 * @author      huangyj
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加班查看页面</title>
</head>

<body>
	<form id="formCreateFamily" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					加班信息&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
						<dl class="data-unit-dl">
					<dt>
						 加班类型：
					</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicItemCode="${verOverTimeInfoForm.overtimeType}" dicTypeCode="<%=DicConstants.YHRS0132%>"/>"> <dictionary:viewDicItemName dicItemCode="${verOverTimeInfoForm.overtimeType}" dicTypeCode="<%=DicConstants.YHRS0132%>"/>&nbsp; </label>
					</dd>
				</dl >
				<dl class="data-unit-dl">
					<dt>
						开始时间：
					</dt>
					<dd>
						<label title="${verOverTimeInfoForm.startDateStr}">${verOverTimeInfoForm.startDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						截止时间：
					</dt>
					<dd>
						<label title="${verOverTimeInfoForm.endDateStr}">${verOverTimeInfoForm.endDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						 加班天数：
					</dt>
					<dd>
						<label title="${verOverTimeInfoForm.overtimeDays}">${verOverTimeInfoForm.overtimeDays}&nbsp;</label>
					</dd>
				</dl>

				<dl class="data-unit-dl">
					<dt>
						含法定节假日天数：
					</dt>
					<dd>
						<label title="${verOverTimeInfoForm.statutoryHolidayDays}">${verOverTimeInfoForm.statutoryHolidayDays}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						加班事由：
					</dt>
					<dd>
						<label title="${verOverTimeInfoForm.reason}">${verOverTimeInfoForm.reason}&nbsp;</label>
					</dd>
				</dl>
				<dl class=" data-unit-dl">
					<dt>备注：</dt>
					<dd>
						<label title="${verOverTimeInfoForm.remark}">${verOverTimeInfoForm.remark}&nbsp;</label>
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
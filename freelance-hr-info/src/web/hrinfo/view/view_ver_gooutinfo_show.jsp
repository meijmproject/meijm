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
<title>外出信息查看页面</title>
</head>

<body>
	<form id="formCreateFamily" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					外出信息&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
							<dl class="data-unit-dl">
					<dt>
						外出类型：
					</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicItemCode="${verGoOutInfoForm.goOutType}"  dicTypeCode="<%=DicConstants.YHRS0129%>"/>"> <dictionary:viewDicItemName dicItemCode="${verGoOutInfoForm.goOutType}"  dicTypeCode="<%=DicConstants.YHRS0129%>"/>&nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						出访机构：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.visitOrg}">${verGoOutInfoForm.visitOrg}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						外出地点：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.goOutAddress}">${verGoOutInfoForm.goOutAddress}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						经费来源：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.budgetFrom}">${verGoOutInfoForm.budgetFrom}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						经费预算：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.fundsBudget}">${verGoOutInfoForm.fundsBudget}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						外出天数：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.dayCount}">${verGoOutInfoForm.dayCount}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						外出开始时间：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.startDateStr}">${verGoOutInfoForm.startDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						外出截止时间：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.endDateStr}"> ${verGoOutInfoForm.endDateStr}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>工作交接人：</dt>
					<dd>
						<label title="${verGoOutInfoForm.handoverMan}">${verGoOutInfoForm.handoverMan}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>
						外出事由：
					</dt>
					<dd>
						<label title="${verGoOutInfoForm.reason}">${verGoOutInfoForm.reason}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>备注：</dt>
					<dd>
						<label title="${verGoOutInfoForm.remark}">${verGoOutInfoForm.remark}&nbsp;</label>
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
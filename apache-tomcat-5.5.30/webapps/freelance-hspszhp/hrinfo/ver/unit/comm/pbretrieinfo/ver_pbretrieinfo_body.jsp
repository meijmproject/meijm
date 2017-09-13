<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员离退休信息
 * @page name   pbPersonInShow.jsp
 * @author      cheny
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员离退休信息</title>
</head>
<body>
<div class="md-units-adddata md-units md-three-column clearfix">
		<dl >
			<dt>
				<b class="Required">* </b>离退类别：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="retrieTypeCode" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0057%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.retrieTypeCode}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退日期：</dt>
			<dd>
				<label><input type="text" name="retrieDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${pbRetrieInfoForm.retrieDateStr}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>提前退休原因：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="retrieReason" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0058%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.retrieReason}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退后享受待遇级别：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="retireTreatmentLevelCode" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0015%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.retireTreatmentLevelCode}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退后享受待遇类别：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="retireTreatmentTypeCode" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0109%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.retireTreatmentTypeCode}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休费比例：</dt>
			<dd>
				<label><input type="text" name="retireFeeRatio" class="modal_iput" maxlength="10" value="<fmt:formatNumber value="${pbRetrieInfoForm.retireFeeRatio}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休补助比例：</dt>
			<dd>
				<label><input type="text" name="retireAllowanceRatio" class="modal_iput" maxlength="10" value="<fmt:formatNumber value="${pbRetrieInfoForm.retireAllowanceRatio}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休费支付单位：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="retirtPaymentUnit" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0059%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.retirtPaymentUnit}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休后管理单位：</dt>
			<dd>
				<label><input type="text" name="retireManageUnit" class="modal_iput" maxlength="100" value="${pbRetrieInfoForm.retireManageUnit}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休批准机关：</dt>
			<dd>
				<label><input type="text" name="retritApproveUnit" class="modal_iput" maxlength="100" value="${pbRetrieInfoForm.retritApproveUnit}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休批准文号：</dt>
			<dd>
				<label><input type="text" name="retrieApproveFileno" class="modal_iput" maxlength="50" value="${pbRetrieInfoForm.retrieApproveFileno}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>离退休证号：</dt>
			<dd>
				<label><input type="text" name="retireNo" class="modal_iput" maxlength="50" value="${pbRetrieInfoForm.retireNo}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>有害工种增加工龄：</dt>
			<dd>
				<label><input type="number" name="serviceYearsAdded" class="modal_iput" maxlength="3" value="${pbRetrieInfoForm.serviceYearsAdded}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>特殊贡献比例：</dt>
			<dd>
				<label><input type="text" name="specialContributionRatio" class="modal_iput" maxlength="10" value="<fmt:formatNumber value="${pbRetrieInfoForm.specialContributionRatio}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>特殊贡献标志：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="specialContributionFlag" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0003%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.specialContributionFlag}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>归侨加发比例：</dt>
			<dd>
				<label><input type="text" name="returnedOverseasChiScale" class="modal_iput" maxlength="10" value="<fmt:formatNumber value="${pbRetrieInfoForm.returnedOverseasChiScale}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>归国华侨标志：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="returnedOverseasFlag" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0003%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.returnedOverseasFlag}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>是否终身无子女：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="isNoChildForLife" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0003%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.isNoChildForLife}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>审批类型：</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="approvalType" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0060%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.approvalType}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>审查科室：</dt>
			<dd>
				<label><input type="text" name="approvalUnit" class="modal_iput" maxlength="100" value="${pbRetrieInfoForm.approvalUnit}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>符合条例：</dt>
			<dd>
				<%-- <label> <dictionary:dicItemSelect name="compliedClause" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0061%>" emptyText="--请选择--" selected="${pbRetrieInfoForm.compliedClause}" /> </label> --%>
				<label><input type="text" name="compliedClause" class="modal_iput" maxlength="100" value="${pbRetrieInfoForm.compliedClause}" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>计生奖励：</dt>
			<dd>
				<label><input type="text" name="familyPlanningAward" class="modal_iput" maxlength="50" value="${pbRetrieInfoForm.familyPlanningAward}" /> </label>
			</dd>
		</dl>
		<dl class="updown-remark">
			<dt>备注：</dt>
			<dd style="width: 83%;">
				<label><textarea rows="3" name="remark" maxlength="1000">${pbRetrieInfoForm.remark}</textarea> </label>
			</dd>
		</dl>
		<div class="clear"></div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员基本信息
 * @page name   basic_person_info_body.jsp
 * @author      duxw
 * @created     2017/03/24
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员基本信息</title>
</head>
<body>
	<div class="data-units md-search-result">
		<dl class="data-unit-dl">
			<dt>工号：</dt>
			<dd>
				<label title="${basicPersonInfoForm.personCode }"> ${basicPersonInfoForm.personCode } &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>姓名：</dt>
			<dd>
				<label title="${basicPersonInfoForm.name }"> ${basicPersonInfoForm.name } &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>性别：</dt>
			<dd>
				<label> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0001%>" dicItemCode="${basicPersonInfoForm.sexCode}" /> &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>所在科室：</dt>
			<dd>
				<label title="${basicPersonInfoForm.hireDeptName }"> ${basicPersonInfoForm.hireDeptName } &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>人员类别：</dt>
			<dd>
				<label> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0010%>" dicItemCode="${basicPersonInfoForm.personType}" /> &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>岗位（院内）：</dt>
			<dd>
				<label title="${basicPersonInfoForm.hisPositionName }"> ${basicPersonInfoForm.hisPositionName } &nbsp; </label>
			</dd>
		</dl>
		<dl class="data-unit-dl">
			<dt>手机号码：</dt>
			<dd>
				<label title="${basicPersonInfoForm.mobilePhone }"> ${basicPersonInfoForm.mobilePhone } &nbsp; </label>
			</dd>
		</dl>
	</div>
</body>
</html>
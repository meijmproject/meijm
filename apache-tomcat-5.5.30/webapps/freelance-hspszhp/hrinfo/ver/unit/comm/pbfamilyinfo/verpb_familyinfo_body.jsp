<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭主要成员情况body页面
 * @page name   verpb_familyinfo_body.jsp
 * @author      chenp
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家庭主要成员情况页面</title>
</head>

<body>
	<div class="md-units md-three-column clearfix">
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>姓名：
			</dt>
			<dd>
				<label><input type="text" id="name" name="name" class="modal_iput" value="${verPbFamilyInfoForm.name}" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>证件类型：</dt>
			<dd>
				<label><dictionary:dicItemSelect id="idType" name="idType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${verPbFamilyInfoForm.idType}" dicTypeCode="<%=DicConstants.YHRS0002%>" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>证件号码：</dt>
			<dd>
				<label><input type="text" id="idNo" name="idNo" class="modal_iput" value="${verPbFamilyInfoForm.idNo}" onblur="validateIdNo(this)" maxlength="20" /> </label>
			</dd>
		</dl>

		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>与本人关系：
			</dt>
			<dd>
				<label><input type="text" name="relationshipName" id="relationship" class="modal_iput" readonly="true" onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0024%>')" value="<dictionary:viewDicItemName dicTypeCode='<%=DicConstants.YHRS0024%>' dicItemCode='${verPbFamilyInfoForm.relationship}' />" /> 
				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
				</label> <input type="hidden" name="relationship" value="${verPbFamilyInfoForm.relationship}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>出生日期：
			</dt>
			<dd>
				<label><input type="text" id="birthdayStr" name="birthdayStr" class="modal_iput" value="${verPbFamilyInfoForm.birthdayStr}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>政治面貌：
			</dt>
			<dd>
				<label><dictionary:dicItemSelect id="politicsVisage" name="politicsVisage" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${verPbFamilyInfoForm.politicsVisage}" dicTypeCode="<%=DicConstants.YHRS0025%>" /> </label>
			</dd>
		</dl>

		<dl class="updown-dl">
			<dt>工作单位：</dt>
			<dd>
				<label><input type="text" id="unit" name="unit" class="modal_iput" value="${verPbFamilyInfoForm.unit}" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>职务：</dt>
			<dd>
				<label><input type="text" id="duty" name="duty" class="modal_iput" value="${verPbFamilyInfoForm.duty}" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>现住址：</dt>
			<dd>
				<label><input type="text" id="address" name="address" class="modal_iput" value="${verPbFamilyInfoForm.address}" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>联系电话：</dt>
			<dd>
				<label><input type="text" id="phone" name="phone" class="modal_iput" value="${verPbFamilyInfoForm.phone}" maxlength="50" />
				</label>
			</dd>
		</dl>
	</div>
</body>
</html>
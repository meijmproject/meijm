<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭成员与社会关系查看页面
 * @page name   /hrinfo/view/view_ver_familyinfo_show.jsp
 * @author      huangyj
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家庭成员与社会关系查看页面</title>
<style type="text/css">
dd {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
</head>

<body>


	<form id="formCreateFamily" action="#" method="post">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					家庭成员与社会关系&gt;查看 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-main-content">
					<div class="infoshow-container md-infoshow-area">
							<dl class="data-unit-dl">
								<dt>姓名：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="name" />'><bean:write name="verPbFamilyInfoForm" property="name" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>与本人关系：</dt>
								<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0024%>" dicItemCode="${verPbFamilyInfoForm.relationship}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0024%>" dicItemCode="${verPbFamilyInfoForm.relationship}" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>工作单位：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="unit" />'><bean:write name="verPbFamilyInfoForm" property="unit" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>联系电话：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="phone" />'><bean:write name="verPbFamilyInfoForm" property="phone" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>证件类型：</dt>
								<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${verPbFamilyInfoForm.idType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${verPbFamilyInfoForm.idType}" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>出生日期：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="birthdayStr" />'><bean:write name="verPbFamilyInfoForm" property="birthdayStr" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>职务：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="duty" />'><bean:write name="verPbFamilyInfoForm" property="duty" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>证件号码：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="idNo" />'><bean:write name="verPbFamilyInfoForm" property="idNo" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>政治面貌：</dt>
								<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${verPbFamilyInfoForm.politicsVisage}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${verPbFamilyInfoForm.politicsVisage}" />&nbsp;</label>
								</dd>
							</dl>

							<dl class="data-unit-dl">
								<dt>现住址：</dt>
								<dd>
									<label title='<bean:write name="verPbFamilyInfoForm" property="address" />'><bean:write name="verPbFamilyInfoForm" property="address" />&nbsp;</label>
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
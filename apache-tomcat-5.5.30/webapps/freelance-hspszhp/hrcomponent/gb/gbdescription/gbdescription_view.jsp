<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    岗位说明书新增页面
 * @page name   hrbiz\standard\management\goout\gooutperson_add.jsp
 * @author      cheny
 * @created     2017/03/28
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>岗位说明书查看页面</title>
<script type="text/javascript" src="hrcomponent/gb/gbdescription/js/gbdescription_add.js"></script>
</head>
<body>
	<form id="gbDescriptionForm" class="form-inline" action="updateGbDescription.do?method=updateGbDescription" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					岗位说明书管理 &gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-main-content">
					<div class="infoshow-container md-infoshow-area">
						<div class="md-infoshow-content">
							<div class="data-units md-search-result">
								<dl class="data-unit-dl">
									<dt>岗位类别：</dt>
									<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${gbDescriptionForm.postType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${gbDescriptionForm.postType}" /></label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位级别：</dt>
									<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${gbDescriptionForm.postLevel}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${gbDescriptionForm.postLevel}" /></label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位名称：</dt>
									<dd>
										<label><input type="text" readonly="readonly" id="postName" value="${gbDescriptionForm.postName}"/> </label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位聘用条件：</dt>
									<dd>
										<label><input type="text" readonly="readonly" id="postCondition" value="${gbDescriptionForm.postCondition}"/> </label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位职责：</dt>
									<dd>
										<label><input type="text" readonly="readonly" id="postDuty" value="${gbDescriptionForm.postDuty}"/> </label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位目标任务：</dt>
									<dd>
										<label><input type="text" readonly="readonly" id="postAssigment" value="${gbDescriptionForm.postAssigment}"/> </label>
									</dd>
								</dl>
								<dl class="data-unit-dl">
									<dt>岗位绩效考核标准：</dt>
									<dd>
										<label><input type="text" readonly="readonly" id="assessmentCriteria" value="${gbDescriptionForm.assessmentCriteria}"/> </label>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
				        <div class="md-btn">
			<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关 闭</button>
		</div>
				<!-- <div class="md-btn">
					<button class="md-btn-save close-popdown">保存</button>
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
				</div> -->
			</div>
		</div>
	</form>
</body>
</html>
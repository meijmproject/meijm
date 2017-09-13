﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    处分信息新增页面
 * @page name   /hrinfo/ver/verPb_PbPunishmentInfo_create.jsp
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-16
-->






<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处分信息新增页面</title>
<script type="text/javascript"
	src="hrinfo/ver/unit/comm/pbpunishmentinfo/js/check_punishment_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>
	<form id="punishmentInfoForm" class="form-inline" action="createPbPunishmentInfo.do?method=create" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">处分信息&gt;新增<a href="#" class="md-close close-popdown"></a></div>
				<div class="md-error" style="display: none">
	        <p>以下信息有误,请重新输入</p>
	      </div>
				<div class="md-main-content">
					<input type="hidden" id="personOid" name="personOid" value="${personOid}" />
					<input type="hidden" id="url_id" value="${urlId}" />
					<div class="md-units md-three-column clearfix">
						<dl class="updown-dl">
							<dt><b class="Required">* </b>处分名称：</dt>
							<dd>
								<label><input type="text" name="punishmentName" class="modal_iput" maxlength="100" /></label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt><b class="Required">* </b>处分原因：</dt>
							<dd>
								<label><input type="text" name="punishmentReason" class="modal_iput" maxlength="100" /></label>
								<%-- <label><dictionary:dicItemSelect name="punishmentReason" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0082%>"/></label> --%>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt><b class="Required"></b>处分期限（月）：</dt>
							<dd>
								<label><input type="text" name="punishmentMonth" class="modal_iput" maxlength="100" /></label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt><b class="Required">* </b>处分批准日期：</dt>
							<dd>
								<label>
								  <input type="text" id="punishmentDateStr" name="punishmentDateStr" class="modal_iput"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>

						<dl class="updown-dl">
							<dt><b class="Required">* </b>处分批准机关名称：</dt>
							<dd>
								<label><input type="text" name="punishmentApprovalUnit" class="modal_iput" maxlength="100" /></label>
								<%-- <label><dictionary:dicItemSelect name="punishmentApprovalUnit" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0084%>"/></label> --%>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>处分截止日期：</dt>
							<dd>
								<label>
								  <input type="text" id="punishmentEndDateStr" name="punishmentEndDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>

						<dl class="updown-dl">
							<dt><b class="Required">* </b>处分是否撤销标识：</dt>
							<dd>
								<!-- <label><input type="text" name="isCancalPunishment" class="modal_iput" maxlength="100" /></label> -->
								<label>
								  <dictionary:dicItemSelect name="isCancalPunishment" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0003%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-remark">
							<dt>备注：</dt>
							<dd>
								<label><textarea rows="3" name="remark" maxlength="1000"></textarea></label>
							</dd>
						</dl>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="md-btn">
        <button id="punishmentSave" type="button" class="md-btn-save">保  存</button>
        <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
      </div>
		</div>
	</form>
</body>
</html>
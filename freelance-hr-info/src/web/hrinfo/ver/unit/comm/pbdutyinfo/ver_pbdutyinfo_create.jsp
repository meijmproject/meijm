<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    院内中层职务任职信息新增页面
 * @page name   /hrinfo/ver/unit/comm/pbdutyinfo/ver_pbdutyinfo_create.jsp
 * @author		duxw
 * @Created   	2017-02-27
-->



<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院内中层职务任职信息新增页面</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/pbdutyinfo/js/check_duty_info.js"></script>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>
	<form id="dutyInfoForm" class="form-inline" action="createPbDutyInfo.do?method=create" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					院内中层职务任职信息&gt;新增<a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
				<div class="md-main-content">
					<input type="hidden" id="personOid" name="personOid" value="${personOid}" /> <input type="hidden" id="url_id" value="${urlId}" /> <input type="hidden" id="unitOid" name="unitOid" value="${unitOid}" />

					<div class="md-units md-three-column clearfix">
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>任职状态：
							</dt>
							<dd>
								<label> <dictionary:dicItemSelect id="dutyStatus" name="dutyStatus" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0026%>" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>任职科室：
							</dt>
							<dd>
								<label> 
									<input type="text" class="modal_iput" name="deptName" id="deptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,$('#unitOid').val(),true,null,null,'true')" value="" /> 
									<input type="hidden" name="deptOid" value="" /> 
									<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>任职职务：
							</dt>
							<dd>
								<label> <dictionary:dicItemSelect id="dutyName" name="dutyName" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0126%>" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required"> </b>任职时间：
							</dt>
							<dd>
								<label> <input type="text" id="startDateStr" name="startDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required"> </b>离任时间：
							</dt>
							<dd>
								<label> <input type="text" id="endDateStr" name="endDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required"> </b>是否主要职务：
							</dt>
							<dd>
								<label> <dictionary:dicItemSelect id="isMainDutyInfo" name="isMainDutyInfo" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0003%>" /> </label>
							</dd>
						</dl>
						<dl class="updown-remark">
							<dt>备注：</dt>
							<dd style="width: 83%;">
								<label><textarea name="remark" rows="3" maxlength="1000" /></textarea>
								</label>
							</dd>
						</dl>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="md-btn">
				<button id="saveDutyInfo" type="button" class="md-btn-save">保 存</button>
				<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
			</div>
		</div>
	</form>
</body>
</html>
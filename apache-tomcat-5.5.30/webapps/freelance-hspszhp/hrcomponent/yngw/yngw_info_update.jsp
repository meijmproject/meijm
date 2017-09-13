<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.component.yngw.util.GwYnInfoConstants"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@ include file="/include/jsp_headers.jsp"%>


<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院内岗位信息修改页面</title>
<script type="text/javascript" src="hrcomponent/yngw/js/check_yngw_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>
	<form id="gwYnInfoForm" class="form-inline" action="updateGwYnInfo.do?method=update" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					院内岗位管理&gt;修改<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<input type="hidden" id="positionOid" name="positionOid" value="${gwYnInfoForm.positionOid}" />
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<div class="md-units-adddata md-units md-three-column clearfix">
							<dl>
								<dt>
									<b class="Required">*</b>工作类别：
								</dt>
								<dd>
									<label> <dictionary:dicItemSelect id="hisWorkTypeId" name="hisWorkType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${gwYnInfoForm.hisWorkType}" dicTypeCode="<%=GwYnInfoConstants.YHRS0112%>" /> </label>
								</dd>
							</dl>
							<dl>
								<dt>
									<b class="Required">*</b>岗位类别：
								</dt>
								<dd>
									<label> <dictionary:dicItemSelect id="hisPositionType" name="hisPositionType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${gwYnInfoForm.hisPositionType}" dicTypeCode="<%=GwYnInfoConstants.YHRS0113%>" /> </label>
								</dd>
							</dl>
							<dl>
								<dt><b class="Required">*</b>岗位名称：</dt>
								<dd>
									<input id="positionName" name="positionName" type="text" class="modal_iput" value="${gwYnInfoForm.positionName}" maxlength="50" />
								</dd>
							</dl>
							<dl>
								<dt>
									上级岗位名称：
								</dt>
							<dd>
								<label> <select id="parentPositionName" name="parentPositionOid" class="modal_select">
											<option value="">--请选择--</option>
										</select> 
										<input type="hidden" id="parentPositionOid" name="parentPositionOid" value="${gwYnInfoForm.parentPositionOid}" />
								</label>
							</dd>
							</dl>
						<dl>
							<dt>岗位职责：</dt>
							<dd>
								<label> <input id="hisPositionObligation" name="hisPositionObligation" type="textarea" class="modal_iput" value="${gwYnInfoForm.hisPositionObligation}" maxlength="1000" /> </label>
							</dd>
						</dl>
						<dl>
							<dt>岗位任职条件：</dt>
							<dd>
								<label> <input id="hisPositionQualifications" name="hisPositionQualifications" type="textarea" class="modal_iput" value="${gwYnInfoForm.hisPositionQualifications}" maxlength="1000" /> </label>
							</dd>
						</dl>
						<dl class="updown-remark">
							<dt>岗位说明：</dt>
							<dd>
								<label> <textarea id="remark" name="remark" rows="3" class="modal_textarea"  maxlength="1000">${gwYnInfoForm.remark}</textarea> </label>
							</dd>
						</dl>
					</div>
					<div class="md-btn">
						<button id="saveYnGwInfo" type="button" class="md-btn-save">保 存</button>
						<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
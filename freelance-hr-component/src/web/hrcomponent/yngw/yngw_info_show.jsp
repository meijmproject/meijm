<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.component.yngw.util.GwYnInfoConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>


<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院内岗位信息查看页面</title>
<script type="text/javascript" src="hrcomponent/yngw/js/check_yngw_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>
	<form id="gwYnInfoForm" class="form-inline" action="" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					院内岗位管理&gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-main-content">

					<div class="infoshow-container md-infoshow-area md-infoshow-area">

						<div class="data-units">
							<dl class="data-unit-dl">
								<dt>工作类别：</dt>
								<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=GwYnInfoConstants.YHRS0112%>" dicItemCode="${gwYnInfoForm.hisWorkType}" />'><dictionary:viewDicItemName dicTypeCode="<%=GwYnInfoConstants.YHRS0112%>" dicItemCode="${gwYnInfoForm.hisWorkType}" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>岗位类别：</dt>
								<dd>
									<label title='<dictionary:viewDicItemName dicTypeCode="<%=GwYnInfoConstants.YHRS0113%>" dicItemCode="${gwYnInfoForm.hisPositionType}" />'><dictionary:viewDicItemName dicTypeCode="<%=GwYnInfoConstants.YHRS0113%>" dicItemCode="${gwYnInfoForm.hisPositionType}" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>岗位名称：</dt>
								<dd>
									<label title='<bean:write name="gwYnInfoForm" property="positionName" />'><bean:write name="gwYnInfoForm" property="positionName" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>上级岗位名称：</dt>
								<dd>
									<label title='<bean:write name="gwYnInfoForm" property="parentPositionName" />'><bean:write name="gwYnInfoForm" property="parentPositionName" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>岗位职责：</dt>
								<dd>
									<label title='<bean:write name="gwYnInfoForm" property="hisPositionObligation" />'><bean:write name="gwYnInfoForm" property="hisPositionObligation" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>岗位任职条件：</dt>
								<dd>
									<label title='<bean:write name="gwYnInfoForm" property="hisPositionQualifications" />'><bean:write name="gwYnInfoForm" property="hisPositionQualifications" />&nbsp;</label>
								</dd>
							</dl>
							<dl class="data-unit-dl remark-unit">
								<dt>备注：</dt>
								<dd>
									<label title='<bean:write name="gwYnInfoForm" property="remark" />'><bean:write name="gwYnInfoForm" property="remark" />&nbsp;</label>
								</dd>
							</dl>
						</div>
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
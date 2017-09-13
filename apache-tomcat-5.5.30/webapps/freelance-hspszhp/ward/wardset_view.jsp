<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看病区记录</title>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<!-- <script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script> -->
<script type="text/javascript" src="ward/check_ward.js"></script>
</head>
<body>
<form id="cfWardForm" class="form-inline" action="" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">病区记录&gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
        <div class="md-main-content">
          <div class="md-error" style="display: none">
            <span class="back-error"></span>
          </div>
          <input type="hidden" id="waedOid" name="waedOid" value="${waedOid}" />
          <div class="infoshow-container multi-info-group md-infoshow-area clearfix">
            <div class="data-units">
							<dl class="data-unit-dl">
								<dt>
									<b class="Required">* </b>病区类型：
								</dt>
								<dd>
									<label
										title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0125%>" dicItemCode="${cfWardForm.waedType}" />">
										<dictionary:viewDicItemName
											dicTypeCode="<%=DicConstants.YHRS0125%>"
											dicItemCode="${cfWardForm.waedType}" /> &nbsp; </label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>
									<b class="Required">* </b>病区科室：
								</dt>
								<dd>
									<label>${deptName}</label>
								</dd>
							</dl>
							<dl class="data-unit-dl">
								<dt>床位数：</dt>
								<dd>
									<label> <bean:write name="cfWardForm" property="bedNum" />
									</label>
								</dd>
							</dl>
							<dl class="data-unit-dl remark-unit" >
								<dt>备注：</dt>
								<dd style="width: 83%;">
									<label title="<bean:write name="cfWardForm" property="remark" />"><bean:write
											name="cfWardForm" property="remark" />
									</label>
								</dd>
							</dl>
						</div>
					</div>
					<div class="md-btn">
            <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关   闭</button>
          </div>
        </div>
    </div>
  </div>
</form>
</body>
</html>
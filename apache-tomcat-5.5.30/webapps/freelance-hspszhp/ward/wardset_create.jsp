<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加病区记录</title>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<!-- <script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script> -->
<script type="text/javascript" src="ward/check_ward.js"></script>
<script type="text/javascript"
	src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
</head>
<body>
<form id="cfWardForm" class="form-inline" action="createWard.do?method=create" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">病区记录 &gt;新增<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
      <div class="md-error" style="display: none">
        <p>以下信息有误,请重新输入</p>
      </div>
      <div class="md-main-content">
        <div class="md-units-adddata md-units md-three-column clearfix">
					<dl>
						<dt>
							<b class="Required">* </b>病区类型：
						</dt>
						<dd>
							<label><dictionary:dicItemSelect name="waedType"
									styleClass="modal_select"
									dicTypeCode="<%=DicConstants.YHRS0125%>" emptyText="--请选择--" />
							</label>
						</dd>
					</dl>
					<dl>
						<dt>
							<b class="Required">* </b>病区科室：
						</dt>
						<dd>
							<label><input type="text" class="modal_iput"
								name="deptName" id="deptOid" readonly="readonly"
								onclick="min_selOrg.min_selectOrg(this,$('#unitOid').val(),null,null,null,'true')" />
								<input type="hidden" name="deptOid" /> <input type="hidden"
								id="unitOid" name="unitOid" value="${cfWardForm.unitOid}" />
							</label>
						</dd>
					</dl>
					<dl>
						<dt>床位数：</dt>
						<dd>
							<label> <input type="text" name="bedNum"
								class="modal_iput" maxlength="10" /> </label>
						</dd>
					</dl>
					<dl class="updown-remark">
						<dt>备注：</dt>
						<dd style="width: 83%;">
							<label><textarea rows="3" name="remark" maxlength="1000"></textarea>
							</label>
						</dd>
					</dl>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <div class="md-btn">
      <button id="saveWard" type="button" class="md-btn-save">保  存</button>
      <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
    </div>
  </div>
</form>
</body>
</html>
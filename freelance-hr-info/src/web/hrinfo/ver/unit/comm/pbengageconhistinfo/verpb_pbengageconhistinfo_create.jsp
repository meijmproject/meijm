<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同变动历史信息新增页面
 * @page name   /hrinfo/ver/unit/comm/pbengageconhistinfo/verpb_pbengageconhistinfo_create.jsp
 * @author      liul
 * @created     2016/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同变动历史信息新增页面</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/pbengageconhistinfo/js/check_engage_contract_con_hist_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script>
	var preContractNo = '${engageConHistInfoForm.contractNo}';
</script>
</head>

<body>


	<form id="engageConHistInfoForm" class="form-inline" action="createPbEngageConHistInfo.do?method=create" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					合同变动历史信息&gt;新增 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="personOid" name="personOid" value="${personOid}" /> <input type="hidden" id="contractNoFlag" name="contractNoFlag" /> <input type="hidden" id="url_id" value="${urlId}" />

					<div class="md-units md-three-column clearfix">

						<dl class="updown-dl">
							<dt>
								合同编号：
							</dt>
							<dd>
								<label><input type="text" name="contractNo" class="modal_iput" maxlength="30" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同签订方式：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="contractType" id="contractType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0114%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同类型：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="changeType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0115%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同开始时间：
							</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractBeginStr" name="engageConHistInfoForm" property="contractBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>合同截止时间：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractEndStr" name="engageConHistInfoForm" property="contractEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>合同实际结束日期：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractEndActualStr" name="engageConHistInfoForm" property="contractEndActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>合同签订时间：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="signDateStr" name="engageConHistInfoForm" property="signDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>工作岗位：</dt>
							<dd>
								<label><input type="text" name="contractJob" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>联系电话：</dt>
							<dd>
								<label><input type="text" name="contactPhone" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>常住住址：</dt>
							<dd>
								<label><input type="text" name="addressPermanant" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>通信地址：</dt>
							<dd>
								<label><input type="text" name="addressCommunication" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>其他事项：</dt>
							<dd>
								<label> <html:text styleClass="modal_iput" name="engageConHistInfoForm" property="others" maxlength="1000" /> </label>
							</dd>
						</dl>
					</div>
					

				</div>
				<div class="modal-footer">
					<button id="saveEngageConHistInfo" type="button" class="md-btn-save">保 存</button>
					<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取 消</button>
				</div>
			</div>
		</div>

	</form>
</body>
</html>
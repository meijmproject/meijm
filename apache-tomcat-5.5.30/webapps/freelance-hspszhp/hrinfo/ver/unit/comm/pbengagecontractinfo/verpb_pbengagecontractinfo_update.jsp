<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同信息修改页面
 * @page name   /hrinfo/ver/unit/comm/pbengagecontractinfo/verpb_pbengagecontractinfo_update.jsp
 * @author      liul
 * @created     2016/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同信息修改页面</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/pbengagecontractinfo/js/check_engage_contract_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script>
	var preContractNo = '${engageContractInfoForm.contractNo}';
	var preStatus = '${engageContractInfoForm.status}';
        $(document).ready(function(){
        	probationChange();
            //statusChange();    一期暂时不用
            //contractTypeChange();
        })
    </script>
</head>

<body>

	<form id="engageContractInfoForm" class="form-inline" action="updatePbEngageContractInfo.do?method=update" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					合同信息&gt;修改<a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="engageContractOid" name="engageContractOid" value="${engageContractInfoForm.engageContractOid}" /> 
					<input type="hidden" id="personOid" name="personOid" value="${engageContractInfoForm.personOid}" /> 
					<input type="hidden" id="contractNoFlag" name="contractNoFlag" /> 
					<input type="hidden" id="statusFlag" name="statusFlag" /> 
					<input type="hidden" id="url_id" value="${urlId}" /> 
					<input type="hidden" name="unitName" value="${engageContractInfoForm.unitName }" /> 
					<input type="hidden" name="unitOid" value="${engageContractInfoForm.unitOid }" /> 
<%-- 					<input type="hidden" name="unitMaster" value="${engageContractInfoForm.unitMaster }" />  --%>
<%-- 					<input type="hidden" name="unitAddress" value="${engageContractInfoForm.unitAddress }" />  --%>
<%-- 					<input type="hidden" name="unitPhone" value="${engageContractInfoForm.unitPhone }" /> --%>

					<div class="md-units md-three-column clearfix">
						<dl class="updown-dl">
							<dt>
								合同编号：
							</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractNo" name="engageContractInfoForm" property="contractNo" maxlength="30" />
								</label>

							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同签订方式：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="contractType" id="contractType" selected="${engageContractInfoForm.contractType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0114%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同类型：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="changeType" selected="${engageContractInfoForm.changeType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0115%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同开始时间：
							</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractBeginStr" name="engageContractInfoForm" property="contractBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同截止时间：
							</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractEndStr" name="engageContractInfoForm" property="contractEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>合同实际结束日期：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractEndActualStr" name="engageContractInfoForm" property="contractEndActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同状态：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="status" id="status" selected="${engageContractInfoForm.status}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0116%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>是否存在试用期：</dt>
							<dd>
								<label><dictionary:dicItemSelect styleClass="modal_select" id="probationFlag" onchange="probationChange();" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${engageContractInfoForm.probationFlag}" name="probationFlag" emptyText="--请选择--" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl" id="probationDiv" style="display: none">
							<dt id="probationBeginB" style="display: none">
								<b class="Required">* </b>试用开始日期：
							</dt>
							<dt id="probationBegin" style="display: none">试用开始日期：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="probationBeginStr" name="engageContractInfoForm" property="probationBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>

						</dl>
						<dl class="updown-dl" id="probationDivE" style="display: none">
							<dt id="probationEndB" style="display: none">
								<b class="Required">* </b>试用结束日期：
							</dt>
							<dt id="probationEnd" style="display: none">试用结束日期：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="probationEndStr" name="engageContractInfoForm" property="probationEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>合同签订时间：</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="signDateStr" name="engageContractInfoForm" property="signDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>工作岗位：</dt>
							<dd>
								<label><input type="text" name="contractJob" value="${engageContractInfoForm.contractJob}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>常住住址：</dt>
							<dd>
								<label><input type="text" name="addressPermanant" value="${engageContractInfoForm.addressPermanant}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>通信地址：</dt>
							<dd>
								<label><input type="text" name="addressCommunication" value="${engageContractInfoForm.addressCommunication}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required"></b>联系电话：
							</dt>
							<dd>
								<label><input type="text" name="contactPhone" value="${engageContractInfoForm.contactPhone}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>用人单位：</dt>
							<dd>
								<label title='<bean:write name="engageContractInfoForm" property="unitName" />'> 
								<input type="text" name="unitName" readonly="readonly" id="unitName" value="<bean:write name="engageContractInfoForm" property="unitName" />" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>用人单位主要负责人：</dt>
							<dd>
								<label title='<bean:write name="engageContractInfoForm" property="unitMaster" />'> 
								<input type="text" name="unitMaster" id="unitMaster" value="${engageContractInfoForm.unitMaster }" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>用人单位地址：</dt>
							<dd>
								<label title='<bean:write name="engageContractInfoForm" property="unitAddress" />'> 
								<input type="text" name="unitAddress" id="unitAddress" value="${engageContractInfoForm.unitAddress }" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>用人单位联系电话：</dt>
							<dd>
								<label title='<bean:write name="engageContractInfoForm" property="unitPhone" />'> 
								<input type="text" name="unitPhone" id="unitPhone" value="${engageContractInfoForm.unitPhone }" /> </label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>其他事项：</dt>
							<dd>
								<label title="${engageContractInfoForm.others}"> <html:text styleClass="modal_iput" name="engageContractInfoForm" property="others" maxlength="1000" /> </label>
							</dd>
						</dl>
					</div>
					

				</div>
				<div class="modal-footer">
						<button id="saveEngageContractInfo" type="button" class="btn btn-primary">保 存</button>
						<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取 消</button>
					</div>
			</div>
		</div>

	</form>

</body>
</html>
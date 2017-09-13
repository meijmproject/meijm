<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同变动历史信息修改页面
 * @page name   /hrinfo/ver/unit/comm/pbengageconhistinfo/verpb_pbengageconhistinfo_update.jsp
 * @author      liul
 * @created     2016/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同变动历史信息修改页面</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/pbengageconhistinfo/js/check_engage_contract_con_hist_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script>
	var preContractNo = '${engageConHistInfoForm.contractNo}';
</script>
</head>

<body>

	<%-- <div id="showmodal" class="modal-content">
<form id="engageConHistInfoForm" class="form-inline" action="updatePbEngageConHistInfo.do?method=update" method="post" onsubmit="return false">

    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">合同变动历史信息&gt;修改</h4>
    </div>

    <div class="modal-body">

        	<input type="hidden" id="engageContractHistOid" name="engageContractHistOid" value="${engageConHistInfoForm.engageContractHistOid}" />
			<input type="hidden" id="personOid" name="personOid" value="${engageConHistInfoForm.personOid}" />
			<input type="hidden" id="contractNoFlag" name="contractNoFlag"/>
            <input type="hidden" id="url_id" value="${urlId}" />
			<div class="modal-wrong" style="display: none">
                <ol class="titwrong"><embed src="img/index/jg.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/> 以下信息有误，请重新输入！</ol>
                <ol class="wroglist">
                    <logic:messagesPresent>
                        <html:messages id="error">
                            <li><bean:write name="error" /></li>
                        </html:messages>
                    </logic:messagesPresent>
                </ol>
            </div>
            
        	<div class="modal-row03">   <!--三列 -->
        			<dl class="updown-dl">    
                        <dt><b class="Required">* </b>合同编号：</dt>
						<dd>
							<label><html:text styleClass="modal_iput" styleId="contractNo" name="engageConHistInfoForm"  property="contractNo" maxlength="30"/></label>
						</dd>
						<dt><b class="Required">* </b>合同签订方式：</dt>
						<dd>
							<label><dictionary:dicItemSelect name="contractType" id="contractType" selected="${engageConHistInfoForm.contractType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0114%>"/></label>
						</dd>
						<dt><b class="Required">* </b>合同类型：</dt>
						<dd>
							<label><dictionary:dicItemSelect name="changeType" selected="${engageConHistInfoForm.changeType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0115%>"/></label>
						</dd>
                    </dl>
            </div>
            <div class="modal-row03">
                <dl class="updown-dl"> 
                    <dt><b class="Required">* </b>合同开始时间：</dt>
                    <dd><label><html:text styleClass="modal_iput" styleId="contractBeginStr" name="engageConHistInfoForm" property="contractBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                    <div id="contractEndDiv">
                    	<dt>合同截止时间：</dt>
                    	 <dd><label><html:text styleClass="modal_iput" styleId="contractEndStr" name="engageConHistInfoForm" property="contractEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                    </div>
                    <div id="contractEndActualDiv">
	                    <dt>合同实际结束日期：</dt>
	                    <dd><label><html:text styleClass="modal_iput" styleId="contractEndActualStr" name="engageConHistInfoForm" property="contractEndActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
	                </div>
                </dl>
            </div>
            <div class="modal-row03">
                <dl class="updown-dl"> 
					<dt>合同签订时间：</dt>
                    <dd><label><html:text styleClass="modal_iput" styleId="signDateStr" name="engageConHistInfoForm" property="signDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                    <dt>工作岗位：</dt>
						<dd>
							<label><input type="text" name="contractJob" value="${engageConHistInfoForm.contractJob}" class="modal_iput" maxlength="100" /></label>
						</dd>
						  <dt>联系电话：</dt>
						<dd>
							<label><input type="text" name="contactPhone" value="${engageConHistInfoForm.contactPhone}" class="modal_iput" maxlength="100" /></label>
						</dd>
                </dl>
            </div>
            <div class="modal-row03">
                <dl class="updown-dl"> 
                    <dt>常住住址：</dt>
						<dd>
							<label><input type="text" name="addressPermanant" value="${engageConHistInfoForm.addressPermanant}" class="modal_iput" maxlength="100" /></label>
						</dd>
					<dt>通信地址：</dt>
						<dd>
							<label><input type="text" name="addressCommunication" value="${engageConHistInfoForm.addressCommunication}" class="modal_iput" maxlength="100" /></label>
						</dd>
                  
						<dt>其他事项：</dt>
							<dd><label title="${engageConHistInfoForm.others}" >
							<html:text styleClass="modal_iput" name="engageConHistInfoForm" property="others" maxlength="1000"/>
							</label></dd>
                </dl>
            </div>
    </div>
    <div class="modal-footer">
    	<button id="saveEngageConHistInfo" type="button" class="btn btn-primary">保　存</button>
    	<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
    </div>
    
    <div style="clear: both"></div>
    
</form>
</div> --%>
	<form id="engageConHistInfoForm" class="form-inline" action="updatePbEngageConHistInfo.do?method=update" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					合同变动历史信息&gt;修改 <a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="engageContractHistOid" name="engageContractHistOid" value="${engageConHistInfoForm.engageContractHistOid}" /> <input type="hidden" id="personOid" name="personOid" value="${engageConHistInfoForm.personOid}" /> <input type="hidden" id="contractNoFlag" name="contractNoFlag" /> <input type="hidden" id="url_id" value="${urlId}" />


					<div class="md-units md-three-column clearfix">
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同编号：
							</dt>
							<dd>
								<label><html:text styleClass="modal_iput" styleId="contractNo" name="engageConHistInfoForm" property="contractNo" maxlength="30" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同签订方式：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="contractType" id="contractType" selected="${engageConHistInfoForm.contractType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0114%>" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>
								<b class="Required">* </b>合同类型：
							</dt>
							<dd>
								<label><dictionary:dicItemSelect name="changeType" selected="${engageConHistInfoForm.changeType}" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0115%>" />
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
								<label><input type="text" name="contractJob" value="${engageConHistInfoForm.contractJob}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>联系电话：</dt>
							<dd>
								<label><input type="text" name="contactPhone" value="${engageConHistInfoForm.contactPhone}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>常住住址：</dt>
							<dd>
								<label><input type="text" name="addressPermanant" value="${engageConHistInfoForm.addressPermanant}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>通信地址：</dt>
							<dd>
								<label><input type="text" name="addressCommunication" value="${engageConHistInfoForm.addressCommunication}" class="modal_iput" maxlength="100" />
								</label>
							</dd>
						</dl>
						<dl class="updown-dl">
							<dt>其他事项：</dt>
							<dd>
								<label title="${engageConHistInfoForm.others}"> <html:text styleClass="modal_iput" name="engageConHistInfoForm" property="others" maxlength="1000" /> </label>
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
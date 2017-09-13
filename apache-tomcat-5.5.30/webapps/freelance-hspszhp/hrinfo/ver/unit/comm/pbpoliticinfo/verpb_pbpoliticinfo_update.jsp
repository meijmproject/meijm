<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    政治面貌与党派信息修改页面
 * @page name   /hrinfo/ver/unit/comm/pbpoliticinfo/verpb_politicinfo_update.jspp
 * @author      huangyj
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>政治面貌与党派信息修改页面</title>
<script type="text/javascript"
	src="hrinfo/ver/unit/comm/pbpoliticinfo/js/check_politic_info.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>

<body>
<form id="politicInfoForm" class="form-inline" action="updatePbPoliticInfo.do?method=update" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">政治面貌与党派信息&gt;修改<a href="#" class="md-close close-popdown"></a></div>
      <div class="md-error" style="display: none">
        <p>以下信息有误,请重新输入</p>
      </div>
      <div class="md-main-content">
				<input type="hidden" id="politicOid" name="politicOid" value="${pbPoliticInfoForm.politicOid}" />
				<input type="hidden" id="personOid" name="personOid" value="${pbPoliticInfoForm.personOid}" />
				<input type="hidden" id="url_id" value="${urlId}" />
        <div class="md-units md-three-column clearfix">
					<dl class="updown-dl">
						<dt>
							<b class="Required">* </b>政治面貌：
						</dt>
						<dd>
							<label><dictionary:dicItemSelect id="politicStatusCode"
									name="politicStatusCode" styleClass="modal_select"
									emptyText="<%=Constant.EMPTY_SELECT %>"
									selected="${pbPoliticInfoForm.politicStatusCode}"
									dicTypeCode="<%=DicConstants.YHRS0025%>" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>参加党派时间：</dt>
						<dd>
							<label><input type="text" id="joinPoliticDateStr"
								name="joinPoliticDateStr" class="modal_iput"
								value="${pbPoliticInfoForm.joinPoliticDateStr}" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>参加党派时所在单位：</dt>
						<dd>
							<label><input type="text" id="unitOfJoinParty"
								name="unitOfJoinParty" class="modal_iput"
								value="${pbPoliticInfoForm.unitOfJoinParty}" maxlength="100" />
							</label>
						</dd>
					</dl>
					<dl class="updown-dl">
						<dt>介绍人：</dt>
						<dd>
							<label><input type="text" id="introducer"
								name="introducer" class="modal_iput"
								value="${pbPoliticInfoForm.introducer}" maxlength="100" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>转正时间：</dt>
						<dd>
							<label><input type="text" id="probationDateStr"
								name="probationDateStr" class="modal_iput"
								value="${pbPoliticInfoForm.probationDateStr}" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>党内职务：</dt>
						<dd>
							<label><dictionary:dicItemSelect id="politicDutyCode"
									name="politicDutyCode" styleClass="modal_select"
									emptyText="<%=Constant.EMPTY_SELECT %>"
									selected="${pbPoliticInfoForm.politicDutyCode}"
									dicTypeCode="<%=DicConstants.YHRS0074%>" />
							</label>
						</dd>
					</dl>
					<dl class="updown-dl">
						<dt>离党日期：</dt>
						<dd>
							<label><input type="text" id="outPartyDateStr"
								name="outPartyDateStr" class="modal_iput"
								value="${pbPoliticInfoForm.outPartyDateStr}" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>异常事项类别：</dt>
						<dd>
							<label><dictionary:dicItemSelect id="abnormityType"
									name="abnormityType" styleClass="modal_select"
									emptyText="<%=Constant.EMPTY_SELECT %>"
									selected="${pbPoliticInfoForm.abnormityType}"
									dicTypeCode="<%=DicConstants.YHRS0075%>" />
							</label>
						</dd>
					</dl>
          <dl class="updown-dl">
						<dt>异常事项原因：</dt>
						<dd>
							<label><input type="text" id="abnormityReason"
								name="abnormityReason" class="modal_iput"
								value="${pbPoliticInfoForm.abnormityReason}" maxlength="1000" />
							</label>
						</dd>
					</dl>
					<dl class="updown-remark">
						<dt>备注：</dt>
						<dd>
							<label><textarea id="remark" name="remark" maxlength="1000">${pbPoliticInfoForm.remark}</textarea>
							</label>
						</dd>
					</dl>
          <div class="clear"></div>
			  </div>
      </div>
    </div>
    <div class="md-btn">
        <button id="savePolitic" type="button" class="md-btn-save">保  存</button>
        <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
    </div>
  </div>
</form>
</body>
</html>
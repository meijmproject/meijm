<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />

<script type="text/javascript"
	src="hrinfo/ver/unit/comm/pbproftech/js/check_pbproftech_info.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
<title>专业技术资格信息</title>
</head>
<body>
	<div id="showmodal" class="modal-content">
<form id="formVerProfTech"
				action="updateVerProfTechInfo.do?method=update" method="post"
				class="form-inline">
				<div class="modal-header">
					<button type="button" class="close close-popdown"
						data-dismiss="modal" aria-label="Close" button-click="widget-close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">专业技术资格信息&gt;修改</h4>
				</div>
		<div class="modal-body">
				<div class="modal-wrong"  style="display: none;">
            <ol class="titwrong"><embed src="img/index/jg.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/>以下红框信息有误，请重新输入！</ol>
            <ol class="wroglist">
               <logic:messagesPresent>
                        <ul>
                            <html:messages id="error">
                                <li><bean:write name="error" /></li>
                            </html:messages>
                        </ul>
                    </logic:messagesPresent>
            </ol>

        </div>

				<input type="hidden" id="profTechId" value="${id}"> <input
					type="hidden" id="u_profTechOid" name="profTechOid"
					value="${verProfTechInfoForm.profTechOid}"> <input
					type="hidden" id="profTechPersonOid" name="personOid"
					value="${verProfTechInfoForm.personOid}">
				<div class="modal-row03">
					<dl>
						<dt title="专业技术资格名称">
							<b class="Required">* </b>专业技术资格名称：
						</dt>
						<dd>
							<input type="text" name="profTechNameId" id="profTechName"
								class="modal_iput" onclick="min_Dic.min_Dictionary(this,'YHRS0030')"
								value="<dictionary:viewDicItemName dicItemCode='${verProfTechInfoForm.profTechName}' dicTypeCode='<%=DicConstants.YHRS0030%>'/>"
								readonly="readonly" /> 
								<html:hidden name="verProfTechInfoForm" property="profTechName"></html:hidden>
								<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
						</dd>
						<dt title="专业技术资格等级">
							<b class="Required">* </b>专业技术资格等级：
						</dt>
						<dd>
							<dictionary:dicItemSelect style="width:180;" id="u_profTechLevel"
								name="profTechLevel"
								selected="${verProfTechInfoForm.profTechLevel}"
								dicTypeCode="<%=DicConstants.YHRS0047%>"
								styleClass="modal_select" emptyText="--请选择--" />
						</dd>
						<dt title="专业名称">专业名称：</dt>
						<dd>
							<input type="text" id="u_specialityName" name="specialityName"
								value="${verProfTechInfoForm.specialityName}" class="modal_iput"
								maxlength="100" />
						</dd>
					</dl>
				</div>
				<div class="modal-row03">
					<dl>

						<dt title="取得资格日期">取得资格日期：</dt>
						<dd>
							<input type="text" id="u_profTechProcureDateStr"
								name="procureDateStr"
								value="${verProfTechInfoForm.procureDateStr}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								class="modal_iput" />
						</dd>
						<%-- <dt title="有效期">有效期：</dt>
						<dd>
							<input type="text" id="u_profTechValidityDateStr"
								name="validityDateStr"
								value="${verProfTechInfoForm.validityDateStr}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								class="modal_iput" />
						</dd> --%>
						<dt title="取得资格途径">取得资格途径：</dt>
						<dd>
							<dictionary:dicItemSelect style="width:180;"
								id="u_profTechAcquireApproachCode" name="acquireApproachCode"
								selected="${verProfTechInfoForm.acquireApproachCode}"
								dicTypeCode="<%=DicConstants.YHRS0048%>"
								styleClass="modal_select" emptyText="--请选择--" />
						</dd>
					</dl>
				</div>
				<div class="modal-row03">
					<dl>
					<dt title="其他取得途径">其他取得途径：</dt>
						<dd>
							<input type="text" id="u_profTechAcquireApproachOther"
								name="acquireApproachOther"
								value="${verProfTechInfoForm.acquireApproachOther}"
								class="modal_iput" maxlength="100" />
						</dd>
						<dt title="发证机构名称">发证机构名称：</dt>
						<dd>
							<input type="text" id="u_profTechIssueOrgan" name="issueOrgan"
								value="${verProfTechInfoForm.issueOrgan}" class="modal_iput"
								maxlength="200" />
						</dd>
						<dt title="是否经过本省/市人力资源（人事）科室核准或认定">是否经过本省/市人力资源（人事）科室核准或认定：</dt>
						<dd>
							<dictionary:dicItemSelect style="width:180;" id="u_isApprove"
								name="isApprove" selected="${verProfTechInfoForm.isApprove}"
								dicTypeCode="<%=DicConstants.YHRS0003%>"
								styleClass="modal_select" emptyText="--请选择--" />
						</dd>
					</dl>
				</div>
				<div class="modal-row03">
				<dl>
				<dt title="资格审批机构名称">资格审批机构名称：</dt>
						<dd>
							<input type="text" id="u_approveOrganName"
								name="approveOrganName"
								value="${verProfTechInfoForm.approveOrganName}"
								class="modal_iput" maxlength="100" />
						</dd>
				</dl>
				</div>
		</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default close-popdown"
						data-dismiss="modal" button-click="widget-close">取 消</button>
				</div>
				<div style="clear: both"></div>
			</form>
	</div>
</body>
</html>

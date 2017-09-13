<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员离开信息新增页面
 * @page name   pbPersonOutCreate.jsp
 * @author      liupt
 * @created     2017/04/7
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员离开信息新增页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbpersonout/js/checkPersonOut.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
	<script type="text/javascript" src="js/comm/common_validator.js"></script>
</head>
<body>
<html:form styleId="pbPersonOutForm" styleClass="form-inline" action="insertPbPbPersonOut.do?method=insert" method="post">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">人员离开信息&gt;新增<a href="#" class="md-close close-popdown"></a></div>
      <div class="md-error" style="display: none">
        <p>以下信息有误,请重新输入</p>
      </div>
      <div class="md-main-content">
				<input type="hidden" id="personOid" name="personOid" value="${personOid}" />
				<input type="hidden" id="url_id" value="${urlId}" />
        <div class="md-units md-three-column clearfix">
					<dl class="updown-dl">
						<dt>
							<b class="Required">* </b>减员类型：
						</dt>
						<dd>
							<label><dictionary:dicItemSelect id="personOutType"
									name="personOutType" styleClass="modal_select"
									emptyText="<%=Constant.EMPTY_SELECT %>"
									selected="${pbPersonOutForm.personOutType}"
									dicTypeCode="<%=DicConstants.YHRS0128%>" /> </label>
						</dd>
					</dl>
					<dl class="updown-dl">
						<dt>
							<b class="Required">* </b>离开日期：
						</dt>
						<dd>
							<label><input type="text" id="outDateStr"
								name="outDateStr" class="modal_iput"
								value="${pbPersonOutForm.outDateStr}" readonly="true"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
						</dd>
					</dl>
					<dl class="updown-dl">
						<dt>离开后去向：</dt>
						<dd>
							<label><input type="text" id="personOutTo"
								name="personOutTo" class="modal_iput"
								value="${pbPersonOutForm.personOutTo}"
								onblur="validateIdNo(this)" maxlength="20" /> </label>
						</dd>
					</dl>
					<dl class="updown-remark">
						<dt>备注：</dt>
						<dd>
							<label><textarea id="remark" name="remark" rows="2" maxlength="1000">${pbPersonOutForm.remark}</textarea> </label>
						</dd>
					</dl>
				</div>
				<div class="md-btn">
          <button id="savePersonOut" type="button" class="md-btn-save">保  存</button>
          <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
        </div>
      </div>
    </div>
  </div>
</html:form>
</body>
</html>
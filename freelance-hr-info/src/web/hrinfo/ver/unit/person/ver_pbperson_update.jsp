<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    人员基础信息修改页面
 * @page name   /freelance-hr-info/src/web/hrinfo/ver/unit/jg/verjgpbperson/verjg_pbperson_update.jsp
 * @author      luqy
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>人员修改页面</title>

<script type="text/javascript" src="hrworktop/flow/selectDept.js"></script>
<script src="hrinfo/ver/unit/person/js/VerPbPersonOperate.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript" src="js/comm/common_validator.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	selectDept.selectDept_Unit("deptOid","deptAllName",${verPbPersonInfoForm.unitOid});
	selectDept.selectHireDept_Unit("hireDeptOid","hireDeptAllName",${verPbPersonInfoForm.unitOid});
});
</script>
</head>
<body>

	<html:form styleId="formPersonUpdate" styleClass="form-inline" action="updateVerPersonInfo.do?method=updateVerPersonInfo" enctype="multipart/form-data" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					人员信息校核 &gt;修改<a href="#" class="md-close close-popdown"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="url_id" value="${id }" /> 
					<input type="hidden" id="url_personoid" value="${personOid }" /> 
					<input type="hidden" id="imageflag" value="${imageflag }" />
					<html:hidden name="verPbPersonInfoForm" property="personOid" />

					<jsp:include page="/hrinfo/ver/unit/person/ver_pbperson_body.jsp"></jsp:include>

				</div>
				<div class="md-btn">
					<button id="savePersonInfo" class="md-btn-save">保  存</button>
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
				</div>
			</div>
		</div>
	</html:form>
</body>
</html>
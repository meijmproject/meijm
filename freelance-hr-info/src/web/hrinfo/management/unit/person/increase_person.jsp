<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    人员信息管理增员页面
 * @page name   /hrinfo/management/unit/person/increase_person.jsp
 * @author      duxw
 * @created     2017/03/22
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>增员页面</title>
<script type="text/javascript" src="hrworktop/flow/selectDept.js"></script>
<script type="text/javascript" src="hrinfo/management/unit/person/js/check_increase_person.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript" src="js/comm/common_validator.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		selectDept.selectDept_Unit("deptOid","deptAllName",${increasePersonForm.unitOid});
		selectDept.selectHireDept_Unit("hireDeptOid","hireDeptAllName",${increasePersonForm.unitOid});
	});
</script>
</head>
<body>
	<html:form styleId="increasePersonForm" styleClass="form-inline" action="increasePerson.do?method=create" enctype="multipart/form-data" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					人员信息管理 &gt;增员<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="url_id" value="${id}" /> 
					<input type="hidden" id="url_personoid" value="${personOid}" /> 
					<input type="hidden" id="personOid" name="personOid" value="${personOid}" /> 
					<input type="hidden" id="contractNoFlag" name="contractNoFlag" /> 
					<input type="hidden" id="statusFlag" name="statusFlag" /> 
					<input type="hidden" id="checkProbationDateStr" name="checkProbationDateStr" /> 
					<input type="hidden" name="unitName" value="${increasePersonForm.unitName }" /> 
					<input type="hidden" name="unitOid" value="${increasePersonForm.unitOid }" /> 
					<input type="hidden" name="unitMaster" value="${increasePersonForm.unitMaster }" /> 
					<input type="hidden" name="unitAddress" value="${increasePersonForm.unitAddress }" /> 
					<input type="hidden" name="unitPhone" value="${increasePersonForm.unitPhone }" />
					
					<div class="md-units md-three-column clearfix">
						<jsp:include page="/hrinfo/management/unit/person/increase_person_body.jsp"></jsp:include>
					</div>
					
				</div>
				<div class="md-btn">
					<button class="md-btn-save close-popdown">保存</button>
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
				</div>
			</div>
		</div>
	</html:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!--
 * @function    人员信息管理减员 - 离退休页面
 * @page name   /hrinfo/management/unit/person/reduce_person_retire.jsp
 * @author      duxw
 * @created     2017/03/25
 * @version     1.0
-->

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员信息管理-离退休页面</title>
<script type="text/javascript" src="hrinfo/management/unit/person/js/check_reduce_person_retire.js"></script>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript" src="js/comm/common_validator.js"></script>
</head>
<body>
	<form id="retrieInfoForm" class="form-inline" action="createRetireInfo.do?method=createRetireInfo" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					人员信息管理&gt;离退休<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<input type="hidden" id="personOid" name="personOid" value="${personOid}" /> <input type="hidden" id="url_id" value="${urlId}" />
					
					
					<div class="infoshow-container md-infoshow-area">
						<div class="isc-title-box">
							<h3 class="md-isc-title">人员基本信息</h3>
						</div>
						<div class="md-infoshow-content">
							<jsp:include page="/hrinfo/management/comm/basic_person_info_body.jsp"></jsp:include>
						</div>
						<div class="infoshow-container">
							<div class="isc-title-box">
								<h3 class="md-isc-title">人员离退休信息</h3>
							</div>
							<div class="md-infoshow-content">
							<jsp:include page="/hrinfo/ver/unit/comm/pbretrieinfo/ver_pbretrieinfo_body.jsp"></jsp:include>
						</div>
						</div>
					</div>
					
					<%-- <div class="md-units md-three-column clearfix">
						<h3 class="st-title-text">人员基本信息</h3>
						<jsp:include page="/hrinfo/management/comm/basic_person_info_body.jsp"></jsp:include>
						<h3 class="st-title-text">人员离退休信息</h3>
						<jsp:include page="/hrinfo/ver/unit/comm/pbretrieinfo/ver_pbretrieinfo_body.jsp"></jsp:include>

					</div> --%>
					
				</div>
				<div class="md-btn">
					<button id="saveRetrieInfo" type="button" class="md-btn-save">保 存</button>
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员进入信息新增页面
 * @page name   verpb_familyinfo_create.jsp
 * @author      chenp
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员进入信息新增页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbpersonin/js/checkPersonIn.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
	<script type="text/javascript" src="js/comm/common_validator.js"></script>
</head>
<body>
<form id="pbPersonInForm" class="form-inline" action="insertPbPbPersonIn.do?method=insert" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">人员进入信息&gt;新增<a href="#" class="md-close close-popdown"></a></div>
      <div class="md-error" style="display: none">
        <p>以下信息有误,请重新输入</p>
      </div>
      <div class="md-main-content">
        <input type="hidden" id="personOid" name="personOid" value="${personOid}" />
        <input type="hidden" id="url_id" value="${urlId}" />
        <jsp:include page="/hrinfo/ver/unit/comm/pbpersonin/pbPersonInShow.jsp"></jsp:include>
		    <div class="md-btn">
		      <button id="savePersonIn" type="button" class="md-btn-save">保  存</button>
		      <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
		    </div>
	    </div>
	  </div>
	</div>
</form>
</body>
</html>
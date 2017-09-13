<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭主要成员情况修改页面
 * @page name   /hrinfo/ver/unit/comm/pbfamilyinfo/verpb_familyinfo_update.jsp
 * @author      huangyj
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>家庭主要成员情况修改页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbfamilyinfo/js/check_family_info.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
    <script type="text/javascript" src="js/comm/common_validator.js"></script>
</head>

<body>

<form id="familyInfoForm" class="form-inline" action="updatePbFamilyInfo.do?method=update" method="post" onsubmit="return false">
<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
                             家庭主要成员情况&gt;修改
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
        <div class="md-main-content">
        <input type="hidden" id="familyOid" name="familyOid" value="${verPbFamilyInfoForm.familyOid}" />
	        <input type="hidden" id="personOid" name="personOid" value="${verPbFamilyInfoForm.personOid}" />
	        <input type="hidden" id="url_id" value="${urlId}" />

			    <jsp:include page="/hrinfo/ver/unit/comm/pbfamilyinfo/verpb_familyinfo_body.jsp"></jsp:include>    	
    	</div>
    
     </div>
     <div class="modal-footer">
    	<button id="saveFamily" type="button" class="btn btn-primary">保　存</button>
    	<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
    </div>
   </div>
    <div style="clear: both"></div>
    
</form>
</body>
</html>
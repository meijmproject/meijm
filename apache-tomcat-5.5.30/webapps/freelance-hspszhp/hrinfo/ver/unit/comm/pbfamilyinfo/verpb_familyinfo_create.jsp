<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭主要成员情况新增页面
 * @page name   verpb_familyinfo_create.jsp
 * @author      chenp
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>家庭主要成员情况新增页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbfamilyinfo/js/check_family_info.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
	<script type="text/javascript" src="js/comm/common_validator.js"></script>
</head>
<body>
<form id="familyInfoForm" class="form-inline" action="insertPbFamilyInfo.do?method=insert" method="post" onsubmit="return false">
	<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
                             家庭主要成员情况&gt;新增
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
        <div class="md-main-content">
	        <input type="hidden" id="personOid" name="personOid" value="${personOid}" />
	        <input type="hidden" id="url_id" value="${urlId}" />

	        <jsp:include page="/hrinfo/ver/unit/comm/pbfamilyinfo/verpb_familyinfo_body.jsp"></jsp:include>    	
          
          
        </div>
        <div class="md-btn">
            <button id="saveFamily" type="button" class="md-btn-save">保  存</button>
            <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal">取 消</button>
          </div>
    </div>
	</div>
</form>
<%-- <div id="showmodal" class="modal-content">
<form id="familyInfoForm" class="form-inline" action="insertPbFamilyInfo.do?method=insert" method="post" onsubmit="return false">

    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">家庭主要成员情况&gt;新增</h4>
    </div>

	<div class="modal-body">

        	<input type="hidden" id="personOid" name="personOid" value="${personOid}" />
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
    <jsp:include page="/hrinfo/ver/unit/comm/pbfamilyinfo/verpb_familyinfo_body.jsp"></jsp:include>    	
    </div>
   
    <div class="modal-footer">
    	<button id="saveFamily" type="button" class="btn btn-primary">保　存</button>
    	<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
    </div>
    
    <div style="clear: both"></div>
    
</form>
</div> --%>
</body>
</html>
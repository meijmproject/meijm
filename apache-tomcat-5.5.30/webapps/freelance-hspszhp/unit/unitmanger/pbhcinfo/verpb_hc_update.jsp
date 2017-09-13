<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>语言能力信息修改页面</title>
<script type="text/javascript" src="unit/unitmanger/pbhcinfo/check_hc_info.js"></script>
    <script type="text/javascript" src="js/comm/customJs.js"></script>
</head>
<body>

<div id="showmodal" class="modal-content">
    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">编制信息&gt;修改</h4>
    </div>
    <div class="modal-body">
        <div  id="query-condition">
        	<!-- <form id="verUbHcInfoForm" class="form-inline" action="updateVerUbHcInfo.do?method=updateVerUbHcInfo" method="post" onsubmit="return false"> -->
        	<html:form styleId="verUbHcInfoForm" action="updateVerUbHcInfo.do?method=updateVerUbHcInfo" method="post" onsubmit="return false">
            <html:hidden name="verUbHcInfoForm" property="unitOid"/>
            <html:hidden name="verUbHcInfoForm" property="hcOid"/>
            <input type="hidden"  id="url_id" value="${urlId}" />
        	<div class="wrong" style="display:none;"><embed src="img/index/jg.svg" width="20" height="20" top="-5" type="image/svg+xml"/>
        	</div>
        	<div class="modal-row03">   
        			<dl>   
						<dt>编制类型：</dt>
	                	<dd>
	                		<label>
	                		<html:text name="verUbHcInfoForm" property="hcName" styleClass="modal_iput readonly" readonly="true" /> </label>
	                    </dd>
	                    <dt>经费形式：</dt>
	                	<dd>
	                		<label><html:text name="verUbHcInfoForm" property="budgetFromName" styleClass="modal_iput readonly" readonly="true" /> </label>
	                    </dd>
	                    <dt>变动前数量：</dt>
	                	<dd>
	                		<label><html:text name="verUbHcInfoForm" property="preCount" styleClass="modal_iput readonly" readonly="true" /> </label>
	                    </dd>
                    </dl>
        	</div>
        	
        	<div class="modal-row03">   <!--两列 -->
        			<dl>
	                	<dt><b class="Required">* </b>变动数量：</dt>
	                	<dd>
	                		<label><html:text name="verUbHcInfoForm" property="chgCount" styleClass="modal_iput" /> </label>
	                    </dd>
	                    <dt>变动后数量：</dt>
	                	<dd>
	                		<label><html:text name="verUbHcInfoForm" property="curCount" styleClass="modal_iput readonly" readonly="true" /> </label>
	                    </dd>
	            	</dl>
        	</div>
        	
            <div style="clear: both"></div>
        </html:form>
    	</div>
    </div>

    <div class="modal-footer">
        <button id="saveVerUbHcInfo" type="button" class="btn btn-primary">修　改</button>
        <button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
    </div>
</div>
</body>
</html>
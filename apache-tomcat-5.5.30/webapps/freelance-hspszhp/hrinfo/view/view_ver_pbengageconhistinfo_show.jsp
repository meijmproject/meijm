<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.yh.platform.core.constant.Constant" %>
<%@ page import="com.yh.hr.res.dictionary.DicConstants" %>
<%@ include file="/include/jsp_headers.jsp" %>

<!--
* @function 合同变动历史信息查看页面
* @page name /hrinfo/view/view_ver_pbengageconhistinfo_show.jsp
* @Created 2017-02-14
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合同变动历史信息查看页面</title>
    <style type="text/css">
        dd {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>

<body>

<%-- <div id="showmodal" class="modal-content">

    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">合同变动历史信息&gt;查看</h4>
    </div>
    
    <form id="viewJhcPbEngageConHistInfo" action="#" method="post">
    <div class="wrong" style="display:none;"><embed src="img/index/jg.svg" width="20" height="20" top="-5" type="image/svg+xml"/></div>
	<div class="sys_list">
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同编号：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contractNo" />'><bean:write name="engageConHistInfoForm" property="contractNo" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同开始时间：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contractBeginStr" />'><bean:write name="engageConHistInfoForm" property="contractBeginStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同签订时间：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="signDateStr" />'><bean:write name="engageConHistInfoForm" property="signDateStr" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>常住住址：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="addressPermanant" />'><bean:write name="engageConHistInfoForm" property="addressPermanant" />&nbsp;</label>
				</dd>
			</dl>
		</div>		
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同签订方式：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageConHistInfoForm.contractType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageConHistInfoForm.contractType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同截止时间：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contractEndStr" />'><bean:write name="engageConHistInfoForm" property="contractEndStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>工作岗位：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contractJob" />'><bean:write name="engageConHistInfoForm" property="contractJob" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>通信地址：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="addressCommunication" />'><bean:write name="engageConHistInfoForm" property="addressCommunication" />&nbsp;</label>
				</dd>
			</dl>
	        
		</div>		
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同类型：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageConHistInfoForm.changeType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageConHistInfoForm.changeType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同实际结束日期：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contractEndActualStr" />'><bean:write name="engageConHistInfoForm" property="contractEndActualStr" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>联系电话：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="contactPhone" />'><bean:write name="engageConHistInfoForm" property="contactPhone" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>其它事项：</dt>
				<dd>
					<label title='<bean:write name="engageConHistInfoForm" property="others" />'><bean:write name="engageConHistInfoForm" property="others" />&nbsp;</label>
				</dd>
			</dl>
	        
		</div>		
    </div>
    <div style="clear: both"></div>
    </form>
    
    <div class="modal-footer">
        <button type="button" class="btn btn-primary close-popdown">返　回</button>
   	</div>
   	
</div> --%>

<form id="viewJhcPbEngageConHistInfo" action="#" method="post">
    <div id="transaction_modal_box" class="transaction_modal_box">
        <div class="modal-dialog-container">
            <div class="md-title">
                合同历史信息&gt;查看
                <a href="#" class="md-close close-popdown" button-click="widget-close"></a>
            </div>
            <div class="md-main-content">
                <div class="infoshow-container md-infoshow-area">
                        <dl class="data-unit-dl">
                        <dt>合同编号：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contractNo" />'><bean:write
                                    name="engageConHistInfoForm" property="contractNo"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>合同开始时间：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contractBeginStr" />'><bean:write
                                    name="engageConHistInfoForm" property="contractBeginStr"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>合同签订时间：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="signDateStr" />'><bean:write
                                    name="engageConHistInfoForm" property="signDateStr"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>常住住址：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="addressPermanant" />'><bean:write
                                    name="engageConHistInfoForm" property="addressPermanant"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>合同签订方式：</dt>
                        <dd>
                            <label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageConHistInfoForm.contractType}" />'><dictionary:viewDicItemName
                                    dicTypeCode="<%=DicConstants.YHRS0114%>"
                                    dicItemCode="${engageConHistInfoForm.contractType}"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>合同截止时间：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contractEndStr" />'><bean:write
                                    name="engageConHistInfoForm" property="contractEndStr"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>工作岗位：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contractJob" />'><bean:write
                                    name="engageConHistInfoForm" property="contractJob"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>通信地址：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="addressCommunication" />'><bean:write
                                    name="engageConHistInfoForm" property="addressCommunication"/>&nbsp;</label>
                        </dd>
                    </dl>

                    <dl class="data-unit-dl">
                        <dt>合同类型：</dt>
                        <dd>
                            <label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageConHistInfoForm.changeType}" />'><dictionary:viewDicItemName
                                    dicTypeCode="<%=DicConstants.YHRS0115%>"
                                    dicItemCode="${engageConHistInfoForm.changeType}"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>合同实际结束日期：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contractEndActualStr" />'><bean:write
                                    name="engageConHistInfoForm" property="contractEndActualStr"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>联系电话：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="contactPhone" />'><bean:write
                                    name="engageConHistInfoForm" property="contactPhone"/>&nbsp;</label>
                        </dd>
                    </dl>
                    <dl class="data-unit-dl">
                        <dt>其它事项：</dt>
                        <dd>
                            <label title='<bean:write name="engageConHistInfoForm" property="others" />'><bean:write
                                    name="engageConHistInfoForm" property="others"/>&nbsp;</label>
                        </dd>
                    </dl>
            </div>
            </div>
            <div class="md-btn">
                <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关闭</button>
            </div>
        </div>
    </div>

</form>
</body>
</html>
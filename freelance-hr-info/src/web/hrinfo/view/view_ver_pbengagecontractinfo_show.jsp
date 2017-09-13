<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    合同信息查看页面
 * @page name   /hrinfo/view/view_ver_pbengagecontractinfo_show.jsp
 * @Created   2017-02-14
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合同信息查看页面</title>
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
        <h4 class="modal-title">合同信息&gt;查看</h4>
    </div>
    
    <form id="viewJhcPbEngageContractInfo" action="#" method="post">
    <div class="wrong" style="display:none;"><embed src="img/index/jg.svg" width="20" height="20" top="-5" type="image/svg+xml"/></div>
	<div class="sys_list">
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同编号：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractNo" />'><bean:write name="engageContractInfoForm" property="contractNo" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同开始时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractBeginStr" />'><bean:write name="engageContractInfoForm" property="contractBeginStr" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>是否存在试用期：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${engageContractInfoForm.probationFlag}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${engageContractInfoForm.probationFlag}" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>合同状态：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${engageContractInfoForm.status}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${engageContractInfoForm.status}"/>&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>常住住址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="addressPermanant" />'><bean:write name="engageContractInfoForm" property="addressPermanant" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>用人单位：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitName" />'><bean:write name="engageContractInfoForm" property="unitName" />&nbsp;</label>
				</dd>
			</dl>
	       <dl class="dl-horizontal">   
	                	<dt>用人单位联系电话：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitPhone" />'><bean:write name="engageContractInfoForm" property="unitPhone" />&nbsp;</label>
				</dd>
			</dl>
		</div>		
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同签订方式：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageContractInfoForm.contractType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageContractInfoForm.contractType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同截止时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractEndStr" />'><bean:write name="engageContractInfoForm" property="contractEndStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>试用期开始日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="probationBeginStr" />'><bean:write name="engageContractInfoForm" property="probationBeginStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同签订时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="signDateStr" />'><bean:write name="engageContractInfoForm" property="signDateStr" />&nbsp;</label>
				</dd>
			</dl>
	         <dl class="dl-horizontal">   
	                	<dt>通信地址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="addressCommunication" />'><bean:write name="engageContractInfoForm" property="addressCommunication" />&nbsp;</label>
				</dd>
			</dl>
			 <dl class="dl-horizontal">   
	                	<dt>用人单位主要负责人：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitMaster" />'><bean:write name="engageContractInfoForm" property="unitMaster" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>其它事项：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="others" />'><bean:write name="engageContractInfoForm" property="others" />&nbsp;</label>
				</dd>
			</dl>
		</div>		
	    <div class="col_float">
	        <dl class="dl-horizontal">   
	                	<dt>合同类型：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageContractInfoForm.changeType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageContractInfoForm.changeType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>合同实际结束日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractEndActualStr" />'><bean:write name="engageContractInfoForm" property="contractEndActualStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>试用期结束日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="probationEndStr" />'><bean:write name="engageContractInfoForm" property="probationEndStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>工作岗位：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractJob" />'><bean:write name="engageContractInfoForm" property="contractJob" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="dl-horizontal">   
	                	<dt>用人单位地址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitAddress" />'><bean:write name="engageContractInfoForm" property="unitAddress" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="dl-horizontal">   
	                	<dt>联系电话：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contactPhone" />'><bean:write name="engageContractInfoForm" property="contactPhone" />&nbsp;</label>
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
<form id="viewJhcPbEngageContractInfo" action="#" method="post">
	<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
                         合同信息&gt;查看
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-main-content">
	      <div class="md-units md-three-column clearfix">
                        <dl class="updown-dl">  
	                	<dt>合同编号：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractNo" />'><bean:write name="engageContractInfoForm" property="contractNo" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>合同签订方式：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageContractInfoForm.contractType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0114%>" dicItemCode="${engageContractInfoForm.contractType}" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>合同类型：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageContractInfoForm.changeType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0115%>" dicItemCode="${engageContractInfoForm.changeType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>合同开始时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractBeginStr" />'><bean:write name="engageContractInfoForm" property="contractBeginStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>合同截止时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractEndStr" />'><bean:write name="engageContractInfoForm" property="contractEndStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>合同实际结束日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractEndActualStr" />'><bean:write name="engageContractInfoForm" property="contractEndActualStr" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>合同状态：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${engageContractInfoForm.status}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0116%>" dicItemCode="${engageContractInfoForm.status}"/>&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>是否存在试用期：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${engageContractInfoForm.probationFlag}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${engageContractInfoForm.probationFlag}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>试用期开始日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="probationBeginStr" />'><bean:write name="engageContractInfoForm" property="probationBeginStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>试用期结束日期：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="probationEndStr" />'><bean:write name="engageContractInfoForm" property="probationEndStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>合同签订时间：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="signDateStr" />'><bean:write name="engageContractInfoForm" property="signDateStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>工作岗位：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contractJob" />'><bean:write name="engageContractInfoForm" property="contractJob" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>常住住址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="addressPermanant" />'><bean:write name="engageContractInfoForm" property="addressPermanant" />&nbsp;</label>
				</dd>
			</dl>
	         <dl class="updown-dl">  
	                	<dt>通信地址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="addressCommunication" />'><bean:write name="engageContractInfoForm" property="addressCommunication" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="updown-dl">  
	                	<dt>联系电话：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="contactPhone" />'><bean:write name="engageContractInfoForm" property="contactPhone" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>用人单位：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitName" />'><bean:write name="engageContractInfoForm" property="unitName" />&nbsp;</label>
				</dd>
			</dl>
			 <dl class="updown-dl">  
	                	<dt>用人单位主要负责人：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitMaster" />'><bean:write name="engageContractInfoForm" property="unitMaster" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>用人单位地址：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitAddress" />'><bean:write name="engageContractInfoForm" property="unitAddress" />&nbsp;</label>
				</dd>
			</dl>
	       <dl class="updown-dl">  
	                	<dt>用人单位联系电话：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="unitPhone" />'><bean:write name="engageContractInfoForm" property="unitPhone" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="updown-dl">  
	                	<dt>其它事项：</dt>
				<dd>
					<label title='<bean:write name="engageContractInfoForm" property="others" />'><bean:write name="engageContractInfoForm" property="others" />&nbsp;</label>
				</dd>
			</dl>
	      </div>
        </div>
        <div class="md-btn">
					<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关 闭</button>
				</div>
    </div>
	</div>
</form>
</body>
</html>
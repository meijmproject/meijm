<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    事业岗位聘任信息查看页面
 * @page name   /hrinfo/view/view_ver_pbsygwemployinfo_show.jsp
 * @Created   2017-02-14
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>事业岗位聘任信息查看页面</title>
    <style type="text/css">
		dd {
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
	</style>
</head>

<body>
 <form id="viewJhcPbSyGwEmployInfo" action="#" method="post">
	<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
                          事业岗位聘任信息&gt;查看
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-main-content">
	      <div class="md-units md-three-column clearfix">
	       <dl class="data-unit-dl">
	                	<dt>岗位聘任状态：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbSyGwEmployInfoForm.positioningStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbSyGwEmployInfoForm.positioningStatus}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>聘任单位名称：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="dutyUnitName" />'><bean:write name="pbSyGwEmployInfoForm" property="dutyUnitName" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="data-unit-dl">
	                	<dt>内设机构名称：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="deptName" />'><bean:write name="pbSyGwEmployInfoForm" property="deptName" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="data-unit-dl">
	                	<dt>岗位名称：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="positionName" />'><bean:write name="pbSyGwEmployInfoForm" property="positionName" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>岗位类别：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${pbSyGwEmployInfoForm.positionType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${pbSyGwEmployInfoForm.positionType}" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="data-unit-dl">
	                	<dt>岗位级别：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${pbSyGwEmployInfoForm.positionLevel}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${pbSyGwEmployInfoForm.positionLevel}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>职务类型：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0036%>" dicItemCode="${pbSyGwEmployInfoForm.dutyRecordType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0036%>" dicItemCode="${pbSyGwEmployInfoForm.dutyRecordType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>是否主岗位：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbSyGwEmployInfoForm.isMPosition}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${pbSyGwEmployInfoForm.isMPosition}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>岗位聘任开始时间：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="beginDateStr" />'><bean:write name="pbSyGwEmployInfoForm" property="beginDateStr" />&nbsp;</label>
				</dd>
			</dl>
	        <dl class="data-unit-dl">
	                	<dt>岗位聘任拟截止时间：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="endDateStr" />'><bean:write name="pbSyGwEmployInfoForm" property="endDateStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>岗位聘任实际截止时间：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="endDateActualStr" />'><bean:write name="pbSyGwEmployInfoForm" property="endDateActualStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl">
	                	<dt>岗位属性：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0028%>" dicItemCode="${pbSyGwEmployInfoForm.dutyAttribute}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0028%>" dicItemCode="${pbSyGwEmployInfoForm.dutyAttribute}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl remark-unit">
	                	<dt>备注：</dt>
				<dd>
					<label title='<bean:write name="pbSyGwEmployInfoForm" property="remark" />'><bean:write name="pbSyGwEmployInfoForm" property="remark" />&nbsp;</label>
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
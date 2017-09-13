<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    院内岗位聘任信息查看页面
 * @page name   /hrinfo/view/view_ver_pbyngwemployinfo_show.jsp
 * @Created   2017-02-14
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>院内岗位聘任信息查看页面</title>
    <style type="text/css">
		dd {
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
	</style>
</head>

<body>
<form id="viewJhcPbYnGwEmployInfo" action="#" method="post">
	<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
                          院内岗位聘任信息&gt;查看
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-main-content">
	      <div class="md-units md-three-column clearfix">
	      <dl class="data-unit-dl">
	                	<dt>岗位聘任状态：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbYnGwEmployInfoForm.hisPositionStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0026%>" dicItemCode="${pbYnGwEmployInfoForm.hisPositionStatus}" />&nbsp;</label>
				</dd>
			</dl>
	       <dl class="data-unit-dl">
	                	<dt>工作类别：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0112%>" dicItemCode="${pbYnGwEmployInfoForm.hisWorkType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0112%>" dicItemCode="${pbYnGwEmployInfoForm.hisWorkType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl"> 
	                	<dt>岗位聘任开始时间：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="hisBeginDateStr" />'><bean:write name="pbYnGwEmployInfoForm" property="hisBeginDateStr" />&nbsp;</label>
				</dd>
			</dl>
			
	       <dl class="data-unit-dl">
	                	<dt>聘任内设机构名称：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="deptName" />'><bean:write name="pbYnGwEmployInfoForm" property="deptName" />&nbsp;</label>
				</dd>
			</dl>
	       <dl class="data-unit-dl">
	                	<dt>岗位类别：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="YHRS0113" dicItemCode="${pbYnGwEmployInfoForm.hisPositionType}" />'><dictionary:viewDicItemName dicTypeCode="YHRS0113" dicItemCode="${pbYnGwEmployInfoForm.hisPositionType}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl"> 
	                	<dt>岗位聘任拟截止时间：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="endDateStr" />'><bean:write name="pbYnGwEmployInfoForm" property="endDateStr" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl"> 
	                	<dt>岗位名称：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="hisPositionName" />'><bean:write name="pbYnGwEmployInfoForm" property="hisPositionName" />&nbsp;</label>
				</dd>
			</dl>
	       <dl class="data-unit-dl">
	                	<dt>岗位级别：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="YHRS0124" dicItemCode="${pbYnGwEmployInfoForm.hisPositionLevel}" />'><dictionary:viewDicItemName dicTypeCode="YHRS0124" dicItemCode="${pbYnGwEmployInfoForm.hisPositionLevel}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl"> 
	                	<dt>职务属性：</dt>
				<dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="YHRS0028" dicItemCode="${pbYnGwEmployInfoForm.hisDutyAttribute}" />'><dictionary:viewDicItemName dicTypeCode="YHRS0028" dicItemCode="${pbYnGwEmployInfoForm.hisDutyAttribute}" />&nbsp;</label>
				</dd>
			</dl>
			<dl class="data-unit-dl"> 
	                	<dt>岗位聘任实际截止时间：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="endDateActualStr" />'><bean:write name="pbYnGwEmployInfoForm" property="endDateActualStr" />&nbsp;</label>
				</dd>
			</dl>
			
			<dl class="data-unit-dl">
	                     <dt>是否兼职：</dt>
	            <dd>
					<label title='<dictionary:viewDicItemName dicTypeCode="YHRS0003" dicItemCode="${pbYnGwEmployInfoForm.isPartTime}" />'><dictionary:viewDicItemName dicTypeCode="YHRS0003" dicItemCode="${pbYnGwEmployInfoForm.isPartTime}" />&nbsp;</label>
				</dd>
                 </dl>
		     <dl class="data-unit-dl">
	                	<dt>所聘职称：</dt>
				<label title='<bean:write name="pbYnGwEmployInfoForm" property="appointProfTitleName" />'><bean:write name="pbYnGwEmployInfoForm" property="appointProfTitleName" />&nbsp;</label>
			</dl>
			
			
			
			<dl class="data-unit-dl"> 
	                	<dt>备注：</dt>
				<dd>
					<label title='<bean:write name="pbYnGwEmployInfoForm" property="remark" />'><bean:write name="pbYnGwEmployInfoForm" property="remark" />&nbsp;</label>
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
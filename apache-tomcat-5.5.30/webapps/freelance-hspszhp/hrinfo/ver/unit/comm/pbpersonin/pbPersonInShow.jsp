<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员进入信息
 * @page name   pbPersonInShow.jsp
 * @author      cheny
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员进入信息</title>
</head>
<body>
<div class="md-units md-three-column clearfix">
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>进入单位类型：
			</dt>
			<dd>
				<label><dictionary:dicItemSelect id="entryCurrentUnitType"
						name="entryCurrentUnitType" styleClass="modal_select"
						emptyText="<%=Constant.EMPTY_SELECT %>"
						selected="${pbPersonInForm.entryCurrentUnitType}"
						dicTypeCode="<%=DicConstants.YHRS0110%>" />
				</label>
			</dd>
		</dl>
    <dl class="updown-dl">
			<dt><b class="Required">* </b>进入单位时间：</dt>
			<dd>
				<label><input type="text" id="entryCurrentUnitDateStr"
					name="entryCurrentUnitDateStr" class="modal_iput"
					value="${pbPersonInForm.entryCurrentUnitDateStr}" readonly="readonly"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
				</label>
			</dd>
		</dl>
    <dl class="updown-dl">
			<dt>进入前所在单位：</dt>
			<dd>
				<label><input type="text" id="personInFrom"
					name="personInFrom" class="modal_iput"
					value="${pbPersonInForm.personInFrom}" onblur="validateIdNo(this)"
					maxlength="20" />
				</label>
			</dd>
		</dl>
		<dl class="updown-remark">
			<dt>备注：</dt>
			<dd>
				<label><textarea id="remark" name="remark" maxlength="1000">${pbPersonInForm.remark}</textarea>
				</label>
			</dd>
		</dl>
		<div class="clear"></div>
</div>
</body>
</html>
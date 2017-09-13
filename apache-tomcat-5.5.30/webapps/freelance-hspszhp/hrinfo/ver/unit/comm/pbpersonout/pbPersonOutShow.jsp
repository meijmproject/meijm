<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员进入信息</title>
</head>
<body>
<div class="md-units-adddata md-units md-three-column clearfix">
		<dl >
			<dt>
				<b class="Required">* </b>减员类型：
			</dt>
			<dd>
				<label><dictionary:dicItemSelect id="personOutType"
						name="personOutType" styleClass="modal_select"
						emptyText="<%=Constant.EMPTY_SELECT %>"
						selected="${pbPersonOutForm.personOutType}"
						dicTypeCode="<%=DicConstants.YHRS0128%>" />
				</label>
			</dd>
		</dl>
    <dl >
			<dt>离开日期：</dt>
			<dd>
				<label><input type="text" id="outDateStr" name="outDateStr"
					class="modal_iput" value="${pbPersonOutForm.outDateStr}"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
				</label>
			</dd>
		</dl>
    <dl >
			<dt>离开后去向：</dt>
			<dd>
				<label><input type="text" id="personOutTo"
					name="personOutTo" class="modal_iput"
					value="${pbPersonOutForm.personOutTo}" onblur="validateIdNo(this)"
					maxlength="20" />
				</label>
			</dd>
		</dl>
		<dl class="updown-remark">
			<dt>备注：</dt>
			<dd>
				<label><textarea id="remark" name="remark" maxlength="50">${pbPersonOutForm.remark}</textarea>
				</label>
			</dd>
		</dl>
</div>
</body>
</html>
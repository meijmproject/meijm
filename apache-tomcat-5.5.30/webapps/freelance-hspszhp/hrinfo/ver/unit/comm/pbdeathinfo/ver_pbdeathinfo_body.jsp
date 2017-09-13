<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员离退休信息
 * @page name   pbPersonInShow.jsp
 * @author      cheny
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员离退休信息</title>
</head>
<body>
<div class="md-units-adddata md-units md-three-column clearfix">
		<dl >
			<dt>
				<b class="Required">* </b>死亡时间：
			</dt>
			<dd>
				<label> <input type="text" name="deathDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>
				<b class="Required">* </b>死亡原因类别：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="deathType" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0103%>" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>
				<b class="Required">* </b>死亡原因：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect name="deathReason" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0104%>" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>抚恤金：</dt>
			<dd>
				<label><input type="text" name="smartMoney" class="modal_iput" maxlength="10" /> </label>
			</dd>
		</dl>
		<dl >
			<dt>丧葬费：</dt>
			<dd>
				<label><input type="text" name="funeralMoney" class="modal_iput" maxlength="10" /> </label>
			</dd>
		</dl>
		<div class="clear"></div>
	</div>
</body>
</html>
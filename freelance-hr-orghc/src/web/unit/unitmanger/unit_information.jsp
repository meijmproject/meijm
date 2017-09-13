<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head lang="en">
<title>单位查看页面</title>
<script src="hrinfo/ver/unit/person/js/VerPbPersonOperate.js"></script>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="infoshow-container padding-lrb">
		<div class="st-title-box">
			<h3 class="st-title-text">
				单位信息
				
			</h3>
			<div class="st-title-icon">
					<i><a href="goToUpdatUnit.do?method=getUnitInformation&unitOid=${unitOid}" class="popdown st-handle-modify  btn"></a></i>
			</div>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt>单位名称：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="unitName" />'><bean:write name="ubUnitForm" property="unitName" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>单位简称：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="unitShortName" />'><bean:write name="ubUnitForm" property="unitShortName" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>组织机构代码：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="corporationCode" />'><bean:write name="ubUnitForm" property="corporationCode" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>统一社会信用代码：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="unitCreditNo" />'><bean:write name="ubUnitForm" property="unitCreditNo" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>成立时间：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="establishedDateStr" />'><bean:write name="ubUnitForm" property="establishedDateStr" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>排序号：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="orderOfView" />'><bean:write name="ubUnitForm" property="orderOfView" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>联系人：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="contacter" />'><bean:write name="ubUnitForm" property="contacter" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>手机：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="mobilePhone" />'><bean:write name="ubUnitForm" property="mobilePhone" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>联系电话：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="phone" />'><bean:write name="ubUnitForm" property="phone" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>电子邮箱：</dt>
					<dd>
					<label title='<bean:write name="ubUnitForm" property="email" />'><bean:write name="ubUnitForm" property="email" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>传真：</dt>
					<dd>
						<label title='<bean:write name="ubUnitForm" property="fax" />'><bean:write name="ubUnitForm" property="fax" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>单位地址：</dt>
					<dd>
					<label title='<bean:write name="ubUnitForm" property="address" />'><bean:write name="ubUnitForm" property="address" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl updown-remark">
					<dt>备注：</dt>
					<dd>
					<label title='<bean:write name="ubUnitForm" property="remark" />'><bean:write name="ubUnitForm" property="remark" /></label>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function() {

	$('.popdown').popdown({
		width : 1400
	});
})
</script>
</html>
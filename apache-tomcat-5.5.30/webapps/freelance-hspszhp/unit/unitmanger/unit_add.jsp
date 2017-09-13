<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<title>单位查看页面</title>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="unit/unitmanger/unit_add_validate.js"></script>
</head>
<body>
<form id="ubUnitForm" class="form-inline" action="addUnitInfo.do?method=addUnitInfo" method="post" onsubmit="return false">
    <div class="modal-dialog-container">
        <div class="md-title">
                             单位信息&gt;新增
        </div>
        <div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
        <div class="md-main-content">

	       <div class="md-units md-three-column clearfix">
				<dl class="updown-dl">
					<dt><b class="Required">* </b>单位名称：</dt>
					<dd>
						<label><input type="text" id="unitName" name="unitName" value="${ubUnitForm.unitName }" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>单位简称：</dt>
					<dd>
						<label><input type="text" id="unitShortName" name="unitShortName" value="${ubUnitForm.unitShortName }" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>组织机构代码：</dt>
					<dd>
						<label><input type="text" id="corporationCode" name="corporationCode" value="${ubUnitForm.corporationCode }" maxlength="20" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>统一社会信用代码：</dt>
					<dd>
						<label><input type="text" id="unitCreditNo" name="unitCreditNo" value="${ubUnitForm.unitCreditNo }" maxlength="20" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>成立时间：</dt>
					<dd>
					<label> <html:text styleId="establishedDateStr" name="ubUnitForm" property="establishedDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>排序号：</dt>
					<dd>
					<label><input type="text" id="orderOfView" name="orderOfView" value="${ubUnitForm.orderOfView}" maxlength="3" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>联系人：</dt>
					<dd>
						<label><input type="text" id="contacter" name="contacter" value="${ubUnitForm.contacter}" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>手机：</dt>
					<dd>
						<label><input type="text" id="mobilePhone" name="mobilePhone" value="${ubUnitForm.mobilePhone}" maxlength="20" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>联系电话：</dt>
					<dd>
						<label><input type="text" id="phone" name="phone" value="${ubUnitForm.phone}" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>电子邮箱：</dt>
					<dd>
					<label><input type="text" id="email" name="email" value="${ubUnitForm.email}" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>传真：</dt>
					<dd>
						<label><input type="text" id="fax" name="fax" value="${ubUnitForm.fax}" maxlength="100" /></label>
					</dd>
				</dl>
				<dl class="updown-dl">
					<dt>单位地址：</dt>
					<dd>
					<label><input type="text" id="address" name="address" value="${ubUnitForm.address}" maxlength="250" /></label>
					</dd>
				</dl>
				<dl class="updown-remark">
					<dt>备注：</dt>
					<dd>
					<label><textarea  id="remark" name="remark"  maxlength="1000">${ubUnitForm.remark}</textarea></label>
					</dd>
				</dl>
			</div>
        </div>
        <div class="md-btn">
            <button id="saveUnit" type="button" class="md-btn-save">保  存</button>
            <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
          </div>
    </div>
</form>
	<%-- <div class="infoshow-container  padding-lrb">
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">
				单位信息
			</h2>
		<div class="search-content">
		<div class="search-include clearfix mrb-40">
				<dl class="search-unit">
					<dt class='search-unit-dt'>单位名称：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="unitName" name="unitName" value="${ubUnitForm.unitName }" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>单位简称：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="unitShortName" name="unitShortName" value="${ubUnitForm.unitShortName }" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>组织机构代码：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="corporationCode" name="corporationCode" value="${ubUnitForm.corporationCode }" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>统一社会信用代码：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="unitCreditNo" name="unitCreditNo" value="${ubUnitForm.unitCreditNo }" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>成立时间：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="establishedDate" name="establishedDate" value="${ubUnitForm.establishedDate}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>排序号：</dt>
					<dd class='search-unit-dd'>
					<label><input type="text" id="orderOfView" name="orderOfView" value="${ubUnitForm.orderOfView}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>联系人：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="contacter" name="contacter" value="${ubUnitForm.contacter}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>手机：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="mobilePhone" name="mobilePhone" value="${ubUnitForm.mobilePhone}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>联系电话：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="phone" name="phone" value="${ubUnitForm.phone}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>电子邮箱：</dt>
					<dd class='search-unit-dd'>
					<label><input type="text" id="email" name="email" value="${ubUnitForm.email}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>传真：</dt>
					<dd class='search-unit-dd'>
						<label><input type="text" id="fax" name="fax" value="${ubUnitForm.fax}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>单位地址：</dt>
					<dd class='search-unit-dd'>
					<label><input type="text" id="address" name="address" value="${ubUnitForm.address}" maxlength="50" /></label>
					</dd>
				</dl>
				<dl class="search-unit">
					<dt class='search-unit-dt'>备注：</dt>
					<dd class='search-unit-dd'>
					<label><input type="text" id="remark" name="remark" value="${ubUnitForm.remark}" maxlength="50" /></label>
					</dd>
				</dl>
			</div>
			</div>
		</div>
	</div> --%>
</body>
<script>
</script>
</html>
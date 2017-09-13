<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>编制信息</title>
<script type="text/javascript" src="unit/unitmanger/pbhcinfo/check_hc_info.js"></script>
</head>
<body>

<div class="infoshow-container padding-lrb mt20">
		<div class="st-title-box">
			<h3 class="st-title-text">编制信息</h3>
			
			<div class="st-title-icon">
					<i> <a class="st-add-icon  btn" href="javascript:void(0)" onclick="addHcRow()"></a> </i>
					<i> <a class="st-handle-delete  btn" href="javascript:void(0)" onclick="deleteAllhc()"></a> </i>
			</div>
			
			<div class="st-title-select">
	           	<label>编制类型：<dictionary:dicItemSelect style="width: 92px;" emptyText="--请选择--" id="hcCode" dicTypeCode="<%=DicConstants.YHRS0012 %>" /></label>
	            <label>经费形式：<dictionary:dicItemSelect style="width: 92px;" emptyText="--请选择--" id="budgetCode" dicTypeCode="<%=DicConstants.YHRS0013 %>" /></label>            
				<!-- <a style="margin-left:35px;"><img src="img/DetailPages/icon_add_green.png" onclick="addHcRow()"/> </a> -->
			</div>
		</div>
		<div class="st-main-table">
			<table class="sr-table" id="hcTable">
				<thead class="sr-table-thead">
					<tr>
						<th class="md-th"><input type="checkbox" onclick="selectAll(this);"></th>
						<th>编制类型</th>
						<th>经费形式</th>
						<th>变动前数量</th>
						<th>变动数量</th>
						<th>变动后数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="sr-table-tbody">
					<c:forEach var="row" items="${hcList}">
					<tr>
						<td class="md-th"><input type="checkbox" name='hcCheckBox'></td>
						<td title="${row.hcName}"><input type="hidden" value="${row.hcCode}" name="hcCode"/>
						<input type="text" value="${row.hcName}" readonly="readonly" name="hcName"/></td>
						<td title="${row.budgetFromName}"><input type="hidden" value="${row.budgetFromCode}" name="budgetFromCode"/>
						<input type="text" value="${row.budgetFromName}" readonly="readonly" name="budgetFromName"/></td>
						<td title="${row.preCount}"><input type="text" value="${row.preCount}" readonly="readonly" name="preCount"/></td>
						<td title="${row.chgCount}"><input type='text' value='${row.chgCount}' readonly="readonly" name="chgCount" onchange='checkNum(this)' onblur="checkNum1(this)"/></td>
						<td title="${row.curCount}"><input type="text" value="${row.curCount}" readonly="readonly" name="curCount"/></td>
						<td><input type="hidden" value="${row.hcOid}" name="hcOid"/>
						<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteHc('${row.hcOid}')"></a>
						<a class=" st-handle-modify" href="javascript:void(0)" id="${row.hcOid}" onclick="toUpdateHc(this);"></a>
						<a href='javascript:void(0)' title='保存' class='st-handle-save btn' onclick='updateHcInfo(this)'></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<%-- 
	<div class="sys_box sys_box_up" style="height: inherit;">
		<div class="sys_base">
			<span>编制信息</span>
           	<div style="float: right; margin-left: 20px;">
           	<label>编制类型：</lable><dictionary:dicItemSelect emptyText="--请选择--" id="hcCode" dicTypeCode="<%=DicConstants.YHRS0012 %>" /> 
            <label>经费形式：</lable><dictionary:dicItemSelect emptyText="--请选择--" id="budgetCode" dicTypeCode="<%=DicConstants.YHRS0013 %>" />            
			<a style="margin-left:35px;"><img src="img/DetailPages/icon_add_green.png" onclick="addHcRow()"/> </a>
			</div>
		</div>
		<div class="sys_list">
			<table class="table_edit_bg" width="95%" id="hcTable">
				<tr class="td_title">
					<td width="50"><input type="checkbox" />
					</td>
					<td>编制类型</td>
					<td>经费形式</td>
					<td>变动前数量</td>
					<td>变动数量</td>
					<td>变动后数量</td>
					<td width="100">操作</td>
				</tr>
				<c:forEach var="row" items="${hcList}">
					<tr class="td_content">
						<td width="50"><input type="checkbox" />
						<td><input type="hidden" value="${row.hcCode}" name="hcCode"/>
						<input type="text" value="${row.hcName}" readonly="readonly" name="hcName"/></td>
						<td><input type="hidden" value="${row.budgetFromCode}" name="budgetFromCode"/>
						<input type="text" value="${row.budgetFromName}" readonly="readonly" name="budgetFromName"/></td>
						<td><input type="text" value="${row.preCount}" readonly="readonly" name="preCount"/></td>
						<td><input type='text' value='${row.chgCount}' readonly="readonly" name="chgCount" onchange='checkNum(this)'/></td>
						<td><input type="text" value="${row.curCount}" readonly="readonly" name="curCount"/></td>
						<td><input type="hidden" value="${row.hcOid}" name="hcOid"/>
						<c:choose> 
							<c:when test="${row.preCount eq '0'}">
								<a onclick='deleteHcInfo(this)'><img src='img/DetailPages/icon_delete.png'/></a>
								<a class="btn"><img src="img/DetailPages/icon_revise.png" onclick="toUpdateHc(this);"/></a>
							</c:when>
							<c:otherwise> 
								<a><img src='img/DetailPages/icon_delete_disable.png'/></a>
								<a class="btn"><img src="img/DetailPages/icon_revise.png" onclick="toUpdateHc(this);"/></a>
							</c:otherwise>
						</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div> --%>
</body>
</html>
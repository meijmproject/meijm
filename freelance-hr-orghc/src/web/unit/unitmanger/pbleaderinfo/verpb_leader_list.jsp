<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>领导职数信息页面</title>
<script type="text/javascript" src="unit/unitmanger/pbleaderinfo/check_leader_info.js"></script>
</head>
<body>
<div class="infoshow-container padding-lrb">
		<div class="st-title-box">
			<h3 class="st-title-text">领导职数信息</h3>
			
			<div class="st-title-icon">
					<i><a class="st-add-icon btn" href="javascript:void(0);" onclick="addLeaderRow()"></a></i>
					<i> <a class="st-handle-delete  btn" href="javascript:void(0)" onclick="deleteAll()"></a> </i>
			</div>
			<div class="st-title-select">
	           	<label>职务属性：<dictionary:dicItemSelect style="width: 92px;" notInclude="<%=DicConstants.YHRS0028_31%>" emptyText="--请选择--" id="dutyAttribute" dicTypeCode="<%=DicConstants.YHRS0028 %>" /></label> 
	            <label>职务级别：
	            <input type="text" name="chooseDutyLevelName" id="chooseDutyLevel" readonly="readonly" 
	            value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0015%>"/>'
	            onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0015%>',null,null,null,null,['top','left'])" notInclude="1,4,5" style="width: 92px;height: 23px;border: 1px solid #ddd;"/></label>
				<input type="hidden" name="chooseDutyLevel" id="chooseDutyLevelVal"/>
			</div>
		</div>
		<div class="st-main-table">
			<table class="sr-table" id="leaderTable">
				<thead class="sr-table-thead">
					<tr>
						<th class="md-th"><input type="checkbox" onclick="selectAllOnclick(this);"></th>
						<th>职务属性</th>
						<th>职务级别</th>
						<th>变动前数量</th>
						<th>变动数量</th>
						<th>变动后数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="sr-table-tbody">
					<c:forEach var="row" items="${leaderList}">
					<tr >
						<td class="md-th"><input type="checkbox" name='leaderCheckBox'></td>
						<td title="${row.dutyAttributeName}"><input type="text" readonly="readonly" value="${row.dutyAttributeName}" name="dutyAttributeName"/>
						<input type="hidden" value="${row.dutyAttribute}" name="dutyAttribute"/></td>
						<td title="${row.dutyLevelName}"><input type="text" readonly="readonly" value="${row.dutyLevelName}" name="dutyLevelName"/>
						<input type="hidden" value="${row.dutyLevel}" name="dutyLevel"/></td>
						<td title="${row.preCount}"><input type="text" readonly="readonly" value="${row.preCount}" name="preCount"/></td>
						<td title="${row.chgCount}"><input type="text" readonly="readonly" value="${row.chgCount}" name="chgCount" onchange='checkNum(this)' onblur='checkNum1(this)' /></td>
						<td title="${row.curCount}"><input type="text" readonly="readonly" value="${row.curCount}" name="curCount"/></td>
						<td><input type="hidden" value="${row.leaderOid}" name="leaderOid"/>
						<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteLeaderInfo('${row.leaderOid}')"></a>
						<a class=" st-handle-modify" href="javascript:void(0)" id="${row.leaderOid}" onclick="toUpdateLeader(this);"></a>
						<a href='javascript:void(0)' title='保存' class='st-handle-save' onclick='updateLeaderInfo(this)'></a>
						
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	
	<%-- <div class="sys_box sys_box_up" style="height: inherit;">
		<div class="sys_base">
			<span>领导职数信息</span>
			
			<div style="float: right; margin-left: 20px;">
           	<label>职务属性：</label><dictionary:dicItemSelect style="width: 92px;" emptyText="--请选择--" id="dutyAttribute" dicTypeCode="<%=DicConstants.YHRS0028 %>" /> 
            <label>职务级别：</label>
            <input type="text" name="chooseDutyLevelName" id="chooseDutyLevel" readonly="readonly" 
            value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0015%>"/>'
            onclick="dictionaryType(this,'<%=DicConstants.YHRS0015%>')" style="width: 92px;height: 23px;border: 1px solid #ddd;"/>
			<input type="hidden" name="chooseDutyLevel" id="chooseDutyLevelVal"/>
			<a style="margin-left:35px;"><img src="img/DetailPages/icon_add_green.png" onclick="addLeaderRow()"/></a>
			</div>
		</div>
		<input type="hidden" id="verVacationId" value="${id}">
		<div class="sys_list">
			<table class="table_edit_bg" width="95%" id="leaderTable">
				<tr class="td_title">
					<td width="50"><input type="checkbox" /></td>
					<td>职务属性</td>
					<td>职务级别</td>
					<td>变动前数量</td>
					<td>变动数量</td>
					<td>变动后数量</td>
					<td width="100">操作</td>
				</tr>
				<input type="hidden" value="${unitOid}" id="unitOid"/>
				<c:forEach var="row" items="${leaderList}">
					<tr class="td_content">
						<td width="50"><input type="checkbox" /></td>
						<td ><input type="text" readonly="readonly" value="${row.dutyAttributeName}" name="dutyAttributeName"/>
						<input type="hidden" value="${row.dutyAttribute}" name="dutyAttribute"/></td>
						<td ><input type="text" readonly="readonly" value="${row.dutyLevelName}" name="dutyLevelName"/>
						<input type="hidden" value="${row.dutyLevel}" name="dutyLevel"/></td>
						<td><input type="text" readonly="readonly" value="${row.preCount}" name="preCount"/></td>
						<td><input type="text" readonly="readonly" value="${row.chgCount}" name="chgCount" onchange='checkNum(this)'/></td>
						<td><input type="text" readonly="readonly" value="${row.curCount}" name="curCount"/></td>
						<td><input type="hidden" value="${row.leaderOid}" name="leaderOid"/>
						<c:choose> 
							<c:when test="${row.preCount eq '0'}">
								<a onclick='deleteLeaderInfo(this)'><img src='img/DetailPages/icon_delete.png'/></a> 
								<a class="btn"><img src="img/DetailPages/icon_revise.png" onclick="toUpdateLeader(this);"/></a>
							</c:when>
							<c:otherwise> 
								<a><img src='img/DetailPages/icon_delete_disable.png'/></a>
								<a class="btn"><img src="img/DetailPages/icon_revise.png" onclick="toUpdateLeader(this);"/></a>
							</c:otherwise>
						</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div> --%>
</body>
</html>
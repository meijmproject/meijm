<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>专业技术资格信息</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>

<script type="text/javascript" src="hrinfo/ver/unit/comm/pbproftech/js/check_pbproftech_info.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
		        $('.popdown').popdown({width:1200});
		    })
	</script>
	<style type="text/css">
/* td {
      white-space:nowrap;overflow:hidden;text-overflow: ellipsis;
} */
</style>
</head>
<body>
		<input type="hidden" id="verVerProfTechId" value="${id}">
		<input type="hidden" id="verVerProfTechPersonOid" name="personOid" value="${verProfTechInfoForm.personOid}">
	<div class="sys_box sys_box_up">
		<div class="sys_base">
			<span>专业技术资格信息</span> <a style="float: right; margin-left: 20px;"
				href="goAddVerProfTechInfo.do?method=goAdd&personOid=${param.personOid}&id=${id}"
				class="popdown btn"><img
				src="img/DetailPages/icon_add_green.png" /></a>
		</div>
		<div class="sys_list">
			<table class="table_edit_bg" width="95%">
				<tr class="td_title">
					<td width="50"><input type="checkbox" /></td>
					<td>资格名称</td>
					<td>资格等级</td>
					<td>专业名称</td>
					<td>取得时间</td>
					<td>取得途径</td>
					<td width="100">操作</td>
				</tr>
				<c:forEach var="row" items="${list}">
					<tr class="td_content">
						<td width="50"><input type="checkbox" /></td>
						<td title="${row.profTechName}">${row.profTechName}</td>
						<td title="${row.profTechLevel}">${row.profTechLevel}</td>
						<td title="${row.specialityName}">${row.specialityName}</td>
						<td title="${row.procureDateStr}">${row.procureDateStr}</td>
						<td title="${row.acquireApproachCode}">${row.acquireApproachCode}</td>
						<td><a href="javascript:void(0);"
							onclick="deleteVerProfTechInfo('${row.profTechOid}')"><img
								src="img/DetailPages/icon_delete.png" /></a> <a
							href="goUpdateVerProfTechInfo.do?method=goUpdate&profTechOid=${row.profTechOid}&id=${id}"
							class="popdown btn"
							id="modaltriggerVerProfessionalInfoUpdate${row.profTechOid}"><img
								src="img/DetailPages/icon_revise.png" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>

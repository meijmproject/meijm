<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内设机构列表</title>
<script src="hrinfo/ver/unit/unit/pborginfo/check_org_info.js"></script>
</head>
	<!-- 内设机构div -->
	<div class="sys_box sys_box_up" style="height: inherit;">
		<div class="sys_base">
			<span>内设机构信息</span>
			<div style="float:right;" > 
			<a href="goToCreateVerUbOrgInfo.do?method=goToCreateVerUbOrgInfo&unitName=${unitName }&unitOid=${unitOid }"
				class="popdown btn"><img src="img/DetailPages/icon_add_green.png" /></a>
				</div>
		</div>
		<div class="sys_list">
			<table class="table_edit_bg" width="95%" id="orgTable">
				<tr class="td_title">
					<td width="50"><input type="checkbox" /></td>
					<td>内设机构名称</td>
					<td>内设机构级别</td>
					<td>上级内设机构</td>
					<td width="150">操作</td>
				</tr>
				<c:forEach var="row" items="${orgLists}">
					<tr class="td_content">
						<td width="50"><input type="checkbox" /></td>
						<td title="${row.orgName}">${row.orgName}</td>
						<td title="${row.levelCode}">${row.levelCode}</td>
						<td title="${row.parentOrgName}">${row.parentOrgName}</td>
						<td>
							<a href="viewUbOrgInfo.do?method=goToViewUbOrgInfoPage&orgOid=${row.orgOid}&unitName=${unitName }&unitOid=${unitOid }" class="popdown btn">查看</a>
							<c:if test="${row.orgType != '1' && row.orgType != '3'}">
								<c:if test="${row.createdByCode != 'migration' }">
									<a><img src="img/DetailPages/icon_delete.png" onclick="deleteOrgInfo('${row.orgOid}','${unitOid }')"/></a>
								</c:if>
                        		<a href="goToUpdateVerUbOrgInfo.do?method=goToUpdateVerUbOrgInfoPage&orgOid=${row.orgOid}&unitName=${unitName }&unitOid=${unitOid }"  class="popdown btn""><img src="img/DetailPages/icon_revise.png"/></a>
                        	</c:if>
                        </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
<script>
    $(document).ready(function(){
        //弹出框
        $('.popdown').popdown({width:1200});
    });
</script>
</html>
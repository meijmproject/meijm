<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html class="poied_html">
<head lang="en">
<meta charset="UTF-8">
<title>信息校核列表页</title>
</head>
<body>
<div>
<input id="unitOid" type="hidden" value="${unitOid}"/>
<div id="unitInfo">
<jsp:include page="/unit/unitmanger/unit_information.jsp"></jsp:include>
</div>
<jsp:include page="/unit/unitmanger/org/unitmanager_org_workbench.jsp"></jsp:include>
<%-- <div id="hcList">
<jsp:include page="/unit/unitmanger/pbhcinfo/verpb_hc_list.jsp"></jsp:include>
</div>
<div id="leaderList">
<jsp:include page="/unit/unitmanger/pbleaderinfo/verpb_leader_list.jsp"></jsp:include>
</div> --%>
</div>
</body>
<script type="text/javascript">
</script>
</html>
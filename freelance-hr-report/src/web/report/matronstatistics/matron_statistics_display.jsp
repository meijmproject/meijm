<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name matron_statistics_display.jsp
* @function   科主任及护士长系列人员汇总 显示页面
* @author     liul
* @created    2017-03-07
* @version    1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科主任及护士长系列人员汇总</title>
<style>
#allperson_statistics_div {
	border-collapse: collapse;
	text-align: center;
}

#allperson_statistics_div td,#allperson_statistics_div th {
	border: 1px solid #000;
}
</style>
</head>
<body>
	<div class="handel">
		<button class="btn_print"
			onclick="location.href='goViewMatronInfo.do?method=goviewMatronInfo&exportExcel=true';">导出</button>
	</div>
	<div id="allperson_statistics_div">
		<div class="sys_list">
			<table class="table_edit_bg" width="95%" cellspadding="0"
				cellspacing="0">
				<tr>
					<th colspan="34"><h2 align="center">医院科主任及护士长系列人员汇总</h2>
					</th>
				</tr>

				<tr align="center" height="25px">
					<td rowspan="3" colspan="2">科室</td>
					<td colspan="16">科主任（负责人）</td>
					<td colspan="16">护士长</td>
				</tr>
				<tr><%for(int i =0;i<2;i++){%>
					<td colspan="4">职位</td>
					<td colspan="4">职称</td>

					<td colspan="4">学历</td>
					<td rowspan="2">合计</td>
					<td colspan="2">性别</td>

					<td rowspan="2">平均年龄（岁）</td>
				<%}%>
				</tr>
				<tr><%for(int i =0;i<2;i++){%>
					<td>正</td>
					<td>姓名</td>
					<td>副</td>
					<td>姓名</td>
					<td>正高</td>
					<td>副高</td>
					<td>中级</td>
					<td>初级</td>
					<td>博士</td>
					<td>硕士</td>
					<td>本科</td>
					<td>大专</td>
					<td>男</td>
					<td>女</td>
				<%}%>
				</tr>
				<c:if test="${nkbqLength gt 0 }">
				 <tr>
					<td rowspan="${nkbqLength }">内科病区</td>
					<c:forEach items="${nkbqList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${nkbqList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0}">
						<tr>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${wkbqLength gt 0 }">
				<tr>
					<td rowspan="${wkbqLength }">外科病区</td>
					<c:forEach items="${wkbqList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${wkbqList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0}">
						<tr>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${mzksLength gt 0 }">
				<tr>
					<td rowspan="${mzksLength }">门诊科室</td>
					<c:forEach items="${mzksList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${mzksList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0}">
						<tr>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${yjksLength gt 0 }">
				<tr>
					<td rowspan="${yjksLength }">医技科室</td>
					<c:forEach items="${yjksList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:if test="${units.deptOid eq 1}">
								<td></td>
							</c:if>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${yjksList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<c:if test="${units.deptOid eq 1}">
								<td bgcolor="#D4E3F3" align="center" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1}">
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							</c:if>
							<td align="center" height="25px">${units.kzrzwzCount}</td>
							<td align="center" height="25px">${units.kzrzwzxmCount}</td>
							<td align="center" height="25px">${units.kzrzwfCount}</td>
							<td align="center" height="25px">${units.kzrzwfxmCount}</td>
							<td align="center" height="25px">${units.kzrzczgCount}</td>
							<td align="center" height="25px">${units.kzrzcfgCount}</td>
							<td align="center" height="25px">${units.kzrzczjCount}</td>
							<td align="center" height="25px">${units.kzrzccjCount}</td>
							<td align="center" height="25px">${units.kzrxlbsCount}</td>
							<td align="center" height="25px">${units.kzrxlssCount}</td>
							<td align="center" height="25px">${units.kzrxlbkCount}</td>
							<td align="center" height="25px">${units.kzrxldzCount}</td>
							<td align="center" height="25px">${units.kzrhjCount}</td>
							<td align="center" height="25px">${units.kzrSex1Count}</td>
							<td align="center" height="25px">${units.kzrSex2Count}</td>
							<td align="center" height="25px">${units.kzrnlCount}</td>
							<td align="center" height="25px">${units.hszzwzCount}</td>
							<td align="center" height="25px">${units.hszzwzxmCount}</td>
							<td align="center" height="25px">${units.hszzwfCount}</td>
							<td align="center" height="25px">${units.hszzwfxmCount}</td>
							<td align="center" height="25px">${units.hszzczgCount}</td>
							<td align="center" height="25px">${units.hszzcfgCount}</td>
							<td align="center" height="25px">${units.hszzczjCount}</td>
							<td align="center" height="25px">${units.hszzccjCount}</td>
							<td align="center" height="25px">${units.hszxlbsCount}</td>
							<td align="center" height="25px">${units.hszxlssCount}</td>
							<td align="center" height="25px">${units.hszxlbkCount}</td>
							<td align="center" height="25px">${units.hszxldzCount}</td>
							<td align="center" height="25px">${units.hszhjCount}</td>
							<td align="center" height="25px">${units.hszSex1Count}</td>
							<td align="center" height="25px">${units.hszSex2Count}</td>
							<td align="center" height="25px">${units.hsznlCount}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
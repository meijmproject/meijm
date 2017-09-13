<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name technicaltitleage_statistics_display.jsp
* @function   卫生技术人员依据职称等级汇总的年龄、性别架构表 显示页面
* @author     liul
* @created    2017-03-07
* @version    1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>卫生技术人员依据职称等级汇总的年龄、性别架构表</title>
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
			onclick="location.href='goViewTechnicalTitleAgeInfo.do?method=goviewTechnicalTitleAgeInfo&exportExcel=true';">导出</button>
	</div>
	<div id="allperson_statistics_div">
		<div class="sys_list">
			<table class="table_edit_bg" width="95%" cellspadding="0"
				cellspacing="0">
				<tr>
					<th colspan="13" style="border-right:0px"><h2 align="center">医院卫生技术人员依据职称等级汇总的年龄、性别架构表</h2>
					</th>
					<th align="center" style="border-left:0px">单位：人</th>
				</tr>

				<tr align="center" height="25px">
					<td rowspan="2">职称</td>
					<td colspan="8">年龄段</td>
					<td colspan="2">性别</td>
					<td rowspan="2">合计</td>
					<td rowspan="2">比例（%）</td>
					<td rowspan="2">平均年龄（岁）</td>
				</tr>
				<tr>
					<td>25岁以下</td>
					<td>26-30岁</td>
					<td>31-35岁</td>
					<td>36-40岁</td>
					<td>41-45岁</td>
					<td>46-50岁</td>
					<td>51-55岁</td>
					<td>56岁以上</td>
					<td>男</td>
					<td>女</td>
				</tr>
				<c:forEach items="${positionLevelList }" var="units" varStatus="status">
						<tr>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.positionLevelName}</td>
							<td align="center" height="25px">${units.age1}</td>
							<td align="center" height="25px">${units.age2}</td>
							<td align="center" height="25px">${units.age3}</td>
							<td align="center" height="25px">${units.age4}</td>
							<td align="center" height="25px">${units.age5}</td>
							<td align="center" height="25px">${units.age6}</td>
							<td align="center" height="25px">${units.age7}</td>
							<td align="center" height="25px">${units.age8}</td>
							<td align="center" height="25px">${units.sex1}</td>
							<td align="center" height="25px">${units.sex2}</td>
							<td align="center" height="25px">${units.total}</td>
							<td align="center" height="25px"><fmt:formatNumber value="${units.bl }" pattern="0.00" type="number" /></td>
							<td align="center" height="25px">${units.avgAge}</td>
						</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
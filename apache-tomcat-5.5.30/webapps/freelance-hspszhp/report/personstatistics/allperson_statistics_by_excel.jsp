<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name allperson_statistics_display.jsp
* @function   全院员工汇总统计表 显示页面
* @author     liul
* @created    2017-03-03
* @version    1.0
-->
<!DOCTYPE html>
<html> 
<%
	response.setHeader("Content-Disposition", "attachment; filename="
			+ new String(("医院全院员工汇总.xls").getBytes("GBK"), 
					"ISO8859-1"));
%>
<head>
<meta charset="UTF-8">
<title>全院员工汇总统计表</title>
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
<style>
</style>
</head>
<body>

	<div id="allperson_statistics_div">
		<div class="sys_list">
			<table class="institution_basic big-data-excel">
				<tr align="center" height="25px">
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="3">项目名称</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="3">科室</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="34">非护人员</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="14">护士</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="3">合计</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="3">备注</td>
				</tr>
				<tr>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="4">医师职称</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">执业医师合计</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">见习医师</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="4">技师职称</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">技师合计</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">见习技师</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="4">药师职称</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">药师合计</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">见习药师</td>

					<td class="institution_basic_td_bg border_right border_bottom" colspan="4">研究员职称</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">研究员合计</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">其他</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">小计</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="5">学历</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">年龄（岁）</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="2">性别</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">规培人员</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="4">职称</td>

					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">小计</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">见习护士</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="5">学历</td>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="2">年龄（岁）</td>
					<td class="institution_basic_td_bg border_right border_bottom" colspan="2">性别</td>
				</tr>
				<tr>
					<td class="institution_basic_td_bg border_right border_bottom">正高</td>
					<td class="institution_basic_td_bg border_right border_bottom">副高</td>
					<td class="institution_basic_td_bg border_right border_bottom">中级</td>
					<td class="institution_basic_td_bg border_right border_bottom">初级</td>
					<td class="institution_basic_td_bg border_right border_bottom">正高</td>
					<td class="institution_basic_td_bg border_right border_bottom">副高</td>
					<td class="institution_basic_td_bg border_right border_bottom">中级</td>
					<td class="institution_basic_td_bg border_right border_bottom">初级</td>
					<td class="institution_basic_td_bg border_right border_bottom">正高</td>
					<td class="institution_basic_td_bg border_right border_bottom">副高</td>
					<td class="institution_basic_td_bg border_right border_bottom">中级</td>
					<td class="institution_basic_td_bg border_right border_bottom">初级</td>
					<td class="institution_basic_td_bg border_right border_bottom">正高</td>
					<td class="institution_basic_td_bg border_right border_bottom">副高</td>
					<td class="institution_basic_td_bg border_right border_bottom">中级</td>
					<td class="institution_basic_td_bg border_right border_bottom">初级</td>

					<td class="institution_basic_td_bg border_right border_bottom">博士</td>
					<td class="institution_basic_td_bg border_right border_bottom">硕士</td>
					<td class="institution_basic_td_bg border_right border_bottom">本科</td>
					<td class="institution_basic_td_bg border_right border_bottom">大专</td>
					<td class="institution_basic_td_bg border_right border_bottom">其他</td>
					<td class="institution_basic_td_bg border_right border_bottom">男</td>
					<td class="institution_basic_td_bg border_right border_bottom">女</td>
					<td class="institution_basic_td_bg border_right border_bottom">正高</td>
					<td class="institution_basic_td_bg border_right border_bottom">副高</td>
					<td class="institution_basic_td_bg border_right border_bottom">中级</td>
					<td class="institution_basic_td_bg border_right border_bottom">初级</td>
					<td class="institution_basic_td_bg border_right border_bottom">博士</td>
					<td class="institution_basic_td_bg border_right border_bottom">硕士</td>
					<td class="institution_basic_td_bg border_right border_bottom">本科</td>
					<td class="institution_basic_td_bg border_right border_bottom">大专</td>
					<td class="institution_basic_td_bg border_right border_bottom">其他</td>
					<td class="institution_basic_td_bg border_right border_bottom">男</td>
					<td class="institution_basic_td_bg border_right border_bottom">女</td>
				</tr>
				<%-- <c:forEach items="${map }" var="var" varStatus="a">
				<c:forEach items="${map }" var="list" varStatus="b">
				<tr>
				<c:if test="${list.key eq 'nkbqList' && a.index eq 0 }">
					<td rowspan="${nkbqLength-1 }">内科病区</td>
					
				</c:if>
				<c:if test="${list.key eq 'wkbqList' && a.index eq 1}">
					<td rowspan="${wkbqLength-2 }">外科病区</td>
				</c:if>
				<c:if test="${list.key eq 'mzksList' && a.index eq 2}">
					<td rowspan="${mzksLength-2 }">门诊科室</td>
				</c:if>
				<c:if test="${list.key eq 'yjksList' && a.index eq 3}">
					<td rowspan="${yjksLength-2 }">医技科室</td>
				</c:if>
				<c:if test="${list.key eq 'xzhqList' && a.index eq 4}">
					<td rowspan="${xzhqLength-2 }">行政后勤</td>
				</c:if>
					<c:forEach items="${list.value }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.yiszgCount}</td>
							<td align="center" height="25px">${units.yisfgCount}</td>
							<td align="center" height="25px">${units.yiszjCount}</td>
							<td align="center" height="25px">${units.yiscjCount}</td>
							<td align="center" height="25px">${units.yishjCount}</td>
							<td align="center" height="25px">${units.yissxCount}</td>
							<td align="center" height="25px">${units.jiszgCount}</td>
							<td align="center" height="25px">${units.jisfgCount}</td>
							<td align="center" height="25px">${units.jiszjCount}</td>
							<td align="center" height="25px">${units.jiscjCount}</td>
							<td align="center" height="25px">${units.jishjCount}</td>
							<td align="center" height="25px">${units.jissxCount}</td>
							<td align="center" height="25px">${units.yaoszgCount}</td>
							<td align="center" height="25px">${units.yaosfgCount}</td>
							<td align="center" height="25px">${units.yaoszjCount}</td>
							<td align="center" height="25px">${units.yaoscjCount}</td>
							<td align="center" height="25px">${units.yaoshjCount}</td>
							<td align="center" height="25px">${units.yaossxCount}</td>
							<td align="center" height="25px">${units.yjyzgCount}</td>
							<td align="center" height="25px">${units.yjyfgCount}</td>
							<td align="center" height="25px">${units.yjyzjCount}</td>
							<td align="center" height="25px">${units.yjycjCount}</td>
							<td align="center" height="25px">${units.yjyhjCount}</td>
							<td align="center" height="25px">${units.qtCount}</td>
							<td align="center" height="25px">${units.fhxjCount}</td>
							<td align="center" height="25px">${units.fhbsxlCount}</td>
							<td align="center" height="25px">${units.fhssxlCount}</td>
							<td align="center" height="25px">${units.fhbkxlCount}</td>
							<td align="center" height="25px">${units.fhdzxlCount}</td>
							<td align="center" height="25px">${units.fhqtxlCount}</td>
							<td align="center" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td align="center" height="25px">${units.fhSex1Count}</td>
							<td align="center" height="25px">${units.fhSex2Count}</td>
							<td align="center" height="25px">${units.fhgpCount}</td>
							<td align="center" height="25px">${units.hszgCount}</td>
							<td align="center" height="25px">${units.hsfgCount}</td>
							<td align="center" height="25px">${units.hszjCount}</td>
							<td align="center" height="25px">${units.hscjCount}</td>
							<td align="center" height="25px">${units.hssxCount}</td>
							<td align="center" height="25px">${units.hsxjCount}</td>
							<td align="center" height="25px">${units.hsbsxlCount}</td>
							<td align="center" height="25px">${units.hsssxlCount}</td>
							<td align="center" height="25px">${units.hsbkxlCount}</td>
							<td align="center" height="25px">${units.hsdzxlCount}</td>
							<td align="center" height="25px">${units.hsqtxlCount}</td>
							<td align="center" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td align="center" height="25px">${units.hsSex1Count}</td>
							<td align="center" height="25px">${units.hsSex2Count}</td>
							<td align="center" height="25px">${units.totalCount}</td>
							<td align="center" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				<c:forEach items="${list.value }" var="units" varStatus="status">
					<c:if test="${status.index ne 0}">
						<tr>
							<c:if test="${units.deptOid eq 1}">
								<td></td>
							</c:if>
							<td bgcolor="#D4E3F3" align="center" height="25px">${units.deptName}</td>
							<td align="center" height="25px">${units.yiszgCount}</td>
							<td align="center" height="25px">${units.yisfgCount}</td>
							<td align="center" height="25px">${units.yiszjCount}</td>
							<td align="center" height="25px">${units.yiscjCount}</td>
							<td align="center" height="25px">${units.yishjCount}</td>
							<td align="center" height="25px">${units.yissxCount}</td>
							<td align="center" height="25px">${units.jiszgCount}</td>
							<td align="center" height="25px">${units.jisfgCount}</td>
							<td align="center" height="25px">${units.jiszjCount}</td>
							<td align="center" height="25px">${units.jiscjCount}</td>
							<td align="center" height="25px">${units.jishjCount}</td>
							<td align="center" height="25px">${units.jissxCount}</td>
							<td align="center" height="25px">${units.yaoszgCount}</td>
							<td align="center" height="25px">${units.yaosfgCount}</td>
							<td align="center" height="25px">${units.yaoszjCount}</td>
							<td align="center" height="25px">${units.yaoscjCount}</td>
							<td align="center" height="25px">${units.yaoshjCount}</td>
							<td align="center" height="25px">${units.yaossxCount}</td>
							<td align="center" height="25px">${units.yjyzgCount}</td>
							<td align="center" height="25px">${units.yjyfgCount}</td>
							<td align="center" height="25px">${units.yjyzjCount}</td>
							<td align="center" height="25px">${units.yjycjCount}</td>
							<td align="center" height="25px">${units.yjyhjCount}</td>
							<td align="center" height="25px">${units.qtCount}</td>
							<td align="center" height="25px">${units.fhxjCount}</td>
							<td align="center" height="25px">${units.fhbsxlCount}</td>
							<td align="center" height="25px">${units.fhssxlCount}</td>
							<td align="center" height="25px">${units.fhbkxlCount}</td>
							<td align="center" height="25px">${units.fhdzxlCount}</td>
							<td align="center" height="25px">${units.fhqtxlCount}</td>
							<td align="center" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td align="center" height="25px">${units.fhSex1Count}</td>
							<td align="center" height="25px">${units.fhSex2Count}</td>
							<td align="center" height="25px">${units.fhgpCount}</td>
							<td align="center" height="25px">${units.hszgCount}</td>
							<td align="center" height="25px">${units.hsfgCount}</td>
							<td align="center" height="25px">${units.hszjCount}</td>
							<td align="center" height="25px">${units.hscjCount}</td>
							<td align="center" height="25px">${units.hssxCount}</td>
							<td align="center" height="25px">${units.hsxjCount}</td>
							<td align="center" height="25px">${units.hsbsxlCount}</td>
							<td align="center" height="25px">${units.hsssxlCount}</td>
							<td align="center" height="25px">${units.hsbkxlCount}</td>
							<td align="center" height="25px">${units.hsdzxlCount}</td>
							<td align="center" height="25px">${units.hsqtxlCount}</td>
							<td align="center" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td align="center" height="25px">${units.hsSex1Count}</td>
							<td align="center" height="25px">${units.hsSex2Count}</td>
							<td align="center" height="25px">${units.totalCount}</td>
							<td align="center" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
				</c:forEach>
				</c:forEach> --%>
				<c:if test="${nkbqLength gt 0 }">
				 <tr>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="${nkbqLength }">内科病区</td>
					<c:forEach items="${nkbqList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:if test="${units.deptOid eq 1}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${nkbqList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<c:if test="${units.deptOid eq 1}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${wkbqLength gt 0 }">
				<tr>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="${wkbqLength }">外科病区</td>
					<c:forEach items="${wkbqList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${wkbqList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${mzksLength gt 0 }">
				<tr>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="${mzksLength }">门诊科室</td>
					<c:forEach items="${mzksList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${mzksList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${yjksLength gt 0 }">
				<tr>
					<td class="institution_basic_td_bg border_right border_bottom" rowspan="${yjksLength }">医技科室</td>
					<c:forEach items="${yjksList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${yjksList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<c:if test="${units.deptOid eq 1 || units.deptOid eq 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							</c:if>
							<c:if test="${units.deptOid ne 1 && units.deptOid ne 2}">
								<td class="institution_basic_td_bg border_right border_bottom" height="25px">${units.deptName}</td>
							</c:if>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${xzhqLength gt 0 }">
				<tr>
					<c:forEach items="${xzhqList }" var="units" varStatus="status">
						<c:if test="${status.index eq 0}">
							<%-- <c:if test="${units.deptOid eq 1}">
								<td></td>
							</c:if>
							<c:if test="${units.deptOid eq 2}">
								<td></td>
							</c:if> --%>
							<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
				<c:forEach items="${xzhqList }" var="units" varStatus="status">
					<c:if test="${status.index ne 0 || units.wardType eq null}">
						<tr>
							<%-- <c:if test="${units.deptOid eq 1}">
								<td></td>
							</c:if>
							<c:if test="${units.deptOid eq 2}">
								<td></td>
							</c:if> --%>
							<td class="institution_basic_td_bg border_right border_bottom" height="25px" colspan="2">${units.deptName}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jisfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jiscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jishjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.jissxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaosfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaoshjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yaossxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyzjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjycjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.yjyhjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.qtCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.fhnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.fhgpCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsfgCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hszjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hscjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsxjCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hssxCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbsxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsssxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsbkxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsdzxlCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsqtxlCount}</td>
							<td class="institution_basic_td_color" height="25px"><fmt:formatNumber value="${units.hsnlCount}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex1Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.hsSex2Count}</td>
							<td class="institution_basic_td_color" height="25px">${units.totalCount}</td>
							<td class="institution_basic_td_color" height="25px">${units.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
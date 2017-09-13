<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在岗人员总表导出的表格显示页</title>
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
</head>
<body>
  <h3 style="height: 30px; padding: 5px 0;"><a class="btn-search btn-default" href="javascript:void(0)" class="popdown btn1" onclick="location.href='goViewEmployedPersonInfo.do?method=goviewEmployedPersonInfo&flag=export';">导出</a></h3>
	<table class="institution_basic" id="zgemplyedperson_collection_table_html">
		<tbody id="zgemplyedperson_collection_content">
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">项目名称</td>
				<td class="institution_basic_td_bg border_right border_bottom">正高</td>
        <td class="institution_basic_td_bg border_right border_bottom">副高</td>
        <td class="institution_basic_td_bg border_right border_bottom">中级</td>
        <td class="institution_basic_td_bg border_right border_bottom">其他</td>
        <td class="institution_basic_td_bg border_right border_bottom">合计</td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">上级下达编制人数</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">年末在岗职工数（包括正式职工、聘用职工、本院规培学员）</td>
				<c:forEach items="${zgzg}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">卫生技术人员（未含行政人员）</td>
				<c:forEach items="${wsjs}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">执业医师</td>
				<c:forEach items="${zyys}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其中：中医类别</td>
				<c:forEach items="${zyysZylb}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">执业助理医师</td>
				<c:forEach items="${zyzlys}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其中：中医类别</td>
				<c:forEach items="${zyzlysZylb}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">注册护士</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其中：助产士</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">药师(士)</td>
				<c:forEach items="${zyyaos}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">西药师(士)</td>
				<c:forEach items="${zyyaosxys}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">中药师(士)</td>
				<c:forEach items="${zyyaoszys}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">检验技师(士)</td>
				<c:forEach items="${jyjs}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">影像技师(士)</td>
				<c:forEach items="${yxjs}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.count}"></c:out></td>
        </c:forEach>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其他卫生技术人员（除影像、药剂、检验之外人员）</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其中：见习医师</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">内：中医</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">其他技术人员</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">管理人员（含行政医生、护理人员）</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
			<tr>
				<td class="institution_basic_td_bg border_right border_bottom">工勤技能人员</td>
				<td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td><td class="institution_basic_td_color"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html>
<%
	response.setHeader("Content-Disposition", "attachment; filename="+new String(("各病区卫技人员配备情况一览表.xls").getBytes("GBK"), "ISO8859-1"));
%>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="hspszhphtml/css/components/left_nav.css">
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
</head>
<body>
	<table class="institution_basic" id="wardset_collection_table_html" border=1>
    <tbody id="wardset_collection_content">
      <tr id="deptName">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">病区</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_bg border_right border_bottom"><c:out value="${map.deptName}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="bedNum">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">床位数</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.bedNum}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="yisheng">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">医生</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.yisheng}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="hushi">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">护士</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.hushi}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="yaoshi">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">药师</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.yaoshi}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="jishi">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">技师</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.jishi}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="total">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">合计</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.total}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="huPerBed">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">护：床</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.huPerBed}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="yiPerBed">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">医：床</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.yiPerBed}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="totalPerBed">
        <td colspan="2" class="institution_basic_td_bg border_right border_bottom">卫技：床</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"><c:out value="${map.totalPerBed}"></c:out></td>
        </c:forEach>
      </tr>
      <tr id="">
        <td rowspan="2" width="70px" class="institution_basic_td_bg border_right border_bottom">卫技床比</td>
        <td width="70" class="institution_basic_td_bg border_right border_bottom">达标标准</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
      <tr id=""><td class="institution_basic_td_bg border_right border_bottom">达标情况</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
      <tr id="">
        <td rowspan="2" width="70px" class="institution_basic_td_bg border_right border_bottom">医床比</td>
        <td width="70" class="institution_basic_td_bg border_right border_bottom">达标标准</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
      <tr id=""><td class="institution_basic_td_bg border_right border_bottom">达标情况</td>
       <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
      <tr id="">
        <td rowspan="2" width="70px" class="institution_basic_td_bg border_right border_bottom">护床比</td>
        <td width="70" class="institution_basic_td_bg border_right border_bottom">达标标准</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
      <tr id=""><td class="institution_basic_td_bg border_right border_bottom">达标情况</td>
        <c:forEach items="${list}" var="map">
          <td class="institution_basic_td_color"></td>
        </c:forEach>
      </tr>
    </tbody>
  </table>
</body>
<script>
/* var myDate = new Date();
var list = '${list}';
list = eval('('+list+')');
$('#wardset_collection_title').attr('colspan',list.length+2);
$('#wardset_collection_title').html('医院各病区卫技人员配备情况一览表（截止'+myDate.getFullYear()+'）');
list.forEach(function(v,i) {
	for(var k in v) {
		$('#'+k).append('<td>'+v[k]+'</td>');
	}
}); */
</script>
</html>
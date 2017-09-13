<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>

<!--
 * @description 导出管理人员明细
 * @page name   list_manage_person_info.jsp
 * @author      duxw
 * @created     2017-03-02
-->

<%
	//String fileName = request.getParameter("fileName");
	response.setHeader("Content-Disposition", "attachment; filename="+new String(("管理人员明细.xls").getBytes("utf-8"), "ISO8859-1"));
%>
<html>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name=ProgId content=Excel.Sheet1>
<meta name=ProgId content=Excel.Sheet2>
<meta name=Generator content="Microsoft Excel">
<head>
<title>管理人员明细</title>
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
</head>

<body style="padding: 0">
	<table class="institution_basic" border=1>
    <tr align="center" height="25px">
      <td style="width: 10%;" class="institution_basic_td_bg border_right border_bottom"></td>
      <td style="width: 15%;" class="institution_basic_td_bg border_right border_bottom">岗位</td>
      <td style="width: 10%;" class="institution_basic_td_bg border_right border_bottom">姓名</td>
      <td style="width: 5%;" class="institution_basic_td_bg border_right border_bottom"> 年龄</td>
      <td style="width: 10%;" class="institution_basic_td_bg border_right border_bottom">学历</td>
      <td style="width: 10%;" class="institution_basic_td_bg border_right border_bottom">职称</td>
    </tr>
    <c:forEach items="${first }" var ="first" varStatus="status">
      <tr>
        <c:if test="${status.index==0}">
          <td  class="institution_basic_td_bg border_right border_right" height="25px" rowspan="${first.rowCount }">医院领导</td>
        </c:if>
        <td class="institution_basic_td_color" height="25px">${first.hisPositionName }</td>
        <td class="institution_basic_td_color" height="25px">${first.name }</td>
        <td class="institution_basic_td_color" height="25px">${first.age }</td>
        <td class="institution_basic_td_color" height="25px">${first.ftEducationLevelCode }</td>
        <td class="institution_basic_td_color" height="25px">${first.profTechName }</td>
      </tr>
    </c:forEach>
    <c:forEach items="${second }" var ="second" varStatus="status">
      <tr>
        <c:if test="${status.index==0}">
          <td  class="institution_basic_td_bg border_right border_bottom" height="25px" rowspan="${second.rowCount }">临床科室负责人</td>
        </c:if>
        <td class="institution_basic_td_color" height="25px">${second.hisPositionName }</td>
        <td class="institution_basic_td_color" height="25px">${second.name }</td>
        <td class="institution_basic_td_color" height="25px">${second.age }</td>
        <td class="institution_basic_td_color" height="25px">${second.ftEducationLevelCode }</td>
        <td class="institution_basic_td_color" height="25px">${second.profTechName }</td>
      </tr>
    </c:forEach>
    <c:forEach items="${third }" var ="third" varStatus="status">
      <tr>
        <c:if test="${status.index==0}">
          <td  class="institution_basic_td_bg border_right border_bottom" height="25px" rowspan="${third.rowCount }">各职能部门负责人</td>
        </c:if>
        <td class="institution_basic_td_color" height="25px">${third.hisPositionName }</td>
        <td class="institution_basic_td_color" height="25px">${third.name }</td>
        <td class="institution_basic_td_color" height="25px">${third.age }</td>
        <td class="institution_basic_td_color" height="25px">${third.ftEducationLevelCode }</td>
        <td class="institution_basic_td_color" height="25px">${third.profTechName }</td>
      </tr>
    </c:forEach>
    <c:forEach items="${fourth }" var ="fourth" varStatus="status">
      <tr>
        <c:if test="${status.index==0}">
          <td  class="institution_basic_td_bg border_right border_bottom" height="25px" rowspan="${fourth.rowCount }">医技辅助科室负责人</td>
        </c:if>
        <td class="institution_basic_td_color" height="25px">${fourth.hisPositionName }</td>
        <td class="institution_basic_td_color" height="25px">${fourth.name }</td>
        <td class="institution_basic_td_color" height="25px">${fourth.age }</td>
        <td class="institution_basic_td_color" height="25px">${fourth.ftEducationLevelCode }</td>
        <td class="institution_basic_td_color" height="25px">${fourth.profTechName }</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
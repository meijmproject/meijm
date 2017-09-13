<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="DicConstants"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="gov.szghrs.base.utils.DicParameter"%>
<%@ page import="gov.szghrs.base.utils.Constants"%>
 --%>
<!--
 * @description 管理人员明细
 * @page name   list_manage_person_info.jsp
 * @author      duxw
 * @created     2017-03-02
-->


<html>
<script type="text/javascript">
</script>

<head>
<title>管理人员明细</title>
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
</head>

<body style="padding: 0">
  <h3 style="height: 30px; padding: 5px 0;"><a class="btn-search btn-default" href="javascript:void(0)" class="popdown btn1" onclick="location.href='goViewManagePersonInfo.do?method=goviewManagePersonInfo&exportExcel=excel';">导出</a></h3>
  <table class="institution_basic">
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
          <td  class="institution_basic_td_bg border_right border_bottom" height="25px" rowspan="${first.rowCount }">医院领导</td>
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
<script>
/* $(function(){
    $('.hebing').each(function(index, element) {
        if(!$(this).hasClass('hide'))
        {    var next=$(this).parent('tr').next('tr').children('.hebing');//下一个合并的对象
            $(this).attr('rowspan',1);
                while($(this).text()==next.text())
                {
                    $(this).attr('rowspan',parseInt($(this).attr('rowspan'))+1);
                    next.hide();
                    next.addClass('hide');
                    next=next.parent('tr').next('tr').children('.hebing');//下一个合并的对象
                }
        }
    });
}); */
</script>
</html>
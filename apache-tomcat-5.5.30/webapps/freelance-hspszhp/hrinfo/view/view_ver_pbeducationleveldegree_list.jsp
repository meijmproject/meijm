<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    JhcPbEducationLevelDegree�鿴�б�ҳ��
 * @page name   /hrinfo/view/view_ver_jhcpbeducationleveldegree_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>信息校核列表页</title>
	<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
</head>
<logic:messagesPresent>
		<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
					<li>
						<bean:write name="error" />
					</li>
				</html:messages>
			</ul>
</logic:messagesPresent>
<body>

  <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">学历学位信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>学校</th>
                	<th>所在院系</th>
                	<th>入学日期</th>
                	<th>毕业日期</th>
                	<th>教育类别</th>
                	<th>学历</th>
                	<th>学位</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                     <td title="${dto.schoolName}">${dto.schoolName }</td>
                	<td title="${dto.academyName}">${dto.academyName }</td>
                	<td title="${dto.schoolEnrollDateStr}">${dto.schoolEnrollDateStr }</td>
                	<td title="${dto.graduateDateStr}">${dto.graduateDateStr }</td>
                	<td title="${dto.eduType}">${dto.eduType }</td>
                	<td title="${dto.educationCode}">${dto.educationCode }</td>
                	<td title="${dto.degreeCode}">${dto.degreeCode }</td>
                    <td>
                        <a href="showPbEducationLevelDegree.do?method=show&educationLevelOid=${dto.educationLevelOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>
</body>

<script type="text/javascript">

$(document).ready(function(){
    $('.popdown').popdown({width:1180, top: 50});
})

</script>
</html>
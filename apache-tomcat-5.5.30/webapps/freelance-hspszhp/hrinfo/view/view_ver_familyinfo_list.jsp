<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭成员与社会关系查看列表页面
 * @page name   /hrinfo/view/view_ver_familyinfo_list.jsp
 * @author      huangyj
 * @created     2016/08/18
 * @version     1.0
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
        <div class="st-title-box"><h3 class="st-title-text">家庭成员信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>姓名</th>
                	<th>与本人关系</th>
                	<th>出生日期</th>
                	<th>政治面貌</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                    <td title="${dto.name}">${dto.name }</td>
                	<td title="${dto.relationship}">${dto.relationship }</td>
                	<td title="${dto.birthdayStr}">${dto.birthdayStr }</td>
                	<td title="${dto.politicsVisage}">${dto.politicsVisage }</td>
                    <td>
                   <a href="showPbFamilyInfo.do?method=show&familyOid=${dto.familyOid}" class="popdown btn">查看</a>
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
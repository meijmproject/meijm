<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    JhcPbEducationTrainingInfo�鿴�б�ҳ��
 * @page name   /hrinfo/view/view_ver_jhcpbeducationtraininginfo_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>教育培训信息列表</title>
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
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>教育培训信息</span></div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>教育培训性质</td>
                	<td>培训类别</td>
                	<td>起始时间</td>
                	<td>终止时间</td>
                	<td>考核成绩</td>
                	<td>培训结果</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.educationTrainingKinkCode}">${dto.educationTrainingKinkCode}</td>
                	<td title="${dto.trainingType}">${dto.trainingType}</td>
                	<td title="${dto.trainingBeginDateStr}">${dto.trainingBeginDateStr }</td>
                	<td title="${dto.trainingEndDateStr}">${dto.trainingEndDateStr }</td>
                	<td title="${dto.trainingGrade}">${dto.trainingGrade }</td>
                	<td title="${dto.trainingResult}">${dto.trainingResult }</td>
                    <td>
                        <a href="showPbEducationTrainingInfo.do?method=show&educationTrainingOid=${dto.educationTrainingOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">教育培训信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>教育培训性质</th>
                	<th>培训类别</th>
                	<th>起始时间</th>
                	<th>终止时间</th>
                	<th>考核成绩</th>
                	<th>培训结果</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                     <td title="${dto.educationTrainingKinkCode}">${dto.educationTrainingKinkCode}</td>
                	<td title="${dto.trainingType}">${dto.trainingType}</td>
                	<td title="${dto.trainingBeginDateStr}">${dto.trainingBeginDateStr }</td>
                	<td title="${dto.trainingEndDateStr}">${dto.trainingEndDateStr }</td>
                	<td title="${dto.trainingGrade}">${dto.trainingGrade }</td>
                	<td title="${dto.trainingResult}">${dto.trainingResult }</td>
                    <td>
                        <a href="showPbEducationTrainingInfo.do?method=show&educationTrainingOid=${dto.educationTrainingOid}" class="popdown btn">查看</a>
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
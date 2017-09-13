<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    JhcPbInjuryInfo�鿴�б�ҳ��
 * @page name   /hrinfo/view/view_ver_jhcpbinjuryinfo_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>工伤信息查看</title>
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
        <div class="sys_base"><span>工伤信息</span></div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>受伤日期</td>
                    <td>伤残等级</td>
                    <td>认定公函</td>
                    <td>认定单位</td>
                    <td>备注</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.hurtDateStr}">${dto.hurtDateStr}</td>
                    <td title="${dto.hurtLevel}">${dto.hurtLevel}</td>
                    <td title="${dto.congizanceLetter}">${dto.congizanceLetter}</td>
                    <td title="${dto.judgeOrgan}">${dto.judgeOrgan}</td>
                    <td title="${dto.remark}">${dto.remark}</td>
                    <td>
                        <a href="showPbInjuryInfo.do?method=show&injuryOid=${dto.injuryOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">工伤信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>受伤日期</th>
                    <th>伤残等级</th>
                    <th>认定公函</th>
                    <th>认定单位</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                     <td title="${dto.hurtDateStr}">${dto.hurtDateStr}</td>
                    <td title="${dto.hurtLevel}">${dto.hurtLevel}</td>
                    <td title="${dto.congizanceLetter}">${dto.congizanceLetter}</td>
                    <td title="${dto.judgeOrgan}">${dto.judgeOrgan}</td>
                    <td>
                        <a href="showPbInjuryInfo.do?method=show&injuryOid=${dto.injuryOid}" class="popdown btn">查看</a>
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
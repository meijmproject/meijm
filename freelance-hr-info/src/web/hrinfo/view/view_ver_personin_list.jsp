<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员进入查看页面
 * @page name   pb_person_in.jsp
 * @author      cheny
 * @created     2017/03/20
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title> 人员进入信息列表页</title>
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
        <div class="st-title-box"><h3 class="st-title-text">人员进入信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>进入单位类型</th>
                	<th>进入单位时间</th>
                	<th>进入前所在单位</th>
                	<th>备注</th>
                	<th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                    <td><dictionary:viewDicItemName dicItemCode="${dto.entryCurrentUnitType}" dicTypeCode="<%=DicConstants.YHRS0110%>"/>&nbsp;</td>
                	<td >${dto.entryCurrentUnitDateStr}</td>
                	<td >${dto.personInFrom }</td>
                	<td >${dto.remark }</td>
                	<td>
                        <a href="showPbPersonIn.do?method=show&personInOid=${dto.personInOid}" class="popdown btn">查看</a>
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
    $('.popdown').popdown({width:1200});
})
</script>
</html>
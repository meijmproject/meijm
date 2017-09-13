<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    JhcPbDeathInfo�鿴�б�ҳ��
 * @page name   /hrinfo/view/view_ver_jhcpbdeathinfo_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>自然减员信息查看</title>
	<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
	<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
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
        <div class="sys_base"><span>自然减员</span></div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>死亡原因类别</td>
                    <td>死亡原因</td>
                    <td>抚恤金</td>
                    <td>丧葬费</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.deathType}">${dto.deathType}</td>
                    <td title="${dto.deathReason}">${dto.deathReason}</td>
                    <td title="<fmt:formatNumber value="${dto.smartMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>"><fmt:formatNumber value="${dto.smartMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
                    <td title="<fmt:formatNumber value="${dto.funeralMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>"><fmt:formatNumber value="${dto.funeralMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
                    <td>
                        <a href="showPbDeathInfo.do?method=show&personOid=${dto.personOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">自然减员</h3>
        <div class="st-title-icon">
    	</div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                    <th>死亡时间</th>
                	<th>死亡原因类别</th>
                    <th>抚恤金</th>
                    <th>丧葬费</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                      <td title="${dto.deathDateStr}">${dto.deathDateStr}</td>
                    <td title="${dto.deathReason}">${dto.deathReason}</td>
                    <td title="<fmt:formatNumber value="${dto.smartMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>"><fmt:formatNumber value="${dto.smartMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
                    <td title="<fmt:formatNumber value="${dto.funeralMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber>"><fmt:formatNumber value="${dto.funeralMoney}" pattern="#.##" minFractionDigits="2"></fmt:formatNumber></td>
                    <td>
                        <a href="showPbDeathInfo.do?method=show&personOid=${dto.personOid}" class="popdown btn">查看</a>
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
function uploadPbDeathInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '21'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
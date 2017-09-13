<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    政治面貌与党派信息信息列表
 * @page name   /hrinfo/view/view_ver_pbpoliticinfo_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>政治面貌与党派信息信息</title>
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
        <div class="sys_base"><span>政治面貌与党派信息信息</span></div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>政治面貌</td>
                    <td>参加党派时间</td>
                    <td>参加党派时所在单位</td>
                    <td>介绍人</td>
                    <td>转正时间</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />&nbsp;</td>
                    <td title="${dto.joinPoliticDateStr}">${dto.joinPoliticDateStr}</td>
                    <td title="${dto.unitOfJoinParty}">${dto.unitOfJoinParty}</td>
                    <td title="${dto.introducer}">${dto.introducer}</td>
                    <td title="${dto.probationDateStr}">${dto.probationDateStr}</td>
                    <td>
                        <a href="showPbPoliticInfo.do?method=show&politicOid=${dto.politicOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
  <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">政治面貌与党派信息信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	 <th>政治面貌</th>
                    <th>参加党派时间</th>
                    <th>参加党派时所在单位</th>
                    <th>介绍人</th>
                    <th>转正时间</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                     <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />&nbsp;</td>
                    <td title="${dto.joinPoliticDateStr}">${dto.joinPoliticDateStr}</td>
                    <td title="${dto.unitOfJoinParty}">${dto.unitOfJoinParty}</td>
                    <td title="${dto.introducer}">${dto.introducer}</td>
                    <td title="${dto.probationDateStr}">${dto.probationDateStr}</td>
                    <td>
                        <a href="showPbPoliticInfo.do?method=show&politicOid=${dto.politicOid}" class="popdown btn">查看</a>
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
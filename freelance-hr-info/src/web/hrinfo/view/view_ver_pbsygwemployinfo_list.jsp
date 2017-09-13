<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name  view_ver_pbsygwemployinfo_list.jsp
* @function   事业岗位聘任信息列表-查看
* @author     liul
* @created    2017-02-18
* @version    1.0
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
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>事业岗位聘任信息</span>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                <td width="50"><input type="checkbox"/></td>
                <td>岗位聘任状态</td>
                <td>岗位名称</td>
                <td>岗位类别</td>
                <td>岗位级别</td>
                <td>职务类型</td>
                <td>是否主岗位</td>
                <td>岗位聘任开始时间</td>
                <td width="100">操作</td>
            </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.positioningStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.positioningStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>&nbsp;
                    </td>
                    <td title="${dto.positionName}">${dto.positionName}</td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.positionType}" dicTypeCode="<%=DicConstants.YHRS0022%>"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.positionLevel}" dicTypeCode="<%=DicConstants.YHRS0023%>"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.dutyRecordType}" dicTypeCode="<%=DicConstants.YHRS0036%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.dutyRecordType}" dicTypeCode="<%=DicConstants.YHRS0036%>"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.isMPosition}" dicTypeCode="<%=DicConstants.YHRS0003%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.isMPosition}" dicTypeCode="<%=DicConstants.YHRS0003%>"/>&nbsp;
                    </td>
                    <td title="${dto.beginDateStr}">
                            ${dto.beginDateStr}
                    </td>
                    <td>
                        <a href="showPbSyGwEmployInfo.do?method=show&syGwEmployOid=${dto.syGwEmployOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">事业岗位聘任信息</h3>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>岗位聘任状态</th>
                <th>岗位名称</th>
                <th>岗位类别</th>
                <th>岗位级别</th>
                <th>职务类型</th>
                <th>是否主岗位</th>
                <th title="岗位聘任开始时间">岗位聘任开始时间</th>
                <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                   <td title="<dictionary:viewDicItemName dicItemCode="${dto.positioningStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.positioningStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>&nbsp;
                    </td>
                    <td title="${dto.positionName}">${dto.positionName}</td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.positionType}" dicTypeCode="<%=DicConstants.YHRS0022%>"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.positionLevel}" dicTypeCode="<%=DicConstants.YHRS0023%>"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.dutyRecordType}" dicTypeCode="<%=DicConstants.YHRS0036%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.dutyRecordType}" dicTypeCode="<%=DicConstants.YHRS0036%>"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.isMPosition}" dicTypeCode="<%=DicConstants.YHRS0003%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.isMPosition}" dicTypeCode="<%=DicConstants.YHRS0003%>"/>&nbsp;
                    </td>
                    <td title="${dto.beginDateStr}">
                            ${dto.beginDateStr}
                    </td>
                    <td>
                        <a href="showPbSyGwEmployInfo.do?method=show&syGwEmployOid=${dto.syGwEmployOid}" class="popdown btn">查看</a>
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
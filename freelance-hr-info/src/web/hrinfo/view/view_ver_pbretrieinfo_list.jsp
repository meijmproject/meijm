<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    JhcPbRetrieInfo�鿴�б�ҳ��
 * @page name   /hrinfo/view/view_ver_jhcpbretrieinfo_list.jsp
 * @author      Auto
 * @created     2017/02/16
 * @version     2.0
-->



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>离退休信息</title>
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
        <div class="sys_base"><span>离退休信息</span></div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>离退日期</td>
                    <td>提前退休原因</td>
                    <td>离退后享受待遇级别</td>
                    <td>离退后享受待遇类别</td>
                    <td>离退休费比例</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.retrieDateStr}">${dto.retrieDateStr}</td>
                    <td title="${dto.retrieReason}">${dto.retrieReason}</td>
                    <td title="${dto.retireTreatmentLevelCode}">${dto.retireTreatmentLevelCode}</td>
                    <td title="${dto.retireTreatmentTypeCode}">${dto.retireTreatmentTypeCode}</td>
                    <td title="${dto.retireFeeRatio}">${dto.retireFeeRatio}</td>
                    <td>
                        <a href="showPbRetrieInfo.do?method=show&personOid=${dto.personOid}" class="popdown btn">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">离退休信息</h3>
        <div class="st-title-icon">
        </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	 <th>离退日期</th>
                    <th>提前退休原因</th>
                    <th>离退休费支付单位</th>
					<th>离退休后管理单位</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                    <td title="${dto.retrieDateStr}">${dto.retrieDateStr}</td>
                    <td title="${dto.retrieReason}">${dto.retrieReason}</td>
                    <td title="${dto.retrieReason}">${dto.retrieReason}</td>
                    <td title="${dto.retirtPaymentUnit}">${dto.retirtPaymentUnit}</td>
                    <td>
                        <a href="showPbRetrieInfo.do?method=show&personOid=${dto.personOid}" class="popdown btn">查看</a>
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
function uploadPbRetrieInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '20'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
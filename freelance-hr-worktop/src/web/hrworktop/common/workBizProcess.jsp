<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!--
 * @function    查看业务信息
 * @page name   /freelance-hr-info/src/web/hrinfo/biz/mananger/ptperson/biz_ptperson_create.jsp
 * @author      xiongyx
 * @created     2016/10/10
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>查看业务信息</title>
</head>
<body>
	<div id="transaction_modal_box" class="transaction_modal_box">
		<div class="modal-dialog-container">
			<div class="md-title">
				查看业务信息&gt;查看审批记录<a href="#" class="md-close close-popdown"
					button-click="widget-close"></a>
			</div>
			<div class="md-main-content">
				<div class="infoshow-container md-infoshow-area">

					<div class="st-main-table">
						<table class="sr-table">
							<thead class="sr-table-thead">
								<tr><th style="width:5%!important"></th>
									<th style="width:15%!important">时间</th>
									<th style="width:10%!important">业务状态</th>
									<th style="width:10%!important">处理人</th>
									<th style="width:10%!important">办理部门</th>
									<th style="width:30%!important">处理意见</th>
								</tr>
							</thead>
							<tbody class="sr-table-tbody">
								<c:forEach items="${list}" var="BtLogDTO" varStatus="stat">
									<tr class="td_content">
									    <c:if test="${!stat.last}">
								<td style="width:5%!important">
									<ul class="bus_flow_path">
										<li class="bus_flow_span"><img
											src="img/business/check_transaction/bus_finish.png"
											class="bus_finish" alt="" /></li>
									</ul>
								</td>
							</c:if>
							<c:if test="${stat.last}">
								<td style="width:5%!important">
									<ul class="bus_flow_path">
										<li class="bus_flow_finish"><img
											src="img/business/check_transaction/under-report.png"
											class="under-report" alt="" /></li>
									</ul>
								</td>
							</c:if>
										<td style="width:15%!important"><bean:write name="BtLogDTO"
												property="createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
										<td style="width:10%!important">${BtLogDTO.bizStatusName}</td>
										<td style="width:10%!important">${BtLogDTO.createdByName}</td>
										<td style="width:10%!important">${BtLogDTO.processDeptName}</td>
										<td style="width:30%!important" title='${BtLogDTO.remark}'>${BtLogDTO.remark}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="md-btn">
			<button type="button" class="md-btn-cancel close-popdown"
				data-dismiss="modal" button-click="widget-close">关 闭</button>
		</div>
	</div>
	<%-- <div class="modal-body">
		<div style="height: 400px">
			<table class="transaction_modal_table2">
				<tr class="transaction_modal_table_title"
					style="border-bottom: 1px solid #ddd;">
					<td width="5%!important"></td>
					<td style="width:20%!important">时间</td>
					<td style="width:15%!important">业务状态</td>
					<td style="width:15%!important">处理人</td>
					<td style="width:15%!important">办理科室</td>
					<td style="width:15%!important">处理意见</td>
				</tr>
			</table>
			<div class="transaction_modal_table_table">
				<table class="transaction_modal_table">
					<c:forEach items="${list}" var="BtLogDTO" varStatus="stat">
						<tr class="transaction_modal_table_content bus_finnish">
							<c:if test="${!stat.last}">
								<td width="5%!important" class="bus_table1_td">
									<ul class="bus_flow_path">
										<li class="bus_flow_span"><img
											src="img/business/check_transaction/bus_finish.png"
											class="bus_finish" alt="" /></li>
									</ul>
								</td>
							</c:if>
							<c:if test="${stat.last}">
								<td width="5%!important" class="bus_table1_td">
									<ul class="bus_flow_path">
										<li class="bus_flow_finish"><img
											src="img/business/check_transaction/under-report.png"
											class="under-report" alt="" /></li>
									</ul>
								</td>
							</c:if>
							<td style="width:20%!important"><bean:write name="BtLogDTO"
									property="createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td style="width:15%!important">${BtLogDTO.bizStatusName}</td>
							<td style="width:15%!important">${BtLogDTO.createdByName}</td>
							<td style="width:15%!important">${BtLogDTO.processDeptName}</td>
							<td style="width:15%!important" title='${BtLogDTO.remark}'>${BtLogDTO.remark}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div> --%>
</body>
</html>
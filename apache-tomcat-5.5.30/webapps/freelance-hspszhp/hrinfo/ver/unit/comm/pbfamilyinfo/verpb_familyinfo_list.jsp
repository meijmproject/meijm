<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    家庭主要成员情况查看列表页面
 * @page name   verpb_familyinfo_list.jsp
 * @author      chenp
 * @created     2017/02/16
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>信息校核列表页</title>
<!-- <script type="text/javascript" src="hrinfo/ver/unit/comm/pbfamilyinfo/js/check_family_info.js"></script> -->
</head>
<logic:messagesPresent>
	<bean:message key="errors.header" />
	<ul>
		<html:messages id="error">
			<li><bean:write name="error" /></li>
		</html:messages>
	</ul>
</logic:messagesPresent>
<body>
	<div class="infoshow-container padding-lrb">
		<div class="st-title-box">
			<h3 class="st-title-text">家庭成员信息</h3>
			<div class="st-title-icon st-title-button">
				<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goInsertPbFamilyInfo.do?method=goInsert&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
				 <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadFamilyInfo('${param.personOid}')">上传附件</button>
			</div>
			<%-- <a style="float:right;margin-left: 20px;" href="goInsertPbFamilyInfo.do?method=goInsert&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a> --%>
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
					<c:forEach var="dto" items="${list}" varStatus="status">
						<tr>
							<td class="md-th">${status.count}</td>
							<td class="md-th"><input type="checkbox" /></td>
							<td title="${dto.name}">${dto.name }</td>
							<td title="${dto.relationship}">${dto.relationship }</td>
							<td title="${dto.birthdayStr}">${dto.birthdayStr }</td>
							<td title="${dto.politicsVisage}">${dto.politicsVisage }</td>
							<td><a class="st-handle-delete" href="javascript:void(0);" onclick="deleteFamilyInfo('${dto.familyOid}')"></a> <a class="st-handle-modify popdown btn" href="goUpdatePbFamilyInfo.do?method=goUpdate&urlId=${param.Id}&familyOid=${dto.familyOid}"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('.popdown').popdown({
			width : 1200
		});

		//滚动效果
		$(window).load();
		tableScorll = function() {
			//表头和tbody内容相等
			var ths = $(".sr-table-thead tr th");
			var td = $(".sr-table-tbody tr td");
			for ( var i = 0; i < td.length; i++) {
				$(ths[i]).width($(td[i]).width());
			}
			//表头滚动
			$(".sr-table-tbody").scroll(function(e) {
				$(this).siblings(".sr-table-thead").css({
					left : -this.scrollLeft
				});
			});
		}
		tableScorll();
	})

	function deleteFamilyInfo(familyOid) {
		MessageBox.confirm('提示', '确认删除？', function(action) {
			if (action == 'yes') {
				$.ajax({
					url : 'deletePbFamilyInfo.do?method=delete',
					data : {
						familyOid : familyOid
					},
					dataType : 'json',
					error : function(x, t) {
						alert(t);
						alert("error occured!!!");
					},
					async : false,
					success : function(data) {
						if (data.success) {
							$('#${param.Id}').load(
									$('#${param.Id}').attr('url'), {
										personOid : '${param.personOid}',
										Id : '${param.Id}'
									});
						} else {
							alert(data.message);
						}
					}
				});
			}
		});
	}
	function uploadFamilyInfo(personOid) {

		var params = {
			personOid : personOid//字节 5MB
			,refType : '01'
		}
		BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
	}
</script>
</html>
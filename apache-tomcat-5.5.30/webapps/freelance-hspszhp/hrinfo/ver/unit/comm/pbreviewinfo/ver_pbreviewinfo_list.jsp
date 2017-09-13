<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    考核信息页面
 * @page name  /hrinfo/ver/unit/comm/pbreviewinfo/ver_pb_review_info_insert.jsp
 * @author      sunzc
 * @created     2016/08/19
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>信息校核列表页</title>
<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
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
<input type="hidden" id="unitOid" name="unitOid" value="${unitOid}" />

<div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">年度考核信息</h3>
	       <div class="st-title-icon st-title-button">
	           <button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbReviewInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}&unitOid=${unitOid}">新增</button>
	           <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPbReviewInfo('${param.personOid}')">上传附件</button>
	       </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>考核类别</th>
					<th>考核年度</th>
					<th>考核起始日期</th>
					<th>考核时所在科室</th>
					<th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                    <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                   <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0069%>" dicItemCode="${dto.reviewTypeCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0069%>" dicItemCode="${dto.reviewTypeCode}" />&nbsp;</td>
						<td title="${dto.reviewYearStr }">${dto.reviewYearStr }</td>
						<td title="${dto.reviewDateStr }">${dto.reviewDateStr }</td>
						<td title="${dto.reviewUnitName }">${dto.reviewUnitName }</td>
                    <td>
                    <a class="st-handle-delete" href="javascript:void(0);" onclick="deletePbReviewInfo('${dto.reviewOid}')"></a>
                    <a class="st-handle-modify popdown btn" href="goToUpdatePbReviewInfoPage.do?method=goUpdate&urlId=${param.Id}&reviewOid=${dto.reviewOid}&personOid=${personOid}&unitOid=${unitOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>
 

	<%-- <div class="sys_box sys_box_up">
		<div class="sys_base">
			<span>年度考核信息</span> <a style="float: right; margin-left: 20px;"
				href="goToCreatePbReviewInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}&unitOid=${unitOid}"
				class="popdown btn"><img
				src="img/DetailPages/icon_add_green.png" />
			</a>
		</div>
		<div class="sys_list">
			<table class="table_edit_bg" width="95%">
				<tr class="td_title">
					<td width="50"><input type="checkbox" />
					</td>
					<td>考核类别</td>
					<td>考核年度</td>
					<td>考核起始日期</td>
					<td>考核时所在科室</td>
					<td width="100">操作</td>
				</tr>
				<c:forEach var="dto" items="${list}">
					<tr class="td_content">
						<td width="50"><input type="checkbox" />
						</td>
						<td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0069%>" dicItemCode="${dto.reviewTypeCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0069%>" dicItemCode="${dto.reviewTypeCode}" />&nbsp;</td>
						<td title="${dto.reviewYearStr }">${dto.reviewYearStr }</td>
						<td title="${dto.reviewDateStr }">${dto.reviewDateStr }</td>
						<td title="${dto.reviewUnitName }">${dto.reviewUnitName }</td>
						<td><a  href="javascript:void(0);"
							onclick="deletePbReviewInfo('${dto.reviewOid}')"><img
								src="img/DetailPages/icon_delete.png" />
						</a> <a
							href="goToUpdatePbReviewInfoPage.do?method=goUpdate&urlId=${param.Id}&reviewOid=${dto.reviewOid}&personOid=${personOid}&unitOid=${unitOid}"
							class="popdown btn"><img
								src="img/DetailPages/icon_revise.png" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div> --%>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('.popdown').popdown({
			width : 1200
		});
	})

	function deletePbReviewInfo(reviewOid) {
        MessageBox.confirm('提示', '确认删除？',function(action)
                {
                    if (action == 'yes') 
                    {
		$.ajax({
			url : 'deletePbReviewInfo.do?method=delete',
			data : {
				reviewOid : reviewOid
			},
			dataType : 'json',
			error : function(x, t) {
				alert(t);
				alert("error occured!!!");
			},
			async : false,
			success : function(data) {
				if (data.success) {
					$('#${param.Id}').load($('#${param.Id}').attr('url'), {
						personOid : '${param.personOid}',
						Id : '${param.Id}'
					});
				}
                else
                {
                    alert(data.message);
                }
            }
        });
    }
});
}

	function reward_close_model() {
		function close_modalPbPunishmentInfo() {
			$("#lean_overlay").fadeOut(200);
			$("#createmodalRewardInfo").css({
				"display" : "none"
			})
			$("#updatemodalRewardInfo").css({
				"display" : "none"
			})

		}
	}
	function uploadPbReviewInfo(personOid){
		
		var params = {
			personOid : personOid//字节 5MB
			,refType : '15'
		}
		BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
	}
</script>
</html>
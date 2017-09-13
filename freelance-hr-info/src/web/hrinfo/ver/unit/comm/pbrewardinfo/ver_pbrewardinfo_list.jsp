<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员奖励信息列表页面
 * @page name   /freelance-hr-info/src/web/hrinfo/ver/unit/comm/ver_pbreward_list.jsp
 * @author      wuxq
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>信息校核列表页</title>
	<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
	<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
		        $('.popdown').popdown({width:1200});
		    })
	</script>
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
  <div class="st-title-box">
    <h3 class="st-title-text">奖励信息</h3>
    <div class="st-title-icon st-title-button">
      <button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbRewardInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
      <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPbRewardInfo('${param.personOid}')">上传附件</button>
    </div>
  </div>
  <div class="st-main-table">
    <table class="sr-table">
      <thead class="sr-table-thead">
        <tr>
          <th class="md-th"><input type="checkbox"></th>
          <th>奖励名称</th>
          <th>奖励类别</th>
          <th>授予荣誉称号级别</th>
          <th>奖励批准日期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody class="sr-table-tbody">
        <c:forEach var ="dto" items="${list}">
          <tr class="td_content">
            <td class="md-th"><input type="checkbox"/></td>
            <td title="${dto.rewardName }">${dto.rewardName }</td>
            <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0076%>" dicItemCode="${dto.rewardTypeCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0076%>" dicItemCode="${dto.rewardTypeCode}" />&nbsp;</td>
            <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0077%>" dicItemCode="${dto.honourLevel}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0077%>" dicItemCode="${dto.honourLevel}" />&nbsp;</td>
            <td title="${dto.rewardDateStr }">${dto.rewardDateStr }</td>
            <td>
              <a class="st-handle-delete" href="javascript:void(0);" onclick="deletePbRewardInfo('${dto.rewardOid}')"></a>
              <a class="st-handle-modify popdown btn" href="goToUpdatePbRewardInfoPage.do?method=goUpdate&urlId=${param.Id}&rewardOid=${dto.rewardOid}"></a>
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

function deletePbRewardInfo(rewardOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
{		
if (action == 'yes') {
	$.ajax({
		url : 'deletePbRewardInfo.do?method=delete',
		data :  {rewardOid:rewardOid},
		dataType : 'json', 
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data.success) {
				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
			}
		}
	});
}
});
}
function reward_close_model(){
	function close_modalPbPunishmentInfo(){
		$("#lean_overlay").fadeOut(200);
		$("#createmodalRewardInfo").css({"display":"none"})
		$("#updatemodalRewardInfo").css({"display":"none"})
		
	}
}
function uploadPbRewardInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '17'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
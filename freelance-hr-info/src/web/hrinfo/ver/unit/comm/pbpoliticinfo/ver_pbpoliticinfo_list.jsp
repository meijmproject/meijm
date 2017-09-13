<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    政治面貌与党派信息查看列表页面
 * @page name   /hrinfo/ver/unit/comm/pbpoliticinfo/verpb_politicinfo_list.jsp
 * @author      huangyj
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
	<!-- <script type="text/javascript" src="hrinfo/ver/unit/comm/pbpoliticinfo/js/check_politic_info.js"></script> -->
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
    <h3 class="st-title-text">政治面貌与党派信息</h3>
    <div class="st-title-icon st-title-button">
      <button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbPoliticInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
      <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPoliticInfo('${param.personOid}')">上传附件</button>
    </div>
  </div>
  <div class="st-main-table">
    <table class="sr-table">
      <thead class="sr-table-thead">
        <tr>
          <th class="md-th"><input type="checkbox"></th>
          <th>政治面貌</th>
          <th>参加党派时间</th>
          <th>介绍人</th>
          <th>参加党派时所在单位</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody class="sr-table-tbody">
        <c:forEach var ="dto" items="${list}">
          <tr class="td_content">
            <td class="md-th"><input type="checkbox"/></td>
            <td title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${dto.politicStatusCode}" />&nbsp;</td>
            <td title="${dto.joinPoliticDateStr }">${dto.joinPoliticDateStr }</td>
            <td title="${dto.introducer }">${dto.introducer }</td>
            <td title="${dto.unitOfJoinParty }">${dto.unitOfJoinParty }</td>
            <td>
              <a class="st-handle-delete" href="javascript:void(0);" onclick="deletePoliticInfo('${dto.politicOid}')"></a>
              <a class="st-handle-modify popdown btn" href="goToUpdatePbPoliticInfoPage.do?method=goUpdate&politicOid=${dto.politicOid}&urlId=${param.Id}&personOid=${personOid}"></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
<script type="text/javascript">
/* $(document).ready(function(){
    $('#modaltriggerCreatePolitic').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
    $('#modaltriggerUpdatePolitic').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
})
function closePoliticModal(){
	$("#lean_overlay").fadeOut(200);
	$("#createmodalPoliticInfo").css({"display":"none"})
	$("#updatemodalPoliticInfo").css({"display":"none"})
}
function createPoliticInfo(){
	if(!validatePoliticInfoCreate())
	{
		return;
	}
	
	$.ajax({
		url : 'insertPbPoliticInfo.do?method=insert&personOid=${personOid}',
		data :  $("#formCreatePoliticInfo").serializeArray(),
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data.success) {
				closePoliticModal();
				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
			}
		}
	});
}
function updatePoliticInfo(politicOid){
	if(!validatePoliticInfoUpdate())
	{
		return;
	}
	
	$.ajax({
		url : 'updatePbPoliticInfo.do?method=update&politicOid='+politicOid+'&personOid=${personOid}',
		data :  $("#formUpdatePoliticInfo").serializeArray(),
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data.success) {
				closePoliticModal();
				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
				$('#modaltriggerUpdatePolitic').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
			}
		}
	});
}
function goUpdatePoliticInfo(politicOid){
	$.ajax({
		url : 'goUpdatePbPoliticInfo.do?method=goUpdate',
		data :  {politicOid:politicOid},
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data) {
				$("#u_politicStatusCode").val(data.politicStatusCode);
				$("#u_joinPoliticDateStr").val(data.joinPoliticDateStr);
				$("#u_unitOfJoinParty").val(data.unitOfJoinParty);
				$("#u_introducer").val(data.introducer);
				$("#u_probationDateStr").val(data.probationDateStr);
				$("#u_politicDutyCode").val(data.politicDutyCode);
				$("#u_outPartyDateStr").val(data.outPartyDateStr);
				$("#u_abnormityType").val(data.abnormityType);
				$("#u_abnormityReason").val(data.abnormityReason);
				$("#u_politicOid").val(data.politicOid);
				$("#u_personOid").val(data.personOid);
			}
		}
	});
} */

$(document).ready(function(){
    $('.popdown').popdown({width:1180, top: 50});
})

function deletePoliticInfo(politicOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
	if (action == 'yes') {
	$.ajax({
		url : 'deletePbPoliticInfo.do?method=delete',
		data :  {politicOid:politicOid},
		dataType : 'json',
		error : function(x,t) {
			alert(t);
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
	})
}
function uploadPoliticInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '16'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
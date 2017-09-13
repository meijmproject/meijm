<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    教育培训信息列表页面
 * @page name   /hrinfo/ver/unit/comm/pbeducatiotraininginfo/ver_pbeducatiotraininginfo_list.jsp
 * @author      huangyj
 * @created     2017/02/13
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
	<!-- <script type="text/javascript" src="hrinfo/ver/unit/comm/pbeducationinfo/js/check_education_info.js"></script> -->
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
        <div class="st-title-box"><h3 class="st-title-text">教育培训信息</h3>
	       <div class="st-title-icon st-title-button">
		        <c:if test="${'1' ne flag}">
		            <i>
		            	<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbEducationTrainingInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增<button/>
	           			<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadEducationTrainingInfo('${param.personOid}')">上传附件</button>
		            </i>
	        	</c:if>
	       </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>教育培训性质</th>
                	<th>培训类别</th>
                	<th>起始时间</th>
                	<th>终止时间</th>
                	<th>考核成绩</th>
                	<th>培训结果</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                 <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                 	 <td title="${dto.educationTrainingKinkCode}">${dto.educationTrainingKinkCode}</td>
                	<td title="${dto.trainingType}">${dto.trainingType}</td>
                	<td title="${dto.trainingBeginDateStr}">${dto.trainingBeginDateStr }</td>
                	<td title="${dto.trainingEndDateStr}">${dto.trainingEndDateStr }</td>
                	<td title="${dto.trainingGrade}">${dto.trainingGrade }</td>
                	<td title="${dto.trainingResult}">${dto.trainingResult }</td>
                	 <td>
                    	<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteEducationTrainingInfo('${dto.educationTrainingOid}')"></a>
                        <a class="st-handle-modify popdown btn" href="goToUpdatePbEducationTrainingInfoPage.do?method=goUpdate&urlId=${param.Id}&educationTrainingOid=${dto.educationTrainingOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>
 
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>教育培训信息</span>
            <a style="float:right;margin-left: 20px;" href="goToCreatePbEducationTrainingInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>教育培训性质</td>
                	<td>培训类别</td>
                	<td>起始时间</td>
                	<td>终止时间</td>
                	<td>考核成绩</td>
                	<td>培训结果</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.educationTrainingKinkCode}">${dto.educationTrainingKinkCode}</td>
                	<td title="${dto.trainingType}">${dto.trainingType}</td>
                	<td title="${dto.trainingBeginDateStr}">${dto.trainingBeginDateStr }</td>
                	<td title="${dto.trainingEndDateStr}">${dto.trainingEndDateStr }</td>
                	<td title="${dto.trainingGrade}">${dto.trainingGrade }</td>
                	<td title="${dto.trainingResult}">${dto.trainingResult }</td>
                    <td>
                    	<a href="javascript:void(0);" onclick="deleteEducationTrainingInfo('${dto.educationTrainingOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbEducationTrainingInfoPage.do?method=goUpdate&urlId=${param.Id}&educationTrainingOid=${dto.educationTrainingOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
</body>
<script type="text/javascript">
/* $(document).ready(function(){
    $('#modaltriggerCreateEducation').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
    $('#modaltriggerUpdateEducation').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
    $('.popdown').popdown({width:1180, top: 50});
})
function closeEducationModal(){
	$("#lean_overlay").fadeOut(200);
	$("#createmodalEducationInfo").css({"display":"none"})
	$("#updatemodalEducationInfo").css({"display":"none"})
}
function createEducationInfo(){
	if(!validateEducationInfoCreate())
	 {
		 return;
	 }
	
	$.ajax({
		url : 'insertPbEducationInfo.do?method=insert&personOid=${personOid}',
		data :  $("#formCreateEducation").serializeArray(),
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data.success) {
				alert("新增成功！");
				closeEducationModal();
				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
			}
		}
	});
}
function updateEducationInfo(educationTrainingOid){
	if(!validateEducationInfoUpdate())
	 {
		 return;
	 }
	
	$.ajax({
		url : 'updatePbEducationInfo.do?method=update&educationTrainingOid='+educationTrainingOid+'&personOid=${personOid}',
		data :  $("#formUpdateEducation").serializeArray(),
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data.success) {
				alert("修改成功！");
				closeEducationModal();
				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
				$('#modaltriggerUpdateEducation').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });
			}
		}
	});
}
function goUpdateEducationInfo(educationTrainingOid){
	$.ajax({
		url : 'goUpdatePbEducationInfo.do?method=goUpdate',
		data :  {educationTrainingOid:educationTrainingOid},
		dataType : 'json',
		error : function(x,t) {
			alert(t)
			alert("error occured!!!");
		},
		async : false,
		success : function(data) {
			if (data) {
				$("#u_schoolName").val(data.schoolName);
				$("#u_academyName").val(data.academyName);
				$("#u_eduTypeCode").val(data.eduTypeCode);
				$("#u_studyTypeCode").val(data.studyTypeCode);
				$("#u_eductionalSystem").val(data.eductionalSystem);
				$("#u_majorName").val(data.majorName);
				$("#u_schoolEnrollDateStr").val(data.schoolEnrollDateStr);
				$("#u_graduateDateStr").val(data.graduateDateStr);
				$("#u_isHighestEducationLevel").val(data.isHighestEducationLevel);
				$("#u_educationCode").val(data.educationCode);
				$("#u_educationCertificate").val(data.educationCertificate);
				$("#u_educationLevelCode").val(data.educationLevelCode);
				$("#u_degreeCode").val(data.degreeCode);
				$("#u_degreeCertificateNo").val(data.degreeCertificateNo);
				$("#u_isHighestDegree").val(data.isHighestDegree);
				$("#u_degreeGrantDateStr").val(data.degreeGrantDateStr);
				$("#u_degreeGrantUnit").val(data.degreeGrantUnit);
				$("#u_degreeGrantCountryCode").val(data.degreeGrantCountryCode);
				$("#u_remark").val(data.remark);
				$("#u_educationTrainingOid").val(data.educationTrainingOid);
				$("#u_personOid").val(data.personOid);
			}
		}
	});
} */

$(document).ready(function(){
    $('.popdown').popdown({width:1200});
})

function deleteEducationTrainingInfo(educationTrainingOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbEducationTrainingInfo.do?method=delete',
				data :  {educationTrainingOid:educationTrainingOid},
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
					else
                    {
                        alert(data.message);
                    }
				}
			});
		}
	});
}
function uploadEducationTrainingInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '09'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
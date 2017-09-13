<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    学历学位信息列表页面
 * @page name   /hrinfo/ver/unit/comm/pbeducationleveldegree/ver_pbeducationleveldegree_list.jsp
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
        <div class="st-title-box"><h3 class="st-title-text">学历学位信息</h3>
	       <div class="st-title-icon st-title-button">
		        <c:if test="${'1' ne flag}">
		            <i>
		            	<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbEducationLevelDegreePage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
	           			<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadEducationInfo('${param.personOid}')">上传附件</button>
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
                	<th>学校</th>
                	<th>所在院系</th>
                	<th>入学日期</th>
                	<th>毕业日期</th>
                	<th>教育类别</th>
                	<th>学历</th>
                	<th>学位</th>
                    <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                 <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                 <td title="${dto.schoolName}">${dto.schoolName }</td>
                	<td title="${dto.academyName}">${dto.academyName }</td>
                	<td title="${dto.schoolEnrollDateStr}">${dto.schoolEnrollDateStr }</td>
                	<td title="${dto.graduateDateStr}">${dto.graduateDateStr }</td>
                	<td title="${dto.eduType}">${dto.eduType }</td>
                	<td title="${dto.educationCode}">${dto.educationCode }</td>
                	<td title="${dto.degreeCode}">${dto.degreeCode }</td>
                	 <td>
                    	<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteEducationInfo('${dto.educationLevelOid}')"></a>
                        <a class="st-handle-modify popdown btn" href="goToUpdatePbEducationLevelDegreePage.do?method=goUpdate&urlId=${param.Id}&educationLevelOid=${dto.educationLevelOid}"></a>
                    </td>
                </tr>
                </c:forEach>
             </tbody>
            </table>
        </div>
 </div>

<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>学历学位信息</span>
            <a style="float:right;margin-left: 20px;" href="goToCreatePbEducationLevelDegreePage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>学校</td>
                	<td>所在院系</td>
                	<td>入学日期</td>
                	<td>毕业日期</td>
                	<td>教育类别</td>
                	<td>学历</td>
                	<td>学位</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.schoolName}">${dto.schoolName }</td>
                	<td title="${dto.academyName}">${dto.academyName }</td>
                	<td title="${dto.schoolEnrollDateStr}">${dto.schoolEnrollDateStr }</td>
                	<td title="${dto.graduateDateStr}">${dto.graduateDateStr }</td>
                	<td title="${dto.eduType}">${dto.eduType }</td>
                	<td title="${dto.educationCode}">${dto.educationCode }</td>
                	<td title="${dto.degreeCode}">${dto.degreeCode }</td>
                    <td>
                    	<a href="javascript:void(0);" onclick="deleteEducationInfo('${dto.educationLevelOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbEducationLevelDegreePage.do?method=goUpdate&urlId=${param.Id}&educationLevelOid=${dto.educationLevelOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
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
function updateEducationInfo(educationLevelOid){
	if(!validateEducationInfoUpdate())
	 {
		 return;
	 }
	
	$.ajax({
		url : 'updatePbEducationInfo.do?method=update&educationLevelOid='+educationLevelOid+'&personOid=${personOid}',
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
function goUpdateEducationInfo(educationLevelOid){
	$.ajax({
		url : 'goUpdatePbEducationInfo.do?method=goUpdate',
		data :  {educationLevelOid:educationLevelOid},
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
				$("#u_educationLevelOid").val(data.educationLevelOid);
				$("#u_personOid").val(data.personOid);
			}
		}
	});
} */

$(document).ready(function(){
    $('.popdown').popdown({width:1200});
})

function deleteEducationInfo(educationLevelOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbEducationLevelDegree.do?method=delete',
				data :  {educationLevelOid:educationLevelOid},
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
function uploadEducationInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '08'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
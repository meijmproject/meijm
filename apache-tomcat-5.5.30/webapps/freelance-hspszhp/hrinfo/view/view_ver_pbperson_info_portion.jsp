<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>

<!--
 * @function    人员基础信息查看页面
 * @page name   view_ver_pbperson_info.jsp
 * @author      luqy
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<title>人员查看页面</title>
<script src="hrinfo/ver/unit/person/js/VerPbPersonOperate.js"></script>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="current-position">
    	当前位置：
        <span>基础信息管理 > </span>
        <span>信息校核 > </span>
        <span>个人信息维护 </span>
    <div style="clear: both"></div>
</div>
	<div class="infoshow-container  padding-lrb">
		<div class="isc-title-box">
			<h3 class="isc-title">基本信息</h3>
		</div>
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">
				基础信息
				<c:if test="${flag ne '1'}">
					<!-- <button class="btn-modify-gray btn-icon-default"></button> -->
					<!-- 跳转人员管理修改页面 -->
					<c:if test="${updateFlag eq '2'}">
						<button href="goToUpdateManagePersonPage.do?method=goToUpdateVerPersonPage&personOid=${param.personOid}&id=${param.Id}" class="popdown btn-modify btn-left-icon btn-default btn-right">修改</button>
					</c:if>
					<!-- 跳转人员校核修改页面 -->
					<c:if test="${updateFlag ne '2'}">
						<button href="goToUpdateVerPersonPortion.do?method=goToUpdateVerPersonPage&personOid=${param.personOid}&id=${param.Id}" class="popdown btn-modify btn-left-icon btn-default btn-right">修改</button>
					</c:if>
		
			<button title="请上传身份证正反面图片"  class="btn-upload btn-left-icon btn-default btn-right"  onclick="uploadIdCard('${param.personOid}')">上传身份证</button>

				</c:if>
				
				
			</h2>
			<div class="data-units">
				<dl class="data-unit-dl photo-unit">
				    <dt>照片</dt>
				    <dd><img name="offerImg" id="offerImg" src="<%=request.getContextPath()%>/hrinfo/ver/unit/comm/image_view.jsp?personOid=${verPbPersonInfoForm.personOid}&_<%=new java.util.Random().nextInt(1000)%>" alt="照片" /></dd>
					
				</dl>
				<dl class="data-unit-dl">
					<dt>姓名：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="name" />'><bean:write name="verPbPersonInfoForm" property="name" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>性别：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0001%>" dicItemCode="${verPbPersonInfoForm.sexCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0001%>" dicItemCode="${verPbPersonInfoForm.sexCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>工号：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="personCode" />'><bean:write name="verPbPersonInfoForm" property="personCode" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>所在科室：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="hireDeptName" />'><bean:write name="verPbPersonInfoForm" property="hireDeptName" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>人员状态：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0009%>" dicItemCode="${verPbPersonInfoForm.personStatus}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0009%>" dicItemCode="${verPbPersonInfoForm.personStatus}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>人员类别：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0010%>" dicItemCode="${verPbPersonInfoForm.personType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0010%>" dicItemCode="${verPbPersonInfoForm.personType}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>参加工作时间：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="startWorkDateStr" />'><bean:write name="verPbPersonInfoForm" property="startWorkDateStr" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>民族：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0004%>" dicItemCode="${verPbPersonInfoForm.peopleCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0004%>" dicItemCode="${verPbPersonInfoForm.peopleCode}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>政治面貌：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${verPbPersonInfoForm.politicStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0025%>" dicItemCode="${verPbPersonInfoForm.politicStatusCode}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>籍贯：</dt>
					<dd><label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.ancestorPlaceCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.ancestorPlaceCode}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>出生地：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.birthplaceCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.birthplaceCode}" /></label>
						
					</dd>
				</dl>
				<%-- <dl class="data-unit-dl">
					<dt>人员排序号：</dt>
					<dd><label title='<bean:write name="verPbPersonInfoForm" property="personOrderView" />'><bean:write name="verPbPersonInfoForm" property="personOrderView" /></label>
						
					</dd>
				</dl> --%>
				<dl class="data-unit-dl">
					<dt>户口所在地：</dt>
					<dd><label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.hukouPlace}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.hukouPlace}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>是否本地户口：</dt>
					<dd><label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isSz}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isSz}" /></label>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>家庭住址：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="address" />'><bean:write name="verPbPersonInfoForm" property="address" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>证件类别：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${verPbPersonInfoForm.idCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${verPbPersonInfoForm.idCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>证件号码：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="idNo" />'><bean:write name="verPbPersonInfoForm" property="idNo" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>出生日期：</dt>
					<dd>
						<label id='birthdayStr' title='<bean:write name="verPbPersonInfoForm" property="birthdayStr" />'><bean:write name="verPbPersonInfoForm" property="birthdayStr" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>年龄：</dt>
					<dd>
						<label id='age'></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>婚姻状况：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0008%>" dicItemCode="${verPbPersonInfoForm.marriageStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0008%>" dicItemCode="${verPbPersonInfoForm.marriageStatusCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>健康状况：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0007%>" dicItemCode="${verPbPersonInfoForm.healthStatusCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0007%>" dicItemCode="${verPbPersonInfoForm.healthStatusCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>国籍：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0005%>" dicItemCode="${verPbPersonInfoForm.nationalityCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0005%>" dicItemCode="${verPbPersonInfoForm.nationalityCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>手机号码：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="mobilePhone" />'><bean:write name="verPbPersonInfoForm" property="mobilePhone" /></label>
					</dd>
				</dl>
				<%-- <dl class="data-unit-dl">
					<dt>办公电话：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="officePhone" />'><bean:write name="verPbPersonInfoForm" property="officePhone" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>个人Email地址：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="email" />'><bean:write name="verPbPersonInfoForm" property="email" /></label>
					</dd>
				</dl> --%>
				<%-- <dl class="data-unit-dl">
					<dt>编制类型：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0012%>" dicItemCode="${verPbPersonInfoForm.dPositionType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0012%>" dicItemCode="${verPbPersonInfoForm.dPositionType}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>经费形式：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0013%>" dicItemCode="${verPbPersonInfoForm.bankpoll}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0013%>" dicItemCode="${verPbPersonInfoForm.bankpoll}" /></label>
					</dd>
				</dl> --%>
				<dl class="data-unit-dl">
					<dt>护士层级：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0122%>" dicItemCode="${verPbPersonInfoForm.levelCode}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0122%>" dicItemCode="${verPbPersonInfoForm.levelCode}" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>紧急联系人：</dt>
					<dd>
					<label title='<bean:write name="verPbPersonInfoForm" property="emergContact" />'><bean:write name="verPbPersonInfoForm" property="emergContact" /></label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>紧急联系人手机号码：</dt>
					<dd>
					<label title='<bean:write name="verPbPersonInfoForm" property="emergPhone" />'><bean:write name="verPbPersonInfoForm" property="emergPhone" /></label>
					</dd>
				</dl>
			</div>
		</div>
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">院内岗位</h2>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt>岗位类别：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="YHRS0113" dicItemCode="${verPbPersonInfoForm.hisPositionType}" />'> <dictionary:viewDicItemName dicTypeCode="YHRS0113" dicItemCode="${verPbPersonInfoForm.hisPositionType}" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>岗位级别：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0124%>" dicItemCode="${verPbPersonInfoForm.hisPositionLevel}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0124%>" dicItemCode="${verPbPersonInfoForm.hisPositionLevel}" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>岗位名称：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="hisPositionName" />"> <bean:write name="verPbPersonInfoForm" property="hisPositionName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="岗位聘任开始时间：">岗位聘任开始时间：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="hisBeginDateStr" />"> <bean:write name="verPbPersonInfoForm" property="hisBeginDateStr" /> &nbsp; </label>
					</dd>
				</dl>
			</div>
		</div>
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">学历学位信息</h2>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt>全日制最高学历学校名称：</dt>
					<dd><label title=''></label>
						<bean:write name="verPbPersonInfoForm" property="ftSchoolName" />
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="全日制最高学历专业名称：">全日制最高学历专业名称：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="ftMajorName" />'> <bean:write name="verPbPersonInfoForm" property="ftMajorName" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="全日制最高学历：">全日制最高学历：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0039%>" dicItemCode="${verPbPersonInfoForm.ftEducationLevelCode}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0039%>" dicItemCode="${verPbPersonInfoForm.ftEducationLevelCode}" />&nbsp;</label>

					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>全日制最高学位：</dt>
					<dd>
						<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0040%>" dicItemCode="${verPbPersonInfoForm.ftDegreeCode}" />
					</dd>
				</dl>
				
				<dl class="data-unit-dl">
					<dt title="在职最高学历学校名称：">在职最高学历学校名称：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="ojSchoolName" />"> <bean:write name="verPbPersonInfoForm" property="ojSchoolName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="在职最高学历专业名称：">在职最高学历专业名称：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="ojMajorName" />"> <bean:write name="verPbPersonInfoForm" property="ojMajorName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>在职最高学历：</dt>
					<dd>
						<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0039%>" dicItemCode="${verPbPersonInfoForm.ojEducationLevelCode}" />
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>在职最高学位：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0040%>" dicItemCode="${verPbPersonInfoForm.ojDegreeCode}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0040%>" dicItemCode="${verPbPersonInfoForm.ojDegreeCode}" />&nbsp;</label>
					</dd>
				</dl>
			</div>
		</div>
		
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">资格信息</h2>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt title="专业技术资格证书编号：">专业技术资格证书编号：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="profCertificateNo" />"> <bean:write name="verPbPersonInfoForm" property="profCertificateNo" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="专业技术资格名称：">专业技术资格名称：</dt>
					<dd>
<%-- 						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0117%>" dicItemCode="${verPbPersonInfoForm.profTechName}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0117%>" dicItemCode="${verPbPersonInfoForm.profTechName}" />&nbsp;</label> --%>
						<label title='${verPbPersonInfoForm.profTechName}'> ${verPbPersonInfoForm.profTechName}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="专业技术资格等级：">专业技术资格等级：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0047%>" dicItemCode="${verPbPersonInfoForm.profTechLevel}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0047%>" dicItemCode="${verPbPersonInfoForm.profTechLevel}" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>专业名称：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="specialityName" />'> <bean:write name="verPbPersonInfoForm" property="specialityName" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="专业技术资格取得日期：">专业技术资格取得日期：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="profProcureDateStr" />'> <bean:write name="verPbPersonInfoForm" property="profProcureDateStr" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="职业资格证书编号：">职业资格证书编号：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="certificateNo" />'> <bean:write name="verPbPersonInfoForm" property="certificateNo" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>职业资格名称：</dt>
					<dd>
<%-- 						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS9004%>" dicItemCode="${verPbPersonInfoForm.qualificationsName}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS9004%>" dicItemCode="${verPbPersonInfoForm.qualificationsName}" />&nbsp;</label> --%>
						<label title='${verPbPersonInfoForm.qualificationsName}'> ${verPbPersonInfoForm.qualificationsName}&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="职（执）业资格等级：">职（执）业资格等级：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0049%>" dicItemCode="${verPbPersonInfoForm.qualificationsLevelCode}" />'> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0049%>" dicItemCode="${verPbPersonInfoForm.qualificationsLevelCode}" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="职业资格取得日期：">职业资格取得日期：</dt>
					<dd>
						<label title='<bean:write name="verPbPersonInfoForm" property="procureDateStr" />'> <bean:write name="verPbPersonInfoForm" property="procureDateStr" />&nbsp;</label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="职业资格类型：">职业资格类型：</dt>
					<dd>
						<label title='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0127%>" dicItemCode="${verPbPersonInfoForm.qualificationsType}" />'><dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0127%>" dicItemCode="${verPbPersonInfoForm.qualificationsType}" />&nbsp;</label>
					</dd>
				</dl>

			</div>
		</div>
		<%-- <div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">现聘事业岗位信息</h2>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt>主岗位类别（级别）：</dt>
					<dd>
						<c:if test="${not empty verPbPersonInfoForm.mPositionType}">
							<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${verPbPersonInfoForm.mPositionType}" />
							(<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${verPbPersonInfoForm.mPositionLevel}" />)"> 
							<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${verPbPersonInfoForm.mPositionType}" />
							(<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${verPbPersonInfoForm.mPositionLevel}" />) &nbsp; </label>
						</c:if>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>主岗位名称：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="mPositionName" />"> <bean:write name="verPbPersonInfoForm" property="mPositionName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="主岗位级别起始时间：">主岗位级别起始时间：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="mPositionLevelDateStr" />"> <bean:write name="verPbPersonInfoForm" property="mPositionLevelDateStr" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>辅岗位类别（级别）：</dt>
					<dd>
						<c:if test="${not empty verPbPersonInfoForm.sPositionType}">
							<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${verPbPersonInfoForm.sPositionType}" />
							(<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${verPbPersonInfoForm.sPositionLevel}" />)"> 
							<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0022%>" dicItemCode="${verPbPersonInfoForm.sPositionType}" /> 
							(<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${verPbPersonInfoForm.sPositionLevel}" />)&nbsp; </label>
						</c:if>
						
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>辅岗位名称：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="sPositionName" />"> <bean:write name="verPbPersonInfoForm" property="sPositionName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="辅岗位级别起始时间：">辅岗位级别起始时间：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="sPositionLevelDateStr" />"> <bean:write name="verPbPersonInfoForm" property="sPositionLevelDateStr" /> &nbsp; </label>
					</dd>
				</dl>
			</div>
		</div> --%>
		<div class="infoshow-container multi-info-group clearfix">
			<h2 class="infoshow-title">其他</h2>
			<div class="data-units">
				<dl class="data-unit-dl">
					<dt>紧急联系人：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="emergContact" />"> <bean:write name="verPbPersonInfoForm" property="emergContact" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="紧急联系人手机号码：">紧急联系人手机号码：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="emergPhone" />"> <bean:write name="verPbPersonInfoForm" property="emergPhone" /> &nbsp; </label>
					</dd>
				</dl>
				<%-- <dl class="data-unit-dl">
					<dt title="所属劳务派遣公司：">所属劳务派遣公司：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="dispatchingUnitCode" />"> <bean:write name="verPbPersonInfoForm" property="dispatchingUnitCode" /> &nbsp; </label>
					</dd>
				</dl> --%>
				<dl class="data-unit-dl">
					<dt>外文姓名：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="englishName" />"> <bean:write name="verPbPersonInfoForm" property="englishName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>曾用名：</dt>
					<dd>
						<label title="<bean:write name="verPbPersonInfoForm" property="alternativeName" />"> <bean:write name="verPbPersonInfoForm" property="alternativeName" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="港澳台侨属标识：">港澳台侨属标识：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.flagOfHkmctwChineseCode}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.flagOfHkmctwChineseCode}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否专业技术人员标识：">是否专业技术人员标识：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.protTechFlag}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.protTechFlag}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt>是否专家标志：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.exportFlag}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.exportFlag}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否博士后研究人员：">是否博士后研究人员：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.doctorFlag}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.doctorFlag}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否海外专家：">是否海外专家：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isAbordExpert}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isAbordExpert}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否留学回国专家：">是否留学回国专家：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isStudyAbordExpert}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isStudyAbordExpert}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否来华定居专家：">是否来华定居专家：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isComesChinaExpert}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isComesChinaExpert}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否退役军人标识：">是否退役军人标识：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isVeteran}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isVeteran}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否军转安置干部：">是否军转安置干部：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isAllocation}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isAllocation}" /> &nbsp; </label>
					</dd>
				</dl>
				<dl class="data-unit-dl">
					<dt title="是否干部身份：">是否干部身份：</dt>
					<dd>
						<label title="<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isCadre}" />"> <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0003%>" dicItemCode="${verPbPersonInfoForm.isCadre}" /> &nbsp; </label>
					</dd>
				</dl>
			</div>
		</div>

	</div>
</body>
<script>
	$(document).ready(function() {
		var birthday =  $('#birthdayStr').text().replace(/-/g,"");
		countAge(birthday);
		/* var birthdayYear = birthday.substring(0,4);
		var currentYear = new Date().getFullYear();
		$('#age').text(parseInt(currentYear)-parseInt(birthdayYear)+1); */
		$('.popdown').popdown({
			width : 1400
		});
	})
	
	
	function countAge(birthday){
	var birthdayYear = birthday.substring(0,4);
	var currentYear = new Date().getFullYear();
	
	var age=parseInt(currentYear)-parseInt(birthdayYear);
	
	var nowtime = new Date();  
	var month = padleft0(nowtime.getMonth() + 1);  
	var day = padleft0(nowtime.getDate());  
	if(age>0){
	  var birthdayMonth = birthday.substring(4,6);
	  if((month-birthdayMonth)>0){
		
	  }else if((month-birthdayMonth)==0){
		  var birthdayDate=birthday.substring(6,8);
		  if(day-birthdayDate<0){
			  age=age-1;
		  }
	  }else{
		  age=age-1;
	  }
   }	
	$('#age').text(age);
}
//补齐两位数  
function padleft0(obj) {  
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
} 

function uploadIdCard(personOid) {

	var params = {
		personOid : personOid//字节 5MB
		,refType : '23'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
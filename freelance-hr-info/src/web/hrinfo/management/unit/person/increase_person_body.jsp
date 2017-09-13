<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    人员信息管理增员页面
 * @page name   ver_pbperson_body.jsp
 * @author      chenp
 * @created     2016/10/8
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员信息管理增员body页面</title>
<!-- 选择内设机构组件 -->
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
</head>

<body>
	<h2 class="infoshow-title">人员基本信息</h2>
	<div class="md-units md-three-column md-unit-hasimg clearfix">
		<dl class="updown-dl updown-img">
			<dt>
				照片
			</dt>
			<dd>
				<div class="md-unit-img">
					<img name="offerImg" id="previewImg" src="<%=request.getContextPath()%>/hrinfo/ver/unit/comm/image_view.jsp?personOid=${increasePersonForm.personOid}&_<%=new java.util.Random().nextInt(1000)%>" alt="照片" />
				</div>
				<a class="img-upload"> <input type="file" id="file_upload" name="image" accept="image/*">选择照片 </a>
				<p class="md-unit-p" title="图片请小于100K（jpg、png、bmp、gif、tif等）">图片请小于100K（jpg、png、bmp、gif、tif等）</p>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>姓名：
			</dt>
			<dd>
				<label> <input type="text" id="name" name="name" value="${increasePersonForm.name }" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>性别：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="sexCode" dicTypeCode="<%=DicConstants.YHRS0001%>" selected="${increasePersonForm.sexCode}" name="sexCode" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>工号：
			</dt>
			<dd>
				<label> <input type="text" id="personCode" name="personCode" value="${increasePersonForm.personCode }" maxlength="20" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>所在科室：
			</dt>
			<dd>
				<label> <input type="text" class="modal_iput" name="hireDeptName" id="hireDeptOid" value='${increasePersonForm.hireDeptName}' onclick="min_selOrg.min_selectOrg(this,$('#unitOid').val(),true,null,['bottom','left'],'true')" /> 
				<input type="hidden" id="hiddenHireDeptOid" name="hireDeptOid" value="${increasePersonForm.hireDeptOid}" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>人员状态：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="personStatus" dicTypeCode="<%=DicConstants.YHRS0009%>" selected="${increasePersonForm.personStatus}" name="personStatus" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>人员类别：
			</dt>
			<dd>
				<label><input type="text" name="personTypeName" readonly="readonly" id="personType" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0010%>" dicItemCode="${increasePersonForm.personType}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0010%>',null,null,null,null,['bottom','left'])" /> </label> <input type="hidden" name="personType" value="${increasePersonForm.personType}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="参加工作时间："> <b class="Required">* </b>参加工作时间：</dt>
			<dd>
				<label> <html:text styleId="startWorkDateStr" property="startWorkDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>证件类别：
			</dt>
			<dd>
				<label> 
				<input type="text" name="idName" id="idCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${increasePersonForm.idCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0002%>',null,null,null,null,['bottom','left'])" /> 
				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
				</label> <input type="hidden" name="idCode" value="${increasePersonForm.idCode}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>证件号码：
			</dt>
			<dd>
				<label> <input type="text" id="idNo" name="idNo" value="${increasePersonForm.idNo }" onblur="validateIdNo(this)" maxlength="18" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>外文姓名：</dt>
			<dd>
				<label> <input type="text" id="englishName" name="englishName" value="${increasePersonForm.englishName }" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>曾用名：</dt>
			<dd>
				<label> <input type="text" id="alternativeName" name="alternativeName" value="${increasePersonForm.alternativeName }" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>出生日期：
			</dt>
			<dd>
				<label> <html:text styleId="birthdayStr" property="birthdayStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>民族：</dt>
			<dd>
				<label><input type="text" name="peopleName" readonly="readonly" id="peopleCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0004%>" dicItemCode="${increasePersonForm.peopleCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0004%>')" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a></label> <input type="hidden" name="peopleCode" value="${increasePersonForm.peopleCode}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>国籍：</dt>
			<dd>
				<label>
				<input type="text" name="nationalityName" readonly="readonly" id="nationalityCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0005%>" dicItemCode="${increasePersonForm.nationalityCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0005%>',null,null,null,null,['bottom','left'])" /> 
				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
				</label> 
				<input type="hidden" name="nationalityCode" value="${increasePersonForm.nationalityCode}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>籍贯：</dt>
			<dd>
				<label>
				<input type="text" name="ancestorPlaceName" id="ancestorPlaceCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${increasePersonForm.ancestorPlaceCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>',null,null,null,null,['bottom','left'])" /> 
				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
				</label> 
				<input type="hidden" name="ancestorPlaceCode" value="${increasePersonForm.ancestorPlaceCode}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>出生地：</dt>
			<dd>
				<label> 
				<input type="text" name="birthplaceName" id="birthplaceCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${increasePersonForm.birthplaceCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>')" /> 
				<input type="hidden" name="birthplaceCode" value="${increasePersonForm.birthplaceCode}" /> 
				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
				</label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>户口所在地：</dt>
			<dd>
				<label><input type="text" name="hukouPlaceName" id="hukouPlace" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${increasePersonForm.hukouPlace}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a> </label> <input type="hidden" name="hukouPlace" value="${increasePersonForm.hukouPlace}" />
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否本地户口：">
				<b class="Required">* </b>是否本地户口：
			</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isSz" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isSz}" name="isSz" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>健康状况：</dt>
			<dd>
				<label> <input type="text" name="healthStatusName" id="healthStatusCode" readonly="readonly" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0007%>" 
                 dicItemCode="${increasePersonForm.healthStatusCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0007%>')" /> <html:hidden property="healthStatusCode" name="increasePersonForm" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a></label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>婚姻状况：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="marriageStatusCode" dicTypeCode="<%=DicConstants.YHRS0008%>" selected="${increasePersonForm.marriageStatusCode}" name="marriageStatusCode" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>家庭住址：</dt>
			<dd>
				<label> <input type="text" id="address" name="address" value="${increasePersonForm.address}" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="个人Email地址：">个人Email地址：</dt>
			<dd>
				<label> <html:text styleId="email" property="email" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>手机号码：
			</dt>
			<dd>
				<label> <input type="text" id="mobilePhone" name="mobilePhone" value="${increasePersonForm.mobilePhone }" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>办公电话：</dt>
			<dd>
				<label> <input type="text" id="officePhone" name="officePhone" value="${increasePersonForm.officePhone}" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>工作单位：
			</dt>
			<dd>
				<label> <input type="text" name="unitName" id="unitName" value="${increasePersonForm.unitName}" readonly="readonly" /> <input type="hidden" name="unitOid" id="unitOid" value="${increasePersonForm.unitOid}" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="港澳台侨属标识：">港澳台侨属标识：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="flagOfHkmctwChineseCode" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.flagOfHkmctwChineseCode}" name="flagOfHkmctwChineseCode" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否专业技术人员标识：">是否专业技术人员标识：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="protTechFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.protTechFlag}" name="protTechFlag" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否专家标志：">是否专家标志：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="exportFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.exportFlag}" name="exportFlag" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否博士后研究人员：">是否博士后研究人员：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="doctorFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.doctorFlag}" name="doctorFlag" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否海外专家：">是否海外专家：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isAbordExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isAbordExpert}" name="isAbordExpert" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否留学回国专家：">是否留学回国专家：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isStudyAbordExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isStudyAbordExpert}" name="isStudyAbordExpert" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否来华定居专家：">是否来华定居专家：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isComesChinaExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isComesChinaExpert}" name="isComesChinaExpert" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否退役军人标识：">是否退役军人标识：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isVeteran" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isVeteran}" name="isVeteran" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否军转安置干部：">是否军转安置干部：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isAllocation" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isAllocation}" name="isAllocation" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="是否干部身份：">是否干部身份：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="isCadre" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.isCadre}" name="isCadre" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>编制类型：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="dPositionType" dicTypeCode="<%=DicConstants.YHRS0012%>" selected="${increasePersonForm.dPositionType}" name="dPositionType" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>经费形式：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="bankpoll" dicTypeCode="<%=DicConstants.YHRS0013%>" selected="${increasePersonForm.bankpoll}" name="bankpoll" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
		<%-- <dl class="updown-dl">
			<dt>人员排序号：</dt>
			<dd>
				<label> <input type="text" id="personOrderView" name="personOrderView" value="${increasePersonForm.personOrderView}" maxlength="12" /> </label>
			</dd>
		</dl> --%>
		<dl class="updown-dl">
			<dt>紧急联系人：</dt>
			<dd>
				<label> <input type="text" id="emergContact" name="emergContact" value="${increasePersonForm.emergContact}" maxlength="50" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt title="紧急联系人手机号码：">紧急联系人手机号码：</dt>
			<dd>
				<label> <html:text styleId="emergPhone" property="emergPhone" maxlength="20" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>护士层级：</dt>
			<dd>
				<label> <dictionary:dicItemSelect id="levelCode" dicTypeCode="<%=DicConstants.YHRS0122%>" selected="${increasePersonForm.levelCode}" name="levelCode" emptyText="--请选择--" /> </label>
			</dd>
		</dl>
	</div>


	<h2 class="infoshow-title">人员进入信息</h2>
	<div class="md-three-column clearfix">
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>进入方式：
			</dt>
			<dd>
				<label><dictionary:dicItemSelect id="entryCurrentUnitType" name="entryCurrentUnitType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${increasePersonForm.entryCurrentUnitType}" dicTypeCode="<%=DicConstants.YHRS0110%>" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt><b class="Required">* </b>进入单位时间：</dt>
			<dd>
				<label><input type="text" id="entryCurrentUnitDateStr" name="entryCurrentUnitDateStr" class="modal_iput" value="${increasePersonForm.entryCurrentUnitDateStr}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>进入前所在单位：</dt>
			<dd>
				<label><input type="text" id="personInFrom" name="personInFrom" class="modal_iput" value="${increasePersonForm.personInFrom}" maxlength="20" /> </label>
			</dd>
		</dl>
		<dl class="updown-remark">
			<dt>备注：</dt>
			<dd>
				<label><textarea id="remark" name="remark" maxlength="1000">${increasePersonForm.remark}</textarea> </label>
			</dd>
		</dl>
		<div class="clear"></div>
	</div>


	<h2 class="infoshow-title">合同信息</h2>
	<div class="md-three-column clearfix">
		<dl class="updown-dl">
			<dt>
				合同编号：
			</dt>
			<dd>
				<label><input type="text" name="contractNo" class="modal_iput" maxlength="30" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>合同签订方式：
			</dt>
			<dd>
				<label><dictionary:dicItemSelect name="contractType" id="contractType" styleClass="modal_select" emptyText="<%=Constant.EMPTY_SELECT %>" dicTypeCode="<%=DicConstants.YHRS0114%>" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>合同签订时间：</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="signDateStr" name="increasePersonForm" property="signDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>
				<b class="Required">* </b>合同开始时间：
			</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="contractBeginStr" name="increasePersonForm" property="contractBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt><b class="Required">* </b>合同截止时间：</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="contractEndStr" name="increasePersonForm" property="contractEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>合同实际结束日期：</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="contractEndActualStr" name="increasePersonForm" property="contractEndActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>是否存在试用期：</dt>
			<dd>
				<label><dictionary:dicItemSelect styleClass="modal_select" id="probationFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${increasePersonForm.probationFlag}" name="probationFlag" emptyText="--请选择--" /> </label>
			</dd>

		</dl>
		<dl class="updown-dl" id="probationDiv">
			<dt id="probationBegin"><b id="probationBeginFlag" class="Required" style="display:none">* </b>试用开始日期：</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="probationBeginStr" name="increasePersonForm" property="probationBeginStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>

		</dl>
		<dl class="updown-dl" id="probationDivE">
			<dt id="probationEnd"><b id="probationEndFlag" class="Required" style="display:none">* </b>试用结束日期：</dt>
			<dd>
				<label><html:text styleClass="modal_iput" styleId="probationEndStr" name="increasePersonForm" property="probationEndStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>工作岗位：</dt>
			<dd>
				<label><input type="text" name="contractJob" class="modal_iput" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>常住住址：</dt>
			<dd>
				<label><input type="text" name="addressPermanant" class="modal_iput" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>联系电话：</dt>
			<dd>
				<label><input type="text" name="contactPhone" class="modal_iput" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>用人单位：</dt>
			<dd>
				<label title='<bean:write name="increasePersonForm" property="unitName" />'> <!-- <bean:write name="increasePersonForm" property="unitName" />&nbsp; --> <input type="text" name="unitName" readonly="readonly" id="unitName" value="<bean:write name="increasePersonForm" property="unitName" />" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>用人单位主要负责人：</dt>
			<dd>
				<label title='<bean:write name="increasePersonForm" property="unitMaster" />'> 
				<!-- <bean:write name="increasePersonForm" property="unitMaster" />&nbsp; --> 
				<input type="text" name="unitMaster" id="unitMaster" value="<bean:write name="increasePersonForm" property="unitMaster" />" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>用人单位地址：</dt>
			<dd>
				<label title='<bean:write name="increasePersonForm" property="unitAddress" />'> 
				<!-- <bean:write name="increasePersonForm" property="unitAddress" />&nbsp; --> 
				<input type="text" name="unitAddress" id="unitAddress" value="<bean:write name="increasePersonForm" property="unitAddress" />" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>用人单位联系电话：</dt>
			<dd>
				<label title='<bean:write name="increasePersonForm" property="unitPhone" />'> 
				<!-- <bean:write name="increasePersonForm" property="unitPhone" />&nbsp; --> 
				<input type="text" name="unitAddress" id="unitAddress" value="<bean:write name="increasePersonForm" property="unitPhone" />" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>通信地址：</dt>
			<dd>
				<label><input type="text" name="addressCommunication" class="modal_iput" maxlength="100" /> </label>
			</dd>
		</dl>
		<dl class="updown-dl">
			<dt>其他事项：</dt>
			<dd>
				<label title="${increasePersonForm.others}"> <html:text styleClass="modal_iput" name="increasePersonForm" property="others" maxlength="1000" /> </label>
			</dd>
		</dl>
	</div>
</body>
</html>
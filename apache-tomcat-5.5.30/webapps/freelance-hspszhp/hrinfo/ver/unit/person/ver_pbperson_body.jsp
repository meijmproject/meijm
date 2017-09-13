<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    人员基础信息新增页面
 * @page name   ver_pbperson_body.jsp
 * @author      chenp
 * @created     2016/10/8
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单位信息body页面</title>
<!-- 选择内设机构组件 -->
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
</head>
<body>
	<div class="isc-title-box">
		<h3 class="md-isc-title">基础信息</h3>
	</div>
	<div class="md-infoshow-content">
		<div class="md-units md-three-column md-unit-hasimg clearfix">
			<dl class="updown-dl updown-img">
				<dt>
					照片
				</dt>
				<dd>
					<div class="md-unit-img">
						<img name="offerImg" id="previewImg" src="<%=request.getContextPath()%>/hrinfo/ver/unit/comm/image_view.jsp?personOid=${verPbPersonInfoForm.personOid}&_<%=new java.util.Random().nextInt(1000)%>" alt="照片" />
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
					<label> <input type="text" id="name" name="name" value="${verPbPersonInfoForm.name }" maxlength="50" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					<b class="Required">* </b>性别：
				</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="sexCode" dicTypeCode="<%=DicConstants.YHRS0001%>" selected="${verPbPersonInfoForm.sexCode}" name="sexCode" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
						工号：
				</dt>
				<dd>
					<label> <input type="text" id="personCode" name="personCode" value="${verPbPersonInfoForm.personCode }" maxlength="20" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt><b class="Required">* </b>所在科室：</dt>
				<dd>
					<label> 
					<input type="text" class="modal_iput" name="hireDeptName" id="hireDeptOid" value='${verPbPersonInfoForm.hireDeptName}' onclick="min_selOrg.min_selectOrg(this,$('#unitOid').val(),true,null,['bottom','left'],'true')" /> 
					<input type="hidden" name="hireDeptOid" value="${verPbPersonInfoForm.hireDeptOid}" /> 
					<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
					</label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					<b class="Required">* </b>工作单位：
				</dt>
				<dd>
					<label> 
					<input class='readonly' type="text" name="unitName" id="unitName" value="${verPbPersonInfoForm.unitName}" readonly="readonly" /> 
					<input type="hidden" name="unitOid" id="unitOid" value="${verPbPersonInfoForm.unitOid}" /> 
					</label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					<b class="Required">* </b>人员状态：
				</dt>
				<dd>
					<label> 
						<dictionary:dicItemSelect id="personStatus" dicTypeCode="<%=DicConstants.YHRS0009%>" selected="${verPbPersonInfoForm.personStatus}" name="personStatus" emptyText="--请选择--" /> 
					</label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					<b class="Required">* </b>人员类别：
				</dt>
				<dd>
					<label>
						<input type="text" name="personTypeName" readonly="readonly" id="personType" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0010%>" dicItemCode="${verPbPersonInfoForm.personType}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0010%>')" /> 
						<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
					</label> 
					<input type="hidden" name="personType" value="${verPbPersonInfoForm.personType}" />
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="参加工作时间：">参加工作时间：</dt>
				<dd>
					<label> <html:text styleId="startWorkDateStr" property="startWorkDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>民族：</dt>
				<dd>
					<label>
					<input type="text" name="peopleName" readonly="readonly" id="peopleCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0004%>" dicItemCode="${verPbPersonInfoForm.peopleCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0004%>')" /> 
					<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
					</label> 
					<input type="hidden" name="peopleCode" value="${verPbPersonInfoForm.peopleCode}" />
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>籍贯：</dt>
				<dd>
					<label><input type="text" name="ancestorPlaceName" id="ancestorPlaceCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.ancestorPlaceCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a></label> <input type="hidden" name="ancestorPlaceCode" value="${verPbPersonInfoForm.ancestorPlaceCode}" />
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>出生地：</dt>
				<dd>
					<label> <input type="text" name="birthplaceName" id="birthplaceCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.birthplaceCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a><input type="hidden" name="birthplaceCode" value="${verPbPersonInfoForm.birthplaceCode}" /> </label>
				</dd>
			</dl>
			<%-- <dl class="updown-dl">
				<dt>人员排序号：</dt>
				<dd>
					<label> <input type="text" id="personOrderView" name="personOrderView" value="${verPbPersonInfoForm.personOrderView}" maxlength="12" /> </label>
				</dd>
			</dl> --%>
			<dl class="updown-dl">
				<dt>户口所在地：</dt>
				<dd>
					<label><input type="text" name="hukouPlaceName" id="hukouPlace" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0006%>" dicItemCode="${verPbPersonInfoForm.hukouPlace}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0006%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a> </label> <input type="hidden" name="hukouPlace" value="${verPbPersonInfoForm.hukouPlace}" />
					</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否本地户口：">
					是否本地户口：
				</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isSz" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isSz}" name="isSz" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>家庭住址：</dt>
				<dd>
					<label> <input type="text" id="address" name="address" value="${verPbPersonInfoForm.address}" maxlength="100" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					证件类别：
				</dt>
				<dd>
					<label> <input type="text" name="idName" id="idCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0002%>" dicItemCode="${verPbPersonInfoForm.idCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0002%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a></label> <input type="hidden" name="idCode" value="${verPbPersonInfoForm.idCode}" />
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					证件号码：
				</dt>
				<dd>
					<label> <input type="text" id="idNo" name="idNo" value="${verPbPersonInfoForm.idNo}" onblur="validateIdNo(this)" maxlength="18" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					出生日期：
				</dt>
				<dd>
					<label> <html:text styleId="birthdayStrUpdate" onchange="numAge(this)"  property="birthdayStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					年龄：
				</dt>
				<dd>
					<label><input type="text" id="ageUpdate" name="age"  disabled maxlength="18" />  </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>婚姻状况：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="marriageStatusCode" dicTypeCode="<%=DicConstants.YHRS0008%>" selected="${verPbPersonInfoForm.marriageStatusCode}" name="marriageStatusCode" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>健康状况：</dt>
				<dd>
					<label> <input type="text" name="healthStatusName" id="healthStatusCode" readonly="readonly" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0007%>" 
	                 dicItemCode="${verPbPersonInfoForm.healthStatusCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0007%>',true,null,null,null,['bottom','left'])" /> <html:hidden property="healthStatusCode" name="verPbPersonInfoForm" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a></label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>国籍：</dt>
				<dd>
					<label><input type="text" name="nationalityName" readonly="readonly" id="nationalityCode" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0005%>" dicItemCode="${verPbPersonInfoForm.nationalityCode}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0005%>',null,null,null,null,['bottom','left'])" /> <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a> </label> <input type="hidden" name="nationalityCode" value="${verPbPersonInfoForm.nationalityCode}" />
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>
					手机号码：
				</dt>
				<dd>
					<label> <input type="text" id="mobilePhone" name="mobilePhone" value="${verPbPersonInfoForm.mobilePhone }" maxlength="50" /> </label>
				</dd>
			</dl>
			<%-- <dl class="updown-dl">
				<dt>办公电话：</dt>
				<dd>
					<label> <input type="text" id="officePhone" name="officePhone" value="${verPbPersonInfoForm.officePhone}" maxlength="50" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="个人Email地址：">个人Email地址：</dt>
				<dd>
					<label> <html:text styleId="email" property="email" maxlength="100" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>编制类型：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="dPositionType" dicTypeCode="<%=DicConstants.YHRS0012%>" selected="${verPbPersonInfoForm.dPositionType}" name="dPositionType" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>经费形式：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="bankpoll" dicTypeCode="<%=DicConstants.YHRS0013%>" selected="${verPbPersonInfoForm.bankpoll}" name="bankpoll" emptyText="--请选择--" /> </label>
				</dd>
			</dl> --%>
			<dl class="updown-dl">
				<dt>护士层级：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="levelCode" dicTypeCode="<%=DicConstants.YHRS0122%>" selected="${verPbPersonInfoForm.levelCode}" name="levelCode" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt><b class="Required"> </b>紧急联系人：</dt>
				<dd>
					<label> <input type="text" id="emergContact" name="emergContact" value="${verPbPersonInfoForm.emergContact}" maxlength="50" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="紧急联系人手机号码："><b class="Required"> </b>紧急联系人手机号码：</dt>
				<dd>
					<label> <html:text styleId="emergPhone" property="emergPhone" maxlength="20" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>外文姓名：</dt>
				<dd>
					<label> <input type="text" id="englishName" name="englishName" value="${verPbPersonInfoForm.englishName }" maxlength="50" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt>曾用名：</dt>
				<dd>
					<label> <input type="text" id="alternativeName" name="alternativeName" value="${verPbPersonInfoForm.alternativeName }" maxlength="50" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否专家标志：">是否专家标志：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="exportFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.exportFlag}" name="exportFlag" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否留学回国专家：">是否留学回国专家：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isStudyAbordExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isStudyAbordExpert}" name="isStudyAbordExpert" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否军转安置干部：">是否军转安置干部：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isAllocation" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isAllocation}" name="isAllocation" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="港澳台侨属标识：">港澳台侨属标识：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="flagOfHkmctwChineseCode" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.flagOfHkmctwChineseCode}" name="flagOfHkmctwChineseCode" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否博士后研究人员：">是否博士后研究人员：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="doctorFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.doctorFlag}" name="doctorFlag" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否来华定居专家：">是否来华定居专家：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isComesChinaExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isComesChinaExpert}" name="isComesChinaExpert" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否干部身份：">是否干部身份：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isCadre" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isCadre}" name="isCadre" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否专业技术人员标识：">是否专业技术人员标识：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="protTechFlag" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.protTechFlag}" name="protTechFlag" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否海外专家：">是否海外专家：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isAbordExpert" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isAbordExpert}" name="isAbordExpert" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
			<dl class="updown-dl">
				<dt title="是否退役军人标识：">是否退役军人标识：</dt>
				<dd>
					<label> <dictionary:dicItemSelect id="isVeteran" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${verPbPersonInfoForm.isVeteran}" name="isVeteran" emptyText="--请选择--" /> </label>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>
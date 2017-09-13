package com.yh.hr.orghc.unit.unitadmincreate.web.form;

import jade.workflow.utils.DateUtil;

import org.apache.struts.validator.ValidatorForm;
/**
 * 主管单位创建 Form
 * @author zhengdr
 *
 * 时间:2016-12-20下午02:46:32
 */
public class UnitAdminCreateForm extends ValidatorForm{

	private java.lang.Long		utUnitOid;					// utUnitOid
	private java.lang.Long		taskOid;					// TaskOid
	
	private static final long serialVersionUID = 8424406110643065384L;
	private java.lang.Long  	unitOid;	//UnitOid
	private java.lang.String	unitName;	//机构名称
	private java.lang.String	unitKind;	//单位性质YHRS0090
	private java.lang.String	unitKindStr;	//单位性质前台展示
	private java.lang.String	unitShortName;	//单位简称
	private java.lang.String	secondNameWork;	//第二名称(合署办公)
	private java.lang.String	secondNameShow;	//第二名称（挂牌）
	private java.lang.Long  	parentUnitOid;	//上级单位（隶属单位）
	private java.lang.String  	parentUnitName;	//上级单位（隶属单位）名称
	private java.lang.String	unitCategoryCode;	//系统类别YHRS0091
	private java.lang.String	unitTypeCode;	//机关机构类别YHRS0092
	private java.lang.String	levelCode;	//机构级别YHRS0093
	private java.lang.String	internalOrgLevel;	//内设机构级别YHRS0093
	private java.lang.String	budgetFromCode;	//经费来源
	private java.lang.String	budgetFromCodeStr;	//经费来源前台显示
	private java.lang.String	areaCode;	//区域代码
	private java.lang.String	areaCodeStr;	//区域代码前台显示
	private java.lang.String	isUnionPay;	//是否统发YHRS0003
	private java.lang.String	isSecurityUnit;	//是否机密单位YHRS0003
	private java.lang.String	unitFunction;	//单位职能
	private java.lang.String	branchTypeCode;	//机关下设机构编码（中编办）YHRS0094
	private java.lang.String	unitAttrSrvCode;	//单位属性（中编办）（机关）YHRS0095
	private java.lang.String	unitTypeCodeZbb;	//机关编码（中编办）YHRS0096
	private java.lang.String	relationCode;	//机构隶属关系（事业）YHRS0097
	private java.lang.String	industryCode;	//所属行业（中编办）（事业）YHRS0098
	private java.lang.String	unitTypeBizCode;	//事业单位类型（中编办）（事业）YHRS0099
	private java.lang.String	unitAttrCode;	//单位属性（中编办）（事业）YHRS0100
	private java.lang.String	unitLegal;	//是否法定机构（事业）YHRS0003
	private java.lang.String	corporationCode;	//组织机构代码
	private java.util.Date  	establishedDate;	//成立时间
	private java.lang.String  	establishedDateStr;    //成立时间页面显示
	private java.util.Date  	cancelDate;	//撤销时间
	private java.lang.String	orderOfView;	//排序号
	private java.lang.String	orderOfAll;	//全局排序号
	private java.lang.String	contacter;	//联系人
	private java.lang.String	otherContacter;	//其它联系人
	private java.lang.String	mobilePhone;	//手机
	private java.lang.String	email;	//电子邮箱
	private java.lang.String	phone;	//联系电话
	private java.lang.String	fax;	//传真
	private java.lang.String	address;	//单位地址
	private java.lang.String	zipcode;	//邮政编码
	private java.lang.String	unitCode;	//UnitCode
	private java.lang.String	unitStatus;	//机构状态YHRS0101
	private java.lang.String	unitStatusStr;	//机构状态页面展示
	private java.lang.String	unitCreditNo;	//统一社会信用代码
	private java.lang.String	remark;	//备注
	private java.lang.String	fileNo;	//文号

	//事业单位
	private java.lang.Integer	zhengGaoPercent;	//专技正高级百分比
	private java.lang.Integer	fuGaoPercent;	//专技副高级百分比
	private java.lang.Integer	zhongPercent;	//专技中级百分比
	private java.lang.Integer	chuPercent;	//专技初级百分比
	private java.lang.String	unitFunc;	//单位功能
	
	public java.lang.Long getUtUnitOid() {
		return utUnitOid;
	}


	public void setUtUnitOid(java.lang.Long utUnitOid) {
		this.utUnitOid = utUnitOid;
	}


	public java.lang.Long getTaskOid() {
		return taskOid;
	}


	public void setTaskOid(java.lang.Long taskOid) {
		this.taskOid = taskOid;
	}


	public java.lang.String getAreaCodeStr() {
		return areaCodeStr;
	}


	public void setAreaCodeStr(java.lang.String areaCodeStr) {
		this.areaCodeStr = areaCodeStr;
	}

	public java.lang.String getParentUnitName() {
		return parentUnitName;
	}

	public void setParentUnitName(java.lang.String parentUnitName) {
		this.parentUnitName = parentUnitName;
	}

	public java.lang.String getBudgetFromCodeStr() {
		return budgetFromCodeStr;
	}

	public void setBudgetFromCodeStr(java.lang.String budgetFromCodeStr) {
		this.budgetFromCodeStr = budgetFromCodeStr;
	}

	
	public java.lang.String getFileNo() {
		return fileNo;
	}

	public void setFileNo(java.lang.String fileNo) {
		this.fileNo = fileNo;
	}

	

	public java.lang.String getUnitKindStr() {
		return unitKindStr;
	}

	public void setUnitKindStr(java.lang.String unitKindStr) {
		this.unitKindStr = unitKindStr;
	}

	public java.lang.String getUnitStatusStr() {
		return unitStatusStr;
	}

	public void setUnitStatusStr(java.lang.String unitStatusStr) {
		this.unitStatusStr = unitStatusStr;
	}
	public java.lang.String getEstablishedDateStr() {
		return establishedDateStr;
	}

	public void setEstablishedDateStr(java.lang.String establishedDateStr) {
		this.establishedDateStr = establishedDateStr;
		this.establishedDate = DateUtil.parseDate(establishedDateStr);
	}
	public java.lang.Long getUnitOid() {
		return unitOid;
	}

	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}

	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.String getUnitKind() {
		return unitKind;
	}

	public void setUnitKind(java.lang.String unitKind) {
		this.unitKind = unitKind;
	}

	public java.lang.String getUnitShortName() {
		return unitShortName;
	}

	public void setUnitShortName(java.lang.String unitShortName) {
		this.unitShortName = unitShortName;
	}

	public java.lang.String getSecondNameWork() {
		return secondNameWork;
	}

	public void setSecondNameWork(java.lang.String secondNameWork) {
		this.secondNameWork = secondNameWork;
	}

	public java.lang.String getSecondNameShow() {
		return secondNameShow;
	}

	public void setSecondNameShow(java.lang.String secondNameShow) {
		this.secondNameShow = secondNameShow;
	}

	public java.lang.Long getParentUnitOid() {
		return parentUnitOid;
	}

	public void setParentUnitOid(java.lang.Long parentUnitOid) {
		this.parentUnitOid = parentUnitOid;
	}

	public java.lang.String getUnitCategoryCode() {
		return unitCategoryCode;
	}

	public void setUnitCategoryCode(java.lang.String unitCategoryCode) {
		this.unitCategoryCode = unitCategoryCode;
	}

	public java.lang.String getUnitTypeCode() {
		return unitTypeCode;
	}

	public void setUnitTypeCode(java.lang.String unitTypeCode) {
		this.unitTypeCode = unitTypeCode;
	}

	public java.lang.String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(java.lang.String levelCode) {
		this.levelCode = levelCode;
	}

	public java.lang.String getInternalOrgLevel() {
		return internalOrgLevel;
	}

	public void setInternalOrgLevel(java.lang.String internalOrgLevel) {
		this.internalOrgLevel = internalOrgLevel;
	}

	public java.lang.String getBudgetFromCode() {
		return budgetFromCode;
	}

	public void setBudgetFromCode(java.lang.String budgetFromCode) {
		this.budgetFromCode = budgetFromCode;
	}

	public java.lang.String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	public java.lang.String getIsUnionPay() {
		return isUnionPay;
	}

	public void setIsUnionPay(java.lang.String isUnionPay) {
		this.isUnionPay = isUnionPay;
	}

	public java.lang.String getIsSecurityUnit() {
		return isSecurityUnit;
	}

	public void setIsSecurityUnit(java.lang.String isSecurityUnit) {
		this.isSecurityUnit = isSecurityUnit;
	}

	public java.lang.String getUnitFunction() {
		return unitFunction;
	}

	public void setUnitFunction(java.lang.String unitFunction) {
		this.unitFunction = unitFunction;
	}

	public java.lang.String getBranchTypeCode() {
		return branchTypeCode;
	}

	public void setBranchTypeCode(java.lang.String branchTypeCode) {
		this.branchTypeCode = branchTypeCode;
	}

	public java.lang.String getUnitAttrSrvCode() {
		return unitAttrSrvCode;
	}

	public void setUnitAttrSrvCode(java.lang.String unitAttrSrvCode) {
		this.unitAttrSrvCode = unitAttrSrvCode;
	}

	public java.lang.String getUnitTypeCodeZbb() {
		return unitTypeCodeZbb;
	}

	public void setUnitTypeCodeZbb(java.lang.String unitTypeCodeZbb) {
		this.unitTypeCodeZbb = unitTypeCodeZbb;
	}

	public java.lang.String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(java.lang.String relationCode) {
		this.relationCode = relationCode;
	}

	public java.lang.String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(java.lang.String industryCode) {
		this.industryCode = industryCode;
	}

	public java.lang.String getUnitTypeBizCode() {
		return unitTypeBizCode;
	}

	public void setUnitTypeBizCode(java.lang.String unitTypeBizCode) {
		this.unitTypeBizCode = unitTypeBizCode;
	}

	public java.lang.String getUnitAttrCode() {
		return unitAttrCode;
	}

	public void setUnitAttrCode(java.lang.String unitAttrCode) {
		this.unitAttrCode = unitAttrCode;
	}

	public java.lang.String getUnitLegal() {
		return unitLegal;
	}

	public void setUnitLegal(java.lang.String unitLegal) {
		this.unitLegal = unitLegal;
	}

	public java.lang.String getCorporationCode() {
		return corporationCode;
	}

	public void setCorporationCode(java.lang.String corporationCode) {
		this.corporationCode = corporationCode;
	}

	public java.util.Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(java.util.Date establishedDate) {
		if(establishedDate!=null){
			this.establishedDate = establishedDate;
			this.establishedDateStr = DateUtil.formatDate(establishedDate);
		}
		
	}

	public java.util.Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public java.lang.String getOrderOfView() {
		return orderOfView;
	}

	public void setOrderOfView(java.lang.String orderOfView) {
		this.orderOfView = orderOfView;
	}

	public java.lang.String getOrderOfAll() {
		return orderOfAll;
	}

	public void setOrderOfAll(java.lang.String orderOfAll) {
		this.orderOfAll = orderOfAll;
	}

	public java.lang.String getContacter() {
		return contacter;
	}

	public void setContacter(java.lang.String contacter) {
		this.contacter = contacter;
	}

	public java.lang.String getOtherContacter() {
		return otherContacter;
	}

	public void setOtherContacter(java.lang.String otherContacter) {
		this.otherContacter = otherContacter;
	}

	public java.lang.String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(java.lang.String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getFax() {
		return fax;
	}

	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getZipcode() {
		return zipcode;
	}

	public void setZipcode(java.lang.String zipcode) {
		this.zipcode = zipcode;
	}

	public java.lang.String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(java.lang.String unitCode) {
		this.unitCode = unitCode;
	}

	public java.lang.String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(java.lang.String unitStatus) {
		this.unitStatus = unitStatus;
	}

	public java.lang.String getUnitCreditNo() {
		return unitCreditNo;
	}

	public void setUnitCreditNo(java.lang.String unitCreditNo) {
		this.unitCreditNo = unitCreditNo;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public java.lang.Integer getZhengGaoPercent() {
		return zhengGaoPercent;
	}


	public void setZhengGaoPercent(java.lang.Integer zhengGaoPercent) {
		this.zhengGaoPercent = zhengGaoPercent;
	}


	public java.lang.Integer getFuGaoPercent() {
		return fuGaoPercent;
	}


	public void setFuGaoPercent(java.lang.Integer fuGaoPercent) {
		this.fuGaoPercent = fuGaoPercent;
	}


	public java.lang.Integer getZhongPercent() {
		return zhongPercent;
	}


	public void setZhongPercent(java.lang.Integer zhongPercent) {
		this.zhongPercent = zhongPercent;
	}


	public java.lang.Integer getChuPercent() {
		return chuPercent;
	}


	public void setChuPercent(java.lang.Integer chuPercent) {
		this.chuPercent = chuPercent;
	}


	public java.lang.String getUnitFunc() {
		return unitFunc;
	}


	public void setUnitFunc(java.lang.String unitFunc) {
		this.unitFunc = unitFunc;
	}

	
}

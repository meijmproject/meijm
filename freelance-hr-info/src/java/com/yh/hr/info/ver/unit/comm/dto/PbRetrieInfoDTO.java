package com.yh.hr.info.ver.unit.comm.dto;

public class PbRetrieInfoDTO {

	/**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *离退类别YHRS0057
     **/
	private java.lang.String retrieTypeCode;
    /**
     *离退日期
     **/
	private java.util.Date retrieDate;
    /**
     *提前退休原因YHRS0058
     **/
	private java.lang.String retrieReason;
    /**
     *离退后享受待遇级别YHRS0015
     **/
	private java.lang.String retireTreatmentLevelCode;
    /**
     *离退后享受待遇类别YHRS0109
     **/
	private java.lang.String retireTreatmentTypeCode;
    /**
     *离退休费比例
     **/
	private java.lang.Float retireFeeRatio;
    /**
     *离退休补助比例
     **/
	private java.lang.Float retireAllowanceRatio;
    /**
     *离退休费支付单位
     **/
	private java.lang.String retirtPaymentUnit;
    /**
     *离退休后管理单位
     **/
	private java.lang.String retireManageUnit;
    /**
     *离退休批准机关
     **/
	private java.lang.String retritApproveUnit;
    /**
     *离退休批准文号
     **/
	private java.lang.String retrieApproveFileno;
    /**
     *离退休证号
     **/
	private java.lang.String retireNo;
    /**
     *有害工种增加工龄
     **/
	private java.lang.Long serviceYearsAdded;
    /**
     *特殊贡献比例
     **/
	private java.lang.Float specialContributionRatio;
    /**
     *特殊贡献标志YHRS0003
     **/
	private java.lang.String specialContributionFlag;
    /**
     *归侨加发比例
     **/
	private java.lang.Float returnedOverseasChiScale;
    /**
     *归国华侨标志YHRS0003
     **/
	private java.lang.String returnedOverseasFlag;
    /**
     *是否终身无子女YHRS0003
     **/
	private java.lang.String isNoChildForLife;
    /**
     *是否教龄满三十年YHRS0003
     **/
	private java.lang.String isTechingThirtyYears;
    /**
     *是否公安干警满三十年YHRS0003
     **/
	private java.lang.String isPoliceThirtyYears;
    /**
     *审批类型YHRS0060
     **/
	private java.lang.String approvalType;
    /**
     *审查部门
     **/
	private java.lang.String approvalUnit;
    /**
     *符合条例YHRS0061
     **/
	private java.lang.String compliedClause;
    /**
     *计生奖励
     **/
	private java.lang.String familyPlanningAward;
    /**
     *备注
     **/
	private java.lang.String remark;
    /**
     *CreatedByCode
     **/
	private java.lang.String createdByCode;
    /**
     *CreatedByName
     **/
	private java.lang.String createdByName;
    /**
     *CreatedDate
     **/
	private java.util.Date createdDate;
    /**
     *UpdatedByCode
     **/
	private java.lang.String updatedByCode;
    /**
     *UpdatedByName
     **/
	private java.lang.String updatedByName;
    /**
     *UpdatedDate
     **/
	private java.util.Date updatedDate;

/**
* public JhcPbRetrieInfo(java.lang.Long personOid) {
*  *       this.personOid = personOid;
**   }
**/
	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setRetrieTypeCode(java.lang.String retrieTypeCode){
		this.retrieTypeCode = retrieTypeCode;
	}

	public java.lang.String getRetrieTypeCode(){
		return this.retrieTypeCode;
	}

	public void setRetrieDate(java.util.Date retrieDate){
		this.retrieDate = retrieDate;
	}

	public java.util.Date getRetrieDate(){
		return this.retrieDate;
	}

	public void setRetrieReason(java.lang.String retrieReason){
		this.retrieReason = retrieReason;
	}

	public java.lang.String getRetrieReason(){
		return this.retrieReason;
	}

	public void setRetireTreatmentLevelCode(java.lang.String retireTreatmentLevelCode){
		this.retireTreatmentLevelCode = retireTreatmentLevelCode;
	}

	public java.lang.String getRetireTreatmentLevelCode(){
		return this.retireTreatmentLevelCode;
	}

	public void setRetireTreatmentTypeCode(java.lang.String retireTreatmentTypeCode){
		this.retireTreatmentTypeCode = retireTreatmentTypeCode;
	}

	public java.lang.String getRetireTreatmentTypeCode(){
		return this.retireTreatmentTypeCode;
	}

	public void setRetireFeeRatio(java.lang.Float retireFeeRatio){
		this.retireFeeRatio = retireFeeRatio;
	}

	public java.lang.Float getRetireFeeRatio(){
		return this.retireFeeRatio;
	}

	public void setRetireAllowanceRatio(java.lang.Float retireAllowanceRatio){
		this.retireAllowanceRatio = retireAllowanceRatio;
	}

	public java.lang.Float getRetireAllowanceRatio(){
		return this.retireAllowanceRatio;
	}

	public void setRetirtPaymentUnit(java.lang.String retirtPaymentUnit){
		this.retirtPaymentUnit = retirtPaymentUnit;
	}

	public java.lang.String getRetirtPaymentUnit(){
		return this.retirtPaymentUnit;
	}

	public void setRetireManageUnit(java.lang.String retireManageUnit){
		this.retireManageUnit = retireManageUnit;
	}

	public java.lang.String getRetireManageUnit(){
		return this.retireManageUnit;
	}

	public void setRetritApproveUnit(java.lang.String retritApproveUnit){
		this.retritApproveUnit = retritApproveUnit;
	}

	public java.lang.String getRetritApproveUnit(){
		return this.retritApproveUnit;
	}

	public void setRetrieApproveFileno(java.lang.String retrieApproveFileno){
		this.retrieApproveFileno = retrieApproveFileno;
	}

	public java.lang.String getRetrieApproveFileno(){
		return this.retrieApproveFileno;
	}

	public void setRetireNo(java.lang.String retireNo){
		this.retireNo = retireNo;
	}

	public java.lang.String getRetireNo(){
		return this.retireNo;
	}

	public void setServiceYearsAdded(java.lang.Long serviceYearsAdded){
		this.serviceYearsAdded = serviceYearsAdded;
	}

	public java.lang.Long getServiceYearsAdded(){
		return this.serviceYearsAdded;
	}

	public void setSpecialContributionRatio(java.lang.Float specialContributionRatio){
		this.specialContributionRatio = specialContributionRatio;
	}

	public java.lang.Float getSpecialContributionRatio(){
		return this.specialContributionRatio;
	}

	public void setSpecialContributionFlag(java.lang.String specialContributionFlag){
		this.specialContributionFlag = specialContributionFlag;
	}

	public java.lang.String getSpecialContributionFlag(){
		return this.specialContributionFlag;
	}

	public void setReturnedOverseasChiScale(java.lang.Float returnedOverseasChiScale){
		this.returnedOverseasChiScale = returnedOverseasChiScale;
	}

	public java.lang.Float getReturnedOverseasChiScale(){
		return this.returnedOverseasChiScale;
	}

	public void setReturnedOverseasFlag(java.lang.String returnedOverseasFlag){
		this.returnedOverseasFlag = returnedOverseasFlag;
	}

	public java.lang.String getReturnedOverseasFlag(){
		return this.returnedOverseasFlag;
	}

	public void setIsNoChildForLife(java.lang.String isNoChildForLife){
		this.isNoChildForLife = isNoChildForLife;
	}

	public java.lang.String getIsNoChildForLife(){
		return this.isNoChildForLife;
	}

	public void setIsTechingThirtyYears(java.lang.String isTechingThirtyYears){
		this.isTechingThirtyYears = isTechingThirtyYears;
	}

	public java.lang.String getIsTechingThirtyYears(){
		return this.isTechingThirtyYears;
	}

	public void setIsPoliceThirtyYears(java.lang.String isPoliceThirtyYears){
		this.isPoliceThirtyYears = isPoliceThirtyYears;
	}

	public java.lang.String getIsPoliceThirtyYears(){
		return this.isPoliceThirtyYears;
	}

	public void setApprovalType(java.lang.String approvalType){
		this.approvalType = approvalType;
	}

	public java.lang.String getApprovalType(){
		return this.approvalType;
	}

	public void setApprovalUnit(java.lang.String approvalUnit){
		this.approvalUnit = approvalUnit;
	}

	public java.lang.String getApprovalUnit(){
		return this.approvalUnit;
	}

	public void setCompliedClause(java.lang.String compliedClause){
		this.compliedClause = compliedClause;
	}

	public java.lang.String getCompliedClause(){
		return this.compliedClause;
	}

	public void setFamilyPlanningAward(java.lang.String familyPlanningAward){
		this.familyPlanningAward = familyPlanningAward;
	}

	public java.lang.String getFamilyPlanningAward(){
		return this.familyPlanningAward;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
	}

	public void setCreatedByCode(java.lang.String createdByCode){
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByCode(){
		return this.createdByCode;
	}

	public void setCreatedByName(java.lang.String createdByName){
		this.createdByName = createdByName;
	}

	public java.lang.String getCreatedByName(){
		return this.createdByName;
	}

	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate(){
		return this.createdDate;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName){
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}
}

package com.yh.hr.info.ver.unit.comm.dto;


public class VerPbRewardInfoDTO {
	/**
     *RewardOid
     **/
	private java.lang.Long rewardOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *奖励名称
     **/
	private java.lang.String rewardName;
	/**
	 * 奖惩情况
	 */
	private java.lang.String jcqk;

	/**
     *奖励类别YHRS0076
     **/
	private java.lang.String rewardTypeCode;
    /**
     *授予荣誉称号级别YHRS0077
     **/
	private java.lang.String honourLevelCode;
    /**
     *奖励原因类别YHRS0078
     **/
	private java.lang.String rewardReasonCode;
    /**
     *奖励原因类别描述
     **/
	private java.lang.String rewardReasonName;
    /**
     *奖励原因
     **/
	private java.lang.String encouragementReason;
    /**
     *奖励等级
     **/
	private java.lang.String rewarLevel;
    /**
     *奖励批准单位
     **/
	private java.lang.String rewaApprUnit;
    /**
     *奖励批准单位类别YHRS0079
     **/
	private java.lang.String rewardUnitType;
    /**
     *奖励批准日期
     **/
	private java.util.Date rewardApprDate;
	//private String rewardApprDateStr;
    /**
     *奖励文号
     **/
	private java.lang.String rewardFileno;
    /**
     *享受待遇类别YHRS0080
     **/
	private java.lang.String enjoyTreatmentType;
    /**
     *是否影响工资变动YHRS0003
     **/
	private java.lang.String isAffectWage;
    /**
     *奖励金额
     **/
	private java.lang.String rewardMoney;
    /**
     *奖励金发放单位
     **/
	private java.lang.String provideUnit;
    /**
     *是否撤销奖励YHRS0003
     **/
	private java.lang.String isQuashReward;
    /**
     *撤销奖励原因
     **/
	private java.lang.String quashRewardReason;
    /**
     *撤销奖励单位
     **/
	private java.lang.String quashRewardUnit;
    /**
     *撤销奖励日期
     **/
	private java.util.Date quashRewardDate;
	//private String quashRewardDateStr;
	/**
     *撤销奖励文号
     **/
	private java.lang.String quashRewardFileno;
    /**
     *撤销奖励批准单位类别YHRS0079
     **/
	private java.lang.String quashRewardUnitType;
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
* public JhcPbRewardInfo(java.lang.Long rewardOid) {
*  *       this.rewardOid = rewardOid;
**   }
**/
	public void setRewardOid(java.lang.Long rewardOid){
		this.rewardOid = rewardOid;
	}

	public java.lang.Long getRewardOid(){
		return this.rewardOid;
	}

	public java.lang.String getJcqk() {
		return jcqk;
	}
	
	public void setJcqk(java.lang.String jcqk) {
		this.jcqk = jcqk;
	}
	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setRewardName(java.lang.String rewardName){
		this.rewardName = rewardName;
	}

	public java.lang.String getRewardName(){
		return this.rewardName;
	}

	public void setRewardTypeCode(java.lang.String rewardTypeCode){
		this.rewardTypeCode = rewardTypeCode;
	}

	public java.lang.String getRewardTypeCode(){
		return this.rewardTypeCode;
	}

	public void setHonourLevelCode(java.lang.String honourLevelCode){
		this.honourLevelCode = honourLevelCode;
	}

	public java.lang.String getHonourLevelCode(){
		return this.honourLevelCode;
	}

	public void setRewardReasonCode(java.lang.String rewardReasonCode){
		this.rewardReasonCode = rewardReasonCode;
	}

	public java.lang.String getRewardReasonCode(){
		return this.rewardReasonCode;
	}

	public void setRewardReasonName(java.lang.String rewardReasonName){
		this.rewardReasonName = rewardReasonName;
	}

	public java.lang.String getRewardReasonName(){
		return this.rewardReasonName;
	}

	public void setEncouragementReason(java.lang.String encouragementReason){
		this.encouragementReason = encouragementReason;
	}

	public java.lang.String getEncouragementReason(){
		return this.encouragementReason;
	}

	public void setRewarLevel(java.lang.String rewarLevel){
		this.rewarLevel = rewarLevel;
	}

	public java.lang.String getRewarLevel(){
		return this.rewarLevel;
	}

	public void setRewaApprUnit(java.lang.String rewaApprUnit){
		this.rewaApprUnit = rewaApprUnit;
	}

	public java.lang.String getRewaApprUnit(){
		return this.rewaApprUnit;
	}

	public void setRewardUnitType(java.lang.String rewardUnitType){
		this.rewardUnitType = rewardUnitType;
	}

	public java.lang.String getRewardUnitType(){
		return this.rewardUnitType;
	}

	public void setRewardApprDate(java.util.Date rewardApprDate){
		this.rewardApprDate = rewardApprDate;
	}

	public java.util.Date getRewardApprDate(){
		return this.rewardApprDate;
	}
	/*public String getRewardApprDateStr() {
		return rewardApprDateStr;
	}
	public void setRewardApprDateStr(String rewardApprDateStr) throws DateParseException {
		if(!StringUtils.isEmpty(rewardApprDateStr)){
			//this.rewardApprDate = DateUtil.parseDate(rewardApprDateStr);
			this.rewardApprDateStr = rewardApprDateStr;
		}
		else
			rewardApprDateStr = "";
	}*/
	public void setRewardFileno(java.lang.String rewardFileno){
		this.rewardFileno = rewardFileno;
	}

	public java.lang.String getRewardFileno(){
		return this.rewardFileno;
	}

	public void setEnjoyTreatmentType(java.lang.String enjoyTreatmentType){
		this.enjoyTreatmentType = enjoyTreatmentType;
	}

	public java.lang.String getEnjoyTreatmentType(){
		return this.enjoyTreatmentType;
	}

	public void setIsAffectWage(java.lang.String isAffectWage){
		this.isAffectWage = isAffectWage;
	}

	public java.lang.String getIsAffectWage(){
		return this.isAffectWage;
	}

	public void setRewardMoney(java.lang.String rewardMoney){
		this.rewardMoney = rewardMoney;
	}

	public java.lang.String getRewardMoney(){
		return this.rewardMoney;
	}

	public void setProvideUnit(java.lang.String provideUnit){
		this.provideUnit = provideUnit;
	}

	public java.lang.String getProvideUnit(){
		return this.provideUnit;
	}

	public void setIsQuashReward(java.lang.String isQuashReward){
		this.isQuashReward = isQuashReward;
	}

	public java.lang.String getIsQuashReward(){
		return this.isQuashReward;
	}

	public void setQuashRewardReason(java.lang.String quashRewardReason){
		this.quashRewardReason = quashRewardReason;
	}

	public java.lang.String getQuashRewardReason(){
		return this.quashRewardReason;
	}

	public void setQuashRewardUnit(java.lang.String quashRewardUnit){
		this.quashRewardUnit = quashRewardUnit;
	}

	public java.lang.String getQuashRewardUnit(){
		return this.quashRewardUnit;
	}

	public void setQuashRewardDate(java.util.Date quashRewardDate){
		this.quashRewardDate = quashRewardDate;
	}

	public java.util.Date getQuashRewardDate(){
		return this.quashRewardDate;
	}
	/*public String getQuashRewardDateStr() {
		return quashRewardDateStr;
	}

	public void setQuashRewardDateStr(String quashRewardDateStr) throws DateParseException {
		if(!StringUtils.isEmpty(quashRewardDateStr)){
			//this.quashRewardDate = DateUtil.parseDate(quashRewardDateStr);		
			this.quashRewardDateStr = quashRewardDateStr;
		}
		else
			quashRewardDateStr = "";
	}*/
	public void setQuashRewardFileno(java.lang.String quashRewardFileno){
		this.quashRewardFileno = quashRewardFileno;
	}

	public java.lang.String getQuashRewardFileno(){
		return this.quashRewardFileno;
	}

	public void setQuashRewardUnitType(java.lang.String quashRewardUnitType){
		this.quashRewardUnitType = quashRewardUnitType;
	}

	public java.lang.String getQuashRewardUnitType(){
		return this.quashRewardUnitType;
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

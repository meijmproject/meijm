package com.yh.hr.res.pt.dto;

public class PtGoOutInfoDTO {
	
	/**
     *主键ID
     **/
	private java.lang.Long goOutOid;
    /**
     *人员ID
     **/
	private java.lang.Long bizPersonOid;
	/**
	 * 基础OID
	 */
	private java.lang.Long baseGoOutOid;
    /**
     *外出类型 1-参会;2-讲课;3-短期;4-培训;5-交流;6-学习
     **/
	private java.lang.String goOutType;
    /**
     *出访机构
     **/
	private java.lang.String visitOrg;
    /**
     *外出地点
     **/
	private java.lang.String goOutAddress;
    /**
     *经费来源
     **/
	private java.lang.String budgetFrom;
    /**
     *经费预算
     **/
	private java.lang.Float fundsBudget;
    /**
     *外出时间
     **/
	private java.util.Date startDate;
    /**
     *返回时间
     **/
	private java.util.Date endDate;
	/**
	 * 开始当天假长
	 */
	private Double startDateDays;
	/**
	 * 结束当天假长
	 */
	private Double endDateDays;
    /**
     *外出天数
     **/
	private java.lang.Float dayCount;
	/**
	 * 含节假日天数
	 */
	private Double statutoryHolidayDays;
    /**
     *工作交接人
     **/
	private java.lang.String handoverMan;
    /**
     *外出事由
     **/
	private java.lang.String reason;
    /**
     *备注
     **/
	private java.lang.String remark;
	/**
	 * 紧急联络人
	 */
	private String emergContact;
	/**
	 * 紧急联系人电话
	 */
	private String emergPhone;
	/**
	 * 外事办批件号
	 */
	private java.lang.String foreignAffairsNo;
	/**
	 * 工作交接事项
	 */
	private java.lang.String jobHandover;
    /**
     *创建人ID
     **/
	private java.lang.String createBy;
    /**
     *创建人名称
     **/
	private java.lang.String createName;
    /**
     *创建时间
     **/
	private java.util.Date createDate;
    /**
     *修改人ID
     **/
	private java.lang.String updateBy;
    /**
     *修改人名称
     **/
	private java.lang.String updateName;
    /**
     *修改时间
     **/
	private java.util.Date updateDate;

	public void setGoOutOid(java.lang.Long goOutOid){
		this.goOutOid = goOutOid;
	}

	public java.lang.Long getGoOutOid(){
		return this.goOutOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid){
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Long getBizPersonOid(){
		return this.bizPersonOid;
	}

	public void setGoOutType(java.lang.String goOutType){
		this.goOutType = goOutType;
	}

	public java.lang.String getGoOutType(){
		return this.goOutType;
	}

	public void setVisitOrg(java.lang.String visitOrg){
		this.visitOrg = visitOrg;
	}

	public java.lang.String getVisitOrg(){
		return this.visitOrg;
	}

	public void setGoOutAddress(java.lang.String goOutAddress){
		this.goOutAddress = goOutAddress;
	}

	public java.lang.String getGoOutAddress(){
		return this.goOutAddress;
	}

	public void setBudgetFrom(java.lang.String budgetFrom){
		this.budgetFrom = budgetFrom;
	}

	public java.lang.String getBudgetFrom(){
		return this.budgetFrom;
	}

	public void setFundsBudget(java.lang.Float fundsBudget){
		this.fundsBudget = fundsBudget;
	}

	public java.lang.Float getFundsBudget(){
		return this.fundsBudget;
	}

	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}

	public java.util.Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}

	public java.util.Date getEndDate(){
		return this.endDate;
	}

	public Double getStartDateDays() {
		return startDateDays;
	}

	public void setStartDateDays(Double startDateDays) {
		this.startDateDays = startDateDays;
	}

	public Double getEndDateDays() {
		return endDateDays;
	}

	public void setEndDateDays(Double endDateDays) {
		this.endDateDays = endDateDays;
	}

	public void setDayCount(java.lang.Float dayCount){
		this.dayCount = dayCount;
	}

	public java.lang.Float getDayCount(){
		return this.dayCount;
	}

	public Double getStatutoryHolidayDays() {
		return statutoryHolidayDays;
	}

	public void setStatutoryHolidayDays(Double statutoryHolidayDays) {
		this.statutoryHolidayDays = statutoryHolidayDays;
	}

	public void setHandoverMan(java.lang.String handoverMan){
		this.handoverMan = handoverMan;
	}

	public java.lang.String getHandoverMan(){
		return this.handoverMan;
	}

	public void setReason(java.lang.String reason){
		this.reason = reason;
	}

	public java.lang.String getReason(){
		return this.reason;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
	}

	public String getEmergContact() {
		return emergContact;
	}

	public void setEmergContact(String emergContact) {
		this.emergContact = emergContact;
	}

	public String getEmergPhone() {
		return emergPhone;
	}

	public void setEmergPhone(String emergPhone) {
		this.emergPhone = emergPhone;
	}

	public java.lang.String getForeignAffairsNo() {
		return foreignAffairsNo;
	}

	public void setForeignAffairsNo(java.lang.String foreignAffairsNo) {
		this.foreignAffairsNo = foreignAffairsNo;
	}

	public java.lang.String getJobHandover() {
		return jobHandover;
	}

	public void setJobHandover(java.lang.String jobHandover) {
		this.jobHandover = jobHandover;
	}

	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}

	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}

	public java.lang.String getCreateName(){
		return this.createName;
	}

	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}

	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}

	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}

	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}

	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	public java.lang.Long getBaseGoOutOid() {
		return baseGoOutOid;
	}

	public void setBaseGoOutOid(java.lang.Long baseGoOutOid) {
		this.baseGoOutOid = baseGoOutOid;
	}
}

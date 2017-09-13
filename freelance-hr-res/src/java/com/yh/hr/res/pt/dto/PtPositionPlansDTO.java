package com.yh.hr.res.pt.dto;

public class PtPositionPlansDTO {

	private java.lang.Long  	ptPostOid;	//主键
	private java.lang.Long  	bizPersonOid;	//BizPersonOid
	private java.lang.Long  	dutyUnitOid;	//聘任单位OID
	private java.lang.String	dutyUnitName;	//聘任单位名称
	private java.lang.Long  	deptOid;	//内设机构OID
	private java.lang.String	deptName;	//内设机构名称
	private java.lang.String	positionStatus;	//岗位聘任状态YHRS0026
	private java.lang.String	positionType;	//岗位类别YHRS0022
	private java.lang.String	positionLevel;	//岗位级别YHRS0023
	private java.lang.String	positionName;	//岗位名称
	private java.util.Date  	beginDate;	//岗位聘任开始时间
	private java.util.Date  	endDate;	//岗位聘任拟截止时间
	private java.util.Date  	endDateActual;	//岗位聘任实际截止时间
	private java.lang.String	specialPositionType;	//特殊岗位类别 YHRS0111 如：教师、护士
	private java.lang.String	isMPosition;	//是否主岗位 YHRS0003
	private java.lang.String	remark;	//备注
	private java.lang.Long  	positioningOid;	//任职聘任信息OID，外键关联免职聘任信息表
	private java.lang.String	dutyRecordType;	//职务类型YHRS0036
	private java.lang.String    dutyLevel;      //职务级别YHRS0015
	private java.lang.String    wageDutyLevel;  // 工资职级，用于兑现工资待遇事业单位依据岗位级别转职务级别，可修改YHRS0015
	private java.lang.String    dutyAttribute; // 职务属性YHRS0028
	private java.lang.String    dutyName; // 职务名称
	private java.lang.String    profTechCode; // 专业技术资格编码 YHRS0030
	private java.lang.String    qualificationsCode; // 职业资格编码 YHRS9004
	private java.lang.String    positioningReason; // 任职原因YHRS0033
	private java.lang.String    positioningTypeCode; // 任职方式YHRS0032
	private java.lang.String    dutyChangeType; // 变动类型YHRS0034
	private java.lang.String    dutyChangeTypeName; // 变动类型描述
	private java.lang.String    positioningFileno; // 任职文号
	private java.lang.String    positioningUnitDesc; // 任命(聘任)单位名称
	private java.lang.String	dutyType;	//职务类别YHRS0027
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate

	public java.util.Date getBeginDate() {
		return beginDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public java.util.Date getEndDateActual() {
		return endDateActual;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public void setEndDateActual(java.util.Date endDateActual) {
		this.endDateActual = endDateActual;
	}

	public PtPositionPlansDTO() {
		
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid){
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Long getBizPersonOid(){
		return this.bizPersonOid;
	}

	public void setDutyUnitOid(java.lang.Long dutyUnitOid){
		this.dutyUnitOid = dutyUnitOid;
	}

	public java.lang.Long getDutyUnitOid(){
		return this.dutyUnitOid;
	}

	public void setDutyUnitName(java.lang.String dutyUnitName){
		this.dutyUnitName = dutyUnitName;
	}

	public java.lang.String getDutyUnitName(){
		return this.dutyUnitName;
	}

	public void setDeptOid(java.lang.Long deptOid){
		this.deptOid = deptOid;
	}

	public java.lang.Long getDeptOid(){
		return this.deptOid;
	}

	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}

	public java.lang.String getDeptName(){
		return this.deptName;
	}

	public void setPositionStatus(java.lang.String positionStatus){
		this.positionStatus = positionStatus;
	}

	public java.lang.String getPositionStatus(){
		return this.positionStatus;
	}

	public void setPositionType(java.lang.String positionType){
		this.positionType = positionType;
	}

	public java.lang.String getPositionType(){
		return this.positionType;
	}

	public void setPositionLevel(java.lang.String positionLevel){
		this.positionLevel = positionLevel;
	}

	public java.lang.String getPositionLevel(){
		return this.positionLevel;
	}

	public void setPositionName(java.lang.String positionName){
		this.positionName = positionName;
	}

	public java.lang.String getPositionName(){
		return this.positionName;
	}


	public void setSpecialPositionType(java.lang.String specialPositionType){
		this.specialPositionType = specialPositionType;
	}

	public java.lang.String getSpecialPositionType(){
		return this.specialPositionType;
	}

	public void setIsMPosition(java.lang.String isMPosition){
		this.isMPosition = isMPosition;
	}

	public java.lang.String getIsMPosition(){
		return this.isMPosition;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
	}

	public void setDutyRecordType(java.lang.String dutyRecordType){
		this.dutyRecordType = dutyRecordType;
	}

	public java.lang.String getDutyRecordType(){
		return this.dutyRecordType;
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

	public java.lang.String getDutyLevel() {
		return dutyLevel;
	}

	public void setDutyLevel(java.lang.String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}

	public java.lang.Long getPtPostOid() {
		return ptPostOid;
	}

	public java.lang.Long getPositioningOid() {
		return positioningOid;
	}

	public java.lang.String getWageDutyLevel() {
		return wageDutyLevel;
	}

	public java.lang.String getDutyAttribute() {
		return dutyAttribute;
	}

	public java.lang.String getDutyName() {
		return dutyName;
	}

	public java.lang.String getProfTechCode() {
		return profTechCode;
	}

	public java.lang.String getQualificationsCode() {
		return qualificationsCode;
	}

	public java.lang.String getPositioningReason() {
		return positioningReason;
	}

	public java.lang.String getPositioningTypeCode() {
		return positioningTypeCode;
	}

	public java.lang.String getDutyChangeType() {
		return dutyChangeType;
	}

	public java.lang.String getDutyChangeTypeName() {
		return dutyChangeTypeName;
	}

	public java.lang.String getPositioningFileno() {
		return positioningFileno;
	}

	public java.lang.String getPositioningUnitDesc() {
		return positioningUnitDesc;
	}

	public void setPtPostOid(java.lang.Long ptPostOid) {
		this.ptPostOid = ptPostOid;
	}

	public void setPositioningOid(java.lang.Long positioningOid) {
		this.positioningOid = positioningOid;
	}

	public void setWageDutyLevel(java.lang.String wageDutyLevel) {
		this.wageDutyLevel = wageDutyLevel;
	}

	public void setDutyAttribute(java.lang.String dutyAttribute) {
		this.dutyAttribute = dutyAttribute;
	}

	public void setDutyName(java.lang.String dutyName) {
		this.dutyName = dutyName;
	}

	public void setProfTechCode(java.lang.String profTechCode) {
		this.profTechCode = profTechCode;
	}

	public void setQualificationsCode(java.lang.String qualificationsCode) {
		this.qualificationsCode = qualificationsCode;
	}

	public void setPositioningReason(java.lang.String positioningReason) {
		this.positioningReason = positioningReason;
	}

	public void setPositioningTypeCode(java.lang.String positioningTypeCode) {
		this.positioningTypeCode = positioningTypeCode;
	}

	public void setDutyChangeType(java.lang.String dutyChangeType) {
		this.dutyChangeType = dutyChangeType;
	}

	public void setDutyChangeTypeName(java.lang.String dutyChangeTypeName) {
		this.dutyChangeTypeName = dutyChangeTypeName;
	}

	public void setPositioningFileno(java.lang.String positioningFileno) {
		this.positioningFileno = positioningFileno;
	}

	public void setPositioningUnitDesc(java.lang.String positioningUnitDesc) {
		this.positioningUnitDesc = positioningUnitDesc;
	}

	public java.lang.String getDutyType() {
		return dutyType;
	}

	public void setDutyType(java.lang.String dutyType) {
		this.dutyType = dutyType;
	}
}

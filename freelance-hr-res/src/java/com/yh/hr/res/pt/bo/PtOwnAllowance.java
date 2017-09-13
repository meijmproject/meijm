package com.yh.hr.res.pt.bo;
import com.yh.platform.core.bo.BaseBo;
//特岗津贴
public class PtOwnAllowance extends BaseBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4084609775248895359L;
	/**
     *主键
     **/
	private java.lang.Long ownAllowanceOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
	/**
     *bizPersonOid
     **/
	private java.lang.Long bizPersonOid;
    /**
     *津贴类别编码
     **/
	private java.lang.String allowanceCategoryCode;
    /**
     *津贴类别名称
     **/
	private java.lang.String allowanceCategoryName;
    /**
     *津贴编码
     **/
	private java.lang.String allowanceCode;
    /**
     *津贴名称
     **/
	private java.lang.String allowanceName;
    /**
     *金额
     **/
	private java.lang.Double allowanceAmount;	
    /**
     *开始享受时间
     **/
	private java.util.Date startDate;	
    /**
     *截止享受时间
     **/
	private java.util.Date endDate;
    /**
     *备注
     **/
	private java.lang.String remark;
	
	/**
     *操作类型
     **/
	private java.lang.String handleCode;
	/**
     *两条数据修改前后对应识别码
     **/
	private java.lang.Long handleMatchNo;
	/**
     *文件依据
     **/
	private java.lang.String fileBaseOn;
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

	
	public java.lang.String getHandleCode() {
		return handleCode;
	}


	public void setHandleCode(java.lang.String handleCode) {
		this.handleCode = handleCode;
	}


	public java.lang.Long getHandleMatchNo() {
		return handleMatchNo;
	}


	public void setHandleMatchNo(java.lang.Long handleMatchNo) {
		this.handleMatchNo = handleMatchNo;
	}
	public PtOwnAllowance() {
		
	}
	
	
	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}


	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}


	public java.lang.Long getOwnAllowanceOid() {
		return ownAllowanceOid;
	}

	public void setOwnAllowanceOid(java.lang.Long ownAllowanceOid) {
		this.ownAllowanceOid = ownAllowanceOid;
	}

	public java.lang.String getAllowanceCategoryCode() {
		return allowanceCategoryCode;
	}

	public void setAllowanceCategoryCode(java.lang.String allowanceCategoryCode) {
		this.allowanceCategoryCode = allowanceCategoryCode;
	}

	public java.lang.String getAllowanceCategoryName() {
		return allowanceCategoryName;
	}

	public void setAllowanceCategoryName(java.lang.String allowanceCategoryName) {
		this.allowanceCategoryName = allowanceCategoryName;
	}

	public java.lang.String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(java.lang.String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public java.lang.String getAllowanceName() {
		return allowanceName;
	}

	public void setAllowanceName(java.lang.String allowanceName) {
		this.allowanceName = allowanceName;
	}

	public java.lang.Double getAllowanceAmount() {
		return allowanceAmount;
	}

	public void setAllowanceAmount(java.lang.Double allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
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

	public java.lang.String getFileBaseOn() {
		return fileBaseOn;
	}

	public void setFileBaseOn(java.lang.String fileBaseOn) {
		this.fileBaseOn = fileBaseOn;
	}
	
}

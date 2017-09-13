/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-10
**/
package com.yh.hr.res.pb.bo;

import com.yh.platform.core.bo.BaseBo;

public class PbCertificateInfo extends BaseBo {
	private static final long serialVersionUID = 2515516587055227706L;

    /**
     *主键OID
     **/
	private java.lang.Long certificateOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *执业注册证书编号
     **/
	private java.lang.String certificateNo;
    /**
     *发证机构
     **/
	private java.lang.String approveOrganName;
    /**
     *审批日期
     **/
	private java.util.Date approvedDate;
    /**
     *签发日期
     **/
	private java.util.Date issuedDate;
    /**
     *执业范围
     **/
	private java.lang.String scopeCode;
    /**
     *执业地点
     **/
	private java.lang.String certificateUnit;
    /**
     *当前注册日期
     **/
	private java.util.Date certificateBeginDate;
    /**
     *当前注册有效截止日期
     **/
	private java.util.Date certificateEndDate;
    /**
     *是否注销
     **/
	private java.lang.String cancelFlag;
    /**
     *注销日期
     **/
	private java.util.Date cancelDate;
    /**
     *备注
     **/
	private java.lang.String remark;
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

	public PbCertificateInfo() {}

    public PbCertificateInfo(java.lang.Long certificateOid) {
        this.certificateOid = certificateOid;
    }

	public void setCertificateOid(java.lang.Long certificateOid){
		this.certificateOid = certificateOid;
	}

	public java.lang.Long getCertificateOid(){
		return this.certificateOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setCertificateNo(java.lang.String certificateNo){
		this.certificateNo = certificateNo;
	}

	public java.lang.String getCertificateNo(){
		return this.certificateNo;
	}

	public void setApproveOrganName(java.lang.String approveOrganName){
		this.approveOrganName = approveOrganName;
	}

	public java.lang.String getApproveOrganName(){
		return this.approveOrganName;
	}

	public void setApprovedDate(java.util.Date approvedDate){
		this.approvedDate = approvedDate;
	}

	public java.util.Date getApprovedDate(){
		return this.approvedDate;
	}

	public void setIssuedDate(java.util.Date issuedDate){
		this.issuedDate = issuedDate;
	}

	public java.util.Date getIssuedDate(){
		return this.issuedDate;
	}

	public void setScopeCode(java.lang.String scopeCode){
		this.scopeCode = scopeCode;
	}

	public java.lang.String getScopeCode(){
		return this.scopeCode;
	}

	public void setCertificateUnit(java.lang.String certificateUnit){
		this.certificateUnit = certificateUnit;
	}

	public java.lang.String getCertificateUnit(){
		return this.certificateUnit;
	}

	public void setCertificateBeginDate(java.util.Date certificateBeginDate){
		this.certificateBeginDate = certificateBeginDate;
	}

	public java.util.Date getCertificateBeginDate(){
		return this.certificateBeginDate;
	}

	public void setCertificateEndDate(java.util.Date certificateEndDate){
		this.certificateEndDate = certificateEndDate;
	}

	public java.util.Date getCertificateEndDate(){
		return this.certificateEndDate;
	}

	public void setCancelFlag(java.lang.String cancelFlag){
		this.cancelFlag = cancelFlag;
	}

	public java.lang.String getCancelFlag(){
		return this.cancelFlag;
	}

	public void setCancelDate(java.util.Date cancelDate){
		this.cancelDate = cancelDate;
	}

	public java.util.Date getCancelDate(){
		return this.cancelDate;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
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

}
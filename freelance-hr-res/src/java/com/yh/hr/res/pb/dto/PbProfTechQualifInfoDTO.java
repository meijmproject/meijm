/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-10 
 */
package com.yh.hr.res.pb.dto;

import com.yh.platform.core.util.DateUtil;


public class PbProfTechQualifInfoDTO {

	private java.lang.Long profTechQualifOid;        //主键OID
	private java.lang.Long personOid;        //PersonOid
	private java.lang.String certificateNo;        //资格证书编号
	private java.lang.String profTechCode;        //专业技术资格CODE
	private java.lang.String profTechName;        //专业技术资格名称
	private java.lang.String profTechLevel;        //专业技术资格等级
	private java.lang.String profTechLevelName;        //专业技术资格等级描述
	private java.lang.String specialityName;        //专业名称
	private java.util.Date procureDate;        //取得资格日期
	private java.lang.String procureDateStr;
	private java.util.Date validityDate;        //有效期
	private java.lang.String validityDateStr;
	private java.lang.String acquireApproachCode;        //取得资格途径
	private java.lang.String issueOrgan;        //发证机构
	private java.lang.String approveOrganName;        //资格审批机构名称
	private java.lang.String isHighestLevel;        //是否主要专业技术资格
	private java.lang.String remark;        //备注
	private java.lang.String createBy;        //创建人ID
	private java.lang.String createName;        //创建人名称
	private java.util.Date createDate;        //创建时间
	private java.lang.String updateBy;        //修改人ID
	private java.lang.String updateName;        //修改人名称
	private java.util.Date updateDate;        //修改时间

	public void setProfTechQualifOid(java.lang.Long profTechQualifOid){
		this.profTechQualifOid = profTechQualifOid;
	}

	public java.lang.Long getProfTechQualifOid(){
		return this.profTechQualifOid;
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
	public void setProfTechCode(java.lang.String profTechCode){
		this.profTechCode = profTechCode;
	}

	public java.lang.String getProfTechCode(){
		return this.profTechCode;
	}
	public void setProfTechName(java.lang.String profTechName){
		this.profTechName = profTechName;
	}

	public java.lang.String getProfTechName(){
		return this.profTechName;
	}
	public void setProfTechLevel(java.lang.String profTechLevel){
		this.profTechLevel = profTechLevel;
	}

	public java.lang.String getProfTechLevel(){
		return this.profTechLevel;
	}
	public void setProfTechLevelName(java.lang.String profTechLevelName){
		this.profTechLevelName = profTechLevelName;
	}

	public java.lang.String getProfTechLevelName(){
		return this.profTechLevelName;
	}
	public void setSpecialityName(java.lang.String specialityName){
		this.specialityName = specialityName;
	}

	public java.lang.String getSpecialityName(){
		return this.specialityName;
	}
	public void setProcureDate(java.util.Date procureDate){
		this.procureDate = procureDate;
		this.procureDateStr =DateUtil.formatDate(procureDate);
	}

	public java.util.Date getProcureDate(){
		return this.procureDate;
	}
	public void setValidityDate(java.util.Date validityDate){
		this.validityDate = validityDate;
		this.validityDateStr =DateUtil.formatDate(validityDate);
	}

	public java.util.Date getValidityDate(){
		return this.validityDate;
	}
	public void setAcquireApproachCode(java.lang.String acquireApproachCode){
		this.acquireApproachCode = acquireApproachCode;
	}

	public java.lang.String getAcquireApproachCode(){
		return this.acquireApproachCode;
	}
	public void setIssueOrgan(java.lang.String issueOrgan){
		this.issueOrgan = issueOrgan;
	}

	public java.lang.String getIssueOrgan(){
		return this.issueOrgan;
	}
	public void setApproveOrganName(java.lang.String approveOrganName){
		this.approveOrganName = approveOrganName;
	}

	public java.lang.String getApproveOrganName(){
		return this.approveOrganName;
	}
	public void setIsHighestLevel(java.lang.String isHighestLevel){
		this.isHighestLevel = isHighestLevel;
	}

	public java.lang.String getIsHighestLevel(){
		return this.isHighestLevel;
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

	public java.lang.String getProcureDateStr() {
		return procureDateStr;
	}

	public void setProcureDateStr(java.lang.String procureDateStr) {
		this.procureDateStr = procureDateStr;
		this.procureDate =DateUtil.parseDate(procureDateStr);
	}

	public java.lang.String getValidityDateStr() {
		return validityDateStr;
	}

	public void setValidityDateStr(java.lang.String validityDateStr) {
		this.validityDateStr = validityDateStr;
		this.validityDate =DateUtil.parseDate(validityDateStr);
	}
}
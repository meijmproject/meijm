package com.yh.hr.component.wardset.web.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class CfWardForm extends ValidatorForm {
	private static final long serialVersionUID = 1213310119120L;

	private Long waedOid;		//主键
	private Long unitOid;
	private Long deptOid;		//病区科室
	private Long bedNum;		//床位数
	private String remark;		//备注
	private String createdByCode;	//创建人ID
	private String createdByName;	//创建人姓名
	private Date createdDate;	//创建日期
	private String createdDateStr;
	private String updatedByCode;	//修改人ID
	private String updatedByName;	//修改人姓名
	private Date updatedDate;	//修改日期
	private String updatedDateStr;
	private String waedType;	//病区类型
	public Long getWaedOid() {
		return waedOid;
	}
	public void setWaedOid(Long waedOid) {
		this.waedOid = waedOid;
	}
	
	public Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(Long unitOid) {
		this.unitOid = unitOid;
	}
	public Long getDeptOid() {
		return deptOid;
	}
	public void setDeptOid(Long deptOid) {
		this.deptOid = deptOid;
	}
	public Long getBedNum() {
		return bedNum;
	}
	public void setBedNum(Long bedNum) {
		this.bedNum = bedNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		if(null != createdDate){
			this.createdDateStr = DateUtil.format(createdDate, "yyyy-MM-dd");
         }
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
        if(StringUtils.isNotEmpty(createdDateStr)){
               this.createdDate = DateUtil.parse(createdDateStr, "yyyy-MM-dd");
        }
	}
	public String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
		if(null != updatedDate){
			this.updatedDateStr = DateUtil.format(updatedDate, "yyyy-MM-dd");
         }
	}
	public String getUpdatedDateStr() {
		return updatedDateStr;
	}
	public void setUpdatedDateStr(String updatedDateStr) {
		this.updatedDateStr = updatedDateStr;
        if(StringUtils.isNotEmpty(updatedDateStr)){
               this.updatedDate = DateUtil.parse(updatedDateStr, "yyyy-MM-dd");
        }
	}
	public String getWaedType() {
		return waedType;
	}
	public void setWaedType(String waedType) {
		this.waedType = waedType;
	}
	
}

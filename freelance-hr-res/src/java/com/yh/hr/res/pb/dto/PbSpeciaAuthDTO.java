/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-13 
 */
package com.yh.hr.res.pb.dto;

import com.yh.platform.core.util.DateUtil;


public class PbSpeciaAuthDTO {

	private java.lang.Long speciaAuthOid;        //主键OID
	private java.lang.Long personOid;        //PersonOid
	private java.lang.String authType;        //权限类型
	private java.lang.String authLevel;        //授权级别
	private java.lang.String authStatus;        //授权状态
	private java.util.Date authDate;           //授权时间
	private java.lang.String authDateStr;       //授权时间
	private java.util.Date cancelAuthDate;           //取消授权时间
	private java.lang.String cancelAuthDateStr;       //取消授权时间
	private java.lang.String remark;          //备注信息
	private java.lang.String createBy;        //创建人ID
	private java.lang.String createName;        //创建人名称
	private java.util.Date createDate;        //创建时间
	private java.lang.String createDateStr; 
	private java.lang.String updateBy;        //修改人ID
	private java.lang.String updateName;        //修改人名称
	private java.util.Date updateDate;        //修改时间
	private java.lang.String updateDateStr;
	
	public void setSpeciaAuthOid(java.lang.Long speciaAuthOid){
		this.speciaAuthOid = speciaAuthOid;
	}

	public java.lang.Long getSpeciaAuthOid(){
		return this.speciaAuthOid;
	}
	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}
	public void setAuthType(java.lang.String authType){
		this.authType = authType;
	}

	public java.lang.String getAuthType(){
		return this.authType;
	}
	public void setAuthLevel(java.lang.String authLevel){
		this.authLevel = authLevel;
	}

	public java.lang.String getAuthLevel(){
		return this.authLevel;
	}
	public void setAuthStatus(java.lang.String authStatus){
		this.authStatus = authStatus;
	}

	public java.lang.String getAuthStatus(){
		return this.authStatus;
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
		if(createDate!=null){
			this.createDateStr = DateUtil.formatDate(createDate);
		}
	}

	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	public java.lang.String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(java.lang.String createDateStr) {
		this.createDateStr = createDateStr;
		if(createDateStr!=null){
			this.createDate = DateUtil.parseDate(createDateStr);
		}
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
		if(updateDate!=null){
			this.updateDateStr = DateUtil.formatDate(updateDate);
		}
	}

	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	public java.lang.String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(java.lang.String updateDateStr) {
		this.updateDateStr = updateDateStr;
		if(updateDateStr!=null){
			this.updateDate = DateUtil.parseDate(createDateStr);
		}
	}

	public java.util.Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(java.util.Date authDate) {
		this.authDate = authDate;
		if(authDate!=null){
			this.authDateStr = DateUtil.formatDate(authDate);
		}
	}

	public java.lang.String getAuthDateStr() {
		return authDateStr;
	}

	public void setAuthDateStr(java.lang.String authDateStr) {
		this.authDateStr = authDateStr;
		if(authDateStr!=null){
			this.authDate = DateUtil.parseDate(authDateStr);
		}
	}

	public java.util.Date getCancelAuthDate() {
		return cancelAuthDate;
	}

	public void setCancelAuthDate(java.util.Date cancelAuthDate) {
		this.cancelAuthDate = cancelAuthDate;
		if(cancelAuthDate!=null){
			this.cancelAuthDateStr = DateUtil.formatDate(cancelAuthDate);
		}
	}

	public java.lang.String getCancelAuthDateStr() {
		return cancelAuthDateStr;
	}

	public void setCancelAuthDateStr(java.lang.String cancelAuthDateStr) {
		this.cancelAuthDateStr = cancelAuthDateStr;
		if(cancelAuthDateStr!=null){
			this.cancelAuthDate = DateUtil.parseDate(cancelAuthDateStr);
		}
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
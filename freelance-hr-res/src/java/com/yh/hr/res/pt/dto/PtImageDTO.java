/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-10-09
**/
package com.yh.hr.res.pt.dto;

public class PtImageDTO {

	private java.lang.Long  	bizPersonOid;	//BizPersonOid
	private byte[]	photoPath;	//图片
	private java.lang.String	photoType;	//图片扩展名
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate

	public PtImageDTO() {
		
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid){
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Long getBizPersonOid(){
		return this.bizPersonOid;
	}

	public void setPhotoPath(byte[] photoPath){
		this.photoPath = photoPath;
	}

	public byte[] getPhotoPath(){
		return this.photoPath;
	}

	public void setPhotoType(java.lang.String photoType){
		this.photoType = photoType;
	}

	public java.lang.String getPhotoType(){
		return this.photoType;
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

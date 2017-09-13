/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-03-14
**/
package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

public class PtPhoto extends BaseBo {
	private static final long serialVersionUID = 2515516587055227706L;

    /**
     *主键ID
     **/
	private java.lang.Long photoOid;
    /**
     *业务人员OID
     **/
	private java.lang.Long bizPersonOid;
    /**
     *图片
     **/
	private java.lang.String photoName;
	 /**
     *图片在服务器的名字
     **/
	private java.lang.String photoCode;
    /**
     *图片扩展名
     **/
	private java.lang.String photoType;
    /**
     *影像类别
     **/
	private java.lang.String pictureType;
    /**
     *来源类型（区分是那个信息集的影像）
     **/
	private java.lang.String refType;
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

	public PtPhoto() {}

    public PtPhoto(java.lang.Long photoOid) {
        this.photoOid = photoOid;
    }

	public void setPhotoOid(java.lang.Long photoOid){
		this.photoOid = photoOid;
	}

	public java.lang.Long getPhotoOid(){
		return this.photoOid;
	}

	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(java.lang.String photoName) {
		this.photoName = photoName;
	}

	public java.lang.String getPhotoCode() {
		return photoCode;
	}

	public void setPhotoCode(java.lang.String photoCode) {
		this.photoCode = photoCode;
	}

	public void setPhotoType(java.lang.String photoType){
		this.photoType = photoType;
	}

	public java.lang.String getPhotoType(){
		return this.photoType;
	}

	public void setPictureType(java.lang.String pictureType){
		this.pictureType = pictureType;
	}

	public java.lang.String getPictureType(){
		return this.pictureType;
	}

	public void setRefType(java.lang.String refType){
		this.refType = refType;
	}

	public java.lang.String getRefType(){
		return this.refType;
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

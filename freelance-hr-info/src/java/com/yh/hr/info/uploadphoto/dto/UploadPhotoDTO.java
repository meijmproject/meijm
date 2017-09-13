package com.yh.hr.info.uploadphoto.dto;

/**
 * 文件上传DTO
 * @author chenjl
 * @date 2017-03-27
 * @version 1.0
 */
public class UploadPhotoDTO {
	/**
     *主键ID
     **/
	private java.lang.Long photoOid;
    /**
     *人员OID
     **/
	private java.lang.Long personOid;
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
	
	private java.lang.String	uuid;	//文件唯一名称
	private java.lang.String	fileNameLocal;	//用户本地文件名称（上传文件名称）
	private java.lang.String	fileNameRemote;	//保存在服务器的文件名称
	private java.lang.String	filePathLocal;	//用户本地的文件路径
	private java.lang.String	filePathRemote;	//文件在服务器的路径
	private java.lang.String	fileMd5;	//文件的MD5值
	private java.lang.Long  	fileLength;	//文件总字节长度
	private java.lang.String	fileSize;	//文件大小描述
	private java.lang.Long  	filePos;	//文件已上传的位置
	private java.lang.Long  	postedLength;	//文件已上传的长度？
	private java.lang.String	postedPercent;	//已上传的百分比
	private java.util.Date  	postedTime;	//上传完成时间
	
	public java.lang.Long getPhotoOid() {
		return photoOid;
	}
	public void setPhotoOid(java.lang.Long photoOid) {
		this.photoOid = photoOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
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
	public java.lang.String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(java.lang.String photoType) {
		this.photoType = photoType;
	}
	public java.lang.String getPictureType() {
		return pictureType;
	}
	public void setPictureType(java.lang.String pictureType) {
		this.pictureType = pictureType;
	}
	public java.lang.String getRefType() {
		return refType;
	}
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public java.lang.String getUuid() {
		return uuid;
	}
	public void setUuid(java.lang.String uuid) {
		this.uuid = uuid;
	}
	public java.lang.String getFileNameLocal() {
		return fileNameLocal;
	}
	public void setFileNameLocal(java.lang.String fileNameLocal) {
		this.fileNameLocal = fileNameLocal;
	}
	public java.lang.String getFileNameRemote() {
		return fileNameRemote;
	}
	public void setFileNameRemote(java.lang.String fileNameRemote) {
		this.fileNameRemote = fileNameRemote;
	}
	public java.lang.String getFilePathLocal() {
		return filePathLocal;
	}
	public void setFilePathLocal(java.lang.String filePathLocal) {
		this.filePathLocal = filePathLocal;
	}
	public java.lang.String getFilePathRemote() {
		return filePathRemote;
	}
	public void setFilePathRemote(java.lang.String filePathRemote) {
		this.filePathRemote = filePathRemote;
	}
	public java.lang.String getFileMd5() {
		return fileMd5;
	}
	public void setFileMd5(java.lang.String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	public java.lang.Long getFileLength() {
		return fileLength;
	}
	public void setFileLength(java.lang.Long fileLength) {
		this.fileLength = fileLength;
	}
	public java.lang.String getFileSize() {
		return fileSize;
	}
	public void setFileSize(java.lang.String fileSize) {
		this.fileSize = fileSize;
	}
	public java.lang.Long getFilePos() {
		return filePos;
	}
	public void setFilePos(java.lang.Long filePos) {
		this.filePos = filePos;
	}
	public java.lang.Long getPostedLength() {
		return postedLength;
	}
	public void setPostedLength(java.lang.Long postedLength) {
		this.postedLength = postedLength;
	}
	public java.lang.String getPostedPercent() {
		return postedPercent;
	}
	public void setPostedPercent(java.lang.String postedPercent) {
		this.postedPercent = postedPercent;
	}
	public java.util.Date getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(java.util.Date postedTime) {
		this.postedTime = postedTime;
	}

}

package com.yh.hr.info.ver.unit.comm.dto;

public class VerPbImageDTO {
	/**
     *PersonOid
     **/
	private Long personOid;
    /**
     *图片
     **/
	private byte[] photoPath;
    /**
     *图片扩展名
     **/
	private String photoType;

	public void setPersonOid(Long personOid){
		this.personOid = personOid;
	}

	public Long getPersonOid(){
		return this.personOid;
	}

	public void setPhotoPath(byte[] photoPath){
		this.photoPath = photoPath;
	}

	public byte[] getPhotoPath(){
		return this.photoPath;
	}

	public void setPhotoType(String photoType){
		this.photoType = photoType;
	}

	public String getPhotoType(){
		return this.photoType;
	}

}

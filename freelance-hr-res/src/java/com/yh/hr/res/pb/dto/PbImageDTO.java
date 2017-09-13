package com.yh.hr.res.pb.dto;

public class PbImageDTO{
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

	public byte[] getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(byte[] photoPath) {
		this.photoPath = photoPath;
	}

	public void setPhotoType(String photoType){
		this.photoType = photoType;
	}

	public String getPhotoType(){
		return this.photoType;
	}

}

package com.yh.hr.info.uploadphoto.facade;

import java.util.List;

import com.yh.hr.info.uploadphoto.dto.UploadPhotoDTO;
import com.yh.hr.info.uploadphoto.service.UploadPhotoService;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.hr.res.pb.dto.PbPhotoDTO;
import com.yh.hr.res.pb.service.PbPhotoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 上传文件facade
 * @author	chenjl
 * @version	1.0,	17/03/25
 */
public class UploadPhotoFacade {

	private PbPhotoService pbPhotoService;
	private UploadPhotoService uploadPhotoService;
	
	public void setPbPhotoService(PbPhotoService pbPhotoService) {
		this.pbPhotoService = pbPhotoService;
	}

	public void setUploadPhotoService(UploadPhotoService uploadPhotoService) {
		this.uploadPhotoService = uploadPhotoService;
	}


	/**
	 * 保存文件信息
	 * @param personOid
	 * @param refType 
	 * @param uploadStatus 
	 * @throws ServiceException
	 */
	public void create(Long personOid, String refType, UploadStatusDTO uploadStatus) throws ServiceException {
		
		PbPhotoDTO pbPhoto = new PbPhotoDTO();
		
		pbPhoto.setPersonOid(personOid);
		pbPhoto.setRefType(refType);
		pbPhoto.setPhotoName(uploadStatus.getFileNameLocal());
		pbPhoto.setPhotoCode(uploadStatus.getFileNameRemote());
		pbPhoto.setPhotoType(uploadStatus.getFilePathRemote().substring(uploadStatus.getFilePathRemote().lastIndexOf('.')+1));
		Long photoOid=pbPhotoService.create(pbPhoto);
		
		uploadStatus.setPhotoOid(photoOid);
	}


	/**
	 * 查询上传文件信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listUploadPhoto(TableTagBean ttb) throws ServiceException {
		return uploadPhotoService.listUploadPhoto(ttb);
	}

	/**
	 * 删除已上传文件
	 * @param photoOid
	 * @throws ServiceException
	 */
	public void delete(Long photoOid) throws ServiceException{
		uploadPhotoService.delete(photoOid);
	}

	/**
	 * 获取已上传文件信息
	 * @param photoOid
	 * @return
	 * @throws ServiceException
	 */
	public UploadPhotoDTO get(Long photoOid) throws ServiceException{
		return uploadPhotoService.get(photoOid);
	}

}

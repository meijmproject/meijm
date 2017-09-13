package com.yh.hr.info.biz.bizuploadphoto.facade;

import java.util.List;

import com.yh.hr.info.biz.bizuploadphoto.dto.BizUploadPhotoDTO;
import com.yh.hr.info.biz.bizuploadphoto.service.BizUploadPhotoService;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.hr.res.pt.dto.PtPhotoDTO;
import com.yh.hr.res.pt.service.PtPhotoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 上传文件facade
 * @author	chenjl
 * @version	1.0,	17/03/25
 */
public class BizUploadPhotoFacade {

	private PtPhotoService ptPhotoService;
	private BizUploadPhotoService bizUploadPhotoService;
	
	public void setPtPhotoService(PtPhotoService ptPhotoService) {
		this.ptPhotoService = ptPhotoService;
	}

	public void setBizUploadPhotoService(BizUploadPhotoService bizUploadPhotoService) {
		this.bizUploadPhotoService = bizUploadPhotoService;
	}


	/**
	 * 保存文件信息
	 * @param bizPersonOid
	 * @param refType 
	 * @param uploadStatus 
	 * @throws ServiceException
	 */
	public void create(Long bizPersonOid, String refType, UploadStatusDTO uploadStatus) throws ServiceException {
		
		PtPhotoDTO ptPhoto = new PtPhotoDTO();
		
		ptPhoto.setBizPersonOid(bizPersonOid);
		ptPhoto.setRefType(refType);
		ptPhoto.setPhotoName(uploadStatus.getFileNameLocal());
		ptPhoto.setPhotoCode(uploadStatus.getFileNameRemote());
		ptPhoto.setPhotoType(uploadStatus.getFilePathRemote().substring(uploadStatus.getFilePathRemote().lastIndexOf('.')+1));
		Long photoOid=ptPhotoService.create(ptPhoto);
		
		uploadStatus.setPhotoOid(photoOid);
	}


	/**
	 * 查询上传文件信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listUploadPhoto(TableTagBean ttb) throws ServiceException {
		return bizUploadPhotoService.listUploadPhoto(ttb);
	}

	/**
	 * 删除已上传文件
	 * @param photoOid
	 * @throws ServiceException
	 */
	public void delete(Long photoOid) throws ServiceException{
		bizUploadPhotoService.delete(photoOid);
	}

	/**
	 * 获取已上传文件信息
	 * @param photoOid
	 * @return
	 * @throws ServiceException
	 */
	public BizUploadPhotoDTO get(Long photoOid) throws ServiceException{
		return bizUploadPhotoService.get(photoOid);
	}

}

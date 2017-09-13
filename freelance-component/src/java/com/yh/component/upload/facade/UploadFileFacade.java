package com.yh.component.upload.facade;

import com.yh.component.upload.bo.UploadFile;
import com.yh.component.upload.bo.UploadFileDetail;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.component.upload.service.UploadFileService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */
public class UploadFileFacade {

	private UploadFileService uploadFileService;
	
	public void setUploadFileService(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}
	
	/**
	 * 保存文件信息
	 * @param refCode
	 * @param refOid
	 * @param uploadStatus
	 * @throws ServiceException 
	 */
	public void create(String refCode, Long refOid, UploadStatusDTO uploadStatus) throws ServiceException {
		UploadFile uploadFile = uploadFileService.findByCodeOid(refCode, refOid);
		
		if (uploadFile == null) {
			uploadFile = new UploadFile();
			
			uploadFile.setRefCode(refCode);
			uploadFile.setRefOid(refOid);
			
			uploadFile.save();
		}
		
		UploadFileDetail detail = new UploadFileDetail();
		
		detail.setUploadFileOid(uploadFile.getUploadFileOid());
		detail.setFileName(uploadStatus.getFileNameLocal());
		detail.setFileNameTarget(uploadStatus.getUuid());
		detail.setPath(uploadStatus.getFilePathRemote());
		detail.setFileType(uploadStatus.getFilePathRemote().substring(uploadStatus.getFilePathRemote().lastIndexOf('.')+1));
		
		detail.save();
		
		uploadStatus.setFileOid(detail.getFileOid());
	}

}

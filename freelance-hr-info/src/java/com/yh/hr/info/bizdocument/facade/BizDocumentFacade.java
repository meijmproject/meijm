package com.yh.hr.info.bizdocument.facade;

import java.io.File;
import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.bo.UploadFile;
import com.yh.component.upload.bo.UploadFileDetail;
import com.yh.component.upload.bo.UploadStatus;
import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.component.upload.service.UploadFileService;
import com.yh.component.upload.service.UploadNodeRefService;
import com.yh.hr.info.bizdocument.service.BizDocumentService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class BizDocumentFacade {
	
	private UploadNodeRefService uploadNodeRefService;
	private BizDocumentService bizDocumentService;
	private UploadFileService uploadFileService;

	public void setUploadNodeRefService(UploadNodeRefService uploadNodeRefService) {
		this.uploadNodeRefService = uploadNodeRefService;
	}

	public void setBizDocumentService(BizDocumentService bizDocumentService) {
		this.bizDocumentService = bizDocumentService;
	}

	public void setUploadFileService(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	/**
	 * 查询文件列表
	 * @param uploadNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public List<UploadNodeRefDTO> listBizBusiness(String uploadNodeCode) throws ServiceException {
		return uploadNodeRefService.listBizBusiness(uploadNodeCode) ;
	}

	/**
	 * 查询业务文档列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listBizDocument(TableTagBean ttb) throws ServiceException{
		return bizDocumentService.listBizDocument(ttb);
	}

	public UploadStatusDTO get(String uuid) throws ServiceException{
		return BeanHelper.copyProperties(DaoUtil.get(UploadStatus.class, uuid), UploadStatusDTO.class);
	}

	public File getRefUploadDir(String refCode) throws ServiceException {
		return uploadNodeRefService.getRefUploadDir(refCode);
	}

	public void saveOrUpdate(UploadStatusDTO uploadStatus) throws ServiceException {
		BeanHelper.copyProperties(uploadStatus, UploadStatus.class).saveOrUpdate();
	}

	public void create(String refCode, UploadStatusDTO uploadStatus) throws ServiceException {
		UploadFile uploadFile = uploadFileService.findByCodeOid(refCode, null);
		
		if (uploadFile == null) {
			uploadFile = new UploadFile();
			
			uploadFile.setRefCode(refCode);
			
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

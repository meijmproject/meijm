package com.yh.hr.info.biz.bizuploadphoto.service.impl;

import java.io.File;
import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.bo.UploadStatus;
import com.yh.hr.info.biz.bizuploadphoto.dto.BizUploadPhotoDTO;
import com.yh.hr.info.biz.bizuploadphoto.queryhelper.BizUploadPhotoQueryHelper;
import com.yh.hr.info.biz.bizuploadphoto.service.BizUploadPhotoService;
import com.yh.hr.res.pt.bo.PtPhoto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 上传文件serviceImpl
 * @author chenjl
 * @date 2017-03-25
 * @version 1.0
 */
public class BizUploadPhotoServiceImpl implements BizUploadPhotoService{

	/* (non-Javadoc)
	 * @see UploadPhotoService#listUploadPhoto(TableTagBean)
	 */
	public List<JSONObject> listUploadPhoto(TableTagBean ttb)
			throws ServiceException {
		return BizUploadPhotoQueryHelper.listUploadPhoto(ttb);
	}

	/* (non-Javadoc)
	 * @see UploadPhotoService#delete(java.lang.Long)
	 */
	public void delete(Long photoOid) throws ServiceException {
		
		PtPhoto ptPhoto = DaoUtil.get(PtPhoto.class, photoOid);
		
		if (ptPhoto != null) {
			UploadStatus uploadStatus = BizUploadPhotoQueryHelper.getUploadStatus(ptPhoto.getPhotoCode());
			if(uploadStatus != null) {
				uploadStatus.delete();
				new File(uploadStatus.getFilePathRemote()).delete();
			}
			ptPhoto.delete();
		}
	}

	/* (non-Javadoc)
	 * @see UploadPhotoService#get(java.lang.Long)
	 */
	public BizUploadPhotoDTO get(Long photoOid) throws ServiceException {
		BizUploadPhotoDTO bizUploadPhotoDTO=new BizUploadPhotoDTO();
		PtPhoto ptPhoto = DaoUtil.get(PtPhoto.class, photoOid);
		if (ptPhoto != null) {
			UploadStatus uploadStatus = BizUploadPhotoQueryHelper.getUploadStatus(ptPhoto.getPhotoCode());
			if(uploadStatus != null) {
				BeanHelper.copyProperties(uploadStatus, bizUploadPhotoDTO);	}
			BeanHelper.copyProperties(ptPhoto, bizUploadPhotoDTO);
		}
        BeanHelper.copyProperties(ptPhoto, bizUploadPhotoDTO);
        
		return bizUploadPhotoDTO;
	}

}

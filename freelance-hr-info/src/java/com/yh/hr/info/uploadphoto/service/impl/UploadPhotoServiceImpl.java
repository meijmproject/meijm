package com.yh.hr.info.uploadphoto.service.impl;

import java.io.File;
import java.util.List;

import com.yh.hr.info.uploadphoto.dto.UploadPhotoDTO;
import com.yh.hr.info.uploadphoto.service.UploadPhotoService;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.bo.UploadStatus;
import com.yh.hr.info.uploadphoto.queryhelper.UploadPhotoQueryHelper;
import com.yh.hr.res.pb.bo.PbPhoto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 上传文件serviceImpl
 * @author chenjl
 * @date 2017-03-25
 * @version 1.0
 */
public class UploadPhotoServiceImpl implements UploadPhotoService {

	/* (non-Javadoc)
	 * @see UploadPhotoService#listUploadPhoto(TableTagBean)
	 */
	public List<JSONObject> listUploadPhoto(TableTagBean ttb)
			throws ServiceException {
		return UploadPhotoQueryHelper.listUploadPhoto(ttb);
	}

	/* (non-Javadoc)
	 * @see UploadPhotoService#delete(java.lang.Long)
	 */
	public void delete(Long photoOid) throws ServiceException {
		
		PbPhoto pbPhoto = DaoUtil.get(PbPhoto.class, photoOid);
		
		if (pbPhoto != null) {
			UploadStatus uploadStatus = UploadPhotoQueryHelper.getUploadStatus(pbPhoto.getPhotoCode());
			if(uploadStatus != null) {
				uploadStatus.delete();
				new File(uploadStatus.getFilePathRemote()).delete();
			}
			pbPhoto.delete();
		}
	}

	/* (non-Javadoc)
	 * @see UploadPhotoService#get(java.lang.Long)
	 */
	public UploadPhotoDTO get(Long photoOid) throws ServiceException {
		UploadPhotoDTO uploadPhotoDTO=new UploadPhotoDTO();
		PbPhoto pbPhoto = DaoUtil.get(PbPhoto.class, photoOid);
		if (pbPhoto != null) {
			UploadStatus uploadStatus = UploadPhotoQueryHelper.getUploadStatus(pbPhoto.getPhotoCode());
			if(uploadStatus != null) {
				BeanHelper.copyProperties(uploadStatus, uploadPhotoDTO);
			}
			BeanHelper.copyProperties(pbPhoto, uploadPhotoDTO);
		}
        BeanHelper.copyProperties(pbPhoto, uploadPhotoDTO);
        
		return uploadPhotoDTO;
	}

}

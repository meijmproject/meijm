package com.yh.hr.res.pb.service.impl;

import java.util.List;

import com.yh.hr.res.pb.bo.PbPhoto;
import com.yh.hr.res.pb.dto.PbPhotoDTO;
import com.yh.hr.res.pb.queryhelper.PbPersonPhotoQueryHelper;
import com.yh.hr.res.pb.service.PbPhotoService;
import org.apache.struts.upload.FormFile;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 *@description 照片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public class PbPhotoServiceImpl implements PbPhotoService {

    /**
     * 获取照片信息
     *
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public List<PbPhotoDTO> listPersonPhoto(Long personOid) throws ServiceException {
        try {
            return PbPersonPhotoQueryHelper.listPbPersonPhoto(personOid);
        } catch (Exception e) {
            throw new ServiceException(null,"获取影像信息失败!");
        }
    }

    /**
     * 更新或插入照片
     * @param Photo
     * @param personOid
     */
    public void insertOrUpdatePhoto(FormFile Photo,Long personOid) throws ServiceException
    {
    	
    }

	public Long create(PbPhotoDTO pbPhotoDTO) throws ServiceException {
		PbPhoto pbPhoto =BeanHelper.copyProperties(pbPhotoDTO,PbPhoto.class);
		pbPhoto.setCreateBy(UserContext.getLoginUserID());
		pbPhoto.setCreateName(UserContext.getLoginUserName());
		pbPhoto.setCreateDate(DateUtil.now());
		pbPhoto.save();
		return pbPhoto.getPhotoOid();
	}
}

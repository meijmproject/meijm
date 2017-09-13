package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPhoto;
import com.yh.hr.res.pt.dto.PtPhotoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonPhotoQueryHelper;
import com.yh.hr.res.pt.service.PtPhotoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.upload.FormFile;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 *@description 照片信息
 *@author      wangx
 *@date     2017-06-07
 *@version     1.0
 */
public class PtPhotoServiceImpl implements PtPhotoService {

    /**
     * 获取照片信息
     *
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPhotoDTO> listPersonPhoto(Long bizPersonOid) throws ServiceException {
        try {
            return PtPersonPhotoQueryHelper.listPtPersonPhoto(bizPersonOid);
        } catch (Exception e) {
            throw new ServiceException(null,"获取影像信息失败!");
        }
    }

    /**
     * 更新或插入照片
     * @param Photo
     * @param bizPersonOid
     */
    public void insertOrUpdatePhoto(FormFile Photo,Long bizPersonOid) throws ServiceException
    {
    	
    }

    /**照片信息创建
     * @param ptPhotoDTO
     * @return
     * @throws ServiceException
     */
	public Long create(PtPhotoDTO ptPhotoDTO) throws ServiceException {
		PtPhoto ptPhoto =BeanHelper.copyProperties(ptPhotoDTO,PtPhoto.class);
		ptPhoto.setCreateBy(UserContext.getLoginUserID());
		ptPhoto.setCreateName(UserContext.getLoginUserName());
		ptPhoto.setCreateDate(DateUtil.now());
		ptPhoto.save();
		return ptPhoto.getPhotoOid();
	}
	
	/**
     * 删除照片信息
     * @param photoOid
     * @throws ServiceException
     */
    public void delete(Long photoOid) throws ServiceException {
    	PtPhoto ptPhoto = DaoUtil.get(PtPhoto.class, photoOid);
    	if(ptPhoto!=null) {
    		ptPhoto.delete();
    	}
    }
    
    /**
     * 通过业务人员OID删除照片信息
     * @param bizPersonOid
     * @throws ServiceException
     */
    public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
    	List<PtPhotoDTO> list = PtPersonPhotoQueryHelper.listPtPersonPhoto(bizPersonOid);
    	if(CollectionUtils.isNotEmpty(list)) {
    		for(PtPhotoDTO dto:list) {
    			PtPhoto ptPhoto = DaoUtil.get(PtPhoto.class, dto.getPhotoOid());
    	    	if(ptPhoto!=null) {
    	    		ptPhoto.delete();
    	    	}
    		}
    	}
    }
}

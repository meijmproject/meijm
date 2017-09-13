package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbImageDTO;
import com.yh.platform.core.exception.ServiceException;
import org.apache.struts.upload.FormFile;

/**
 *@description 图片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public interface PbImageService {
    /**
     * 更新或插入照片
     * @param image
     * @param personOid
     */
    public void insertOrUpdateImage(FormFile image,Long personOid) throws ServiceException;

    /**
     * 获取照片信息
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public PbImageDTO getPbImage(Long pesonOid) throws ServiceException;

    
    /**
     * 照片信息创建
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public void create(PbImageDTO pbImageDTO) throws ServiceException;
    
}

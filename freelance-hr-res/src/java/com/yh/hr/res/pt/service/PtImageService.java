package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtImageDTO;
import com.yh.platform.core.exception.ServiceException;
import org.apache.struts.upload.FormFile;

/**
 *@description 图片信息
 *@author      xiongyx
 *@created     2016-10-09
 *@version     1.0
 */
public interface PtImageService {
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
    public PtImageDTO getPtImage(Long pesonOid) throws ServiceException;
    /**
     * 照片信息转入业务库
     * @param PtImageDTO
     * @return
     * @throws ServiceException
     */
    
    public void createPtImageInfo(PtImageDTO ptImageDTO) throws ServiceException;
}

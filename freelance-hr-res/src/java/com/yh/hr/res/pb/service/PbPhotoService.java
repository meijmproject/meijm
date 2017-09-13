package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbPhotoDTO;
import com.yh.platform.core.exception.ServiceException;
import org.apache.struts.upload.FormFile;

/**
 *@description 图片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public interface PbPhotoService {
    /**
     * 更新或插入照片
     * @param Photo
     * @param personOid
     */
    public void insertOrUpdatePhoto(FormFile Photo,Long personOid) throws ServiceException;

    /**
     * 获取照片信息
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public List<PbPhotoDTO> listPersonPhoto(Long pesonOid) throws ServiceException;

    
    /**照片信息创建
     * @param pbPhotoDTO
     * @return
     * @throws ServiceException
     */
    public Long create(PbPhotoDTO pbPhotoDTO) throws ServiceException;
    
}

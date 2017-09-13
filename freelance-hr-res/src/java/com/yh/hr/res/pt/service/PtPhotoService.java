package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPhotoDTO;
import org.apache.struts.upload.FormFile;

import com.yh.platform.core.exception.ServiceException;

/**
 *@description 图片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public interface PtPhotoService {
    /**
     * 更新或插入照片
     * @param Photo
     * @param bizPersonOid
     */
    public void insertOrUpdatePhoto(FormFile Photo,Long bizPersonOid) throws ServiceException;

    /**
     * 获取照片信息
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPhotoDTO> listPersonPhoto(Long bizPersonOid) throws ServiceException;

    
    /**照片信息创建
     * @param ptPhotoDTO
     * @return
     * @throws ServiceException
     */
    public Long create(PtPhotoDTO ptPhotoDTO) throws ServiceException;
    
    /**
     * 删除照片信息
     * @param photoOid
     * @throws ServiceException
     */
    public void delete(Long photoOid) throws ServiceException;
    
    /**
     * 通过业务人员OID删除照片信息
     * @param bizPersonOid
     * @throws ServiceException
     */
    public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
}

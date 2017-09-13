package com.yh.hr.res.pb.service;

import java.util.List;
import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 *@description 任职信息
 *@author      weizh
 *@created     2016-08-15
 *@version     1.0
 */
public interface PbPositioningService {
    /**
     * 新增任职信息
     * @param pbPositioningDTO
     * @throws ServiceException
     */
    public Long insertPbPositioning(PbPositioningDTO pbPositioningDTO) throws ServiceException;
    /**
     * 新增任职信息
     * @param pbPositioningDTO
     * @throws ServiceException
     */
    public void updatePbPositioning(PbPositioningDTO pbPositioningDTO) throws ServiceException;
    /**
     * 获取任职信息
     * @param positioningOid
     * @return
     * @throws ServiceException
     */
    public PbPositioningDTO getPbPositioning(Long positioningOid) throws ServiceException;
    
    /**
     * 通过人员ID获取任职信息集合
     * @param positioningOid
     * @return
     * @throws ServiceException
     */
    public List<PbPositioningDTO> listPbPositioningByPersonId(Long personOid) throws ServiceException;
    
    public List<PbPositioningDTO> findPDByPersonOidAndStatus(Long personOid,String dutyStatus) throws ServiceException;
    
    /**
     * 删除任职信息
     * @param positioningOid
     * @return
     * @throws ServiceException
     */
    public void deletePbPositioningInfoById(Long positioningOid) throws ServiceException;
    
    /**
     * 删除任职信息(批量)
     * @param positioningOid
     * @return
     * @throws ServiceException
     */
    public void deletePbPositioningInfoByIds(List<Long> positioningOids) throws ServiceException;
    
    /**
     * 得到最高任职信息
     * @param personOid
     * @param isMPosition
     * @return
     * @throws ServiceException
     */
    public PbPositioningDTO getTopPbPositioningInfoDTO(Long personOid,String isMPosition) throws ServiceException;
}

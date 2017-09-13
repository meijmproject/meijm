package com.yh.hr.res.pt.service;

import java.util.List;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 任职信息Service（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午05:38:15
 */
public interface PtPositioningHistoryService {
    
	/**
     * 新增任职信息
     * @param pbPositioningHistoryDTO
     * @throws ServiceException
     */
    public void insertPtPositioning(PtPositioningHistoryDTO pbPositioningHistoryDTO) throws ServiceException;
    
    /**
     * 更新任职信息
     * @param pbPositioningDTO
     * @throws ServiceException
     */
    public void updatePtPositioning(PtPositioningHistoryDTO ptPositioningHistoryDTO) throws ServiceException;
   
    /**
     * 获取任职信息
     * @param ptPositioningHistoryOid
     * @return
     * @throws ServiceException
     */
    public PtPositioningHistoryDTO getPtPositioning(Long ptPositioningHistoryOid) throws ServiceException;
    
    /**
     * 通过人员ID获取任职信息集合
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPositioningHistoryDTO> listPtPositioningByBizPersonId(Long bizPersonOid) throws ServiceException;
    /**
     * 通过人员ID获取任职信息记录数
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public int  countPtPositioningHistoryByBizPersonOid (Long bizPersonOid) throws ServiceException;
    
    /**
     * 删除任职信息
     * @param ptPositioningHistoryOid
     * @return
     * @throws ServiceException
     */
    public void deletePtPositioningHistoryById(Long ptPositioningHistoryOid) throws ServiceException;
    
    /**
     * 删除任职信息(批量)
     * @param ptPositioningHistoryOids
     * @return
     * @throws ServiceException
     */
    public void deletePtPositioningHistoryByIds(List<Long> ptPositioningHistoryOids) throws ServiceException;
}

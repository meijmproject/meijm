package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPostInfoHistoryDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtPostInfoHistoryService {
	/**
     * 新增任职信息
     * @param pbPositioningHistoryDTO
     * @throws ServiceException
     */
    public void insertPtPostInfo(PtPostInfoHistoryDTO pbPositioningHistoryDTO) throws ServiceException;
    
    /**
     * 更新任职信息
     * @param pbPositioningDTO
     * @throws ServiceException
     */
    public void updatePtPostInfo(PtPostInfoHistoryDTO ptPostInfoHistoryDTO) throws ServiceException;
   
    /**
     * 获取任职信息
     * @param ptPostInfoHistoryOid
     * @return
     * @throws ServiceException
     */
    public PtPostInfoHistoryDTO getPtPostInfo(Long ptPostInfoHistoryOid) throws ServiceException;
    
    /**
     * 通过人员ID获取任职信息集合
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPostInfoHistoryDTO> listPtPostInfoByBizPersonId(Long bizPersonOid) throws ServiceException;
    /**
     * 通过人员ID获取任职信息记录数
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public int  countPtPostInfoHistoryByBizPersonOid (Long bizPersonOid) throws ServiceException;
    
    /**
     * 删除任职信息
     * @param ptPostInfoHistoryOid
     * @return
     * @throws ServiceException
     */
    public void deletePtPostInfoHistoryById(Long ptPostInfoHistoryOid) throws ServiceException;
    
    /**
     * 删除任职信息(批量)
     * @param ptPostInfoHistoryOids
     * @return
     * @throws ServiceException
     */
    public void deletePtPostInfoHistoryByIds(List<Long> ptPostInfoHistoryOids) throws ServiceException;
    /**
	 * 根据ptPositioningHistoryOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * @param ptPositioningHistoryOid
	 * @throws ServiceException
	 */
	public PtPostInfoHistoryDTO getByPtPositioningHistoryOid(Long ptPositioningHistoryOid) throws ServiceException;
	/**
	 * @param ptPositioningHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPostInfoHistoryDTO> getByBizPersonOid(Long ptPositioningHistoryOid) throws ServiceException;
}

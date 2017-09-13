package com.yh.hr.res.pt.service;

import java.util.List;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 任职信息Service（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午05:38:15
 */
public interface PtPositioningService {
    
	/**
     * 新增任职信息
     * @param pbPositioningInfoDTO
     * @throws ServiceException
     */
    public void insertPtPositioning(PtPositioningInfoDTO pbPositioningInfoDTO) throws ServiceException;
    
    /**
     * 更新任职信息
     * @param pbPositioningDTO
     * @throws ServiceException
     */
    public void updatePtPositioning(PtPositioningInfoDTO ptPositioningInfoDTO) throws ServiceException;
   
    /**
     * 获取任职信息
     * @param ptPositioningInfoOid
     * @return
     * @throws ServiceException
     */
    public PtPositioningInfoDTO getPtPositioning(Long ptPositioningInfoOid) throws ServiceException;
    
    /**
     * 通过人员ID获取任职信息集合
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPositioningInfoDTO> listPtPositioningByBizPersonId(Long bizPersonOid) throws ServiceException;
    /**
     * 通过人员ID获取任职信息记录数
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public int  countPtPositioningInfoByBizPersonOid (Long bizPersonOid) throws ServiceException;
    
    /**
     * 删除任职信息
     * @param ptPositioningInfoOid
     * @return
     * @throws ServiceException
     */
    public void deletePtPositioningInfoById(Long ptPositioningInfoOid) throws ServiceException;
    
    /**
     * 删除任职信息(批量)
     * @param ptPositioningInfoOids
     * @return
     * @throws ServiceException
     */
    public void deletePtPositioningInfoByIds(List<Long> ptPositioningInfoOids) throws ServiceException;

	public PtPositioningInfoDTO getByBizPersonOid(Long bizPersonOid);

	public PtPositioningInfoDTO getTopPtPositioningInfoDTO(Long bizPersonOid,String isMPosition) throws ServiceException;
}

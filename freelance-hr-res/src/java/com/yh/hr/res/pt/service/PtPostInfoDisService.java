package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPostInfoDisDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 免职信息服务层
 * @author chenjl
 * @createDate 2016-11-10
 */
public interface PtPostInfoDisService {

	/**
     * 获取任职信息
     * @param ptPostInfoDisOid
     * @return
     * @throws ServiceException
     */
    public PtPostInfoDisDTO getPtPostInfoDis(Long ptPostInfoDisOid) throws ServiceException;
    
    /**
     * 通过人员ID获取任职信息集合
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPostInfoDisDTO> listPtPostInfoDisByBizPersonId(Long bizPersonOid) throws ServiceException;
    /**
     * 通过人员ID获取任职信息记录数
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public int  countPtPostInfoDisByBizPersonOid (Long bizPersonOid) throws ServiceException;
    
    /**
     * 删除任职信息
     * @param ptPostInfoDisOid
     * @return
     * @throws ServiceException
     */
    public void deletePtPostInfoDis(Long ptPostInfoDisOid) throws ServiceException;
    /**
     * 新增任职信息
     * @param ptPostInfoDisOid
     * @return
     * @throws ServiceException
     */
	public void insertPtPostInfoDis(PtPostInfoDisDTO copyProperties) throws ServiceException;
	/**
     * 批量删除任职信息
     * @param ptPostInfoDisOids
     * @return
     * @throws ServiceException
     */
	public void deletePtPostInfoDisByIds(List<Long> ptPostInfoDisOids) throws ServiceException ;
}
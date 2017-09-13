package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtReviewInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 考核情况信息Service
 * @author xiongyx
 *
 * 
 */
public interface PtReviewInfoService {

	/**
	 * 新增考核休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void createPtReviewInfo(PtReviewInfoDTO serdto)throws ServiceException;

	/**
	 * 根据ID查询考核情况详细信息
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtReviewInfoDTO getPtReviewInfoByOid(Long reviewOid) throws ServiceException;

	/**
	 * 更新考核休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtReviewInfo(PtReviewInfoDTO serdto) throws ServiceException;

	/**
	 * 根据ID删除考核情况详细信息
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtReviewInfoByOid(Long reviewOid) throws ServiceException;
	
	/**
	 * 考核情况详细信息列表
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtReviewInfoDTO> listPtReviewInfo(Long personOid) throws ServiceException;

	/**
	 * 考核情况详细信息记录数
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */	
     public int countPtReviewInfoByBizPersonOid(Long personOid) throws ServiceException;
}

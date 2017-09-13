package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtOtherWageChangeInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PtOtherWageChangeInfo service
 * @author wuxq
 * @created 2016-12-08
 * @version 1.0
 */
public interface PtOtherWageChangeInfoService
{
	/**
	 * insert PtOtherWageChangeInfo obj
	 * @param PtOtherWageChangeInfoDTO
	 * @throws ServiceException
	 */
	public void createPtOtherWageChangeInfo(PtOtherWageChangeInfoDTO ptOtherWageChangeInfoDTO) throws ServiceException;
	
	/**
	 * get PtOtherWageChangeInfo obj
	 * @param ptOtherWageChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtOtherWageChangeInfoDTO findPtOtherWageChangeInfoDTOById(Long ptOtherWageChangeOid) throws ServiceException;
	
	/**
	 * update PtOtherWageChangeInfo obj
	 * @param PtOtherWageChangeInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtOtherWageChangeInfo(PtOtherWageChangeInfoDTO ptOtherWageChangeInfoDTO) throws ServiceException;
	
	/**
	 * delete PtOtherWageChangeInfo obj
	 * @param ptOtherWageChangeOid
	 * @throws ServiceException
	 */
	public void deletePtOtherWageChangeInfo(Long ptOtherWageChangeOid) throws ServiceException;
	
	/**
	 * list PtOtherWageChangeInfo obj
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public PtOtherWageChangeInfoDTO listPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * count PtOtherWageChangeInfo obj
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
}

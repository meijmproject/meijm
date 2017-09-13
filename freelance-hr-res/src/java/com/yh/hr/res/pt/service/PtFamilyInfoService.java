package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PtFamilyInfo service
 * @author xiongyx
 * @created 2016-10-10
 * @version 1.0
 */
public interface PtFamilyInfoService
{
	/**
	 * insert PtFamilyInfo obj
	 * @param PtFamilyInfoDto
	 * @throws ServiceException
	 */
	public void createPtFamilyInfo(PtFamilyInfoDTO PtFamilyInfoDto) throws ServiceException;
	
	/**
	 * get PtFamilyInfo obj
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public PtFamilyInfoDTO findPtFamilyInfoById(Long familyOid) throws ServiceException;
	
	/**
	 * update PtFamilyInfo obj
	 * @param PtFamilyInfoDto
	 * @throws ServiceException
	 */
	public void updatePtFamilyInfo(PtFamilyInfoDTO PtFamilyInfoDto) throws ServiceException;
	
	/**
	 * delete PtFamilyInfo obj
	 * @param familyOid
	 * @throws ServiceException
	 */
	public void deletePtFamilyInfo(Long familyOid) throws ServiceException;
	
	/**
	 * list PtFamilyInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtFamilyInfoDTO> listPtFamilyInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count PtFamilyInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtFamilyInfoByPersonOid(Long personOid) throws ServiceException;
}

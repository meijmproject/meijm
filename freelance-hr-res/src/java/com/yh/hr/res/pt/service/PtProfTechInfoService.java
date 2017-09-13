package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for ProfTechInfo service
 * @author lihj
 * @created 2016-10-26
 * @version 1.0
 */
public interface PtProfTechInfoService
{
	/**
	 * insert ProfTechInfo obj
	 * @param ProfTechInfoDto
	 * @throws ServiceException
	 */
	public void createPtProfTechInfo(PtProfTechInfoDTO profTechInfoDto) throws ServiceException;
	
	/**
	 * get ProfTechInfo obj
	 * @param profTechInOid
	 * @return
	 * @throws ServiceException
	 */
	public PtProfTechInfoDTO findPtProfTechInfoById(Long profTechInOid) throws ServiceException;
	
	/**
	 * update ProfTechInfo obj
	 * @param ProfTechInfoDto
	 * @throws ServiceException
	 */
	public void updatePtProfTechInfo(PtProfTechInfoDTO profTechInfoDto) throws ServiceException;
	
	/**
	 * delete ProfTechInfo obj
	 * @param profTechInOid
	 * @throws ServiceException
	 */
	public void deletePtProfTechInfo(Long profTechInOid) throws ServiceException;
	
	/**
	 * list ProfTechInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtProfTechInfoDTO> listPtProfTechInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count ProfTechInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtProfTechInfoByPersonOid(Long personOid) throws ServiceException;
}

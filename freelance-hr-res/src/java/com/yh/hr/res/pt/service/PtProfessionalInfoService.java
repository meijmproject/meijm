package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtProfessionalInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PtProfessionalInfo service
 * @author lihj
 * @created 2016-10-26
 * @version 1.0
 */
public interface PtProfessionalInfoService
{
	/**
	 * insert PtProfessionalInfo obj
	 * @param ProfessionalInfoDto
	 * @throws ServiceException
	 */
	public void createPtProfessionalInfo(PtProfessionalInfoDTO professionalInfoDto) throws ServiceException;
	
	/**
	 * get PtProfessionalInfo obj
	 * @param professionalOid
	 * @return
	 * @throws ServiceException
	 */
	public PtProfessionalInfoDTO findPtProfessionalInfoById(Long professionalOid) throws ServiceException;
	
	/**
	 * update PtProfessionalInfo obj
	 * @param ProfessionalInfoDto
	 * @throws ServiceException
	 */
	public void updatePtProfessionalInfo(PtProfessionalInfoDTO professionalInfoDto) throws ServiceException;
	
	/**
	 * delete PtProfessionalInfo obj
	 * @param professionalOid
	 * @throws ServiceException
	 */
	public void deletePtProfessionalInfo(Long professionalOid) throws ServiceException;
	
	/**
	 * list PtProfessionalInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtProfessionalInfoDTO> listPtProfessionalInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count PtProfessionalInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtProfessionalInfoByPersonOid(Long personOid) throws ServiceException;
}

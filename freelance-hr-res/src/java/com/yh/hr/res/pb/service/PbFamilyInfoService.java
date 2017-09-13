package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PbFamilyInfo service
 * @author chenp
 * @created 2017-02-16
 * @version 1.0
 */
public interface PbFamilyInfoService
{
	/**
	 * insert PbFamilyInfo obj
	 * @param pbFamilyInfoDto
	 * @throws ServiceException
	 */
	public void createPbFamilyInfo(PbFamilyInfoDTO pbFamilyInfoDto) throws ServiceException;
	
	/**
	 * get PbFamilyInfo obj
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public PbFamilyInfoDTO findPbFamilyInfoById(Long familyOid) throws ServiceException;
	
	/**
	 * update PbFamilyInfo obj
	 * @param pbFamilyInfoDto
	 * @throws ServiceException
	 */
	public void updatePbFamilyInfo(PbFamilyInfoDTO pbFamilyInfoDto) throws ServiceException;
	
	/**
	 * delete PbFamilyInfo obj
	 * @param familyOid
	 * @throws ServiceException
	 */
	public void deletePbFamilyInfo(Long familyOid) throws ServiceException;
	
	/**
	 * list PbFamilyInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbFamilyInfoDTO> listPbFamilyInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count PbFamilyInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPbFamilyInfoByPersonOid(Long personOid) throws ServiceException;
}

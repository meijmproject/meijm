package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbEducationInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PbEducationInfo service
 * @author huangyj
 * @created 2016-08-15
 * @version 1.0
 */
public interface PbEducationInfoService
{
	/**
	 * insert PbEducationInfo obj
	 * @param pbEducationInfoDto
	 * @throws ServiceException
	 */
	public void createPbEducationInfo(PbEducationInfoDTO pbEducationInfoDto) throws ServiceException;
	
	/**
	 * get PbEducationInfo obj
	 * @param educationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbEducationInfoDTO findPbEducationInfoById(Long educationOid) throws ServiceException;
	
	/**
	 * update PbEducationInfo obj
	 * @param pbEducationInfoDto
	 * @throws ServiceException
	 */
	public void updatePbEducationInfo(PbEducationInfoDTO pbEducationInfoDto) throws ServiceException;
	
	/**
	 * delete PbEducationInfo obj
	 * @param educationOid
	 * @throws ServiceException
	 */
	public void deletePbEducationInfo(Long educationOid) throws ServiceException;
	
	/**
	 * list PbEducationInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbEducationInfoDTO> listPbEducationInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count PbEducationInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPbEducationInfoByPersonOid(Long personOid) throws ServiceException;
}

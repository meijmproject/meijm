package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员基础信息业务逻辑层
 * @author xiongyx
 * @createDate 2016-10-09
 */
public interface PtPersonService {
	public Long addPersonInfo(PtPersonDTO ptPersonDTO) throws ServiceException;

	public void updatePersonInfo(PtPersonDTO ptPersonDTO) throws ServiceException;

	public void deletePersonInfo(Long bizPersonOid) throws ServiceException;

	/**
	 * 根据主键bizPersonOid获取业务基础信息DTO
	 * 
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPersonDTO getPtPersonDTO(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 根据taskOid查找人员业务基础信息
	 * 
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPersonDTO getByTaskOid(Long bizTaskOid) throws ServiceException;
	
	/**
	 * 根据personOid查找人员业务基础信息
	 * 
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPersonDTO getByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * 证件唯一性检查
	 * @param PtPersonDTO
	 * @throws ServiceException
	 */
	public void checkCanUpdate(PtPersonDTO PtPersonDTO) throws ServiceException;
	/**
	 *工号唯一性检查
	 * @param PtPersonDTO
	 * @throws ServiceException
	 */
	public void checkUniquePersonCode(PtPersonDTO PtPersonDTO) throws ServiceException;

}
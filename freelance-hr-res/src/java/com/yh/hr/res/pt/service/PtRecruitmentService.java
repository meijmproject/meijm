package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtRecruitmentDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 招考信息Service
 * @author xiongyx
 * @created 2016-11-17
 * @version 1.0
 */
public interface PtRecruitmentService {
	
	/**
	 * 新增招考信息
	 * insert PtRecruitmentDTO obj
	 * @param PtRecruitmentDTO
	 * @throws ServiceException
	 */
	public void create(PtRecruitmentDTO PtRecruitmentDTO) throws ServiceException;
	
	/**
	 * 查询招考信息
	 * get PtRecruitment obj
	 * @param punishmentOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRecruitmentDTO find(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 更新招考信息
	 * update PtRecruitmentDTO obj
	 * @param PtRecruitmentDTO
	 * @throws ServiceException
	 */
	public void update(PtRecruitmentDTO PtRecruitmentDTO) throws ServiceException;
	
	/**
	 * 删除招考信息
	 * delete PtRecruitment obj
	 * @param punishmentOid
	 * @throws ServiceException
	 */
	public void delete(Long bizPersonOid) throws ServiceException;
}

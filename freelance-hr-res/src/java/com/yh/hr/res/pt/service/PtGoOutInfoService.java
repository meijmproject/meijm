package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtGoOutInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtGoOutInfoService {
	
	/**
	 * 新增人员外出的业务信息
	 * @param ptGoOutInfoDTO
	 * @throws ServiceException
	 */
	public Long create(PtGoOutInfoDTO ptGoOutInfoDTO) throws ServiceException;
	
	/**
	 * 修改人员外出的业务信息
	 * @param ptGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtGoOutInfoDTO ptGoOutInfoDTO) throws ServiceException;

	/**
	 * 删除人员外出的业务信息
	 * @param goOutOid
	 * @throws ServiceException
	 */
	public void delete(Long goOutOid) throws ServiceException;
	
	/**
	 * 查询人员对应的外出信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtGoOutInfoDTO> list(Long personOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出信息
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutInfoDTO get(Long goOutOid) throws ServiceException;
}

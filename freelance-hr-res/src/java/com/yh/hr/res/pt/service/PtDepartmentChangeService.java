package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtDepartmentChangeDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员科室变动业务信息service接口
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public interface PtDepartmentChangeService {

	/**
	 * 创建人员科室变动业务信息
	 * @param ptDepartmentChangeDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtDepartmentChangeDTO ptDepartmentChangeDTO) throws ServiceException;
	
	/**
	 * 修改人员科室变动业务信息
	 * @param ptDepartmentChangeDTO
	 * @throws ServiceException
	 */
	public void update(PtDepartmentChangeDTO ptDepartmentChangeDTO) throws ServiceException;
	
	/**
	 * 删除人员科室变动业务信息
	 * @param ptDepartmentChangeOid
	 * @throws ServiceException
	 */
	public void delete(Long ptDepartmentChangeOid) throws ServiceException;
	
	/**
	 * 获取人员科室变动业务信息
	 * @param ptDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDepartmentChangeDTO get(Long ptDepartmentChangeOid) throws ServiceException;
	
	/**
	 * 通过业务人员OID查找人员所有科室变动信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtDepartmentChangeDTO> findDepartmentChangeDTOListByBizPersonOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 通过基础OID查找人员科室变动业务信息
	 * @param pbDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDepartmentChangeDTO findDepartmentChangeDTOByPbOid(Long pbDepartmentChangeOid) throws ServiceException;

	/**
	 * 通过bizPersonOid删除科室变动业务信息
	 * @param bizPersonOid
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
}

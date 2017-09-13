package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbDepartmentChangeDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员科室变动基础信息service接口
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public interface PbDepartmentChangeService {

	/**
	 * 创建人员科室变动基础信息
	 * @param pbDepartmentChangeDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbDepartmentChangeDTO pbDepartmentChangeDTO) throws ServiceException;
	
	/**
	 * 修改人员科室变动基础信息
	 * @param pbDepartmentChangeDTO
	 * @throws ServiceException
	 */
	public void update(PbDepartmentChangeDTO pbDepartmentChangeDTO) throws ServiceException;
	
	/**
	 * 删除人员科室变动基础信息
	 * @param pbDepartmentChangeOid
	 * @throws ServiceException
	 */
	public void delete(Long pbDepartmentChangeOid) throws ServiceException;
	
	/**
	 * 获取人员科室变动基础信息
	 * @param pbDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PbDepartmentChangeDTO get(Long pbDepartmentChangeOid) throws ServiceException;
	
	/**
	 * 通过基础人员OID查找人员所有科室变动基础信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServiceException
	 */
	public List<PbDepartmentChangeDTO> findDepartmentChangeDTOListByPersonOidAndDate(Long personOid, Date startDate, Date endDate) throws ServiceException;
}

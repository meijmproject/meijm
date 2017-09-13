package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtProlongMaternityLeaveDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 延长产假业务信息service接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public interface PtProlongMaternityLeaveService {

	/**
	 * 创建延长产假业务信息
	 * @param ptProlongMaternityLeaveDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtProlongMaternityLeaveDTO ptProlongMaternityLeaveDTO) throws ServiceException;
	
	/**
	 * 修改延长产假业务信息
	 * @param ptProlongMaternityLeaveDTO
	 * @throws ServiceException
	 */
	public void update(PtProlongMaternityLeaveDTO ptProlongMaternityLeaveDTO) throws ServiceException;
	
	/**
	 * 通过主键OID删除延长产假业务信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException;
	
	/**
	 * 通过主键OID获取延长产假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtProlongMaternityLeaveDTO get(Long vacationOid) throws ServiceException;
}

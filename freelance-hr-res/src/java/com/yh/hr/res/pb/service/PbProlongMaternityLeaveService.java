package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbProlongMaternityLeaveDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 延长产假基础信息service接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public interface PbProlongMaternityLeaveService {

	/**
	 * 创建延长产假基础信息
	 * @param pbProlongMaternityLeaveDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbProlongMaternityLeaveDTO pbProlongMaternityLeaveDTO) throws ServiceException;
	
	/**
	 * 修改延长产假基础信息
	 * @param pbProlongMaternityLeaveDTO
	 * @throws ServiceException
	 */
	public void update(PbProlongMaternityLeaveDTO pbProlongMaternityLeaveDTO) throws ServiceException;
	
	/**
	 * 通过主键OID删除延长产假基础信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException;
	
	/**
	 * 通过主键OID获取延长产假基础信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbProlongMaternityLeaveDTO get(Long vacationOid) throws ServiceException;
}

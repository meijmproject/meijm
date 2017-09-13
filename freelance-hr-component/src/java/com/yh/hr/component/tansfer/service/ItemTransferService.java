package com.yh.hr.component.tansfer.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 业务事项转库（模板）接口（ 前缀 b:基础信息pb, t:业务信息pt）
 * @author zhangqp
 * @version 1.0, 16/10/12
 */
public interface ItemTransferService {
	
	/**
	 * 转库
	 * 
	 * @throws ServiceException
	 */
	public void transfer(Long taskOid) throws ServiceException;
}

package com.yh.hr.component.tansfer.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 信息集转库接口（ 前缀 b:基础信息pb, t:业务信息pt）
 * @author zhangqp
 * @version 1.0, 16/10/12
 */
public interface InfoTransferService {
	
	/**
	 * 转库
	 * @param sourceBizOid
	 * @param targetBizOid
	 * @throws ServiceException
	 */
	public void transfer(Long sourceBizOid, Long targetBizOid) throws ServiceException;
}

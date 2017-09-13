package com.yh.hr.component.im.check.service.relation;

import com.yh.platform.core.exception.ServiceException;

/**
 * 关联性检查接口
 * @author wangx
 * @date 2017-07-19
 * @version 1.0
 */
public interface RelationCheckService {

	/**
	 * 检查
	 * @param importBatchOid 当前导入批次号
	 * @throws ServiceException
	 */
	public void check(Long importBatchOid) throws ServiceException;
}

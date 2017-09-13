package com.yh.hr.component.im.tablehandle.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 清空表数据接口
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public interface TableDataEmptyService {

	/**
	 * 清空表数据
	 * @param tableCode
	 * @throws ServiceException
	 */
	public void emptyTable(String tableCode) throws ServiceException;
}

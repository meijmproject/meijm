package com.yh.hr.component.im.tablehandle.service;

import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 组装SQL插入数据接口
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public interface TableDataInsertService {

	/**
	 * 插入数据
	 * @param tableDefDTO
	 * @throws ServiceException
	 */
	public void insertData(TableDefDTO tableDefDTO) throws ServiceException;
}

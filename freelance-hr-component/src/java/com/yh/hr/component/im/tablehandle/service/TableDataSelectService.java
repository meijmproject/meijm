package com.yh.hr.component.im.tablehandle.service;

import java.util.List;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 组装SQL查询数据接口
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public interface TableDataSelectService {

	/**
	 * 查询数据
	 * @param tableDefDTO
	 * @return 
	 * @throws ServiceException
	 */
	public List<List<ColumnDefDTO>> selectData(TableDefDTO tableDefDTO) throws ServiceException;
}

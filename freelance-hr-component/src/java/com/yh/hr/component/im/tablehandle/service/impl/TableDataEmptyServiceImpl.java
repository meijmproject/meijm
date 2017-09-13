package com.yh.hr.component.im.tablehandle.service.impl;

import com.yh.hr.component.im.tablehandle.queryHelper.TableDefQueryHelper;
import com.yh.hr.component.im.tablehandle.service.TableDataEmptyService;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 清空表数据实现类
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class TableDataEmptyServiceImpl implements TableDataEmptyService {

	/**
	 * 清空表数据
	 * @param tableCode
	 * @throws ServiceException
	 */
	public void emptyTable(String tableCode) throws ServiceException {
		//检查
//		checkData(tableCode);
		//执行删除sql
		SqlDaoUtil.executeSqlUpdate("DELETE FROM "+tableCode);
	}
	
	/**
	 * 检查
	 * @param tableCode
	 * @throws ServiceException
	 */
	private void checkData(String tableCode) throws ServiceException {
		if(tableCode==null) {
			throw new ServiceException(null,"表名没有定义！");
		}
		if(!TableDefQueryHelper.checkTableExist(tableCode)) {
			throw new ServiceException(null,"表"+tableCode+"不存在！");
		}
	}
}

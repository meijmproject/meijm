package com.yh.hr.component.im.tablehandle.queryHelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class TableDefQueryHelper {

	/**
	 * 检查字段是否指定表的字段
	 * @param tableCode
	 * @param columnCode
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkColumnInTable(String tableCode, String columnCode) throws ServiceException {
		String sql = "SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = '"+columnCode+"' AND TABLE_NAME = '"+tableCode+"'";
		List<Object[]> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查指定表名的表是否存在
	 * @param tableCode
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkTableExist(String tableCode) throws ServiceException {
		String sql = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '"+tableCode+"'";
		List<Object[]> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}
}

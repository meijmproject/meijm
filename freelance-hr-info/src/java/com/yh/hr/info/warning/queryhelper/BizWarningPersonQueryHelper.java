package com.yh.hr.info.warning.queryhelper;

import org.springframework.dao.DataAccessException;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;


public class BizWarningPersonQueryHelper
{
	/**
	 * 删除所有预警人员
	 * @throws DataAccessException
	 */
	public static void deleteAll() throws DataAccessFailureException
	{
		DaoUtil.executeUpdateWithSql("delete from biz_warning_person");
	}
}

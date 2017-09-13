package com.yh.hr.orghc.ut.queryhelper;

import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 单位信息管理查询工具类
 * @author zhengdr
 *
 * 时间:2016-12-20下午05:39:58
 */
public class BizUtUnitQueryHelper
{
	
	/**
	 * 根据utUnitOid删除单位信息
	 * 
	 * @param utUnitOid
	 * @throws ServiceException
	 */
	public static void deleteByUtUnitOid(Long utUnitOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtUnit buu where buu.utUnitOid = " + utUnitOid);
	}
	
	/**
	 * 通过taskOid查找单位信息
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public static BizUtUnit getByTaskOid(Long bizTaskOid) throws ServiceException {
		return DaoUtil.uniqueResult("from BizUtUnit buu where buu.taskOid = ? ", bizTaskOid);
	}
}

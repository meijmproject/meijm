package com.yh.hr.res.bt.queryhelper;

import java.util.List;

import com.yh.hr.res.bt.bo.BtLog;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

/**
 * 业务日志查询
 * 
 * @author zhangqp
 * @version 1.0, 16/10/19
 */
public class BtLogQueryHelper {

	/**
	 * 删除业务日志信息
	 * 
	 * @param taskOid
	 * @throws DataAccessFailureException
	 */
	public static void deleteByTaskOid(Long taskOid)
			throws DataAccessFailureException {

		DaoUtil.executeUpdate(new StringBuilder()
				.append("delete BtLog bl where bl.taskOid = ").append(taskOid)
				.toString());
	}

	public static List<BtLog> findAuditWorkProcess(Long taskOid)
			throws ServiceException {
		return DaoUtil.find(" from BtLog bl where bl.auditStatusCode is not null and bl.taskOid ="+taskOid+" order by bl.taskLogOid ");
	}
	public static List<BtLog> findBizWorkProcess(Long taskOid) throws ServiceException {
		return DaoUtil.find(" from BtLog bl where bl.bizStatusCode is not null and bl.taskOid ="+taskOid+" order by bl.taskLogOid ");
	}
}

package com.yh.hr.res.im.queryhelper;


import java.util.List;

import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImDataUnusualLogQueryHelper {

	/**
	 * 获取该导入批次的所有数据异常日志信息
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImDataUnusualLogDTO> findImDataUnusualLogDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		String hql = "from ImDataUnusualLog dul where dul.importBatchOid = ?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid), ImDataUnusualLogDTO.class);
	}
	
	/**
	 * 获取该导入批次的某个检查类型的数据异常日志信息
	 * @param importBatchOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public static ImDataUnusualLogDTO findImDataUnusualLogDTOByBatchOidAndType(Long importBatchOid, String checkType) throws ServiceException {
		String hql = "from ImDataUnusualLog dul where dul.importBatchOid = ? and dul.checkType = ? and dul.checkNopassedAmount>0";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql, importBatchOid, checkType), ImDataUnusualLogDTO.class);
	}
	
	/**
	 * 获取该导入批次的指定检查状态的数据异常日志信息
	 * @param importBatchOid
	 * @param checkStatus
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImDataUnusualLogDTO> findImDataUnusualLogDTOByBatchOidAndStatus(Long importBatchOid, String checkStatus) throws ServiceException {
		String hql = "from ImDataUnusualLog dul where dul.importBatchOid = ? and dul.checkStatus = ? and dul.checkNopassedAmount>0";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid, checkStatus), ImDataUnusualLogDTO.class);
	}
	
	/**
	 * 检查当前批次是否还有检查未通过的异常日志
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkNopassedLogs(Long importBatchOid) throws ServiceException {
		String hql = "select 1 from ImDataUnusualLog dul where dul.importBatchOid = ? and dul.checkStatus='0' and dul.checkNopassedAmount>0";
		List<Object> list = DaoUtil.find(hql, importBatchOid);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}
}

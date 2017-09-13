package com.yh.hr.component.im.loghandle.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 字典映射日志批量更新服务接口
 * @author wangx
 * @date 2017-07-13
 * @version 1.0
 */
public interface DicMappingLogHandleService {

	/**
	 * 字典映射人员检查异常明细处理
	 * @param importBatchOid 当期导入批次OID
	 * @param databaseColumnCode 数据库字段代码
	 * @param importCollValue 该采集项导入值
	 * @throws ServiceException
	 */
	public void handlePersonLog(Long importBatchOid, String databaseColumnCode, String importCollValue) throws ServiceException;
}

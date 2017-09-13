package com.yh.hr.component.im.check.service.dic;

import com.yh.platform.core.exception.ServiceException;

/**
 * 字典检查接口
 * @author wangx
 * @date 2017-07-19
 * @version 1.0
 */
public interface DictionaryCheckService {

	/**
	 * 检查
	 * @param importBatchOid 当前导入批次号
	 * @param databaseColumnCode 数据库字段代码
	 * @param templateCollName 模板采集项名称
	 * @throws ServiceException
	 */
	public void check(Long importBatchOid, String databaseColumnCode, String templateCollName) throws ServiceException;
}

package com.yh.hr.component.im.check.manage;

import com.yh.hr.component.im.check.factory.RelationCheckFactory;
import com.yh.hr.component.im.check.service.relation.RelationCheckService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 关联性检查service管理类
 * @author wangx
 * @date 2017-07-19
 * @version 1.0
 */
public class RelationCheckServiceManage {

	/**
	 * 关联性数据检查
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @throws ServiceException
	 */
	public static void check(Long importBatchOid,String databaseColumnCode) throws ServiceException {
		RelationCheckService relationCheckService = RelationCheckFactory.getRelationCheckService(databaseColumnCode);
		if(relationCheckService==null) {
			throw new ServiceException(null,"该检查类service没有配置！");
		}
		relationCheckService.check(importBatchOid);
	}
}

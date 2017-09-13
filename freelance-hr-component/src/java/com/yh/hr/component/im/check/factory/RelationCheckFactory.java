package com.yh.hr.component.im.check.factory;

import com.yh.hr.component.im.check.service.relation.RelationCheckService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 关联性检查工厂类
 * @author wangx
 * @date 2017-07-19
 * @version	1.0
 */
public class RelationCheckFactory {

	private static final String RELATION_PREFIX = "relationCheck_";
	
	/**
	 * 通过数据库字段代码获取对应的关联性检查实现类
	 * @param databaseColumnCode 数据库字段代码
	 * @return 对应的关联性检查实现类
	 * @throws ServiceException
	 */
	public static RelationCheckService getRelationCheckService(String databaseColumnCode) throws ServiceException {
		if (databaseColumnCode != null) {
			RelationCheckService relationCheckService = (RelationCheckService)SpringBeanUtil.getBean(RELATION_PREFIX+databaseColumnCode);
			return relationCheckService;
		}
		return null;
	}
}

package com.yh.hr.component.im.check.factory;

import com.yh.hr.component.im.check.service.CollItemCheckService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 导入前数据检查工厂类
 * @author wangx
 * @date 2017-07-10
 * @version	1.0
 */
public abstract class ImportCheckFactory {
	private static final String IMPORT_PREFIX = "importCheckType_";
	
	/**
	 * 通过检查类型获取service
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public static CollItemCheckService getCollItemCheckService(String checkType) throws ServiceException {
		if (checkType != null) {
			CollItemCheckService collItemCheckService = (CollItemCheckService)SpringBeanUtil.getBean(IMPORT_PREFIX+checkType);
			
			return collItemCheckService;
		}
		return null;
	}
}

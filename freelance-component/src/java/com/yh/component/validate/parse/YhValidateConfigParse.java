package com.yh.component.validate.parse;

import java.util.List;

import com.yh.component.validate.BaseValidateService;
import com.yh.platform.core.exception.ServiceException;


/**
 *@description 获取检查规则解析器
 *
 *@author            zhangyx
 *@created           2013-8-30
 *@version           1.0
 *
 */
public interface YhValidateConfigParse
{
	/**
	 * @param methodName方法名
	 * @param className类名
	 * @return 检查规则集合
	 * @throws ServiceException
	 */
	public List<BaseValidateService> listValidateService(String methodName, String className) throws ServiceException;
}

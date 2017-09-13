package com.yh.hr.worktop.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.yh.component.validate.BaseValidateService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 推动流程前批量检查（如：批量上报前编制信息的检查）
 * @author	zhangqp
 * @version	1.0,	16/11/01
 */
public class TaskCheckFactory
{
	private final static String BEFORE_TASK		= "beforeTask";			//强制验证信息
	private final static String CHECK_REMIND		= "checkRemind";	//警告信息
	
	/**
	 * 获取检查类
	 * @param taskOids
	 * @param itemCode
	 * @param itemNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public static BaseValidateService getBeforeTaskCheckService(String itemCode, String itemNodeCode) throws ServiceException
	{
		String beanId = BEFORE_TASK + itemCode + itemNodeCode;
		BaseValidateService validateService;
		try {
			validateService = (BaseValidateService) SpringBeanUtil.getBean(beanId);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
		return (BaseValidateService) validateService;
	}
	
	/**
	 * 获取检查类
	 * @param taskOids
	 * @param itemCode
	 * @param itemNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public static BaseValidateService getCheckRemindService(String itemCode, String itemNodeCode) throws ServiceException
	{
		String beanId = CHECK_REMIND + itemCode + itemNodeCode;
		BaseValidateService validateService;
		try {
			validateService = (BaseValidateService) SpringBeanUtil.getBean(beanId);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
		return (BaseValidateService) validateService;
	}
}
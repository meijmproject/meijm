package com.yh.component.validate.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.yh.component.validate.facade.YhValidateFacade;

/**
 *@description 前置检查拦截
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public class YhValidateBeforeAdvice implements MethodBeforeAdvice 
{
	private Log log = LogFactory.getLog(YhValidateBeforeAdvice.class);
	private YhValidateFacade yhValidateFacade;
	


	
	public void setYhValidateFacade(YhValidateFacade yhValidateFacade) {
		this.yhValidateFacade = yhValidateFacade;
	}




	/**
	 * 方法执行前规则检查
	 * @param method
	 * @param args
	 * @param target
	 * @throws Throwable
	 */
	public void before(Method method, Object[] args, Object target)throws Throwable 
	{
		log.debug("biz check:----------------"+method.getDeclaringClass().getSimpleName()+""+method.getName()+"--------------------------");
		yhValidateFacade.check(method.getName(), method.getDeclaringClass().getSimpleName());
	}


}

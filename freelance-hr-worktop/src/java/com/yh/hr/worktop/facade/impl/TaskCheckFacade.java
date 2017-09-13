package com.yh.hr.worktop.facade.impl;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.worktop.factory.TaskCheckFactory;
import com.yh.platform.core.exception.ServiceException;

/**
 * 信息检查
 * @author	zhangqp
 * @version	1.0,	16/11/01
 */
public class TaskCheckFacade {
	
	/**
	 * 检查
	 * @throws ServiceException
	 */
	public void checkRemind() throws ServiceException {
		// 获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		String itemCode = (String)data.get("itemCode") ;
		String itemNodeCode = (String)data.get("itemNodeCode") ;
		
		BaseValidateService validateService = TaskCheckFactory.getCheckRemindService(itemCode, itemNodeCode);
		
		if (validateService != null) {
			validateService.validate();
		}
	}
	
	/**
	 * 上报前检查（强制）
	 * @throws ServiceException
	 */
	public void checkBeforeTask() throws ServiceException {
		// 获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		String itemCode = (String)data.get("itemCode") ;
		String itemNodeCode = (String)data.get("itemNodeCode") ;
		
		BaseValidateService validateService = TaskCheckFactory.getBeforeTaskCheckService(itemCode, itemNodeCode);
		
		if (validateService != null) {
			validateService.validate();
		}
	}
	
}

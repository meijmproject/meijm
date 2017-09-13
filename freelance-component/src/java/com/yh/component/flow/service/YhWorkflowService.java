package com.yh.component.flow.service;

import java.util.HashMap;

import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 *	流程流转 - Service
 *	@author		liuhw
 *	@created	2016-08-25
 */
public interface YhWorkflowService
{
	/**
	 *	启动
	 *	@param	workFlowData	流程数据map
	 *	@return	JadeWorkflowDTO	流程流转信息
	 *	@throws ServiceException
	 */
	public JadeWorkflowDTO start(HashMap<String, Object> workFlowData) throws ServiceException;
	
	/**
	 *	推动
	 *	@param	workFlowData	流程数据map
	 *	@return	JadeWorkflowDTO	流程流转信息
	 *	@throws ServiceException
	 */
	public JadeWorkflowDTO next(HashMap<String, Object> workFlowData) throws ServiceException;
	
	/**
	 *	删除
	 *	@param	workFlowData	流程数据map
	 *	@throws ServiceException
	 */
	public void delete(HashMap<String, Object> workFlowData) throws ServiceException;
}
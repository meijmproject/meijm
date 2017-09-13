package com.yh.component.flow.service.impl;

import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.component.flow.queryhelper.JadeFlowNodeInfoQueryHelper;
import com.yh.component.flow.service.YhWorkflowService;
import com.yh.component.flow.utils.JadeFlowConstants;
import jade.workflow.application.WorkFlowUtil;
import jade.workflow.engine.bo.FlowNode;
import jade.workflow.exception.JadeWorkFlowException;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yh.platform.core.exception.ServiceException;

/**
 *	工作流流转 - ServiceImpl
 *	@author		liuhw
 *	@created	2016-08-25
 */
public class YhWorkflowServiceImpl implements YhWorkflowService
{
	private static Logger log = Logger.getLogger(YhWorkflowServiceImpl.class);
	
	/**
	 *	查询工作流流转信息
	 *	@param flowInstanceOid 流程实例ID
	 *	@throws ServiceException
	 */
	private JadeWorkflowDTO getCurrentBizWorkflowDTO(Long flowInstanceOid) throws ServiceException
	{
		return JadeFlowNodeInfoQueryHelper.findDTOByFlowInstanceOid(flowInstanceOid);
	}
	
	/**
	 *	启动
	 *	1.启动工作流
	 *  2.查询工作流流转信息
	 *	@param	bizTaskOid		业务事项ID
	 *	@param	itemCode		事项代码
	 *	@return	bizWorkflowDTO	流程流转信息
	 *	@throws ServiceException
	 */
	public JadeWorkflowDTO start(HashMap<String, Object> workFlowData) throws ServiceException
	{
		
		// 1.启动工作流
		if(null == workFlowData || workFlowData.size()==0)
		{
			throw new ServiceException("工作流程容器不能为空");
		}
		Long flowInstanceOid=null;
		try
		{
			if(null != workFlowData && workFlowData.size()>0)
			{
			    flowInstanceOid = WorkFlowUtil.start((String) workFlowData.get("itemCode"), workFlowData, (String)workFlowData.get("userId"));
			}
		}
		catch(JadeWorkFlowException jex)
		{
			log.error(JadeFlowConstants.START_FLOW_EXCEPTION_PRE+jex.getMessage());
			throw new ServiceException(JadeFlowConstants.START_FLOW_EXCEPTION_PRE+jex.getMessage(),jex);
		}
		// 2.查询工作流流转信息
		return this.getCurrentBizWorkflowDTO(flowInstanceOid);
	}
	
	/**
	 *	下一步
	 *	1.推动工作流
	 *  2.查询工作流流转信息
	 *	@param bizWorkFlowContext  工作流程容器
	 *	@throws ServiceException
	 */
	public JadeWorkflowDTO next(HashMap<String, Object> workFlowData) throws ServiceException
	{
		String flowInstanceOidStr = (String)workFlowData.get("flowInstanceOid");
		if(StringUtils.isNotEmpty(flowInstanceOidStr))
		{
			FlowNode flowNode = JadeFlowNodeInfoQueryHelper.getFlowNodeByFlowInstanceOid(Long.valueOf(flowInstanceOidStr));
			
			try
			{
				WorkFlowUtil.completeStep(flowNode.getFlowNodeOid(), workFlowData, (String)workFlowData.get("userId"));
			}
			catch(JadeWorkFlowException flowException)
			{
				log.error(JadeFlowConstants.COMPLETESTEP_FLOW_EXCEPTION_PRE+flowException.getMessage());
	            if(JadeFlowConstants.JWF_1007.equals(flowException.getErrorCode())) {
	                //如果是没有找到匹配的流转方向
	                throw new ServiceException("此业务事项不支持该操作!", flowException);
	            }else{
				    throw new ServiceException(JadeFlowConstants.COMPLETESTEP_FLOW_EXCEPTION_PRE+flowException.getMessage(),flowException);
	            }
			}
			// 2.查询工作流流转信息
			return this.getCurrentBizWorkflowDTO(Long.valueOf(flowInstanceOidStr));
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param bizWorkFlowContext  工作流程容器
	 * @throws ServiceException
	 */
	public void delete(HashMap<String, Object> workFlowData) throws ServiceException
	{
		try
		{
			String flowInstanceOidStr = (String)workFlowData.get("flowInstanceOid");
			if(StringUtils.isNotEmpty(flowInstanceOidStr))
			{
				WorkFlowUtil.deleteFlow(Long.valueOf(flowInstanceOidStr), (String)workFlowData.get("userId"));
			}
		}
		catch(JadeWorkFlowException flowException)
		{
			log.error(JadeFlowConstants.DELETE_FLOW_EXCEPTION_PRE+flowException.getMessage());
            if(JadeFlowConstants.JWF_1007.equals(flowException.getErrorCode())) {
                //如果是没有找到匹配的流转方向
                throw new ServiceException("此业务事项不支持该操作!", flowException);
            }else{
			    throw new ServiceException(JadeFlowConstants.DELETE_FLOW_EXCEPTION_PRE+flowException.getMessage(),flowException);
            }
		}
	}
}
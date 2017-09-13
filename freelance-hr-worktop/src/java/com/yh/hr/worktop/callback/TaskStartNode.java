/**
 * 
 */
package com.yh.hr.worktop.callback;


import jade.workflow.callback.FlowCallBackInterface;
import jade.workflow.exception.JadeWorkFlowException;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.bt.bo.BtTask;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * @description 开始节点 
 * @author liuhw
 * @created 2016-08-30
 * @version 1.0
 * 
 */
public class TaskStartNode implements FlowCallBackInterface 
{
	
	/*
	 * (non-Javadoc)
	 * @see jade.workflow.callback.FlowCallBackInterface#doAfter(java.lang.Long, java.lang.Long, java.util.Map)
	 */
	public void doAfter(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException
	{
		
		//开始节点，只会被调用doAfter方法
		String taskOidStr =  flowData.get("taskOid") != null ? flowData.get("taskOid").toString() : null;
		if(StringUtils.isBlank(taskOidStr))
		{
			throw new JadeWorkFlowException("传入参数taskOid不能为空。","error.taskOidisempty.flowdata");
		}
		try {
			BtTask bo = DaoUtil.get(BtTask.class, Long.valueOf(taskOidStr));
			if(null != bo)
			{
				bo.setFlowInstanceOid(processId);
				bo.update();
			}
		} catch (DataAccessFailureException e) {
			throw new JadeWorkFlowException("BizStartNode fail");
		}
		
    }

	/*
	 * (non-Javadoc)
	 * @see jade.workflow.callback.FlowCallBackInterface#doBefore(java.lang.Long, java.lang.Long, java.util.Map)
	 */
	public void doBefore(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException
	{
		
	}

	/*
	 * (non-Javadoc)
	 * @see jade.workflow.callback.FlowCallBackInterface#doPreCheck(java.lang.Long, java.lang.Long, java.util.Map)
	 */
	public void doPreCheck(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException
	{
		
	}

}

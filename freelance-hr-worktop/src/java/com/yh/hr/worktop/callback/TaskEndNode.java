/**
 * 
 */
package com.yh.hr.worktop.callback;

import jade.workflow.callback.FlowCallBackInterface;
import jade.workflow.exception.JadeWorkFlowException;
import java.util.Map;

/**
 * @description 结束节点 
 * @author liuhw
 * @created 2016-08-30
 * @version 1.0
 * 
 */
public class TaskEndNode implements FlowCallBackInterface 
{

	/*
	 * (non-Javadoc)
	 * @see jade.workflow.callback.FlowCallBackInterface#doAfter(java.lang.Long, java.lang.Long, java.util.Map)
	 */
	public void doAfter(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException
	{
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

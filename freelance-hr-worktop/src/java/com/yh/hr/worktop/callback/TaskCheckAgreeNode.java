package com.yh.hr.worktop.callback;


import java.util.Map;

import com.yh.hr.component.task.util.TaskConstants;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.DateUtil;

import jade.workflow.callback.FlowCallBackInterface;
import jade.workflow.exception.JadeWorkFlowException;

/**审核同意更新业务表状态
 *
 * @author liuhw
 * @version 1.0
 * @created 2016-8-30
 */
public class TaskCheckAgreeNode implements FlowCallBackInterface {

    /**
     * 环节前置数据校验。
     * 进入某个环节前，自动触发校验接口，若校验不通过，系统以异常形式返回。
     *
     * @param processId 流程实例 ID
     * @param flowData  本环节的流转参数
     * @return
     * @throws jade.workflow.core.exception.JadeWorkFlowException
     *
     */
    public void doPreCheck(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
    }

    /**
     * 环节前置处理。
     * 流程推进到某个环节时，自动触发前置处理接口。
     *
     * @param processId 流程实例 ID
     * @param flowData  本环节的流转参数
     * @throws jade.workflow.core.exception.JadeWorkFlowException
     *
     */
    public void doBefore(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
    }

    /**
     * 环节后置处理
     * 流程离开某个环节时，自动触发本接口。
     *
     * @param processId 流程实例 ID
     * @param flowData  本环节的流转参数
     * @throws jade.workflow.core.exception.JadeWorkFlowException
     *
     */
    public void doAfter(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
 		//通过流程实例ID获取BizPersonInfo CREATE_FLOW_UNIT
		try {
			BtTask bo = DaoUtil.get(BtTask.class, Long.valueOf(flowData.get("taskOid").toString()));
//	 		更新办理同意
			bo.setProcessResult(TaskConstants.PROCESS_RESULT_0);
	    	 //更新完成时间
			bo.setCompleteTime(DateUtil.now());
			bo.update();
		} catch (DataAccessFailureException e) 
		{
			throw new JadeWorkFlowException("doAfter processId fail","环 节后置处理失败！");
		}

    }
}

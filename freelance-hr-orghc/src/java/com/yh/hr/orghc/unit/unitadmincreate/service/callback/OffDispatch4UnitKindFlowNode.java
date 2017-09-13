package com.yh.hr.orghc.unit.unitadmincreate.service.callback;

import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.hr.orghc.ut.queryhelper.BizUtUnitQueryHelper;
import jade.workflow.callback.FlowCallBackInterface;
import jade.workflow.exception.JadeWorkFlowException;
import java.util.Map;

import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.queryhelper.BtTaskQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.worktop.util.TaskWorkTopConstants;
import com.yh.platform.core.exception.ServiceException;

/**
 * 根据单位性质---机关单位和事业单位
 * @author zhengdr
 *
 * 时间:2016-12-21上午09:28:13
 */
public class OffDispatch4UnitKindFlowNode implements FlowCallBackInterface {
	
	/**
	 * 环节前置数据校验。 进入某个环节前，自动触发校验接口，若校验不通过，系统以异常形式返回。
	 * 
	 * @param processId
	 *            流程实例 ID
	 * @param flowData
	 *            本环节的流转参数
	 * @return
	 * @throws jade.workflow.core.exception.JadeWorkFlowException
	 */
	public void doPreCheck(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
	}

	/**
	 * 环节前置处理。 流程推进到某个环节时，自动触发前置处理接口。
	 * 
	 * @param processId
	 *            流程实例 ID
	 * @param flowData
	 *            本环节的流转参数
	 * @throws jade.workflow.core.exception.JadeWorkFlowException
	 */
	public void doBefore(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
	}

	/**
	 * 环节后置处理 流程离开某个环节时，自动触发本接口。
	 * 
	 * @param processId
	 *            流程实例 ID
	 * @param flowData
	 *            本环节的流转参数
	 * @throws jade.workflow.core.exception.JadeWorkFlowException
	 */
	public void doAfter(Long processId, Long flowNodeOid, Map<String, Object> flowData) throws JadeWorkFlowException {
		try {
			/**
			 * 默认主管单位申报跳转到公管处审核
			 */
			String dispatch = "y";//y:公管处审核；n：事管处审核

			BtTask btTask = BtTaskQueryHelper.getByProcessId(processId);
			if(btTask==null){
				throw new ServiceException(null, "单位业务信息不存在！processId="+processId);
			}
			
			BizUtUnit bizUtUnit = BizUtUnitQueryHelper.getByTaskOid(btTask.getTaskOid());
			if (bizUtUnit == null) {
				throw new ServiceException(null, "单位业务信息不存在！processId="+processId);
			}
			 //判断是否为事业单位
			if(DicConstants.YHRS0090_104.equals(bizUtUnit.getUnitKind())){
				//事业单位到公管处审核
				dispatch = TaskWorkTopConstants.FLOW_EXP_N;
			}
			
			// 推动下一设定环节
			flowData.put("__dcma", dispatch);
			
		} catch (ServiceException e) {
			throw new JadeWorkFlowException(e.getMessage(), e);
		}
	}
	
}

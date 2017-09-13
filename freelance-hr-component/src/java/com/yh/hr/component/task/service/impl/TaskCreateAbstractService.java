package com.yh.hr.component.task.service.impl;

import java.util.Date;
import java.util.HashMap;

import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.component.flow.service.YhWorkflowService;
import com.yh.hr.component.task.dto.BtFlowNodeDTO;
import com.yh.hr.component.task.queryhelper.TaskQueryHelper;
import com.yh.hr.component.task.service.TaskCreateService;
import com.yh.hr.component.task.util.TaskConstants;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.hr.res.bt.dto.FlowDTO;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.queryhelper.UtOrgQueryHelper;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;

/**
 *	业务事项创建 - AbstractService
 *	@author		liuhw
 *	@created	2016-08-29
 */
public abstract class TaskCreateAbstractService implements TaskCreateService
{
	protected	FlowDTO			flowDTO;
	private		YhWorkflowService	yhWorkflowService;
	
	public void setFlowDTO(FlowDTO flowDTO) 
	{
		this.flowDTO = flowDTO;
	}
	

	public void setYhWorkflowService(YhWorkflowService yhWorkflowService) {
		this.yhWorkflowService = yhWorkflowService;
	}


	/**
	 *	创建业务事项信息
	 *	@param	itemCode		事项代码
	 *	@throws ServiceException
	 */
	protected Long createTaskInfo(String itemCode) throws ServiceException
	{
		Date now = DateUtil.now();
		BtTask bo = new BtTask();
		
		//业务发起单位
		bo.setStartUnitOid(flowDTO.getUnitOid());
		if (NumberUtils.isNotNullOrZero(flowDTO.getUnitOid())) {
			UtUnit unit = UtUnitQueryHelper.get(flowDTO.getUnitOid());
			if (unit != null) {
				bo.setStartUnitName(unit.getUnitName());
			}
		}
		//业务发起科室
		bo.setStartOrgOid(flowDTO.getOrgOid());
		if (NumberUtils.isNotNullOrZero(flowDTO.getOrgOid())) {
			UtOrg org = UtOrgQueryHelper.get(flowDTO.getOrgOid());
			if (org != null) {
				bo.setStartOrgName(org.getOrgName());
			}
		}
//		bo.setStartTime(now);//业务上报时间，改为在上报节点中更新
		bo.setItemCode(itemCode);
		bo.setCreatedByCode(flowDTO.getCreateByCode());
		bo.setCreatedByName(flowDTO.getCreateByName());
		bo.setCreatedDate(now);
		bo.setUpdatedByCode(flowDTO.getUpdateByCode());
		bo.setUpdatedDate(now);
		bo.setUpdatedByName(flowDTO.getUpdateByName());
		bo.save();
		
		return bo.getTaskOid();
	}
	
	/**
	 *	创建具体事项信息
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public abstract void createSubTaskInfo(Long bizTaskOid) throws ServiceException;
	
	/**
	 *	创建待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@param	bizWorkflowDTO	流程流转信息
	 *  @param  flowDTO    流程节点配置DTO（待办）
	 *	@throws ServiceException
	 */
	protected void createWaitItemInfo(Long bizTaskOid, JadeWorkflowDTO dto,BtFlowNodeDTO btFlowDTO) throws ServiceException
	{
		BtTaskItem bo = new BtTaskItem();
		bo.setTaskOid(bizTaskOid);
		bo.setFlowNodeOid(dto.getFlowNodeOid());
		bo.setTaskItemCode(btFlowDTO.getFlowNodeCode());
		bo.setTaskItemName(btFlowDTO.getFlowNodeName());
		bo.setTaskItemStatus(TaskConstants.QUERY_TYPE_1);
		bo.setAuditStatus(flowDTO.getAuditStatus());
		bo.setCreatedByCode(flowDTO.getCreateByCode());
		bo.setCreatedByName(flowDTO.getCreateByName()); 
		bo.setCreatedDate(DateUtil.now());
		bo.setUpdatedByCode(flowDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(flowDTO.getUpdateByName());
		bo.save();
	}
	
	/**
	 *	创建
	 *	1.创建业务事项信息
	 *	2.创建具体事项信息
	 *	3.开启工作流
	 *	4.创建待办事项
	 *	@param	itemCode		事项代码
	 *	@param	bizTaskOid		业务OID
	 *	@throws ServiceException
	 */
	public Long create() throws ServiceException
	{
		// 1.创建业务事项信息
		Long bizTaskOid = this.createTaskInfo(flowDTO.getItemCode());
		// 2.创建具体事项信息
		createSubTaskInfo(bizTaskOid);
		
		//组装开启流程需要的参数
		HashMap<String, Object> workFlowData = new HashMap<String,Object>();
		workFlowData.put("itemCode", flowDTO.getItemCode());
		workFlowData.put("taskOid", bizTaskOid);
		workFlowData.put("userId", flowDTO.getCreateByCode());
		
		// 3.开启工作流
		JadeWorkflowDTO dto = yhWorkflowService.start(workFlowData);
		if(null != dto)
		{
			//更新业务信息，将流程实例ID写入业务表
			updateTaskInfo(dto.getFlowInstanceOid(),bizTaskOid);
			
			//根据工作流返回数据：流程节点，查找配置表对应的业务环节
			BtFlowNodeDTO flowDTO= TaskQueryHelper.findTaskItemByFlowNode(dto.getFlowInstanceOid(), dto.getFlowNode());
			if(null != flowDTO)
			{
				// 4.创建待办事项
				this.createWaitItemInfo(bizTaskOid, dto,flowDTO);
			}
		}		
		return bizTaskOid;
	}

	/**
	 * 更新业务信息
	 * @param flowInstanceOid
	 * @param bizTaskOid
	 * @throws ServiceException
	 */
	private void updateTaskInfo(Long flowInstanceOid, Long bizTaskOid) throws ServiceException
	{
		BtTask bo = DaoUtil.get(BtTask.class, bizTaskOid);
		bo.setFlowInstanceOid(flowInstanceOid);
		bo.setUpdatedByCode(flowDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(flowDTO.getUpdateByName());
		bo.update();
	}
	
	/**
	 * 更新业务来源
	 */
	protected void updateRefTaskOid(Long taskOid, Long refBizTaskOid) throws ServiceException {
		BtTask task = DaoUtil.get(BtTask.class, taskOid);
		task.setRefBizTaskOid(refBizTaskOid);  //上一笔业务来源ID
		task.update();
	}
}
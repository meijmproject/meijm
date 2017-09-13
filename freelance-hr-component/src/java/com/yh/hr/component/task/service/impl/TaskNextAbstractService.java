package com.yh.hr.component.task.service.impl;

import java.util.HashMap;

import com.yh.hr.component.task.dto.BtFlowNodeDTO;
import org.apache.commons.lang.StringUtils;

import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.component.flow.service.YhWorkflowService;
import com.yh.hr.component.task.queryhelper.TaskQueryHelper;
import com.yh.hr.component.task.service.TaskNextService;
import com.yh.hr.component.task.util.TaskConstants;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.res.bt.queryhelper.BtTaskItemQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 *	业务事项推动 - AbstractService
 *	@author		liuhw
 *	@created	2016-08-30
 */
public abstract class TaskNextAbstractService implements TaskNextService
{
	protected	LinkDTO linkDTO;
	private		YhWorkflowService	yhWorkflowService;
	
	public void setLinkDTO(LinkDTO linkDTO) 
	{
		this.linkDTO = linkDTO;
	}

	public void setYhWorkflowService(YhWorkflowService yhWorkflowService) {
		this.yhWorkflowService = yhWorkflowService;
	}

	/**
	 *	流程通用校验
	 *	@param	LinkDTO
	 *	@throws	ServiceException
	 */
	protected void validateCommon(LinkDTO linkDTO) throws ServiceException 
	{
		if(null != linkDTO)
		{
			BtTaskItem bo = BtTaskItemQueryHelper.findWaitItemByCondition(linkDTO.getBizTaskOid(), TaskConstants.QUERY_TYPE_1, linkDTO.getItemNodeCode());
			if(null == bo)
			{
				throw new ServiceException("nonentity.operate.data","操作失败，该业务已不在当前环节，无法操作！");
			}
		}
	}
	
	/**
	 *	修改业务事项信息
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	protected void modifyTaskInfo(Long bizTaskOid) throws ServiceException
	{
		BtTask bo = DaoUtil.get(BtTask.class, bizTaskOid);
		
		bo.setUpdatedByCode(linkDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(linkDTO.getUpdateByName());
		bo.update();
	}
	
	/**
	 *	推动具体事项处理
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public abstract void modifySubTaskInfoByNext(Long bizTaskOid) throws ServiceException;
	
	/**
	 *	修改当前待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@return	bizWaitItemOid	待办事项OID
	 *	@throws ServiceException
	 */
	protected Long modifyWaitItemInfo(Long bizTaskOid) throws ServiceException
	{
		Long bizWaitItemOid = BtTaskItemQueryHelper.getBizWaititemOid(bizTaskOid, TaskConstants.QUERY_TYPE_1);
		
		BtTaskItem bo = DaoUtil.get(BtTaskItem.class, bizWaitItemOid);
		
		bo.setTaskItemStatus(TaskConstants.QUERY_TYPE_2);
		bo.setUpdatedByCode(linkDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(linkDTO.getUpdateByName());
		
		bo.update();
		
		return bizWaitItemOid;
	}
	
	/**
	 *	创建新的待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@param	bizWorkflowDTO	流程流转信息
	 *	@throws ServiceException
	 */
	protected void createWaitItemInfo(Long bizTaskOid, Long preWaitItemOid,JadeWorkflowDTO dto,BtFlowNodeDTO flowDTO) throws ServiceException
	{
		BtTaskItem preBo = DaoUtil.get(BtTaskItem.class, preWaitItemOid);
		
		BtTaskItem bo = new BtTaskItem();
		bo.setTaskOid(bizTaskOid);
		bo.setPreTaskitemOid(preWaitItemOid);
		bo.setPreTaskItemCode(preBo.getTaskItemCode());
		bo.setPreTaskItemName(preBo.getTaskItemName());
		bo.setFlowNodeOid(dto.getFlowNodeOid());
		bo.setTaskItemCode(flowDTO.getFlowNodeCode());
		bo.setTaskItemName(flowDTO.getFlowNodeName());
		bo.setOpinion(linkDTO.getOpinion());
		bo.setTaskItemStatus(TaskConstants.QUERY_TYPE_1);
		bo.setCreatedByCode(linkDTO.getCreateByCode());
		bo.setCreatedByName(linkDTO.getCreateByName()); 
		bo.setCreatedDate(DateUtil.now());
		bo.setUpdatedByCode(linkDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(linkDTO.getUpdateByName());
		bo.save();
	}
	
	/**
	 *	推动
	 *	1.流程通用校验
	 *	2.修改业务事项信息
	 *	3.推动具体事项处理
	 *	4.推动工作流
	 *	5.修改当前待办事项
	 *	6.创建新的待办事项
	 *	@throws ServiceException
	 */
	public void next() throws ServiceException
	{
		// 1.流程通用校验
		this.validateCommon(linkDTO);
		// 2.修改业务事项信息
		this.modifyTaskInfo(linkDTO.getBizTaskOid());
		// 3.上报具体事项处理
		modifySubTaskInfoByNext(linkDTO.getBizTaskOid());
		
		HashMap<String, Object> workFlowData = new HashMap<String,Object>();
		workFlowData.put("taskOid", linkDTO.getBizTaskOid());
		workFlowData.put("userId", linkDTO.getUpdateByCode());
		//通过业务信息获取到流程实例ID
		BtTask bo = DaoUtil.get(BtTask.class, linkDTO.getBizTaskOid());
		workFlowData.put("flowInstanceOid", bo.getFlowInstanceOid().toString());
		if(StringUtils.isNotEmpty(linkDTO.getDefFlowExpress()) && StringUtils.isNotEmpty(linkDTO.getDefFlowExpressName()))
		{
			workFlowData.put(linkDTO.getDefFlowExpressName(), linkDTO.getDefFlowExpress());
		}else
		{
			throw new ServiceException("流程表达式不能为空！");
		}
		
		// 4.推动工作流
		JadeWorkflowDTO dto = yhWorkflowService.next(workFlowData);
		// 5.修改当前待办事项
		Long bizWaititemOid = this.modifyWaitItemInfo(linkDTO.getBizTaskOid());
		// 6.创建新的待办事项
		if(null != dto)
		{
			//根据工作流返回数据：流程节点，查找配置表对应的业务环节
			BtFlowNodeDTO flowDTO= TaskQueryHelper.findTaskItemByFlowNode(dto.getFlowInstanceOid(), dto.getFlowNode());
			if(null != flowDTO)
			{
				this.createWaitItemInfo(linkDTO.getBizTaskOid(), bizWaititemOid, dto,flowDTO);
			}
		}
	}
}
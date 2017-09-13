package com.yh.hr.component.task.service.impl;

import java.util.HashMap;

import com.yh.hr.component.task.dto.BtFlowNodeDTO;
import com.yh.hr.component.task.queryhelper.TaskQueryHelper;
import com.yh.hr.component.task.service.TaskRevokeService;
import com.yh.hr.component.task.util.TaskConstants;
import org.apache.commons.lang.StringUtils;

import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.component.flow.service.YhWorkflowService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.res.bt.queryhelper.BtTaskItemQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 *	业务事项撤回 - AbstractService
 *	@author		liuhw
 *	@created	2016-08-30
 */
public abstract class TaskRevokeAbstractService implements TaskRevokeService
{
	protected	LinkDTO linkDTO;
	private		YhWorkflowService	yhWorkflowService;
	
	public void setLinkDTO(LinkDTO linkDTO) 
	{
		this.linkDTO = linkDTO;
	}
	
	public void setJadeWorkflowService(YhWorkflowService yhWorkflowService) {
		this.yhWorkflowService = yhWorkflowService;
	}
	

	/**
	 *	流程撤回校验
	 *	@param	LinkDTO
	 *	@throws	ServiceException
	 */
	protected void validateRecall(LinkDTO linkDTO) throws ServiceException 
	{
		if(null != linkDTO)
		{
			BtTaskItem bo = BtTaskItemQueryHelper.findWaitItemByCond(linkDTO.getBizTaskOid(), TaskConstants.QUERY_TYPE_1, linkDTO.getItemNodeCode());
			if(null == bo || linkDTO.getItemNodeCode().equals(bo.getTaskItemCode()))
			{
				throw new ServiceException("curr.taskinfo.cannot.revoke","<nobr>操作失败，该业务在下一环节已办理或已被撤回或已办结，无法再撤！</nobr>");
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
	 *	撤回具体事项处理
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public abstract void modifySubTaskInfoByRevoke(Long bizTaskOid) throws ServiceException;
	
	/**
	 *	删除当前待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	protected Long deleteWaitItemInfo(Long bizTaskOid) throws ServiceException
	{
		Long bizWaitItemOid = BtTaskItemQueryHelper.getBizWaititemOid(bizTaskOid, TaskConstants.QUERY_TYPE_1);
		
		BtTaskItem bo = DaoUtil.get(BtTaskItem.class, bizWaitItemOid);
		
		bo.delete();
		
		Long preWaitItemOid = BtTaskItemQueryHelper.getBizWaititemOid(bizTaskOid, TaskConstants.QUERY_TYPE_2);
		
		return preWaitItemOid;
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
	 *	撤回
	 *	1.流程撤回校验
	 *	2.修改业务事项信息
	 *	3.撤回具体事项处理
	 *	4.推动工作流
	 *	5.修改当前待办事项
	 *	6.创建新的待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public void revoke() throws ServiceException
	{
		// 1.流程撤回校验
		this.validateRecall(linkDTO);
		// 2.修改业务事项信息
		this.modifyTaskInfo(linkDTO.getBizTaskOid());
		// 3.撤回具体事项处理
		modifySubTaskInfoByRevoke(linkDTO.getBizTaskOid());
		
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
		// 5.删除当前待办事项
		Long bizWaititemOid = this.deleteWaitItemInfo(linkDTO.getBizTaskOid());
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
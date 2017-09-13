package com.yh.hr.component.task.service.impl;


import java.util.HashMap;

import com.yh.hr.component.task.service.TaskStopService;
import com.yh.hr.component.task.util.TaskConstants;
import org.apache.commons.lang.StringUtils;

import com.yh.component.flow.service.YhWorkflowService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.res.bt.queryhelper.BtTaskItemQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 * 
 *@description		终止抽象类
 *
 *@author            liuhw
 *@created           2013-03-20
 *@version           1.0
 *
 */
public abstract class TaskStopAbstractService implements TaskStopService
{
	protected LinkDTO LinkDTO;
	
	private		YhWorkflowService	yhWorkflowService;
	
	public void setJadeWorkflowService(YhWorkflowService yhWorkflowService) {
		this.yhWorkflowService = yhWorkflowService;
	}


	protected LinkDTO getLinkDTO() {
		return LinkDTO;
	}
    
	
	public void setLinkDTO(LinkDTO LinkDTO) {
		this.LinkDTO = LinkDTO;
	}
	
	public void stop() throws ServiceException 
	{
		this.validate(LinkDTO);
		//更新业务信息
		updateTaskInfo();
		//操作其他信息
		nextExecute();
		
		HashMap<String, Object> workFlowData = new HashMap<String,Object>();
		workFlowData.put("taskOid", LinkDTO.getBizTaskOid());
		workFlowData.put("userId", LinkDTO.getUpdateByCode());
		//通过业务信息获取到流程实例ID
		BtTask bo = DaoUtil.get(BtTask.class, LinkDTO.getBizTaskOid());
		workFlowData.put("flowInstanceOid", bo.getFlowInstanceOid().toString());
		if(StringUtils.isNotEmpty(LinkDTO.getDefFlowExpress()) && StringUtils.isNotEmpty(LinkDTO.getDefFlowExpressName()))
		{
			workFlowData.put(LinkDTO.getDefFlowExpressName(), LinkDTO.getDefFlowExpress());
		}else
		{
			throw new ServiceException("流程表达式不能为空！");
		}
		workFlowData.put("opinion", LinkDTO.getOpinion());
		// 4.推动工作流
		yhWorkflowService.next(workFlowData);
	    
	    updateWaitItemInfo(LinkDTO.getBizTaskOid());
		
	}
	
	
	/**
	 *	修改当前待办事项
	 *	@param	bizTaskOid		业务事项OID
	 *	@return	bizWaitItemOid	待办事项OID
	 *	@throws ServiceException
	 */
	protected Long updateWaitItemInfo(Long bizTaskOid) throws ServiceException
	{
		Long bizWaitItemOid = BtTaskItemQueryHelper.getBizWaititemOid(bizTaskOid, TaskConstants.QUERY_TYPE_1);
		
		//创建事项环节审核信息
		//BizItemNodeAuditWrappor wrappor = new BizItemNodeAuditWrappor(bizLinkDTO.getOpinion(), bizLinkDTO.getAuditDate(), bizWaitItemOid, BaseCoreConstants.DISAGREE_CODE);//不同意;
		//wrappor.save();
		
		BtTaskItem bo = DaoUtil.get(BtTaskItem.class, bizWaitItemOid);
		
		bo.setTaskItemStatus(TaskConstants.QUERY_TYPE_2);
		bo.setOpinion(LinkDTO.getOpinion());
		bo.setUpdatedByCode(LinkDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(LinkDTO.getUpdateByName());
		
		bo.update();
		
		return bizWaitItemOid;
	}
	
	/**
	 * 业务校验
	 * @param bizTaskOid
	 */
	protected void validate(LinkDTO LinkDTO)throws ServiceException
	{
		if(null != LinkDTO)
		{
			BtTaskItem bo = BtTaskItemQueryHelper.findWaitItemByCondition(LinkDTO.getBizTaskOid(), TaskConstants.QUERY_TYPE_1, LinkDTO.getItemNodeCode());
			if(null == bo)
			{
				throw new ServiceException("nonentity.operate.data","操作失败，该业务已不在当前环节，无法操作！");
			}
		}
	}
	/**
	 * 更新业务信息
	 * @throws ServiceException
	 */
	protected void updateTaskInfo()throws ServiceException 
	{
		BtTask bo = DaoUtil.get(BtTask.class, LinkDTO.getBizTaskOid());
		
		bo.setUpdatedByCode(LinkDTO.getUpdateByCode());
		bo.setUpdatedDate(DateUtil.now());
		bo.setUpdatedByName(LinkDTO.getUpdateByName());
		//因为业务终止流程没有回调接口，所以在这个地方更新业务完成时间
		bo.setCompleteTime(DateUtil.now());
		bo.setProcessResult(TaskConstants.PROCESS_RESULT_2);
		bo.update();
	}

	public abstract void nextExecute() throws ServiceException;
}

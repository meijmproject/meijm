package com.yh.hr.component.task.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.hr.component.task.util.TaskConstants;
import org.apache.commons.collections.CollectionUtils;

import com.yh.component.flow.service.YhWorkflowService;
import com.yh.hr.component.task.service.TaskDeleteService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.res.bt.queryhelper.BtLogQueryHelper;
import com.yh.hr.res.bt.queryhelper.BtTaskItemQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项删除 - AbstractService
 *	@author		liuhw
 *	@created	2016-08-30
 */
public abstract class TaskDeleteAbstractService implements TaskDeleteService
{
	protected	LinkDTO linkDTO;
	private		YhWorkflowService	yhWorkflowService = (YhWorkflowService) SpringBeanUtil.getBean("yhWorkflowService");
	
	public void setLinkDTO(LinkDTO linkDTO) 
	{
		this.linkDTO = linkDTO;
	}
	
	/**
	 *	流程删除校验
	 *	@param	LinkDTO
	 *	@throws	ServiceException
	 */
	protected void validateDelete(LinkDTO linkDTO) throws ServiceException 
	{
		if(null != linkDTO)
		{
			List<BtTaskItem> list = BtTaskItemQueryHelper.findBizWaitItemInfoByBizTaskOid(linkDTO.getBizTaskOid());
			if(CollectionUtils.isEmpty(list))
			{
				throw new ServiceException("already.delete","操作失败，该业务已被删除，无法操作！");
			}
			BtTaskItem bizWaitItemInfo1 = BtTaskItemQueryHelper.findWaitItemByCondition(linkDTO.getBizTaskOid(), TaskConstants.QUERY_TYPE_1, linkDTO.getItemNodeCode());
			if(null == bizWaitItemInfo1)
			{
				throw new ServiceException("nonentity.operate.data","操作失败，该业务已不在当前环节，无法操作！");
			}
			if(CollectionUtils.isNotEmpty(list))
			{
				if (list.size() > 1) {
					throw new ServiceException("already.delete","操作失败，该业务在下一环节已办理过，无法删除！");
				}
				/*for(BtTaskItem bizWaitItemInfo : list )
				{
					if(null != bizWaitItemInfo.getPreTaskItemCode() &&  !linkDTO.getItemNodeCode().equals(bizWaitItemInfo.getPreTaskItemCode()))
					{
						throw new ServiceException("already.delete","操作失败，该业务在下一环节已办理过，无法删除！");
					}
				}*/
			}
		}
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
				throw new ServiceException("none.operate.flow", "操作失败，该业务已不在当前环节，无法操作！");
			}
		}
	}
	
	/**
	 *	删除业务事项信息
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	protected void deleteTaskInfo(Long bizTaskOid) throws ServiceException
	{
		BtLogQueryHelper.deleteByTaskOid(bizTaskOid);
		
		BtTask bo = DaoUtil.get(BtTask.class, bizTaskOid);
		if(null != bo)
		{
			bo.delete();
		}
	}
	
	/**
	 *	删除具体事项处理
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public abstract void deleteSubTaskInfo(Long bizTaskOid) throws ServiceException;
	
	/**
	 *	删除待办事项信息
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	protected void deleteWaitItemInfos(Long bizTaskOid) throws ServiceException
	{
		BtTaskItemQueryHelper.deleteWaitItemInfos(bizTaskOid);
	}
	
	/**
	 *	删除
	 *	1.流程删除校验
	 *	2.流程通用校验
	 *	3.推动工作流
	 *	4.删除待办事项信息
	 *	5.删除具体事项处理
	 *	6.删除业务事项信息
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public void delete() throws ServiceException
	{
		// 1.流程删除校验
		this.validateDelete(linkDTO);
		// 2.流程通用校验
		this.validateCommon(linkDTO);
		HashMap<String, Object> workFlowData = new HashMap<String,Object>();
		workFlowData.put("taskOid", linkDTO.getBizTaskOid());
		workFlowData.put("userId", linkDTO.getUpdateByCode());
		//通过业务信息获取到流程实例ID
		BtTask bo = DaoUtil.get(BtTask.class, linkDTO.getBizTaskOid());
		workFlowData.put("flowInstanceOid", bo.getFlowInstanceOid().toString());
		// 3.删除具体事项处理
		deleteSubTaskInfo(linkDTO.getBizTaskOid());
		// 4.删除待办事项信息
		this.deleteWaitItemInfos(linkDTO.getBizTaskOid());
		// 5.推动工作流
		yhWorkflowService.delete(workFlowData);
		// 6.删除业务事项信息
		this.deleteTaskInfo(linkDTO.getBizTaskOid());
	}
}
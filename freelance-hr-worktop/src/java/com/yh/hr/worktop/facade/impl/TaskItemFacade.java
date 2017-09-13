package com.yh.hr.worktop.facade.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.hr.worktop.factory.TaskItemFactory;
import com.yh.platform.core.exception.ServiceException;

import net.sf.json.JSONObject;

/**
 * 查询办事项列表（工作台右边查询列表）  facade
 * @author huw
 * @time 2016-09-28
 */
public class TaskItemFacade 
{
	/**
	 * 查询业务信息
	 * @param ttb
	 * @return List<JSONObject>
	 * @throws ServiceException
	 */
	public  List<JSONObject> listJhcBtTaskItem(TableTagBean ttb) throws ServiceException
	{
		TaskItemService taskItemService = TaskItemFactory.getJhcBtTaskViewService(ttb);
		return taskItemService.list(ttb);
	}
}

package com.yh.hr.component.task.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.platform.core.exception.ServiceException;

import net.sf.json.JSONObject;

/**
 * 默认待办事项查询服务类（工作台右边查询列表）
 * @author huw
 * @time 2016-09-28
 */
public class TaskItemDefaultServiceImpl implements TaskItemService
{
	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#list(TableTagBean)
	 */
	public List<JSONObject> list(TableTagBean ttb) throws ServiceException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#count(TableTagBean)
	 */
	public int count(TableTagBean ttb) throws ServiceException 
	{
		//如代办事项出现99请查询是否实现了自己的代办事项查询类
		return 99;
	}
}

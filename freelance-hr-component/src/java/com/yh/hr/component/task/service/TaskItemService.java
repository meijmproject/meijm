package com.yh.hr.component.task.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

import net.sf.json.JSONObject;

/**
 * 查询待办业务信息通用服务接口（工作台右边查询列表）
 * @author huw
 * @time 2016-09-28
 */
public interface TaskItemService 
{
	/**
	 * 获取待办/已办业务信息列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> list(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 获取待办/已办业务信息数量
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public int count(TableTagBean ttb) throws ServiceException;
}

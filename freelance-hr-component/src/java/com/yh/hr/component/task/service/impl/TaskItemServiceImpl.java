package com.yh.hr.component.task.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.queryhelper.TaskItemDefaultQueryHelper;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;

/**
 * 默认待办事项查询服务类（工作台右边查询列表）
 * @author huw
 * @time 2016-09-28
 */
public class TaskItemServiceImpl implements TaskItemService
{
	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#list(TableTagBean)
	 */
	public List<JSONObject> list(TableTagBean ttb) throws ServiceException 
	{
		ttb.setTotal(TaskItemDefaultQueryHelper.count(ttb));
		List<JSONObject> dtoList=TaskItemDefaultQueryHelper.list(ttb);
		
		if(CollectionUtils.isNotEmpty(dtoList)){
			for(JSONObject json:dtoList ){
				json.put("personTypeName", DicHelper.viewName(DicConstants.YHRS0010, ObjectUtils.toString(json.get("personType"), null)));
				json.put("dutyLevelName", DicHelper.viewName(DicConstants.YHRS0015, ObjectUtils.toString(json.get("dutyLevel"), null)));
				json.put("processResult", DicHelper.viewName(DicConstants.YHRS2003, ObjectUtils.toString(json.get("processResult"), null)));
				json.put("mPositionTypeDesc", DicHelper.viewName(DicConstants.YHRS0022, ObjectUtils.toString(json.get("mPositionType"), null)));
				json.put("mPositionLevelDesc", DicHelper.viewName(DicConstants.YHRS0023, ObjectUtils.toString(json.get("mPositionLevel"), null)));
			}
			
		}
		return dtoList;
	}

	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#count(TableTagBean)
	 */
	public int count(TableTagBean ttb) throws ServiceException 
	{
		return TaskItemDefaultQueryHelper.count(ttb);
	}
}

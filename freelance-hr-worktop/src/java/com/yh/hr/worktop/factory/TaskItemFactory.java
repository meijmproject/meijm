package com.yh.hr.worktop.factory;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 查询办事项列表（工作台右边查询列表） 维护工厂
 * @author huw
 * @time 2016-09-28
 */
public class TaskItemFactory 
{
	private final static String PREFIX_TASKWAITITEM = "taskItem";//查询待办事项
	private final static String PREFIX_DEFAULT ="Default";//默认
	/**
	 * 业务信息创建工厂类
	 * @param itemCode
	 * @return 
	 * @throws ServiceException
	 */
	public static TaskItemService getJhcBtTaskViewService(TableTagBean ttb) throws ServiceException
	{
		//当拿不到Bean的时候那么去拿一个默认的Bean;
		try{
//			String beanId = PREFIX_TASKWAITITEM +ttb.getCondition().get("taskItemCode");
			String beanId = PREFIX_TASKWAITITEM +ttb.getCondition().get("menuCode");
			TaskItemService taskItemService = (TaskItemService) SpringBeanUtil.getBean(beanId);
			return taskItemService;
		}catch (Exception e) {
			String beanId = PREFIX_TASKWAITITEM + PREFIX_DEFAULT;
			TaskItemService taskItemService = (TaskItemService) SpringBeanUtil.getBean(beanId);
			return taskItemService;
		}
	}
}

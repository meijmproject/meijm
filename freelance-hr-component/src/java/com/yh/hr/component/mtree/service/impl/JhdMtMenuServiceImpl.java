package com.yh.hr.component.mtree.service.impl;

import java.util.List;

import com.yh.hr.component.mtree.dto.MtCountCode;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.hr.component.task.util.TaskConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.hr.component.mtree.queryhelper.JhdMtMenuFacadeQueryHelper;
import com.yh.hr.component.mtree.service.JhdMtMenuService;
import com.yh.hr.component.mtree.util.MtConstants;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;

public class JhdMtMenuServiceImpl implements JhdMtMenuService {
	public boolean checkByRoleId(String roleId, String menuCode) throws ServiceException {
		return JhdMtMenuFacadeQueryHelper.checkByRoleId(roleId, menuCode);
	}
	public List<MtMenuDto> findItem(String parentMenuCode, String menuType) throws ServiceException{
		return JhdMtMenuFacadeQueryHelper.findItem(parentMenuCode,menuType);
	}

	public List<MtMenuDto> findJhdMtMenu(String menuCode, String menuType) throws ServiceException {
		List<MtMenuDto> list1 = JhdMtMenuFacadeQueryHelper.findJhdMtMenu(menuCode, menuType);
		
		if (CollectionUtils.isNotEmpty(list1)) {
			
			for (MtMenuDto dto1 : list1) {
				int countTotal = 0;
				//一级
				if (!"#".equals(dto1.getLocation())) {
					int count1 = this.countItemNodeByAuth(dto1.getMenuCode());
					countTotal = countTotal + count1;
				} else {
					countTotal=countNumber(dto1.getMenuCode(),menuType,countTotal);
				}
				dto1.setCount(countTotal);
			}
			return list1;
		}
		return null;
	}
    private int countNumber(String menuCode, String menuType,int countTotal) throws ServiceException{
    	List<MtMenuDto> list = JhdMtMenuFacadeQueryHelper.findJhdMtMenu(menuCode, menuType);
    	if (CollectionUtils.isNotEmpty(list)) {
			for (MtMenuDto dto : list) {
				if (!"#".equals(dto.getLocation())) {
					int count = this.countItemNodeByAuth(dto.getMenuCode());
					countTotal = countTotal + count;
				} else {
					countTotal = this.countNumber(dto.getMenuCode(), menuType, countTotal);
				}
			}
		}
		return countTotal;
	}
	public int countItemNodeByAuth(String menuCode) throws ServiceException {
		try {
			String flowNodeCode = JhdMtMenuFacadeQueryHelper.findMtNodeByMenuCode(menuCode);
			TableTagBean ttb = new TableTagBean();
			ttb.getCondition().put("taskItemCode", flowNodeCode==null?"":flowNodeCode);
			ttb.getCondition().put("menuCode", menuCode==null?"":menuCode);
			ttb.getCondition().put("taskItemStatus", MtConstants.QUERY_TYPE_1);
			TaskItemService bizWaitItemService = this.getBizTaskViewService(ttb);
			return bizWaitItemService.count(ttb);
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("count ItemNode By Auth faild !", e);
		}
	}

	public List<String> getChildWaitItemNode(String parentNodeCode) throws ServiceException {

		return JhdMtMenuFacadeQueryHelper.getLeafNodeCode(parentNodeCode);
	}

	/**
	 * 业务信息创建工厂类
	 * 
	 * @param itemCode
	 * @return
	 * @throws ServiceException
	 */
	private TaskItemService getBizTaskViewService(TableTagBean ttb) throws ServiceException {
		String PREFIX_TASKWAITITEM = "taskItem";// 查询待办事项
		String PREFIX_DEFAULT = "Default";// 默认
		// 当拿不到Bean的时候那么去拿一个默认的Bean;
		try {
			
			String beanId = PREFIX_TASKWAITITEM + ttb.getCondition().get("menuCode");
			TaskItemService taskItemService = (TaskItemService) SpringBeanUtil.getBean(beanId);
			return taskItemService;
		} catch (Exception e) {
			String beanId = PREFIX_TASKWAITITEM + PREFIX_DEFAULT;
			TaskItemService taskItemService = (TaskItemService) SpringBeanUtil.getBean(beanId);
			return taskItemService;
		}
	}
	public List<MtMenuDto> findJhdMtMenu1(String menuCode, String menuType) throws ServiceException {
		if(StringUtil.isNotBlank(menuType)){
			List<MtMenuDto> list = JhdMtMenuFacadeQueryHelper.findJhdMtMenu(menuCode, menuType);
			List<MtCountCode> countListDefault = JhdMtMenuFacadeQueryHelper.getCountByCodeDefault(menuType);
			if (CollectionUtils.isNotEmpty(list)) {
				for (MtMenuDto dto : list) {
						int countTotal = 0;
						List<MtCountCode> childList = JhdMtMenuFacadeQueryHelper.getAllChildCode(dto.getMenuCode());
						for(int j=0;j<childList.size();j++){
							if(!"#".equals(childList.get(j).getAction())){
								if(CollectionUtils.isNotEmpty(countListDefault)){
									for(int i=0;i<countListDefault.size();i++){
										if(childList.get(j).getMenuCode().equals(countListDefault.get(i).getMenuCode())){
											countTotal+=countListDefault.get(i).getCount();
										}
									}
								}
								
							}
						}
						dto.setCount(countTotal);
					}
				}
				return list;
		}else{
			List<MtMenuDto> list = JhdMtMenuFacadeQueryHelper.findJhdMtMenu(
					menuCode, menuType);
			List<MtCountCode> countListDefault1 = JhdMtMenuFacadeQueryHelper
					.getCountByCodeDefault(TaskConstants.MENU_TYPE_1);
			List<MtCountCode> countListDefault2 = JhdMtMenuFacadeQueryHelper
					.getCountByCodeDefault(TaskConstants.MENU_TYPE_2);
			if (CollectionUtils.isNotEmpty(list)) {
				for (MtMenuDto dto : list) {
					if (TaskConstants.MENU_TYPE_1.equals(dto.getMenuType())) {
						int countTotal = 0;
						List<MtCountCode> childList = JhdMtMenuFacadeQueryHelper
								.getAllChildCode(dto.getMenuCode());
						for (int j = 0; j < childList.size(); j++) {
							if (!"#".equals(childList.get(j).getAction())) {
								if(CollectionUtils.isNotEmpty(countListDefault1)){
									for (int i = 0; i < countListDefault1.size(); i++) {
										if (childList
												.get(j)
												.getMenuCode()
												.equals(countListDefault1.get(i)
														.getMenuCode())) {
											countTotal += countListDefault1.get(i)
													.getCount();
										}
									}
								}
								
							}
						}
						dto.setCount(countTotal);
					} else if (TaskConstants.MENU_TYPE_2.equals(dto
							.getMenuType())) {
						int countTotal = 0;
						List<MtCountCode> childList = JhdMtMenuFacadeQueryHelper
								.getAllChildCode(dto.getMenuCode());
						for (int j = 0; j < childList.size(); j++) {
							if (!"#".equals(childList.get(j).getAction())) {
								if(CollectionUtils.isNotEmpty(countListDefault2)){
									for (int i = 0; i < countListDefault2.size(); i++) {
										if (childList
												.get(j)
												.getMenuCode()
												.equals(countListDefault2.get(i)
														.getMenuCode())) {
											countTotal += countListDefault2.get(i)
													.getCount();
										}
									}
								}
								
							}
						}
						dto.setCount(countTotal);
					}

				}
			}
			return list;
		}
	}
}

package com.yh.hr.component.mtree.facade;

import java.util.List;

import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.hr.component.mtree.service.JhdMtMenuService;
import com.yh.hr.component.task.util.TaskConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class JhdMtMenuFacade {
	private JhdMtMenuService jhdMtMenuService;

	public void setJhdMtMenuService(JhdMtMenuService jhdMtMenuService) {
		this.jhdMtMenuService = jhdMtMenuService;
	}

	public List<MtMenuDto> findJhdMtMenu(String menuCode,String menuType) throws ServiceException {
		return jhdMtMenuService.findJhdMtMenu1(menuCode,menuType);
	}

	public List<MtMenuDto> findAllMenuTitle() throws ServiceException {
		return BeanHelper.copyProperties(jhdMtMenuService.findItem(null,TaskConstants.MENU_TYPE_1), MtMenuDto.class);
	}
}

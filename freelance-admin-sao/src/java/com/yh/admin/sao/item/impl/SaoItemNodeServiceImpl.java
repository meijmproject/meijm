/**
 * 
 */
package com.yh.admin.sao.item.impl;

import java.util.List;

import com.yh.admin.dto.MtMenuDto;
import com.yh.admin.sao.item.SaoItemNodeService;
import com.yh.hr.component.mtree.service.JhdMtMenuService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class SaoItemNodeServiceImpl implements SaoItemNodeService {
	private JhdMtMenuService jhdMtMenuService;

	public void setJhdMtMenuService(JhdMtMenuService jhdMtMenuService) {
		this.jhdMtMenuService = jhdMtMenuService;
	}

	public List<MtMenuDto> findItem(String parentMenuCode,String menuType) throws ServiceException{
		 return BeanHelper.copyProperties(jhdMtMenuService.findItem(parentMenuCode,menuType), MtMenuDto.class);
	}

	public boolean checkByRoleId(String roleId, String menuCode) throws ServiceException {
		return jhdMtMenuService.checkByRoleId(roleId,menuCode);
	}
}

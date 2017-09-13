package com.yh.hr.component.mtree.service;

import java.util.List;

import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.platform.core.exception.ServiceException;

public interface JhdMtMenuService {

	public List<MtMenuDto> findJhdMtMenu(String menuCode,String menuType) throws ServiceException;
	public int countItemNodeByAuth(String menuCode) throws ServiceException ;
	public List<String> getChildWaitItemNode(String parentNodeCode) throws ServiceException;
	public boolean checkByRoleId(String roleId, String menuCode) throws ServiceException;
	public List<MtMenuDto> findItem(String parentMenuCode,String menuType) throws ServiceException;
	public List<MtMenuDto> findJhdMtMenu1(String menuCode,String menuType) throws ServiceException;
}

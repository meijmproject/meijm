/*
 * @(#) MenuServiceImpl.java        1.00         2006-4-13
 * 
 * Copyright (c) 2006 FEDEX EXPRESS Corporation. All Rights Reserved.
 *
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FEDERAL EXPRESS AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL FEDERAL
 * EXPRESS OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF FEDERAL EXPRESS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 */
package com.yh.admin.menu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;

import com.yh.admin.bo.MenuItem;
import com.yh.admin.menu.queryhelper.MenuQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;

/**
 * @description
 * @author chenkebing
 * @created 2006-4-13
 * @version 1.0
 */

public class MenuService {
	Logger log = Logger.getLogger(MenuService.class);

	/**
	 * find all menu and list them by step
	 * 
	 * @throws ServiceException
	 */
	@SuppressWarnings("rawtypes")
	public List<MenuItem> findAllMenu(String selectSystemId) throws ServiceException {
		List<MenuItem> list = new ArrayList<MenuItem>(0);
		try {
			List top = MenuQueryHelper.findAllTopMenu(selectSystemId);
			Iterator iterator = top.iterator();
			while (iterator.hasNext()) {
				MenuItem menuItem = (MenuItem) iterator.next();
				MenuItem menuDto = new MenuItem();

				BeanUtils.copyProperties(menuItem, menuDto);
				list.add(menuDto);
				this.getChildren(list, menuItem, 1, null, selectSystemId);
			}
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("menu find all faild", e);
		}

		return list;
	}

	/**
	 * list parentName
	 * 
	 * @throws ServiceException
	 */
	@SuppressWarnings("rawtypes")
	public List<MenuItem> listParent(String presentName, String selectSystemId) throws ServiceException {
		List<MenuItem> list = new ArrayList<MenuItem>(0);
		try {
			List top = MenuQueryHelper.findAllTopMenu(selectSystemId);
			Iterator iterator = top.iterator();
			while (iterator.hasNext()) {
				MenuItem menuItem = (MenuItem) iterator.next();

				MenuItem menuDto = new MenuItem();
				BeanUtils.copyProperties(menuItem, menuDto);
				list.add(menuDto);
				this.getChildren(list, menuItem, 1, menuItem.getId(), selectSystemId);

			}
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("menu find all faild", e);
		}

		return list;
	}

	/**
	 * @param list
	 *            : topMenuList
	 * @param menuItem
	 * @param step
	 * @param presendName
	 */
	@SuppressWarnings("rawtypes")
	private void getChildren(List<MenuItem> list, MenuItem menuItem, int step, Long presentId, String selectSystemId) throws ServiceException {

		List childrenList = MenuQueryHelper.findMenuByParentId(menuItem.getId(), selectSystemId);
		Iterator iterator = childrenList.iterator();
		while (iterator.hasNext()) {
			MenuItem children = (MenuItem) iterator.next();
			// if (presentId == null ||
			// !children.getParentId().equals(presentId))
			// {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < step; i++) {
				sb.append("&nbsp;&nbsp;&nbsp;");
			}

			if (step != 0) {
				sb.append("|_");
			}

			MenuItem childrenDto = new MenuItem();
			BeanUtils.copyProperties(children, childrenDto);
			childrenDto.setName(sb.toString() + children.getName());
			list.add(childrenDto);
			this.getChildren(list, children, step + 1, children.getId(), selectSystemId);
			// }
		}
	}

	/**
	 * find menuitem by id
	 */
	public MenuItem findMenuItemById(Long id) throws ServiceException {
		try {
			return DaoUtil.get(MenuItem.class, id);
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("find menu by id failed", e);
		}

	}

	/**
	 * create new menu
	 * 
	 * @throws ServiceException
	 */
	public void createMenu(MenuItem menu) throws ServiceException {
		try {
			if (menu.getParentId() != null) {
				MenuItem parentMenu = DaoUtil.get(MenuItem.class, menu.getParentId());
				if (parentMenu != null) {
					menu.setParentName(parentMenu.getName());
				}
			}

			menu.save();
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("add menu failed", e);
		}
	}

	/**
	 * delete menu by id
	 * 
	 * @throws ServiceException
	 */
	public void deleteMenu(Long id) throws ServiceException {
		try {
			MenuItem parentMenu = DaoUtil.get(MenuItem.class, id);
			parentMenu.delete();
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("delete menu failed", e);
		}
	}

	/**
	 * update menu
	 * 
	 * @throws ServiceException
	 */
	public void updateMenu(MenuItem menu) throws ServiceException {
		try {
			menu.update();
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("update menu failed", e);
		}
	}

	/**
	 * 根据父菜单name,sysemtId删除其子菜单
	 */
	public void deleteSubMenu(Long menuId) throws ServiceException {
		MenuItem parentMenu = this.findMenuItemById(menuId);
		if (parentMenu != null) {
			List<MenuItem> subMenuList = MenuQueryHelper.findMenuByParentId(parentMenu.getId(), parentMenu.getSystemCode());
			if (subMenuList != null && subMenuList.size() > 0) {
				for (MenuItem subMenu : subMenuList) {
					subMenu.delete();
				}
			}
		}
	}

	/**
	 * 得到顶层菜单
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MenuItem> listTopMenu(List<String> resources) throws ServiceException {
		List<MenuItem> list = new ArrayList();
		try {
			if (ConfigUtil.isSecurityCheckRequired()) {
				if (!resources.isEmpty()) {
					list = MenuQueryHelper.listTopMenu(resources);
				}
			} else {
				list = MenuQueryHelper.listTopMenu(resources);
			}

		} catch (DataAccessException e) {
			log.error(e, e);
			throw new DataAccessFailureException("This user does not have the authorization", e);
		}
		return list;
	}

	/**
	 * 得到下次菜单
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<MenuItem> listMenuByParentOid(List<String> resources, Long parentId) throws ServiceException {
		return MenuQueryHelper.listMenuByParentOid(resources, parentId);
	}

}

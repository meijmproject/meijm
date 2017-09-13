package com.yh.admin.menu.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.admin.bo.MenuItem;
import com.yh.admin.menu.service.MenuService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.admin.dto.MenuItemDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.TreeNode;

public class MenuFacade {
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	
	/**
	 * 加载下级所有菜单节点
	 * 
	 * @param parentMenuOid
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode<MenuItemDTO>> listMenuTree(List<String> resources, Long parentMenuOid) throws ServiceException {

		TreeNode<MenuItemDTO> root = new TreeNode<MenuItemDTO>();

		root.setChildren((cycleGetChild(resources, parentMenuOid, root)));

		return root.getChildren();
	}

	private List<TreeNode<MenuItemDTO>> cycleGetChild(List<String> resources, Long parentMenuOid, TreeNode<MenuItemDTO> parent) throws ServiceException {
		List<MenuItem> childMenuItems = null;

		if (NumberUtils.isNullOrZero(parentMenuOid)) {
			childMenuItems = menuService.listTopMenu(resources);
		} else {
			childMenuItems = menuService.listMenuByParentOid(resources, parentMenuOid);
		}

		List<TreeNode<MenuItemDTO>> list = new ArrayList<TreeNode<MenuItemDTO>>();

		TreeNode<MenuItemDTO> node = null;
		MenuItemDTO MenuItemDTO = null;
		if (CollectionUtils.isNotEmpty(childMenuItems)) {

			for (MenuItem menuItem : childMenuItems) {
				node = new TreeNode<MenuItemDTO>();
				MenuItemDTO = new MenuItemDTO();
				
				BeanHelper.copyProperties(menuItem, MenuItemDTO);

				node.setEntry(MenuItemDTO);
				node.setId(MenuItemDTO.getId());

				node.setChildren((cycleGetChild(resources, menuItem.getId(), node)));

				list.add(node);
			}

		}

		return list;
	}

}

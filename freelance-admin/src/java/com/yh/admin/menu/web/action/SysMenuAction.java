/*
 * @(#) MenuAction.java        1.00         2006-11-21
 * 
 * Copyright (c) 2006 FEDEX EXPRESS Corporation. All Rights Reserved.
 *
 */
package com.yh.admin.menu.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.admin.dto.MenuItemDTO;
import com.yh.admin.menu.facade.MenuFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.TreeNode;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统菜单
 * @author	zhangqp
 * @version	1.0,	16/08/22
 */
public class SysMenuAction extends BaseAction {
	private MenuFacade menuFacade = (MenuFacade) SpringBeanUtil.getBean("menuFacade");

	/**
	 * 查询菜单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listSysMenuTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			List<TreeNode<MenuItemDTO>> list = menuFacade.listMenuTree(UserContext.getInstance().getResources(), null);
			
			response.getWriter().print(toJSONArray(list).toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询菜单列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询菜单列表失败")));
		}
		
		return null;
	}
	
	private JSONArray toJSONArray(List<TreeNode<MenuItemDTO>> nodes) {
		JSONArray ary = new JSONArray();
		
		if (CollectionUtils.isNotEmpty(nodes)) {
			for (TreeNode<MenuItemDTO> node : nodes) {
				ary.element(toJSON(node));
			}
		}
		
		return ary;
	}

	private JSONObject toJSON(TreeNode<MenuItemDTO> node) {
		JSONObject json = JSONHelper.fromObject(node.getEntry());
		
		json.put("children", toJSONArray(node.getChildren()));
		
		return json;
	}
	
}

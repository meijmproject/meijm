/**
 * 
 */
package com.yh.admin.roles.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.admin.bo.Functions;
import com.yh.admin.bo.Roles;
import com.yh.admin.dto.FunctionsDTO;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.sao.item.SaoItemNodeService;
import org.apache.commons.collections.CollectionUtils;
import com.yh.admin.dto.MtMenuDto;
import com.yh.admin.roles.service.RolesService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.TreeNode;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/12
 */

public class RolesFacade {
	private RolesService rolesService;
	
	private SaoItemNodeService saoItemNodeService;

	public void setSaoItemNodeService(SaoItemNodeService saoItemNodeService) {
		this.saoItemNodeService = saoItemNodeService;
	}

	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	public List<RolesDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		return rolesService.listByCondition(ttb);
	}

	public RolesDTO get(Long roleId) throws ServiceException  {
		return BeanHelper.copyProperties(DaoUtil.get(Roles.class, roleId), RolesDTO.class);
	}
	
	public boolean checkRoleByRoleName(String roleName,RolesDTO rolesDTO) throws ServiceException {
		return rolesService.checkRoleByRoleName(roleName,rolesDTO);
	}
	
	public String findRolesIdByName(String roleName,String roleType) throws ServiceException {
		return rolesService.findRolesIdByName(roleName,roleType);
	}
	public boolean checkRoleByRoleNameAndID(String roleName,long roleId) throws ServiceException {
		return rolesService.checkRoleByRoleNameAndID(roleName,roleId);
	}
	
	public void createRole(RolesDTO rolesDTO) throws ServiceException {
		rolesService.createRole(rolesDTO);
	}
	
	public RolesDTO findRolesByRoleName(String rolesName) throws ServiceException {
		return rolesService.findRolesByRoleName(rolesName);
	}
	
	public RolesDTO findRolesByID(String roleID) throws ServiceException {
		return rolesService.findRolesByID(roleID);
	}
	public void updateRole(RolesDTO rolesDTO) throws ServiceException {
		rolesService.updateRole(rolesDTO);
	}
	
	public void deleteRoleByRoleID(String roleId,String roleType) throws ServiceException {
		rolesService.deleteRoleByRoleID(roleId,roleType);
	}
	/**
	 * 加载下级所有菜单节点
	 * 
	 * @param parentMenuOid
	 * @return
	 * @throws ServiceException
	 */
	public List<TreeNode<FunctionsDTO>> findFunction(Long parentMenuOid, String roleId) throws ServiceException {

		TreeNode<FunctionsDTO> root = new TreeNode<FunctionsDTO>();

		root.setChildren(cycleGetChild(parentMenuOid, root,roleId));

		return root.getChildren();
	}

	private List<TreeNode<FunctionsDTO>> cycleGetChild(Long parentMenuOid, TreeNode<FunctionsDTO> parent,String roleId) throws ServiceException {
		List<Functions> childFunctions = null;

		childFunctions = rolesService.findFunction(parentMenuOid);

		List<TreeNode<FunctionsDTO>> list = new ArrayList<TreeNode<FunctionsDTO>>();

		TreeNode<FunctionsDTO> node = null;
		FunctionsDTO functionsDTO = null;
		if (CollectionUtils.isNotEmpty(childFunctions)) {

			for (Functions functions : childFunctions) {
				node = new TreeNode<FunctionsDTO>();
				functionsDTO = new FunctionsDTO();
				
				BeanHelper.copyProperties(functions, functionsDTO);

				node.setEntry(functionsDTO);
				node.setId(functionsDTO.getFuncId());

				node.setAttribute("checked", rolesService.checkByRoleId(roleId,functionsDTO.getFuncId()));
				node.setChildren(cycleGetChild(functions.getFuncId(), node,roleId));
				list.add(node);
			}

		}

		return list;
	}

	public void updateRoleFunction(String roleId, String funcIds) throws ServiceException{
		rolesService.updateRoleFunction(roleId,funcIds);
	}

	public List<TreeNode<MtMenuDto>> findItemTree(String parentMenuCode, String roleId,String menuType ) throws ServiceException {
		TreeNode<MtMenuDto> root = new TreeNode<MtMenuDto>();

		root.setChildren(cycleGetChildItem(parentMenuCode,root,roleId,menuType));

		return root.getChildren();
	}

	private List<TreeNode<MtMenuDto>> cycleGetChildItem(String parentMenuCode,
			TreeNode<MtMenuDto> root, String roleId,String menuType) throws ServiceException {
		List<MtMenuDto> childMtMenus = null;
		childMtMenus = saoItemNodeService.findItem(parentMenuCode,menuType);

		List<TreeNode<MtMenuDto>> list = new ArrayList<TreeNode<MtMenuDto>>();

		TreeNode<MtMenuDto> node = null;
		if (CollectionUtils.isNotEmpty(childMtMenus)) {

			for (MtMenuDto mtMenuDto : childMtMenus) {
				node = new TreeNode<MtMenuDto>();

				node.setEntry(mtMenuDto);
				node.setId(mtMenuDto.getMenuCode());

				node.setAttribute("checked", saoItemNodeService.checkByRoleId(roleId,mtMenuDto.getMenuCode()));
				node.setChildren(cycleGetChildItem(mtMenuDto.getMenuCode(), node,roleId,menuType));
				list.add(node);
			}

		}

		return list;
	}

	public void updateRoleItemNode(String roleId, String menuCode,String flag) throws ServiceException {
		rolesService.updateRoleItemNode(roleId,menuCode,flag);
	}
}

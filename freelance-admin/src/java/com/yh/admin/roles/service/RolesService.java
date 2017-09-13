package com.yh.admin.roles.service;

import java.util.List;

import com.yh.admin.bo.Functions;
import com.yh.admin.bo.RoleFunc;
import com.yh.admin.bo.RoleMenu;
import com.yh.admin.bo.Roles;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.roles.queryhelper.RolesQueryHelper;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class RolesService {

	public List<RolesDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		
		return RolesQueryHelper.listByCondition(ttb);
	}

	public List<Roles> listByUserId(String userId, String sytemId, String roleType) {
		
		return null;
	}
	
	public boolean checkRoleByRoleName(String roleName,RolesDTO rolesDTO) throws ServiceException {
		if(RolesQueryHelper.checkRoleByRoleName(roleName, rolesDTO.getRoleType())){
			Roles roles = new  Roles();		
			BeanHelper.copyProperties(rolesDTO,roles);
			roles.setCreatedByCode(UserContext.getLoginUserID());
			roles.setCreatedDate(DateUtil.now());
			roles.save();
			return true;
		}
		return false;
	}
	
	
	public String findRolesIdByName(String roleName,String roleType) throws ServiceException {
		
		return RolesQueryHelper.findRolesIdByName(roleName,roleType);
	}
	
	public boolean checkRoleByRoleNameAndID(String roleName,long roleId) throws ServiceException {
		
		return RolesQueryHelper.checkRoleByRoleNameAndID(roleName,roleId);
	}
	
	public void createRole(RolesDTO rolesDTO) throws ServiceException {
		Roles roles = new  Roles();		
		BeanHelper.copyProperties(rolesDTO,roles);
		roles.setCreatedByCode(UserContext.getLoginUserID());
		roles.setCreatedDate(DateUtil.now());
		roles.save();
	}
	
	public void updateRole(RolesDTO rolesDTO) throws ServiceException {
		Roles roles = new  Roles();		
		BeanHelper.copyProperties(rolesDTO,roles);
		roles.setUpdatedByCode(UserContext.getLoginUserID());
		roles.setUpdatedDate(DateUtil.now());
		roles.update();
	}
	
	
	public RolesDTO findRolesByRoleName(String roleName) throws ServiceException {
		
		return RolesQueryHelper.findRolesByRoleName(roleName);
	}
	
	public RolesDTO findRolesByID(String roleID) throws ServiceException {
		
		return RolesQueryHelper.findRolesByID(roleID);
	}
	
	public void deleteRoleByRoleID(String roleId,String roleType) throws ServiceException {
		RolesQueryHelper.deleteRoleByRoleID(roleId, roleType);
	}

	public List<Functions> findFunction(Long funcId) throws ServiceException  {
		return RolesQueryHelper.findFunction(funcId);
	}

	public boolean checkByRoleId(String roleId,Long funcId) throws ServiceException {
		return RolesQueryHelper.checkByRoleId(roleId,funcId);
	}

	public void deleteRoleFunction(String roleId) throws ServiceException{
		DaoUtil.executeUpdate(" delete from RoleFunc rf where rf.roleId ="+roleId);
	}

	public void deleteRoleItemNode(String roleId,String flag) throws ServiceException {
		DaoUtil.executeUpdate(" delete from RoleMenu rf where rf.roleId ="+roleId+" and SUBSTRING(menu_code,1,1) = '"+flag+"'");
	}

	public void updateRoleFunction(String roleId, String funcIds) throws ServiceException {
		this.deleteRoleFunction(roleId);
		if(StringUtils.isNotEmpty(funcIds))
		{
			String[] funcIdStr=funcIds.split(",");
			for(int i=0;i<funcIdStr.length;i++){
				RoleFunc roleFunc=new RoleFunc();
				roleFunc.setRoleId(Long.valueOf(roleId));
				roleFunc.setFuncId(Long.valueOf(funcIdStr[i]));
				roleFunc.save();
			}
		}
	}

	public void updateRoleItemNode(String roleId, String menuCode,String flag) throws ServiceException {
		this.deleteRoleItemNode(roleId,flag);
	 if(StringUtils.isNotEmpty(menuCode)){
		String[] menuCodeStr=menuCode.split(",");
		for(int i=0;i<menuCodeStr.length;i++){
			RoleMenu roleItemNode=new RoleMenu();
			roleItemNode.setRoleId(Long.valueOf(roleId));
			roleItemNode.setMenuCode(menuCodeStr[i]);
			roleItemNode.save();
		}
	  }else{
		  return;
	  }
	}
}

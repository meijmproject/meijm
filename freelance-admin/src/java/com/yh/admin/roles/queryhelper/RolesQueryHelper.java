/**
 * 
 */
package com.yh.admin.roles.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.admin.bo.Functions;
import com.yh.admin.bo.RoleDataAuth;
import com.yh.admin.bo.RoleFunc;
import com.yh.admin.bo.RoleMenu;
import com.yh.admin.bo.Roles;
import com.yh.admin.bo.SystemPosition;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.util.AuthConstants;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/12
 */

public class RolesQueryHelper {
	/**
	 * 角色信息查询（roleType：0 为功能角色 ，1位数据角色）
	 * */
	public static List<RolesDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from Roles r where 1=1 ");
		
		if(StringUtils.isNotEmpty(ttb.getCondition().get("roleType"))) {
			hql.append(" and r.roleType = '").append(ttb.getCondition().get("roleType")).append("' ");
		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("roleName"))){
			hql.append(" and r.roleName like '%").append(ttb.getCondition().get("roleName")).append("%' ");
		}
		ttb.setTotal(DaoUtil.countByCondition(new StringBuilder().append("select count(*) ").append(hql).toString(), params));
		
		return BeanHelper.copyProperties(DaoUtil.listByCondition(new StringBuilder().append(hql).append(" order by r.updatedDate desc").toString(), params, ttb.getPage(), ttb.getPageSize()), RolesDTO.class);
	}
	/**
	 * 新增时检查角色的名称是否重复
	 * @param roleName
	 * */
	public static boolean checkRoleByRoleName(String roleName,String roleType) throws ServiceException {
	
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleName", roleName);
		params.put("roleType", roleType);
		List<Roles> rolesBo= DaoUtil.find("from Roles r where r.roleName = :roleName and r.roleType = :roleType", params);
		if(CollectionUtils.isEmpty(rolesBo)){
			return true;
		}
		return false;
	}
	/**
	 * 查看角色信息
	 * @param roleID
	 * */
	public static RolesDTO findRolesByID(String roleID) throws ServiceException {
		RolesDTO rolesDTO=new RolesDTO();
		Roles roles =DaoUtil.get(Roles.class, Long.valueOf(roleID.trim()));
		BeanHelper.copyProperties(roles,rolesDTO);
		return rolesDTO;
	}
	/**
	 * 角色修改时的重名检查
	 * @param roleName
	 * @param roleId
	 * */
	public static boolean checkRoleByRoleNameAndID(String roleName,long roleId) throws ServiceException {
		
		List<Roles> rolesBo= DaoUtil.find("from Roles r where r.roleName = ? ",roleName);
		if(!CollectionUtils.isEmpty(rolesBo)){
			for(Roles roles:rolesBo){
				if(roles.getRoleId()==roleId){
					return true;
				}
			}
		}else{
			return true;
		}
		return false;
	}
	/**
	 * 查询角色信息
	 * @param roleName
	 * */
	public static RolesDTO findRolesByRoleName(String roleName)  throws ServiceException {
		List<Roles> rolesBo= DaoUtil.find("from Roles r where r.roleName = ? ",roleName.trim());
		RolesDTO rolesDTO =new RolesDTO();
		if(!CollectionUtils.isEmpty(rolesBo)){
			  BeanHelper.copyProperties(rolesDTO,rolesBo.get(0));
		   return  rolesDTO;
		}
		return null;
	}
	
	
	
	/**
	 *删除角色 
	 *@param roleId
	 *@param roleType
	 * */
	public static void deleteRoleByRoleID(String roleId,String roleType) throws ServiceException {
		
		
		if(AuthConstants.ROLE_TYPE_DATA.equals(roleType)){
			//岗位角色ID清空
			List<SystemPosition> dataRolePositions=DaoUtil.find(" from SystemPosition sp where sp.dataRoleId = ?",Long.valueOf(roleId.trim()));
			for(SystemPosition systemPosition:dataRolePositions){
				systemPosition.setDataRoleId(null);
				systemPosition.update();
			}
			//删除数据角色授权信息
			List<RoleDataAuth> roleDataAuthList = DaoUtil.find(" from  RoleDataAuth ra where ra.roleId=?",Long.valueOf(roleId.trim()));
			for(RoleDataAuth roleDataAuth : roleDataAuthList){
				roleDataAuth.delete();
			}
			
		}else if(AuthConstants.ROLE_TYPE_FUNCTION.equals(roleType)){
			//岗位信息中的id置空
			List<SystemPosition> funcRolePositions=DaoUtil.find(" from SystemPosition sp where sp.functionRoleId=?",Long.valueOf(roleId.trim()));
			for(SystemPosition systemPosition:funcRolePositions){
				systemPosition.setFunctionRoleId(null);
				systemPosition.update();
			}
			//功能授权删除
			List<RoleFunc> roleFuncList = DaoUtil.find(" from RoleFunc rf where rf.roleId=?",Long.valueOf(roleId.trim()));
			for(RoleFunc roleFunc : roleFuncList){
				roleFunc.delete();
			}
			//事项环节和环节事项授权删除
			List<RoleMenu> roleMenulist = DaoUtil.find(" from RoleMenu rin where rin.roleId=?",Long.valueOf(roleId.trim()));
			for(RoleMenu roleMenu : roleMenulist) {
				roleMenu.delete();
			}
			
		}
		//角色删除
		Roles roles=DaoUtil.get(Roles.class, Long.valueOf(roleId.trim()));
		if(roles!=null){
			List<RoleMenu> roleItemNodeList=DaoUtil.find(" from RoleMenu rn where rn.roleId = ?",roles.getRoleId());
			for( RoleMenu roleItemNode :roleItemNodeList ){
				roleItemNode.delete();
			}
			roles.delete();
		}
	}
	/**
	 * 根据角色名称查询角色ID
	 * */
	public static String findRolesIdByName(String rolesName,String roleType) throws ServiceException {
		List<Roles> rolesBo=DaoUtil.find(" from Roles r where r.roleName=? and r.roleType=?",rolesName.trim(),roleType);
		if(CollectionUtils.isNotEmpty(rolesBo)){
			return  rolesBo.get(0).getRoleId().toString();
		}
		return null;
	}
	
	public static List<Functions> findFunction(Long funcId) throws ServiceException {
		StringBuilder hql=new StringBuilder();
		hql.append(" from Functions f");
		if(funcId==null){
			hql.append(" where f.fatherId is null");
		}else{
			hql.append(" where f.fatherId = ");
			hql.append(funcId);
		}
		List<Functions> list=DaoUtil.find(hql.toString());
		return list;
	}
	public static boolean checkByRoleId(String roleId,Long funcId) throws ServiceException {
		List<RoleFunc> list=DaoUtil.find(" from RoleFunc r where r.roleId=? and r.funcId=?",Long.valueOf(roleId),funcId);
		return CollectionUtils.isNotEmpty(list);
	}
}

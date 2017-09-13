package com.yh.admin.roles.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yh.admin.bo.RoleDataAuth;
import com.yh.admin.dto.RoleDataAuthDTO;
import com.yh.admin.roles.queryhelper.RoleDataAuthQueryHelper;
import com.yh.admin.util.AuthConstants;
import com.yh.platform.core.exception.ServiceException;

/**
 *@description	
 *
 *@author		zhangqp
 *@created		16/01/14
 *@version		1.0
 */
public class RoleDataAuthService {

//	public void save(RoleDataAuth bo) throws ServiceException {
//		
//	}
	
//	/**
//	 * 查询用户权限节点
//	 * @param userCode
//	 * @return
//	 * @throws ServiceException
//	 */
//	public List<String> listUserRoleDataCode(String userId,String authLevel,String authType,String unitStatus)throws ServiceException
//	{
//		return RoleDataAuthQueryHelper.listUserRoleDataCode(userId, authLevel, authType,unitStatus);
//	}

//	public void updateRoleData(List<String> roleRight, Long roleId, String authType) throws ServiceException {
//		// 删除原节点
//		List<RoleDataAuth> listData = RoleDataAuthQueryHelper.listByRoleIdAndAuthType(roleId, authType);
//		if (listData != null && listData.size() > 0) {
//			for (RoleDataAuth roleData : listData) {
//				RoleDataAuthQueryHelper.deleteAllRoleDataAuthByOid(roleData.getRoleDataAuthOid());
//			}
//		}
//		// 创建新节点
//		if (roleRight != null && roleRight.size() > 0) {
//			for (String itemCode : roleRight) {
//				RoleDataAuth roleDataAuth = new RoleDataAuth();
//				roleDataAuth.setAuthCode(itemCode);
//				roleDataAuth.setAuthType(authType);
//				roleDataAuth.setAuthLevel(AuthConstants.AUTH_LEVEL_EDIT);
//				roleDataAuth.setRoleId(roleId);
//				roleDataAuth.save();
//			}
//		}
//	}

	public void create(String unitCode, Long roleDataOid) throws ServiceException {
		RoleDataAuth roleDataAuth = new RoleDataAuth();
		roleDataAuth.setAuthCode(unitCode);
		roleDataAuth.setAuthLevel(AuthConstants.AUTH_LEVEL_SEARCH);
		roleDataAuth.setAuthType(AuthConstants.AUTH_TYPE_UNIT);
		roleDataAuth.setRoleId(roleDataOid);
		roleDataAuth.setIsOnlyOwn(AuthConstants.IS_ONLY_OWN_N);
		roleDataAuth.save();
//		roleDataAuth = new RoleDataAuth();
//		roleDataAuth.setAuthCode(unitCode);
//		roleDataAuth.setAuthLevel(AuthConstants.AUTH_LEVEL_EDIT);
//		roleDataAuth.setAuthType(AuthConstants.AUTH_TYPE_UNIT);
//		roleDataAuth.setRoleId(roleDataOid);
//		roleDataAuth.setIsOnlyOwn(AuthConstants.IS_ONLY_OWN_N);
//		roleDataAuth.save();
	}

//	public List<RoleDataAuth> list(Long unitOid, String systemId) throws ServiceException {
//		return RoleDataAuthQueryHelper.list(unitOid, systemId);
//	}

//	public void remove(RoleDataAuth rr) throws ServiceException {
//		roleDataAuthDao.remove(rr.getRoleId());
//	}

	/**
	 * 根据角色查询对应数据权限
	 * 
	 * @param roleOid
	 * @return List
	 */
//	public List<RoleDataAuth> listByRoleIdAndAuthType(Long roleOid, String authType) throws ServiceException {
//		return RoleDataAuthQueryHelper.listByRoleIdAndAuthType(roleOid, authType);
//	}

	/**
	 * 查询用户工作流数据权限
	 * 
	 * @param userId
	 * @param authType
	 *            数据类型
	 */
//
//	public List<RoleDataAuth> listByUserIdAndAuthType(Long userid, String authType) throws ServiceException {
//		return RoleDataAuthQueryHelper.listByUserIdAndAuthType(userid, authType);
//	}

	/**
	 * 查询用户工作流节点权限
	 * 
	 * @param userId
	 */
//	public List<Long> listDataWorkflowByUserId(Long userid) throws ServiceException {
//		List<RoleDataAuth> listAuth = listByUserIdAndAuthType(userid, AuthConstants.AUTH_TYPE_WORKFlOW);
//		List<Long> list = new ArrayList<Long>();
//		if (listAuth != null && listAuth.size() > 0) {
//			for (RoleDataAuth roleAuthData : listAuth) {
//				list.add(new Long(roleAuthData.getAuthCode()));
//			}
//
//		}
//		return list;
//	}

//	public List<RoleDataAuth> listByRoleOids(List<Long> dataRoleIds) throws ServiceException {
//		return RoleDataAuthQueryHelper.listByRoleOids(dataRoleIds);
//	}

	public String updateUserAuth(String userId, Long roleId, Long systemPositionOid) throws ServiceException {
		if (StringUtils.isNotEmpty(userId)) {
			return RoleDataAuthQueryHelper.updateUserAuth(userId);
		} else if (roleId != null && roleId.longValue() != 0) {
			return RoleDataAuthQueryHelper.updateUserAuthByRole(roleId);
		} else if (systemPositionOid != null && systemPositionOid.longValue() != 0) {
			return RoleDataAuthQueryHelper.updateUserAuthBySystemPosition(systemPositionOid);
		} else {
			return RoleDataAuthQueryHelper.updateUserAuth();
		}
	}

	/**
	 * 查询角色对应的 权限
	 * @param roleId
	 * @param authType
	 * @return
	 * @throws ServiceException
	 */
	public List<String> listRoleDataCode(Long roleId, String authType) throws ServiceException {
		
		return RoleDataAuthQueryHelper.listRoleDataCode(roleId, authType, AuthConstants.AUTH_LEVEL_SEARCH);
	}

	/**
	 * 是否具有全部权限（%）
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasAllUnitAuth(String userId) throws ServiceException {
		
		return RoleDataAuthQueryHelper.hasAuthCode(userId, Arrays.asList(AuthConstants.ALL_UNIT_AUTH_CODE), null, null);
	}

	/**
	 * 根据角色删除该角色所有的机构数据权限
	 * 
	 * @param RoleOid
	 * @throws ServiceException
	 */
	public void deleteRoleDataAuthByRoleOid(Long RoleOid, String authLevel) throws ServiceException {
		
		RoleDataAuthQueryHelper.deleteRoleDataAuthByRoleOid(RoleOid, authLevel);
	}

	/**
	 * 查询角色已授权的单位编码、名称
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleDataAuthDTO> listRoleUnitCodeAuth(Long roleId) throws ServiceException {
		
		return RoleDataAuthQueryHelper.listRoleUnitCodeAuth(roleId, AuthConstants.AUTH_LEVEL_SEARCH);
	}
	
}

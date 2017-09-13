package com.yh.admin.roles.facade;

import java.util.ArrayList;
import java.util.List;
import com.yh.admin.bo.RoleDataAuth;
import com.yh.admin.dto.RoleDataAuthDTO;
import com.yh.admin.roles.service.RoleDataAuthService;
import com.yh.admin.sao.unit.SaoOrgInfoService;
import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.admin.util.AdminConstants;
import com.yh.admin.util.AuthConstants;
import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description
 *
 * @author cheny
 * @created 17/02/21
 * @version 1.0
 */
public class RoleDataAuthFacade {

	private RoleDataAuthService roleDataAuthService;
	private SaoOrgInfoService saoOrgInfoService;
	public void setSaoOrgInfoService(SaoOrgInfoService saoOrgInfoService) {
		this.saoOrgInfoService = saoOrgInfoService;
	}

	public void setRoleDataAuthService(RoleDataAuthService roleDataAuthService) {
		this.roleDataAuthService = roleDataAuthService;
	}

	
	/**
	 * 添加用户节点查看，操作权限
	 * 
	 * @param userAuth
	 * @throws ServiceException
	 */
	public void createRoleNodeAuthList(RoleDataAuthDTO dto) throws ServiceException {
		// 单位节点list
		List<String> orgOidList = dto.getOrgOidList();
		// 系统类别节点list
		//List<String> personTypeList = dto.getPersonTypeList();
		// 是否包含本身单位节点List
		List<String> onlyOwnList = dto.getOnlyOwnList();

		Long roleId = dto.getRoleId();
		

		// 授予最大权限（比如授予整个区的权限）
		if (dto.isAuthAllUnitCode()) {
			// 删除 原来的所有机构权限
			roleDataAuthService.deleteRoleDataAuthByRoleOid(Long.valueOf(roleId), AuthConstants.AUTH_LEVEL_SEARCH);

			// 授予查看权限
			RoleDataAuth bo = new RoleDataAuth();
			bo.setAuthCode(AuthConstants.ALL_UNIT_AUTH_CODE);//所有单位
			bo.setAuthType(AuthConstants.AUTH_TYPE_UNIT);
			bo.setAuthLevel(AuthConstants.AUTH_LEVEL_SEARCH);
			bo.setRoleId(Long.valueOf(roleId));
			bo.setIsOnlyOwn(AuthConstants.IS_ONLY_OWN_N);
			
			bo.save();
		} 
		// 普通授权
		else {
			
			// 删除 原来所有机构权限
			roleDataAuthService.deleteRoleDataAuthByRoleOid(Long.valueOf(roleId), AuthConstants.AUTH_LEVEL_SEARCH);
			
			for (String orgOid : orgOidList) {
				RoleDataAuth bo = new RoleDataAuth();

				bo.setIsOnlyOwn(onlyOwnList.contains(orgOid) ? AuthConstants.IS_ONLY_OWN_Y : AuthConstants.IS_ONLY_OWN_N);

				bo.setAuthCode(orgOid);
				bo.setAuthType(AuthConstants.AUTH_TYPE_ORG);
				bo.setAuthLevel(AuthConstants.AUTH_LEVEL_SEARCH);
				bo.setRoleId(roleId);
				
				bo.save();
			}
		}
		/*---------------------------纬度----------------------------------*/
		/*RoleDataAuth bo = null;
		for (String personTypeCode : personTypeList) {
			bo = new RoleDataAuth();
			bo.setAuthCode(personTypeCode.trim());
			bo.setAuthType(AuthConstants.AUTH_TYPE_PERSON);
			bo.setAuthLevel(AuthConstants.AUTH_LEVEL_SEARCH);
			bo.setRoleId(Long.valueOf(roleId));
			bo.save();
		}*/
	}
	
	/**
	 * 查询用户工作流数据权限
	 * 
	 * @param userId
	 * @param authType
	 *            数据类型
	 */


	public String updateUserAuth(String userId, Long roleId, Long systemPositionOid) throws ServiceException {
		return roleDataAuthService.updateUserAuth(userId, roleId, systemPositionOid);
	}

	public String updateUserAuth() throws ServiceException {
		return roleDataAuthService.updateUserAuth(null, null, null);
	}



	/**
	 * 是否有所有单位的权限（是否包含%）
	 * @param userId
	 * @return
	 * @throws ServiceException 
	 */
	public boolean hasAllUnitAuth(String userId) throws ServiceException {
		
		return roleDataAuthService.hasAllUnitAuth(userId);
	}
	
	/**
	 * 查询角色已授权的单位（含名称）
	 * @param roleId
	 * @return
	 * @throws ServiceException 
	 */
	public List<RoleDataAuthDTO> listRoleUnitCodeAuth (Long roleId) throws ServiceException {
		return roleDataAuthService.listRoleUnitCodeAuth(roleId);
	}

	public List<SaoAdminOrgDTO> listUnitByCondition(TableTagBean ttb) throws ServiceException {
		
		return saoOrgInfoService.listByCondition(ttb);
	}

	public List<RoleDataAuthDTO> listPersonType(Long roleId) throws ServiceException {
		List<String> authCodes = roleDataAuthService.listRoleDataCode(roleId, AuthConstants.AUTH_TYPE_PERSON);
		// 人员类别
		List<DicItem> personTypeList = DicHelper.listSubItem(AdminConstants.YHRS0010);//单位性质
		
		RoleDataAuthDTO dto = null;
		List<RoleDataAuthDTO> list = new ArrayList<RoleDataAuthDTO>(personTypeList.size());
		for (DicItem bo : personTypeList) {
			dto = new RoleDataAuthDTO();
			
			dto.setAuthCode(bo.getDicItemCode());
			dto.setAuthCodeName(bo.getDicItemName());
			
			if (authCodes != null && authCodes.contains(bo.getDicItemCode())) {
				dto.setChecked(true);
			}
			
			list.add(dto);
		}
		
		return list;
	}


}

/**
 * 
 */
package com.yh.hr.res.sao.unit.impl;

import java.util.List;

import com.yh.admin.auth.service.UserAuthorizationService;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员单位权限接口
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class SaoUserUnitAuthorizationServiceImpl implements SaoUserUnitAuthorizationService {

	private UserAuthorizationService userAuthorizationService;
	
	public void setUserAuthorizationService(UserAuthorizationService userAuthorizationService) {
		this.userAuthorizationService = userAuthorizationService;
	}
	
	/* (non-Javadoc)
	 * @see SaoUserUnitAuthorizationService#findAuthorityList(java.lang.String)
	 */
	public List<String> findAuthorityList(String userId) throws ServiceException {
		return userAuthorizationService.findAuthorityList(userId);
	}

	public void updateAuthorityByUserId(Long taskOid)
			throws ServiceException {
		 userAuthorizationService.updateAuthorityByUserId(taskOid);
	}

}

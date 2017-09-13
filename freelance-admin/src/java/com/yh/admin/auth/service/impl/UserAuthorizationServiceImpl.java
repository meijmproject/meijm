/**
 * 
 */
package com.yh.admin.auth.service.impl;

import java.util.List;

import com.yh.admin.auth.service.UserAuthorizationService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.admin.auth.queryhelper.UserUnitAuthQueryHelper;
import com.yh.admin.roles.queryhelper.RoleDataAuthQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/16
 */

public class UserAuthorizationServiceImpl implements UserAuthorizationService {

	public List<String> findAuthorityList(String userId)
			throws ServiceException {
		return UserUnitAuthQueryHelper.findAuthorityList(userId);
	}

	public void updateAuthorityByUserId(Long taskOid) throws ServiceException {
		List<String> userList = UserUnitAuthQueryHelper
				.findUserIdByTaskOid(taskOid);
		if (CollectionUtils.isNotEmpty(userList)) {
			if(!userList.contains(UserContext.getLoginUserID())){
				userList.add(UserContext.getLoginUserID());
			}
			for (String userId : userList) {
				List<String> list = UserUnitAuthQueryHelper
						.findSystemPositionIdByUserId(userId);
				if (CollectionUtils.isNotEmpty(list)) {
					for (String str : list) {
						RoleDataAuthQueryHelper
								.updateUserAuthBySystemPosition(Long
										.valueOf(str));
					}
				}
			}
		}
	}

	public void updateUserAuth() throws ServiceException {
		RoleDataAuthQueryHelper.updateUserAuth();
	}
}


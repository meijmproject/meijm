/**
 * 
 */
package com.yh.admin.auth.service;

import java.util.List;

import com.yh.platform.core.exception.ServiceException;

/**
 * 获取人员权限接口
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public interface UserAuthorizationService {
	
	/**
	 * 查询用户所有有权限的单位id（已去重，转为String类型）
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<String> findAuthorityList(String userId) throws ServiceException;

	public void updateAuthorityByUserId(Long taskOid) throws ServiceException;
	
	public void updateUserAuth() throws ServiceException;

}

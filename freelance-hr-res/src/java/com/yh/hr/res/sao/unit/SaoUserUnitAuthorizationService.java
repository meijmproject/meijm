/**
 * 
 */
package com.yh.hr.res.sao.unit;

import java.util.List;

import com.yh.platform.core.exception.ServiceException;

/**
 * 人员单位权限接口
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public interface SaoUserUnitAuthorizationService {

	/**
	 * 人员权限单位id集合
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<String> findAuthorityList(String userId) throws ServiceException;
	/**
	 * 根据userid更新单位权限
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public void updateAuthorityByUserId(Long taskOid) throws ServiceException;
	
}

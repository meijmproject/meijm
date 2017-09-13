/**
 * 
 */
package com.yh.admin.users.queryhelper;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/20
 */

public class UserAgentQueryHelper {

	/**
	 * 解除岗位上所有代理用户的对应关系
	 * @param systemPositionOid
	 * @throws DataAccessFailureException
	 */
	public static void deleteBySystemPositionOid(Long systemPositionOid) throws DataAccessFailureException  {
		if (NumberUtils.isNullOrZero(systemPositionOid)) {
			return;
		}
		
		DaoUtil.executeUpdate("delete from UserAgent ua where ua.systemPositionOid = " + systemPositionOid);
	}
	
}

/**
 * 
 */
package com.yh.admin.auth.queryhelper;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/25
 */

public class UserOrgAuthQueryHelper {
	/**
	 * 删除岗位对应的所有权限
	 * @param systemPositionOid
	 * @throws DataAccessFailureException
	 */
	public static void deleteBySystemPositionOid(Long systemPositionOid) throws DataAccessFailureException  {
		if (NumberUtils.isNullOrZero(systemPositionOid)) {
			return;
		}
		
		DaoUtil.executeUpdate("delete from UserOrgAuth uua where uua.systemPositionOid = " + systemPositionOid);
	}
	
}

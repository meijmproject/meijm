/**
 * 
 */
package com.yh.admin.auth.queryhelper;

import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/25
 */

public class UserUnitAuthQueryHelper {

	public static List<String> findAuthorityList(String userId) throws ServiceException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct CAST(org_oid AS char) from YHB_USER_ORG_AUTH uua where user_id = '").append(userId).append("' ");
		
		return DaoUtil.findWithSQL(sql.toString());
	}
	
	/**
	 * 删除岗位对应的所有权限
	 * @param systemPositionOid
	 * @throws DataAccessFailureException
	 */
	public static void deleteBySystemPositionOid(Long systemPositionOid) throws DataAccessFailureException  {
		if (NumberUtils.isNullOrZero(systemPositionOid)) {
			return;
		}
		
		DaoUtil.executeUpdate("delete from UserUnitAuth uua where uua.systemPositionOid = " + systemPositionOid);
	}
	
  public static List<String> findSystemPositionIdByUserId(String userId) throws ServiceException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct to_char(system_position_oid) from yhb_user_sp  where user_id = '").append(userId).append("' ");
		
		return DaoUtil.findWithSQL(sql.toString());
	}
  public static List<String> findUserIdByTaskOid(Long taskOid) throws ServiceException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct to_char(updated_by_code) from yhc_bt_task_item  where task_oid = ").append(taskOid);
		
		return DaoUtil.findWithSQL(sql.toString());
	}
}

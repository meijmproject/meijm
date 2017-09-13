/**
 * 
 */
package com.yh.admin.roles.queryhelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.yh.admin.dto.RoleDataAuthDTO;
import com.yh.admin.util.AuthConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/09/13
 */

public class RoleDataAuthQueryHelper {
	
	private static Log log = LogFactory.getLog(RoleDataAuthQueryHelper.class);

	
	/**
	 * 查询角色已授权的单位编码、名称
	 * @param roleId
	 * @param authLevelSearch
	 * @param object
	 * @throws ServiceException 
	 */
	public static List<RoleDataAuthDTO> listRoleUnitCodeAuth(Long roleId, String authLevel, String... unitStatus) throws ServiceException {
		StringBuilder sql = new StringBuilder();

		sql.append("select jrda.role_data_auth_oid, ");
		sql.append("       jrda.role_id, ");
		sql.append("       jrda.auth_code, ");
		sql.append("       jrda.auth_level, ");
		sql.append("       jrda.auth_type, ");
		sql.append("       jrda.is_only_own, ");
		sql.append("        juo.org_status, ");
		sql.append("       juo.org_name ,");
		sql.append("       juo.org_oid ");
		sql.append("  from yhb_role_data_auth jrda,yhc_ut_org juo ");
		sql.append(" where jrda.auth_type = '8' ");
		sql.append("   and jrda.auth_code = juo.org_oid ");
		sql.append("   and jrda.role_id = ").append(roleId);
		
		/*if (!ArrayUtils.isEmpty(unitStatus)) {
			sql.append(" and juu.unit_status in(").append(StringUtil.joinWrap(unitStatus)).append(") ");
		}*/
		
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], RoleDataAuthDTO>() {

			public RoleDataAuthDTO getValue(Object[] src) throws ServiceException {
				RoleDataAuthDTO dto = new RoleDataAuthDTO();
				
				dto.setRoleDataAuthOid(DataConverUtils.toLong(src[0]));
				dto.setRoleId(DataConverUtils.toLong(src[1]));
				dto.setAuthCode(DataConverUtils.toString(src[2]));
				dto.setAuthLevel(DataConverUtils.toString(src[3]));
				dto.setAuthType(DataConverUtils.toString(src[4]));
				dto.setIsOnlyOwn(DataConverUtils.toString(src[5]));
				dto.setAuthCodeName(DataConverUtils.toString(src[7]));
				dto.setOrgStatus(DataConverUtils.toString(src[6]));
				dto.setOrgOid(DataConverUtils.toLong(src[8]));
				return dto;
			}
			
		});
	}

	/**
	 * 查询角色对应的 权限
	 * 
	 * @param userCode
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<String> listRoleDataCode(Long roleId, String authType, String authLevel) throws DataAccessFailureException {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select rda.authCode from RoleDataAuth rda where rda.roleId = ").append(roleId);
		
		if (StringUtils.isNotEmpty(authType)) {
			hql.append(" and rda.authType = '").append(authType).append("' ");
		}
		
		if (StringUtils.isNotEmpty(authLevel)) {
			hql.append(" and rda.authLevel = '").append(authLevel).append("' ");
		}
		
		return DaoUtil.find(hql.toString());
	}

	/**
	 * 根据角色删除该角色所有的机构数据权限
	 * 
	 * @param RoleOid
	 * @throws DataAccessFailureException 
	 */
	public static void deleteRoleDataAuthByRoleOid(Long RoleOid, String authLevel) throws DataAccessFailureException {
		StringBuilder hql = new StringBuilder();
		hql.append(" delete RoleDataAuth r where ");
		//hql.append("  r.authLevel = '").append(authLevel).append("' and");
		hql.append("  r.roleId = ").append(RoleOid);
		hql.append( " and r.authType in(")
		   .append(StringUtil.joinWrap(AuthConstants.AUTH_TYPE_ORG)).append(")");
		DaoUtil.executeUpdate(hql.toString());
	}


	/**
	 * 根据角色和授权类型查询数据权限
	 * 
	 * @param roleId
	 * @param authType
	 * @throws DataAccessFailureException 
	 */

	/**
	 * 查询用户工作流数据权限
	 * 
	 * @param userId
	 * @param authType
	 *            数据类型
	 * @throws DataAccessFailureException 
	 */

	public static String updateUserAuth(String userId) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量更新某人员单位数据权限。人员ID：" + userId + "，开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			String proc = "{call updateUserAuth(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.setString(1, userId);
			cstmt.execute();
			cstmt.close();
			log.info("执行存储过程批量更新某人员单位数据权限。人员ID：" + userId + "，结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新某人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}

	/**
	 * 更新所有用户
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static String updateUserAuth() throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量更新所有人员单位数据权限。开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			
			String proc = "{call updateAllUserAuth()}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.execute();
			cstmt.close();
			
			log.info("执行存储过程批量更新所有人员单位数据权限。结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新所有人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}

	public static String updateUserAuthByRole(Long roleId) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量更新某角色下所有人员单位数据权限。角色ID：" + roleId + "，开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			
			String proc = "{call updateUserAuthByRole(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.setLong(1, roleId);
			cstmt.execute();
			cstmt.close();
			
			log.info("执行存储过程批量更新某角色下所有人员单位数据权限。角色ID：" + roleId + "，结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新某角色下所有人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}

	public static String updateUserAuthBySystemPosition(Long systemPositionOid) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量更新某岗位下所有人员单位数据权限。岗位ID：" + systemPositionOid + "，开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			
			String proc = "{call updateUserAuthBySystemPosition(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.setLong(1, systemPositionOid);
			cstmt.execute();
			cstmt.close();
			
			log.info("执行存储过程批量更新某岗位下所有人员单位数据权限。岗位ID：" + systemPositionOid + "，结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新某岗位下所有人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}
	
	private static void close(Connection conn) throws DataAccessFailureException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Connection失败", e);
		}
	}
	
	private static void close(Statement stmt) throws DataAccessFailureException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Statement失败", e);
		}
	}

	public static void deleteAllRoleDataAuthByOid(Long roleDataAuthOid) throws DataAccessFailureException {
		if (NumberUtils.isNullOrZero(roleDataAuthOid)) {
			return;
		}
		
		DaoUtil.executeUpdate("delete from RoleDataAuth ra where ra.roleDataAuthOid = " + roleDataAuthOid);
	}

	/**
	 * 是否具有指定权限
	 * @param userId
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static boolean hasAuthCode(String userId, List<String> authCodes, String authType, String authLevel) throws DataAccessFailureException {
		
		StringBuilder sql = new StringBuilder();

		sql.append("select count(1) ");
		sql.append("  from yhb_user_sp usp, yhb_system_position jsp, yhb_role_data_auth jrda ");
		sql.append(" where usp.user_id = '").append(userId).append("' ");
		
		sql.append("   and jrda.auth_code in (").append(StringUtil.joinWrap(authCodes)).append(") ");
		
		if (StringUtils.isNotEmpty(authType)) {
			sql.append(" and jrda.auth_type = '").append(authType).append("' ");
		}
		
		if (StringUtils.isNotEmpty(authLevel)) {
			sql.append(" and jrda.auth_level = '").append(authLevel).append("' ");
		}
		
		sql.append("   and jrda.role_id = jsp.data_role_id ");
		sql.append("   and usp.system_position_oid = jsp.system_position_oid ");
		sql.append("   and (usp.effective_date is null or usp.effective_date <= now()) ");
		sql.append("   and (usp.expired_date is null or usp.expired_date >= now())" );
		
		return DataConverUtils.toLong(DaoUtil.uniqueResultWithSQL(sql.toString())) > 0;
	}

}

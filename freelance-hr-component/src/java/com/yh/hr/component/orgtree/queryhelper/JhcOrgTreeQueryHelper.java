package com.yh.hr.component.orgtree.queryhelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.util.AdminConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 内设机构信息查询服务类
 * 
 * @author liuhw 2017-2-25
 */
public class JhcOrgTreeQueryHelper {

	/**
	 * 根据单位Oid查询一级内设机构信息
	 * 
	 * @param unitOid
	 * @param powerControl
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<UtOrgDTO> findOrgInfoByUnitOid(Long unitOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer(
				"select o.org_oid,o.org_name,o.hierarchy_code,o.org_type from yhc_ut_org o where o.org_status ='"
						+ AdminConstants.ORG_STATUS_NORMAL
						+ "' and o.organization_oid in ( select ur.child_organization_oid ")
				.append(" from yhc_ut_unit ut, yhc_ut_organization o,yhc_ut_relation ur where ut.organization_oid = o.organization_oid and ur.parent_organization_oid = ut.organization_oid")
				.append(" and ut.unit_oid = '").append(unitOid).append("') ");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

			for (Object[] objs : list) {
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0]
						.toString()));
				dto.setOrgName(objs[1] == null ? null : objs[1].toString());
				dto.setHierarchyCode(objs[2] == null ? null : objs[2]
						.toString());
				dto.setOrgType(objs[3] == null ? null : objs[3].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	/**
	 * 根据父内设机构Oid查询子内设机构Oid
	 * 
	 * @param orgOid
	 * @param powerControl
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<UtOrgDTO> findOrgInfoByParentOrgOid(Long orgOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer(
				"select ju.org_oid, ju.org_name,ju.hierarchy_code,ju.org_type from yhc_ut_org juo, yhc_ut_relation jur, yhc_ut_org ju ")
				.append(" where ju.org_status ='"
						+ AdminConstants.ORG_STATUS_NORMAL
						+ "' and juo.organization_oid = jur.parent_organization_oid and ju.organization_oid = jur.child_organization_oid")
				.append(" and jur.relation_type = '")
				.append(UtConstants.UT_ORGANIZATION_TYPE_2).append("' ")
				.append(" and juo.org_oid = ").append(orgOid)
				.append(" order by ju.org_oid ");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

			for (Object[] objs : list) {
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0]
						.toString()));
				dto.setOrgName(objs[1] == null ? null : objs[1].toString());
				dto.setHierarchyCode(objs[2] == null ? null : objs[2]
						.toString());
				dto.setOrgType(objs[3] == null ? null : objs[3].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	public static UtOrgDTO findParentOrgInfoByOrgOid(String orgOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer(
				"select juo.org_oid, juo.org_name,juo.org_type from yhc_ut_org uo, yhc_ut_relation ur, yhc_ut_org juo ")
				.append(" where uo.organization_oid = ur.child_organization_oid  and ur.parent_organization_oid = juo.organization_oid")
				.append(" and uo.org_oid = ").append(orgOid)
				.append(" order by juo.org_oid ");

		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			Object[] objs = list.get(0);
			UtOrgDTO dto = new UtOrgDTO();
			dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0]
					.toString()));
			dto.setOrgName(objs[1] == null ? null : objs[1].toString());
			dto.setOrgType(objs[2] == null ? null : objs[2].toString());
			return dto;
		}
		return null;
	}

	public static boolean checkIsChild(Long organizationOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ju.org_name,ju.org_oid from yhc_ut_org juo,yhc_ut_relation jur,yhc_ut_org ju where juo.organization_oid = jur.parent_organization_oid");
		sql.append(" and ju.organization_oid = jur.child_organization_oid and jur.relation_type = '"
				+ UtConstants.UT_ORGANIZATION_TYPE_2 + "'");
		sql.append(" and juo.organization_oid=" + organizationOid);
		sql.append(" order by ju.org_oid");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		}
		return true;
	}

	public static List<UtOrgDTO> findOrgList(boolean checkUnit)
			throws DataAccessFailureException {
		if (checkUnit) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select juu.unit_name,juu.organization_oid,juu.unit_oid from yhc_ut_unit juu ");
			List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
			if (CollectionUtils.isNotEmpty(list)) {
				List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

				for (Object[] objs : list) {
					UtOrgDTO dto = new UtOrgDTO();
					dto.setOrgName(objs[0] == null ? null : objs[0].toString());
					dto.setOrganizationOid(objs[1] == null ? null : Long
							.valueOf(objs[1].toString()));
					dto.setUnitOid(objs[2] == null ? null : Long
							.valueOf(objs[2].toString()));
					items.add(dto);
				}
				return items;
			}
		} else {
			StringBuffer sql = new StringBuffer();
			sql.append(" select ju.org_oid,ju.org_name,ju.hierarchy_code,ju.org_type ");
			sql.append(" from yhc_ut_org ju");
			sql.append(" where exists (select 1 from yhb_user_org_auth juoa where juoa.org_oid = ju.org_oid and juoa.user_id ='"
					+ UserContext.getLoginUserID() + "' ) ");
			sql.append(" order by ju.hierarchy_code");
			List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
			if (CollectionUtils.isNotEmpty(list)) {
				List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

				for (Object[] objs : list) {
					UtOrgDTO dto = new UtOrgDTO();
					dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0]
							.toString()));
					dto.setOrgName(objs[1] == null ? null : objs[1].toString());
					dto.setHierarchyCode(objs[2] == null ? null : objs[2]
							.toString());
					dto.setOrgType(objs[3] == null ? null : objs[3].toString());
					items.add(dto);
				}
				return items;
			}
		}
		return null;
	}

	public static boolean checkIsUnit(Long organizationOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select juu.organization_oid from yhc_ut_unit juu where juu.organization_oid="
				+ organizationOid);
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return CollectionUtils.isNotEmpty(list);
	}

	public static List<UtOrgDTO> listAllChildren(Long orgOid)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		String orgOidString = JhcOrgTreeQueryHelper
				.getOrgOidListByOid(DataConverUtils.toString(orgOid));
		sql.append("select juoo.org_name,juoo.org_oid from yhc_ut_org juoo");
		sql.append(" where juoo.org_oid in(").append(orgOidString).append(")");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

			for (Object[] objs : list) {
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgName(objs[0] == null ? null : objs[0].toString());
				dto.setOrgOid(objs[1] == null ? null : Long.valueOf(objs[1]
						.toString()));
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	public static List<UtOrgDTO> findAllOrgList() throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ju.org_oid,ju.org_name,ju.organization_oid, ");
		sql.append("( CASE (select count(juo1.org_oid) from yhc_ut_org juo, yhc_ut_relation jur, yhc_ut_org juo1 ");
		sql.append(" where juo.organization_oid = jur.parent_organization_oid and juo1.organization_oid = jur.child_organization_oid ");
		sql.append(" and jur.relation_type = '"
				+ UtConstants.UT_RELATION_TYPE_2 + "'");
		sql.append(" and juo.organization_oid = ju.organization_oid) WHEN 0 then 'Y' else 'N' end) as child,ju.ORG_TYPE,ju.hierarchy_code");
		sql.append(" from yhc_ut_org ju where ju.org_status ='2'");
		sql.append(" order by ju.org_oid");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) {
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();

			for (Object[] objs : list) {
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0]
						.toString()));
				dto.setOrgName(objs[1] == null ? null : objs[1].toString());
				dto.setOrganizationOid(objs[2] == null ? null : Long
						.valueOf(objs[2].toString()));
				dto.setIsleaf("Y".equals(objs[3]));
				dto.setOrgType(objs[4] == null ? null : objs[4].toString());
				dto.setHierarchyCode(objs[5] == null ? null : objs[5]
						.toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	/**
	 * 获取科室OID拼接字符串
	 * 
	 * @param orgOid
	 * @throws DataAccessFailureException
	 */
	public static String getOrgOidListByOid(String orgOid)
			throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			String proc = "{?=call getChildOrgList(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil
					.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, orgOid);
			cstmt.execute();
			String orgOidString = cstmt.getString(1);
			cstmt.close();
			return orgOidString;
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程获取科室OID拼接字符串失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
	}

	/**
	 * 获取科室OID拼接字符串
	 * 
	 * @param orgType
	 * @throws DataAccessFailureException
	 */
	public static String getOrgOidListByType(String orgType)
			throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			String proc = "{?=call getChildOrgListByType(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil
					.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, orgType);
			cstmt.execute();
			String orgOidString = cstmt.getString(1);
			cstmt.close();
			return orgOidString;
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程获取科室OID拼接字符串失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
	}

	private static void close(Connection conn)
			throws DataAccessFailureException {
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

}

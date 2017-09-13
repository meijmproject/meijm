package com.yh.hr.orghc.ub.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 科室层级编码生成工具类
 * @author wangx
 * @date 2017-05-02
 * @version 1.0
 */
public class UbOrgServiceUtil {
	
	/**
	 * 通过科室OID获取科室层级编码
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public static String getHierarchyCodeByOrgOid(Long orgOid) throws ServiceException {
		String sql = "select o.HIERARCHY_CODE from yhe_ub_org o where o.org_oid = ?";
		return DaoUtil.uniqueResultWithSQL(sql, orgOid);
	}
	
	/**
	 * 通过上级科室层级编码获取当前科室的层级编码
	 * @param parentHierarchyCode
	 * @return
	 * @throws ServiceException
	 */
	public static String getHierarchyCodeByParentOrgOid(String parentHierarchyCode) throws ServiceException {
		String sql = "";
		if(parentHierarchyCode!=null) {
			sql = "select max(uo.HIERARCHY_CODE) from yhe_ub_org uo where CHAR_LENGTH(uo.HIERARCHY_CODE)<=(CHAR_LENGTH('"+parentHierarchyCode+"')+3)" +
				" and CHAR_LENGTH(uo.HIERARCHY_CODE)>CHAR_LENGTH('"+parentHierarchyCode+"') and uo.HIERARCHY_CODE like concat('"+parentHierarchyCode+"','%')";
		}else {
			sql = "select max(uo.HIERARCHY_CODE) from yhe_ub_org uo where CHAR_LENGTH(uo.HIERARCHY_CODE)<=3";
		}
		return DaoUtil.uniqueResultWithSQL(sql);
	}
	
	/**
	 * 拼接层级编码
	 * @param beforeCode
	 * @param codeSize
	 * @return
	 * @throws ServiceException
	 */
	public static String buildHierarchyCode(String beforeCode,int codeSize) throws ServiceException {
		String code = NumberUtils.toString(NumberUtils.createInteger(beforeCode)+1);
		StringBuffer preStr = new StringBuffer();
		for(int i=0;i<codeSize-code.length();i++) {
			preStr.append("0");
		}
		return preStr.toString()+code;
	}
	
	/**
	 * 生成科室层级编码
	 * @throws ServiceException
	 */
	public static void createHierarchyCode() throws ServiceException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			String proc = "{call initOrgHierarchyCode()}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.execute();
			cstmt.close();
		} catch (Exception e) {
			throw new DataAccessFailureException("生成科室层级编码失败", e);
		} finally {
			close(cstmt);
			close(conn);
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
	
	private static void close(Connection conn) throws DataAccessFailureException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Connection失败", e);
		}
	}
}

package com.yh.hr.component.im.tablehandle.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 执行原生SQL工具类
 * @author wangx
 * @date 2017-07-15
 * @version 1.0
 */
public class SqlDaoUtil {
	
	public static HibernateTemplate getHibernateTemplate() {
        return SpringBeanUtil.getHibernateTemplate();
    }

	/**
	 * 执行更新操作
	 * @param sql
	 * @throws DataAccessFailureException
	 */
	public static void executeSqlUpdate(final String sql) throws DataAccessFailureException {
		PreparedStatement psmt = null;
		Connection conn = null;
		Session session = null;
		Transaction t = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			t = session.beginTransaction();
			conn = session.connection();
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			t.commit();
		}catch (Exception e) {
			t.rollback();
			throw new DataAccessFailureException("更新操作失败", e);
		} finally {
			close(psmt);
			close(conn);
			close(session);
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
	
	private static void close(Statement stmt) throws DataAccessFailureException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Statement失败", e);
		}
	}
	
	private static void close(Session session) throws DataAccessFailureException {
		if (session != null) {
			session.close();
		}
	}
}

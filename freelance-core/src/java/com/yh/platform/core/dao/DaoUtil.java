/*
 * @(#) Dao.java        1.00         2006-11-6
 * 
 * Copyright (c) 2006  Corporation. All Rights Reserved.
 *
 *
 */
package com.yh.platform.core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yh.platform.core.exception.DataAccessFailureException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * dao 工具类
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DaoUtil {

    public static HibernateTemplate getHibernateTemplate() {
        return SpringBeanUtil.getHibernateTemplate();
    }

	public static <T> T get(Class<T> clz, Serializable id) throws DataAccessFailureException {
        try {
            return (T) getHibernateTemplate().get(clz, id);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException("get fail", e);
        }
    }

    /**
     * @param queryString
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> find(String queryString) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().find(queryString);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param queryString
     * @param value
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> find(String queryString, Object value) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().find(queryString, value);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param queryString
     * @param values
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> find(String queryString, Object ... values) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().find(queryString, values);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param queryName
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findByNamed(String queryName) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().findByNamedQuery(queryName);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }
    
    /**
     * @param queryName
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findByNamed(String queryName, Object... value) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().findByNamedQuery(queryName, value);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }
    
    /**
     * @param queryString
     * @param paramName
     * @param value
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findByNamed(String queryString, String paramName, Object value) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }
    
    /**
     * @param queryString
     * @param paramName
     * @param value
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findByNamed(String queryString, String[] paramName, Object[] value) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }


    /**
     * @param queryString
     * @return
     * @throws DataAccessFailureException
     */

    public static <T> List<T> findWithSQL(String queryString) throws DataAccessFailureException {
        return findWithSQL(queryString, (Object[]) null);
    }

    /**
     * @param sql
     * @param params
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findWithSQL(final String sql, final Map<String, Object> params) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return setParameters(session.createSQLQuery(sql), params).list();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /** 
     * @param sql
     * @param params
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> findWithSQL(final String sql, final Object... params) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return setParameters(session.createSQLQuery(sql), params).list();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param query
     * @param params
     * @return
     */
    protected static Query setParameters(Query query, Object... params) {
        if (params == null || params.length == 0) return query;
        int index = 0;
        for (Object e : params) {
            query.setParameter(index++, e);
        }
        return query;
    }

    /**
     * @param query
     * @param params
     * @return
     */
	protected static Query setParameters(Query query, Map<String, Object> params) {
        if (params == null || params.size() == 0) return query;
        Object v;
        for (Map.Entry<String, Object> e : params.entrySet()) {
            v = e.getValue();
            if (v instanceof Collection) {
                query.setParameterList(e.getKey(), (Collection) v);
            } else if (v.getClass().isArray()) {
                query.setParameterList(e.getKey(), (Object[]) v);
            } else {
                query.setParameter(e.getKey(), v);
            }
        }
        return query;
    }

    /**
     * @param hql
     * @param params
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> find(final String hql, final Map<String, Object> params) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return setParameters(session.createQuery(hql), params).list();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param hql
     * @return
     * @throws DataAccessFailureException
     */
    public static int executeUpdate(final String hql) throws DataAccessFailureException {
        try {
            return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return session.createQuery(hql).executeUpdate();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * hibernate 不支持sql删除
     * @param sql
     * @return
     * @throws DataAccessFailureException
     */
    public static int executeUpdateWithSql(final String sql) throws DataAccessFailureException {
        try {
            return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return session.createSQLQuery(sql).executeUpdate();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param sql
     * @param params
     * @param firstRow
     * @param maxRow
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> listWithSQLByCondition(final String sql, final Map<String, Object> params, final int firstRow, final int maxRow) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return listRecord(session.createSQLQuery(sql), params, firstRow, maxRow);
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param countSql
     * @param params
     * @return
     * @throws DataAccessFailureException
     */
    public static int countWithSQLByCondition(final String countSql, final Map<String, Object> params) throws DataAccessFailureException {
        try {
            return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return countRecord(session.createSQLQuery(countSql), params);
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param hql
     * @param params
     * @param firstRow
     * @param maxRow
     * @return
     * @throws DataAccessFailureException
     */
    public static <T> List<T> listByCondition(final String hql, final Map<String, Object> params, final int firstRow, final int maxRow) throws DataAccessFailureException {
        try {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return listRecord(session.createQuery(hql), params, firstRow, maxRow);
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param countHql
     * @param hqlParams
     * @return
     * @throws DataAccessFailureException
     */
    public static int countByCondition(final String countHql, final Map<String, Object> params) throws DataAccessFailureException {
        try {
            return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return countRecord(session.createQuery(countHql), params);
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

    /**
     * @param query
     * @param params
     * @param firstRow
     * @param maxRow
     * @return
     * @throws DataAccessException
     */
	protected static <T> List<T> listRecord(Query query, Map<String, Object> params, int firstRow, int maxRow) throws DataAccessException {
        if (maxRow > 0) {
            query.setFirstResult(firstRow);
            query.setMaxResults(maxRow);
        }
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                if (value.getClass().isArray()) {
                    query.setParameterList(name, (Object[]) value);
                } else if (value instanceof List) {
                    query.setParameterList(name, (List) value);
                } else {
                    query.setParameter(name, value);
                }
            }
        }
        return query.list();
    }

    /**
     * @param countQuery
     * @param params
     * @return
     * @throws DataAccessException
     */
    protected static Integer countRecord(Query countQuery, Map<String, Object> params) throws DataAccessException {
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof List) {
                    countQuery.setParameterList(name, (List) value);
                } else {
                    countQuery.setParameter(name, value);
                }
            }
        }
        Object count = countQuery.uniqueResult();
        return count == null ? 0 : Integer.valueOf(count.toString());
    }

    public static String getQueryString(final String queryName) throws DataAccessFailureException{
        try {
            return (String) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return session.getNamedQuery(queryName).getQueryString();
                }
            });
        } catch (DataAccessException e) {
            throw new DataAccessFailureException(e);
        }
    }

	/**
	 * 唯一查找
	 * @param hql
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static <T> T uniqueResult(final String hql, final Object... params) throws DataAccessFailureException {
		try {
			return (T) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,SQLException {
					return setParameters(session.createQuery(hql), params).uniqueResult();
				}
			});
		} catch (DataAccessException e) {
			throw new DataAccessFailureException(e);
		}
	}
	
	/**
	 * 唯一查找
	 * @param hql
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static <T> T uniqueResultWithSQL(final String sql, final Object... params) throws DataAccessFailureException {
		try {
			return (T) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,SQLException {
					return setParameters(session.createSQLQuery(sql), params).uniqueResult();
				}
			});
		} catch (DataAccessException e) {
			throw new DataAccessFailureException(e);
		}
	}
	
}

package com.yh.platform.core.bo;

import java.io.Serializable;

import com.yh.platform.core.exception.DataAccessFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @author qieren
 * @version 1.0
 * @since 2013-03-29
 */
public abstract class BaseBo implements Serializable {
    private static final long serialVersionUID = 1L;

    protected HibernateTemplate getHibernateTemplate() {
        return SpringBeanUtil.getHibernateTemplate();
    }

    public final void save() throws DataAccessFailureException {
        try {
            getHibernateTemplate().save(this);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            throw new DataAccessFailureException("save fail", e);
        }
    }

    public final void delete() throws DataAccessFailureException {
        try {
            getHibernateTemplate().delete(this);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            throw new DataAccessFailureException("delete fail", e);
        }
    }

    public final void update() throws DataAccessFailureException {
        try {
            getHibernateTemplate().update(this);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            throw new DataAccessFailureException("update fail", e);
        }
    }

    public final void saveOrUpdate() throws DataAccessFailureException {
        try {
            getHibernateTemplate().saveOrUpdate(this);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            throw new DataAccessFailureException("saveOrUpdate fail", e);
        }
    }
}

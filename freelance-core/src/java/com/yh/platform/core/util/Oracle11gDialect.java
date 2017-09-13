package com.yh.platform.core.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle9Dialect;

/**
 * Created by weizh on 2015/4/30.
 */
public class Oracle11gDialect extends Oracle9Dialect{
    public Oracle11gDialect() {
        super();
        registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
    }
}

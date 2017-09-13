package com.yh.platform.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *@description
 *
 *@author            chenkebing
 *@created           2006-6-22
 *@version           1.0
 *
 */

public class SpringBeanUtil implements ApplicationContextAware
{
	protected static ApplicationContext context = null;
    
    public static final String BEAN_NAME_JDBC="jdbcTemplate";
    public static final String BEAN_NAME_HIBERNATE="hibernateTemplate";
    public static final String BEAN_DAO = "dao";

    public void setApplicationContext(ApplicationContext arg0)
	{
		SpringBeanUtil.context = arg0;
	}
	
	public static ApplicationContext getContext()
	{
		return context;
	}
	
	public static Object getBean(String beanId)
	{
		return context.getBean(beanId);
	}

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId, Class<T> clz) {
        return (T) context.getBean(beanId, clz);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return (JdbcTemplate)context.getBean(BEAN_NAME_JDBC);
    }

    public static HibernateTemplate getHibernateTemplate() {
        return (HibernateTemplate)context.getBean(BEAN_NAME_HIBERNATE);
    }

}

package com.yh.platform.core.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
/**
 * 重写MySQL的hibernate方言类
 * @author wangx
 * @date 2017-04-17
 * @version 1.0
 */
public class MySQL5Dialect extends MySQLDialect {

	public MySQL5Dialect() {
		super();
		//重新注册hibernate的映射类型
		//decimal or numeric -> big_decimal
		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	}
}

/**
 * 
 */
package com.yh.platform.core.interceptor;

import java.io.Serializable;
import java.util.Date;

import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.yh.platform.core.exception.ServiceException;

/**
 * 公共的审计字段填充Hibernate拦截器
 * @author	zhangqp
 * @version	1.0,	16/10/18
 */

public class AuditFieldsInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 4629816954270299162L;
	
	protected Logger	logger	= Logger.getLogger(this.getClass());

	//修改
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] atype) {
		try {
			setObject(propertyNames, currentState, "updatedByCode", UserContext.getLoginUserID());
			setObject(propertyNames, currentState, "updatedByName", UserContext.getLoginUserName());
			setObject(propertyNames, currentState, "updatedDate", DateUtil.now());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return true;//true表示修改了数据？
	}
		
	//新增
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] atype) {
		try {
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			Date now = DateUtil.now();
			
			setObject(propertyNames, currentState, "createdByCode", uid);
			setObject(propertyNames, currentState, "createdByName", uname);
			setObject(propertyNames, currentState, "createdDate", now);
			
			setObject(propertyNames, currentState, "updatedByCode", uid);
			setObject(propertyNames, currentState, "updatedByName", uname);
			setObject(propertyNames, currentState, "updatedDate", now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return true;//true表示修改了数据？
	}
	
	private boolean setObject(String[] propertyNames, Object[] currentState, String propertyName, Object value) throws ServiceException {
		if (propertyNames == null || propertyNames.length == 0) return false;
		
		for (int i=0; i<propertyNames.length; i++) {
			if (propertyName.equals(propertyNames[i]) ) {
				currentState[i] = value;
				return true;
			}
		}
		
		return false;
	}
	
}

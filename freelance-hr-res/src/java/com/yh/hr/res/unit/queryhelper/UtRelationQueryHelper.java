/**
 * 
 */
package com.yh.hr.res.unit.queryhelper;

import com.yh.hr.res.unit.bo.UtRelation;
import org.apache.commons.lang.StringUtils;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	lenghh
 * @version	1.0,	16/09/06
 */

public class UtRelationQueryHelper {

	
	public static UtRelation findByChildOrganizationOid(Long organizationOid, String relationType) throws ServiceException {
		StringBuffer hql = new StringBuffer("from UtRelation ur where 1=1");
		if(null != organizationOid && organizationOid != 0){
			hql.append(" and ur.childOrganizationOid = " + organizationOid);
		}
		if(StringUtils.isNotEmpty(relationType)){
			hql.append(" and ur.relationType = '" + relationType + "'");
		}
		return DaoUtil.uniqueResult(hql.toString());
	}

	public static Long findAdminByChildOrganizationOid(Long organizationOid) throws ServiceException {
		//获取单位作为子单位的组织机构关系
		UtRelation utRelation = findByChildOrganizationOid(organizationOid,null);
		//如果不为空，则递归调用，直到为空为止
		if(null != utRelation){
			return findAdminByChildOrganizationOid(utRelation.getParentOrganizationOid());
		}
		//如果为空，表明单位本身就是主管单位,直接返回
		return organizationOid;
	}
	
	public static void deleteByOrganizationOid(Long organizationOid) throws ServiceException {
		StringBuilder hql = new StringBuilder("delete from UtRelation ur where 1=1 ");
		if(null != organizationOid && organizationOid != 0){
			hql.append(" and (ur.parentOrganizationOid =" +organizationOid+" or ur.childOrganizationOid =" +organizationOid+ ")");
			DaoUtil.executeUpdate(hql.toString());
		}
	}
}

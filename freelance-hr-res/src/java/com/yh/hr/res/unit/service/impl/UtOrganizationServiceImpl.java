/**
 * 
 */
package com.yh.hr.res.unit.service.impl;

import com.yh.hr.res.unit.bo.UtOrganization;
import com.yh.hr.res.unit.service.UtOrganizationService;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 组织机构对应关系ServiceImpl
 * @author	lenghh
 * @version	1.0,	16/09/06
 */

public class UtOrganizationServiceImpl implements UtOrganizationService {

	public Long create(String organizationName,String organizationType) throws ServiceException {
		UtOrganization utOrganization = new UtOrganization();
		utOrganization.setOrganizationName(organizationName);
		utOrganization.setOrganizationType(organizationType);
		utOrganization.setCreatedByCode(UserContext.getLoginUserID());
		utOrganization.setCreatedByName(UserContext.getLoginUserName());
		utOrganization.setCreatedDate(DateUtil.now());
		utOrganization.save();
		return utOrganization.getOrganizationOid();
	}

	public void update(Long organizationOid, String organizationName) throws ServiceException {
		UtOrganization utOrganization = DaoUtil.get(UtOrganization.class, organizationOid);
		if(utOrganization == null){throw new ServiceException(null,"组织信息不存在");}
		if(StringUtils.isNotEmpty(utOrganization.getOrganizationName())
				&& !organizationName.equals(utOrganization.getOrganizationName())){
			utOrganization.setOrganizationName(organizationName);
			utOrganization.setUpdatedByCode(UserContext.getLoginUserID());
			utOrganization.setUpdatedByName(UserContext.getLoginUserName());
			utOrganization.setUpdatedDate(DateUtil.now());
			utOrganization.update();
		}
	}

	public void delete(Long organizationOid) throws ServiceException {
		UtOrganization utOrganization = DaoUtil.get(UtOrganization.class, organizationOid);
		utOrganization.delete();
	}
}

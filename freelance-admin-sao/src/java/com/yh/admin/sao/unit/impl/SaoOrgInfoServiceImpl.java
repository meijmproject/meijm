/**
 * 
 */
package com.yh.admin.sao.unit.impl;

import java.util.List;

import com.yh.admin.sao.unit.SaoOrgInfoService;
import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public class SaoOrgInfoServiceImpl implements SaoOrgInfoService {
	
	private UtOrgService utOrgService;
	
	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	public SaoAdminOrgDTO get(Long orgOid) throws ServiceException {
		
		return BeanHelper.copyProperties(utOrgService.get(orgOid), SaoAdminOrgDTO.class);
	}

	public String getOrgName(Long orgOid) throws ServiceException {
		
		return utOrgService.getOrgName(orgOid);
	}

	public List<SaoAdminOrgDTO> findUnitOrg(Long unitOid) throws ServiceException {
		
		return BeanHelper.copyProperties(utOrgService.findUnitOrg(unitOid), SaoAdminOrgDTO.class);
	}

	public List<SaoAdminOrgDTO> listByCondition(TableTagBean ttb)
			throws ServiceException {
		return BeanHelper.copyProperties(utOrgService.listByCondition(ttb), SaoAdminOrgDTO.class) ;
	}

}

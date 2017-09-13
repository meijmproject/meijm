package com.yh.hr.component.unit.service.impl;

import com.yh.hr.component.unit.service.UtOrganizationComService;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;

public class UtOrganizationComServiceImpl implements UtOrganizationComService {

	private UtUnitService			utUnitService;
	private UtOrgService			utOrgService;
	
	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	public void synchroniseCreateUnitInfo(UtUnitDTO utUnitDTO) throws ServiceException {
		utUnitService.create(utUnitDTO);
	}
	
	public void synchroniseUpdateUnitInfo(UtUnitDTO utUnitDTO) throws ServiceException {
		utUnitService.update(utUnitDTO);
	}

	public void synchroniseCreateOrgInfo(UtOrgDTO utOrgDTO) throws ServiceException {
		utOrgService.create(utOrgDTO);
	}

	public void synchroniseUpdateOrgInfo(UtOrgDTO utOrgDTO) throws ServiceException {
		utOrgService.update(utOrgDTO);
	}

	public void synchroniseDeleteOrgInfo(Long orgOid) throws ServiceException {
		utOrgService.delete(orgOid);
	}

	public UtUnitDTO getAdminUnit(Long unitOid) throws ServiceException {
		return utUnitService.getAdminUnit(unitOid);
	}
}

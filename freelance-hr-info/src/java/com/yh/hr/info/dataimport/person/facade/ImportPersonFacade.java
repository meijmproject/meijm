package com.yh.hr.info.dataimport.person.facade;

import java.util.Map;

import com.yh.hr.info.dataimport.person.service.ImportPersonService;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

public class ImportPersonFacade {

	private ImportPersonService importPersonService;
	private UtOrgService utOrgService =(UtOrgService) SpringBeanUtil.getBean("utOrgService");

	public void setImportPersonService(ImportPersonService importPersonService) {
		this.importPersonService = importPersonService;
	}

	/**
	 * 导入人员信息
	 * @param map
	 * @param unitOid
	 * @param orgOid
	 */
	public void insertPersonGroupByOrg(Map<String, Object> map, String unitOid, String orgOid) throws ServiceException {
		importPersonService.importPersonGroupByOrg(map, unitOid, orgOid);
	}
	
	/**
	 * 获取通过科室名称获取科室信息
	 * @param orgName
	 * @return
	 * @throws ServiceException
	 */
	public UtOrgDTO findOrgDTOByName(String orgName) throws ServiceException {
		return utOrgService.findUtOrgDTOByOrgName(orgName);
	}
}

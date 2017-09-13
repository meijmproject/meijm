package com.yh.hr.info.dataexport.facade;

import java.util.List;

import com.yh.hr.info.dataexport.service.DataExportManagerService;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;

public class DataExportManagerFacade {
	
	private DataExportManagerService dataExportManagerService;
	private UtOrgService utOrgService;
	private UtUnitService utUnitService;
	
	public void setDataExportManagerService(
			DataExportManagerService dataExportManagerService) {
		this.dataExportManagerService = dataExportManagerService;
	}
	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}
	/**
	 * 查询导出人员信息
	 * @param personType 
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<List<Object>> listDataExportPersonInfo(List<String> fieldList, List<String> transFlagList) throws ServiceException{
		return dataExportManagerService.listDataExportPersonInfo(fieldList, transFlagList);
	}

	/**
	 * 通过科室OID查询科室名称
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public String getOrgName(Long orgOid) throws ServiceException {
		return utOrgService.getOrgName(orgOid);
	}

	/**
	 * 通过单位OID查询单位名称
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public String getUnitName(Long unitOid) throws ServiceException {
		return utUnitService.getUnitName(unitOid);
	}
}
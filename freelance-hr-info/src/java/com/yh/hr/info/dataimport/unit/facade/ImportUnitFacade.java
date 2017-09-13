package com.yh.hr.info.dataimport.unit.facade;

import com.yh.hr.info.dataimport.unit.dto.ImportUnitDTO;
import com.yh.hr.info.dataimport.unit.service.ImportUnitService;
import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.platform.core.exception.ServiceException;
/**
 * 单位机构导入Facade
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitFacade {
	
	private ImportUnitService importUnitService;

	public void setImportUnitService(ImportUnitService importUnitService) {
		this.importUnitService = importUnitService;
	}
	
	/**导入数据
	 * @param dto
	 * @return 
	 * @throws ServiceException
	 */
	public void insertUnitData(ImportUnitDTO dto) throws ServiceException {
		 importUnitService.importData(dto);
	}

	/**清除历史数据
	 * @throws ServiceException
	 */
	public void deleteData() throws ServiceException{
		importUnitService.deleteData();
	}

	/**
	 * 根据单位名称获取单位信息
	 * @param orgUnitName
	 * @return
	 * @throws ServiceException
	 */
	public UbUnit getUnitByName(String orgUnitName) throws ServiceException{
		return importUnitService.getUnitByName(orgUnitName);
	}

	public UbUnit getUbUnit() throws ServiceException{
		 return importUnitService.getUbUnit();
	}

	/**
	 * 根据科室名称获取科室信息
	 * @param orgName
	 * @return
	 * @throws ServiceException
	 */
	public UbOrg getOrgByName(String orgName) throws ServiceException{
		return importUnitService.getOrgByName(orgName);
	}
}

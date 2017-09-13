package com.yh.hr.info.dataimport.unit.service;

import com.yh.hr.info.dataimport.unit.dto.ImportUnitDTO;
import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.platform.core.exception.ServiceException;

/**
 * 单位、科室导入Service
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public interface ImportUnitService {

	/**
	 * 导入数据
	 * @param dto
	 * @throws ServiceException
	 */
	void importData(ImportUnitDTO dto) throws ServiceException;

	/**清除导入的历史数据
	 * @param flag 
	 * @throws ServiceException
	 */
	void deleteData() throws ServiceException;

	/**
	 * 根据单位名称获取单位信息
	 * @param orgUnitName
	 * @return
	 * @throws ServiceException
	 */
	UbUnit getUnitByName(String orgUnitName) throws ServiceException;

	/**
	 * 获取单位信息
	 * @return
	 * @throws ServiceException
	 */
	UbUnit getUbUnit() throws ServiceException;

	/**
	 * 根据科室名称获取科室信息
	 * @param orgName
	 * @return
	 * @throws ServiceException
	 */
	UbOrg getOrgByName(String orgName) throws ServiceException;

}

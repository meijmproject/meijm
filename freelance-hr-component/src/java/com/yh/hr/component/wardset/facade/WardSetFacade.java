package com.yh.hr.component.wardset.facade;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.wardset.dto.CfWardDto;
import com.yh.hr.component.wardset.service.WardSetService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;

public class WardSetFacade {

	private WardSetService wardSetService;
	private UtOrgService utOrgService;
	private UtUnitService  utUnitService;
	
	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public void setWardSetService(WardSetService wardSetService) throws ServiceException {
		this.wardSetService = wardSetService;
	}
	
	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}



	public List<CfWardDto> find(TableTagBean ttb) throws ServiceException {
		return wardSetService.find(ttb);
	}

	public void create(CfWardDto cfWardDto) throws ServiceException {
		wardSetService.create(cfWardDto);
	}

	public CfWardDto get(Long wardOid) throws ServiceException {
		return wardSetService.get(wardOid);
	}

	public void update(CfWardDto cfWardDto) throws ServiceException {
		wardSetService.update(cfWardDto);
	}

	public void delete(Long waedOid) throws ServiceException {
		wardSetService.delete(waedOid);
	}

	public List<Map<String,String>> findWardCollection() throws ServiceException {
		return wardSetService.findWardCollection();
	}
	
	/**
	 * 根据内设机构Oid查询内设机构名称
	 * @param deptOid
	 * @return
	 * @throws ServiceException
	 */
	public String findDeptName(Long deptOid) throws ServiceException
	{
		return utOrgService.getOrgName(deptOid);
	}

	/**
	 * 查询所有单位信息
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitInfo() throws ServiceException
	{
		return utUnitService.findUnitInfo();
	}
}

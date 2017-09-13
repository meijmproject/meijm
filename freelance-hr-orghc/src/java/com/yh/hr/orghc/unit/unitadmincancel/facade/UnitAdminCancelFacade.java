package com.yh.hr.orghc.unit.unitadmincancel.facade;

import com.yh.hr.orghc.unit.unitadmincancel.dto.UnitAdminCancelDTO;
import com.yh.hr.orghc.unit.unitadmincancel.service.info.UnitAdminCancelService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 主管单位撤销 Facade
 * @author zhengdr
 *
 * 时间:2016-12-22下午06:14:02
 */
public class UnitAdminCancelFacade {

	private UnitAdminCancelService unitAdminCancelService;
	
	public void setUnitAdminCancelService(
			UnitAdminCancelService unitAdminCancelService) {
		this.unitAdminCancelService = unitAdminCancelService;
	}

	/**
	 * 得到主管单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitAdminCancelDTO get(Long utUnitOid)throws ServiceException {
		
		return unitAdminCancelService.get(utUnitOid);
	}
	
	/**
	 * 修改
	 * @param unitAdminCancelDTO
	 * @throws ServiceException
	 */
	public void update(UnitAdminCancelDTO unitAdminCancelDTO)throws ServiceException {	
		
		unitAdminCancelService.update(unitAdminCancelDTO);
	}
}

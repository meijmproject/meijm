package com.yh.hr.orghc.unit.unitchildcancel.facade;

import com.yh.hr.orghc.unit.unitchildcancel.dto.UnitChildCancelDTO;
import com.yh.hr.orghc.unit.unitchildcancel.service.info.UnitChildCancelService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 下级单位撤销 Facade
 * @author zhengdr
 *
 * 时间:2016-12-23下午01:58:36
 */
public class UnitChildCancelFacade {

	private UnitChildCancelService unitChildCancelService;
	
	public void setUnitChildCancelService(
			UnitChildCancelService unitChildCancelService) {
		this.unitChildCancelService = unitChildCancelService;
	}

	/**
	 * 得到下级单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitChildCancelDTO get(Long utUnitOid)throws ServiceException {	
		
		return unitChildCancelService.get(utUnitOid);
	}
	
	/**
	 * 修改
	 * @param unitChildCancelDTO
	 * @throws ServiceException
	 */
	public void update(UnitChildCancelDTO unitChildCancelDTO)throws ServiceException {	
		
		unitChildCancelService.update(unitChildCancelDTO);
	}
}

package com.yh.hr.orghc.unit.unitchildcreate.facade;

import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.worktop.service.impl.TaskManageServiceImpl;
import com.yh.platform.core.exception.ServiceException;

/**
 * 下级单位创建---流程Facade
 * @author zhengdr
 *
 * 时间:2016-12-22下午03:00:59
 */
public class UnitChildCreateFlowFacade {

	/**
	 * 创建业务
	 * @param dto
	 * @throws ServiceException
	 */
	public void submitCreate(UnitChildCreateDTO dto) throws ServiceException {
		
		dto.setUnitOid(dto.getParentUnitOid());
		
		TaskManageServiceImpl.submitCreate(dto);
	}
}

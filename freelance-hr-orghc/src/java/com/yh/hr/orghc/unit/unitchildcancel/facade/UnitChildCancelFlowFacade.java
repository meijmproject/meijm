package com.yh.hr.orghc.unit.unitchildcancel.facade;

import com.yh.hr.orghc.unit.unitchildcancel.dto.UnitChildCancelDTO;
import com.yh.hr.worktop.service.impl.TaskManageServiceImpl;
import com.yh.platform.core.exception.ServiceException;

/**
 * 下级单位撤销 流程Facade
 * @author zhengdr
 *
 * 时间:2016-12-23下午02:00:02
 */
public class UnitChildCancelFlowFacade {

	/**
	 * 创建业务
	 * @param dto
	 * @throws ServiceException
	 */
	public void submitCreate(UnitChildCancelDTO dto) throws ServiceException {
		
		TaskManageServiceImpl.submitCreate(dto);
	}
}

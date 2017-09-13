package com.yh.hr.orghc.unit.unitadmincancel.facade;

import com.yh.hr.orghc.unit.unitadmincancel.dto.UnitAdminCancelDTO;
import com.yh.hr.worktop.service.impl.TaskManageServiceImpl;
import com.yh.platform.core.exception.ServiceException;

/**
 * 主管单位撤销  流程Facade
 * @author zhengdr
 *
 * 时间:2016-12-22下午06:15:36
 */
public class UnitAdminCancelFlowFacade {

	/**
	 * 创建业务
	 * @param dto
	 * @throws ServiceException
	 */
	public void submitCreate(UnitAdminCancelDTO dto) throws ServiceException {
		
		TaskManageServiceImpl.submitCreate(dto);
	}
}

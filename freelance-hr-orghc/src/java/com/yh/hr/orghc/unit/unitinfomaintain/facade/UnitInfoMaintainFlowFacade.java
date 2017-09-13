package com.yh.hr.orghc.unit.unitinfomaintain.facade;

import com.yh.hr.orghc.unit.unitinfomaintain.dto.UnitInfoMaintainDTO;
import com.yh.hr.worktop.service.impl.TaskManageServiceImpl;
import com.yh.platform.core.exception.ServiceException;

/**
 * 流程Facade
 * 机关业务不通用的实现
 * @author xiongyx
 *
 * 时间:2016-12-21
 */
public class UnitInfoMaintainFlowFacade {

	/**
	 * 创建业务，同时开启流程
	 */
	public void submitCreate(UnitInfoMaintainDTO dto)throws ServiceException {
		
		TaskManageServiceImpl.submitCreate(dto);
	}
}

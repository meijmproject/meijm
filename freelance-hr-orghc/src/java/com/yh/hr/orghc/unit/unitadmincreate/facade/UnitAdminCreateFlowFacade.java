package com.yh.hr.orghc.unit.unitadmincreate.facade;

import com.yh.hr.orghc.unit.unitadmincreate.dto.UnitAdminCreateDTO;
import com.yh.hr.worktop.service.impl.TaskManageServiceImpl;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

/**
 * 主管单位创建--流程Facade
 * @author zhengdr
 *
 * 时间:2016-12-20下午03:14:52
 */
public class UnitAdminCreateFlowFacade {

	/**
	 * 创建业务
	 * @param dto
	 * @throws ServiceException
	 */
	public void submitCreate(UnitAdminCreateDTO dto) throws ServiceException {
		
		dto.setUnitOid(UserContext.getLoginUserUnitOid());
		TaskManageServiceImpl.submitCreate(dto);
	}
}

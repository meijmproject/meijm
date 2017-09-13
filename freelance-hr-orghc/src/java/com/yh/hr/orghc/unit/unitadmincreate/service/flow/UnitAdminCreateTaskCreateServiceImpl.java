package com.yh.hr.orghc.unit.unitadmincreate.service.flow;

import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.orghc.unit.unitadmincreate.dto.UnitAdminCreateDTO;
import com.yh.hr.orghc.unit.unitadmincreate.service.info.UnitAdminCreateService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 主管单位创建----业务创建
 * @author zhengdr
 *
 * 时间:2016-12-20下午03:01:52
 */
public class UnitAdminCreateTaskCreateServiceImpl extends TaskCreateAbstractService {

	//service
	private UnitAdminCreateService unitAdminCreateService
	            =  (UnitAdminCreateService)SpringBeanUtil.getBean("unitAdminCreateService");
	
	/*
	 * (non-Javadoc)
	 * @see TaskCreateAbstractService#createSubTaskInfo(java.lang.Long)
	 */
	public void createSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		UnitAdminCreateDTO unitAdminCreateDTO = (UnitAdminCreateDTO)this.flowDTO;
		unitAdminCreateDTO.setTaskOid(bizTaskOid);
		unitAdminCreateService.save(unitAdminCreateDTO);
	}

}

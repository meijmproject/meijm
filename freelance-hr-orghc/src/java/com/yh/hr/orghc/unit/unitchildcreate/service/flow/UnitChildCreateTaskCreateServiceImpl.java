package com.yh.hr.orghc.unit.unitchildcreate.service.flow;

import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.orghc.unit.unitchildcreate.service.info.UnitChildCreateService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 下级单位创建---业务创建
 * @author zhengdr
 *
 * 时间:2016-12-22下午02:56:46
 */
public class UnitChildCreateTaskCreateServiceImpl extends TaskCreateAbstractService {

	//service
	private UnitChildCreateService unitChildCreateService
	            =  (UnitChildCreateService)SpringBeanUtil.getBean("unitChildCreateService");
	
	/*
	 * (non-Javadoc)
	 * @see TaskCreateAbstractService#createSubTaskInfo(java.lang.Long)
	 */
	public void createSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		UnitChildCreateDTO unitChildCreateDTO = (UnitChildCreateDTO)this.flowDTO;
		unitChildCreateDTO.setTaskOid(bizTaskOid);
		
		unitChildCreateService.save(unitChildCreateDTO);
	}

}

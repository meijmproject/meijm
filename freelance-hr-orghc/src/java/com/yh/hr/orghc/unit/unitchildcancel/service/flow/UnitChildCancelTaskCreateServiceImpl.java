package com.yh.hr.orghc.unit.unitchildcancel.service.flow;

import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.orghc.unit.unitchildcancel.dto.UnitChildCancelDTO;
import com.yh.hr.orghc.unit.unitchildcancel.service.info.UnitChildCancelService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 下级单位撤销---业务创建
 * @author zhengdr
 *
 * 时间:2016-12-23下午01:56:34
 */
public class UnitChildCancelTaskCreateServiceImpl extends TaskCreateAbstractService {

	//service
	private UnitChildCancelService unitChildCancelService
	            =  (UnitChildCancelService)SpringBeanUtil.getBean("unitChildCancelService");
	
	/*
	 * (non-Javadoc)
	 * @see TaskCreateAbstractService#createSubTaskInfo(java.lang.Long)
	 */
	public void createSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		UnitChildCancelDTO unitChildCancelDTO = (UnitChildCancelDTO)this.flowDTO;
		unitChildCancelDTO.setTaskOid(bizTaskOid);
		unitChildCancelService.save(unitChildCancelDTO);
	}

}

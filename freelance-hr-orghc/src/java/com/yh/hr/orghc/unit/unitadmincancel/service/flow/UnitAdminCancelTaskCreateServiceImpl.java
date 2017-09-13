package com.yh.hr.orghc.unit.unitadmincancel.service.flow;

import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.orghc.unit.unitadmincancel.dto.UnitAdminCancelDTO;
import com.yh.hr.orghc.unit.unitadmincancel.service.info.UnitAdminCancelService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 主管单位撤销---业务创建
 * @author zhengdr
 *
 * 时间:2016-12-22下午06:12:11
 */
public class UnitAdminCancelTaskCreateServiceImpl extends TaskCreateAbstractService {

	//service
	private UnitAdminCancelService unitAdminCancelService
	            =  (UnitAdminCancelService)SpringBeanUtil.getBean("unitAdminCancelService");
	
	/*
	 * (non-Javadoc)
	 * @see TaskCreateAbstractService#createSubTaskInfo(java.lang.Long)
	 */
	public void createSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		UnitAdminCancelDTO unitAdminCancelDTO = (UnitAdminCancelDTO)this.flowDTO;
		unitAdminCancelDTO.setTaskOid(bizTaskOid);
		unitAdminCancelService.save(unitAdminCancelDTO);
	}

}

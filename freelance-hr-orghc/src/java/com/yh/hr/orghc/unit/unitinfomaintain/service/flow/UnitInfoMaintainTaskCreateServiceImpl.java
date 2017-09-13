package com.yh.hr.orghc.unit.unitinfomaintain.service.flow;

import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.orghc.unit.unitinfomaintain.dto.UnitInfoMaintainDTO;
import com.yh.hr.orghc.unit.unitinfomaintain.service.info.UnitInfoMaintainService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
/**
 * 业务创建
 * @author xiongyx
 *
 * 时间:2016-12-21
 */
public class UnitInfoMaintainTaskCreateServiceImpl extends TaskCreateAbstractService {

	private UnitInfoMaintainService unitInfoMaintainService
	            = (UnitInfoMaintainService) SpringBeanUtil.getBean("unitInfoMaintainService");
	
	public void createSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		UnitInfoMaintainDTO unitInfoMaintainDTO = (UnitInfoMaintainDTO)this.flowDTO;
		unitInfoMaintainDTO.setTaskOid(bizTaskOid);
		unitInfoMaintainService.save(unitInfoMaintainDTO);
	}

}

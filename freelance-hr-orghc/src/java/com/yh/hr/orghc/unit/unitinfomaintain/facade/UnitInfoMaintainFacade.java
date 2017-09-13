package com.yh.hr.orghc.unit.unitinfomaintain.facade;

import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;

/**
 */
public class UnitInfoMaintainFacade {

	private BizUtUnitService bizUtUnitService;
	
	public void setBizUtUnitService(BizUtUnitService bizUtUnitService) {
		this.bizUtUnitService = bizUtUnitService;
	}

	/**
	 * 判段是否下设单位
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public boolean getUnitLevel(Long utUnitOid)throws ServiceException {	
		
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitById(utUnitOid);
		if(null != bizUtUnitDTO.getParentUnitOid()){			
			return true;
		}else{
			return false;
		}
	}
	
	
}

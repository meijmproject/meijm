/**
 * 
 */
package com.yh.admin.sao.unit.impl;

import java.util.List;

import com.yh.admin.sao.unit.SaoUnitInfoService;
import com.yh.admin.sao.unit.dto.SaoAdminUnitDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public class SaoUnitInfoServiceImpl implements SaoUnitInfoService {
	
	private UtUnitService utUnitService;
	
	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public SaoAdminUnitDTO get(Long unitOid) throws ServiceException {
		return BeanHelper.copyProperties(utUnitService.get(unitOid), SaoAdminUnitDTO.class);
	}

	public String getUnitName(Long unitOid) throws ServiceException {
		return utUnitService.getUnitName(unitOid);
	}

	public List<SaoAdminUnitDTO> listByCondition(TableTagBean ttb) throws ServiceException {

		return BeanHelper.copyProperties(utUnitService.listByCondition(ttb), SaoAdminUnitDTO.class) ;
	}

}

package com.yh.hr.select.unit.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.unit.dto.UnitSelectDTO;
import com.yh.hr.select.unit.queryhelper.UnitSelectQueryHelper;
import com.yh.platform.core.exception.ServiceException;

public class UnitSelectServiceImpl extends AbsUnitSelectService {

	protected List<UnitSelectDTO> getUnitList(TableTagBean ttb) throws ServiceException {
		
		return UnitSelectQueryHelper.listUbUnitInfo(ttb);
	}
	
}

package com.yh.hr.report.service.impl;

import com.yh.hr.report.queryhelper.PersonFloorDiagramQueryHelper;
import com.yh.hr.report.service.PersonFloorDiagramService;
import com.yh.platform.core.exception.ServiceException;
/**
 * 全院人员框图ServiceImpl
 * @author chenp
 * 2017-3-2
 */
public class PersonFloorDiagramServiceImpl implements PersonFloorDiagramService{

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.PersonFloorDiagramService#getOfficePersonNumByCond(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int getOfficePersonNumByCond(String waedType, String positionType,
			String flag) throws ServiceException {
		return PersonFloorDiagramQueryHelper.getOfficePersonNumByCond(waedType,positionType,flag);
	}

}

package com.yh.hr.report.facade.impl;

import com.yh.hr.report.service.PersonFloorDiagramService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 全院人员框架图 action
 * @author chenp
 * 2017-03-01
 * 
 */
public class PersonFloorDiagramFacadeImpl {
	private PersonFloorDiagramService personFloorDiagramService;
	
	public void setPersonFloorDiagramService(
			PersonFloorDiagramService personFloorDiagramService) {
		this.personFloorDiagramService = personFloorDiagramService;
	}

	/**
	 * 按照病区类别和岗位类别查询各科室人数
	 * @param waedType
	 * @param positionType
	 * @param flag (若为‘1’则统计研究人员，若为‘66’则统计药剂科人员,若为‘7’则统计行政后勤人员 为空 则统计除开这几类人员的其他人员)
	 * @return
	 * @throws ServiceException 
	 */
	public int getOfficePersonNumByCond(String waedType, String positionType,
			String flag) throws ServiceException {
		return personFloorDiagramService.getOfficePersonNumByCond(waedType,positionType,flag);
	}

}

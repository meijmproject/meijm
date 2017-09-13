package com.yh.hr.report.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 全院人员框图Service
 * @author chenp
 * 2017-3-2
 */
public interface PersonFloorDiagramService {

	/**
	 * 按照病区类别和岗位类别查询各科室人数
	 * @param waedType
	 * @param positionType
	 * @param flag(若为‘1’则统计研究人员，若为‘66’则统计药剂科人员,若为‘7’则统计行政后勤人员 为空 则统计除开这几类人员的其他人员)
	 * @return
	 */
	int getOfficePersonNumByCond(String waedType, String positionType,
			String flag)throws ServiceException ;

}

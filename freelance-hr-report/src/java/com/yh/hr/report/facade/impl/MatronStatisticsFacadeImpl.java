package com.yh.hr.report.facade.impl;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.MatronStatisticsDTO;
import com.yh.hr.report.service.MatronStatisticsService;
import com.yh.platform.core.exception.ServiceException;


/**
 * 科主任及护士长系列人员汇总 - FacadeImpl
 * @author liul
 * @date 2017-3-7
 */
public class MatronStatisticsFacadeImpl{
	
	private MatronStatisticsService matronStatisticsService;

	public void setMatronStatisticsService(
			MatronStatisticsService matronStatisticsService) {
		this.matronStatisticsService = matronStatisticsService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.facade.MatronStatisticsFacade#getOrgUnitNameCount(TableTagBean)
	 */
	public Map<String, List<MatronStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb) throws ServiceException {
		// TODO Auto-generated method stub
		return matronStatisticsService.getOrgUnitNameCount(ttb);
	}
	
}

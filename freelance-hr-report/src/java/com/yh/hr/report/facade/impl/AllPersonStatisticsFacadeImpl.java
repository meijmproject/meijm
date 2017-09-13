package com.yh.hr.report.facade.impl;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.AllPersonStatisticsDTO;
import com.yh.hr.report.service.AllPersonStatisticsService;
import com.yh.platform.core.exception.ServiceException;


/**
 * 全院员工汇总统计 - FacadeImpl
 * @author liul
 * @date 2017-3-2
 */
public class AllPersonStatisticsFacadeImpl{
	
	private AllPersonStatisticsService allPersonStatisticsService;

	public void setAllPersonStatisticsService(
			AllPersonStatisticsService allPersonStatisticsService) {
		this.allPersonStatisticsService = allPersonStatisticsService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.facade.AllPersonStatisticsFacade#getOrgUnitNameCount(TableTagBean)
	 */
	public Map<String, List<AllPersonStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb) throws ServiceException {
		// TODO Auto-generated method stub
		return allPersonStatisticsService.getOrgUnitNameCount(ttb);
	}
	
}

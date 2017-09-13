package com.yh.hr.report.facade.impl;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.TechnicalTitleAgeStatisticsDTO;
import com.yh.hr.report.service.TechnicalTitleAgeStatisticsService;
import com.yh.platform.core.exception.ServiceException;


/**
 * 卫生技术人员依据职称等级汇总的年龄、性别架构表 - FacadeImpl
 * @author liul
 * @date 2017-3-7
 */
public class TechnicalTitleAgeStatisticsFacadeImpl{
	
	private TechnicalTitleAgeStatisticsService technicalTitleAgeStatisticsService;

	public void setTechnicalTitleAgeStatisticsService(
			TechnicalTitleAgeStatisticsService technicalTitleAgeStatisticsService) {
		this.technicalTitleAgeStatisticsService = technicalTitleAgeStatisticsService;
	}
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.facade.TechnicalTitleAgeStatisticsFacade#getPositionLevelCount(TableTagBean)
	 */
	public Map<String, List<TechnicalTitleAgeStatisticsDTO>> getPositionLevelCount(
			TableTagBean ttb) throws ServiceException {
		// TODO Auto-generated method stub
		return technicalTitleAgeStatisticsService.getPositionLevelCount(ttb);
	}
	
}

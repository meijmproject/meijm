package com.yh.hr.report.service;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.TechnicalTitleAgeStatisticsDTO;
import com.yh.platform.core.exception.ServiceException;


/**
 * 卫生技术人员依据职称等级汇总的年龄、性别架构表 - Service
 * @author liul
 * @date 2017-3-7
 */
public interface TechnicalTitleAgeStatisticsService
{
	/**
	 * 得到各级别统计数据
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	Map<String, List<TechnicalTitleAgeStatisticsDTO>> getPositionLevelCount(
			TableTagBean ttb)throws ServiceException;
	
}

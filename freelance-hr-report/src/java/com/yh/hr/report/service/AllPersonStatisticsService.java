package com.yh.hr.report.service;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.AllPersonStatisticsDTO;
import com.yh.platform.core.exception.ServiceException;


/**
 * 全院员工汇总统计 - Service
 * @author liul
 * @date 2017-3-2
 */
public interface AllPersonStatisticsService
{
	/**
	 * 得到各科室员工统计数据
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	Map<String, List<AllPersonStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb)throws ServiceException;
	
}

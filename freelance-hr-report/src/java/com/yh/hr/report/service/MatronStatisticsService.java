package com.yh.hr.report.service;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.MatronStatisticsDTO;
import com.yh.platform.core.exception.ServiceException;


/**
 * 科主任及护士长系列人员汇总 - Service
 * @author liul
 * @date 2017-3-7
 */
public interface MatronStatisticsService
{
	/**
	 * 得到各科室员工统计数据
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	Map<String, List<MatronStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb)throws ServiceException;
	
}

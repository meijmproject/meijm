package com.yh.hr.report.service;

import java.util.List;

import com.yh.hr.report.dto.QsReportDTO;
import com.yh.platform.core.exception.ServiceException;

public interface ReportService {

	/**
	 * 根据父id查询报表列表
	 * @param parentOid
	 * @param statType
	 * @return
	 * @throws ServiceException 
	 */
	List<QsReportDTO> find(Long parentOid, String statType) throws ServiceException;

}

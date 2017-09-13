package com.yh.hr.report.service.impl;

import java.util.List;

import com.yh.hr.report.dto.QsReportDTO;
import com.yh.hr.report.queryhelper.ReportQueryHelper;
import com.yh.hr.report.service.ReportService;
import com.yh.platform.core.exception.ServiceException;

public class ReportServiceImpl implements ReportService {

	@Override
	public List<QsReportDTO> find(Long parentOid, String statType) throws ServiceException {
		return ReportQueryHelper.findByParentOid(parentOid,statType);
	}

}

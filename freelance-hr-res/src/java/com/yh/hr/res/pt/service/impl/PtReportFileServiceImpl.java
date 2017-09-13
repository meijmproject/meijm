package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.queryhelper.PtReportFileQueryHelper;
import com.yh.hr.res.pt.service.PtReportFileService;
import com.yh.platform.core.exception.ServiceException;

public class PtReportFileServiceImpl implements PtReportFileService {

	public String getFileNoType(String reportFileType) throws ServiceException {
		return PtReportFileQueryHelper.getFileNoType(reportFileType);
	}
}

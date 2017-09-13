package com.yh.hr.res.pt.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 报表信息service
 * @author lenghh
 *
 * 时间:2016-11-10下午05:14:07
 */
public interface PtReportFileService
{
	/**
	 * 根据报表类型获取文号类型
	 * @param reportFileType
	 * @throws ServiceException
	 */
	public String getFileNoType(String reportFileType) throws ServiceException;
}

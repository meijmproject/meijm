package com.yh.hr.report.service;

import java.util.HashMap;

import com.yh.platform.core.exception.ServiceException;

public interface BaseReportService
{
	/**
	 * 打印干部信息表
	 * @author   liul
	 * @created  2017-2-25
	 * @param personOid
	 * @param dir
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, Object> createOfficerInfoReport(Long personOid,String dir)throws ServiceException;
	public HashMap<String, Object> createPersonInfoReport(Long personOid, String path,String createBy) throws ServiceException;	
	
}

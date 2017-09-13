package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtReportDataDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 报表打印采集信息service
 * @author huangyj
 * 2016-11-18
 */
public interface PtReportDataService
{
	/**
	 * 新增
	 * @param ptReportDataDTO
	 * @throws ServiceException
	 */
	public void createPtReportData(PtReportDataDTO ptReportDataDTO) throws ServiceException;
	
	/**
	 * 修改
	 * @param ptReportDataDTO
	 * @throws ServiceException
	 */
	public void updatePtReportData(PtReportDataDTO ptReportDataDTO) throws ServiceException;
	
	/**
	 * 根据ID获取报表打印采集信息
	 * @param ptReportDataOid
	 * @throws ServiceException
	 */
	public PtReportDataDTO getPtReportData(Long ptReportDataOid) throws ServiceException;
	
	/**
	 * 删除
	 * @param ptReportDataOid
	 * @throws ServiceException
	 */
	public void deletePtReportData(Long ptReportDataOid) throws ServiceException;
}

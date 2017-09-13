package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtFileNoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 报表打印文号信息service
 * @author lenghh
 *
 * 时间:2016-11-10下午05:14:07
 */
public interface PtFileNoService
{
	/**
	 * 新增文号信息
	 * @param ReportFileNoDTO
	 * @throws ServiceException
	 */
	public void create(PtFileNoDTO fileNoDTO) throws ServiceException;
	
	/**
	 * 修改文号信息
	 * @param ReportFileNoDTO
	 * @throws ServiceException
	 */
	public void update(PtFileNoDTO fileNoDTO) throws ServiceException;
	
	/**
	 * 新增文号信息
	 * @param reportFileNoOid
	 * @throws ServiceException
	 */
	public PtFileNoDTO get(Long fileNoOid) throws ServiceException;
	
	/**
	 * 根据文号类型、年份获取最大的文号信息
	 * @param ReportFileNoDTO
	 * @throws ServiceException
	 */
	public PtFileNoDTO findMaxByFileTypeAndYear(String fileType, String year) throws ServiceException;
}

package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbGoOutInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PbGoOutInfoService {
	
	/**
	 * 新增人员外出的基础信息
	 * @param pbGoOutInfoDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PbGoOutInfoDTO pbGoOutInfoDTO) throws ServiceException;
	
	/**
	 * 修改人员外出的基础信息
	 * @param pbGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void update(PbGoOutInfoDTO pbGoOutInfoDTO) throws ServiceException;

	/**
	 * 删除人员外出的基础信息
	 * @param goOutOid
	 * @throws ServiceException
	 */
	public void delete(Long goOutOid) throws ServiceException;
	
	/**
	 * 查询人员对应的外出信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> list(Long personOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出信息
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PbGoOutInfoDTO get(Long goOutOid) throws ServiceException;

	/**
	 * 根据人员ID、开始时间、结束时间查询考勤周期内的外出信息（如考勤日期是5-1~5-31；外出时间是5-1~5-2）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate)throws ServiceException;

	/**
	 * 根据人员ID、开始时间、结束时间查询外出开始时间不在考勤周期内，
	 * 但外出结束时间在考勤周期内的外出信息（如考勤日期是5-1~5-31；外出时间是4-28~5-2）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate)throws ServiceException;
	
	/**
	 * 根据人员ID、开始时间、结束时间查询外出开始时间在考勤周期内，
	 * 但外出结束时间不在考勤周期内的外出信息（如考勤日期是5-1~5-31；外出时间是5-28~6-3）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate2(
			Long personOid, Date startDate, Date endDate)throws ServiceException;
	/**
	 * 根据人员ID、开始时间、结束时间查询外出开始时间不在考勤周期内，
	 * 外出结束时间不在考勤周期内的外出信息（如考勤日期是5-1~5-3；外出时间是4-20~6-3）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)throws ServiceException;

	public List<PbGoOutInfoDTO> findGoOutInfoByPersonOid(Long personOid,
			Date startDate, Date endDate) throws ServiceException;
}

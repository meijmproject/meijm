package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbGoOutStudyDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 外出进修基础信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PbGoOutStudyService {
	
	/**
	 * 新增外出进修的基础信息
	 * @param pbGoOutStudyDTO
	 * @throws ServiceException
	 */
	public Long create(PbGoOutStudyDTO pbGoOutStudyDTO) throws ServiceException;
	
	/**
	 * 修改外出进修的基础信息
	 * @param pbGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PbGoOutStudyDTO pbGoOutStudyDTO) throws ServiceException;

	/**
	 * 删除外出进修的基础信息
	 * @param pbGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long pbGoOutStudyOid) throws ServiceException;
	
	/**
	 * 查询人员对应的外出进修基础信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutStudyDTO> getPbGoOutStudyDTOListByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出进修基础信息
	 * @param pbGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PbGoOutStudyDTO get(Long pbGoOutStudyOid) throws ServiceException;
	
	/**
	 * 根据人员ID、开始时间、结束时间查询考勤周期内的外出信息（如考勤日期是5-1~5-31；外出时间是5-1~5-2）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate(
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
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate1(
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
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate2(
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
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)throws ServiceException;

	public List<PbGoOutStudyDTO> findGoOutInfoByPersonOid(Long personOid,
			Date startDate, Date endDate) throws ServiceException;
}

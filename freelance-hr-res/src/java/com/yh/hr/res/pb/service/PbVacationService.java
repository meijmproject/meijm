package com.yh.hr.res.pb.service;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbVacationDto;
import com.yh.platform.core.exception.ServiceException;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

public interface PbVacationService {

	/**
	 * 新增请休假信息
	 * @param pbVacationDto
	 * @throws ServiceException
	 */
	public void create(PbVacationDto pbVacationDto) throws ServiceException;


	/**
	 * 查询人员对应的请休假信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbVacationDto> list(Long personOid,String vacationType) throws ServiceException;

	/**
	 * 根据主键获取请休假信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbVacationDto get(Long vacationOid) throws ServiceException;


	/**
	 * 查询销假基础信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listPbVacation(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 修改请休假信息
	 * @param pbVacationDto
	 * @throws ServiceException
	 */
	public void update(PbVacationDto pbVacationDto) throws ServiceException;

	/**
	 * 根据人员ID、开始时间、结束时间查询考勤周期内的请休假信息（如考勤日期是5-1~5-31；请假时间是5-1~5-2）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate) throws ServiceException;

	/**
	 * 根据人员ID、开始时间、结束时间查询请假开始时间不在考勤周期内，
	 * 但请假结束时间在考勤周期内的请休假信息（如考勤日期是5-1~5-31；请假时间是4-28~5-2）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate)throws ServiceException;
	
	/**
	 * 根据人员ID、开始时间、结束时间查询请假开始时间在考勤周期内，
	 * 但请假结束时间不在考勤周期内的请休假信息（如考勤日期是5-1~5-31；请假时间是5-28~6-3）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate2(
			Long personOid, Date startDate, Date endDate)throws ServiceException;
	/**
	 * 根据人员ID、开始时间、结束时间查询请假开始时间不在考勤周期内，
	 * 但请假结束时间不在考勤周期内的请休假信息（如考勤日期是5-1~5-3；请假时间是4-20~6-3）
	 * @param personOid 人员ID
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws ServiceException
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)throws ServiceException;


	public List<PbVacationDto> findVacationInfoBypersonOid(Long personOid, Date startDate, Date endDate) throws ServiceException;
}

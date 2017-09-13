package com.yh.hr.res.pb.service.impl;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.bo.PbVacation;
import com.yh.hr.res.pb.dto.PbVacationDto;
import com.yh.hr.res.pb.queryhelper.PbVacationInfoQueryHelper;
import com.yh.hr.res.pb.service.PbVacationService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

public class PbVacationServiceImpl implements PbVacationService {

	public void create(PbVacationDto pbVacationDto) throws ServiceException {
		PbVacation bo = new PbVacation();
		BeanHelper.copyProperties(pbVacationDto, bo);
		bo.setCreateBy(UserContext.getLoginUserID());
		bo.setCreateName(UserContext.getLoginUserName());
		bo.setCreateDate(DateUtil.now());
		bo.save();
		pbVacationDto.setVacationOid(bo.getVacationOid());
	}

	/* (non-Javadoc)
 * @see PbOverTimeInfoService#list(java.lang.Long)
 */
	public List<PbVacationDto> list(Long personOid,String vacationType) throws ServiceException {
		return PbVacationInfoQueryHelper.list(personOid,vacationType);
	}

	/* (non-Javadoc)
	 * @see PbOverTimeInfoService#get(java.lang.Long)
	 */
	public PbVacationDto get(Long vacationOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbVacation.class, vacationOid), PbVacationDto.class);
	}

	/* (non-Javadoc)
	 * @see PbVacationService#pbVacationService(TableTagBean)
	 */
	public List<JSONObject> listPbVacation(TableTagBean ttb)
			throws ServiceException {
		return PbVacationInfoQueryHelper.listPbVacation(ttb);
	}

	/* (non-Javadoc)
	 * @see PbVacationService#update(PbVacationDto)
	 */
	public void update(PbVacationDto dto) throws ServiceException {
		PbVacation bo =DaoUtil.get(PbVacation.class, dto.getVacationOid());
		BeanHelper.copyProperties(dto, bo, BeanHelper.getNullPropertyNames(dto));
		bo.setUpdateBy(UserContext.getLoginUserID());
		bo.setUpdateName(UserContext.getLoginUserName());
		bo.setUpdateDate(DateUtil.now());
		bo.update();
	}

	/*
	 * (non-Javadoc)
	 * @see PbVacationService#findVacationInfoByPeriodStartDateAndEndDate(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbVacationInfoQueryHelper.findVacationInfoByPeriodStartDateAndEndDate1(personOid,startDate,endDate,"1");
	}

	/*
	 * (non-Javadoc)
	 * @see PbVacationService#findVacationInfoByPeriodStartDateAndEndDate1(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbVacationInfoQueryHelper.findVacationInfoByPeriodStartDateAndEndDate1(personOid,startDate,endDate,"2");
	}

	/*
	 * (non-Javadoc)
	 * @see PbVacationService#findVacationInfoByPeriodStartDateAndEndDate2(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate2(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbVacationInfoQueryHelper.findVacationInfoByPeriodStartDateAndEndDate1(personOid,startDate,endDate,"3");
	}

	/*
	 * (non-Javadoc)
	 * @see PbVacationService#findVacationInfoByPeriodStartDateAndEndDate3(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbVacationInfoQueryHelper.findVacationInfoByPeriodStartDateAndEndDate1(personOid,startDate,endDate,"4");
	}

	public List<PbVacationDto> findVacationInfoBypersonOid(Long personOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbVacationInfoQueryHelper.findVacationInfoBypersonOid(personOid,startDate,endDate);
	}
}

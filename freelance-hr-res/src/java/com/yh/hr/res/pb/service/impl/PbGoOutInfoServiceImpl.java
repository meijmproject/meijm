package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.bo.PbGoOutInfo;
import com.yh.hr.res.pb.dto.PbGoOutInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbGoOutInfoQueryHelper;
import com.yh.hr.res.pb.service.PbGoOutInfoService;
import jade.workflow.utils.DateUtil;

import java.util.Date;
import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

public class PbGoOutInfoServiceImpl implements PbGoOutInfoService {
	
	/**
	 * 新增人员外出的基础信息
	 * @param pbGoOutInfoDTO
	 * @throws ServiceException
	 */
	public Long create(PbGoOutInfoDTO pbGoOutInfoDTO) throws ServiceException{
		PbGoOutInfo pbGoOutInfo = new PbGoOutInfo();
		BeanHelper.copyProperties(pbGoOutInfoDTO, pbGoOutInfo);
		pbGoOutInfo.setCreateBy(UserContext.getLoginUserID());
		pbGoOutInfo.setCreateName(UserContext.getLoginUserName());
		pbGoOutInfo.setCreateDate(DateUtil.now());
		pbGoOutInfo.setUpdateBy(UserContext.getLoginUserID());
		pbGoOutInfo.setUpdateName(UserContext.getLoginUserName());
		pbGoOutInfo.setUpdateDate(DateUtil.now());
		pbGoOutInfo.save();
		return pbGoOutInfo.getGoOutOid();
	}
	
	/**
	 * 修改人员外出的基础信息
	 * @param pbGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void update(PbGoOutInfoDTO pbGoOutInfoDTO) throws ServiceException {
		PbGoOutInfo pbGoOutInfo = DaoUtil.get(PbGoOutInfo.class, pbGoOutInfoDTO.getGoOutOid());
		if(pbGoOutInfo!=null) {
			BeanHelper.copyProperties(pbGoOutInfoDTO, pbGoOutInfo, BeanHelper.getNullPropertyNames(pbGoOutInfoDTO));
			pbGoOutInfo.setUpdateBy(UserContext.getLoginUserID());
			pbGoOutInfo.setUpdateName(UserContext.getLoginUserName());
			pbGoOutInfo.setUpdateDate(DateUtil.now());
			pbGoOutInfo.update();
		}
	}
	
	/**
	 * 删除人员外出的基础信息
	 * @param goOutOid
	 * @throws ServiceException
	 */
	public void delete(Long goOutOid) throws ServiceException {
		PbGoOutInfo pbGoOutInfo = DaoUtil.get(PbGoOutInfo.class, goOutOid);
		if(pbGoOutInfo!=null) {
			pbGoOutInfo.delete();
		}
	}

	/**
	 * 查询人员对应的外出信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutInfoDTO> list(Long personOid) throws ServiceException {
		return PbGoOutInfoQueryHelper.list(personOid);
	}

	/**
	 * 根据主键获取外出信息
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PbGoOutInfoDTO get(Long goOutOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbGoOutInfo.class, goOutOid), PbGoOutInfoDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutInfoService#findGoOutInfoByPeriodStartDateAndEndDate(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbGoOutInfoQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "1");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutInfoService#findGoOutInfoByPeriodStartDateAndEndDate1(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbGoOutInfoQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "2");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutInfoService#findGoOutInfoByPeriodStartDateAndEndDate2(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate2(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException 
	{
		return PbGoOutInfoQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "3");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutInfoService#findGoOutInfoByPeriodStartDateAndEndDate3(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException
	{
		return PbGoOutInfoQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "4");
	}

	public List<PbGoOutInfoDTO> findGoOutInfoByPersonOid(Long personOid,
			Date startDate, Date endDate) throws ServiceException {
		return PbGoOutInfoQueryHelper.findGoOutInfoByPersonOid(personOid, startDate, endDate);
	}
}

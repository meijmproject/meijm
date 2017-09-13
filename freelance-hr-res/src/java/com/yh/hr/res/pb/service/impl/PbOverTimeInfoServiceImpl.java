package com.yh.hr.res.pb.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.bo.PbOvertimeInfo;
import com.yh.hr.res.pb.dto.PbOverTimeInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbOverTimeInfoQueryHelper;
import com.yh.hr.res.pb.service.PbOverTimeInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PbOverTimeInfoServiceImpl implements PbOverTimeInfoService {
	
	/**
	 * 新增人员外出的基础信息
	 * @param bizGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void create(PbOverTimeInfoDTO pbOverTimeInfoDTO) throws ServiceException{
		PbOvertimeInfo pbOverTimeInfo = new PbOvertimeInfo();
		BeanHelper.copyProperties(pbOverTimeInfoDTO, pbOverTimeInfo);
		pbOverTimeInfo.save();
	}

	/* (non-Javadoc)
	 * @see PbOverTimeInfoService#list(java.lang.Long)
	 */
	public List<PbOverTimeInfoDTO> list(Long personOid) throws ServiceException {
		return PbOverTimeInfoQueryHelper.list(personOid);
	}

	/* (non-Javadoc)
	 * @see PbOverTimeInfoService#get(java.lang.Long)
	 */
	public PbOverTimeInfoDTO get(Long overtimeOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbOvertimeInfo.class, overtimeOid), PbOverTimeInfoDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see PbOverTimeInfoService#findOvertimeDaysByCon(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public Float findOvertimeDaysByCon(Long personOid, Date startDate,
			Date endDate) throws ServiceException {
		return PbOverTimeInfoQueryHelper.findOvertimeDaysByCon(personOid, startDate, endDate);
	}
}

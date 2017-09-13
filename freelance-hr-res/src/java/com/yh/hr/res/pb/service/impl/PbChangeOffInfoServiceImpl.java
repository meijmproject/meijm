package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.bo.PbChangeOffInfo;
import com.yh.hr.res.pb.dto.PbChangeOffInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbChangeOffInfoQueryHelper;
import com.yh.hr.res.pb.service.PbChangeOffInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import java.util.Date;
import java.util.List;

public class PbChangeOffInfoServiceImpl implements PbChangeOffInfoService {

	/**
	 * 新增人员调休假基本信息
	 * @param pbChangeOffInfoDTO
	 * @throws com.jade.platform.core.exception.ServiceException
	 */
	public void create(PbChangeOffInfoDTO pbChangeOffInfoDTO) throws ServiceException{
		PbChangeOffInfo pbChangeOffInfo = new PbChangeOffInfo();
		BeanHelper.copyProperties(pbChangeOffInfoDTO, pbChangeOffInfo);
		pbChangeOffInfo.save();
		pbChangeOffInfoDTO.setChangeOffOid(pbChangeOffInfo.getChangeOffOid());
	}

	/* (non-Javadoc)
 * @see PbOverTimeInfoService#list(java.lang.Long)
 */
	public List<PbChangeOffInfoDTO> list(Long personOid) throws ServiceException {
		return PbChangeOffInfoQueryHelper.list(personOid);
	}

	/* (non-Javadoc)
	 * @see PbOverTimeInfoService#get(java.lang.Long)
	 */
	public PbChangeOffInfoDTO get(Long changeOffOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbChangeOffInfo.class, changeOffOid), PbChangeOffInfoDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see PbChangeOffInfoService#findChangeOffDaysByCon(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbChangeOffInfoDTO> findChangeOffDaysByCon(Long personOid, Date startDate,
			Date endDate,Long parameterSetOid) throws ServiceException {
		return PbChangeOffInfoQueryHelper.findChangeOffDaysByCon(personOid, startDate, endDate,parameterSetOid);
	}
}

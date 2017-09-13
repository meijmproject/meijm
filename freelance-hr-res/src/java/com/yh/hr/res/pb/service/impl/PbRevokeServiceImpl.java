package com.yh.hr.res.pb.service.impl;

import java.util.Date;

import com.yh.hr.res.pb.bo.PbRevokeVacation;
import com.yh.hr.res.pb.dto.PbRevokeVacationDto;
import com.yh.hr.res.pb.queryhelper.PbRevokeQueryHelper;
import com.yh.hr.res.pb.service.PbRevokeService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PbRevokeServiceImpl implements PbRevokeService {

	/* (non-Javadoc)
	 * @see PbRevokeService#create(PbRevokeVacationDto)
	 */
	public void create(PbRevokeVacationDto dto) throws ServiceException {
		PbRevokeVacation bo =BeanHelper.copyProperties(dto, PbRevokeVacation.class);
		bo.setCreateBy(UserContext.getLoginUserID());
		bo.setCreateName(UserContext.getLoginUserName());
		bo.setCreateDate(DateUtil.now());
		bo.save();
		dto.setRevokeVacationOid(bo.getRevokeVacationOid());
	}

	/* (non-Javadoc)
	 * @see PbRevokeService#list(java.lang.Long)
	 */
	public PbRevokeVacationDto getPbRevokeVacationByVacationOid(Long vacationOid) throws ServiceException {
		return PbRevokeQueryHelper.getPbRevokeByVacationOid(vacationOid);
	}

	/* (non-Javadoc)
	 * @see PbRevokeService#get(java.lang.Long)
	 */
	public PbRevokeVacationDto get(Long revokeVacationOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbRevokeVacation.class, revokeVacationOid), PbRevokeVacationDto.class);
	}


	/* (non-Javadoc)
	 * @see PbRevokeService#update(PbRevokeVacationDto)
	 */
	public void update(PbRevokeVacationDto dto) throws ServiceException {
		PbRevokeVacation bo =DaoUtil.get(PbRevokeVacation.class, dto.getRevokeVacationOid());
		BeanHelper.copyProperties(dto, bo, BeanHelper.getNullPropertyNames(dto));
		bo.setUpdateBy(UserContext.getLoginUserID());
		bo.setUpdateName(UserContext.getLoginUserName());
		bo.setUpdateDate(DateUtil.now());
		bo.update();
	}

	public PbRevokeVacationDto getPbRevokeVacation(Long vacationOid,
			Date startDate, Date endDate) throws ServiceException {
		return PbRevokeQueryHelper.getPbRevokeVacation(vacationOid,startDate,endDate);
	}
}

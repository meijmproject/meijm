package com.yh.hr.orghc.ut.service.impl;

import java.util.Date;

import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.queryhelper.BizUtUnitQueryHelper;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
/**
 * 单位信息管理Service
 * @author zhengdr
 *
 * 时间:2016-12-20下午05:35:51
 */
public class BizUtUnitServiceImpl implements BizUtUnitService {

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.bizunit.service.BizUtUnitService#createBizUtUnit(com.yh.hr.res.bizunit.dto.BizUtUnitDTO)
	 */
	public Long createBizUtUnit(BizUtUnitDTO bizUtUnitDTO) throws ServiceException {
		
		BizUtUnit bizUtUnit = BeanHelper.copyProperties(bizUtUnitDTO, BizUtUnit.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		bizUtUnit.setCreatedByCode(userId);
		bizUtUnit.setCreatedByName(userName);
		bizUtUnit.setCreatedDate(now);
		
		bizUtUnit.save();

		return bizUtUnit.getUtUnitOid();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.bizunit.service.BizUtUnitService#findBizUtUnitById(java.lang.Long)
	 */
	public BizUtUnitDTO findBizUtUnitById(Long bizUtUnitOid) throws ServiceException {
		
		BizUtUnit bizUtUnit = DaoUtil.get(BizUtUnit.class,bizUtUnitOid);
		
		return BeanHelper.copyProperties(bizUtUnit, BizUtUnitDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.bizunit.service.BizUtUnitService#updateBizUtUnit(com.yh.hr.res.bizunit.dto.BizUtUnitDTO)
	 */
	public void updateBizUtUnit(BizUtUnitDTO bizUtUnitDTO) throws ServiceException {
		BizUtUnit bizUtUnit = DaoUtil.get(BizUtUnit.class,bizUtUnitDTO.getUtUnitOid());
		if(bizUtUnit != null){
			BeanHelper.copyProperties(bizUtUnitDTO, bizUtUnit,new String[]{"createdDate","createdByCode","createdByName"});
			bizUtUnit.setUpdatedByCode(UserContext.getLoginUserID());
			bizUtUnit.setUpdatedByName(UserContext.getLoginUserName());
			bizUtUnit.setUpdatedDate(DateUtil.now());
			bizUtUnit.update();
		}
	}

	@Override
	public BizUtUnitDTO findBizUtUnitByTaskOid(Long taskOid)
			throws ServiceException {
		return BeanHelper.copyProperties(BizUtUnitQueryHelper.getByTaskOid(taskOid),BizUtUnitDTO.class);
	}

}

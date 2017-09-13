package com.yh.hr.res.gt.service.impl;

import java.util.Date;

import com.yh.hr.res.gt.bo.GtSetPlan;
import com.yh.hr.res.gt.dto.GtSetPlanDTO;
import com.yh.hr.res.gt.service.GtSetPlanService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class GtSetPlanServiceImpl implements GtSetPlanService {

	public Long createGtSetPlan(GtSetPlanDTO gtSetPlanDTO) throws ServiceException {
		GtSetPlan gtSetPlan = BeanHelper.copyProperties(gtSetPlanDTO, GtSetPlan.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		gtSetPlan.setCreatedByCode(userId);
		gtSetPlan.setCreatedByName(userName);
		gtSetPlan.setCreatedDate(now);
		gtSetPlan.save();
		return gtSetPlan.getGtSetPlanOid();
	}

	public GtSetPlanDTO findGtSetPlanById(Long gtSetPlanOid) throws ServiceException {
		GtSetPlan gtSetPlan = DaoUtil.get(GtSetPlan.class,gtSetPlanOid);
		return BeanHelper.copyProperties(gtSetPlan, GtSetPlanDTO.class);
	}

	public void updateGtSetPlan(GtSetPlanDTO gtSetPlanDTO) throws ServiceException {
		GtSetPlan gtSetPlan = DaoUtil.get(GtSetPlan.class,gtSetPlanDTO.getGtSetPlanOid());
		if(gtSetPlan != null){
			BeanHelper.copyProperties(gtSetPlanDTO, gtSetPlan);
			gtSetPlan.setUpdatedByCode(UserContext.getLoginUserID());
			gtSetPlan.setUpdatedByName(UserContext.getLoginUserName());
			gtSetPlan.setUpdatedDate(DateUtil.now());
			gtSetPlan.update();
		}
	}

	public void deleteGtSetPlan(Long gtSetPlanOid)
			throws ServiceException {
		DaoUtil.get(GtSetPlan.class,gtSetPlanOid).delete();
	}
}

package com.yh.hr.component.gb.service.impl;

import com.yh.hr.component.gb.bo.GBPlan;
import com.yh.hr.component.gb.dto.GBPlanDTO;
import com.yh.hr.component.gb.service.GBPlanService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.gb.queryhelper.GBPlanQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

public class GBPlanServiceImpl implements GBPlanService {

	public List<GBPlanDTO> listInfoCondition(TableTagBean ttb)
			throws ServiceException {
		return GBPlanQueryHelper.listInfoCondition(ttb);
	}

	public void createGBPlans(List<GBPlanDTO> list) throws ServiceException {
		for(GBPlanDTO dto: list) {
			GBPlan bo = new GBPlan();
			BeanHelper.copyProperties(dto, bo);
			bo.setCreatedByCode(UserContext.getLoginUserID());
			bo.setCreatedByName(UserContext.getLoginUserName());
			bo.setCreatedDate(DateUtil.now());
			bo.save();
		}
	}

	public void updateGBPlans(List<GBPlanDTO> list) throws ServiceException {
		GBPlanQueryHelper.deleteByUnitOid(4810L);
		for(GBPlanDTO dto: list) {
			GBPlan bo = new GBPlan();
			BeanHelper.copyProperties(dto, bo);
			bo.setCreatedByCode(UserContext.getLoginUserID());
			bo.setCreatedByName(UserContext.getLoginUserName());
			bo.setCreatedDate(DateUtil.now());
			bo.save();
		}
	}

	public List<JSONObject> listGBPlanWorking() throws ServiceException {
		return GBPlanQueryHelper.listGBPlanWorking();
	}

	public int listGBPlanTwoWorking() throws ServiceException {
		return GBPlanQueryHelper.listGBPlanTwoWorking();
	}

	public JSONObject listGBPlanWorkings() throws ServiceException {
		return GBPlanQueryHelper.listGBPlanWorkings();
	}

}

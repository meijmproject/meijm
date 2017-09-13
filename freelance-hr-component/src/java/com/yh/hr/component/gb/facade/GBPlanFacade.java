package com.yh.hr.component.gb.facade;

import java.util.List;

import com.yh.hr.component.gb.dto.GBPlanDTO;
import com.yh.hr.component.gb.service.GBPlanService;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public class GBPlanFacade {

	private GBPlanService gBPlanService;

	public GBPlanService getgBPlanService() {
		return gBPlanService;
	}

	public void setgBPlanService(GBPlanService gBPlanService) {
		this.gBPlanService = gBPlanService;
	}

	/**
	 * 获取岗位下达列表
	 * @param ttb
	 * @return
	 */
	public List<GBPlanDTO> listInfoCondition(TableTagBean ttb) throws ServiceException {
		return gBPlanService.listInfoCondition(ttb);
	}

	/**
	 * 创建岗位下达
	 * @param list
	 */
	public void createGBPlans(List<GBPlanDTO> list) throws ServiceException {
		gBPlanService.createGBPlans(list);
	}

	/**
	 * 修改岗位下达
	 * @param list
	 * @throws ServiceException
	 */
	public void updateGBPlans(List<GBPlanDTO> list) throws ServiceException {
		gBPlanService.updateGBPlans(list);
	}

	public List<JSONObject> listGBPlanWorking() throws ServiceException{
		return gBPlanService.listGBPlanWorking();
	}

	public int listGBPlanTwoWorking() throws ServiceException{
		return gBPlanService.listGBPlanTwoWorking();
	}

	public JSONObject listGBPlanWorkings() throws ServiceException{
		return gBPlanService.listGBPlanWorkings();
	}
}

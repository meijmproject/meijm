package com.yh.hr.component.gb.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.gb.dto.GBPlanDTO;
import com.yh.platform.core.exception.ServiceException;

public interface GBPlanService {

	/**
	 * 获取岗位下达列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	List<GBPlanDTO> listInfoCondition(TableTagBean ttb) throws ServiceException;

	/**
	 * 创建岗位下达
	 * @param list
	 * @throws ServiceException
	 */
	void createGBPlans(List<GBPlanDTO> list) throws ServiceException;

	/**
	 * 修改岗位下达
	 * @param list
	 * @throws ServiceException
	 */
	void updateGBPlans(List<GBPlanDTO> list) throws ServiceException;

	List<JSONObject> listGBPlanWorking() throws ServiceException;

	int listGBPlanTwoWorking() throws ServiceException;

	JSONObject listGBPlanWorkings() throws ServiceException;

}

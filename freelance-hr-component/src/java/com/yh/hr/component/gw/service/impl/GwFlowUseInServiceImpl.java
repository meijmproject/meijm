package com.yh.hr.component.gw.service.impl;

import java.util.List;

import com.yh.hr.component.gw.service.GwFlowUseInService;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.manage.GwFlowManager;
import com.yh.hr.res.gw.service.GwInfoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位资源占用实现类
 * @author liuhw
 * 2016-8-23
 */
public class GwFlowUseInServiceImpl implements GwFlowUseInService {
	private GwInfoService gwInfoService;
	
	public void setGwInfoService(GwInfoService gwInfoService) {
		this.gwInfoService = gwInfoService;
	}


	/*
   * (non-Javadoc)
   * @see GwFlowUseInService#useIn(java.util.List)
   */
	public void useIn(List<GwFlowDTO> list) throws ServiceException{
		
		for(GwFlowDTO dto: list)
		{
			//创建/更新岗位账户、头寸、流水信息
			GwFlowManager manage= new GwFlowManager(dto);
			manage.createFlows();
			//创建岗位资源使用信息
			gwInfoService.createGwInfo(dto);
		}
		
	}
}

package com.yh.hr.component.gw.service.impl;

import java.util.List;

import com.yh.hr.component.gw.service.GwFlowUseOutService;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.manage.GwFlowManager;
import com.yh.hr.res.gw.service.GwInfoService;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位资源释放服务接口实现类
 * @author liuhw
 * 2016-8-24
 */
public class GwFlowUseOutServiceImpl implements GwFlowUseOutService
{
	private GwInfoService gwInfoService;
	
	public void setGwInfoService(GwInfoService gwInfoService) {
		this.gwInfoService = gwInfoService;
	}
	/*
	 * (non-Javadoc)
	 * @see GwFlowUseOutService#useOut(java.util.List)
	 */
	public void useOut(List<GwFlowDTO> list) throws ServiceException{
		
		for(GwFlowDTO bp: list)
		{
			GwFlowManager bfm = new GwFlowManager(bp);
			bfm.createFlows();
			gwInfoService.updateGwInfo(bp);
		}
		
	}
}

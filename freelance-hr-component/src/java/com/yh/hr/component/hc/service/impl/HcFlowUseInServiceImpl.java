package com.yh.hr.component.hc.service.impl;

import java.util.List;

import com.yh.hr.component.hc.service.HcFlowUseInService;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.manage.HcFlowManager;
import com.yh.hr.res.hc.service.HcInfoService;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制资源占用实现类
 * @author liuhw
 * 2016-8-23
 */
public class HcFlowUseInServiceImpl implements HcFlowUseInService{
	private HcInfoService hcInfoService;
	
	public void setHcInfoService(HcInfoService hcInfoService) {
		this.hcInfoService = hcInfoService;
	}


	/*
	 * (non-Javadoc)
	 * @see HcFlowUseInService#useIn(java.util.List)
	 */
	public void useIn(List<HcFlowDTO> list) throws ServiceException{
		
		for(HcFlowDTO dto: list)
		{
			//创建/更新编制账户、头寸、流水信息
			HcFlowManager manage= new HcFlowManager(dto);
			manage.createFlows();
			//创建编制资源使用信息
			hcInfoService.createHcInfo(dto);
		}
		
	}

}

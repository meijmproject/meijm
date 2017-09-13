package com.yh.hr.component.hc.service.impl;

import java.util.List;

import com.yh.hr.component.hc.service.HcFlowUseOutService;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.manage.HcFlowManager;
import com.yh.hr.res.hc.service.HcInfoService;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制资源释放接口服务实现类
 * @author liuhw
 * 2016-8-24
 */
public class HcFlowUseOutServiceImpl implements HcFlowUseOutService
{
	private HcInfoService hcInfoService;
	
	public void setHcInfoService(HcInfoService hcInfoService) {
		this.hcInfoService = hcInfoService;
	}
	/*
	 * (non-Javadoc)
	 * @see HcFlowUseOutService#useOut(java.util.List)
	 */
	public void useOut(List<HcFlowDTO> list) throws ServiceException{
		
		for(HcFlowDTO bp: list)
		{
			HcFlowManager bfm = new HcFlowManager(bp);
			bfm.createFlows();
			hcInfoService.updateHcInfo(bp);
		}
		
	}
}

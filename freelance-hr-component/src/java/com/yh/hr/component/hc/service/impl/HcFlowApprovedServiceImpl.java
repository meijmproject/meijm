package com.yh.hr.component.hc.service.impl;

import java.util.List;

import com.yh.hr.component.hc.service.HcFlowApprovedService;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.manage.HcFlowManager;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制下达服务实现类
 * @author liuhw
 * 2016-9-5
 */
public class HcFlowApprovedServiceImpl implements  HcFlowApprovedService
{
	/*
	 * (non-Javadoc)
	 * @see HcFlowApprovedService#approved(java.util.List)
	 */
	public void approved(List<HcFlowDTO> list) throws ServiceException
	{
		for(HcFlowDTO dto: list)
		{
			//创建/更新编制账户、头寸、流水信息
			HcFlowManager manage= new HcFlowManager(dto);
			manage.createFlows();
		}
	}
}

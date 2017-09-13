package com.yh.hr.component.ld.service.impl;

import java.util.List;

import com.yh.hr.component.ld.service.LdFlowApprovedService;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.manage.LdFlowManager;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数下达服务实现类
 * @author liuhw
 * 2016-9-5
 */
public class LdFlowApprovedServiceImpl implements LdFlowApprovedService
{
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.component.ld.service.LeaderFlowApprovedService#approved(java.util.List)
	 */
	public void approved(List<LdFlowDTO> list) throws ServiceException
	{
		for(LdFlowDTO dto: list)
		{
			//创建/更新领导职数账户、头寸、流水信息
			LdFlowManager manage= new LdFlowManager(dto);
			manage.createFlows();
		}
	}
}

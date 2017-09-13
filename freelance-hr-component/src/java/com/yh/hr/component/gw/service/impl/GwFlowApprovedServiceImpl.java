package com.yh.hr.component.gw.service.impl;

import java.util.List;

import com.yh.hr.component.gw.service.GwFlowApprovedService;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.manage.GwFlowManager;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位资源下达实现类
 * @author Thinkpad
 *
 */
public class GwFlowApprovedServiceImpl implements  GwFlowApprovedService
{
	/*
	 * (non-Javadoc)
	 * @see GwFlowApprovedService#approved(java.util.List)
	 */
	public void approved(List<GwFlowDTO> list) throws ServiceException
	{
		for(GwFlowDTO dto: list)
		{
			//创建/更新岗位账户、头寸、流水信息
			GwFlowManager manage= new GwFlowManager(dto);
			manage.createFlows();
		}
	}
}

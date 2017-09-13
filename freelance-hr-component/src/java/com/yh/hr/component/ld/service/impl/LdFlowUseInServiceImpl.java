package com.yh.hr.component.ld.service.impl;

import java.util.List;

import com.yh.hr.component.ld.service.LdFlowUseInService;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.manage.LdFlowManager;
import com.yh.hr.res.ld.service.LdInfoService;
import com.yh.platform.core.exception.ServiceException;
/**
 * 领导职数资源占用实现类
 * @author liuhw
 * 2016-8-23
 */
public class LdFlowUseInServiceImpl implements LdFlowUseInService{
	private LdInfoService ldInfoService;
	
	public void setLdInfoService(LdInfoService ldInfoService) {
		this.ldInfoService = ldInfoService;
	}


	/*
	 * (non-Javadoc)
	 * @see LdFlowUseInService#useIn(java.util.List)
	 */
	public void useIn(List<LdFlowDTO> list) throws ServiceException{
		
		for(LdFlowDTO dto: list)
		{
			//创建/更新领导职数账户、头寸、流水信息
			LdFlowManager manage= new LdFlowManager(dto);
			manage.createFlows();
			//创建领导职数资源使用信息
			ldInfoService.createLdInfo(dto);
		}
		
	}
}

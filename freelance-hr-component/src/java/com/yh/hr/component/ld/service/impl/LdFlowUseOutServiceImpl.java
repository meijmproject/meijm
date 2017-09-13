package com.yh.hr.component.ld.service.impl;

import java.util.List;

import com.yh.hr.component.ld.service.LdFlowUseOutService;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.manage.LdFlowManager;
import com.yh.hr.res.ld.service.LdInfoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数资源释放服务接口实现类
 * @author liuhw
 * 2016-8-24
 */
public class LdFlowUseOutServiceImpl implements LdFlowUseOutService
{
	private LdInfoService ldInfoService;
	
	public void setLdInfoService(LdInfoService ldInfoService) {
		this.ldInfoService = ldInfoService;
	}
	/*
	 * (non-Javadoc)
	 * @see GwFlowUseOutService#useOut(java.util.List)
	 */
	public void useOut(List<LdFlowDTO> list) throws ServiceException{
		
		for(LdFlowDTO bp: list)
		{
			LdFlowManager bfm = new LdFlowManager(bp);
			bfm.createFlows();
			ldInfoService.updateLdInfo(bp);
		}
		
	}
}

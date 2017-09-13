package com.yh.hr.component.worktop.service.impl;

import com.yh.hr.component.worktop.dto.WbWorkBenchForwardDTO;
import com.yh.hr.component.worktop.queryhelper.WbWorkBenchQueryHelper;
import com.yh.hr.component.worktop.service.WbWorkBenchService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 事项树跳转工作台  service 实现
 * @author huw
 * @time 2016-09-29
 */
public class WbWorkBenchServiceImpl implements WbWorkBenchService
{
	/*
	 * (non-Javadoc)
	 * @see qzhrssb.worktop.service.BizWorkTopForwardService#getItemNodeCode(java.lang.String)
	 */
	public String getItemNodeCode(String menuCode) throws ServiceException 
	{
		return WbWorkBenchQueryHelper.getItemNodeCode(menuCode);
	}

	/*
	 * (non-Javadoc)
	 * @see qzhrssb.worktop.service.BizWorkTopForwardService#findBizWorkTopDTOByNodeCode(java.lang.String)
	 */
	public WbWorkBenchForwardDTO findBizWorkTopDTOByNodeCode(String itemNodeCode) throws ServiceException 
	{
		return WbWorkBenchQueryHelper.findBizWorkTopDTOByNodeCode(itemNodeCode);
	}
}

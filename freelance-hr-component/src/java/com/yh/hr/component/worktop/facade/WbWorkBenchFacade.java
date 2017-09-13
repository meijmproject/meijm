package com.yh.hr.component.worktop.facade;

import com.yh.hr.component.worktop.dto.WbWorkBenchForwardDTO;
import com.yh.hr.component.worktop.service.WbWorkBenchService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 事项树跳转工作台  facade
 * @author huw
 * @time 2016-09-29
 */
public class WbWorkBenchFacade 
{
	private WbWorkBenchService wbWorkBenchService;
	public void setWbWorkBenchService(WbWorkBenchService wbWorkBenchService) {
		this.wbWorkBenchService = wbWorkBenchService;
	}

	/**
	 * 获取itemNodeCode	业务事项环节码
	 * @param menuCode  事项树节点
	 * @return
	 * @throws ServiceException
	 */
	public String getItemNodeCode(String menuCode) throws ServiceException
	{
		return wbWorkBenchService.getItemNodeCode(menuCode);
	}
	
	/**
	 * 根据事项节点码查询工作台
	 * @param itemNodeCode
	 * @return WbWorkBenchForwardDTO
	 * @throws DataAccessException
	 */
	public WbWorkBenchForwardDTO findBizWorkTopDTOByNodeCode(String itemNodeCode) throws ServiceException
	{
		return wbWorkBenchService.findBizWorkTopDTOByNodeCode(itemNodeCode);
	}
}

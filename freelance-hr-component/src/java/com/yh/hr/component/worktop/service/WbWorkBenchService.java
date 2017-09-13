package com.yh.hr.component.worktop.service;

import com.yh.hr.component.worktop.dto.WbWorkBenchForwardDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 事项树跳转工作台  service 接口
 * @author huw
 * @time 2016-09-29
 */
public interface WbWorkBenchService 
{
	/**
	 * 获取itemNodeCode	业务事项环节码
	 * @param menuCode  事项树节点
	 * @return
	 * @throws ServiceException
	 */
	public String getItemNodeCode(String menuCode) throws ServiceException;
	
	/**
	 * 根据事项节点码查询工作台
	 * @param itemNodeCode
	 * @return BizWorkTopDTO
	 * @throws DataAccessException
	 */
	public WbWorkBenchForwardDTO findBizWorkTopDTOByNodeCode(String itemNodeCode) throws ServiceException;
}

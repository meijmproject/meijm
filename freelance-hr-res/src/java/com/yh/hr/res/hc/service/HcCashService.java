package com.yh.hr.res.hc.service;

import java.util.List;

import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 编制资源头寸信息
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */
public interface HcCashService {
	
	/**
	 * 查询编制资源头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<HcCashDTO> findHcCash(HcCashDTO dto) throws ServiceException;
}

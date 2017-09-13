package com.yh.hr.res.gw.service;

import java.util.List;

import com.yh.hr.res.gw.dto.GwCashDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位数头寸信息service接口
 * @author wangx
 * @created 2016-12-22
 * @version 1.0
 */
public interface GwCashService {

	/**
	 * 查询岗位数头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<GwCashDTO> findGwCash(GwCashDTO dto) throws ServiceException;
}

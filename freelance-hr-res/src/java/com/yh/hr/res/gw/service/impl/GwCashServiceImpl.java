package com.yh.hr.res.gw.service.impl;

import java.util.List;

import com.yh.hr.res.gw.dto.GwCashDTO;
import com.yh.hr.res.gw.queryhelper.GwCashQueryHelper;
import com.yh.hr.res.gw.service.GwCashService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位数头寸信息service实现类
 * @author wangx
 * @created 2016-12-22
 * @version 1.0
 */
public class GwCashServiceImpl implements GwCashService {

	/**
	 * 查询岗位数头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<GwCashDTO> findGwCash(GwCashDTO dto) throws ServiceException {
		return BeanHelper.copyProperties(GwCashQueryHelper.findGwCash(dto), GwCashDTO.class);
	}

}

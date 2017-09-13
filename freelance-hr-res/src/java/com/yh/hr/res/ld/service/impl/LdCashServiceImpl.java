package com.yh.hr.res.ld.service.impl;

import java.util.List;

import com.yh.hr.res.ld.dto.LdCashDTO;
import com.yh.hr.res.ld.queryhelper.LdCashQueryHelper;
import com.yh.hr.res.ld.service.LdCashService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 领导职数资源使用信息查询接口
 * @author	zhangqp
 * @version	1.0,	16/10/31
 */
public class LdCashServiceImpl implements LdCashService {

	/* (non-Javadoc)
	 * @see LdCashService#findLdCash(LdCashDTO)
	 */
	public List<LdCashDTO> findLdCash(LdCashDTO dto) throws ServiceException {
		return BeanHelper.copyProperties(LdCashQueryHelper.findLdCash(dto), LdCashDTO.class);
	}

}

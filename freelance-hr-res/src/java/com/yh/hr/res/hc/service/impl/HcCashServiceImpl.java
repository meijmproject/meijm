/**
 * 
 */
package com.yh.hr.res.hc.service.impl;

import java.util.List;

import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.hr.res.hc.queryhelper.HcCashQueryHelper;
import com.yh.hr.res.hc.service.HcCashService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 编制资源头寸信息
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */

public class HcCashServiceImpl implements HcCashService {

	public List<HcCashDTO> findHcCash(HcCashDTO dto) throws ServiceException {
		
		return BeanHelper.copyProperties(HcCashQueryHelper.findHcCash(dto), HcCashDTO.class);
	}

}

package com.yh.admin.subsystem.service;

import com.yh.admin.bo.SubSystem;
import com.yh.admin.dto.SubSystemDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class SubSystemService {

	public SubSystemDTO get(String systemCode) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(SubSystem.class, systemCode), SubSystemDTO.class);
	}

}

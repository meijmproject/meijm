package com.yh.admin.subsystem.facade;

import com.yh.admin.dto.SubSystemDTO;
import com.yh.admin.subsystem.service.SubSystemService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class SubSystemFacade {
	
	private SubSystemService subSystemService;

	public void setSubSystemService(SubSystemService subSystemService) {
		this.subSystemService = subSystemService;
	}

	public SubSystemDTO get(String systemCode) throws ServiceException {
		return subSystemService.get(systemCode);
	}
	
}

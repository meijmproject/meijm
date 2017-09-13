package com.yh.hr.component.ld.service;

import java.util.List;

import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.platform.core.exception.ServiceException;

public interface LdFlowApprovedService{
       
	/**
	 * 下达
	 * @param list
	 * @throws ServiceException
	 */
	public void approved(List<LdFlowDTO> list) throws ServiceException;
}

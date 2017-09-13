package com.yh.hr.res.sao.orghc.service;

import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.platform.core.exception.ServiceException;

public interface SaoUbGwService {

	/**
	 * 更新岗位数信息
	 * @param gtPlanDetailDTO
	 * @throws ServiceException
	 */
	public void updateUbGwInfo(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException;
}

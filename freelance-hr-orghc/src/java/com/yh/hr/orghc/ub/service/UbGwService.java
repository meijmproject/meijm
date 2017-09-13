package com.yh.hr.orghc.ub.service;

import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UbGwService {

	/**
	 * 更新岗位数信息
	 * @param gtPlanDetailDTO
	 * @param chgCount
	 * @throws ServiceException
	 */
	public void updateGwInfo(GtPlanDetailDTO gtPlanDetailDTO, Long chgCount) throws ServiceException;
}

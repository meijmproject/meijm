package com.yh.hr.res.sao.orghc.impl;

import com.yh.hr.orghc.ub.service.UbGwService;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.hr.res.gw.bo.GwCash;
import com.yh.hr.res.gw.queryhelper.GwCashQueryHelper;
import com.yh.hr.res.gw.util.GwFlowConstants;
import com.yh.hr.res.sao.orghc.service.SaoUbGwService;
import com.yh.platform.core.exception.ServiceException;

public class SaoUbGwServiceImpl implements SaoUbGwService {

	private UbGwService ubGwService;
	
	public void setUbGwService(UbGwService ubGwService) {
		this.ubGwService = ubGwService;
	}
	
	/**
	 * 更新岗位数信息
	 */
	public void updateUbGwInfo(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException {
		
		GwCash gwCash = GwCashQueryHelper.findGwCash(GwFlowConstants.ACCOUNT_TYPE_1, gtPlanDetailDTO.getUnitOid().toString(), gtPlanDetailDTO.getPostType(), gtPlanDetailDTO.getPostLevel());
		Long approvedCount = 0L;
		if(null != gwCash) {
			approvedCount = gwCash.getApprovedCount()==null ? 0L : gwCash.getApprovedCount();
		}
		Long chgCount = gtPlanDetailDTO.getPostLevelCount() - approvedCount;
		ubGwService.updateGwInfo(gtPlanDetailDTO, chgCount);
	}
}

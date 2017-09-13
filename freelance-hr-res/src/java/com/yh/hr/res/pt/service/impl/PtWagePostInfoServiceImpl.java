package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtWagePostInfoQueryHelper;
import com.yh.hr.res.pt.service.PtWagePostInfoService;
import com.yh.platform.core.exception.ServiceException;

class  PtWagePostInfoServiceImpl implements PtWagePostInfoService{
	
	public List<PtPostInfoDTO> listPtPostDTOByBizPersonOid(Long bizPersonOid) throws ServiceException{
		return PtWagePostInfoQueryHelper.listWagePtPostDTOByBizPersonOid(bizPersonOid);
	}
		
	
}

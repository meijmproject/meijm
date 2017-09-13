package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtWagePostInfoService {
	
	public List<PtPostInfoDTO> listPtPostDTOByBizPersonOid(Long bizPersonOid) throws ServiceException;
		
	
}

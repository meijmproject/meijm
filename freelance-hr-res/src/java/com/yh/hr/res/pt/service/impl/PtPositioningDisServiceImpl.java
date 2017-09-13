package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPositioningDisDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningDisQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningDisService;
import com.yh.platform.core.exception.ServiceException;

public class PtPositioningDisServiceImpl implements PtPositioningDisService
{
	
	/*
	 * (non-Javadoc)
	 * @see PtFamilyInfoService#listPtFamilyInfoByPersonOid(java.lang.Long)
	 */
	public List<PtPositioningDisDTO> listPtPositioningDisByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		return PtPositioningDisQueryHelper.listPtPositioningDisByBizPersonOid(bizPersonOid);
	}

	
}

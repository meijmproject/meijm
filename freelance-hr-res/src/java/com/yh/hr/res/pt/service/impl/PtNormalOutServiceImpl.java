package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtNormalOut;
import com.yh.hr.res.pt.queryhelper.PtNormalOutQueryHelper;
import com.yh.hr.res.pt.service.PtNormalOutService;
import com.yh.platform.core.exception.ServiceException;

public class PtNormalOutServiceImpl implements PtNormalOutService {

	public PtNormalOut findByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		return PtNormalOutQueryHelper.listPtNormalOutByBizPersonOid(bizPersonOid);
	}

	public int countPtNormalOutByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		return PtNormalOutQueryHelper.countPtNormalOutDTOByBizPersonId(bizPersonOid);
	}

}

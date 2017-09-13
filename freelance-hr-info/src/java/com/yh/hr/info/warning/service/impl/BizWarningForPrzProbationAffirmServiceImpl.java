package com.yh.hr.info.warning.service.impl;


import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizWarningInfo;
import com.yh.hr.info.warning.dto.BizWarningPersonDTO;
import com.yh.hr.info.warning.service.BizWarningQueryService;
import com.yh.platform.core.exception.ServiceException;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @description 聘任制试用期转正业务预警
 * @author liuhw
 * @created 2014-3-31
 * @version 1.0
 * 
 */
public class BizWarningForPrzProbationAffirmServiceImpl implements BizWarningQueryService
{
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.unitpanel.warning.service.BizWarningQueryService#listBizWarningInfo(yh.core.taglib.TableTagBean)
	 */
	public List<JSONObject> listBizWarningInfo(TableTagBean ttb)throws ServiceException 
	{
//		return BizWarningForPrzProbationAffirmQueryHelper.listBizWarningInfo(ttb);
		return null;
	}

	public List<BizWarningPersonDTO> listBizWarningPersonInfo(BizWarningInfo bizWarningInfo) throws ServiceException
	{
//		return BizWarningInfoQueryHelper.listBizWarningResultForEngageProbationAffirmDTOs(bizWarningInfo);
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see yh.component.warning.service.BizWarningQueryService#listBaseWarningInfo(com.yh.platform.tech.core.web.TableTagBean)
	 */
	public List<JSONObject> listBaseWarningInfo(TableTagBean ttb)throws ServiceException 
	{
//		return BizWarningInfoQueryHelper.listBizWarningResultForEngageProbationAffirmDTOs(ttb);
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.warning.service.BizWarningQueryService#getWarningCountByItemCodeAndSystemId(java.lang.String, java.lang.String)
	 */
	public int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId) throws ServiceException
	{
//		return BizWarningForPrzProbationAffirmQueryHelper.getWarningCountByItemCodeAndSystemId(itemCode, systemId);
		return 0;
	}

	public long[] countBizWarningInfo(TableTagBean ttb) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.yh.hr.info.warning.service.impl;


import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizWarningInfo;
import com.yh.hr.info.warning.dto.BizWarningPersonDTO;
import com.yh.hr.info.warning.queryhelper.BizWarningForOutRetireQueryHelper;
import com.yh.hr.info.warning.service.BizWarningQueryService;
import com.yh.platform.core.exception.ServiceException;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * @description 提前退休业务预警
 * @author liuhw
 * @created 2014-3-31
 * @version 1.0
 * 
 */
public class BizWarningForOutRetireServiceImpl implements BizWarningQueryService
{
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.unitpanel.warning.service.BizWarningQueryService#listBizWarningInfo(yh.core.taglib.TableTagBean)
	 */
	public List<JSONObject> listBizWarningInfo(TableTagBean ttb)throws ServiceException 
	{
		return BizWarningForOutRetireQueryHelper.listBizWarningInfo(ttb);
	}
	public long[] countBizWarningInfo(TableTagBean ttb)throws ServiceException
	{
		return BizWarningForOutRetireQueryHelper.countBizWarningInfo(ttb);
	}

	public List<BizWarningPersonDTO> listBizWarningPersonInfo(BizWarningInfo bizWarningInfo) throws ServiceException
	{
//		return BizWarningInfoQueryHelper.listBizWarningResultForRetireDTOs(bizWarningInfo);
		return null;
	}

	public List<JSONObject> listBaseWarningInfo(TableTagBean ttb)throws ServiceException 
	{
//		return BizWarningInfoQueryHelper.listBizWarningResultForRetireDTOs(ttb);
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.warning.service.BizWarningQueryService#getWarningCountByItemCodeAndSystemId(java.lang.String, java.lang.String)
	 */
	public int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId) throws ServiceException 
	{
//		return BizWarningForOutRetireQueryHelper.getWarningCountByItemCodeAndSystemId(itemCode, systemId);
		return 0;
	}

}

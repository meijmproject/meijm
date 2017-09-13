package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtProbationInfo;
import com.yh.hr.res.pt.dto.PtProbationInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtProbationInfoQueryHelper;
import com.yh.hr.res.pt.service.PtProbationInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 试用
 * 时间:2016-11-22
 */
public class PtProbationInfoServiceImpl implements PtProbationInfoService {

	public void create(PtProbationInfoDTO ptProbationInfoDto)
			throws ServiceException {
		// TODO Auto-generated method stub
		PtProbationInfo ptProbationInfo =BeanHelper.copyProperties(ptProbationInfoDto,PtProbationInfo.class);
		ptProbationInfo.setCreatedByCode(UserContext.getLoginUserID());
		ptProbationInfo.setCreatedByName(UserContext.getLoginUserName());
		ptProbationInfo.setCreatedDate(DateUtil.now());
		ptProbationInfo.save();
		
	}

	public void update(PtProbationInfoDTO ptProbationInfoDto)
			throws ServiceException {
		// TODO Auto-generated method stub
		PtProbationInfo ptProbationInfo = DaoUtil.get(PtProbationInfo.class, ptProbationInfoDto.getBizPersonOid());
		if(null != ptProbationInfo){
			BeanHelper.copyProperties(ptProbationInfoDto,ptProbationInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptProbationInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptProbationInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptProbationInfo.setUpdatedDate(DateUtil.now());
			ptProbationInfo.update();
		}
	}

	public PtProbationInfoDTO getById(Long bizPersonOid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return BeanHelper.copyProperties(PtProbationInfoQueryHelper.getByBizPersonOid(bizPersonOid),PtProbationInfoDTO.class);
	}

}

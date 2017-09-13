package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtDemissionInfo;
import com.yh.hr.res.pt.dto.PtDemissionInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtDemissionInfoQueryHelper;
import com.yh.hr.res.pt.service.PtDemissionInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtDemissionInfoServiceImpl implements PtDemissionInfoService {

	public void createPtDemissionInfo(PtDemissionInfoDTO ptDemissionInfoDTO) throws ServiceException {
		PtDemissionInfo ptDemissionInfo = BeanHelper.copyProperties(ptDemissionInfoDTO, PtDemissionInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptDemissionInfo.setCreatedByCode(userId);
		ptDemissionInfo.setCreatedByName(userName);
		ptDemissionInfo.setCreatedDate(now);
		ptDemissionInfo.save();
	}

	public PtDemissionInfoDTO findPtDemissionInfoById(Long ptDemissionInfoOid) throws ServiceException {
		PtDemissionInfo ptDemissionInfo = DaoUtil.get(PtDemissionInfo.class,ptDemissionInfoOid);
		return BeanHelper.copyProperties(ptDemissionInfo, PtDemissionInfoDTO.class);
	}

	public void updatePtDemissionInfo(PtDemissionInfoDTO ptDemissionInfoDTO) throws ServiceException {
		PtDemissionInfo ptDemissionInfo = DaoUtil.get(PtDemissionInfo.class,ptDemissionInfoDTO.getPtDemissionInfoOid());
		if(ptDemissionInfo != null){
			BeanHelper.copyProperties(ptDemissionInfoDTO, ptDemissionInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptDemissionInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptDemissionInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptDemissionInfo.setUpdatedDate(DateUtil.now());
			ptDemissionInfo.update();
		}
	}

	public PtDemissionInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtDemissionInfoQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

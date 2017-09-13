package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtDismissInfo;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtDismissInfoQueryHelper;
import com.yh.hr.res.pt.service.PtDismissInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtDismissInfoServiceImpl implements PtDismissInfoService {

	public void createPtDismissInfo(PtDismissInfoDTO ptDismissInfoDTO) throws ServiceException {
		PtDismissInfo ptDismissInfo = BeanHelper.copyProperties(ptDismissInfoDTO, PtDismissInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptDismissInfo.setCreatedByCode(userId);
		ptDismissInfo.setCreatedByName(userName);
		ptDismissInfo.setCreatedDate(now);
		ptDismissInfo.save();
	}

	public PtDismissInfoDTO findPtDismissInfoById(Long ptDismissInfoOid) throws ServiceException {
		PtDismissInfo ptDismissInfo = DaoUtil.get(PtDismissInfo.class,ptDismissInfoOid);
		return BeanHelper.copyProperties(ptDismissInfo, PtDismissInfoDTO.class);
	}

	public void updatePtDismissInfo(PtDismissInfoDTO ptDismissInfoDTO) throws ServiceException {
		PtDismissInfo ptDismissInfo = DaoUtil.get(PtDismissInfo.class,ptDismissInfoDTO.getPtDismissInfoOid());
		if(ptDismissInfo != null){
			BeanHelper.copyProperties(ptDismissInfoDTO, ptDismissInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptDismissInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptDismissInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptDismissInfo.setUpdatedDate(DateUtil.now());
			ptDismissInfo.update();
		}
	}

	public PtDismissInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtDismissInfoQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

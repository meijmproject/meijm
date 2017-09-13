package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtDeathInfo;
import com.yh.hr.res.pt.dto.PtDeathInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtDeathInfoQueryHelper;
import com.yh.hr.res.pt.service.PtDeathInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtDeathInfoServiceImpl implements PtDeathInfoService {

	public void createPtDeathInfo(PtDeathInfoDTO ptDeathInfoDTO) throws ServiceException {
		PtDeathInfo ptDeathInfo = BeanHelper.copyProperties(ptDeathInfoDTO, PtDeathInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptDeathInfo.setCreatedByCode(userId);
		ptDeathInfo.setCreatedByName(userName);
		ptDeathInfo.setCreatedDate(now);
		ptDeathInfo.save();
	}

	public PtDeathInfoDTO findPtDeathInfoById(Long ptDeathInfoOid) throws ServiceException {
		PtDeathInfo ptDeathInfo = DaoUtil.get(PtDeathInfo.class,ptDeathInfoOid);
		return BeanHelper.copyProperties(ptDeathInfo, PtDeathInfoDTO.class);
	}

	public void updatePtCancelEmploy(PtDeathInfoDTO ptDeathInfoDTO) throws ServiceException {
		PtDeathInfo ptDeathInfo = DaoUtil.get(PtDeathInfo.class,ptDeathInfoDTO.getPtDeathInfoOid());
		if(ptDeathInfo != null){
			BeanHelper.copyProperties(ptDeathInfoDTO, ptDeathInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptDeathInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptDeathInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptDeathInfo.setUpdatedDate(DateUtil.now());
			ptDeathInfo.update();
		}
	}

	public PtDeathInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtDeathInfoQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

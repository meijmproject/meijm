package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtRefuseInfo;
import com.yh.hr.res.pt.dto.PtRefuseInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtRefuseInfoQueryHelper;
import com.yh.hr.res.pt.service.PtRefuseInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtRefuseInfoServiceImpl implements PtRefuseInfoService {

	public void createPtRefuseInfo(PtRefuseInfoDTO ptRefuseInfoDTO) throws ServiceException {
		PtRefuseInfo ptRefuseInfo = BeanHelper.copyProperties(ptRefuseInfoDTO, PtRefuseInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptRefuseInfo.setCreatedByCode(userId);
		ptRefuseInfo.setCreatedByName(userName);
		ptRefuseInfo.setCreatedDate(now);
		ptRefuseInfo.save();
	}

	public PtRefuseInfoDTO findPtRefuseInfoById(Long ptRefuseInfoOid) throws ServiceException {
		PtRefuseInfo ptRefuseInfo = DaoUtil.get(PtRefuseInfo.class,ptRefuseInfoOid);
		return BeanHelper.copyProperties(ptRefuseInfo, PtRefuseInfoDTO.class);
	}

	public void updatePtRefuseInfo(PtRefuseInfoDTO ptRefuseInfoDTO) throws ServiceException {
		PtRefuseInfo ptRefuseInfo = DaoUtil.get(PtRefuseInfo.class,ptRefuseInfoDTO.getPtRefuseInfoOid());
		if(ptRefuseInfo != null){
			BeanHelper.copyProperties(ptRefuseInfoDTO, ptRefuseInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptRefuseInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptRefuseInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptRefuseInfo.setUpdatedDate(DateUtil.now());
			ptRefuseInfo.update();
		}
	}

	public PtRefuseInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		// TODO Auto-generated method stub
		return PtRefuseInfoQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

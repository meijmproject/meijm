package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtPersonOut;
import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonOutQueryHelper;
import com.yh.hr.res.pt.service.PtPersonOutService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtPersonOutServiceImpl implements PtPersonOutService {

	public void createPtPersonOut(PtPersonOutDTO ptPersonOutDTO) throws ServiceException {
		PtPersonOut ptPersonOut = BeanHelper.copyProperties(ptPersonOutDTO, PtPersonOut.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptPersonOut.setCreatedByCode(userId);
		ptPersonOut.setCreatedByName(userName);
		ptPersonOut.setCreatedDate(now);
		ptPersonOut.save();
	}

	public PtPersonOutDTO findPtPersonOutById(Long ptPersonOutOid) throws ServiceException {
		PtPersonOut ptPersonOut = DaoUtil.get(PtPersonOut.class,ptPersonOutOid);
		return BeanHelper.copyProperties(ptPersonOut, PtPersonOutDTO.class);
	}

	public void updatePtPersonOut(PtPersonOutDTO ptPersonOutDTO) throws ServiceException {
		PtPersonOut ptPersonOut = DaoUtil.get(PtPersonOut.class,ptPersonOutDTO.getPtPersonOutOid());
		if(ptPersonOut != null){
			BeanHelper.copyProperties(ptPersonOutDTO, ptPersonOut,new String[]{"createdDate","createdByCode","createdByName"});
			ptPersonOut.setUpdatedByCode(UserContext.getLoginUserID());
			ptPersonOut.setUpdatedByName(UserContext.getLoginUserName());
			ptPersonOut.setUpdatedDate(DateUtil.now());
			ptPersonOut.update();
		}
	}

	public PtPersonOutDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtPersonOutQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

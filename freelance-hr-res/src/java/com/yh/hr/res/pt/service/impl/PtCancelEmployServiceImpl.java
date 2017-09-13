package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import com.yh.hr.res.pt.bo.PtCancelEmploy;
import com.yh.hr.res.pt.dto.PtCancelEmployDTO;
import com.yh.hr.res.pt.queryhelper.PtCancelEmployQueryHelper;
import com.yh.hr.res.pt.service.PtCancelEmployService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtCancelEmployServiceImpl implements PtCancelEmployService {

	public void createPtCancelEmploy(PtCancelEmployDTO ptCancelEmployDTO) throws ServiceException {
		PtCancelEmploy ptCancelEmploy = BeanHelper.copyProperties(ptCancelEmployDTO, PtCancelEmploy.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptCancelEmploy.setCreatedByCode(userId);
		ptCancelEmploy.setCreatedByName(userName);
		ptCancelEmploy.setCreatedDate(now);
		ptCancelEmploy.save();

	}

	public PtCancelEmployDTO findPtCancelEmployById(Long ptCancelEmployOid) throws ServiceException {
		PtCancelEmploy ptCancelEmploy = DaoUtil.get(PtCancelEmploy.class,ptCancelEmployOid);
		return BeanHelper.copyProperties(ptCancelEmploy, PtCancelEmployDTO.class);
	}

	public void updatePtCancelEmploy(PtCancelEmployDTO ptCancelEmployDTO) throws ServiceException {
		PtCancelEmploy ptCancelEmploy = DaoUtil.get(PtCancelEmploy.class,ptCancelEmployDTO.getPtCancelEmployOid());
		if(ptCancelEmploy != null){
			BeanHelper.copyProperties(ptCancelEmployDTO, ptCancelEmploy,new String[]{"createdDate","createdByCode","createdByName"});
			ptCancelEmploy.setUpdatedByCode(UserContext.getLoginUserID());
			ptCancelEmploy.setUpdatedByName(UserContext.getLoginUserName());
			ptCancelEmploy.setUpdatedDate(DateUtil.now());
			ptCancelEmploy.update();
		}
	}

	public PtCancelEmployDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtCancelEmployQueryHelper.findByBizPersonOid(bizPersonOid);
	}

}

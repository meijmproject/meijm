package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtRecruitment;
import com.yh.hr.res.pt.dto.PtRecruitmentDTO;
import com.yh.hr.res.pt.service.PtRecruitmentService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
/**
 * @author xiongyx
 * @created 2016-1-17
 * @version 1.0
 */
public class PtRecruitmentServiceImpl implements PtRecruitmentService {

	/*
	 * (non-Javadoc)
	 * @see PtRecruitmentService#create(PtRecruitmentDTO)
	 */
	public void create(PtRecruitmentDTO PtRecruitmentDTO) throws ServiceException {
		
		PtRecruitment PtRecruitment = BeanHelper.copyProperties(PtRecruitmentDTO, PtRecruitment.class);
		PtRecruitment.setCreatedByCode(UserContext.getLoginUserID());
		PtRecruitment.setCreatedByName(UserContext.getLoginUserName());
		PtRecruitment.setCreatedDate(DateUtil.now());
		PtRecruitment.save();
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtRecruitmentService#find(java.lang.Long)
	 */
	public PtRecruitmentDTO find(Long bizPersonOid) throws ServiceException {
		
		PtRecruitment PtRecruitment = DaoUtil.get(PtRecruitment.class, bizPersonOid);
		if(PtRecruitment != null){
			return BeanHelper.copyProperties(PtRecruitment, PtRecruitmentDTO.class);
		}
		
		return null;
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtRecruitmentService#update(PtRecruitmentDTO)
	 */
	public void update(PtRecruitmentDTO PtRecruitmentDTO) throws ServiceException {
		
		PtRecruitment punishmentInfo = DaoUtil.get(PtRecruitment.class, PtRecruitmentDTO.getBizPersonOid());
		if(punishmentInfo != null){
			BeanHelper.copyProperties(PtRecruitmentDTO, punishmentInfo, new String[]{"createdDate","createdByCode","createdByName"});
			punishmentInfo.setUpdatedByCode(UserContext.getLoginUserID());
			punishmentInfo.setUpdatedByName(UserContext.getLoginUserName());
			punishmentInfo.setUpdatedDate(DateUtil.now());
			punishmentInfo.update();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtRecruitmentService#delete(java.lang.Long)
	 */
	public void delete(Long bizPersonOid) throws ServiceException {	
		PtRecruitment PtRecruitment = DaoUtil.get(PtRecruitment.class, bizPersonOid);
		if(null != PtRecruitment){
			PtRecruitment.delete();	
		}	
	}

}

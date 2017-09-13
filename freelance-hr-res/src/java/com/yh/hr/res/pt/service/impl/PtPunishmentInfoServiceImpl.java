package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPunishmentInfo;
import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPunishmentInfoQueryHelper;
import com.yh.hr.res.pt.service.PtPunishmentInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
/**
 * @description The class for PtPunishmentInfo service
 * @author xiongyx
 * @created 2016-10-10
 * @version 1.0
 */
public class PtPunishmentInfoServiceImpl implements PtPunishmentInfoService {

	/*
	 * (non-Javadoc)
	 * @see PtPunishmentInfoService#create(PtPunishmentInfoDTO)
	 */
	public void create(PtPunishmentInfoDTO PtPunishmentInfoDTO) throws ServiceException {
		
		PtPunishmentInfo ptPunishmentInfo = BeanHelper.copyProperties(PtPunishmentInfoDTO, PtPunishmentInfo.class);
		ptPunishmentInfo.setCreatedByCode(UserContext.getLoginUserID());
		ptPunishmentInfo.setCreatedByName(UserContext.getLoginUserName());
		ptPunishmentInfo.setCreatedDate(DateUtil.now());
		ptPunishmentInfo.save();
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtPunishmentInfoService#find(java.lang.Long)
	 */
	public PtPunishmentInfoDTO find(Long punishmentInfoOid) throws ServiceException {
		
		PtPunishmentInfo ptPunishmentInfo = DaoUtil.get(PtPunishmentInfo.class, punishmentInfoOid);
		if(ptPunishmentInfo != null){
			return BeanHelper.copyProperties(ptPunishmentInfo, PtPunishmentInfoDTO.class);
		}
		
		return null;
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtPunishmentInfoService#update(PtPunishmentInfoDTO)
	 */
	public void update(PtPunishmentInfoDTO PtPunishmentInfoDTO) throws ServiceException {
		
		PtPunishmentInfo punishmentInfo = DaoUtil.get(PtPunishmentInfo.class, PtPunishmentInfoDTO.getPtPunishmentOid());
		if(punishmentInfo != null){
			BeanHelper.copyProperties(PtPunishmentInfoDTO, punishmentInfo, new String[]{"createdDate","createdByCode","createdByName"});
			punishmentInfo.setUpdatedByCode(UserContext.getLoginUserID());
			punishmentInfo.setUpdatedByName(UserContext.getLoginUserName());
			punishmentInfo.setUpdatedDate(DateUtil.now());
			punishmentInfo.update();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtPunishmentInfoService#delete(java.lang.Long)
	 */
	public void delete(Long punishmentInfoOid) throws ServiceException {	
		PtPunishmentInfo ptPunishmentInfo = DaoUtil.get(PtPunishmentInfo.class, punishmentInfoOid);
		if(null != ptPunishmentInfo){
			ptPunishmentInfo.delete();	
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see PtPunishmentInfoService#findPtPunishmentInfoByCondition(java.lang.Long)
	 */
	public List<PtPunishmentInfoDTO> findPtPunishmentInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
			return PtPunishmentInfoQueryHelper.listPtPunishmentInfo(bizPersonOid);	
	}

	

}

package com.yh.hr.res.pt.service.impl;

import java.util.List;
import com.yh.hr.res.pt.bo.PtFamilyInfo;
import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtFamilyInfoQueryHelper;
import com.yh.hr.res.pt.service.PtFamilyInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @description The implement for PtFamilyInfo service
 * @author xiongyx
 * @created 2016-10-10
 * @version 1.0
 */
public class PtFamilyInfoServiceImpl implements PtFamilyInfoService
{
	/**
	 * insert PtFamilyInfo obj
	 */
	public void createPtFamilyInfo(PtFamilyInfoDTO PtFamilyInfoDto) throws ServiceException
	{
		PtFamilyInfo ptFamilyInfo = new PtFamilyInfo();
		BeanHelper.copyProperties(PtFamilyInfoDto, ptFamilyInfo);
		ptFamilyInfo.setCreateBy(UserContext.getLoginUserID());
		ptFamilyInfo.setCreateName(UserContext.getLoginUserName());
		ptFamilyInfo.setCreateDate(DateUtil.now());
		ptFamilyInfo.save();
	}
	
	/**
	 * get PtFamilyInfo obj
	 */
	public PtFamilyInfoDTO findPtFamilyInfoById(Long familyOid) throws ServiceException
	{
		PtFamilyInfo ptFamilyInfo = DaoUtil.get(PtFamilyInfo.class, familyOid);
		PtFamilyInfoDTO ptFamilyInfoDto = new PtFamilyInfoDTO();
		BeanHelper.copyProperties(ptFamilyInfo, ptFamilyInfoDto);
		
		return ptFamilyInfoDto;
	}
	
	/**
	 * update PtFamilyInfo obj
	 */
	public void updatePtFamilyInfo(PtFamilyInfoDTO ptFamilyInfoDto) throws ServiceException
	{
		PtFamilyInfo ptFamilyInfo = DaoUtil.get(PtFamilyInfo.class, ptFamilyInfoDto.getPtFamilyOid());
		if(null != ptFamilyInfo){
			BeanHelper.copyProperties(ptFamilyInfoDto, ptFamilyInfo,BeanHelper.getNullPropertyNames(ptFamilyInfoDto));
			ptFamilyInfo.setUpdateBy(UserContext.getLoginUserID());
			ptFamilyInfo.setUpdateName(UserContext.getLoginUserName());
			ptFamilyInfo.setUpdateDate(DateUtil.now());
			ptFamilyInfo.update();
		}

	}
	
	/**
	 * delete PtFamilyInfo obj
	 */
	public void deletePtFamilyInfo(Long familyOid) throws ServiceException
	{
		PtFamilyInfo ptFamilyInfo = DaoUtil.get(PtFamilyInfo.class, familyOid);
		if(null != ptFamilyInfo){
			ptFamilyInfo.delete();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtFamilyInfoService#listPtFamilyInfoByPersonOid(java.lang.Long)
	 */
	public List<PtFamilyInfoDTO> listPtFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtFamilyInfoQueryHelper.listPtFamilyInfoDTOByPersonId(personOid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtFamilyInfoService#countPtFamilyInfoByPersonOid(java.lang.Long)
	 */
	public int countPtFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtFamilyInfoQueryHelper.countPtFamilyInfoDTOByPersonId(personOid);
	}
}

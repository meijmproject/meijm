package com.yh.hr.res.pt.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtProfessionalInfo;
import com.yh.hr.res.pt.dto.PtProfessionalInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtProfessionalInfoQueryHelper;
import com.yh.hr.res.pt.service.PtProfessionalInfoService;
import com.yh.hr.res.util.SortProfessionalInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @description The implement for PtProfessionalInfo service
 * @author lihj
 * @created 2016-10-26
 * @version 1.0
 */
public class PtProfessionalInfoServiceImpl implements PtProfessionalInfoService
{
	/**
	 * insert PtProfessionalInfo obj
	 */
	public void createPtProfessionalInfo(PtProfessionalInfoDTO ptProfessionalDTo) throws ServiceException
	{
		PtProfessionalInfo ptProfessionalInfo = new PtProfessionalInfo();
		BeanHelper.copyProperties(ptProfessionalDTo, ptProfessionalInfo);
		ptProfessionalInfo.setCreatedByCode(UserContext.getLoginUserID());
		ptProfessionalInfo.setCreatedByName(UserContext.getLoginUserName());
		ptProfessionalInfo.setCreatedDate(DateUtil.now());
		ptProfessionalInfo.save();
		setHighestLevel(ptProfessionalInfo.getBizPersonOid());
	}
	
	/**
	 * get PtProfessionalInfo obj
	 */
	public PtProfessionalInfoDTO findPtProfessionalInfoById(Long professionalOid) throws ServiceException
	{
		PtProfessionalInfo ptProfessionalInfo = DaoUtil.get(PtProfessionalInfo.class, professionalOid);
		PtProfessionalInfoDTO ptProfessionalInfoDto = new PtProfessionalInfoDTO();
		BeanHelper.copyProperties(ptProfessionalInfo, ptProfessionalInfoDto);
		
		return ptProfessionalInfoDto;
	}
	
	/**
	 * update PtProfessionalInfo obj
	 */
	public void updatePtProfessionalInfo(PtProfessionalInfoDTO ptProfessionalDTo) throws ServiceException
	{
		PtProfessionalInfo ptProfessionalInfo = DaoUtil.get(PtProfessionalInfo.class, ptProfessionalDTo.getPtProfessionalOid());
		if(null != ptProfessionalInfo){
			BeanHelper.copyProperties(ptProfessionalDTo, ptProfessionalInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptProfessionalInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptProfessionalInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptProfessionalInfo.setUpdatedDate(DateUtil.now());
			ptProfessionalInfo.update();
			setHighestLevel(ptProfessionalInfo.getBizPersonOid());
		}

	}
	
	/**
	 * delete PtProfessionalInfo obj
	 */
	public void deletePtProfessionalInfo(Long professionalOid) throws ServiceException
	{
		PtProfessionalInfo ptProfessionalInfo = DaoUtil.get(PtProfessionalInfo.class, professionalOid);
		if(null != ptProfessionalInfo){
			ptProfessionalInfo.delete();
			setHighestLevel(ptProfessionalInfo.getBizPersonOid());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtProfessionalInfoService#listPtProfessionalInfoByPersonOid(java.lang.Long)
	 */
	public List<PtProfessionalInfoDTO> listPtProfessionalInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtProfessionalInfoQueryHelper.listPtProfessionalInfoDTOByPersonId(personOid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtProfessionalInfoService#countPtProfessionalInfoByPersonOid(java.lang.Long)
	 */
	public int countPtProfessionalInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtProfessionalInfoQueryHelper.countPtProfessionalDTOByPersonId(personOid);
	}
	/**
	 * 设置最高职业技术资格
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	private void setHighestLevel(Long bizPersonOid) throws ServiceException
	{
		List<PtProfessionalInfoDTO> list = PtProfessionalInfoQueryHelper.listPtProfessionalInfoDTOByPersonId(bizPersonOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			Collections.sort(list, new SortProfessionalInfo());
			for(int i=0;i<list.size();i++)
			{
				PtProfessionalInfo ptProfessionalInfo = DaoUtil.get(PtProfessionalInfo.class,list.get(i).getPtProfessionalOid());
				ptProfessionalInfo.setIsHighestLevel(i==0? DicConstants.YHRS0003_1:DicConstants.YHRS0003_0);
				ptProfessionalInfo.setUpdatedByCode(UserContext.getLoginUserID());
				ptProfessionalInfo.setUpdatedByName(UserContext.getLoginUserName());
				ptProfessionalInfo.setUpdatedDate(new Date());
				ptProfessionalInfo.update();
			}
		}
	}
}

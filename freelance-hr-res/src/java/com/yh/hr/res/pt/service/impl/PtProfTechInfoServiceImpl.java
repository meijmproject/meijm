package com.yh.hr.res.pt.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtProfTechInfo;
import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtProfTechInfoQueryHelper;
import com.yh.hr.res.pt.service.PtProfTechInfoService;
import com.yh.hr.res.util.SortProfTechInfo;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @description The implement for PtProfTechInfo service
 * @author lihj
 * @created 2016-10-26
 * @version 1.0
 */
public class PtProfTechInfoServiceImpl implements PtProfTechInfoService
{
	/**
	 * insert PtProfTechInfo obj
	 */
	public void createPtProfTechInfo(PtProfTechInfoDTO ptProfTechInfoDto) throws ServiceException
	{
		PtProfTechInfo ptProfTechInfo = new PtProfTechInfo();
		BeanHelper.copyProperties(ptProfTechInfoDto, ptProfTechInfo);
		ptProfTechInfo.setCreatedByCode(UserContext.getLoginUserID());
		ptProfTechInfo.setCreatedByName(UserContext.getLoginUserName());
		ptProfTechInfo.setCreatedDate(DateUtil.now());
		ptProfTechInfo.save();
		setHighestLevel(ptProfTechInfo.getBizPersonOid());
	}
	
	/**
	 * get PtProfTechInfo obj
	 */
	public PtProfTechInfoDTO findPtProfTechInfoById(Long profTechInOid) throws ServiceException
	{
		PtProfTechInfo ptProfTechInfo = DaoUtil.get(PtProfTechInfo.class, profTechInOid);
		PtProfTechInfoDTO ptProfTechInfoDto = new PtProfTechInfoDTO();
		BeanHelper.copyProperties(ptProfTechInfo, ptProfTechInfoDto);
		
		return ptProfTechInfoDto;
	}
	
	/**
	 * update PtProfTechInfo obj
	 */
	public void updatePtProfTechInfo(PtProfTechInfoDTO ptProfTechInfoDto) throws ServiceException
	{
		PtProfTechInfo ptProfTechInfo = DaoUtil.get(PtProfTechInfo.class, ptProfTechInfoDto.getPtProfTechOid());
		if(null != ptProfTechInfo){
			BeanHelper.copyProperties(ptProfTechInfoDto, ptProfTechInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptProfTechInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptProfTechInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptProfTechInfo.setUpdatedDate(DateUtil.now());
			ptProfTechInfo.update();
			setHighestLevel(ptProfTechInfo.getBizPersonOid());
		}

	}
	
	/**
	 * delete PtProfTechInfo obj
	 */
	public void deletePtProfTechInfo(Long profTechInOid) throws ServiceException
	{
		PtProfTechInfo ptProfTechInfo = DaoUtil.get(PtProfTechInfo.class, profTechInOid);
		if(null != ptProfTechInfo){
			ptProfTechInfo.delete();
			setHighestLevel(ptProfTechInfo.getBizPersonOid());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtProfTechInfoService#listPtProfTechInfoByPersonOid(java.lang.Long)
	 */
	public List<PtProfTechInfoDTO> listPtProfTechInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtProfTechInfoQueryHelper.listPtProfTechInfoDTOByPersonId(personOid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtProfTechInfoService#countPtProfTechInfoByPersonOid(java.lang.Long)
	 */
	public int countPtProfTechInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PtProfTechInfoQueryHelper.countPtProfTechInfoDTOByPersonId(personOid);
	}

	/**
	 * 设置最高专业技术资格
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	private void setHighestLevel(Long bizPersonOid) throws ServiceException
	{
		List<PtProfTechInfoDTO> list = PtProfTechInfoQueryHelper.listPtProfTechInfoDTOByPersonId(bizPersonOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			Collections.sort(list, new SortProfTechInfo());
			for(int i=0;i<list.size();i++)
			{
				PtProfTechInfo ptProfTechInfo = DaoUtil.get(PtProfTechInfo.class,list.get(i).getPtProfTechOid());
				ptProfTechInfo.setIsHighestLevel(i==0? DicConstants.YHRS0003_1:DicConstants.YHRS0003_0);
				ptProfTechInfo.setUpdatedByCode(UserContext.getLoginUserID());
				ptProfTechInfo.setUpdatedByName(UserContext.getLoginUserName());
				ptProfTechInfo.setUpdatedDate(new Date());
				ptProfTechInfo.update();
			}
		}
	}
}

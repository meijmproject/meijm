package com.yh.hr.res.pb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.service.PbFamilyInfoService;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pb.bo.PbFamilyInfo;
import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @description The implement for PbFamilyInfo service
 * @author chenp
 * @created 2017-02-16
 * @version 1.0
 */
public class PbFamilyInfoServiceImpl implements PbFamilyInfoService
{
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#createPbFamilyInfo(PbFamilyInfoDTO)
	 */
	public void createPbFamilyInfo(PbFamilyInfoDTO pbFamilyInfoDto) throws ServiceException
	{
		PbFamilyInfo pbFamilyInfo = new PbFamilyInfo();
		BeanHelper.copyProperties(pbFamilyInfoDto, pbFamilyInfo);
		pbFamilyInfo.setCreateBy(UserContext.getLoginUserID());
		pbFamilyInfo.setCreateName(UserContext.getLoginUserName());
		pbFamilyInfo.setCreateDate(DateUtil.now());
		pbFamilyInfo.save();
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#findPbFamilyInfoById(java.lang.Long)
	 */
	public PbFamilyInfoDTO findPbFamilyInfoById(Long familyOid) throws ServiceException
	{
		PbFamilyInfo pbFamilyInfo = DaoUtil.get(PbFamilyInfo.class, familyOid);
		PbFamilyInfoDTO pbFamilyInfoDto = new PbFamilyInfoDTO();
		BeanHelper.copyProperties(pbFamilyInfo, pbFamilyInfoDto);
		return pbFamilyInfoDto;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#updatePbFamilyInfo(PbFamilyInfoDTO)
	 */
	public void updatePbFamilyInfo(PbFamilyInfoDTO pbFamilyInfoDto) throws ServiceException
	{
		PbFamilyInfo pbFamilyInfo = new PbFamilyInfo();
		BeanHelper.copyProperties(pbFamilyInfoDto, pbFamilyInfo);
		pbFamilyInfo.setUpdateBy(UserContext.getLoginUserID());
		pbFamilyInfo.setUpdateName(UserContext.getLoginUserName());
		pbFamilyInfo.setUpdateDate(DateUtil.now());
		pbFamilyInfo.update();
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#deletePbFamilyInfo(java.lang.Long)
	 */
	public void deletePbFamilyInfo(Long familyOid) throws ServiceException
	{
		DaoUtil.get(PbFamilyInfo.class, familyOid).delete();
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#listPbFamilyInfoByPersonOid(java.lang.Long)
	 */
	public List<PbFamilyInfoDTO> listPbFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		String hql = "from PbFamilyInfo fi where fi.personOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbFamilyInfo> boList = DaoUtil.find(hql, params);
		List<PbFamilyInfoDTO> dtoList = new ArrayList<PbFamilyInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbFamilyInfo bo : boList)
			{
				PbFamilyInfoDTO dto = new PbFamilyInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbFamilyInfoService#countPbFamilyInfoByPersonOid(java.lang.Long)
	 */
	public int countPbFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		String hql = "select count(*) from PbFamilyInfo fi where fi.personOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		return DaoUtil.countByCondition(hql, params);
	}
}

package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtFamilyInfo;
import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import org.springframework.util.CollectionUtils;

import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 家庭成员与社会关系查询工具类
 * @author xiongyx
 * @createDate 2016-10-10
 */
public class PtFamilyInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtFamilyInfoDTO getPtFamilyInfoDTOById(Long familyOid) throws ServiceException
	{
		PtFamilyInfo ptFamilyInfo = DaoUtil.get(PtFamilyInfo.class, familyOid);
		PtFamilyInfoDTO PtFamilyInfoDto = new PtFamilyInfoDTO();
		BeanHelper.copyProperties(ptFamilyInfo, PtFamilyInfoDto);
		
		return PtFamilyInfoDto;
	}
	
	/**
	 * 通过personOid获取
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtFamilyInfoDTO> listPtFamilyInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PtFamilyInfo fi where fi.bizPersonOid = :personOid order by fi.createDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PtFamilyInfo> boList = DaoUtil.find(hql, params);
		List<PtFamilyInfoDTO> dtoList = new ArrayList<PtFamilyInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtFamilyInfo bo : boList)
			{
				PtFamilyInfoDTO dto = new PtFamilyInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	/**
	 * 通过personOid获取配偶信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtFamilyInfoDTO findSpouseByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PtFamilyInfo fi where fi.bizPersonOid = :personOid and fi.relationship in(" + DicConstants.YHRS0024_11 + "," + DicConstants.YHRS0024_12 + ")";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PtFamilyInfo> boList = DaoUtil.find(hql, params);
		
		if(!CollectionUtils.isEmpty(boList))
		{
			PtFamilyInfoDTO PtFamilyInfoDto = new PtFamilyInfoDTO();
			BeanHelper.copyProperties(boList.get(0), PtFamilyInfoDto);
			return PtFamilyInfoDto;
		}
		return null;
	}
	
	/**
	 * 通过personOid获取随迁人员信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtFamilyInfoDTO> listSQPtFamilyInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PtFamilyInfo fi where fi.bizPersonOid = :personOid and fi.isTransfer = :isTransfer order by fi.createdDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		params.put("isTransfer", Constant.YES);
		List<PtFamilyInfo> boList = DaoUtil.find(hql, params);
		List<PtFamilyInfoDTO> dtoList = new ArrayList<PtFamilyInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtFamilyInfo bo : boList)
			{
				PtFamilyInfoDTO dto = new PtFamilyInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtFamilyInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "select count(*) from PtFamilyInfo fi where fi.bizPersonOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 根据bizPersonOid删除家庭成员信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtFamilyInfo fi where fi.bizPersonOid = " + bizPersonOid);
	}
}

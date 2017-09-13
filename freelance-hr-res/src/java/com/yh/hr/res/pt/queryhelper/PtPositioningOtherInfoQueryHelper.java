package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.bo.PtPositioningOtherInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 人员其他职务信息工具类
 * @author lihj
 * @createDate 2016-10-26
 */
public class PtPositioningOtherInfoQueryHelper {
	/**
	 * 通过主键获取
	 * @param ptPositioningOtherOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPositioningOtherInfoDTO getPtPositioningOtherDTOById(Long ptPositioningOtherOid) throws ServiceException
	{
		PtPositioningOtherInfo ptPositioningOtherInfo = DaoUtil.get(PtPositioningOtherInfo.class,ptPositioningOtherOid);
		return BeanHelper.copyProperties(ptPositioningOtherInfo, PtPositioningOtherInfoDTO.class);
	}
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPositioningOtherInfoDTO> listPtPositioningOtherDTOByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPositioningOtherInfo ei where ei.bizPersonOid = :bizPersonOid order by ei.createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPositioningOtherInfo> boList = DaoUtil.find(hql, params);
		List<PtPositioningOtherInfoDTO> dtoList = new ArrayList<PtPositioningOtherInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtPositioningOtherInfoDTO.class);
		}
		
		return dtoList;
	}
	
	/**
	 * 通过bizPersonOid以及positioningOtherCode获取
	 * @param bizPersonOid
	 * @param positioningOtherCodeList
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPositioningOtherInfoDTO> listPtPositioningOtherDTOByPositioningOtherCode(Long bizPersonOid, List<String> positioningOtherCodeList) throws ServiceException
	{
		String hql = "from PtPositioningOtherInfo ei where ei.bizPersonOid = :bizPersonOid and ei.positioningOtherCode in (:positioningOtherCodeList) order by ei.createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		params.put("positioningOtherCodeList", positioningOtherCodeList);
		List<PtPositioningOtherInfo> boList = DaoUtil.find(hql, params);
		List<PtPositioningOtherInfoDTO> dtoList = new ArrayList<PtPositioningOtherInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtPositioningOtherInfoDTO.class);
		}
		
		return dtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtPositioningOtherDTOByBizPersonId(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtPositioningOtherInfo ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 根据bizPersonOid删除学历学位信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPositioningOtherInfo ei where ei.bizPersonOid = " + bizPersonOid);
	}
}
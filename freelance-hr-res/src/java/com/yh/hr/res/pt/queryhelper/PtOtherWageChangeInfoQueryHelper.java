package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtOtherWageChangeInfo;
import com.yh.hr.res.pt.dto.PtOtherWageChangeInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 其他工资变动信息查询工具类
 * @author wuxq
 * @createDate 2016-12-08
 */
public class PtOtherWageChangeInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param ptOtherWageChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtOtherWageChangeInfoDTO findPtOtherWageChangeInfoDTOById(Long ptOtherWageChangeOid) throws ServiceException
	{
		PtOtherWageChangeInfoDTO ptOtherWageChangeInfoDTO = DaoUtil.get(PtOtherWageChangeInfoDTO.class, ptOtherWageChangeOid);
		return BeanHelper.copyProperties(ptOtherWageChangeInfoDTO, PtOtherWageChangeInfoDTO.class);
	}
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtOtherWageChangeInfoDTO> listPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtOtherWageChangeInfo ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtOtherWageChangeInfo> boList = DaoUtil.find(hql, params);
		List<PtOtherWageChangeInfoDTO> dtoList = new ArrayList<PtOtherWageChangeInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtOtherWageChangeInfoDTO.class);
		}
		return dtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{                 
		String hql = "select count(*) from PtOtherWageChangeInfo ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
	/**
	 * 根据bizPersonOid删除其他工资变动信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtOtherWageChangeInfo ei where ei.bizPersonOid = " + bizPersonOid);
	}
}

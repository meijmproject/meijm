package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gt.bo.GtOtherInfo;
import com.yh.hr.res.gt.dto.GtOtherInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class GtOtherInfoQueryHelper {

	/**
	 * 根据ID查询
	 * @param jhgGtOtherInfo
	 * @return
	 * @throws ServiceException
	 */
	public static GtOtherInfoDTO getGtOtherInfoDTOById(Long jhgGtOtherInfo) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GtOtherInfo.class, jhgGtOtherInfo), GtOtherInfoDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtOtherInfoDTO> listGtOtherInfoDTOByUnitOid(Long unitOid, Long taskOid) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from GtOtherInfo goi where 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != unitOid)
		{
			hql.append(" and goi.unitOid = :unitOid");
			params.put("unitOid", unitOid);
		}
		if(null != taskOid)
		{
			hql.append(" and goi.taskOid = :taskOid");
			params.put("taskOid", taskOid);
		}
		List<GtOtherInfo> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtOtherInfoDTO.class);
		}
		return null;
	}
}

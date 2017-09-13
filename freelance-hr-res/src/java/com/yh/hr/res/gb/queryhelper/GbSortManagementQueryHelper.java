package com.yh.hr.res.gb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gb.bo.GbSortManagement;
import com.yh.hr.res.gb.dto.GbSortManagementDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 信息查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GbSortManagementQueryHelper {

	/**
	 * 根据Id查询
	 * @param gbSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public static GbSortManagementDTO getGbSortManagementById(Long gbSortManagementOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GbSortManagement.class, gbSortManagementOid), GbSortManagementDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbSortManagementDTO> listGbSortManagementByUnitOid(Long unitOid) throws ServiceException
	{
		String hql = "from GbSortManagement gsm where gsm.unitOid = :unitOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		List<GbSortManagement> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbSortManagementDTO.class);
		}
		return null;
	}
}

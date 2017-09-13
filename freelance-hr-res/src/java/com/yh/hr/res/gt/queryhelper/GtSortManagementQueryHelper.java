package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gt.dto.GtSortManagementDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gt.bo.GtSortManagement;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 信息查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GtSortManagementQueryHelper {

	/**
	 * 根据ID查询
	 * @param gtSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public static GtSortManagementDTO getGtSortManagementDTOById(Long gtSortManagementOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GtSortManagement.class, gtSortManagementOid), GtSortManagementDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtSortManagementDTO> listGtSortManagementDTOByUnitOid(Long unitOid) throws ServiceException
	{
		String hql = "from GtSortManagement gsm where gsm.unitOid = :unitOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		List<GtSortManagement> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtSortManagementDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据taskOid查询
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtSortManagementDTO> listGtSortManagementDTOByTaskOid(Long taskOid) throws ServiceException
	{
		String hql = "from GtSortManagement gsm where gsm.taskOid = :taskOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskOid", taskOid);
		List<GtSortManagement> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtSortManagementDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据unitOid及taskOid查询
	 * @param unitOid
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtSortManagementDTO> listGtSortManagementDTO(Long unitOid, Long taskOid) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from GtSortManagement gsm where 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != unitOid)
		{
			hql.append(" and gsm.unitOid = :unitOid");
			params.put("unitOid", unitOid);
		}
		if(null != taskOid)
		{
			hql.append(" and gsm.taskOid = :taskOid");
			params.put("taskOid", taskOid);
		}
		List<GtSortManagement> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtSortManagementDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据taskOid删除
	 * @param taskOid
	 * @throws ServiceException
	 */
	public static void deleteGtSortManagementByTaskOid(Long taskOid) throws ServiceException
	{
		DaoUtil.executeUpdate("delete from GtSortManagement gsm where gsm.taskOid = " + taskOid);
	}
}

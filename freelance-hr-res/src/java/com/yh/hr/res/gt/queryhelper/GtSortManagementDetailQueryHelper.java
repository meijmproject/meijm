package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gt.bo.GtSortManagementDetail;
import com.yh.hr.res.gt.dto.GtSortManagementDetailDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 信息查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GtSortManagementDetailQueryHelper {

	/**
	 * 根据Id查询
	 * @param gtSortManagementDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public static GtSortManagementDetailDTO getGtSortManagementDetailById(Long gtSortManagementDetailOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GtSortManagementDetail.class, gtSortManagementDetailOid), GtSortManagementDetailDTO.class);
	}
	
	/**
	 * 根据gtSortManagementOid查询
	 * @param gtSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtSortManagementDetailDTO> listGtSortManagementDetailByManageId(Long gtSortManagementOid) throws ServiceException
	{
		String hql = "from GtSortManagementDetail gsmd where gsmd.gtSortManagementOid = :gtSortManagementOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gtSortManagementOid", gtSortManagementOid);
		List<GtSortManagementDetail> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtSortManagementDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据岗位信息查询
	 * @param gtSortManagementOid
	 * @param postType
	 * @param postSetType
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtSortManagementDetailDTO> listGtSortManagementDetailByPostInfo(Long gtSortManagementOid, String postType, String postSetType) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from GtSortManagementDetail gsmd where gsmd.gtSortManagementOid = :gtSortManagementOid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gtSortManagementOid", gtSortManagementOid);
		if(StringUtils.isNotEmpty(postType))
		{
			hql.append(" and gsmd.postType = :postType");
			params.put("postType", postType);
		}
		if(StringUtils.isNotEmpty(postSetType))
		{
			hql.append(" and gsmd.postSetType = :postSetType");
			params.put("postSetType", postSetType);
		}
		List<GtSortManagementDetail> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtSortManagementDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据gtSortManagementOid删除
	 * @param gtSortManagementOid
	 * @throws ServiceException
	 */
	public static void deleteGtSortManagementDetailByManageId(Long gtSortManagementOid) throws ServiceException
	{
		DaoUtil.executeUpdate("delete from GtSortManagementDetail gsmd where gsmd.gtSortManagementOid = " + gtSortManagementOid);
	}
	
	/**
	 * 根据岗位信息删除
	 * @param taskOid
	 * @param postType
	 * @throws ServiceException
	 */
	public static void deleteGtSortManagementDetailByPostInfo(Long taskOid, String postType) throws ServiceException
	{
		DaoUtil.executeUpdate("delete from GtSortManagementDetail gsmd where gsmd.gtSortManagementOid in" +
				" (select gsm.gtSortManagementOid from GtSortManagement gsm where gsm.taskOid = " + taskOid + ") and gsmd.postType = '" + postType + "'");
	}
}

package com.yh.hr.res.gb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gb.bo.GbSortManagementDetail;
import com.yh.hr.res.gb.dto.GbSortManagementDetailDTO;
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
public class GbSortManagementDetailQueryHelper {

	/**
	 * 根据Id查询
	 * @param gbSortManagementDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public static GbSortManagementDetailDTO getGbSortManagementDetailById(Long gbSortManagementDetailOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GbSortManagementDetail.class, gbSortManagementDetailOid), GbSortManagementDetailDTO.class);
	}
	
	/**
	 * 根据gbSortManagementOid查询
	 * @param gbSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbSortManagementDetailDTO> listGbSortManagementDetailByManageId(Long gbSortManagementOid) throws ServiceException
	{
		String hql = "from GbSortManagementDetail gsmd where gsmd.gbSortManagementOid = :gbSortManagementOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gbSortManagementOid", gbSortManagementOid);
		List<GbSortManagementDetail> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbSortManagementDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据岗位信息查询
	 * @param gbSortManagementOid
	 * @param postType
	 * @param postSetType
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbSortManagementDetailDTO> listGbSortManagementDetailByPostInfo(Long gbSortManagementOid, String postType, String postSetType) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from GbSortManagementDetail gsmd where gsmd.gbSortManagementOid = :gbSortManagementOid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gbSortManagementOid", gbSortManagementOid);
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
		List<GbSortManagementDetail> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbSortManagementDetailDTO.class);
		}
		return null;
	}
}

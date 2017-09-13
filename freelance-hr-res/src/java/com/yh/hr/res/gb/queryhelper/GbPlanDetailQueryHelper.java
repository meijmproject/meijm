package com.yh.hr.res.gb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gb.bo.GbPlanDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.gb.dto.GbPlanDetailDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 信息查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GbPlanDetailQueryHelper {

	/**
	 * 根据Id查询
	 * @param jhgGbPlanDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public static GbPlanDetailDTO getGbPlanDetailById(Long jhgGbPlanDetailOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GbPlanDetail.class, jhgGbPlanDetailOid), GbPlanDetailDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbPlanDetailDTO> listGbPlanDetailByUnitOid(Long unitOid) throws ServiceException
	{
		String hql = "from GbPlanDetail gp where gp.unitOid = :unitOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		List<GbPlanDetail> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据岗位信息查询
	 * @param unitOid
	 * @param postType
	 * @param postLevel
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbPlanDetailDTO> listGbPlanDetailDTOByPostInfo(Long unitOid, String postType, String postLevel) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from GbPlanDetail gp where 1 = 1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != unitOid)
		{
			hql.append("and gp.unitOid = :unitOid ");
			params.put("unitOid", unitOid);
		}
		if(StringUtils.isNotBlank(postType))
		{
			hql.append("and gp.postType = :postType ");
			params.put("postType", postType);
		}
		if(StringUtils.isNotBlank(postLevel))
		{
			hql.append("and gp.postLevel = :postLevel ");
			params.put("postLevel", postLevel);
		}
		List<GbPlanDetail> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbPlanDetailDTO.class);
		}
		return null;
	}
}

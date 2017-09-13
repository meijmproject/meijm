package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gt.bo.GtPlanDetail;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GtPlanDetailQueryHelper {

	/**
	 * 根据ID查询
	 * @param jhgGtPlanDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public static GtPlanDetailDTO getGtPlanDetailDTOById(Long jhgGtPlanDetailOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GtPlanDetail.class, jhgGtPlanDetailOid), GtPlanDetailDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtPlanDetailDTO> listGtPlanDetailDTOByUnitOid(Long unitOid) throws ServiceException
	{
		String hql = "select gp from GtPlanDetail gp,BtTask bt where gp.taskOid = bt.taskOid and gp.unitOid = :unitOid and bt.completeTime is null ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		List<GtPlanDetail> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据taskOid查询
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtPlanDetailDTO> listGtPlanDetailDTOByTaskOid(Long taskOid) throws ServiceException
	{
		String hql = "from GtPlanDetail gp where gp.taskOid = :taskOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskOid", taskOid);
		List<GtPlanDetail> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据岗位信息查询
	 * @param taskOid
	 * @param unitOid
	 * @param postType
	 * @param postLevel
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtPlanDetailDTO> listGtPlanDetailDTOByPostInfo(Long taskOid, Long unitOid, String postType, String postLevel) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("select gp from GtPlanDetail gp,BtTask bt where gp.taskOid = bt.taskOid ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != taskOid)
		{
			hql.append("and gp.taskOid = :taskOid ");
			params.put("taskOid", taskOid);
		}
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
		List<GtPlanDetail> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据taskOid删除
	 * @param taskOid
	 * @throws ServiceException
	 */
	public static void deleteGtPlanDetailByTaskOid(Long taskOid) throws ServiceException {
		
		DaoUtil.executeUpdate("delete from GtPlanDetail gpd where gpd.taskOid = " + taskOid);
	}
}

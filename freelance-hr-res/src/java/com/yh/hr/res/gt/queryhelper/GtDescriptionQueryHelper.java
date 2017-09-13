package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gt.bo.GtDescription;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gt.dto.GtDescriptionDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位说明信息查询工具类（业务）
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GtDescriptionQueryHelper {

	/**
	 * 根据ID查询
	 * @param jhgGtDescriptionOid
	 * @return
	 * @throws ServiceException
	 */
	public static GtDescriptionDTO getGtDescriptionDTOById(Long jhgGtDescriptionOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GtDescription.class, jhgGtDescriptionOid), GtDescriptionDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtDescriptionDTO> listGtDescriptionDTOByUnitOid(Long unitOid,Long taskOid) throws ServiceException
	{
		String hql = "from GtDescription gd where gd.unitOid = :unitOid and gd.taskOid =:taskOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		params.put("taskOid", taskOid);
		List<GtDescription> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtDescriptionDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据taskOid查询
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GtDescriptionDTO> listGtDescriptionDTOByTaskOid(Long taskOid) throws ServiceException
	{
		String hql = "from GtDescription gd where gd.taskOid = :taskOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskOid", taskOid);
		List<GtDescription> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GtDescriptionDTO.class);
		}
		return null;
	}
	
	public static Long createId() throws ServiceException
	{
		Long id=null;
		String hql = " select to_char(SEQ_POST_ID.nextval) from dual";
		@SuppressWarnings("unchecked")
		List<String> list = jade.workflow.utils.DaoUtil.createSQLQuery(hql);
		if (CollectionUtils.isNotEmpty(list)) {
			id=Long.valueOf(list.get(0));
		}
		return id;
	}
}

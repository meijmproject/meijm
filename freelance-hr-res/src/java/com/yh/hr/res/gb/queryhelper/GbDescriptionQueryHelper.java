package com.yh.hr.res.gb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gb.bo.GbDescription;
import com.yh.hr.res.gb.dto.GbDescriptionDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位说明信息查询工具类
 * @author huangyj
 * @createDate 2016-12-14
 */
public class GbDescriptionQueryHelper {

	/**
	 * 根据Id查询
	 * @param jhgGbDescriptionOid
	 * @return
	 * @throws ServiceException
	 */
	public static GbDescriptionDTO getGbDescriptionById(Long jhgGbDescriptionOid) throws ServiceException
	{
		return BeanHelper.copyProperties(DaoUtil.get(GbDescription.class, jhgGbDescriptionOid), GbDescriptionDTO.class);
	}
	
	/**
	 * 根据unitOid查询
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbDescriptionDTO> listGbDescriptionByUnitOid(Long unitOid) throws ServiceException
	{
		String hql = "from GbDescription gd where gd.unitOid = :unitOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		List<GbDescription> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbDescriptionDTO.class);
		}
		return null;
	}
}

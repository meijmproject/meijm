package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtPostInfo;
import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位信息查询工具类（业务）
 * @author lihj
 *
 */
public class PtWagePostInfoQueryHelper
{
	
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPostInfoDTO> listWagePtPostDTOByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPostInfo pi where pi.bizPersonOid = :bizPersonOid and pi.specialPositionType in ('1','2') order by pi.beginDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPostInfo> boList = DaoUtil.find(hql, params);
		List<PtPostInfoDTO> dtoList = new ArrayList<PtPostInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtPostInfoDTO.class);
		}
		
		return dtoList;
	}
	
	
}

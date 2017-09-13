package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.dto.PtFileNoDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.res.pt.bo.PtFileNo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 文号信息查询工具类
 * @author lenghh
 *
 * 时间:2016-11-10下午03:09:39
 */
public class PtFileNoQueryHelper
{

	/**
	 * 根据文号类型、年份获取最大的文号信息
	 * @param ReportFileNoDTO
	 * @throws ServiceException
	 */
	public static PtFileNoDTO findMaxByFileTypeAndYear(String fileType, String year) throws ServiceException {
		String hql = "from PtFileNo fn where fn.fileNoType = :fileNoType and fn.fileNoYear = :fileNoYear order by fn.maxNo desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileNoType", fileType);
		params.put("fileNoYear", year);
		List<PtFileNo> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList)){
			return BeanHelper.copyProperties(boList.get(0), PtFileNoDTO.class);
		}
		return null;
	}
	
}

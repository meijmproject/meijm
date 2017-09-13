package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.res.pt.bo.PtReportFile;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 文号信息查询工具类
 * @author lenghh
 *
 * 时间:2016-11-10下午03:09:39
 */
public class PtReportFileQueryHelper
{
	/**
	 * 根据报表类型获取文号类型
	 * @param reportFileType
	 * @throws ServiceException
	 */
	public static String getFileNoType(String reportFileType) throws ServiceException {
		String hql = "from PtReportFile rf where rf.reportFileType = :reportFileType";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportFileType", reportFileType);
		List<PtReportFile> boList = DaoUtil.find(hql, params);
		if(CollectionUtils.isNotEmpty(boList)){
			return boList.get(0).getFileNoType();
		}
		return null;
	}
	
}

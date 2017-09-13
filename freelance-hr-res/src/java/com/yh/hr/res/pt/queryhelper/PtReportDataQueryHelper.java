package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.pt.bo.PtReportData;
import com.yh.hr.res.pt.dto.PtReportDataDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 报表打印数据查询工具类
 * @author huangyj
 * 2016-11-17
 */
public class PtReportDataQueryHelper
{
	/**
	 * 根据报表编码以及字段编码查询报表打印采集信息
	 * @param taskOid 业务事项ID
	 * @param reportCode 报表编码
	 * @param fieldCode 字段编码
	 * @return
	 * @throws ServiceException
	 */
	public static PtReportDataDTO findPtReportDataByCondition(Long taskOid, String reportCode, String fieldCode) throws ServiceException
	{
		StringBuffer hql = new StringBuffer("from PtReportData rd where rd.taskOid = :taskOid");
		if(StringUtils.isNotEmpty(reportCode))
		{
			hql.append(" and rd.reportCode = :reportCode");
		}
		if(StringUtils.isNotEmpty(fieldCode))
		{
			hql.append(" and rd.fieldCode = :fieldCode");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskOid", taskOid);
		params.put("reportCode", reportCode);
		params.put("fieldCode", fieldCode);
		
		List<PtReportData> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			PtReportData ptReportData = boList.get(0);
			return BeanHelper.copyProperties(ptReportData, PtReportDataDTO.class);
		}
		return null;
	}
}

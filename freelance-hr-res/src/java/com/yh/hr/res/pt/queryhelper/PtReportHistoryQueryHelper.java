package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.dto.PtReportHistoryDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 获取报表历史数据
 */
public class PtReportHistoryQueryHelper {


	/**
	 * 查询报表历史
	 * @param taskOid
	 * @param reportType
	 * @return
	 * @throws ServiceException
	 */
	public static PtReportHistoryDTO getPtReportHistory(Long taskOid, String reportType) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtReportHistory pt where  1 =1 ");
		hBuffer.append(" and  pt.taskOid =" + taskOid);
		hBuffer.append(" and  pt.reportType ='" + reportType+"'");
		List<PtReportHistoryDTO> list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtReportHistoryDTO.class);
		if(CollectionUtils.isNotEmpty(list))
		{
			return list.get(0);
		}
		return null;
	}
}
package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtReportData;
import com.yh.hr.res.pt.dto.PtReportDataDTO;
import com.yh.hr.res.pt.service.PtReportDataService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 报表打印采集信息service实现类
 * @author huangyj
 * 2016-11-18
 */
public class PtReportDataServiceImpl implements PtReportDataService
{
	/**
	 * 新增
	 */
	public void createPtReportData(PtReportDataDTO ptReportDataDTO) throws ServiceException
	{
		PtReportData ptReportData = BeanHelper.copyProperties(ptReportDataDTO, PtReportData.class);
		ptReportData.setCreatedByCode(UserContext.getLoginUserID());
		ptReportData.setCreatedByName(UserContext.getLoginUserName());
		ptReportData.setCreatedDate(DateUtil.now());
		ptReportData.save();
	}
	
	/**
	 * 修改
	 */
	public void updatePtReportData(PtReportDataDTO ptReportDataDTO) throws ServiceException
	{
		PtReportData ptReportData = DaoUtil.get(PtReportData.class, ptReportDataDTO.getPtReportDataOid());
		BeanHelper.copyProperties(ptReportDataDTO, ptReportData, new String[]{"createdDate","createdByCode","createdByName"});
		ptReportData.setUpdatedByCode(UserContext.getLoginUserID());
		ptReportData.setUpdatedByName(UserContext.getLoginUserName());
		ptReportData.setUpdatedDate(DateUtil.now());
		ptReportData.update();
	}
	
	/**
	 * 根据ID获取报表打印采集信息
	 */
	public PtReportDataDTO getPtReportData(Long ptReportDataOid) throws ServiceException
	{
		PtReportData ptReportData = DaoUtil.get(PtReportData.class, ptReportDataOid);
		if(null != ptReportData)
		{
			return BeanHelper.copyProperties(ptReportData, PtReportDataDTO.class);
		}
		return null;
	}
	
	/**
	 * 删除
	 */
	public void deletePtReportData(Long ptReportDataOid) throws ServiceException
	{
		PtReportData ptReportData = DaoUtil.get(PtReportData.class, ptReportDataOid);
		ptReportData.delete();
	}
}

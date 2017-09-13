package com.yh.hr.res.gt.queryhelper;

import java.util.List;

import com.yh.hr.res.gt.bo.GtSetPlan;
import com.yh.hr.res.gt.dto.GtSetPlanDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 岗位设置方案工具类
 * @author chenjl
 * @createDate 2016-12-12
 */
public class GtSetPlanQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static GtSetPlanDTO getGtSetPlanDTOById(Long gtSetPlanOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(GtSetPlan.class, gtSetPlanOid), GtSetPlanDTO.class);
	}
	
	/*
	 * 通过gtSetPlanOid获取
	 */
	public static List<GtSetPlanDTO> listGtSetPlanDTO(Long gtSetPlanOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from GtSetPlan pt where  1 =1 ");
		hBuffer.append(" and  pt.gtSetPlanOid =" + gtSetPlanOid);//必须是按人来查询
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), GtSetPlanDTO.class);
	}
	
	public static GtSetPlan getGtSetPlanByTaskOid(Long taskOid) throws ServiceException {
		return DaoUtil.uniqueResult("from GtSetPlan usp where usp.taskOid = ? ", taskOid);
	}
}
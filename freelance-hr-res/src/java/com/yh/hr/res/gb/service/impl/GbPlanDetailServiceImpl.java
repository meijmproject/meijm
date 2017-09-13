package com.yh.hr.res.gb.service.impl;

import com.yh.hr.res.gb.bo.GbPlanDetail;
import com.yh.hr.res.gb.dto.GbPlanDetailDTO;
import com.yh.hr.res.gb.service.GbPlanDetailService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类
 * @author huangyj
 */
public class GbPlanDetailServiceImpl implements GbPlanDetailService {

	/**
	 * 新增
	 */
	public void createGbPlanDetail(GbPlanDetailDTO gbPlanDetailDTO) throws ServiceException
	{
		GbPlanDetail gbPlanDetail = BeanHelper.copyProperties(gbPlanDetailDTO, GbPlanDetail.class);
		gbPlanDetail.setCreatedByCode(UserContext.getLoginUserID());
		gbPlanDetail.setCreatedByName(UserContext.getLoginUserName());
		gbPlanDetail.setCreatedDate(DateUtil.now());
		gbPlanDetail.save();
	}
	
	/**
	 * 查看
	 */
	public GbPlanDetailDTO findGbPlanDetailById(Long jhgGbPlanDetailOid) throws ServiceException
	{
		GbPlanDetail gbPlanDetail = DaoUtil.get(GbPlanDetail.class, jhgGbPlanDetailOid);
		if(null != gbPlanDetail)
		{
			return BeanHelper.copyProperties(gbPlanDetail, GbPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGbPlanDetail(GbPlanDetailDTO gbPlanDetailDTO) throws ServiceException
	{
		GbPlanDetail gbPlanDetail = DaoUtil.get(GbPlanDetail.class, gbPlanDetailDTO.getJhgGbPlanDetailOid());
		BeanHelper.copyProperties(gbPlanDetailDTO, gbPlanDetail, new String[]{"createdDate","createdByCode","createdByName"});
		gbPlanDetail.setUpdatedByCode(UserContext.getLoginUserID());
		gbPlanDetail.setUpdatedByName(UserContext.getLoginUserName());
		gbPlanDetail.setUpdatedDate(DateUtil.now());
		gbPlanDetail.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGbPlanDetail(Long jhgGbPlanDetailOid) throws ServiceException
	{
		GbPlanDetail gbPlanDetail = DaoUtil.get(GbPlanDetail.class, jhgGbPlanDetailOid);
		gbPlanDetail.delete();
	}
}

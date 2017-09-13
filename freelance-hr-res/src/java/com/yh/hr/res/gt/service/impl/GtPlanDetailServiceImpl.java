package com.yh.hr.res.gt.service.impl;

import com.yh.hr.res.gt.bo.GtPlanDetail;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.hr.res.gt.service.GtPlanDetailService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类（业务）
 * @author huangyj
 */
public class GtPlanDetailServiceImpl implements GtPlanDetailService {

	/**
	 * 新增
	 */
	public void createGtPlanDetail(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException
	{
		GtPlanDetail gtPlanDetail = BeanHelper.copyProperties(gtPlanDetailDTO, GtPlanDetail.class);
		gtPlanDetail.setCreatedByCode(UserContext.getLoginUserID());
		gtPlanDetail.setCreatedByName(UserContext.getLoginUserName());
		gtPlanDetail.setCreatedDate(DateUtil.now());
		gtPlanDetail.save();
	}
	
	/**
	 * 查询
	 */
	public GtPlanDetailDTO findGtPlanDetailById(Long jhgGtPlanDetailOid) throws ServiceException
	{
		GtPlanDetail gtPlanDetail = DaoUtil.get(GtPlanDetail.class, jhgGtPlanDetailOid);
		if(null != gtPlanDetail)
		{
			return BeanHelper.copyProperties(gtPlanDetail, GtPlanDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGtPlanDetail(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException
	{
		GtPlanDetail gtPlanDetail = DaoUtil.get(GtPlanDetail.class, gtPlanDetailDTO.getJhgGtPlanDetailOid());
		BeanHelper.copyProperties(gtPlanDetailDTO, gtPlanDetail, new String[]{"createdDate","createdByCode","createdByName"});
		gtPlanDetail.setUpdatedByCode(UserContext.getLoginUserID());
		gtPlanDetail.setUpdatedByName(UserContext.getLoginUserName());
		gtPlanDetail.setUpdatedDate(DateUtil.now());
		gtPlanDetail.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGtPlanDetail(Long jhgGtPlanDetailOid) throws ServiceException
	{
		GtPlanDetail gtPlanDetail = DaoUtil.get(GtPlanDetail.class, jhgGtPlanDetailOid);
		gtPlanDetail.delete();
	}
}

package com.yh.hr.res.gt.service.impl;

import java.util.List;

import com.yh.hr.res.gt.bo.GtSortManagementDetail;
import com.yh.hr.res.gt.dto.GtSortManagementDetailDTO;
import com.yh.hr.res.gt.queryhelper.GtSortManagementDetailQueryHelper;
import com.yh.hr.res.gt.service.GtSortManagementDetailService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类（业务）
 * @author huangyj
 */
public class GtSortManagementDetailServiceImpl implements GtSortManagementDetailService {

	/**
	 * 新增
	 */
	public void createGtSortManagementDetail(GtSortManagementDetailDTO gtSortManagementDetailDTO) throws ServiceException
	{
		GtSortManagementDetail gtSortManagementDetail = BeanHelper.copyProperties(gtSortManagementDetailDTO, GtSortManagementDetail.class);
		gtSortManagementDetail.setCreatedByCode(UserContext.getLoginUserID());
		gtSortManagementDetail.setCreatedByName(UserContext.getLoginUserName());
		gtSortManagementDetail.setCreatedDate(DateUtil.now());
		gtSortManagementDetail.save();
	}
	
	/**
	 * 查看
	 */
	public GtSortManagementDetailDTO findGtSortManagementDetailById(Long gtSortManagementDetailOid) throws ServiceException
	{
		GtSortManagementDetail gtSortManagementDetail = DaoUtil.get(GtSortManagementDetail.class, gtSortManagementDetailOid);
		if(null != gtSortManagementDetail)
		{
			return BeanHelper.copyProperties(gtSortManagementDetail, GtSortManagementDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGtSortManagementDetail(GtSortManagementDetailDTO gtSortManagementDetailDTO) throws ServiceException
	{
		GtSortManagementDetail gtSortManagementDetail = DaoUtil.get(GtSortManagementDetail.class, gtSortManagementDetailDTO.getGtSortManagementDetailOid());
		BeanHelper.copyProperties(gtSortManagementDetailDTO, gtSortManagementDetail, new String[]{"createdDate","createdByCode","createdByName"});
		gtSortManagementDetail.setUpdatedByCode(UserContext.getLoginUserID());
		gtSortManagementDetail.setUpdatedByName(UserContext.getLoginUserName());
		gtSortManagementDetail.setUpdatedDate(DateUtil.now());
		gtSortManagementDetail.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGtSortManagementDetail(Long gtSortManagementDetailOid) throws ServiceException
	{
		GtSortManagementDetail gtSortManagementDetail = DaoUtil.get(GtSortManagementDetail.class, gtSortManagementDetailOid);
		gtSortManagementDetail.delete();
	}

	public List<GtSortManagementDetailDTO> listGtSortManagementDetailByManageId(
			Long gtSortManagementOid) throws ServiceException {
		return GtSortManagementDetailQueryHelper.listGtSortManagementDetailByManageId(gtSortManagementOid);
	}
}

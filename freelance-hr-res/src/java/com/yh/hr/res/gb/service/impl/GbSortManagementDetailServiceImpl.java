package com.yh.hr.res.gb.service.impl;

import java.util.List;

import com.yh.hr.res.gb.dto.GbSortManagementDetailDTO;
import com.yh.hr.res.gb.service.GbSortManagementDetailService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gb.bo.GbSortManagementDetail;
import com.yh.hr.res.gb.queryhelper.GbSortManagementDetailQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类
 * @author huangyj
 */
public class GbSortManagementDetailServiceImpl implements GbSortManagementDetailService {

	/**
	 * 新增
	 */
	public void createGbSortManagementDetail(GbSortManagementDetailDTO gbSortManagementDetailDTO) throws ServiceException
	{
		GbSortManagementDetail gbSortManagementDetail = BeanHelper.copyProperties(gbSortManagementDetailDTO, GbSortManagementDetail.class);
		gbSortManagementDetail.setCreatedByCode(UserContext.getLoginUserID());
		gbSortManagementDetail.setCreatedByName(UserContext.getLoginUserName());
		gbSortManagementDetail.setCreatedDate(DateUtil.now());
		gbSortManagementDetail.save();
	}
	
	/**
	 * 查看
	 */
	public GbSortManagementDetailDTO findGbSortManagementDetailById(Long gbSortManagementDetailOid) throws ServiceException
	{
		GbSortManagementDetail gbSortManagementDetail = DaoUtil.get(GbSortManagementDetail.class, gbSortManagementDetailOid);
		if(null != gbSortManagementDetail)
		{
			return BeanHelper.copyProperties(gbSortManagementDetail, GbSortManagementDetailDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGbSortManagementDetail(GbSortManagementDetailDTO gbSortManagementDetailDTO) throws ServiceException
	{
		GbSortManagementDetail gbSortManagementDetail = DaoUtil.get(GbSortManagementDetail.class, gbSortManagementDetailDTO.getGbSortManagementDetailOid());
		BeanHelper.copyProperties(gbSortManagementDetailDTO, gbSortManagementDetail, new String[]{"createdDate","createdByCode","createdByName"});
		gbSortManagementDetail.setUpdatedByCode(UserContext.getLoginUserID());
		gbSortManagementDetail.setUpdatedByName(UserContext.getLoginUserName());
		gbSortManagementDetail.setUpdatedDate(DateUtil.now());
		gbSortManagementDetail.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGbSortManagementDetail(Long gbSortManagementDetailOid) throws ServiceException
	{
		GbSortManagementDetail gbSortManagementDetail = DaoUtil.get(GbSortManagementDetail.class, gbSortManagementDetailOid);
		gbSortManagementDetail.delete();
	}

	public List<GbSortManagementDetailDTO> listGbSortManagementDetailByManageId(
			Long gbSortManagementOid) throws ServiceException {
		List<GbSortManagementDetailDTO> list= GbSortManagementDetailQueryHelper.listGbSortManagementDetailByManageId(gbSortManagementOid);
		if(CollectionUtils.isNotEmpty(list)){
			return list;
		}
		return null;
	}
}

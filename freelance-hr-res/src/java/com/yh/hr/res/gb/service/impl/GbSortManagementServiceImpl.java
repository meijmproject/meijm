package com.yh.hr.res.gb.service.impl;

import com.yh.hr.res.gb.bo.GbSortManagement;
import com.yh.hr.res.gb.dto.GbSortManagementDTO;
import com.yh.hr.res.gb.service.GbSortManagementService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类
 * @author huangyj
 */
public class GbSortManagementServiceImpl implements GbSortManagementService {

	/**
	 * 新增
	 */
	public void createGbSortManagement(GbSortManagementDTO gbSortManagementDTO) throws ServiceException
	{
		GbSortManagement gbSortManagement = BeanHelper.copyProperties(gbSortManagementDTO, GbSortManagement.class);
		gbSortManagement.setCreatedByCode(UserContext.getLoginUserID());
		gbSortManagement.setCreatedByName(UserContext.getLoginUserName());
		gbSortManagement.setCreatedDate(DateUtil.now());
		gbSortManagement.save();
	}
	
	/**
	 * 查看
	 */
	public GbSortManagementDTO findGbSortManagementById(Long gbSortManagementOid) throws ServiceException
	{
		GbSortManagement gbSortManagement = DaoUtil.get(GbSortManagement.class, gbSortManagementOid);
		if(null != gbSortManagement)
		{
			return BeanHelper.copyProperties(gbSortManagement, GbSortManagementDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGbSortManagement(GbSortManagementDTO gbSortManagementDTO) throws ServiceException
	{
		GbSortManagement gbSortManagement = DaoUtil.get(GbSortManagement.class, gbSortManagementDTO.getGbSortManagementOid());
		BeanHelper.copyProperties(gbSortManagementDTO, gbSortManagement, new String[]{"createdDate","createdByCode","createdByName"});
		gbSortManagement.setUpdatedByCode(UserContext.getLoginUserID());
		gbSortManagement.setUpdatedByName(UserContext.getLoginUserName());
		gbSortManagement.setUpdatedDate(DateUtil.now());
		gbSortManagement.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGbSortManagement(Long gbSortManagementOid) throws ServiceException
	{
		GbSortManagement gbSortManagement = DaoUtil.get(GbSortManagement.class, gbSortManagementOid);
		gbSortManagement.delete();
	}
}

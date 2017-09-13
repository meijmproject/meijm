package com.yh.hr.res.gt.service.impl;

import java.util.List;

import com.yh.hr.res.gt.bo.GtSortManagement;
import com.yh.hr.res.gt.dto.GtSortManagementDTO;
import com.yh.hr.res.gt.queryhelper.GtSortManagementQueryHelper;
import com.yh.hr.res.gt.service.GtSortManagementService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * service实现类（业务）
 * @author huangyj
 */
public class GtSortManagementServiceImpl implements GtSortManagementService {

	/**
	 * 新增
	 */
	public void createGtSortManagement(GtSortManagementDTO gtSortManagementDTO) throws ServiceException
	{
		GtSortManagement gtSortManagement = BeanHelper.copyProperties(gtSortManagementDTO, GtSortManagement.class);
		gtSortManagement.setCreatedByCode(UserContext.getLoginUserID());
		gtSortManagement.setCreatedByName(UserContext.getLoginUserName());
		gtSortManagement.setCreatedDate(DateUtil.now());
		gtSortManagement.save();
	}
	
	/**
	 * 查看
	 */
	public GtSortManagementDTO findGtSortManagementById(Long gtSortManagementOid) throws ServiceException
	{
		GtSortManagement gtSortManagement = DaoUtil.get(GtSortManagement.class, gtSortManagementOid);
		if(null != gtSortManagement)
		{
			return BeanHelper.copyProperties(gtSortManagement, GtSortManagementDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGtSortManagement(GtSortManagementDTO gtSortManagementDTO) throws ServiceException
	{
		GtSortManagement gtSortManagement = DaoUtil.get(GtSortManagement.class, gtSortManagementDTO.getGtSortManagementOid());
		BeanHelper.copyProperties(gtSortManagementDTO, gtSortManagement, new String[]{"createdDate","createdByCode","createdByName"});
		gtSortManagement.setUpdatedByCode(UserContext.getLoginUserID());
		gtSortManagement.setUpdatedByName(UserContext.getLoginUserName());
		gtSortManagement.setUpdatedDate(DateUtil.now());
		gtSortManagement.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGtSortManagement(Long gtSortManagementOid) throws ServiceException
	{
		GtSortManagement gtSortManagement = DaoUtil.get(GtSortManagement.class, gtSortManagementOid);
		gtSortManagement.delete();
	}
	
	/**
	 * 根据单位OID查询
	 * */
	public List<GtSortManagementDTO> getGtSortManagementByUnitOId(Long unitOId)
			throws ServiceException {
		return GtSortManagementQueryHelper.listGtSortManagementDTOByUnitOid(unitOId);
	}

	public List<GtSortManagementDTO> getGtSortManagementByTaskOId(Long TaskOId)
			throws ServiceException {
		return GtSortManagementQueryHelper.listGtSortManagementDTOByTaskOid(TaskOId);
	}
}

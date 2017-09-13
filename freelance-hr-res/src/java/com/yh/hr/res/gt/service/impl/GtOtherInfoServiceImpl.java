package com.yh.hr.res.gt.service.impl;

import com.yh.hr.res.gt.bo.GtOtherInfo;
import com.yh.hr.res.gt.dto.GtOtherInfoDTO;
import com.yh.hr.res.gt.service.GtOtherInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class GtOtherInfoServiceImpl implements GtOtherInfoService {
	
	/**
	 * 新增
	 */
	public void createGtOtherInfo(GtOtherInfoDTO gtOtherInfoDTO) throws ServiceException
	{
		GtOtherInfo gtOtherInfo = BeanHelper.copyProperties(gtOtherInfoDTO, GtOtherInfo.class);
		gtOtherInfo.setCreatedByCode(UserContext.getLoginUserID());
		gtOtherInfo.setCreatedByName(UserContext.getLoginUserName());
		gtOtherInfo.setCreatedDate(DateUtil.now());
		gtOtherInfo.save();
	}
	
	/**
	 * 查询
	 */
	public GtOtherInfoDTO findGtOtherInfoById(Long jhgGtOtherInfo) throws ServiceException
	{
		GtOtherInfo gtOtherInfo = DaoUtil.get(GtOtherInfo.class, jhgGtOtherInfo);
		if(null != gtOtherInfo)
		{
			return BeanHelper.copyProperties(gtOtherInfo, GtOtherInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGtOtherInfo(GtOtherInfoDTO gtOtherInfoDTO) throws ServiceException
	{
		GtOtherInfo gtOtherInfo = DaoUtil.get(GtOtherInfo.class, gtOtherInfoDTO.getJhgGtOtherInfo());
		BeanHelper.copyProperties(gtOtherInfoDTO, gtOtherInfo, new String[]{"createdDate","createdByCode","createdByName"});
		gtOtherInfo.setUpdatedByCode(UserContext.getLoginUserID());
		gtOtherInfo.setUpdatedByName(UserContext.getLoginUserName());
		gtOtherInfo.setUpdatedDate(DateUtil.now());
		gtOtherInfo.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGtOtherInfo(Long jhgGtOtherInfo) throws ServiceException
	{
		GtOtherInfo gtOtherInfo = DaoUtil.get(GtOtherInfo.class, jhgGtOtherInfo);
		gtOtherInfo.delete();
	}
}

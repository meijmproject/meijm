package com.yh.hr.component.gb.service.impl;


import java.util.List;

import com.yh.hr.component.gb.bo.GBDescription;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.gb.dto.GbDescriptionDTO;
import com.yh.hr.component.gb.queryhelper.GbDescriptionQueryHelper;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;


public class GbDescriptionServiceImpl implements GbDescriptionService{
	/**
	 * 新增
	 */
	public void createGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException
	{
		GBDescription gbDescription = BeanHelper.copyProperties(gbDescriptionDTO, GBDescription.class);
		UtUnitDTO utUnitDTO=GbDescriptionQueryHelper.findUnit();
		gbDescription.setUnitOid(utUnitDTO.getUnitOid());
		gbDescription.setCreatedByCode(UserContext.getLoginUserID());
		gbDescription.setCreatedByName(UserContext.getLoginUserName());
		gbDescription.setCreatedDate(DateUtil.now());
		gbDescription.save();
	}
	
	/**
	 * 查看
	 */
	public GbDescriptionDTO findGbDescriptionById(Long jhgGbDescriptionOid) throws ServiceException
	{
		GBDescription gbDescription = DaoUtil.get(GBDescription.class, jhgGbDescriptionOid);
		if(null != gbDescription)
		{
			return BeanHelper.copyProperties(gbDescription, GbDescriptionDTO.class);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	public void updateGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException
	{
		GBDescription gbDescription = DaoUtil.get(GBDescription.class, gbDescriptionDTO.getJhgGbDescriptionOid());
		BeanHelper.copyProperties(gbDescriptionDTO, gbDescription, new String[]{"createdDate","createdByCode","createdByName"});
		gbDescription.setUpdatedByCode(UserContext.getLoginUserID());
		gbDescription.setUpdatedByName(UserContext.getLoginUserName());
		gbDescription.setUpdatedDate(DateUtil.now());
		gbDescription.update();
	}
	
	/**
	 * 删除
	 */
	public void deleteGbDescription(Long jhgGbDescriptionOid) throws ServiceException
	{
		GBDescription gbDescription = DaoUtil.get(GBDescription.class, jhgGbDescriptionOid);
		gbDescription.delete();
	}

	public List<JSONObject> listGbDescription(TableTagBean ttb)
			throws ServiceException {
		return GbDescriptionQueryHelper.listGbDescription(ttb);
	}
}

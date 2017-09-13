package com.yh.hr.orghc.ut.service.impl;

import java.util.List;

import com.yh.hr.orghc.ut.bo.BizUtHc;
import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.hr.orghc.ut.queryhelper.BizHcInfoQueryHelper;
import com.yh.hr.orghc.ut.service.BizHcInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class BizHcInfoServiceImpl implements BizHcInfoService{

	/**
	 * 新增
	 */
	public void create(BizUtHcDTO dto) throws ServiceException {
	
			BizUtHc bizUtHc = new BizUtHc();
			BeanHelper.copyProperties(dto, bizUtHc);

			bizUtHc.setCreateBy(UserContext.getLoginUserID());
			bizUtHc.setCreateName(UserContext.getLoginUserName());
			bizUtHc.setCreateDate(DateUtil.now());
			bizUtHc.save();
		
	}
	
	/**
	 * 根据ID查询
	 */	
	public BizUtHcDTO get(Long utHcOid) throws ServiceException{
		return BizHcInfoQueryHelper.get(utHcOid);
	}
	
	/**
	 * 更新
	 */
	public void update(BizUtHcDTO dto) throws ServiceException{

			BizUtHc bizUtHc = DaoUtil.get(BizUtHc.class, dto.getUtHcOid());
			if(null != bizUtHc){
				BeanHelper.copyProperties(dto, bizUtHc,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				bizUtHc.setUpdateBy(UserContext.getLoginUserID());
				bizUtHc.setUpdateName(UserContext.getLoginUserName());
				bizUtHc.setUpdateDate(DateUtil.now());
				bizUtHc.update();
			}
		
	}
	
	/**
	 * 根据ID删除
	 */	
	public void delete(Long utHcOid) throws ServiceException{
		BizHcInfoQueryHelper.delete(utHcOid);
	}
	
	/**
	 * 
	 */	
	public List<BizUtHcDTO> list(Long utUnitOid) throws ServiceException{
		return BizHcInfoQueryHelper.listBizUtHc(utUnitOid);
	}


}

package com.yh.hr.orghc.ut.service.impl;

import java.util.List;

import com.yh.hr.orghc.ut.bo.BizUtOrg;
import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.hr.orghc.ut.queryhelper.BizOrgInfoQueryHelper;
import com.yh.hr.orghc.ut.service.BizOrgInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class BizOrgInfoServiceImpl implements BizOrgInfoService {

	/**
	 * 新增
	 */
	public void create(BizUtOrgDTO dto) throws ServiceException {
	
			BizUtOrg bizUtOrg = new BizUtOrg();
			BeanHelper.copyProperties(dto, bizUtOrg);

			bizUtOrg.setCreatedByCode(UserContext.getLoginUserID());
			bizUtOrg.setCreatedByName(UserContext.getLoginUserName());
			bizUtOrg.setCreatedDate(DateUtil.now());
			bizUtOrg.save();
		
	}
	
	/**
	 * 根据ID查询
	 */	
	public BizUtOrgDTO get(Long utOrgOid) throws ServiceException{
		return BizOrgInfoQueryHelper.get(utOrgOid);
	}
	
	/**
	 * 更新
	 */
	public void update(BizUtOrgDTO dto) throws ServiceException{

			BizUtOrg bizUtOrg = DaoUtil.get(BizUtOrg.class, dto.getUtOrgOid());
			if(null != bizUtOrg){
				BeanHelper.copyProperties(dto, bizUtOrg,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				bizUtOrg.setUpdatedByCode(UserContext.getLoginUserID());
				bizUtOrg.setUpdatedByName(UserContext.getLoginUserName());
				bizUtOrg.setUpdatedDate(DateUtil.now());
				bizUtOrg.update();
			}
		
	}
	
	/**
	 * 根据ID删除
	 */	
	public void delete(Long utOrgOid) throws ServiceException{
		BizOrgInfoQueryHelper.delete(utOrgOid);
	}
	
	/**
	 * 考核情况详细信息列表
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public List<BizUtOrgDTO> list(Long utUnitOid,String status) throws ServiceException{
		return BizOrgInfoQueryHelper.listBizUtOrg(utUnitOid,status);
	}


}

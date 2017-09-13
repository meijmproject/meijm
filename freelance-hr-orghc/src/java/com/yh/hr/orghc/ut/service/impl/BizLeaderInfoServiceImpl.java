package com.yh.hr.orghc.ut.service.impl;

import java.util.List;

import com.yh.hr.orghc.ut.bo.BizUtLeader;
import com.yh.hr.orghc.ut.dto.BizUtLeaderDTO;
import com.yh.hr.orghc.ut.queryhelper.BizLeaderInfoQueryHelper;
import com.yh.hr.orghc.ut.service.BizLeaderInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class BizLeaderInfoServiceImpl implements BizLeaderInfoService{

	/**
	 * 新增
	 */
	public void create(BizUtLeaderDTO dto) throws ServiceException {
	
			BizUtLeader bizUtLeader = new BizUtLeader();
			BeanHelper.copyProperties(dto, bizUtLeader);

			bizUtLeader.setCreateBy(UserContext.getLoginUserID());
			bizUtLeader.setCreateName(UserContext.getLoginUserName());
			bizUtLeader.setCreateDate(DateUtil.now());
			bizUtLeader.save();
		
	}
	
	/**
	 * 根据ID查询
	 */	
	public BizUtLeaderDTO get(Long utLeaderOid) throws ServiceException{
		return BizLeaderInfoQueryHelper.get(utLeaderOid);
	}
	
	/**
	 * 更新
	 */
	public void update(BizUtLeaderDTO dto) throws ServiceException{

			BizUtLeader bizUtLeader = DaoUtil.get(BizUtLeader.class, dto.getUtLeaderOid());
			if(null != bizUtLeader){
				BeanHelper.copyProperties(dto, bizUtLeader,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				bizUtLeader.setUpdateBy(UserContext.getLoginUserID());
				bizUtLeader.setUpdateName(UserContext.getLoginUserName());
				bizUtLeader.setUpdateDate(DateUtil.now());
				bizUtLeader.update();
			}
		
	}
	
	/**
	 * 根据ID删除
	 */	
	public void delete(Long utLeaderOid) throws ServiceException{
		BizLeaderInfoQueryHelper.delete(utLeaderOid);
	}
	
	/**
	 * 考核情况详细信息列表
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public List<BizUtLeaderDTO> list(Long utUnitOid) throws ServiceException{
		return BizLeaderInfoQueryHelper.listBizUtLeader(utUnitOid);
	}


}

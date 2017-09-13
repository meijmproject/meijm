package com.yh.hr.component.wardset.service.impl;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.wardset.bo.CfWard;
import com.yh.hr.component.wardset.dto.CfWardDto;
import com.yh.hr.component.wardset.queryhelper.WardSetQueryHelper;
import com.yh.hr.component.wardset.service.WardSetService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class WardSetServiceImpl implements WardSetService {

	public List<CfWardDto> find(TableTagBean ttb) throws ServiceException {
        return WardSetQueryHelper.find(ttb);
	}
	
	/**
	 * 新增病区数据
	 * @param cfWardDto
	 * @throws ServiceException
	 */
	public void create(CfWardDto cfWardDto) throws ServiceException {
		CfWard cfWard = new CfWard();
		BeanHelper.copyProperties(cfWardDto, cfWard);
		cfWard.setCreatedByCode(UserContext.getLoginUserID());
		cfWard.setCreatedByName(UserContext.getLoginUserName());
		cfWard.setCreatedDate(DateUtil.now());
		cfWard.save();
	}

	/**
	 * 获取病区数据
	 * @param wardOid
	 * @return
	 * @throws ServiceException
	 */
	public CfWardDto get(Long wardOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(CfWard.class, wardOid),CfWardDto.class);
	}

	/**
	 * 更新病区数据
	 * @param cfWardDto
	 * @throws ServiceException
	 */
	public void update(CfWardDto cfWardDto) throws ServiceException {
		CfWard cfWard = DaoUtil.get(CfWard.class, cfWardDto.getWaedOid());
		BeanHelper.copyProperties(cfWardDto, cfWard, BeanHelper.getNullPropertyNames(cfWardDto));
		cfWard.setUpdatedByCode(UserContext.getLoginUserID());
		cfWard.setUpdatedByName(UserContext.getLoginUserName());
		cfWard.setUpdatedDate(DateUtil.now());
		cfWard.update();
	}

	/**
	 * 删除病区数据
	 * @param waedOid
	 * @throws ServiceException
	 */
	public void delete(Long waedOid) throws ServiceException {
		DaoUtil.get(CfWard.class, waedOid).delete();
	}
	
	/**
	 * 获取《医院各病区卫技人员配备情况一览表》的数据
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findWardCollection() throws ServiceException {
		return  WardSetQueryHelper.findWardCollection();
	}
}

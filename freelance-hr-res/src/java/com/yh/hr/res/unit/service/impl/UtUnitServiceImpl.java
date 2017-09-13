/**
 * 
 */
package com.yh.hr.res.unit.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.hr.res.unit.service.UtOrganizationService;
import com.yh.hr.res.unit.service.UtRelationService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class UtUnitServiceImpl implements UtUnitService {
	private UtOrganizationService utOrganizationService;
	private UtRelationService utRelationService;
	
	public void setUtOrganizationService(UtOrganizationService utOrganizationService) {
		this.utOrganizationService = utOrganizationService;
	}

	public void setUtRelationService(UtRelationService utRelationService) {
		this.utRelationService = utRelationService;
	}
	/* (non-Javadoc)
	 * @see UtUnitService#findUnitListByAuth(java.util.List, StringMap)
	 */
	public List<UtUnitDTO> findUnitListByAuth(List<String> authorizationOids, StringMap params) throws ServiceException {
		return UtUnitQueryHelper.findUnitListByAuth(authorizationOids, params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see UtUnitService#findUnitList(StringMap)
	 */
	public List<UtUnitDTO> findUnitList(StringMap params)
			throws ServiceException {
		return UtUnitQueryHelper.findUnitList(params);
	}

	/* (non-Javadoc)
	 * @see UtUnitService#get(java.lang.Long)
	 */
	public UtUnitDTO get(Long unitOid) throws ServiceException {
		return BeanHelper.copyProperties(UtUnitQueryHelper.get(unitOid), UtUnitDTO.class);
	}

	/* (non-Javadoc)
	 * @see UtUnitService#getUnitName(java.lang.Long)
	 */
	public String getUnitName(Long unitOid) throws ServiceException {
		UtUnit unit = UtUnitQueryHelper.get(unitOid);
		return unit == null ? null : unit.getUnitName();
	}

	/* (non-Javadoc)
	 * @see UtUnitService#listByCondition(TableTagBean)
	 */
	public List<UtUnitDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		
		return UtUnitQueryHelper.listByCondition(ttb);
	}
	
	public void create(UtUnitDTO utUnitDTO) throws ServiceException{
		//创建单位组织机构账户信息
		Long organizationOid = utOrganizationService.create(utUnitDTO.getUnitName(), UtConstants.UT_ORGANIZATION_TYPE_1);
		utUnitDTO.setOrganizationOid(organizationOid);
		
		//创建单位账户对应关系
		utRelationService.create(utUnitDTO);
		
		//创建单位信息
		UtUnit utUnit = new UtUnit();
		BeanHelper.copyProperties(utUnitDTO, utUnit);
		utUnit.setCreatedByCode(UserContext.getLoginUserID());
		utUnit.setCreatedByName(UserContext.getLoginUserName());
		utUnit.setCreatedDate(DateUtil.now());
		utUnit.save();
	}

	public void update(UtUnitDTO utUnitDTO) throws ServiceException {
		UtUnit utUnit = UtUnitQueryHelper.get(utUnitDTO.getUnitOid());
		utUnit.setUnitName(utUnitDTO.getUnitName());
		utUnit.setUnitKind(utUnitDTO.getUnitKind());
		utUnit.setUnitCategoryCode(utUnitDTO.getUnitCategoryCode());	//系统类别YHRS0091
		utUnit.setUnitLevelCode(utUnitDTO.getUnitLevelCode());	//机构级别YHRS0093
		utUnit.setUnitAreaCode(utUnitDTO.getUnitAreaCode());
		utUnit.setUnitStatus(utUnitDTO.getUnitStatus());
		utUnit.setOrderOfAll(utUnitDTO.getOrderOfAll());
		utUnit.setUnitCode(utUnitDTO.getUnitCode());
		utUnit.setUpdatedByCode(UserContext.getLoginUserID());
		utUnit.setUpdatedByName(UserContext.getLoginUserName());
		utUnit.setUpdatedDate(DateUtil.now());
		utUnit.update();
		//单位名称、隶属单位不可修改，故不需要修改机构账户信息、机构账户对应关系信息
		//单位业务信息开放后，修改控制放开，固此处需要更新机构账户信息、机构账户对应关系信息
		utUnitDTO.setOrganizationOid(utUnit.getOrganizationOid());
		utOrganizationService.update(utUnitDTO.getOrganizationOid(), utUnitDTO.getUnitName());
		utRelationService.update(utUnitDTO);
	}

	
	public String getUnitKind(Long unitOid) throws ServiceException {
		UtUnit unit = UtUnitQueryHelper.get(unitOid);
		return unit == null ? null : unit.getUnitKind();
	}

	/* (non-Javadoc)
	 * @see UtUnitService#getAdminUnit(java.lang.Long)
	 */
	public UtUnitDTO getAdminUnit(Long unitOid) throws ServiceException {
		return UtUnitQueryHelper.getAdminUnit(unitOid);
	}

	public List<Long> findAdminUnitOid(List<Long> unitOids) throws ServiceException {
		return UtUnitQueryHelper.findAdminUnitOid(unitOids);
	}
    /*
     * (non-Javadoc)
     * @see UtUnitService#findUnitInfo()
     */
	public List<UtUnitDTO> findUnitInfo() throws ServiceException{
		return UtUnitQueryHelper.findUnitInfo();
	}

	/* (non-Javadoc)
	 * @see UtUnitService#getUnitInfoByOrgOid(java.lang.Long)
	 */
	public UtUnitDTO getUnitInfoByOrgOid(Long orgOid) throws ServiceException {
		return UtUnitQueryHelper.getUnitInfoByOrgOid(orgOid);
	}
}

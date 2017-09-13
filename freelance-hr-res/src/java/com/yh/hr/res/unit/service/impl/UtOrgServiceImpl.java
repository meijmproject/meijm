/**
 * 
 */
package com.yh.hr.res.unit.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.queryhelper.UtOrgQueryHelper;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtOrganizationService;
import com.yh.hr.res.unit.service.UtRelationService;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 内设机构信息
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public class UtOrgServiceImpl implements UtOrgService {
	
	private UtOrganizationService utOrganizationService;
	private UtRelationService	  utRelationService;
	
	public void setUtOrganizationService(UtOrganizationService utOrganizationService) {
		this.utOrganizationService = utOrganizationService;
	}

	public void setUtRelationService(UtRelationService utRelationService) {
		this.utRelationService = utRelationService;
	}

	public UtOrgDTO get(Long orgOid) throws ServiceException {
		return BeanHelper.copyProperties(UtOrgQueryHelper.get(orgOid), UtOrgDTO.class);
	}

	public String getOrgName(Long orgOid) throws ServiceException {
		UtOrg utOrg = UtOrgQueryHelper.get(orgOid);
		return utOrg == null ? null : utOrg.getOrgName();
	}

	public List<UtOrgDTO> findUnitOrg(Long unitOid) throws ServiceException {
		
		return BeanHelper.copyProperties(UtOrgQueryHelper.findUnitOrg(unitOid), UtOrgDTO.class);
	}

	public void create(UtOrgDTO utOrgDTO) throws ServiceException {
		//创建内设机构账户信息
		Long organizationOid = utOrganizationService.create(utOrgDTO.getOrgName(),UtConstants.UT_ORGANIZATION_TYPE_2);
		utOrgDTO.setOrganizationOid(organizationOid);
		
		//创建内设机构账户对应关系
		utRelationService.create(utOrgDTO);
		
		//创建内设机构信息
		UtOrg utOrg = new UtOrg();
		BeanHelper.copyProperties(utOrgDTO, utOrg);
		utOrg.setCreatedByCode(UserContext.getLoginUserID());
		utOrg.setCreatedByName(UserContext.getLoginUserName());
		utOrg.setCreatedDate(DateUtil.now());
		utOrg.save();
	}

	public void update(UtOrgDTO utOrgDTO) throws ServiceException {
		//修改内设机构信息
		UtOrg utOrg = UtOrgQueryHelper.get(utOrgDTO.getOrgOid());
		if(utOrg == null){throw new ServiceException(null,"组织内设机构信息不存在");}
		utOrg.setOrgName(utOrgDTO.getOrgName());
		utOrg.setOrgType(utOrgDTO.getOrgType());
		utOrg.setOrgStatus(utOrgDTO.getOrgStatus());
		utOrg.setOrderOfAll(utOrgDTO.getOrderOfAll());
		utOrg.setUpdatedByCode(UserContext.getLoginUserID());
		utOrg.setUpdatedByName(UserContext.getLoginUserName());
		utOrg.setUpdatedDate(DateUtil.now());
		utOrg.update();
		utOrgDTO.setOrganizationOid(utOrg.getOrganizationOid());
		
		//修改内设机构账户信息
		utOrganizationService.update(utOrg.getOrganizationOid(),utOrg.getOrgName());
		
		//修改内设机构账户对应关系
		utRelationService.update(utOrgDTO);
		
	}

	public void delete(Long orgOid) throws ServiceException {
		UtOrg utOrg = UtOrgQueryHelper.get(orgOid);
		//删除内设机构账户对应关系
		utRelationService.deleteByOrganizationOid(utOrg.getOrganizationOid());
		
		//删除内设机构信息
		utOrg.delete();
		
		//删除内设机构账户信息
		utOrganizationService.delete(utOrg.getOrganizationOid());
		
	}

	public List<UtOrgDTO> listByCondition(TableTagBean ttb)
			throws ServiceException {
		
		return UtOrgQueryHelper.listByCondition(ttb);
	}
	
	/**
     * 通过科室名称查询科室信息
     * @param orgName
     * @return
     * @throws DataAccessFailureException
     */
    public UtOrgDTO findUtOrgDTOByOrgName(String orgName) throws ServiceException {
    	return UtOrgQueryHelper.findUtOrgDTOByOrgName(orgName);
    }

}

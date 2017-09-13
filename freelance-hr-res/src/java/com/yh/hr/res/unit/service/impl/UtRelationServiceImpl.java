/**
 * 
 */
package com.yh.hr.res.unit.service.impl;

import com.yh.hr.res.unit.bo.UtRelation;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.queryhelper.UtRelationQueryHelper;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtRelationService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 组织机构信息ServiceImpl
 * @author	lenghh
 * @version	1.0,	16/09/06
 */

public class UtRelationServiceImpl implements UtRelationService {

	private UtUnitService 	utUnitService;
	private UtOrgService	utOrgService;
	
	public UtUnitService getUtUnitService() {
		return utUnitService;
	}

	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public UtOrgService getUtOrgService() {
		return utOrgService;
	}

	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	public void create(UtOrgDTO utOrgDTO) throws ServiceException {
		//获取所属单位信息
		UtUnitDTO utUnitDTO = utUnitService.get(utOrgDTO.getUnitOid());
		if(null != utUnitDTO && null == utOrgDTO.getParentOrgOid()){
			//创建单位--内设机构对应关系
			UtRelation utRelation = new UtRelation();
			utRelation.setParentOrganizationOid(utUnitDTO.getOrganizationOid());
			utRelation.setChildOrganizationOid(utOrgDTO.getOrganizationOid());
			utRelation.setRelationType(UtConstants.UT_RELATION_TYPE_3);
			utRelation.setCreatedByCode(UserContext.getLoginUserID());
			utRelation.setCreatedByName(UserContext.getLoginUserName());
			utRelation.setCreatedDate(DateUtil.now());
			utRelation.save();
		}
		//获取上级内设机构信息
		if(null != utOrgDTO.getParentOrgOid()){
		UtOrgDTO parentUtOrgDTO = utOrgService.get(utOrgDTO.getParentOrgOid());
			if(null != parentUtOrgDTO){
				//创建内设机构--内设机构对应关系
				UtRelation utRelation = new UtRelation();
				utRelation.setParentOrganizationOid(parentUtOrgDTO.getOrganizationOid());
				utRelation.setChildOrganizationOid(utOrgDTO.getOrganizationOid());
				utRelation.setRelationType(UtConstants.UT_RELATION_TYPE_2);
				utRelation.setCreatedByCode(UserContext.getLoginUserID());
				utRelation.setCreatedByName(UserContext.getLoginUserName());
				utRelation.setCreatedDate(DateUtil.now());
				utRelation.save();
			}
		}
	}

	public void update(UtOrgDTO utOrgDTO) throws ServiceException {
		//目前内设机构信息是不可维护所属单位的，所以暂不更新单位--内设机构关系
		//如果修改了隶属内设机构，则需维护内设机构--内设机构关系
		UtRelation utRelation = UtRelationQueryHelper.findByChildOrganizationOid(utOrgDTO.getOrganizationOid(),UtConstants.UT_RELATION_TYPE_2);
		if(null != utRelation){
			//获取上级内设机构信息
			if(null != utOrgDTO.getParentOrgOid()){
				UtOrgDTO parentUtOrgDTO = utOrgService.get(utOrgDTO.getParentOrgOid());
				if(null != parentUtOrgDTO){
					utRelation.setParentOrganizationOid(parentUtOrgDTO.getOrganizationOid());
					utRelation.setUpdatedByCode(UserContext.getLoginUserID());
					utRelation.setUpdatedByName(UserContext.getLoginUserName());
					utRelation.setUpdatedDate(DateUtil.now());
					utRelation.update();
				}
			}
		}
	}

	public void deleteByOrganizationOid(Long organizationOid) throws ServiceException {
		UtRelationQueryHelper.deleteByOrganizationOid(organizationOid);
	}

	public void create(UtUnitDTO utUnitDTO) throws ServiceException {
		//如果上级单位不为空，
		if(null != utUnitDTO.getParentUnitOid() && utUnitDTO.getParentUnitOid() != 0){
			UtUnitDTO parentUtUnitDTO = utUnitService.get(utUnitDTO.getParentUnitOid());
			if(null != parentUtUnitDTO){
				UtRelation utRelation = new UtRelation();
				utRelation.setParentOrganizationOid(parentUtUnitDTO.getOrganizationOid());
				utRelation.setChildOrganizationOid(utUnitDTO.getOrganizationOid());
				utRelation.setRelationType(UtConstants.UT_RELATION_TYPE_1);
				utRelation.setCreatedByCode(UserContext.getLoginUserID());
				utRelation.setCreatedByName(UserContext.getLoginUserName());
				utRelation.setCreatedDate(DateUtil.now());
				utRelation.save();
			}
		}
	}

	public void update(UtUnitDTO utUnitDTO) throws ServiceException {
		UtRelation utRelation = UtRelationQueryHelper.findByChildOrganizationOid(utUnitDTO.getOrganizationOid(),UtConstants.UT_RELATION_TYPE_1);
		if(null != utRelation){
			//获取上级单位信息
			if(null != utUnitDTO.getParentUnitOid() && utUnitDTO.getParentUnitOid() != 0){
				UtUnitDTO parentUtUnitDTO = utUnitService.get(utUnitDTO.getParentUnitOid());
				if(null != parentUtUnitDTO){
					utRelation.setParentOrganizationOid(parentUtUnitDTO.getOrganizationOid());
					utRelation.setUpdatedByCode(UserContext.getLoginUserID());
					utRelation.setUpdatedByName(UserContext.getLoginUserName());
					utRelation.setUpdatedDate(DateUtil.now());
					utRelation.update();
				}
			}
		}
	}
}

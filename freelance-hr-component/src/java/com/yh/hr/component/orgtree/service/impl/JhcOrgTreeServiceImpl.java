package com.yh.hr.component.orgtree.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.unittree.queryhelper.JhcUnitTreeQueryHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.hr.component.orgtree.service.JhcOrgTreeService;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 内设机构信息服务实现类
 * 
 * @author liuhw 2017-2-22
 */
public class JhcOrgTreeServiceImpl implements JhcOrgTreeService {
	public UtOrgService utOrgService;

	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	

	/**
	 * 获取内设机构信息
	 * 
	 * @param ttb
	 * @return List<UtOrgDTO>
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> listOrg(TableTagBean ttb) throws ServiceException {
		// 调用权限服务，查询当前用户有权限的单位信息
		/*
		 * List<String> userUnitAuthList =
		 * saoUserUnitAuthorizationService.findAuthorityList
		 * (UserContext.getLoginUserID());
		 * if(CollectionUtils.isNotEmpty(userUnitAuthList)) {
		 * ttb.getCondition().put("authUnits",
		 * StringUtil.arrayToSql(userUnitAuthList)); }
		 */
		List<UtOrgDTO> unitList = utOrgService.listByCondition(ttb);// 单位列表
		if (CollectionUtils.isEmpty(unitList)) {
			return null;
		}
		if (CollectionUtils.isNotEmpty(unitList)) {
			for (UtOrgDTO orgDTO : unitList) {
				orgDTO.setIsleaf(this.checkIsChild(orgDTO.getOrgOid().toString()));
			}
		}
		return unitList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * JhcOrgTreeService#findOrgByOid(
	 * java.lang.Long)
	 */
	public UtOrgDTO findOrgByOid(Long orgOid) throws ServiceException {
		return utOrgService.get(orgOid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see JhcOrgTreeService#
	 * getAllOrgInfoByUnitOid(java.lang.Long)
	 */
	public List<UtOrgDTO> getAllOrgInfoByUnitOid(Long unitOid)
			throws ServiceException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * JhcOrgTreeService#findOrgInfoByCon
	 * (java.lang.String, java.lang.String)
	 */
	public List<UtOrgDTO> findOrgInfoByCon(String unitOid, String orgOidStr,String powerControl)
			throws ServiceException {
		if(StringUtils.isNotEmpty(powerControl)) {
			// 单位不为空，内设机构为空时
			List<UtOrgDTO> listUtOrgDTO = new ArrayList<UtOrgDTO>();
			if (StringUtils.isNotEmpty(unitOid) && StringUtils.isEmpty(orgOidStr)) {
				List<UtOrgDTO> orgdDtoList = JhcOrgTreeQueryHelper.findOrgInfoByUnitOid(Long.valueOf(unitOid));
				if(CollectionUtils.isNotEmpty(orgdDtoList)){
					List<UtOrgDTO> list = JhcOrgTreeQueryHelper.findOrgList(false);//有权限的科室
					List<String> hierarchyCodes = new ArrayList<String>();
					if(CollectionUtils.isNotEmpty(list)){
						for(UtOrgDTO utOrgDTO :list){
			 				String hierarchyCode = utOrgDTO.getHierarchyCode();
			 				hierarchyCodes.add(hierarchyCode);//有权限的添加进去
			 				switch(hierarchyCode.length()){//没有权限 为了构造树  把上级节点加进去
			 				  case 6:
			 					  String hierarchyCodefour = hierarchyCode.substring(0,3);
			 					  if(!hierarchyCodes.contains(hierarchyCodefour)){
			 						 hierarchyCodes.add(hierarchyCodefour);
			 				      }
			 					  break;
			 				 case 9:
			 					  String hierarchyCodefour_nine = hierarchyCode.substring(0, 3);
								  String hierarchyCodeServen = hierarchyCode.substring(0, 6);
								  if(!hierarchyCodes.contains(hierarchyCodefour_nine)){
									 hierarchyCodes.add(hierarchyCodefour_nine);
							      }
								  if(!hierarchyCodes.contains(hierarchyCodeServen)){
										 hierarchyCodes.add(hierarchyCodeServen);
								  }
								  break;
			 				}
						}
					}
					for(UtOrgDTO utOrgDTO :orgdDtoList){
						if(hierarchyCodes.contains(utOrgDTO.getHierarchyCode())){
							listUtOrgDTO.add(utOrgDTO);
						}
					}
					if (CollectionUtils.isNotEmpty(listUtOrgDTO)) {
						for (UtOrgDTO orgDTO : listUtOrgDTO) {
							orgDTO.setIsleaf(this.checkIsChild(orgDTO.getOrgOid().toString()));
						}
					}
					return listUtOrgDTO;
				}
			}
			// 单位不为空，内设机构也不为空时
			if (StringUtils.isNotEmpty(unitOid)&& StringUtils.isNotEmpty(orgOidStr)) {
				List<UtOrgDTO> orgdDtoList = JhcOrgTreeQueryHelper.findOrgInfoByParentOrgOid(Long.valueOf(orgOidStr));
				if(CollectionUtils.isNotEmpty(orgdDtoList)){
					List<UtOrgDTO> list = JhcOrgTreeQueryHelper.findOrgList(false);//有权限的科室
					List<String> hierarchyCodes = new ArrayList<String>();
					if(CollectionUtils.isNotEmpty(list)){
						for(UtOrgDTO utOrgDTO :list){
			 				String hierarchyCode = utOrgDTO.getHierarchyCode();
			 				hierarchyCodes.add(hierarchyCode);//有权限的添加进去
			 				switch(hierarchyCode.length()){//没有权限 为了构造树  把上级节点加进去
			 				  case 6:
			 					  String hierarchyCodefour = hierarchyCode.substring(0,3);
			 					  if(!hierarchyCodes.contains(hierarchyCodefour)){
			 						 hierarchyCodes.add(hierarchyCodefour);
			 				      }
			 					  break;
			 				 case 9:
			 					  String hierarchyCodefour_nine = hierarchyCode.substring(0, 3);
								  String hierarchyCodeServen = hierarchyCode.substring(0, 6);
								  if(!hierarchyCodes.contains(hierarchyCodefour_nine)){
									 hierarchyCodes.add(hierarchyCodefour_nine);
							      }
								  if(!hierarchyCodes.contains(hierarchyCodeServen)){
										 hierarchyCodes.add(hierarchyCodeServen);
								  }
								  break;
			 				}
						}
					}
					for(UtOrgDTO utOrgDTO :orgdDtoList){
						if(hierarchyCodes.contains(utOrgDTO.getHierarchyCode())){
							listUtOrgDTO.add(utOrgDTO);
						}
					}
					
					
					if (CollectionUtils.isNotEmpty(listUtOrgDTO)) {
						for (UtOrgDTO orgDTO : listUtOrgDTO) {
							orgDTO.setIsleaf(this.checkIsChild(orgDTO.getOrgOid().toString()));
						}
					}
					return listUtOrgDTO;
				}
			}
		}else {
			// 单位不为空，内设机构为空时
			if (StringUtils.isNotEmpty(unitOid) && StringUtils.isEmpty(orgOidStr)) {
				List<UtOrgDTO> orgdDtoList = JhcOrgTreeQueryHelper
						.findOrgInfoByUnitOid(Long.valueOf(unitOid));
				if (CollectionUtils.isNotEmpty(orgdDtoList)) {
					for (UtOrgDTO orgDTO : orgdDtoList) {
						orgDTO.setIsleaf(this.checkIsChild(orgDTO.getOrgOid()
								.toString()));
					}
				}
				return orgdDtoList;
			}
			// 单位不为空，内设机构也不为空时
			if (StringUtils.isNotEmpty(unitOid)
					&& StringUtils.isNotEmpty(orgOidStr)) {
				List<UtOrgDTO> orgdDtoList = JhcOrgTreeQueryHelper
						.findOrgInfoByParentOrgOid(Long.valueOf(orgOidStr));
				if (CollectionUtils.isNotEmpty(orgdDtoList)) {
					for (UtOrgDTO orgDTO : orgdDtoList) {
						orgDTO.setIsleaf(this.checkIsChild(orgDTO.getOrgOid()
								.toString()));
					}
				}
				return orgdDtoList;
			}
		}
		return null;
	}

	/**
	 * 判断是否是叶子节点
	 * 
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	private boolean checkIsChild(String orgOid) throws ServiceException {
		return JhcUnitTreeQueryHelper.checkIsChild(orgOid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * JhcOrgTreeService#findOrgInfoUp
	 * (java.lang.String, java.lang.String)
	 */
	public UtOrgDTO findOrgInfoUp(String unitOid, String orgOid)
			throws ServiceException {
		// 如果单位OID不为空，内设机构Oid为空
		if (StringUtils.isNotEmpty(unitOid) && StringUtils.isEmpty(orgOid)) {
			// 返回单位信息
		}
		// 单位Oid不为空，内设机构Oid不为空
		if (StringUtils.isNotEmpty(unitOid) && StringUtils.isNotEmpty(orgOid)) {
			UtOrgDTO orgdDto = JhcOrgTreeQueryHelper
					.findParentOrgInfoByOrgOid(orgOid);
			return orgdDto;
		}

		return null;
	}

	public boolean checkIsChildByorganizationOid(Long organizationOid)
			throws ServiceException {
		return JhcOrgTreeQueryHelper.checkIsChild(organizationOid);
	}

	public List<UtOrgDTO> findOrgList(boolean checkUnit,String controlDataAuthority) throws ServiceException {
		if(checkUnit){
			List<UtOrgDTO> list = JhcOrgTreeQueryHelper.findOrgList(checkUnit);
			return list;
		}else{
			List<UtOrgDTO> listUtOrgDTO = new ArrayList<UtOrgDTO>();
			List<UtOrgDTO> allUtOrg = JhcOrgTreeQueryHelper.findAllOrgList();//所有的科室
			if(CollectionUtils.isNotEmpty(allUtOrg)){
				if(StringUtils.isNotEmpty(controlDataAuthority)){//是否需要控制权限
					return allUtOrg;
				}else{
					List<UtOrgDTO> list = JhcOrgTreeQueryHelper.findOrgList(checkUnit);//有权限的科室
					List<String> hierarchyCodes = new ArrayList<String>();
					if(CollectionUtils.isNotEmpty(list)){
						for(UtOrgDTO utOrgDTO :list){
			 				String hierarchyCode = utOrgDTO.getHierarchyCode();
			 				hierarchyCodes.add(hierarchyCode);//有权限的添加进去
			 				switch(hierarchyCode.length()){//没有权限 为了构造树  把上级节点加进去
			 				  case 6:
			 					  String hierarchyCodefour = hierarchyCode.substring(0,3);
			 					  if(!hierarchyCodes.contains(hierarchyCodefour)){
			 						 hierarchyCodes.add(hierarchyCodefour);
			 				      }
			 					  break;
			 				 case 9:
			 					  String hierarchyCodefour_nine = hierarchyCode.substring(0, 3);
								  String hierarchyCodeServen = hierarchyCode.substring(0, 6);
								  if(!hierarchyCodes.contains(hierarchyCodefour_nine)){
									 hierarchyCodes.add(hierarchyCodefour_nine);
							      }
								  if(!hierarchyCodes.contains(hierarchyCodeServen)){
										 hierarchyCodes.add(hierarchyCodeServen);
								  }
								  break;
			 				}
						}
					}
					for(UtOrgDTO utOrgDTO :allUtOrg){
						if(hierarchyCodes.contains(utOrgDTO.getHierarchyCode())){
							listUtOrgDTO.add(utOrgDTO);
						}
					}
					return listUtOrgDTO;
				}
			}else {
				return null;
			}
	   }
	}
}

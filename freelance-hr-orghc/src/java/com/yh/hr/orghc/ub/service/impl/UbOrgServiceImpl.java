package com.yh.hr.orghc.ub.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.unit.service.UtOrganizationComService;
import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.queryhelper.UbOrgQueryHelper;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.ub.util.UbOrgServiceUtil;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.queryhelper.UtOrgQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class UbOrgServiceImpl implements UbOrgService {

	private UtOrganizationComService	utOrganizationComService;
	
	public void setUtOrganizationComService(
			UtOrganizationComService utOrganizationComService) {
		this.utOrganizationComService = utOrganizationComService;
	}
	
	@Override
	public UbOrgDTO getUbOrgDTOById(Long orgOid) throws ServiceException {
		return UbOrgQueryHelper.getUbOrgDTOById(orgOid);
	}

	@Override
	public List<UbOrgDTO> listByUnitOid(Long unitOid) throws ServiceException {
		return UbOrgQueryHelper.listByUnitOid(unitOid);
	}

	@Override
	public List<UbOrgDTO> listByUnitOidAndStatus(Long unitOid,String orgStatus) throws ServiceException {
		return UbOrgQueryHelper.listByUnitOidAndStatus(unitOid,orgStatus);
	}
	
	@Override
	public Long createOrgInfo(UbOrgDTO ubOrgDTO) throws ServiceException {
		List<UbOrgDTO> ubOrgDTOs = listByUnitOidAndStatus(ubOrgDTO.getUnitOid(),DicConstants.YHRS0101_2);
		if(CollectionUtils.isNotEmpty(ubOrgDTOs)){
			for (UbOrgDTO dto : ubOrgDTOs) {
				if(dto.getOrgName().trim().equals(ubOrgDTO.getOrgName().trim())){
					throw new ServiceException(null,"同单位下内设机构名称不能重复！");
				}
			}
		}
		UbOrg ubOrg = new UbOrg();
		BeanHelper.copyProperties(ubOrgDTO, ubOrg);
		UbOrg parentUbOrg = null;
		String hierarchyCode = null;//本科室层级编码
		String parentHierarchyCode = null;//上级科室层级编码
		String maxHierarchyCode = null;//当前一级最大科室层级编码
		if(ubOrg.getParentOrgOid() != null && ubOrg.getParentOrgOid() != 0)
		{
			parentUbOrg = DaoUtil.get(UbOrg.class,ubOrg.getParentOrgOid());
		}
		if(parentUbOrg != null)
		{
			ubOrg.setOrderOfAll(parentUbOrg.getOrderOfAll() + ubOrgDTO.getOrderOfView());
			parentHierarchyCode = UbOrgServiceUtil.getHierarchyCodeByOrgOid(parentUbOrg.getOrgOid());
		}
		else
		{
			ubOrg.setOrderOfAll(ubOrgDTO.getOrderOfView());
		}
		
		maxHierarchyCode = UbOrgServiceUtil.getHierarchyCodeByParentOrgOid(parentHierarchyCode);
		hierarchyCode = UbOrgServiceUtil.buildHierarchyCode(maxHierarchyCode==null?(parentHierarchyCode==null?"0":parentHierarchyCode+"000"):maxHierarchyCode, parentHierarchyCode==null?3:parentHierarchyCode.length()+3);
		ubOrg.setHierarchyCode(hierarchyCode);
		
		//内设机构类型
		if(ubOrgDTO!=null&&ubOrgDTO.getOrgType()!=null){
			ubOrg.setOrgType(ubOrgDTO.getOrgType());
		}else{
			ubOrg.setOrgType(DicConstants.YHRS0102_2);
		}
		
		ubOrg.setCreatedByCode(UserContext.getLoginUserID());
		ubOrg.setCreatedByName(UserContext.getLoginUserName());
		ubOrg.setCreatedDate(DateUtil.now());
		ubOrg.save();
		//调用组件接口同步创建内设机构信息
		UtOrgDTO utOrgDTO = new UtOrgDTO();
		BeanHelper.copyProperties(ubOrg, utOrgDTO);
		utOrgDTO.setHierarchyCode(hierarchyCode);
		
		utOrganizationComService.synchroniseCreateOrgInfo(utOrgDTO);
		return ubOrg.getOrgOid();
	}

	@Override
	public void updateOrgInfo(UbOrgDTO ubOrgDTO) throws ServiceException {
		List<UbOrgDTO> ubOrgDTOs = listByUnitOidAndStatus(ubOrgDTO.getUnitOid(),DicConstants.YHRS0101_2);
		if(CollectionUtils.isNotEmpty(ubOrgDTOs)){
			for (UbOrgDTO dto : ubOrgDTOs) {
				if(dto.getOrgName().trim().equals(ubOrgDTO.getOrgName().trim()) 
						&& (dto.getOrgOid().longValue() != ubOrgDTO.getOrgOid().longValue()) ){
					throw new ServiceException(null,"同单位下内设机构名称不能重复！");
				}
			}
		}
		UbOrg ubOrg = DaoUtil.get(UbOrg.class, ubOrgDTO.getOrgOid());
		if(null == ubOrg){throw new ServiceException(null,"内设机构信息不存在！");}
		
		Long oldParentOrgOid = ubOrg.getParentOrgOid();	//原上级科室OID
		Long nowParentOrgOid = ubOrgDTO.getParentOrgOid();	//当前科室的上级科室OID
		BeanHelper.copyProperties(ubOrgDTO, ubOrg, BeanHelper.getNullPropertyNames(ubOrgDTO));
		
//		ubOrg.setParentOrgOid(ubOrgDTO.getParentOrgOid());
//		ubOrg.setOrgName(ubOrgDTO.getOrgName());
//		ubOrg.setOrgType(ubOrgDTO.getOrgType());
//		ubOrg.setOrgCategory(ubOrgDTO.getOrgCategory());
//		ubOrg.setSecondNameWork(ubOrgDTO.getSecondNameWork());	//第二名称(合署办公)
//		ubOrg.setSecondNameShow(ubOrgDTO.getSecondNameShow());	//第二名称（挂牌）
//		ubOrg.setBranchTypeCode(ubOrgDTO.getBranchTypeCode());
//		ubOrg.setLevelCode(ubOrgDTO.getLevelCode());
//		ubOrg.setEstablishedDate(ubOrgDTO.getEstablishedDate());	//成立时间
//		ubOrg.setCancelDate(ubOrgDTO.getCancelDate());	//撤销时间
//		ubOrg.setOrgFunction(ubOrgDTO.getOrgFunction());
//		ubOrg.setOrgStatus(ubOrgDTO.getOrgStatus());
//		ubOrg.setOrderOfView(ubOrgDTO.getOrderOfView());	//排序号
//		ubOrg.setRemark(ubOrgDTO.getRemark());	//备注
		UbOrg parentUbOrg = null;
		if(ubOrg.getParentOrgOid() != null && ubOrg.getParentOrgOid() != 0)
		{
			parentUbOrg = DaoUtil.get(UbOrg.class,ubOrg.getParentOrgOid());
		}
		if(parentUbOrg != null)
		{
			ubOrg.setOrderOfAll(parentUbOrg.getOrderOfAll() + ubOrgDTO.getOrderOfView());
		}
		else
		{
			ubOrg.setOrderOfAll(ubOrgDTO.getOrderOfView());
		}
		ubOrg.setUpdatedByCode(UserContext.getLoginUserID());
		ubOrg.setUpdatedByName(UserContext.getLoginUserName());
		ubOrg.setUpdatedDate(DateUtil.now());
		ubOrg.update();
		
		//调用组件接口同步更新内设机构信息
		UtOrgDTO utOrgDTO = new UtOrgDTO();
		BeanHelper.copyProperties(ubOrg, utOrgDTO);
		
		utOrganizationComService.synchroniseUpdateOrgInfo(utOrgDTO);

		//如果变更了上级科室
		if((oldParentOrgOid!=null&&nowParentOrgOid==null)
				|| (oldParentOrgOid==null&&nowParentOrgOid!=null)
				|| (oldParentOrgOid!=null&&nowParentOrgOid!=null&&oldParentOrgOid.longValue()!=nowParentOrgOid.longValue())) {
			//获取当前科室及其下级所有科室
			List<UbOrgDTO> ubOrgDTOList = UbOrgQueryHelper.listAllChildOrgByOid(ubOrg.getOrgOid());
			if(CollectionUtils.isNotEmpty(ubOrgDTOList)) {
				for(UbOrgDTO dto:ubOrgDTOList) {
					UbOrg org = DaoUtil.get(UbOrg.class, dto.getOrgOid());
					//置空层级编码
					org.setHierarchyCode(null);
					org.update();
					
					UtOrg utOrg = UtOrgQueryHelper.get(dto.getOrgOid());
					//置空层级编码
					utOrg.setHierarchyCode(null);
					utOrg.update();
				}
			}
			//生成科室层级编码
			UbOrgServiceUtil.createHierarchyCode();
		}
	}

	@Override
	public void deleteOrgInfo(Long orgOid) throws ServiceException {
		UbOrg ubOrg = DaoUtil.get(UbOrg.class, orgOid);
		if(null == ubOrg){throw new ServiceException(null,"内设机构信息不存在！");}
		ubOrg.delete();
		//调用组件接口同步删除内设机构信息
		utOrganizationComService.synchroniseDeleteOrgInfo(orgOid);
	}
}

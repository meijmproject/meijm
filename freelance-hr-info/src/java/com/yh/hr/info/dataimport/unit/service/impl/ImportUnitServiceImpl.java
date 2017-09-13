package com.yh.hr.info.dataimport.unit.service.impl;

import java.util.List;

import com.yh.hr.info.dataimport.unit.dto.ImportUnitDTO;
import com.yh.hr.info.dataimport.unit.queryhelper.ImportUnitQueryHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.dataimport.unit.service.ImportUnitService;
import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.unit.unitmanger.queryhelper.UnitMangerQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.bo.UtOrganization;
import com.yh.hr.res.unit.bo.UtRelation;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;

/**
 * 单位、科室导入serviceImpl
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitServiceImpl implements ImportUnitService{
	
   public UbUnitService ubOrgDTOService;
   public UbOrgService  ubOrgService;
   
	public void setUbUnitService(UbUnitService ubOrgDTOService) {
	this.ubOrgDTOService = ubOrgDTOService;
}

	public void setUbOrgService(UbOrgService ubOrgService) {
		this.ubOrgService = ubOrgService;
	}

	/* (non-Javadoc)
	 * @see ImportUnitService#importData(ImportUnitDTO)
	 */
	public void importData(ImportUnitDTO dto) throws ServiceException {
		
		if(StringUtils.isNotEmpty(dto.getOrgName())){
			List<UbUnit> units= ImportUnitQueryHelper.getUbUnit();
			UbUnit unit=units.get(0);
			//科室类型
			List<DicItem> items=DicHelper.findDicItemByCode(DicConstants.YHRS0102);
			for (DicItem dicItem : items) {
				if(dicItem.getDicItemName().equals(dto.getOrgTypeName())){
					dto.setOrgType(dicItem.getDicItemCode());
					dto.setOrgCategory(dicItem.getDicItemCode());
				}
			}
			UbOrg org=ImportUnitQueryHelper.getOrgByName(dto.getOrgTypeName());
			Long orgOid;
			if(null==org){
				String orderOfView=UnitMangerQueryHelper.getMaxOrderOfView(dto.getUnitOid());
				if(null==orderOfView){
					orderOfView="001";
				}else{
					orderOfView=NumberUtils.longValue(orderOfView)+1+"";
				}
				UbOrgDTO ubDTO=new UbOrgDTO();
				ubDTO.setOrgName(dto.getOrgTypeName());
				ubDTO.setOrgType(dto.getOrgType());
				ubDTO.setUnitOid(unit.getUnitOid());
				ubDTO.setOrgCategory(dto.getOrgCategory());
				ubDTO.setOrderOfView(orderOfView);
				ubDTO.setOrgStatus(DicConstants.YHRS0101_2);
				orgOid=ubOrgService.createOrgInfo(ubDTO);
			}else{
				orgOid=org.getOrgOid();
				if(null!=dto.getOrderOfView()&&null==org.getOrderOfView()){
					org.setOrderOfView(dto.getOrderOfView());
					ubOrgService.updateOrgInfo(BeanHelper.copyProperties(org, UbOrgDTO.class));
				}
			}

			//查询科室上级科室信息
			UbOrg parentUbOrg=new UbOrg();
			if(StringUtils.isNotEmpty(dto.getParentOrgName())){
				parentUbOrg = ImportUnitQueryHelper.getOrgByName(dto.getParentOrgName());
				if(null!=parentUbOrg){
					dto.setParentOrgOid(parentUbOrg.getOrgOid());
					dto.setParentOrgName(parentUbOrg.getOrgName());
					parentUbOrg.setParentOrgOid(orgOid);
					ubOrgService.updateOrgInfo(BeanHelper.copyProperties(parentUbOrg, UbOrgDTO.class));
				}
			}else{
				dto.setParentOrgOid(orgOid);
				dto.setParentOrgName(dto.getOrgTypeName());
			}
			
			UbOrgDTO ubOrgDTO=BeanHelper.copyProperties(dto, UbOrgDTO.class);
			ubOrgDTO.setOrgType(DicConstants.YHRS0102_2);
			ubOrgDTO.setOrgStatus(DicConstants.YHRS0101_2);
			ubOrgDTO.setUnitOid(unit.getUnitOid());
			ubOrgDTO.setOrderOfView(dto.getOrgOrderOfView());	//排序号
			ubOrgDTO.setEstablishedDate(dto.getOrgEstablishedDate());
			ubOrgDTO.setContacter(dto.getOrgContacter());	    //联系人
			ubOrgDTO.setMobilePhone(dto.getOrgMobilePhone());	//手机
			ubOrgDTO.setEmail(dto.getOrgEmail());	            //电子邮箱
			ubOrgDTO.setPhone(dto.getOrgPhone());	            //联系电话
			ubOrgDTO.setFax(dto.getOrgFax());	                //传真
			ubOrgDTO.setAddress(dto.getOrgAddress());	        //地址
			ubOrgDTO.setRemark(dto.getOrgRemark());	            //备注
			
			UbOrg ubOrg= ImportUnitQueryHelper.getOrgByName(dto.getOrgName());
			if(null!=ubOrg){
				ubOrgDTO.setOrgOid(ubOrg.getOrgOid());
				ubOrgService.updateOrgInfo(ubOrgDTO);
			}else{
				ubOrgService.createOrgInfo(ubOrgDTO);
			}
		}
	}

	/* (non-Javadoc)
	 * @see ImportUnitService#deleteData()
	 */
	public void deleteData() throws ServiceException {
		
			//删除科室基础信息
			List<UbOrg> ubOrgList =ImportUnitQueryHelper.getUbOrg();
			if(CollectionUtils.isNotEmpty(ubOrgList)){
				for (UbOrg ubOrg : ubOrgList) {
					ubOrg.delete();
				}
			}
			//删除科室业务信息
			List<UtOrg> utOrgList=ImportUnitQueryHelper.getUtOrg();
			if(CollectionUtils.isNotEmpty(utOrgList)){
				for (UtOrg utOrg : utOrgList) {
					utOrg.delete();
				}
			}
			//删除单位基础信息
//			List<UbUnit> ubUnitList =ImportUnitQueryHelper.getUbUnit();
//			if(CollectionUtils.isNotEmpty(ubUnitList)){
//				for (UbUnit ubUnit : ubUnitList) {
//					ubUnit.delete();
//				}
//			}
			//删除单位业务信息
//			List<UtUnit> utUnitList =ImportUnitQueryHelper.getUtUnit();
//			if(CollectionUtils.isNotEmpty(utUnitList)){
//				for (UtUnit utUnit : utUnitList) {
//					utUnit.delete();
//				}
//			}
			//删除组织关系信息
			List<UtRelation> utRelationList=ImportUnitQueryHelper.getUtRelation();
			if(CollectionUtils.isNotEmpty(utRelationList)){
				for (UtRelation utRelation : utRelationList) {
					utRelation.delete();
				}
			}
			//删除组织机构信息
			List<UtOrganization>  organizationList=ImportUnitQueryHelper.getUtOrganization();
			if(CollectionUtils.isNotEmpty(organizationList)){
				for (UtOrganization utOrganization : organizationList) {
					utOrganization.delete();
				}
			}
		}

	/* (non-Javadoc)
	 * @see ImportUnitService#getUnitByName(java.lang.String)
	 */
	public UbUnit getUnitByName(String orgUnitName) throws ServiceException {
		return ImportUnitQueryHelper.getUnitByName(orgUnitName);
	}

	/* (non-Javadoc)
	 * @see ImportUnitService#getUbUnit()
	 */
	public UbUnit getUbUnit() throws ServiceException {
		List<UbUnit> units=ImportUnitQueryHelper.getUbUnit();
		return null==units?null:units.get(0);
	}

	/* (non-Javadoc)
	 * @see ImportUnitService#getOrgByName(java.lang.String)
	 */
	public UbOrg getOrgByName(String orgName) throws ServiceException {
		return ImportUnitQueryHelper.getOrgByName(orgName);
	}

}

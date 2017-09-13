package com.yh.hr.orghc.unit.unitmanger.service.impl;


import java.util.List;

import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.service.UbHcService;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.unit.unitmanger.queryhelper.UnitMangerQueryHelper;
import com.yh.hr.orghc.unit.unitmanger.service.UnitMangerService;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.service.UbLeaderService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

public class UnitMangerServiceImpl implements UnitMangerService {
	

	private UbOrgService ubOrgService;
	private UbLeaderService ubLeaderService;
	private UbHcService ubHcService;
	private UbUnitService ubUnitService;
	

	public void setUbOrgService(UbOrgService ubOrgService) {
		this.ubOrgService = ubOrgService;
	}

	public void setUbLeaderService(UbLeaderService ubLeaderService) {
		this.ubLeaderService = ubLeaderService;
	}

	public void setUbHcService(UbHcService ubHcService) {
		this.ubHcService = ubHcService;
	}

	public void setUbUnitService(UbUnitService ubUnitService) {
		this.ubUnitService = ubUnitService;
	}

	public int listUnitCounts() throws ServiceException {
		return UnitMangerQueryHelper.listUnitCounts();
	}

	@Override
	public UbUnit getUnitInformationByUnitOid(String unitOid)
			throws ServiceException {
		return DaoUtil.get(UbUnit.class,Long.valueOf(unitOid));
	}

	@Override
	public List<UbOrgDTO> listOrgByUnitOidAndStatus(String unitOid)
			throws ServiceException {
		return ubOrgService.listByUnitOidAndStatus(Long.valueOf(unitOid),"2");
	}

	@Override
	public List<UbHcDTO> listhcByUnitOid(String unitOid)
			throws ServiceException {
		return ubHcService.listByUnitOid(Long.valueOf(unitOid));
	}

	@Override
	public List<UbLeaderDTO> listleaderByUnitOid(String unitOid)
			throws ServiceException {
		return ubLeaderService.listByUnitOid(Long.valueOf(unitOid));
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#listOrg(TableTagBean)
	 */
	public List<JSONObject> listOrg(TableTagBean ttb) throws ServiceException {
		List<JSONObject> list =UnitMangerQueryHelper.listOrg(ttb);
//		if(null!=list){
//			for (JSONObject jsonObject : list) {
//				UbOrgDTO dto=ubOrgService.getUbOrgDTOById(NumberUtils.objToLong(jsonObject.get("orgOid")));
//				String orgType=getOrgType(BeanHelper.copyProperties(dto, OrgDTO.class));
//				jsonObject.put("orgCategoryName", DicHelper.viewName(DicConstants.YHRS0102, orgType));
//			}
//		}
		return list;
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#create(OrgDTO)
	 */
	public Long create(OrgDTO dto) throws ServiceException{
		//科室类型
		List<DicItem> items=DicHelper.findDicItemByCode(DicConstants.YHRS0102);
		for (DicItem dicItem : items) {
			if(dicItem.getDicItemCode().equals(dto.getOrgCategory())){
				dto.setOrgTypeName(dicItem.getDicItemName());
			}
		}
		UbOrg org=UnitMangerQueryHelper.getOrgByName(dto.getOrgTypeName());
		Long orgOid;
		if(null==org){
			
			String orderOfView=UnitMangerQueryHelper.getMaxOrderOfView(dto.getUnitOid());
			Long longValue=NumberUtils.longValue(orderOfView);
			if(null==orderOfView){
				orderOfView="001";
			}else{
				orderOfView=longValue.toString().length()==1?"00"+(longValue+1):longValue.toString().length()==2?"0"+(longValue+1):+longValue+1+"";
			}
			UbOrgDTO ubDTO=new UbOrgDTO();
			ubDTO.setOrgName(dto.getOrgTypeName());
			ubDTO.setOrgType(dto.getOrgCategory());
			ubDTO.setUnitOid(dto.getUnitOid());
			ubDTO.setOrgCategory(dto.getOrgCategory());
			ubDTO.setOrderOfView(orderOfView);
			ubDTO.setOrgStatus(DicConstants.YHRS0101_2);
			orgOid=ubOrgService.createOrgInfo(ubDTO);
		}else{
			if(null==org.getOrderOfView()&&null==org.getOrderOfAll()){
				String orderOfView=UnitMangerQueryHelper.getMaxOrderOfView(dto.getUnitOid());
				Long longValue=NumberUtils.longValue(orderOfView);
				if(null==orderOfView){
					orderOfView="001";
				}else{
					orderOfView=longValue.toString().length()==1?"00"+(longValue+1):longValue.toString().length()==2?"0"+(longValue+1):+longValue+1+"";
				}
				org.setOrderOfView(orderOfView);
				org.setOrderOfAll(orderOfView);
				org.update();
			}
			if(null!=org.getOrderOfView()&&null==org.getOrderOfAll()){
				org.setOrderOfAll(org.getOrderOfView());
				org.update();
			}
			orgOid=org.getOrgOid();
		}
		//查询科室上级科室信息
		UbOrgDTO parentUbOrg=new UbOrgDTO();
		if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
			parentUbOrg = ubOrgService.getUbOrgDTOById(dto.getParentOrgOid());
			if(null==parentUbOrg){
				throw new ServiceException(null,"上级科室不存在");
			}else{
				dto.setParentOrgName(parentUbOrg.getOrgName());
			}
		}else{
			dto.setParentOrgOid(orgOid);
			dto.setParentOrgName(dto.getOrgTypeName());
		}
		
		dto.setOrgType(DicConstants.YHRS0102_2);
		dto.setOrgStatus(DicConstants.YHRS0101_2);
		return ubOrgService.createOrgInfo(BeanHelper.copyProperties(dto, UbOrgDTO.class));
	}

	public void updateUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException {
		ubUnitService.update(ubUnitDTO);
		
//		UbUnit ubUnit = DaoUtil.get(UbUnit.class, ubUnitDTO.getUnitOid());
//		BeanHelper.copyProperties(ubUnitDTO,ubUnit);
//		ubUnit.setUpdatedByCode(UserContext.getLoginUserID());
//		ubUnit.setUpdatedByName(UserContext.getLoginUserName());
//		ubUnit.setUpdatedDate(DateUtil.now());
//		ubUnit.update();
	}

	@Override
	public void addVerUbHcInfoDTO(UbHcDTO ubHcDTO) throws ServiceException {
		ubHcService.createHcInfo(ubHcDTO);
		
	}

	@Override
	public void deleteSaoUbHcDTO(Long hcId) throws ServiceException {
		ubHcService.deleteHcInfo(hcId);
	}

	@Override
	public UbHcDTO getVerUbHcInfoDTO(Long hcOid) throws ServiceException {
		return ubHcService.getUbHcDTOById(hcOid);
	}

	@Override
	public void updateUbHcDTO(UbHcDTO ubHcDTO) throws ServiceException {
		 ubHcService.updateHcInfo(ubHcDTO);
	}

	@Override
	public void addVerUbLeaderInfo(UbLeaderDTO ubLeaderDTO)
			throws ServiceException {
		ubLeaderService.createLeaderInfo(ubLeaderDTO);
		
	}

	@Override
	public void deleteVerUbLeaderInfo(Long leaderOid) throws ServiceException {
		ubLeaderService.deleteLeaderInfo(leaderOid);
	}

	@Override
	public void updateVerUbLeaderInfo(UbLeaderDTO oldVerUbLeaderInfoDTO)
			throws ServiceException {
		ubLeaderService.updateLeaderInfo(oldVerUbLeaderInfoDTO);
		
	}

	@Override
	public List<UbLeaderDTO> listVerUbLeaderInfo(Long unitOid)
			throws ServiceException {
		return ubLeaderService.listByUnitOid(unitOid);
	}

	@Override
	public UbLeaderDTO getVerUbLeaderInfo(Long leaderOid)
			throws ServiceException {
		return ubLeaderService.getUbOrgDTOById(leaderOid);
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#update(OrgDTO)
	 */
	public void update(OrgDTO dto) throws ServiceException {
		//科室类型
		List<DicItem> items=DicHelper.findDicItemByCode(DicConstants.YHRS0102);
		for (DicItem dicItem : items) {
			if(dicItem.getDicItemCode().equals(dto.getOrgCategory())){
				dto.setOrgTypeName(dicItem.getDicItemName());
			}
		}
		UbOrg org=UnitMangerQueryHelper.getOrgByName(dto.getOrgTypeName());
		Long orgOid;
		if(null==org){
			String orderOfView=UnitMangerQueryHelper.getMaxOrderOfView(dto.getUnitOid());
			Long longValue=NumberUtils.longValue(orderOfView);
			if(null==orderOfView){
				orderOfView="001";
			}else{
				orderOfView=longValue.toString().length()==1?"00"+(longValue+1):longValue.toString().length()==2?"0"+(longValue+1):+longValue+1+"";
			}
			UbOrgDTO ubDTO=new UbOrgDTO();
			ubDTO.setOrgName(dto.getOrgTypeName());
			ubDTO.setOrgType(dto.getOrgCategory());
			ubDTO.setUnitOid(dto.getUnitOid());
			ubDTO.setOrgCategory(dto.getOrgCategory());
			ubDTO.setOrgStatus(DicConstants.YHRS0101_2);
			ubDTO.setOrderOfView(orderOfView);
			orgOid=ubOrgService.createOrgInfo(ubDTO);
		}else{
			if(null==org.getOrderOfView()&&null==org.getOrderOfAll()){
				String orderOfView=UnitMangerQueryHelper.getMaxOrderOfView(dto.getUnitOid());
				Long longValue=NumberUtils.longValue(orderOfView);
				if(null==orderOfView){
					orderOfView="001";
				}else{
					orderOfView=longValue.toString().length()==1?"00"+(longValue+1):longValue.toString().length()==2?"0"+(longValue+1):+longValue+1+"";
				}
				org.setOrderOfView(orderOfView);
				org.setOrderOfAll(orderOfView);
				org.update();
			}
			if(null!=org.getOrderOfView()&&null==org.getOrderOfAll()){
				org.setOrderOfAll(org.getOrderOfView());
				org.update();
			}
			orgOid=org.getOrgOid();
		}
		//查询科室上级科室信息
		UbOrgDTO parentUbOrg=new UbOrgDTO();
		if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
			parentUbOrg = ubOrgService.getUbOrgDTOById(dto.getParentOrgOid());
			if(null==parentUbOrg){
				throw new ServiceException(null,"上级科室不存在");
			}else{
				List<UbOrgDTO> list=getOrg(dto.getUnitOid(),dto.getOrgOid());
				if(CollectionUtils.isNotEmpty(list)){
					throw new ServiceException(null,"科室下存在子科室，不可设置上级科室");
				}
				dto.setParentOrgName(parentUbOrg.getOrgName());
			}
		}else{
			dto.setParentOrgOid(orgOid);
			dto.setParentOrgName(dto.getOrgTypeName());
		}
		
		dto.setOrgType(DicConstants.YHRS0102_2);
		dto.setOrgStatus(DicConstants.YHRS0101_2);
		ubOrgService.updateOrgInfo(BeanHelper.copyProperties(dto, UbOrgDTO.class));
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#getOrg(java.lang.Long)
	 */
	public OrgDTO getOrg(Long orgOid) throws ServiceException {
		OrgDTO dto=BeanHelper.copyProperties(ubOrgService.getUbOrgDTOById(orgOid), OrgDTO.class);
		if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
			UbOrgDTO uo=ubOrgService.getUbOrgDTOById(dto.getParentOrgOid());
			if(null!=uo){
				dto.setParentOrgName(uo.getOrgName());
			}
		}
//		dto.setOrgType(getOrgType(dto));
		return dto;
	}

	/**
	 * 获取科室类型
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unused")
	private String getOrgType(OrgDTO dto) throws ServiceException {
		UbOrgDTO uo=new UbOrgDTO();
		if(DicConstants.YHRS0102_2.equals(dto.getOrgType())){
			if(StringUtil.isNotNull(dto.getParentOrgOid())){
				uo=ubOrgService.getUbOrgDTOById(dto.getParentOrgOid());
				if(null!=uo){
					return getOrgType(BeanHelper.copyProperties(uo, OrgDTO.class));
				}
			}else{
				return dto.getOrgCategory();
			}
		}
		return dto.getOrgType();
	}

	@Override
	public void addUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException {
		ubUnitService.create(ubUnitDTO);
		
//		UtOrganization utOrganization = new UtOrganization();
//		utOrganization.setOrganizationName(ubUnitDTO.getUnitName());
//		utOrganization.setOrganizationType(UtConstants.UT_ORGANIZATION_TYPE_1);
//		utOrganization.setCreatedByCode(UserContext.getLoginUserID());
//		utOrganization.setCreatedByName(UserContext.getLoginUserName());
//		utOrganization.setCreatedDate(DateUtil.now());
//		utOrganization.save();
//		UbUnit ubUnit = new UbUnit();
//		BeanHelper.copyProperties(ubUnitDTO, ubUnit);
//		ubUnit.setCreatedByCode(UserContext.getLoginUserID());
//		ubUnit.setCreatedByName(UserContext.getLoginUserName());
//		ubUnit.setCreatedDate(DateUtil.now());
//		ubUnit.save(); 
//		UtUnit utUnit =  new UtUnit();
//		utUnit = BeanHelper.copyProperties(ubUnit, UtUnit.class);
//		utUnit.setOrganizationOid(utOrganization.getOrganizationOid());
//		utUnit.save();
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#findOrgTypeByParent(java.lang.String)
	 */
	public List<OrgDTO> findByOrgType(String unitOid,String orgType)
			throws ServiceException {
		return UnitMangerQueryHelper.findByOrgType(unitOid,orgType);
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#getOrg(java.lang.String, java.lang.String)
	 */
	public List<UbOrgDTO> getOrg(Long unitOid, Long orgOid)
			throws ServiceException {
		return UnitMangerQueryHelper.getOrg(unitOid,orgOid);
	}

	/* (non-Javadoc)
	 * @see UnitMangerService#revokeOrg(java.lang.Long)
	 */
	public void updateOrgStatus(Long orgOid) throws ServiceException {
		UbOrgDTO dto=ubOrgService.getUbOrgDTOById(orgOid);
		List<PbPersonInfo> list=UnitMangerQueryHelper.listPersonByOrgOid(orgOid);
		if(CollectionUtils.isNotEmpty(list)){
			throw new ServiceException(null,"请先处理该科室下的在职人员！");
		}
		dto.setOrgStatus(DicConstants.YHRS0101_3);
		dto.setUpdatedByCode(UserContext.getLoginUserID());
		dto.setUpdatedByName(UserContext.getLoginUserName());
		dto.setUpdatedDate(DateUtil.now());
		dto.setCancelDate(DateUtil.now());
		ubOrgService.updateOrgInfo(dto);
	}
}

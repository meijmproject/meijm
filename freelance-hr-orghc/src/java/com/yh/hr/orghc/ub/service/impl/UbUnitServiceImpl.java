package com.yh.hr.orghc.ub.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.queryhelper.UbUnitQueryHelper;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.component.unit.service.UtOrganizationComService;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class UbUnitServiceImpl implements UbUnitService {
	
	private UtOrganizationComService	utOrganizationComService;

	public void setUtOrganizationComService(
			UtOrganizationComService utOrganizationComService) {
		this.utOrganizationComService = utOrganizationComService;
	}

	@Override
	public UbUnit get(Long unitOid) throws ServiceException {
		return UbUnitQueryHelper.get(unitOid);
	}
	
	@Override
	public String getUnitName(Long unitOid) throws ServiceException {
		return UbUnitQueryHelper.get(unitOid) == null ? null : UbUnitQueryHelper.get(unitOid).getUnitName();
	}
	
	@Override
	public UbUnitDTO getUbUnitDTOById(Long unitOid) throws ServiceException {
		UbUnitDTO ubUnitDTO = UbUnitQueryHelper.getUbUnitDTOById(unitOid);
		return ubUnitDTO;
	}
	
	@Override
	public Long create(UbUnitDTO ubUnitDTO) throws ServiceException {
		
		//判断单位名称是否已存在--基础库
		boolean flag = UbUnitQueryHelper.checkUniqueUnit(ubUnitDTO.getUnitName(),null);
		if(flag){
			throw new ServiceException(null, "该单位名称已经存在");
		}
		
		UbUnit ubUnit = BeanHelper.copyProperties(ubUnitDTO, UbUnit.class);
		ubUnit.setUnitCode(createUnitCode(ubUnitDTO.getUnitOid(),ubUnitDTO.getParentUnitOid()));
		ubUnit.setUnitStatus(DicConstants.YHRS0101_2);
		ubUnit.setCreatedByCode(UserContext.getLoginUserID());
		ubUnit.setCreatedByName(UserContext.getLoginUserName());
		ubUnit.setCreatedDate(DateUtil.now());
		ubUnit.save();
		//调用组件接口同步机构账户信息
		UtUnitDTO utUnitDTO = new UtUnitDTO();
		BeanHelper.copyProperties(ubUnit, utUnitDTO);
		utUnitDTO.setUnitLevelCode(ubUnit.getLevelCode());
		utUnitDTO.setUnitAreaCode(ubUnit.getAreaCode());
		utOrganizationComService.synchroniseCreateUnitInfo(utUnitDTO);
		
		return ubUnit.getUnitOid();
	}
	
	@Override
	public void update(UbUnitDTO ubUnitDTO) throws ServiceException {
		
		//判断单位名称是否已存在--基础库
		boolean flag = UbUnitQueryHelper.checkUniqueUnit(ubUnitDTO.getUnitName(),ubUnitDTO.getUnitOid());
		if(flag){
			throw new ServiceException(null, "该单位名称已经存在");
		}
		
		UbUnit ubUnit = get(ubUnitDTO.getUnitOid());
		if(null == ubUnit){throw new ServiceException(null,"单位信息不存在！");}
		BeanHelper.copyProperties(ubUnitDTO, ubUnit, BeanHelper.getNullPropertyNames(ubUnitDTO));

//		ubUnit.setUnitShortName(ubUnitDTO.getUnitShortName());	//单位简称
//		ubUnit.setSecondNameWork(ubUnitDTO.getSecondNameWork());	//第二名称(合署办公)
//		ubUnit.setSecondNameShow(ubUnitDTO.getSecondNameShow());	//第二名称（挂牌）
//		ubUnit.setUnitCategoryCode(ubUnitDTO.getUnitCategoryCode());	//系统类别YHRS0091
//		ubUnit.setUnitTypeCode(ubUnitDTO.getUnitTypeCode());	//机关机构类别YHRS0092
//		ubUnit.setLevelCode(ubUnitDTO.getLevelCode());	//机构级别YHRS0093
//		ubUnit.setInternalOrgLevel(ubUnitDTO.getInternalOrgLevel());	//内设机构级别YHRS0093
//		ubUnit.setBudgetFromCode(ubUnitDTO.getBudgetFromCode());	//经费来源
//		ubUnit.setIsUnionPay(ubUnitDTO.getIsUnionPay());	//是否统发YHRS0003
//		ubUnit.setIsSecurityUnit(ubUnitDTO.getIsSecurityUnit());	//是否机密单位YHRS0003
//		//ubUnit.setUnitFunction(ubUnitDTO.getUnitFunction());	//单位职能
//		ubUnit.setBranchTypeCode(ubUnitDTO.getBranchTypeCode());	//机关下设机构编码（中编办）YHRS0094
//		ubUnit.setUnitAttrSrvCode(ubUnitDTO.getUnitAttrSrvCode());	//单位属性（中编办）（机关）YHRS0095
//		ubUnit.setUnitTypeCodeZbb(ubUnitDTO.getUnitTypeCodeZbb());	//机关编码（中编办）YHRS0096
//		ubUnit.setRelationCode(ubUnitDTO.getRelationCode());	//机构隶属关系（事业）YHRS0097
//		ubUnit.setIndustryCode(ubUnitDTO.getIndustryCode());	//所属行业（中编办）（事业）YHRS0098
//		ubUnit.setUnitTypeBizCode(ubUnitDTO.getUnitTypeBizCode());	//事业单位类型（中编办）（事业）YHRS0099
//		ubUnit.setUnitAttrCode(ubUnitDTO.getUnitAttrCode());	//单位属性（中编办）（事业）YHRS0100
//		ubUnit.setUnitLegal(ubUnitDTO.getUnitLegal());	//是否法定机构（事业）YHRS0003
//		ubUnit.setCorporationCode(ubUnitDTO.getCorporationCode());	//组织机构代码
//		ubUnit.setEstablishedDate(ubUnitDTO.getEstablishedDate());	//成立时间
//		ubUnit.setCancelDate(ubUnitDTO.getCancelDate());	//撤销时间
//		ubUnit.setOrderOfView(ubUnitDTO.getOrderOfView());	//排序号
//		ubUnit.setContacter(ubUnitDTO.getContacter());	//联系人
//		ubUnit.setOtherContacter(ubUnitDTO.getOtherContacter());	//其它联系人
//		ubUnit.setMobilePhone(ubUnitDTO.getMobilePhone());	//手机
//		ubUnit.setEmail(ubUnitDTO.getEmail());	//电子邮箱
//		ubUnit.setPhone(ubUnitDTO.getPhone());	//联系电话
//		ubUnit.setFax(ubUnitDTO.getFax());	//传真
//		ubUnit.setAddress(ubUnitDTO.getAddress());	//单位地址
//		ubUnit.setZipcode(ubUnitDTO.getZipcode());	//邮政编码
//		ubUnit.setUnitCreditNo(ubUnitDTO.getUnitCreditNo());	//统一社会信用代码
//		ubUnit.setRemark(ubUnitDTO.getRemark());	//备注
//		ubUnit.setFileNo(ubUnitDTO.getFileNo());//文号
		/*if(null != ubUnitDTO.getUnitFunc()){			
			ubUnit.setUnitFunc(ubUnitDTO.getUnitFunc());//单位功能
		}*/
		ubUnit.setUpdatedByCode(UserContext.getLoginUserID());
		ubUnit.setUpdatedByName(UserContext.getLoginUserName());
		ubUnit.setUpdatedDate(DateUtil.now());
		//如果修改所属上级单位，则维护单位本身及其所有下设单位的单位编码
		Long oldParentUnitOid = null == ubUnit.getParentUnitOid() ? 0L : ubUnit.getParentUnitOid();
		Long newParentUnitOid = null == ubUnitDTO.getParentUnitOid() ? 0L : ubUnitDTO.getParentUnitOid();
		if(oldParentUnitOid.intValue() != newParentUnitOid.intValue()){
			List<UbUnit> listSon = UbUnitQueryHelper.listUnitSonsByUnitOid(ubUnitDTO.getUnitOid());
			Map<Long,List<UbUnit>> mapSon= new HashMap<Long, List<UbUnit>>();
			if(CollectionUtils.isNotEmpty(listSon)){
				Long parentUnitOid = null;
				List<UbUnit> list = null;
	            for (UbUnit unit : listSon) {
	                parentUnitOid = unit.getParentUnitOid();
	                list = mapSon.get(parentUnitOid);
	                if (CollectionUtils.isEmpty(list)) {
	                	list = new ArrayList<UbUnit>();
	                }
	                list.add(unit);
	                mapSon.put(parentUnitOid, list);
	            }
			}
			updateChildrenUnitCode(mapSon, ubUnitDTO);
		}else{
			ubUnit.update();
		}
		//调用组件接口同步机构账户信息
		UtUnitDTO utUnitDTO = new UtUnitDTO();
		BeanHelper.copyProperties(ubUnit, utUnitDTO);
		utUnitDTO.setUnitLevelCode(ubUnit.getLevelCode());
		utUnitDTO.setUnitAreaCode(ubUnit.getAreaCode());
		utOrganizationComService.synchroniseUpdateUnitInfo(utUnitDTO);
	}
	
	@Override
	public void update(UbUnit ubUnit) throws ServiceException {
		ubUnit.update();
		//调用组件接口同步机构账户信息
		UtUnitDTO utUnitDTO = new UtUnitDTO();
		BeanHelper.copyProperties(ubUnit, utUnitDTO);
		utUnitDTO.setUnitLevelCode(ubUnit.getLevelCode());
		utUnitDTO.setUnitAreaCode(ubUnit.getAreaCode());
		utOrganizationComService.synchroniseUpdateUnitInfo(utUnitDTO);
	}
	
	/**
	 * 更新下设单位的单位编码
	 * @param unitOid
	 * @throws ServiceException
	 */
	public void updateChildrenUnitCode(Map<Long, List<UbUnit>> mapSon,UbUnitDTO ubUnitDTO) throws ServiceException {
		UbUnit ubUnit = get(ubUnitDTO.getUnitOid());
		ubUnit.setUnitCode(createUnitCode(ubUnit.getUnitOid(),ubUnitDTO.getParentUnitOid()));
		ubUnit.setParentUnitOid(ubUnitDTO.getParentUnitOid());
		update(ubUnit);
		List<UbUnit> children = mapSon.get(ubUnitDTO.getUnitOid());
		if(CollectionUtils.isNotEmpty(children)){
			for(UbUnit child : children){
				updateChildrenUnitCode(mapSon, BeanHelper.copyProperties(child, UbUnitDTO.class));
			}
		}
	}
	
	/**
	 * 产生单位编码
	 * @param unitMovement
	 * @return
	 * @throws DataAccessException
	 */
	public String createUnitCode(Long unitOid,Long parentUnitOid)throws ServiceException{
		return UbUnitQueryHelper.createUnitCode(unitOid,parentUnitOid);
	}
}

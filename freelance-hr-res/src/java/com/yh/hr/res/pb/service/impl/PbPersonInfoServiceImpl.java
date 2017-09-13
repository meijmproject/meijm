package com.yh.hr.res.pb.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbPersonInfoQueryHelper;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PbPersonInfoServiceImpl implements PbPersonInfoService {
	
//	private PbWageHistoryService pbWageHistoryService = (PbWageHistoryService) SpringBeanUtil.getBean("pbWageHistoryService");
//	private PbOwnAllowanceService pbOwnAllowanceService = (PbOwnAllowanceService) SpringBeanUtil.getBean("pbOwnAllowanceService");
	public void updatePbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException {
		checkPersonInfo(pbPersonInfoDTO);
		PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class, pbPersonInfoDTO.getPersonOid());
		BeanHelper.copyProperties(pbPersonInfoDTO,pbPersonInfo);
		pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
		pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
		pbPersonInfo.setUpdateDate(DateUtil.now());
		pbPersonInfo.update();
		//同步信息到人员附属表
		PersonAttachInfoServiceUtil.sysPbPersonInfo(pbPersonInfo.getPersonOid());
	}
	
	public Long addPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException {
		checkPersonInfo(pbPersonInfoDTO);
		PbPersonInfo pbPersonInfo =BeanHelper.copyProperties(pbPersonInfoDTO,PbPersonInfo.class);
		pbPersonInfo.setCreateBy(UserContext.getLoginUserID());
		pbPersonInfo.setCreateName(UserContext.getLoginUserName());
		pbPersonInfo.setCreateDate(DateUtil.now());
		pbPersonInfo.save();
		//同步信息到人员附属表
		PersonAttachInfoServiceUtil.sysPbPersonInfo(pbPersonInfo.getPersonOid());
		return pbPersonInfo.getPersonOid();
	}
    /**
     * 检查人员基本信息
     * @param pbPersonInfoDTO
     * @throws ServiceException
     */
	private void checkPersonInfo(PbPersonInfoDTO pbPersonInfoDTO)
			throws ServiceException {
		StringBuffer errMsg = new StringBuffer();
		if(StringUtils.isNotEmpty(pbPersonInfoDTO.getIdCode())&&StringUtils.isNotEmpty(pbPersonInfoDTO.getIdNo())){
		List<PbPersonInfoDTO> list=PbPersonInfoQueryHelper.checkUniquePbPerson(pbPersonInfoDTO.getIdCode(),pbPersonInfoDTO.getIdNo(),pbPersonInfoDTO.getPersonOid());
		if(CollectionUtils.isNotEmpty(list)){
			String str = "该证件类型与证件号码人员已经存在";
			errMsg.append(str).append("<br/>");
			//throw new ServiceException(null, "该证件类型与证件号码人员已经存在");
		}
		}
		if(StringUtils.isNotEmpty(pbPersonInfoDTO.getPersonCode())){
		List<PbPersonInfoDTO> listDto=PbPersonInfoQueryHelper.checkUniquePbPersonCode(pbPersonInfoDTO.getPersonCode(),pbPersonInfoDTO.getPersonOid());
		if(CollectionUtils.isNotEmpty(listDto)){
			String str = "该工号已经存在";
			errMsg.append(str).append("<br/>");
			//throw new ServiceException(null, "该工号已经存在");
		}
		}
		if(StringUtils.isNotEmpty(errMsg.toString()))
		{
			throw new ServiceException(null, errMsg.toString());
		}
	}

	public void deletePbPersonInfoById(Long personOid) throws ServiceException {
		/*if("migration".equals(DaoUtil.get(PbPersonInfo.class, personOid).getCreateBy())){
			throw new ServiceException(null, "迁移的数据不允许删除!");
		}*/
		//删除家庭主要情况信息
		DaoUtil.executeUpdate("delete from PbFamilyInfo p where p.personOid="+personOid);
		//删除事业岗位聘任信息
		DaoUtil.executeUpdate("delete from PbSyGwEmployInfo p where p.personOid="+personOid);
		//删除院内岗位聘任信息
		DaoUtil.executeUpdate("delete from PbYnGwEmployInfo p where p.personOid="+personOid);
		//删除合同信息
		DaoUtil.executeUpdate("delete from PbEngageContractInfo p where p.personOid="+personOid);
		//删除合同变动历史信息
		DaoUtil.executeUpdate("delete from PbEngageConHistInfo p where p.personOid="+personOid);
		//删除学历学位信息
		DaoUtil.executeUpdate("delete from PbEducationLevelDegree p where p.personOid="+personOid);
		//删除教育培训经历信息
		DaoUtil.executeUpdate("delete from PbEducationTrainingInfo p where p.personOid="+personOid);
		//删除专业技术资格信息
		DaoUtil.executeUpdate("delete from PbProfTechQualifInfo p where p.personOid="+personOid);
		//删除执业（职业）资格信息
		DaoUtil.executeUpdate("delete from PbQualificationsInfo p where p.personOid="+personOid);
		//删除执业注册信息
		DaoUtil.executeUpdate("delete from PbCertificateInfo p where p.personOid="+personOid);
		//删除执业注册历史信息
		DaoUtil.executeUpdate("delete from PbCertificateHistInfo p where p.personOid="+personOid);
		//删除特殊授权情况
		DaoUtil.executeUpdate("delete from PbSpeciaAuth p where p.personOid="+personOid);
		//删除年度考核信息
		DaoUtil.executeUpdate("delete from PbReviewInfo p where p.personOid="+personOid);
		//删除治面貌与党派信息
		DaoUtil.executeUpdate("delete from PbPoliticInfo p where p.personOid="+personOid);
		//删除奖励信息
		DaoUtil.executeUpdate("delete from PbRewardInfo p where p.personOid="+personOid);	
		//删除处分信息
		DaoUtil.executeUpdate("delete from PbPunishmentInfo p where p.personOid="+personOid);
		//删除工伤信息
		DaoUtil.executeUpdate("delete from PbInjuryInfo p where p.personOid="+personOid);
		//删除工作经历
		DaoUtil.executeUpdate("delete from PbResumeInfo p where p.personOid="+personOid);
		//删除离退休情况
		DaoUtil.executeUpdate("delete from PbRetrieInfo p where p.personOid="+personOid);
		//删除自然减员信息
		DaoUtil.executeUpdate("delete from PbDeathInfo p where p.personOid="+personOid);
		//删除图片信息
		DaoUtil.executeUpdate("delete from PbImage p where p.personOid="+personOid);
		//删除院内中层职务信息
		DaoUtil.executeUpdate("delete from PbDutyInfo p where p.personOid="+personOid);
		//删除附属信息
		DaoUtil.executeUpdate("delete from PbPersonAttach p where p.personOid="+personOid);
		//删除人员进入信息
		DaoUtil.executeUpdate("delete from PbPersonIn p where p.personOid="+personOid);
		//删除人员离开信息
		DaoUtil.executeUpdate("delete from PbPersonOut p where p.personOid="+personOid);
		//删除人员外出信息
		DaoUtil.executeUpdate("delete from PbGoOutInfo p where p.personOid="+personOid);
		//删除人员加班信息
		DaoUtil.executeUpdate("delete from PbOvertimeInfo p where p.personOid="+personOid);
		//删除人员加班信息
		DaoUtil.executeUpdate("delete from PbChangeOffInfo p where p.personOid="+personOid);
		//删除人员调休信息
		DaoUtil.executeUpdate("delete from PbVacation p where p.personOid="+personOid);
		//删除人员请休假信息
		//删除人员附件
		DaoUtil.executeUpdate("delete from PbPhoto p where p.personOid="+personOid);
		DaoUtil.get(PbPersonInfo.class,personOid).delete();
	}	

	public PbPersonInfoDTO getPbPersonInfoDTOById(Long personOid) throws ServiceException {
		return PbPersonInfoQueryHelper.getPbPersonInfoDTOById(personOid);
	}

	public List<PbPersonInfoDTO> listPbPersonInfo(TableTagBean ttb) throws ServiceException {
		return PbPersonInfoQueryHelper.listPbPersonInfo(ttb);
	}

	/**
	 * 通过姓名模糊查询基础人员信息列表
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public  List<PbPersonInfoDTO> listPbPersonInfoByName(String name) throws ServiceException {
		return PbPersonInfoQueryHelper.listPbPersonInfoByName(name);
	}

	/**
	 * 检查是否可以修改人员信息
	 * @param pbPersonInfoDTO
	 */
	@SuppressWarnings("unused")
	private void checkCanUpdate(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException
	{
		List<PbPersonInfoDTO> list = PbPersonInfoQueryHelper.checkUniquePbPerson(pbPersonInfoDTO.getIdCode(), pbPersonInfoDTO.getIdNo(), pbPersonInfoDTO.getPersonOid());
		if(CollectionUtils.isNotEmpty(list))
		{
			throw new ServiceException(null,"已存在相同证件类型，证件号码人员!");
		}
	}

	public Integer countPersonHc(List<Long> unitOids, String dPositionType) throws ServiceException {
		return PbPersonInfoQueryHelper.countPersonHc(unitOids, dPositionType);
	}

	public Integer countPersonLeader(List<Long> unitOids,String positionLevelCode, String isLeader) throws ServiceException {
		return PbPersonInfoQueryHelper.countPersonLeader(unitOids, positionLevelCode, isLeader);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PbPersonInfoService#getGroupByDPositionTypeAndBankpoll(java.lang.Long)
	 */
	public List<PbPersonInfoDTO> getGroupByDPositionTypeAndBankpoll(Long unitOid)
			throws ServiceException {
		
		return PbPersonInfoQueryHelper.getGroupByDPositionTypeAndBankpoll(unitOid);
	}
	/*
	 * (non-Javadoc)
	 * @see PbPersonInfoService#getPersonByUnitOid(java.lang.Long)
	 */
	/*public List<PbPersonAttachDTO> getPersonByUnitOid(Long unitOid)
			throws ServiceException {
		return PbPersonInfoQueryHelper.getPersonByUnitOid(unitOid);
	}*/
	
	/**
	 * 根据人员名称查询人员信息(在职,见习长期休假)
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<PbPersonInfoDTO> findPersonInfoByName(String name) throws ServiceException {
		return PbPersonInfoQueryHelper.findPersonInfoByName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see PbPersonInfoService#findPersonByDeptOid(java.lang.Long)
	 */
	public List<PbPersonInfoDTO> findPersonByDeptOid(Long hireDeptOid)
			throws ServiceException {
		return PbPersonInfoQueryHelper.findPersonByDeptOid(hireDeptOid);
	}

	public List<PbPersonInfoDTO> findPersonInfoByNameAndCode(String name, String personCode) throws ServiceException {
		return PbPersonInfoQueryHelper.findPersonInfoByNameAndCode(name, personCode);
	}

	public Long createPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException {
		PbPersonInfo pbPersonInfo =BeanHelper.copyProperties(pbPersonInfoDTO,PbPersonInfo.class);
		pbPersonInfo.setCreateBy(UserContext.getLoginUserID());
		pbPersonInfo.setCreateName(UserContext.getLoginUserName());
		pbPersonInfo.setCreateDate(DateUtil.now());
		pbPersonInfo.save();
		//同步信息到人员附属表
		PersonAttachInfoServiceUtil.sysPbPersonInfo(pbPersonInfo.getPersonOid());
		return pbPersonInfo.getPersonOid();
	}

	public void modifyPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException {
		PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class, pbPersonInfoDTO.getPersonOid());
		BeanHelper.copyProperties(pbPersonInfoDTO,pbPersonInfo);
		pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
		pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
		pbPersonInfo.setUpdateDate(DateUtil.now());
		pbPersonInfo.update();
		//同步信息到人员附属表
		PersonAttachInfoServiceUtil.sysPbPersonInfo(pbPersonInfo.getPersonOid());
	}
	
	
	public void checkUniquePbPerson(PbPersonInfoDTO pbPersonInfoDTO)
			throws ServiceException {
		List<PbPersonInfoDTO> list=PbPersonInfoQueryHelper.checkUniquePbPerson(pbPersonInfoDTO.getIdCode(),pbPersonInfoDTO.getIdNo(),pbPersonInfoDTO.getPersonOid());
		if(CollectionUtils.isNotEmpty(list)){
			throw new ServiceException (null,"该证件类型与证件号码人员已经存在");
		}
	}
	public void checkPersonCode(PbPersonInfoDTO pbPersonInfoDTO)throws ServiceException {

		List<PbPersonInfoDTO> listDto=PbPersonInfoQueryHelper.checkUniquePbPersonCode(pbPersonInfoDTO.getPersonCode(),pbPersonInfoDTO.getPersonOid());
		if(CollectionUtils.isNotEmpty(listDto)){
			throw new ServiceException (null,"该工号已经存在");
		}
	}

	public List<JSONObject> getBusinessCount(Long personOid) throws ServiceException {
		return PbPersonInfoQueryHelper.getBusinessCount(personOid);
	}
	
	/**
	 * 校核人员入库（人员导入功能）
	 * @param pbPersonInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long transferImportPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException {
		//工号唯一性检查
//		checkPersonCode(pbPersonInfoDTO);
		//通过姓名和出生日期获取人员基础信息
		PbPersonInfoDTO personInfoDTO = PbPersonInfoQueryHelper.getPbPersonInfoDTOByNameAndBirthday(pbPersonInfoDTO.getName(), pbPersonInfoDTO.getBirthday());
		if(personInfoDTO!=null) {
			deletePbPersonInfoById(personInfoDTO.getPersonOid());
		}
		Long orgOid = pbPersonInfoDTO.getHireDeptOid();
		if(orgOid!=null) {
			UtUnitDTO unitDto = UtUnitQueryHelper.getUnitInfoByOrgOid(orgOid);
			if(unitDto!=null) {
				pbPersonInfoDTO.setUnitOid(unitDto.getUnitOid());
			}
		}
		PbPersonInfo pbPersonInfo = new PbPersonInfo();
		BeanHelper.copyProperties(pbPersonInfoDTO, pbPersonInfo);
		pbPersonInfo.setCreateBy(UserContext.getLoginUserID());
		pbPersonInfo.setCreateName(UserContext.getLoginUserName());
		pbPersonInfo.setCreateDate(DateUtil.now());
		pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
		pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
		pbPersonInfo.setUpdateDate(DateUtil.now());
		pbPersonInfo.save();
		//同步信息到人员附属表
		PersonAttachInfoServiceUtil.sysPbPersonInfo(pbPersonInfo.getPersonOid());
		
		return pbPersonInfo.getPersonOid();
	}
}

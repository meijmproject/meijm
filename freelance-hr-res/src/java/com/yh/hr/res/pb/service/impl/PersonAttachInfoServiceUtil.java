package com.yh.hr.res.pb.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.dto.PbPoliticInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbDeathInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbQualificationsInfoQueryHelper;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbDeathInfo;
import com.yh.hr.res.pb.bo.PbEducationLevelDegree;
import com.yh.hr.res.pb.bo.PbPersonAttach;
import com.yh.hr.res.pb.bo.PbPersonIn;
import com.yh.hr.res.pb.bo.PbPersonOut;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.hr.res.pb.dto.PbProfTechQualifInfoDTO;
import com.yh.hr.res.pb.dto.PbQualificationsInfoDTO;
import com.yh.hr.res.pb.dto.PbRetrieInfoDTO;
import com.yh.hr.res.pb.dto.PbSyGwEmployInfoDTO;
import com.yh.hr.res.pb.dto.PbYnGwEmployInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbDutyInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbEducationLevelDegreeQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbPersonAttachQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbPersonInQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbPersonInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbPersonOutQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbPoliticInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbProfTechQualifInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbRetrieInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbSyGwEmployInfoQueryHelper;
import com.yh.hr.res.pb.queryhelper.PbYnGwEmployInfoQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class PersonAttachInfoServiceUtil 
{
	/**
	 * 同步人员基本信息（护士层级字段）
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbPersonInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空护士层级信息
			setPbPersonPropertyIsNull(pbPersonAttach);
		}
		//2.查询人员基本信息，判断护士层级字段是否为空，若不为空则更新该字段到人员附属表
		PbPersonInfoDTO pbPersonInfoDTO = PbPersonInfoQueryHelper.getPbPersonInfoDTOById(personOid);
		if(null != pbPersonInfoDTO)
		{
			pbPersonAttach.setLevelCode(pbPersonInfoDTO.getLevelCode());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（人员基本信息）
	 * @param pbPersonAttach
	 * @throws ServiceException 
	 */
	private static void setPbPersonPropertyIsNull(PbPersonAttach pbPersonAttach) throws ServiceException {
		pbPersonAttach.setLevelCode(null);
		pbPersonAttach.update();
	}
	/**
	 * 同步最高专业技术资格信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbProfTechQualifInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空附属表中专业技术资格信息
			setPbProfTechQualifPropertyIsNull(pbPersonAttach);
		}
		//2.查询最高的专业技术资格信息，若不为空则更新该字段到人员附属表
		PbProfTechQualifInfoDTO pbProfTechQualifInfoDTO = PbProfTechQualifInfoQueryHelper.getPbProfTechQualifInfoByCond(personOid,DicConstants.YHRS0003_1);
		if(null != pbProfTechQualifInfoDTO)
		{
			pbPersonAttach.setProfCertificateNo(pbProfTechQualifInfoDTO.getCertificateNo());
			pbPersonAttach.setProfTechName(pbProfTechQualifInfoDTO.getProfTechName());
			pbPersonAttach.setProfTechLevel(pbProfTechQualifInfoDTO.getProfTechLevel());
			pbPersonAttach.setSpecialityName(pbProfTechQualifInfoDTO.getSpecialityName());
			pbPersonAttach.setProfProcureDate(pbProfTechQualifInfoDTO.getProcureDate());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（专业技术资格信息）
	 * @param pbPersonAttach
	 */
	private static void setPbProfTechQualifPropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setProfCertificateNo(null);
		pbPersonAttach.setProfTechName(null);
		pbPersonAttach.setProfTechLevel(null);
		pbPersonAttach.setSpecialityName(null);
		pbPersonAttach.setProfProcureDate(null);
		pbPersonAttach.update();
	}
	/**
	 * 同步事业岗位信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbSyGwEmployInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空附属表中专业技术资格信息
			setPbSyGwEmployPropertyIsNull(pbPersonAttach);
		}
		//2.查询在聘的事业岗位信息，若不为空则更新事业岗位信息到人员附属表
		List<PbSyGwEmployInfoDTO> pbSyGwEmployInfoDTOList = PbSyGwEmployInfoQueryHelper.findSyGwEmployInfoByCond(personOid,DicConstants.YHRS0026_001);
		if(CollectionUtils.isNotEmpty(pbSyGwEmployInfoDTOList))
		{
			if(pbSyGwEmployInfoDTOList.size()>1)
			{
				pbPersonAttach.setIsTwoDuty(DicConstants.YHRS0003_1);
			}
			else
			{
				pbPersonAttach.setIsTwoDuty(DicConstants.YHRS0003_0);
			}
			for(PbSyGwEmployInfoDTO dto:pbSyGwEmployInfoDTOList)
			{
				if(DicConstants.YHRS0003_1.equals(dto.getIsMPosition()))
				{
					pbPersonAttach.setmPositionName(dto.getPositionName());
					pbPersonAttach.setmPositionType(dto.getPositionType());
					pbPersonAttach.setmPositionLevel(dto.getPositionLevel());
					pbPersonAttach.setmPositionLevelDate(dto.getBeginDate());
					pbPersonAttach.setPositionDate(dto.getBeginDate());
				}
				else
				{
					pbPersonAttach.setsPositionName(dto.getPositionName());
					pbPersonAttach.setsPositionType(dto.getPositionType());
					pbPersonAttach.setsPositionLevel(dto.getPositionLevel());
					pbPersonAttach.setsPositionLevelDate(dto.getBeginDate());
					pbPersonAttach.setPositionDate(dto.getBeginDate());
				}
			}
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（事业岗位信息）
	 * @param pbPersonAttach
	 */
	private static void setPbSyGwEmployPropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setIsTwoDuty(null);
		pbPersonAttach.setmPositionName(null);
		pbPersonAttach.setmPositionType(null);
		pbPersonAttach.setmPositionLevel(null);
		pbPersonAttach.setmPositionLevelDate(null);
		pbPersonAttach.setsPositionName(null);
		pbPersonAttach.setsPositionType(null);
		pbPersonAttach.setsPositionLevel(null);
		pbPersonAttach.setsPositionLevelDate(null);
		pbPersonAttach.update();
	}
	
	/**
	 * 同步学历学位信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbEducationLevelDegreeInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空学历学位信息
			setPbEducationLevelDegreePropertyIsNull(pbPersonAttach);
		}
		List<PbEducationLevelDegreeDTO> pbInfoDTOs  =  PbEducationLevelDegreeQueryHelper.listPbEducationLevelDegreeByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(pbInfoDTOs)){
		// 设置最高学历
		//setTopEduLevelCode(pbInfoDTOs);
		// 设置最高学位
		//setTopDegreeCode(pbInfoDTOs);
		List<PbEducationLevelDegreeDTO>  ftpInfoDTOs = getFtOrOjPbEducationInfo(pbInfoDTOs, true);
		if(CollectionUtils.isNotEmpty(ftpInfoDTOs)){
			//设置全日制最高学历
			setFtTopEduLevelCode(pbPersonAttach,ftpInfoDTOs);
			//设置全日制最高学位
			setFtTopDegreeCode(pbPersonAttach,ftpInfoDTOs);
		}
		List<PbEducationLevelDegreeDTO> ojpInfoDTOs = getFtOrOjPbEducationInfo(pbInfoDTOs, false);
		if(CollectionUtils.isNotEmpty(ojpInfoDTOs)){
			//设置在职最高学历
			setOjTopEduLevelCode(pbPersonAttach,ojpInfoDTOs);
			//设置在职最高学位
			setOjTopDegreeCode(pbPersonAttach,ojpInfoDTOs);
		}
			//设置最高学历、学位
		    if(DicConstants.YHRS0003_1.equals(pbInfoDTOs.get(0).getIsHighestEducationLevel())){
		    	pbPersonAttach.setEducationLevelCode(pbInfoDTOs.get(0).getEducationCode());
		    } 
		    if(DicConstants.YHRS0003_1.equals(pbInfoDTOs.get(0).getIsHighestDegree())){
		    	pbPersonAttach.setDegreeCode(pbInfoDTOs.get(0).getDegreeCode());
		    }
			
			pbPersonAttach.update();
		}
	}
	// 根据人员所有学历信息集过滤出全日制或在职学历集,true表示取全日制，否则表示在职
	private static List<PbEducationLevelDegreeDTO> getFtOrOjPbEducationInfo(List<PbEducationLevelDegreeDTO> pbInfoDTOs,boolean isOjEdu) throws ServiceException {
		List<PbEducationLevelDegreeDTO> newInfoDTOs = new ArrayList<PbEducationLevelDegreeDTO>();
		for (PbEducationLevelDegreeDTO pbEducationInfoDTO : pbInfoDTOs) {
			if(isOjEdu){
				if(DicConstants.YHRS0043_1.equals(pbEducationInfoDTO.getEduType())){
					newInfoDTOs.add(pbEducationInfoDTO);
				}
			}else{
				if(DicConstants.YHRS0043_2.equals(pbEducationInfoDTO.getEduType())){
					newInfoDTOs.add(pbEducationInfoDTO);
				}
			}
		}
		return newInfoDTOs;
	}
	// 设置全日制最高学历
	private static void setFtTopEduLevelCode(PbPersonAttach pbPersonAttach,List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByLevelCode(pbInfoDTOs);
		/*PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(0);
		if(pbEducationInfoDTO != null){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbPersonAttach.setFtSchoolName(pbEducationInfoDTO.getSchoolName());
			pbPersonAttach.setFtMajorName(pbEducationInfoDTO.getMajorCode());
			pbPersonAttach.setFtEducationLevelCode(pbEducationInfoDTO.getEducationCode());
		}*/
		for(int i=0; i<pbInfoDTOs.size(); i++) {
			PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(i);
			if(pbEducationInfoDTO!=null) {
				if(i==0) {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_1);
					pbEducationInfo.update();
					pbPersonAttach.setFtSchoolName(pbEducationInfoDTO.getSchoolName());
					pbPersonAttach.setFtMajorName(pbEducationInfoDTO.getMajorCode());
					pbPersonAttach.setFtEducationLevelCode(pbEducationInfoDTO.getEducationCode());
				}else {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_0);
					pbEducationInfo.update();
				}
			}
		}
	}
	
	//设置全日制最高学位
	private static void setFtTopDegreeCode(PbPersonAttach pbPersonAttach,List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByDegreeCode(pbInfoDTOs);
		/*PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(0);
		if(pbEducationInfoDTO != null){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbPersonAttach.setFtDegreeCode(pbEducationInfoDTO.getDegreeCode());
		}*/
		
		for(int i=0; i<pbInfoDTOs.size(); i++) {
			PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(i);
			if(pbEducationInfoDTO!=null) {
				if(i==0) {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_1);
					pbEducationInfo.update();
					pbPersonAttach.setFtDegreeCode(pbEducationInfoDTO.getDegreeCode());
				}else {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_0);
					pbEducationInfo.update();
				}
			}
		}
	}
	// 设置在职最高学历
	private static void setOjTopEduLevelCode(PbPersonAttach pbPersonAttach,List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByLevelCode(pbInfoDTOs);
		/*PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(0);
		if(pbEducationInfoDTO != null){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbPersonAttach.setOjSchoolName(pbEducationInfoDTO.getSchoolName());
			pbPersonAttach.setOjMajorName(pbEducationInfoDTO.getMajorCode());
			pbPersonAttach.setOjEducationLevelCode(pbEducationInfoDTO.getEducationCode());
		}*/
		for(int i=0; i<pbInfoDTOs.size(); i++) {
			PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(i);
			if(pbEducationInfoDTO!=null) {
				if(i==0) {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_1);
					pbEducationInfo.update();
					pbPersonAttach.setOjSchoolName(pbEducationInfoDTO.getSchoolName());
					pbPersonAttach.setOjMajorName(pbEducationInfoDTO.getMajorCode());
					pbPersonAttach.setOjEducationLevelCode(pbEducationInfoDTO.getEducationCode());
				}else {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_0);
					pbEducationInfo.update();
				}
			}
		}
	}
	
	// 设置在职最高学位
	private static void setOjTopDegreeCode(PbPersonAttach pbPersonAttach,List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByDegreeCode(pbInfoDTOs);
		/*PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(0);
		if(pbEducationInfoDTO != null){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbPersonAttach.setOjDegreeCode(pbEducationInfoDTO.getDegreeCode());
		}*/
		for(int i=0; i<pbInfoDTOs.size(); i++) {
			PbEducationLevelDegreeDTO pbEducationInfoDTO = pbInfoDTOs.get(i);
			if(pbEducationInfoDTO!=null) {
				if(i==0) {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_1);
					pbEducationInfo.update();
					pbPersonAttach.setOjDegreeCode(pbEducationInfoDTO.getDegreeCode());
				}else {
					PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pbEducationInfoDTO.getEducationLevelOid());
					pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_0);
					pbEducationInfo.update();
				}
			}
		}
	}
	// 通过学历排序
	private static void sortPbEducationInfoByLevelCode(List<PbEducationLevelDegreeDTO> pbInfoDTOs) {
		Collections.sort(pbInfoDTOs, new Comparator<PbEducationLevelDegreeDTO>() {
			public int compare(	PbEducationLevelDegreeDTO pDto ,
					PbEducationLevelDegreeDTO _pDto) {
				String eduLevel1 = pDto.getEducationCode();
				String eduLevel2 = _pDto.getEducationCode();
				if(eduLevel1 == null && eduLevel2 == null) return 0;
				if(eduLevel1 == null)return 1;
				if(eduLevel2 == null) return -1;
				Integer i1 = eduLevelMap.get(eduLevel1);
				Integer i2 = eduLevelMap.get(eduLevel2);
				if(i1 == null && i2 == null) return 0;
				if(i1 == null) return 1;
				if(i2 == null) return -1;
				int ii = i1-i2;
				if(ii > 0)return 1;
				if(ii < 0)return -1;
				return ii;
			}
		});
	};
	
	
	// 通过学位排序
	private static void sortPbEducationInfoByDegreeCode(List<PbEducationLevelDegreeDTO> pbInfoDTOs) {
		Collections.sort(pbInfoDTOs, new Comparator<PbEducationLevelDegreeDTO>() {
			public int compare(	PbEducationLevelDegreeDTO pDto ,
					PbEducationLevelDegreeDTO _pDto) {
				String degree1 = pDto.getDegreeCode();
				String degree2 = _pDto.getDegreeCode();
				if(degree1 == null && degree2 == null) return 0;
				if(degree1 == null)return 1;
				if(degree2 == null) return -1;
				Integer i1 = degreeLevelMap.get(degree1);
				Integer i2 = degreeLevelMap.get(degree2);
				if(i1 == null && i2 == null) return 0;
				if(i1 == null) return 1;
				if(i2 == null) return -1;
				int ii = i1-i2;
				if(ii > 0)return 1;
				if(ii < 0)return -1;
				return ii;
			}
		});
	};
	
	
	
	/**   学历编码排序   */
	private static HashMap<String, Integer>	 eduLevelMap			= new HashMap<String, Integer>();
	static {
		eduLevelMap.put("10", 1);
		eduLevelMap.put("11", 2);
		eduLevelMap.put("12", 3);
		eduLevelMap.put("1", 4);
		eduLevelMap.put("2", 5);
		eduLevelMap.put("28", 6);
		eduLevelMap.put("3", 7);
		eduLevelMap.put("4", 8);
		eduLevelMap.put("5", 9);
		eduLevelMap.put("6", 10);
		eduLevelMap.put("7", 11);
		eduLevelMap.put("8", 12);
		eduLevelMap.put("9", 13);
		eduLevelMap.put("90", 14);

	}
	
	
	/**   学位编码排序   */
	private static HashMap<String, Integer>	degreeLevelMap			= new HashMap<String, Integer>();
	static {
		degreeLevelMap.put("5", 1);
		degreeLevelMap.put("4", 2);
		degreeLevelMap.put("3", 3);
		degreeLevelMap.put("2", 4);
		degreeLevelMap.put("1", 5);
	}
	// 设置最高学历
	private static void setTopEduLevelCode(List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByLevelCode(pbInfoDTOs);
		PbEducationLevelDegreeDTO pEducationInfoDTO = pbInfoDTOs.get(0);
		if(pEducationInfoDTO != null ){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestEducationLevel(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbInfoDTOs.get(0).setIsHighestEducationLevel(DicConstants.YHRS0003_1);
			cleanOtherHighFlag(pbEducationInfo.getPersonOid(),pbEducationInfo.getEducationLevelOid(), true);
		}
	}

	// 设置最高学位
	private static void setTopDegreeCode(List<PbEducationLevelDegreeDTO> pbInfoDTOs) throws ServiceException {
		sortPbEducationInfoByDegreeCode(pbInfoDTOs);
		PbEducationLevelDegreeDTO pEducationInfoDTO = pbInfoDTOs.get(0);
		if(pEducationInfoDTO != null ){
			PbEducationLevelDegree pbEducationInfo = DaoUtil.get(PbEducationLevelDegree.class, pEducationInfoDTO.getEducationLevelOid());
			pbEducationInfo.setIsHighestDegree(DicConstants.YHRS0003_1);
			pbEducationInfo.update();
			pbInfoDTOs.get(0).setIsHighestDegree(DicConstants.YHRS0003_1);
			cleanOtherHighFlag(pbEducationInfo.getPersonOid(),pbEducationInfo.getEducationLevelOid(), false);
		}
	}
	//当找到最高学历学位时，清空其它学历信息集中的最高标识 
	private static void cleanOtherHighFlag(Long personOid,Long educationOid,boolean flag) throws ServiceException {
		PbEducationLevelDegreeQueryHelper.updateIsHighFlag(personOid,educationOid, flag);
	}
	
	/**
	 * 将属性值置空（学历学位信息）
	 * @param pbPersonAttach
	 */
	private static void setPbEducationLevelDegreePropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setFtSchoolName(null);
		pbPersonAttach.setFtMajorName(null);
		pbPersonAttach.setFtEducationLevelCode(null);
		pbPersonAttach.setFtDegreeCode(null);
		pbPersonAttach.setOjSchoolName(null);
		pbPersonAttach.setOjMajorName(null);
		pbPersonAttach.setOjDegreeCode(null);
		pbPersonAttach.setOjEducationLevelCode(null);
		pbPersonAttach.setEducationLevelCode(null);
		pbPersonAttach.setDegreeCode(null);
		pbPersonAttach.update();
	}
	/**
	 * 同步人员执业资格
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbQualificationsInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空执业资格信息
			setPbQualificationsPropertyIsNull(pbPersonAttach);
		}
		//2.查询人员基本信息，判断执业资格信息是否为空，若不为空则更新该字段到人员附属表
		PbQualificationsInfoDTO pbQualificationsInfoDTO = PbQualificationsInfoQueryHelper.getQualificationsInfoByCond(personOid,DicConstants.YHRS0003_1);
		if(null != pbQualificationsInfoDTO)
		{
			pbPersonAttach.setCertificateNo(pbQualificationsInfoDTO.getCertificateNo());
			pbPersonAttach.setQualificationsName(pbQualificationsInfoDTO.getQualificationsName());
			pbPersonAttach.setQualificationsLevelCode(pbQualificationsInfoDTO.getQualificationsLevelCode());
			pbPersonAttach.setProcureDate(pbQualificationsInfoDTO.getProcureDate());
			pbPersonAttach.setQualificationsType(pbQualificationsInfoDTO.getQualificationsType());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（执业资格信息）
	 * @param pbPersonAttach
	 */
	private static void setPbQualificationsPropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setCertificateNo(null);
		pbPersonAttach.setQualificationsName(null);
		pbPersonAttach.setQualificationsLevelCode(null);
		pbPersonAttach.setSkillWorkCode(null);
		pbPersonAttach.setProcureDate(null);
		pbPersonAttach.setQualificationsType(null);
		pbPersonAttach.update();
	}
	/**
	 * 同步政治面貌基本信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbPoliticInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空政治面貌
			setPbPoliticPropertyIsNull(pbPersonAttach);
		}
		//2.查询人员基本信息，判断政治面貌信息是否为空，若不为空则更新该字段到人员附属表
		PbPoliticInfoDTO pbPoliticInfoDTO = PbPoliticInfoQueryHelper.getPbPoliticInfoInfoDTOById(personOid);
		if(null != pbPoliticInfoDTO)
		{
			pbPersonAttach.setPoliticStatusCode(pbPoliticInfoDTO.getPoliticStatusCode());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空(政治面貌信息)
	 * @param pbPersonAttach
	 */
	private static void setPbPoliticPropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setPoliticStatusCode(null);
		pbPersonAttach.update();
	}
	/**
	 * 同步事业岗位信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbYnGwEmployInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空附属表中院内岗位信息信息
			setPbYnGwEmployPropertyIsNull(pbPersonAttach);
		}
		//2.查询院内岗位信息，若不为空则更新院内岗位信息到人员附属表
	   PbYnGwEmployInfoDTO pbYnGwEmployInfoDTO = PbYnGwEmployInfoQueryHelper.findYnGwEmployInfoByCond(personOid,DicConstants.YHRS0026_001);
		if(pbYnGwEmployInfoDTO!=null)
		{
			pbPersonAttach.setHisPositionStatus(pbYnGwEmployInfoDTO.getHisPositionStatus());
			pbPersonAttach.setHisPositionType(pbYnGwEmployInfoDTO.getHisPositionType());
			pbPersonAttach.setHisPositionLevel(pbYnGwEmployInfoDTO.getHisPositionLevel());
			pbPersonAttach.setHisPositionName(pbYnGwEmployInfoDTO.getHisPositionName());
			pbPersonAttach.setHisBeginDate(pbYnGwEmployInfoDTO.getHisBeginDate());
			pbPersonAttach.setHisDutyAttribute(pbYnGwEmployInfoDTO.getHisDutyAttribute());
			pbPersonAttach.setAppointProfTitleCode(pbYnGwEmployInfoDTO.getAppointProfTitleCode());
			pbPersonAttach.update();
		}
			
	}
	/**
	 * 将属性值置空（院内岗位信息）
	 * @param pbPersonAttach
	 */
	private static void setPbYnGwEmployPropertyIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setHisPositionStatus(null);
		pbPersonAttach.setHisPositionType(null);
		pbPersonAttach.setHisPositionLevel(null);
		pbPersonAttach.setHisPositionName(null);
		pbPersonAttach.setHisBeginDate(null);
		pbPersonAttach.setHisDutyAttribute(null);
		pbPersonAttach.setAppointProfTitleCode(null);
		pbPersonAttach.update();
	}
	
	/**
	 * 同步主要院内中层职务任职信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbDutyInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的院内中层职务任职信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空附属表中院内中层职务任职信息
			setPbDutyInfoIsNull(pbPersonAttach);
		}
		//2.查询主要院内中层职务任职信息，若不为空则更新该字段到人员附属表
		PbDutyInfoDTO pbPbDutyInfoDTO = PbDutyInfoQueryHelper.getPbProfTechQualifInfoByCond(personOid,DicConstants.YHRS0003_1);
		if(null != pbPbDutyInfoDTO)
		{
			pbPersonAttach.setDutyName(pbPbDutyInfoDTO.getDutyName());
			pbPersonAttach.setStartDate(pbPbDutyInfoDTO.getStartDate());
			pbPersonAttach.setDutyDeptOid(pbPbDutyInfoDTO.getDeptOid());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（院内中层职务任职信息）
	 * @param pbPersonAttach
	 */
	private static void setPbDutyInfoIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setDutyName(null);	//现任职务
		pbPersonAttach.setStartDate(null);	//现任职务
		pbPersonAttach.setDutyDeptOid(null);	//现任职务所在部门
		pbPersonAttach.update();
	}
	
	/**
	 * 同步人员进入信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbInInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空执业资格信息
			setPbInInfoIsNull(pbPersonAttach);
		}
		//2.查询人员进入信息，判断是否为空，若不为空则更新该字段到人员附属表
		List<PbPersonIn> list = PbPersonInQueryHelper.findByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			PbPersonIn bo = list.get(0);
			pbPersonAttach.setEntryCurrentUnitDate(bo.getEntryCurrentUnitDate());
			pbPersonAttach.setEntryCurrentUnitType(bo.getEntryCurrentUnitType());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（进入信息）
	 * @param pbPersonAttach
	 */
	private static void setPbInInfoIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setEntryCurrentUnitDate(null);
		pbPersonAttach.setEntryCurrentUnitType(null);
		pbPersonAttach.update();
	}
	
	/**
	 * 同步人员进入信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbOutInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空执业资格信息
			setPbOutInfoIsNull(pbPersonAttach);
		}
		//2.查询人员离开信息，判断是否为空，若不为空则更新该字段到人员附属表
		List<PbPersonOut> list = PbPersonOutQueryHelper.findByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			PbPersonOut bo = list.get(0);
			pbPersonAttach.setOutDate(bo.getOutDate());
			pbPersonAttach.setPersonOutType(bo.getPersonOutType());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（离开信息）
	 * @param pbPersonAttach
	 */
	private static void setPbOutInfoIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setOutDate(null);
		pbPersonAttach.setPersonOutType(null);
		pbPersonAttach.update();
	}
	
	/**
	 * 同步人员退休信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbRetireInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空退休信息
			setPbRetireInfoIsNull(pbPersonAttach);
		}
		//2.查询人员退休信息，判断是否为空，若不为空则更新该字段到人员附属表
		List<PbRetrieInfoDTO> list = PbRetrieInfoQueryHelper.findByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			PbRetrieInfoDTO bo = list.get(0);
			pbPersonAttach.setRetrieDate(bo.getRetrieDate());
			pbPersonAttach.setRetrieTypeCode(bo.getRetrieTypeCode());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（退休信息）
	 * @param pbPersonAttach
	 */
	private static void setPbRetireInfoIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setRetrieDate(null);
		pbPersonAttach.setRetrieTypeCode(null);
		pbPersonAttach.update();
	}
	
	/**
	 * 同步人员死亡信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void sysPbDeathInfo(Long personOid) throws ServiceException
	{
		//1、先查询人员附属信息  先判断是否存在记录，如果存在，先清空附属表中的人员基本信息信息；如果不存在，则创建人员附属信息
		 PbPersonAttach pbPersonAttach = PbPersonAttachQueryHelper.findAttachInfoByPersonOid(personOid);
		if(null == pbPersonAttach)
		{
			//创建人员附属表
			pbPersonAttach = new PbPersonAttach();
			pbPersonAttach.setPersonOid(personOid);
			pbPersonAttach.save();
		}else{
			//清空死亡信息
			setPbDeathInfoIsNull(pbPersonAttach);
		}
		//2.查询人员死亡信息，判断是否为空，若不为空则更新该字段到人员附属表
		List<PbDeathInfo> list = PbDeathInfoQueryHelper.findByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			PbDeathInfo bo = list.get(0);
			pbPersonAttach.setDeathDate(bo.getDeathDate());
			pbPersonAttach.setDeathType(bo.getDeathType());
			pbPersonAttach.update();
		}
	}
	/**
	 * 将属性值置空（执业资格信息）
	 * @param pbPersonAttach
	 */
	private static void setPbDeathInfoIsNull(
			PbPersonAttach pbPersonAttach) throws ServiceException{
		pbPersonAttach.setDeathDate(null);
		pbPersonAttach.setDeathType(null);
		pbPersonAttach.update();
	}
}
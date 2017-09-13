package com.yh.hr.report.service.impl;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.report.service.OfficeCommonService;
import com.yh.hr.report.utils.CommonPrintUtil;
import com.yh.hr.report.utils.Constants;
import com.yh.hr.report.utils.ReportConfigUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.service.BaseReportService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbCertificateInfoDTO;
import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.hr.res.pb.dto.PbImageDTO;
import com.yh.hr.res.pb.dto.PbPersonAttachDTO;
import com.yh.hr.res.pb.dto.PbPersonInDTO;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.dto.PbPunishmentInfoDTO;
import com.yh.hr.res.pb.dto.PbRewardInfoDTO;
import com.yh.hr.res.pb.service.PbCertificateInfoService;
import com.yh.hr.res.pb.service.PbFamilyInfoService;
import com.yh.hr.res.pb.service.PbImageService;
import com.yh.hr.res.pb.service.PbPersonAttachService;
import com.yh.hr.res.pb.service.PbPersonInService;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.pb.service.PbPunishmentInfoService;
import com.yh.hr.res.pb.service.PbRewardInfoService;
import com.yh.hr.res.sao.orghc.dto.SaoUbOrgDTO;
import com.yh.hr.res.sao.orghc.service.SaoUbOrgService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.DateUtil;

public class BaseReportServiceImpl implements BaseReportService
{

	private PbPersonInfoService pbPersonInfoService;
	private PbPersonAttachService pbPersonAttachService;
	private PbImageService pbImageService;
	private OfficeCommonService officeCommonService;
	private PbRewardInfoService pbRewardInfoService;
	private PbPunishmentInfoService pbPunishmentInfoService;
	private PbFamilyInfoService pbFamilyInfoService;
	private UtUnitService utUnitService;
	
	private SaoUbOrgService saoUbOrgService;
	private PbCertificateInfoService pbCertificateInfoService;
	private PbPersonInService pbPersonInService;
	

	public void setPbPersonInService(PbPersonInService pbPersonInService) {
		this.pbPersonInService = pbPersonInService;
	}
	
	public void setPbCertificateInfoService(
			PbCertificateInfoService pbCertificateInfoService) {
		this.pbCertificateInfoService = pbCertificateInfoService;
	}

	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public void setPbPersonInfoService(PbPersonInfoService pbPersonInfoService) {
		this.pbPersonInfoService = pbPersonInfoService;
	}

	public void setPbPersonAttachService(PbPersonAttachService pbPersonAttachService) {
		this.pbPersonAttachService = pbPersonAttachService;
	}

	public void setPbImageService(PbImageService pbImageService) {
		this.pbImageService = pbImageService;
	}

	public void setOfficeCommonService(OfficeCommonService officeCommonService) {
		this.officeCommonService = officeCommonService;
	}

	public void setPbRewardInfoService(PbRewardInfoService pbRewardInfoService) {
		this.pbRewardInfoService = pbRewardInfoService;
	}

	public void setPbPunishmentInfoService(
			PbPunishmentInfoService pbPunishmentInfoService) {
		this.pbPunishmentInfoService = pbPunishmentInfoService;
	}

	public void setPbFamilyInfoService(PbFamilyInfoService pbFamilyInfoService) {
		this.pbFamilyInfoService = pbFamilyInfoService;
	}

	public void setSaoUbOrgService(SaoUbOrgService saoUbOrgService) {
		this.saoUbOrgService = saoUbOrgService;
	}

	public HashMap<String, Object> createPersonInfoReport(Long personOid, String dir,String createBy) throws ServiceException
	{
		PbPersonInfoDTO person = pbPersonInfoService.getPbPersonInfoDTOById(personOid);
		PbPersonAttachDTO personAttach = pbPersonAttachService.get(personOid);
		SaoUbOrgDTO org= null;
		if(null!=person.getHireDeptOid()){
			
			org = saoUbOrgService.getSaoUbOrgDTO(person.getHireDeptOid());
		}
		HashMap<String,Object> parameters = new HashMap<String,Object>(); 
		String orgName="";
		if(org!=null)
		{
                orgName = org.getOrgName();
		}
		parameters.put("personName", person.getName());
		parameters.put("orgName", orgName);
		parameters.put("personOid",String.valueOf(personOid));		
		parameters.put("createBy",createBy);
		if(null!=person.getUnitOid()){
			
			parameters.put("unitName", utUnitService.getUnitName(person.getUnitOid()));
		}
		
		parameters.put("unitHrMng", "医院管理中心");
		
		parameters.put("name", CommonPrintUtil.checkObjIsNull(person.getName(), null));// 姓名
		parameters.put("sexCode", CommonPrintUtil.checkObjIsNull(person.getSexCode(), DicConstants.YHRS0001));// 性别
		parameters.put("birthday", CommonPrintUtil.checkObjIsNull(person.getBirthday(), "yyyy.MM"));// 出生日期
		//parameters.put("p27", "(" + CommonPrintUtil.checkObjIsNull(CommonPrintUtil.caculateAge(person.getBirthday(), new Date()), null) + ")");
		parameters.put("idNo", CommonPrintUtil.checkObjIsNull(person.getIdNo(), null));//身份证号
		parameters.put("peopleCode", CommonPrintUtil.checkObjIsNull(person.getPeopleCode(), DicConstants.YHRS0004));// 民族
		parameters.put("ancestorPlaceCode", CommonPrintUtil.checkObjIsNull(person.getAncestorPlaceCode(), DicConstants.YHRS0006));// 籍贯
		parameters.put("birthplaceCode", CommonPrintUtil.checkObjIsNull(person.getBirthplaceCode(), DicConstants.YHRS0006));// 出生地
		parameters.put("startWorkDate", CommonPrintUtil.checkObjIsNull(person.getStartWorkDate(), "yyyy.MM"));// 参加工作时间
		
		List<PbPersonInDTO> pbPersonInDTOList = pbPersonInService.listPbPersonInByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(pbPersonInDTOList)){
			parameters.put("entryCurrentUnitDate", CommonPrintUtil.checkObjIsNull(pbPersonInDTOList.get(0).getEntryCurrentUnitDate(), "yyyy.MM"));// 进入本单位时间
		}
		
		parameters.put("marriageStatusCode", CommonPrintUtil.checkObjIsNull(person.getMarriageStatusCode(), DicConstants.YHRS0008));// 婚姻状况
		//职业资格信息
		parameters.put("certificateNo", CommonPrintUtil.checkObjIsNull(personAttach.getCertificateNo()	, null));//编号
		if(StringUtils.isNotEmpty(personAttach.getCertificateNo())){
			PbCertificateInfoDTO certificateInfoDto = pbCertificateInfoService.getCertificateInfoByCertificateNo(person.getPersonOid(),personAttach.getCertificateNo().trim());
			parameters.put("certificatePlace", certificateInfoDto==null?"":certificateInfoDto.getCertificateUnit());//执业地点
		}else{
			parameters.put("certificatePlace","/");
		}
		parameters.put("qualificationsName", CommonPrintUtil.checkObjIsNull(personAttach.getQualificationsName(), null));//名称
		//专业技术资格
		parameters.put("profCertificateNo", CommonPrintUtil.checkObjIsNull(personAttach.getProfCertificateNo(), null));//编号
		parameters.put("profTechName", CommonPrintUtil.checkObjIsNull(personAttach.getProfTechName(), null));//名称
		parameters.put("profTechLevel", CommonPrintUtil.checkObjIsNull(personAttach.getProfTechLevel(), DicConstants.YHRS0047));//等级
		//聘任岗位信息
		//parameters.put("hisPositionName", "/");
		parameters.put("hisPositionName", CommonPrintUtil.checkObjIsNull(personAttach.getHisPositionName(), null));//在聘岗位
		//信息集中取
		//PbDutyInfoDTO pbDutyInfoDto = pbDutyInfoService.getPbDutyInfoByPersonOid(person.getPersonOid());
		//parameters.put("duty", CommonPrintUtil.checkObjIsNull((pbDutyInfoDto==null?"":pbDutyInfoDto.getDutyName()), DicConstants.YHRS0126));//现任职务
		//附属表中取
		parameters.put("duty",CommonPrintUtil.checkObjIsNull(personAttach.getDutyName(),DicConstants.YHRS0126));//现任职务
		parameters.put("address", CommonPrintUtil.checkObjIsNull(person.getAddress(), null));//家庭住址
		parameters.put("mobilePhone", CommonPrintUtil.checkObjIsNull(person.getMobilePhone(), null));//联系电话
		parameters.put("printDate",DateUtil.format(new Date(), DateUtil.DATE_PATTERN_DEFAULT));
		//政治面貌信息
		parameters.put("p2", ReportConfigUtil.getProperty(Constants.PERSON_SUB_POLITICAL_LANDSCAPE));
		//学历学位信息
		parameters.put("p3",ReportConfigUtil.getProperty(Constants.PERSON_SUB_EDUCATION_URL));
		//专业技术资格信息
		parameters.put("p4",ReportConfigUtil.getProperty(Constants.PERSON_SUB_PROF_TECH_TITLE_URL));
		//职业资格信息
		parameters.put("p5",ReportConfigUtil.getProperty(Constants.PERSON_SUB_QUALIFICATIONS_URL));
		//执业注册信息
		parameters.put("p6",ReportConfigUtil.getProperty(Constants.PERSON_SUB_CERTIFICATE_URL));
		//特殊授权信息
		parameters.put("p7",ReportConfigUtil.getProperty(Constants.PERSON_SUB_SPECIA_AUTH_URL));

		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String warteMark = df.format(new Date());
		//parameters.put("WARER_MARK", warteMark);
		//parameters.put("SYSTEM_NAME", ReportConfigUtil.getProperty("water.mark.system.name"));
		return parameters;
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.BaseReportService#createOfficerInfoReport(java.lang.Long, java.lang.String)
	 */
	public HashMap<String, Object> createOfficerInfoReport(Long personOid, String dir) throws ServiceException
	{
		HashMap<String, Object> params = new HashMap<String, Object>();

		PbPersonInfoDTO person = pbPersonInfoService.getPbPersonInfoDTOById(personOid);
		if (null == person)
		{
			throw new ServiceException("error.office.print.havenopersons", "异常，根据personOid找不到记录");
		}
		PbPersonAttachDTO personAttach = pbPersonAttachService.get(personOid);
		PbImageDTO image = this.pbImageService.getPbImage(personOid);//获得照片
		if (image != null && image.getPhotoPath() != null && image.getPhotoPath().length > 100)
		{
			InputStream photoStream = new ByteArrayInputStream(image.getPhotoPath());
			params.put("photoStream", photoStream);
		}
		else
		{
			String defaultPhotoFile = ConfigUtil.getProperty("file.path.affiche") + "photo.jpg";
			try
			{
				params.put("photoStream", new FileInputStream(defaultPhotoFile));
			}
			catch (FileNotFoundException e)
			{
			}
		}


		params.put("title", ReportConfigUtil.getProperty(Constants.PERSON_GANBU_TITLE));
		params.put("p1", CommonPrintUtil.checkObjIsNull(person.getName(), null));//姓名
		params.put("p2", CommonPrintUtil.checkObjIsNull(person.getSexCode(), DicConstants.YHRS0001));//性别
		params.put("p3", CommonPrintUtil.checkObjIsNull(person.getBirthday(), "yyyy.MM"));//出生日期
		params.put("p27", "(" + CommonPrintUtil.checkObjIsNull(CommonPrintUtil.caculateAge(person.getBirthday(), new Date()), null) + ")");
		params.put("p4", CommonPrintUtil.checkObjIsNull(person.getPeopleCode(), DicConstants.YHRS0004));//民族
		params.put("p5", CommonPrintUtil.checkObjIsNull(person.getAncestorPlaceCode(), DicConstants.YHRS0006));//籍贯
		params.put("p6", CommonPrintUtil.checkObjIsNull(person.getBirthplaceCode(), DicConstants.YHRS0006));//出生地
		params.put("p8", CommonPrintUtil.checkObjIsNull(person.getStartWorkDate(), "yyyy.MM"));//工作开始时间
		List<PbPersonInDTO> pbPersonInDTOList = pbPersonInService.listPbPersonInByPersonOid(personOid);
		if(CollectionUtils.isNotEmpty(pbPersonInDTOList)){
			params.put("p7", CommonPrintUtil.checkObjIsNull(pbPersonInDTOList.get(0).getEntryCurrentUnitDate(), "yyyy.MM"));//进入本单位时间字段已删除
		}
		params.put("p9", CommonPrintUtil.checkObjIsNull(person.getMarriageStatusCode(), DicConstants.YHRS0008));//婚姻状况
		//职业资格信息
		params.put("p15", CommonPrintUtil.checkObjIsNull(personAttach.getCertificateNo()	, null));//编号
		if(StringUtils.isNotEmpty(personAttach.getCertificateNo())){
			PbCertificateInfoDTO certificateInfoDto = pbCertificateInfoService.getCertificateInfoByCertificateNo(person.getPersonOid(),personAttach.getCertificateNo().trim());
			params.put("p16", certificateInfoDto==null?"":certificateInfoDto.getCertificateUnit());//执业地点
		}else{
			params.put("p16", "/");//执业地点
		}
		
		params.put("p17", CommonPrintUtil.checkObjIsNull(personAttach.getQualificationsName(), null));//名称
		//专业技术资格信息
		params.put("p18", CommonPrintUtil.checkObjIsNull(personAttach.getProfCertificateNo(), null));//编号
		params.put("p19", CommonPrintUtil.checkObjIsNull(personAttach.getProfTechName(), null));//名称
		params.put("p20", CommonPrintUtil.checkObjIsNull(personAttach.getProfTechLevel(), DicConstants.YHRS0047));//等级
		//岗位聘任信息
		params.put("p21", CommonPrintUtil.checkObjIsNull(personAttach.getHisPositionName(), null));//在聘岗位
		params.put("p22", "/");//现任岗位
		//去信息集中取
		//PbDutyInfoDTO pbDutyInfoDto = pbDutyInfoService.getPbDutyInfoByPersonOid(person.getPersonOid());
		//params.put("p14", CommonPrintUtil.checkObjIsNull((pbDutyInfoDto==null?"":pbDutyInfoDto.getDutyName()), DicConstants.YHRS0126));//现任职务
		//附属表中取
		params.put("p14", CommonPrintUtil.checkObjIsNull(personAttach.getDutyName(),DicConstants.YHRS0126));//现任职务
		//学历学位
		String qrzEducationLevelCode = DicHelper.viewName(DicConstants.YHRS0039, personAttach.getFtEducationLevelCode());//全日制学历
		String qEducationLevelCode = CommonPrintUtil.checkObjIsNull((qrzEducationLevelCode==null?personAttach.getFtEducationLevelCode():qrzEducationLevelCode),null);
		String qDegreeCode = CommonPrintUtil.checkObjIsNull(DicHelper.viewName(DicConstants.YHRS0040, personAttach.getFtDegreeCode()), null);
		String s = ",";
		String qSchoolName = CommonPrintUtil.checkObjIsNull(personAttach.getFtSchoolName(),null);
		String qMajorCodeName = CommonPrintUtil.checkObjIsNull(personAttach.getFtMajorName(), null);
		if(qEducationLevelCode == "/"){
			params.put("p12", qDegreeCode);//全日制
		}else if(qDegreeCode == "/"){
			params.put("p12", qEducationLevelCode);//全日制
		}else if(qEducationLevelCode != "/"&&qDegreeCode != "/"){
			params.put("p12", qEducationLevelCode+s+qDegreeCode);//全日制
		}
		if(qSchoolName == "/"){
			params.put("p13", qMajorCodeName);// 毕业院校及专业
		}else if(qMajorCodeName == "/"){
			params.put("p13", qSchoolName);// 毕业院校及专业
		}else if(qSchoolName != "/"&&qMajorCodeName != "/"){
			params.put("p13", qSchoolName+s+qMajorCodeName);// 毕业院校及专业
		}
		
		String zzEducationLevelCode = DicHelper.viewName(DicConstants.YHRS0039, personAttach.getOjEducationLevelCode());
		String zEducationLevelCode = CommonPrintUtil.checkObjIsNull((zzEducationLevelCode==null?personAttach.getOjEducationLevelCode():zzEducationLevelCode),null);
		String zDegreeCode = CommonPrintUtil.checkObjIsNull(DicHelper.viewName(DicConstants.YHRS0040, personAttach.getOjDegreeCode()), null);
		String zSchoolName = CommonPrintUtil.checkObjIsNull(personAttach.getOjSchoolName(),null);
		String zMajorCodeName = CommonPrintUtil.checkObjIsNull(personAttach.getOjMajorName(), null);
		if(zEducationLevelCode == "/"){
			params.put("p100", zDegreeCode);//在职
		}else if(zDegreeCode == "/"){
			params.put("p100", zEducationLevelCode);//在职
		}else if(zEducationLevelCode != "/"&&zDegreeCode != "/"){
			params.put("p100", zEducationLevelCode+s+zDegreeCode);//在职
		}
		if(zSchoolName == "/"){
			params.put("p101", zMajorCodeName);// 毕业院校及专业
		}else if(zMajorCodeName == "/"){
			params.put("p101", zSchoolName);// 毕业院校及专业
		}else if(zSchoolName != "/"&&zMajorCodeName != "/"){
			params.put("p101", zSchoolName+s+zMajorCodeName);//毕业院校及专业
		}
		List<String> resumeList = this.officeCommonService.getResumeByPersonOid(personOid, true);//存放简历信息的list

		if (null != resumeList && resumeList.size() > 0)//传递简历参数
		{
			int size = resumeList.size();
			if (size > 15)
			{
				params.put("totalResume", size + "");
			}

			int start = size - 1;
			if (size > 30)
			{
				start = 29;
			}

			int index = 0;
			for (int i = start; i >= 0; i--)
			{
				index++;
				params.put("r" + index, resumeList.get(i));
			}
		}
		else
		{
			params.put("r7", "无");
		}
		TableTagBean ttb = new TableTagBean();
		ttb.getCondition().put("personOid", personOid.toString());
		List<PbRewardInfoDTO> rewardInfoList = this.pbRewardInfoService.find(ttb);//奖励信息
		List<PbPunishmentInfoDTO> punishmentInfoList = this.pbPunishmentInfoService.find(ttb);//惩罚信息
		List<String> rewardTmpList = new ArrayList<String>();
		List<String> punishmentTmpList = new ArrayList<String>();
		List<String> rewardAndPunishmentList = new ArrayList<String>();

		if (null != rewardInfoList && rewardInfoList.size() > 0)//组装奖励信息
		{
			for (PbRewardInfoDTO rewardInfo : rewardInfoList)
			{
				StringBuffer rewardAndPunishment = new StringBuffer();
				rewardAndPunishment.append(rewardInfo.getRewardDate() == null ? "" : DateUtil.format(rewardInfo.getRewardDate(), DateUtil.DATE_PATTERN_DEFAULT));
				rewardAndPunishment.append(" ");
				rewardAndPunishment.append(rewardInfo.getRewardName() == null ? "" : rewardInfo.getRewardName());
				rewardTmpList.add(rewardAndPunishment.toString());
			}
		}

		if (null != punishmentInfoList && punishmentInfoList.size() > 0)//组装惩罚信息
		{
			for (PbPunishmentInfoDTO punishmentInfo : punishmentInfoList)
			{
				StringBuffer rewardAndPunishment = new StringBuffer();
				rewardAndPunishment.append(punishmentInfo.getPunishmentDate() == null ? "" : DateUtil.format(punishmentInfo.getPunishmentDate(), DateUtil.DATE_PATTERN_DEFAULT));
				rewardAndPunishment.append(" ");
				rewardAndPunishment.append(punishmentInfo.getPunishmentName() == null ? "" : punishmentInfo.getPunishmentName());
				punishmentTmpList.add(rewardAndPunishment.toString());
			}
		}

		if (null != rewardTmpList && rewardTmpList.size() > 0)// 奖励信息排序，并加入rewardAndPunishmentList中
		{
			List<String> sortList = CommonPrintUtil.sortList(rewardTmpList);
			int j = 0;
			for (String str : sortList)
			{
				if (j == 3)
					break;
				rewardAndPunishmentList.add(str);
				j++;
			}
		}

		if (null != punishmentTmpList && punishmentTmpList.size() > 0)//惩罚信息排序，并加入rewardAndPunishmentList中
		{
			List<String> sortList = CommonPrintUtil.sortList(punishmentTmpList);
			int j = 0;
			for (String str : sortList)
			{
				if (j == 3)
					break;
				rewardAndPunishmentList.add(str);
				j++;
			}
		}

		if (null != rewardAndPunishmentList && rewardAndPunishmentList.size() > 0)//奖惩信息的参数传递给报表
		{
			int index = 0;
			for (String str : rewardAndPunishmentList)
			{
				index++;
				switch (index)
				{
					case 1:
						params.put("p28", str);
						break;
					case 2:
						params.put("p29", str);
						break;
					case 3:
						params.put("p30", str);
						break;
					case 4:
						params.put("p31", str);
						break;
					case 5:
						params.put("p32", str);
						break;
					case 6:
						params.put("p33", str);
						break;
				}
			}
		}
		else
		{
			params.put("p30", "无");
		}
		//年度考核结果
		String review = this.officeCommonService.getReviewInfoByPersonOid(personOid, 5);

		params.put("p34", StringUtils.isEmpty(review) ? "无" : review);

		List<PbFamilyInfoDTO> familyInfoList = this.pbFamilyInfoService.listPbFamilyInfoByPersonOid(personOid);// 家庭成员

		if (null != familyInfoList && familyInfoList.size() > 0)//  将家庭成员信息参数传入报表
		{
			int index = 0;
			for (PbFamilyInfoDTO familyInfo : familyInfoList)
			{
				String relationship = CommonPrintUtil.checkObjIsNull(familyInfo.getRelationship(), DicConstants.YHRS0024);//与本人关系
				String name = CommonPrintUtil.checkObjIsNull(familyInfo.getName(), null);
				String age = "";
				if (null != DateUtil.format(familyInfo.getBirthday(), "yyyy.MM"))
				{
					age = CommonPrintUtil.checkObjIsNull(DateUtil.format(familyInfo.getBirthday(), "yyyy.MM") + "\r（" + CommonPrintUtil.caculateAge(familyInfo.getBirthday(), new Date()) + "）",
							null);
				}
				String politic = CommonPrintUtil.checkObjIsNull(familyInfo.getPoliticsVisage(), DicConstants.YHRS0025);
				String unitAndDuty = (familyInfo.getUnit() == null ? "" : familyInfo.getUnit()) + " " + (familyInfo.getDuty() == null ? "" : familyInfo.getDuty());
				unitAndDuty = CommonPrintUtil.checkObjIsNull(unitAndDuty.trim(), null);
				index++;
				switch (index)
				{
					case 1:
						params.put("p42", relationship);
						params.put("p43", name);
						params.put("p44", age);
						params.put("p45", politic);
						params.put("p46", unitAndDuty);
						break;
					case 2:
						params.put("p47", relationship);
						params.put("p48", name);
						params.put("p49", age);
						params.put("p50", politic);
						params.put("p51", unitAndDuty);
						break;
					case 3:
						params.put("p52", relationship);
						params.put("p53", name);
						params.put("p54", age);
						params.put("p55", politic);
						params.put("p56", unitAndDuty);
						break;
					case 4:
						params.put("p57", relationship);
						params.put("p58", name);
						params.put("p59", age);
						params.put("p60", politic);
						params.put("p61", unitAndDuty);
						break;
					case 5:
						params.put("p62", relationship);
						params.put("p63", name);
						params.put("p64", age);
						params.put("p65", politic);
						params.put("p66", unitAndDuty);
						break;
					case 6:
						params.put("p67", relationship);
						params.put("p68", name);
						params.put("p69", age);
						params.put("p70", politic);
						params.put("p71", unitAndDuty);
						break;
					case 7:
						params.put("p72", relationship);
						params.put("p73", name);
						params.put("p74", age);
						params.put("p75", politic);
						params.put("p76", unitAndDuty);
						break;
					case 8:
						params.put("p77", relationship);
						params.put("p78", name);
						params.put("p79", age);
						params.put("p80", politic);
						params.put("p81", unitAndDuty);
						break;
					case 9:
						params.put("p82", relationship);
						params.put("p83", name);
						params.put("p84", age);
						params.put("p85", politic);
						params.put("p86", unitAndDuty);
						break;
					case 10:
						params.put("p87", relationship);
						params.put("p88", name);
						params.put("p89", age);
						params.put("p90", politic);
						params.put("p91", unitAndDuty);
						break;
				}
			}
		}

		return params;
	}

	

}

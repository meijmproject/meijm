package com.yh.hr.report.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.NumberUtils;

public class TrainSituationPersonInfoQueryHelper {

	/**
	 *  接受其他医院进修人员数
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<List<PbPersonInfoDTO>> wpPersonInfo(String year) throws DataAccessFailureException {
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select p.*");
		hql.append(" from (select distinct pi.*");
		hql.append(" from yhc_Pb_Person_Info pi");
		hql.append(" left join yhc_Pb_Prof_Tech_Qualif_Info ptqi");
		hql.append(" on pi.person_Oid = ptqi.person_Oid");
		hql.append(" where pi.person_Type = '05'");
		hql.append(" and ptqi.prof_Tech_Level like :profTechLevel) p");
		hql.append(" left join yhc_Pb_Education_Training_Info peti");
		hql.append(" on p.person_Oid = peti.person_Oid");
		hql.append(" where peti.education_training_kink_code='2' and date_format(peti.training_Begin_Date, '%Y') =:trainingBeginDate ");
		
		List<List<PbPersonInfoDTO>> rowList = new ArrayList<List<PbPersonInfoDTO>>();  //包含所有委培人员信息的list
		List<List<Object[]>> list = new ArrayList<List<Object[]>>();  //sql查询结果list
		//循环，从2013--2017年
		for(int i=4; i>=0;i--){
			int numberYear = NumberUtils.intValue(year);
			String realYear = String.valueOf(numberYear-i);
			HashMap<String, Object> hqlParams1 = new HashMap<String, Object>();
			hqlParams1.put("profTechLevel", "41_");     //高级
			hqlParams1.put("trainingBeginDate", realYear);
			List<Object[]> boList1 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams1, 0, 0);
			
			HashMap<String, Object> hqlParams2 = new HashMap<String, Object>();
			hqlParams2.put("profTechLevel", "420");     //中级
			hqlParams2.put("trainingBeginDate", realYear);
			List<Object[]> boList2 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams2, 0, 0);
			
			HashMap<String, Object> hqlParams3 = new HashMap<String, Object>();
			hqlParams3.put("profTechLevel", "43_");     //初级
			hqlParams3.put("trainingBeginDate", realYear);
			List<Object[]> boList3 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams3, 0, 0);
			list.add(boList1);
			list.add(boList2);
			list.add(boList3);
		}
		//将list<Object[]>转为list<PbPersonInfoDTO>
		for(List<Object[]> vList : list){
			rowList.add(changeToDTO(vList));
		}
		return rowList;
	}
	
	/**
	 * 统计参加政府举办的岗位培训人次数
	 * @param year
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<List<Long>> trainingByGov(String year) throws DataAccessFailureException {
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select distinct tqi.person_oid");
		hql.append(" from yhc_Pb_Prof_Tech_Qualif_Info tqi");
		hql.append(" left join yhc_pb_education_training_info eti");
		hql.append(" on tqi.person_oid = eti.person_oid");
		hql.append(" where eti.education_training_kink_code='1' and date_format(eti.training_Begin_Date, '%Y') = :trainingBeginDate");
		hql.append(" and tqi.prof_Tech_Level like :profTechLevel");
		List<List<Long>> list = new ArrayList<List<Long>>();  //sql查询结果list
		//循环，从2013--2017年
		for(int i=4; i>=0;i--){
			int numberYear = NumberUtils.intValue(year);
			String realYear = String.valueOf(numberYear-i);
			HashMap<String, Object> hqlParams1 = new HashMap<String, Object>();
			hqlParams1.put("profTechLevel", "41_");     //高级
			hqlParams1.put("trainingBeginDate", realYear);
			List<Long> boList1 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams1, 0, 0);
			
			HashMap<String, Object> hqlParams2 = new HashMap<String, Object>();
			hqlParams2.put("profTechLevel", "420");     //中级
			hqlParams2.put("trainingBeginDate", realYear);
			List<Long> boList2 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams2, 0, 0);
			
			HashMap<String, Object> hqlParams3 = new HashMap<String, Object>();
			hqlParams3.put("profTechLevel", "43_");     //初级
			hqlParams3.put("trainingBeginDate", realYear);
			List<Long> boList3 = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams3, 0, 0);
			
			list.add(boList1);
			list.add(boList2);
			list.add(boList3);
		}
		return list;
	}
	
	public static List<PbPersonInfoDTO> changeToDTO(List<Object[]> list){
		try {
			return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], PbPersonInfoDTO>() {

				public PbPersonInfoDTO getValue(Object[] src) throws ServiceException {
					PbPersonInfoDTO dto = new PbPersonInfoDTO();
					
					dto.setPersonOid(DataConverUtils.toLong(src[0]));
					dto.setName(DataConverUtils.toString(src[1]));
					dto.setIdCode(DataConverUtils.toString(src[2]));
					dto.setIdNo(DataConverUtils.toString(src[3]));
					dto.setEnglishName(DataConverUtils.toString(src[4]));
					dto.setAlternativeName(DataConverUtils.toString(src[5]));
					dto.setSexCode(DataConverUtils.toString(src[6]));
					dto.setBirthday(DataConverUtils.toDate(src[7]));
					dto.setPersonCode(DataConverUtils.toString(src[8]));
					dto.setNationalityCode(DataConverUtils.toString(src[9]));
					dto.setAncestorPlaceCode(DataConverUtils.toString(src[10]));
					dto.setBirthplaceCode(DataConverUtils.toString(src[11]));
					dto.setHukouPlace(DataConverUtils.toString(src[12]));
					dto.setIsSz(DataConverUtils.toString(src[13]));
					dto.setHealthStatusCode(DataConverUtils.toString(src[14]));
					dto.setMarriageStatusCode(DataConverUtils.toString(src[15]));
					dto.setAddress(DataConverUtils.toString(src[16]));
					dto.setEmail(DataConverUtils.toString(src[17]));
					dto.setMobilePhone(DataConverUtils.toString(src[18]));
					dto.setOfficePhone(DataConverUtils.toString(src[19]));
					dto.setUnitOid(DataConverUtils.toLong(src[20]));
					dto.setDeptOid(DataConverUtils.toLong(src[21]));
					dto.setHireDeptOid(DataConverUtils.toLong(src[22]));
					dto.setPersonStatus(DataConverUtils.toString(src[23]));
					dto.setPersonType(DataConverUtils.toString(src[24]));
					dto.setFlagOfHkmctwChineseCode(DataConverUtils.toString(src[25]));
					dto.setProtTechFlag(DataConverUtils.toString(src[26]));
					dto.setExportFlag(DataConverUtils.toString(src[27]));
					dto.setDoctorFlag(DataConverUtils.toString(src[28]));
					dto.setIsAbordExpert(DataConverUtils.toString(src[29]));
					dto.setIsStudyAbordExpert(DataConverUtils.toString(src[30]));
					dto.setIsComesChinaExpert(DataConverUtils.toString(src[31]));
					dto.setIsVeteran(DataConverUtils.toString(src[32]));
					dto.setIsAllocation(DataConverUtils.toString(src[33]));
					dto.setIsCadre(DataConverUtils.toString(src[34]));
					dto.setdPositionType(DataConverUtils.toString(src[35]));
					dto.setBankpoll(DataConverUtils.toString(src[36]));
					dto.setPersonOrderView(DataConverUtils.toLong(src[37]));
//					dto.setEntryCurrentUnitType(DataConverUtils.toString(src[38])); 进入单位方式字段已删除
//					dto.setEntryCurrentUnitDate(DataConverUtils.toDate(src[37])); 进入单位时间字段已删除
					dto.setEmergContact(DataConverUtils.toString(src[40]));
					dto.setEmergPhone(DataConverUtils.toString(src[41]));
					dto.setDispatchingUnitCode(DataConverUtils.toString(src[42]));
					dto.setPersonCode(DataConverUtils.toString(src[43]));
					dto.setPersonIntOid(DataConverUtils.toLong(src[44]));
					dto.setCreateBy(DataConverUtils.toString(src[45]));
					dto.setCreateName(DataConverUtils.toString(src[46]));
					dto.setCreateDate(DataConverUtils.toDate(src[47]));
					dto.setUpdateBy(DataConverUtils.toString(src[48]));
					dto.setUpdateName(DataConverUtils.toString(src[49]));
					dto.setUpdateDate(DataConverUtils.toDate(src[50]));
					dto.setLevelCode(DataConverUtils.toString(src[51]));
					return dto;
				}});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}



}

package com.yh.hr.report.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class ZgEmployedPersonQueryHelper {
	
	/**
	 * 映射统计结果
	 * @param list
	 * @return
	 */
	public static List<Map<String,String>> mappingResult(List<Object[]> list) {
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		Map<String,String> mainHighMap = new HashMap<String, String>();
		Map<String,String> highMap = new HashMap<String, String>();
		Map<String,String> middleMap = new HashMap<String, String>();
		Map<String,String> otherMap = new HashMap<String, String>();
		Map<String,String> totalMap = new HashMap<String, String>();
		String mainHigh = "";
		String high = "";
		String middle = "";
		String others = "";
		String total = "";
		for(Object[] obj: list) {
			if(obj[0]!=null&&obj[0].equals(DicConstants.YHRS0015_411)) {
				mainHigh = obj[1].toString();
			}else if(obj[0]!=null&&obj[0].equals(DicConstants.YHRS0015_412)) {
				high = obj[1].toString();
			}else if(obj[0]!=null&&obj[0].equals(DicConstants.YHRS0015_420)) {
				middle = obj[1].toString();
			}else {
				others = sum(new String[]{obj[1]!=null?obj[1].toString():"",others})+"";
			}
			total = sum(new String[]{obj[1]!=null?obj[1].toString():"",total})+"";
		}
		mainHighMap.put("profTechLevel", "meinHeigh");
		mainHighMap.put("count", mainHigh);
		resultList.add(mainHighMap);
		
		highMap.put("profTechLevel", "high");
		highMap.put("count", high);
		resultList.add(highMap);
		
		middleMap.put("profTechLevel", "middle");
		middleMap.put("count", middle);
		resultList.add(middleMap);
		
		otherMap.put("profTechLevel", "other");
		otherMap.put("count",others+"");
		resultList.add(otherMap);
		
		totalMap.put("profTechLevel", "total");
		totalMap.put("count", total+"");
		resultList.add(totalMap);
		return resultList;
	}
	
	/**
	 * 年末在岗职工数（包括正式职工、聘用职工、本院规培学员）
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZgzg() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level " +
				" from yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				" 	prof_tech_level " +
				" 	from yhc_pb_person_attach " +
				" 	group by person_oid" +
				" ) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_type in ('01','02','04')" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 卫生技术人员数（未含行政人	员）
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findWsjs() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpygei.his_position_type," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_YN_GW_EMPLOY_INFO jpygei,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpygei.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpygei.his_position_status = '"+DicConstants.YHRS0026_001+"'" +
				" and jpygei.his_position_type in ('1','2','3','4')" +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业医师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyys() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_1+"' " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业医师——中医类别数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyysZylb() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_1+"' " +
				" and jpqi.qualifications_code in " +
				" (select dic_item_code from yha_dic_item " +
				" where dic_type_oid = (select dic_type_oid from yha_dic_type where dic_type_code='"+DicConstants.YHRS9004+"')" +
				" and dic_item_code in ('02','03','04') or parent_code in('02','03','04'))" +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业助理医师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyzlys() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_2+"' " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业助理医师——中医类别数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyzlysZylb() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_2+"' " +
				" and jpqi.qualifications_code in " +
				" (select dic_item_code from yha_dic_item " +
				" where dic_type_oid = (select dic_type_oid from yha_dic_type where dic_type_code='"+DicConstants.YHRS9004+"')" +
				" and dic_item_code in ('02','03','04') or parent_code in('02','03','04'))" +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业药师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyyaos() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_3+"' " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业药师——西药师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyyaosxys() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_3+"' " +
				" and jpqi.qualifications_code ='0601' " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 执业药师师——中药师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findZyyaoszys() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_type ='"+DicConstants.YHRS0127_3+"' " +
				" and jpqi.qualifications_code ='0602' " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 检验技师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findJyjs() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_code in ('0704','0705') " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 影像技师数
	 * @return
	 * @throws ServiceException
	 */
	public static List<Map<String, String>> findYxjs() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select prof_tech_level," +
				"count(person_oid)" +
				" from (select jppi.person_oid," +
				" jpptqi.prof_tech_level" +
				" from YHC_PB_QUALIFICATIONS_INFO jpqi,yhc_pb_person_info jppi" +
				" left join " +
				" (select person_oid," +
				"	prof_tech_level " +
				"	from yhc_pb_person_attach " +
				"	group by person_oid) jpptqi" +
				" on jppi.person_oid=jpptqi.person_oid" +
				" where jppi.person_oid=jpqi.person_oid" +
				" and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				" and jpqi.is_highest_level=1" +
				" and jpqi.qualifications_code in ('0701') " +
				" ) t group by prof_tech_level");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 求和
	 * @param values
	 * @return
	 */
	private static int sum(String[] values) {
		int result = 0;
		for(String s: values) {
			result+=number(s);
		}
		return result;
	}
	
	/**
	 * 转换为int类型
	 * @param value
	 * @return
	 */
	private static int number(String value) {
		return isEmpty(value)?0:Integer.parseInt(value);
	}
	
	/**
	 * 判断字符串是否为空、null、“0”
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if(value==null||value.equals("")||value.equals("0")) {
			return true;
		}else{
			return false;
		}
	}

}

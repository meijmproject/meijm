/**
 * 
 */
package com.yh.hr.info.biz.unit.workbench.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.hr.res.cf.queryhelper.JhcShowResultOrderQueryHelper;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class VerPbPersonWorkbenchQueryHelper {

	public static List<VerPersonDTO> listVerPerson(TableTagBean ttb) throws ServiceException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();
		buildSQL(ttb,sql,params);
		
		StringBuilder lsql = new StringBuilder();
		lsql.append(" from (");
		lsql.append("select p.person_oid,");
		lsql.append("       p.PERSON_CODE, "); 
		lsql.append("       p.name," );
		lsql.append("       p.person_status," );
		lsql.append("       p.person_type," );
		lsql.append("       p.unit_oid," );
		lsql.append("       p.unit_name," );
		lsql.append("       p.HIRE_DEPT_OID," );
		lsql.append("       p.org_name, ");
		lsql.append("       p.id_code," );
		lsql.append("       p.id_no," );
		lsql.append("       p.sex_code, "); 
		lsql.append("       p.MARRIAGE_STATUS_CODE," );
		lsql.append("       p.birthday, "); 
		lsql.append("       p.BIRTHPLACE_CODE, "); 
		lsql.append("       p.HUKOU_PLACE, "); 
		lsql.append("       p.IS_SZ, "); 
		lsql.append("       p.ANCESTOR_PLACE_CODE, "); 
		lsql.append("       pa.POLITIC_STATUS_CODE, "); 
		lsql.append("       pa.ENTRY_CURRENT_UNIT_DATE, "); 
		
		//学历
		lsql.append("       pa.ft_school_name, "); 
		lsql.append("       pa.ft_major_name, "); 
		lsql.append("       pa.ft_education_level_code, "); 
		lsql.append("       pa.ft_degree_code, "); 
		lsql.append("       pa.oj_school_name, "); 
		lsql.append("       pa.oj_major_name, "); 
		lsql.append("       pa.oj_education_level_code, "); 
		lsql.append("       pa.oj_degree_code, "); 
		//专业技术资格
		lsql.append("       pa.PROF_TECH_NAME, "); 
		lsql.append("       pa.PROF_PROCURE_DATE, "); 
		//执业资格
		lsql.append("       pa.CERTIFICATE_NO, "); 
		lsql.append("       pa.QUALIFICATIONS_NAME, "); 
		//从事岗位
		lsql.append("       pa.HIS_POSITION_NAME, ");
		
		//护士层级
		lsql.append("       pa.LEVEL_CODE, ");
		
		//专业技术资格编号
		lsql.append("       pa.prof_Certificate_No, ");
		//手机号码
		lsql.append("       p.mobile_phone, ");
		//执业类型
		lsql.append("       pa.QUALIFICATIONS_TYPE, ");
		//所聘院内岗位时间
		lsql.append("       pa.HIS_BEGIN_DATE, ");
		//所聘事业岗位
		lsql.append("       pa.M_POSITION_NAME, ");
		//所聘事业岗位时间
		lsql.append("       pa.POSITION_DATE, ");
		//事业单位岗位类型
		lsql.append("       pa.M_POSITION_TYPE, ");
		//院内岗位类型
		lsql.append("       pa.HIS_POSITION_TYPE ");
		//民族
		lsql.append("       ,p.PEOPLE_CODE ");
		//排序号
		lsql.append("		,p.PERSON_ORDER_VIEW");
		//专业资格等级
		lsql.append("		,pa.PROF_TECH_LEVEL");
		//执业资格等级
		lsql.append("		,pa.QUALIFICATIONS_LEVEL_CODE");
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) ").append(lsql).append(sql).toString(), params));
		}
				
		//order by  p.order_View, p.person_oid, pa.administrative_duty_attribute, pa.administrative_duty_level 

	List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder().append("select * ").append(lsql).append(sql).toString(), params, ttb.getPage(), ttb.getPageSize());
		//List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).toString(), params, ttb.getPage(), ttb.getPageSize());
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], VerPersonDTO>() {

			public VerPersonDTO getValue(Object[] src) throws ServiceException {
				VerPersonDTO dto = new VerPersonDTO();
				
				dto.setPersonOid(DataConverUtils.toLong(src[0]));
				dto.setPersonCode(DataConverUtils.toString(src[1]));
				dto.setName(DataConverUtils.toString(src[2]));
				dto.setPersonStatus(DataConverUtils.toString(src[3]));
				dto.setPersonType(DataConverUtils.toString(src[4]));
				dto.setUnitOid(DataConverUtils.toLong(src[5]));
				dto.setUnitName(DataConverUtils.toString(src[6]));
				dto.setHireDeptOid(DataConverUtils.toLong(src[7]));
				dto.setHireDeptName(DataConverUtils.toString(src[8]));
				dto.setIdCode(DataConverUtils.toString(src[9]));
				dto.setIdNo(DataConverUtils.toString(src[10]));
				dto.setSexCode(DataConverUtils.toString(src[11]));
				dto.setMarriageStatusCode(DataConverUtils.toString(src[12]));
				dto.setBirthday(DataConverUtils.toDate(src[13]));
                dto.setBirthplaceCode(DataConverUtils.toString(src[14]));
                dto.setHukouPlace(DataConverUtils.toString(src[15]));
                dto.setIsSz(DataConverUtils.toString(src[16]));
                dto.setAncestorPlaceCode(DataConverUtils.toString(src[17]));
                dto.setPoliticStatusCode(DataConverUtils.toString(src[18]));
                dto.setEntryCurrentUnitDate(DataConverUtils.toDate(src[19]));
				dto.setFtSchoolName(DataConverUtils.toString(src[20]));
				dto.setFtMajorName(DataConverUtils.toString(src[21]));
				dto.setFtEducationLevelCode(DataConverUtils.toString(src[22]));
				dto.setFtDegreeCode(DataConverUtils.toString(src[23]));
				dto.setOjSchoolName(DataConverUtils.toString(src[24]));
				dto.setOjMajorName(DataConverUtils.toString(src[25]));
				dto.setOjEducationLevelCode(DataConverUtils.toString(src[26]));
				dto.setOjDegreeCode(DataConverUtils.toString(src[27]));
				dto.setProfTechName(DataConverUtils.toString(src[28]));
				dto.setProfProcureDate(DataConverUtils.toDate(src[29]));
				dto.setCertificateNo(DataConverUtils.toString(src[30]));
				dto.setQualificationsName(DataConverUtils.toString(src[31]));
				dto.setHisPositionName(DataConverUtils.toString(src[32]));
				dto.setLevelCode(DataConverUtils.toString(src[33]));
				dto.setProfCertificateNo(DataConverUtils.toString(src[34]));
				dto.setMobilePhone(DataConverUtils.toString(src[35]));
				dto.setQualificationsType(DataConverUtils.toString(src[36]));
				dto.setHisBeginDate(DataConverUtils.toDate(src[37]));
				dto.setmPositionName(DataConverUtils.toString(src[38]));
				dto.setPositionDate(DataConverUtils.toDate(src[39]));
				dto.setmPositionType(DataConverUtils.toString(src[40]));
				dto.setHisPositionType(DataConverUtils.toString(src[41]));
				dto.setPeopleCode(DataConverUtils.toString(src[42]));
				dto.setPersonOrderView(DataConverUtils.toLong(src[43]));
				dto.setProfTechLevel(DataConverUtils.toString(src[44]));
				dto.setQualificationsLevelCode(DataConverUtils.toString(src[45]));
				return dto;
			}});
	}
	
	public static void buildSQL(TableTagBean ttb, StringBuilder sql, Map<String, Object> params) throws ServiceException {
		sql.append(" from (select pi.*, u.unit_name,o.org_name " );
		sql.append("          from yhc_ut_unit u, yhc_pb_person_info pi" );
		sql.append("          left join yhc_ut_org o on pi.HIRE_DEPT_OID = o.org_oid" );
		sql.append("         where pi.unit_oid = u.unit_oid" );
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitKind"))) {
			sql.append(" and u.unit_Kind in (").append(ttb.getCondition().get("unitKind")).append(") ");
		}
		
		/*if (StringUtils.isNotEmpty(ttb.getCondition().get("organizationOid"))) {
			sql.append(" and u.ORGANIZATION_OID = ").append(ttb.getCondition().get("organizationOid"));
		}*/
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitOid"))) {
			sql.append(" and pi.unit_Oid = ").append(ttb.getCondition().get("unitOid"));
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgOid"))) {
			String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByOid(ttb.getCondition().get("orgOid"));
			sql.append(" and pi.HIRE_DEPT_OID in(").append(orgOidString).append(")");
		}
		//过滤用户权限
		sql.append(" and pi.HIRE_DEPT_OID in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("')");
		if (StringUtils.isNotEmpty(ttb.getCondition().get("name"))) {
			sql.append(" and pi.name like :name ");
			params.put("name", StringUtil.wrapPercent(ttb.getCondition().get("name")));
		}
		sql.append("        ) p" );
		sql.append("  left join yhc_pb_person_attach pa on (p.person_oid = pa.person_oid)" );
		
		sql.append("  where 1=1 " );
		sql.append(" ) t where 1=1 ");
		List<Map<String,String>> list = mapCondition(ttb.getCondition());
		for(int i=0; i<list.size(); i++) {
			Map<String,String> map = list.get(i);
			String value = map.get("value");
			value = value.replace("\\", "\\\\");
			value = value.replace("\'", "\\\'");
			Object param = new Object();
			if(StringUtils.isNotEmpty(value)) {
				String type = map.get("type");
				if(type.equals("date")) {
					param = "str_to_date('"+value+"', '%Y-%m-%d')";
				}else {
					param = "'"+value+"'";
					if(map.get("sign").equals("like")) {
						param = "'%"+value+"%'";
					}
				}
				
				if(map.get("sign").equals("in")){
					String[] arr = value.split(",");
					String in = " (";
					for(String s: arr) {
						in+="'"+s+"',";
					}
					in = in.substring(0,in.length()-1)+")";
					sql.append(" and "+map.get("name")+" "+map.get("sign")+in);
					continue;
				}else {
					sql.append(" and "+map.get("name")+" "+map.get("sign")+param);
				}
			}
		}
		List<Map<String, String>> sortFields = JhcShowResultOrderQueryHelper.find(UserContext.getLoginUserID(), ttb.getCondition().get("functionCode"));
		String orderby = sortFields.size()>0 ? " order by " : " order by person_oid ";
		for(Map<String, String> map: sortFields) {
			orderby += map.get("databaseField");
			orderby += " ";
			orderby += map.get("sort");
			orderby += ",";
		}
		orderby = orderby.substring(0, orderby.length()-1);
		sql.append(orderby);
	}
	public static List<Map<String,String>> mapCondition(StringMap params){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(String key:params.keySet()){
			Matcher isNum = pattern.matcher(key);
			if(isNum.matches()){
				Map<String, String> map = new HashMap<String, String>();  
				JSONObject json = JSONObject.fromObject(params.get(key));
				@SuppressWarnings("rawtypes")
				Iterator it = json.keys();  
				while (it.hasNext()) {  
					String k = String.valueOf(it.next());  
					String value = (String) json.get(k);  
					map.put(k, value);
				}
				list.add(map);
			}
		}
		return list;
		
	}

	public static List<UtOrgDTO> listLeafOrg(String orgOid,String organizationOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select juoo.org_name, juoo.org_oid 	from yhc_ut_org juoo, yhc_ut_relation jur");
		sql.append(" where juoo.organization_oid = jur.child_organization_oid");
		sql.append(" and not exists (select 1 from yhc_ut_org juo, yhc_ut_relation ju where juo.organization_oid = ju.parent_organization_oid");
		sql.append(" and juo.org_oid = juoo.org_oid)");
		if(StringUtils.isNotEmpty(organizationOid)){
			sql.append(" start with juoo.org_oid in (select ju.org_oid from yhc_ut_relation jur, yhc_ut_org ju");
			sql.append(" where ju.organization_oid = jur.child_organization_oid and jur.relation_type = '"+UtConstants.UT_RELATION_TYPE_3+"'");
			sql.append(" 	and jur.parent_organization_oid ="+organizationOid+")");
		}else if(StringUtils.isNotEmpty(orgOid)){
			sql.append(" start with juoo.org_oid="+orgOid);
		}
		sql.append(" connect by prior jur.child_organization_oid = jur.parent_organization_oid");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();
			
			for (Object[] objs : list) 
			{
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(objs[1] == null ? null : Long.valueOf(objs[1].toString()));
				dto.setOrgName(objs[0] == null ? null : objs[0].toString());
				items.add(dto);
			}
			return items;
		}
	 return null;
	}

	public static List<VerPersonDTO> listVerPersonByOrgOid(Long orgOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select pi.name,pi.person_code,pi.person_oid from yhc_pb_person_info pi");
		sql.append(" where  pi.HIRE_DEPT_OID ="+orgOid);
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<VerPersonDTO> items = new ArrayList<VerPersonDTO>();
			for (Object[] objs : list) 
			{
				VerPersonDTO dto = new VerPersonDTO();
				dto.setPersonCode(objs[1] == null ? null : objs[1].toString());
				dto.setName(objs[0] == null ? null : objs[0].toString());
				dto.setPersonOid(objs[2] == null ? null : Long.valueOf(objs[2].toString()));
				items.add(dto);
			}
			return items;
		}
	 return null;
	}
}

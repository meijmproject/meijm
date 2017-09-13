package com.yh.hr.report.queryhelper;


import java.util.ArrayList;
import java.util.List;

import com.yh.hr.report.utils.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;

import com.yh.hr.report.dto.AllPersonStatisticsDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 全院员工汇总统计queryhelper
 * @author liul
 * @create 2017-03-02
 */
public class AllPersonStatisticsQueryHelper {
	/**
	 * 得到所有需要统计的科室
	 * @param unitKind
	 * @return
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static List<AllPersonStatisticsDTO> getOrg() throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select jcw.waed_type as wardType,juo.org_oid,juo.org_name from yhc_ut_org juo left join yhc_cf_ward jcw on juo.org_oid = jcw.dept_oid and jcw.waed_type in ('"+DicConstants.YHRS0125_1+"','"+DicConstants.YHRS0125_2+"','"+DicConstants.YHRS0125_3+"','"+DicConstants.YHRS0125_4+"')");
		
		sql.append("  union all ");
		sql.append(" select '99' as wardType, juo1.org_oid, juo1.org_name from yhc_ut_org juo1 left join yhc_cf_ward jcw1 on juo1.org_oid = jcw1.dept_oid where juo1.org_oid in");
		sql.append(" (select ju.org_oid from yhc_ut_org juo,yhc_ut_relation jur,yhc_ut_org ju where juo.organization_oid = jur.parent_organization_oid");
		sql.append(" and ju.organization_oid = jur.child_organization_oid and jur.relation_type = '"+UtConstants.UT_ORGANIZATION_TYPE_2+"'");
		sql.append(" and juo.org_oid in ( select t.org_oid from YHC_UT_ORG t where t.org_type in ('"+ Constants.HSPSZHP_ORG_TYPE_7+"') )");
		sql.append(") and (juo1.org_oid not in (select juo.org_oid from yhc_ut_org juo left join yhc_cf_ward jcw on juo.org_oid = jcw.dept_oid and jcw.waed_type in ('"+DicConstants.YHRS0125_1+"','"+DicConstants.YHRS0125_2+"','"+DicConstants.YHRS0125_3+"','"+DicConstants.YHRS0125_4+"')) or jcw1.waed_type is null)");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
			List<AllPersonStatisticsDTO> list = new ArrayList<AllPersonStatisticsDTO>(); 
				for(int i =0;i<objects.size();i++){
					AllPersonStatisticsDTO dto = new AllPersonStatisticsDTO();
					Object[] obj =objects.get(i);
					dto.setWardType(obj[0] == null ? null : String.valueOf(obj[0]));
					dto.setDeptOid(obj[1] == null ? null : new Long(String.valueOf(obj[1])));
					dto.setDeptName(obj[2] == null ? null : String.valueOf(obj[2]));
					list.add(dto);
				}
			return list;
		}
		return null;
	}
	/**
	 * 根据科室查询员工统计数据
	 */
	public static AllPersonStatisticsDTO getAllPersonCountByOrg(AllPersonStatisticsDTO dto) throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct");
		//医师
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as yiszgCount,");//正高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as yisfgCount,");//副高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as yiszjCount,");//中级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppa.prof_tech_level in('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yiscjCount,");//初级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppa.prof_tech_level in ('"+DicConstants.YHRS0047_411+"','"+DicConstants.YHRS0047_412+"','"+DicConstants.YHRS0047_420+"','"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yishjCount,"); //合计
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_1+"' and jppi.person_status = '"+DicConstants.YHRS0009_120+"') as yissxCount,");// 实习
		//技师
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as jiszgCount,");//正高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as jisfgCount,");//副高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as jiszjCount,");//中级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppa.prof_tech_level in('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as jiscjCount,");//初级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppa.prof_tech_level in ('"+DicConstants.YHRS0047_411+"','"+DicConstants.YHRS0047_412+"','"+DicConstants.YHRS0047_420+"','"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as jishjCount,");//合计
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_2+"' and jppi.person_status = '"+DicConstants.YHRS0009_120+"') as jissxCount,");//实习
		//药师
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as yaoszgCount,");//正高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as yaosfgCount,");//副高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as yaoszjCount,");//中级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppa.prof_tech_level in('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yaoscjCount,");//初级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppa.prof_tech_level in ('"+DicConstants.YHRS0047_411+"','"+DicConstants.YHRS0047_412+"','"+DicConstants.YHRS0047_420+"','"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yaoshjCount,");//合计
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_3+"' and jppi.person_status = '"+DicConstants.YHRS0009_120+"') as yaossxCount,");//实习
		//研究员
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_7+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as yjyzgCount,");//正高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_7+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as yjyfgCount,");//副高
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_7+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as yjyzjCount,");//中级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_7+"' and jppa.prof_tech_level in('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yjycjCount,");//初级
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_7+"' and jppa.prof_tech_level in('"+DicConstants.YHRS0047_411+"','"+DicConstants.YHRS0047_412+"','"+DicConstants.YHRS0047_420+"','"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as yjyhjCount,");//合计
		//其他（管理，工勤）
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"')) as qtCount,");//其他
		//非护小计
		buildSql(sql,dto.getDeptOid()); 
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"'))as fhxjCount,");//小计
		//学历
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and (jppa.education_level_code ='"+DicConstants.YHRS0039_10+"')) as fhbsxlCount,");//博士
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and (jppa.education_level_code ='"+DicConstants.YHRS0039_11+"')) as fhssxlCount,");//硕士
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and (jppa.education_level_code ='"+DicConstants.YHRS0039_2+"')) as fhbkxlCount,");//本科
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and (jppa.education_level_code ='"+DicConstants.YHRS0039_3+"')) as fhdzxlCount,");//大专
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and (jppa.education_level_code not in('"+DicConstants.YHRS0039_2+"','"+DicConstants.YHRS0039_3+"','"+DicConstants.YHRS0039_10+"','"+DicConstants.YHRS0039_11+"'))) as fhqtxlCount,");//其他
		//年龄
		sql.append(" (select ROUND(avg(FLOOR(year(now())-year(jppi.birthday) - (DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(jppi.birthday, '%m%d')))),1) from yhc_pb_person_info jppi,yhc_pb_person_attach jppa where jppi.hire_dept_oid = '"+dto.getDeptOid()+"' and jppi.person_oid=jppa.person_oid and jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"') and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and jppi.birthday is not null) as fhnlCount,");//年龄
		//性别
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and jppi.sex_code = '"+DicConstants.YHRS0001_1+"') as fhSex1Count,");//男
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and jppi.sex_code = '"+DicConstants.YHRS0001_2+"') as fhSex2Count,");//女
		//本院规培学员
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_7+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"') and jppi.person_type = '"+DicConstants.YHRS0010_04+"') as fhgpCount,");
		//护士岗位类别 正高 副高 中级 初级 实习 小计
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as hszgCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as hsfgCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppa.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as hszjCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppa.prof_tech_level in ('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as hscjCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppi.person_status = '"+DicConstants.YHRS0009_120+"') as hssxCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type = '"+DicConstants.YHRS0113_4+"' and jppa.prof_tech_level in ('"+DicConstants.YHRS0047_411+"','"+DicConstants.YHRS0047_412+"','"+DicConstants.YHRS0047_420+"','"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as hsxjCount,");
		//护士学历 博士 硕士 本科 大专 其他
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and (jppa.education_level_code ='"+DicConstants.YHRS0039_10+"')) as hsbsxlCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and (jppa.education_level_code ='"+DicConstants.YHRS0039_11+"')) as hsssxlCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and (jppa.education_level_code ='"+DicConstants.YHRS0039_2+"')) as hsbkxlCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and (jppa.education_level_code ='"+DicConstants.YHRS0039_3+"')) as hsdzxlCount,");
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and (jppa.education_level_code not in('"+DicConstants.YHRS0039_2+"','"+DicConstants.YHRS0039_3+"','"+DicConstants.YHRS0039_10+"','"+DicConstants.YHRS0039_11+"'))) as hsqtxlCount,");
		//年龄
		sql.append(" (select ROUND(avg(FLOOR(year(now())-year(jppi.birthday) - (DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(jppi.birthday, '%m%d')))),1) from yhc_pb_person_info jppi,yhc_pb_person_attach jppa where jppi.hire_dept_oid = '"+dto.getDeptOid()+"' and jppi.person_oid=jppa.person_oid and jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"') and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and jppi.birthday is not null) as hsnlCount,");
		//性别
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and jppi.sex_code = '"+DicConstants.YHRS0001_1+"') as hsSex1Count,");//男
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type ='"+DicConstants.YHRS0113_4+"' and jppi.sex_code = '"+DicConstants.YHRS0001_2+"') as hsSex2Count,");//女
		//合计
		buildSql(sql,dto.getDeptOid());
		sql.append(" and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_4+"','"+DicConstants.YHRS0113_5+"','"+DicConstants.YHRS0113_6+"','"+DicConstants.YHRS0113_7+"')) as totalCount,");
		sql.append(" ppi.dept_oid");
		sql.append(" from yhc_pb_person_info ppi,yhc_ut_org juo	where ppi.hire_dept_oid = juo.org_oid");
		sql.append(" and ppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"') and ppi.hire_dept_oid = '"+dto.getDeptOid()+"'");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
					Object[] obj =objects.get(0);
					objToDto(obj,dto);
		}else{
			dto.setFhnlCount("0");
			dto.setHsnlCount("0");
		}
		return dto;
	}
	/**
	 * 拼接查询语句
	 * @param sql
	 * @param deptOid
	 * @return
	 */
	private static StringBuffer buildSql(StringBuffer sql,Long deptOid){
		sql.append(" (select count(1) from yhc_pb_person_info jppi,yhc_pb_person_attach jppa where jppi.hire_dept_oid = '"+deptOid+"' and jppi.person_oid=jppa.person_oid and jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"')");
		return sql;
		
	}
	/**
	 * obj转dto
	 * @param obj
	 * @param dto
	 * @return
	 * @throws DataAccessException
	 */
	private static AllPersonStatisticsDTO objToDto(Object[] obj,AllPersonStatisticsDTO dto) throws DataAccessException{
		dto.setYiszgCount(obj[0]==null?0:new Integer(String.valueOf(obj[0])));
		dto.setYisfgCount(obj[1]==null?0:new Integer(String.valueOf(obj[1])));
		dto.setYiszjCount(obj[2]==null?0:new Integer(String.valueOf(obj[2])));
		dto.setYiscjCount(obj[3]==null?0:new Integer(String.valueOf(obj[3])));
		dto.setYishjCount(obj[4]==null?0:new Integer(String.valueOf(obj[4])));
		dto.setYissxCount(obj[5]==null?0:new Integer(String.valueOf(obj[5])));
		dto.setJiszgCount(obj[6]==null?0:new Integer(String.valueOf(obj[6])));
		dto.setJisfgCount(obj[7]==null?0:new Integer(String.valueOf(obj[7])));
		dto.setJiszjCount(obj[8]==null?0:new Integer(String.valueOf(obj[8])));
		dto.setJiscjCount(obj[9]==null?0:new Integer(String.valueOf(obj[9])));
		dto.setJishjCount(obj[10]==null?0:new Integer(String.valueOf(obj[10])));
		dto.setJissxCount(obj[11]==null?0:new Integer(String.valueOf(obj[11])));
		dto.setYaoszgCount(obj[12]==null?0:new Integer(String.valueOf(obj[12])));
		dto.setYaosfgCount(obj[13]==null?0:new Integer(String.valueOf(obj[13])));
		dto.setYaoszjCount(obj[14]==null?0:new Integer(String.valueOf(obj[14])));
		dto.setYaoscjCount(obj[15]==null?0:new Integer(String.valueOf(obj[15])));
		dto.setYaoshjCount(obj[16]==null?0:new Integer(String.valueOf(obj[16])));
		dto.setYaossxCount(obj[17]==null?0:new Integer(String.valueOf(obj[17])));
		dto.setYjyzgCount(obj[18]==null?0:new Integer(String.valueOf(obj[18])));
		dto.setYjyfgCount(obj[19]==null?0:new Integer(String.valueOf(obj[19])));
		dto.setYjyzjCount(obj[20]==null?0:new Integer(String.valueOf(obj[20])));
		dto.setYjycjCount(obj[21]==null?0:new Integer(String.valueOf(obj[21])));
		dto.setYjyhjCount(obj[22]==null?0:new Integer(String.valueOf(obj[22])));
		dto.setQtCount(obj[23]==null?0:new Integer(String.valueOf(obj[23])));
		dto.setFhxjCount(obj[24]==null?0:new Integer(String.valueOf(obj[24])));
		dto.setFhbsxlCount(obj[25]==null?0:new Integer(String.valueOf(obj[25])));
		dto.setFhssxlCount(obj[26]==null?0:new Integer(String.valueOf(obj[26])));
		dto.setFhbkxlCount(obj[27]==null?0:new Integer(String.valueOf(obj[27])));
		dto.setFhdzxlCount(obj[28]==null?0:new Integer(String.valueOf(obj[28])));
		dto.setFhqtxlCount(obj[29]==null?0:new Integer(String.valueOf(obj[29])));
		dto.setFhnlCount(obj[30]==null?"0":String.valueOf(obj[30]));
		dto.setFhSex1Count(obj[31]==null?0:new Integer(String.valueOf(obj[31])));
		dto.setFhSex2Count(obj[32]==null?0:new Integer(String.valueOf(obj[32])));
		dto.setFhgpCount(obj[33]==null?0:new Integer(String.valueOf(obj[33])));
		dto.setHszgCount(obj[34]==null?0:new Integer(String.valueOf(obj[34])));
		dto.setHsfgCount(obj[35]==null?0:new Integer(String.valueOf(obj[35])));
		dto.setHszjCount(obj[36]==null?0:new Integer(String.valueOf(obj[36])));
		dto.setHscjCount(obj[37]==null?0:new Integer(String.valueOf(obj[37])));
		dto.setHssxCount(obj[38]==null?0:new Integer(String.valueOf(obj[38])));
		dto.setHsxjCount(obj[39]==null?0:new Integer(String.valueOf(obj[39])));
		dto.setHsbsxlCount(obj[40]==null?0:new Integer(String.valueOf(obj[40])));
		dto.setHsssxlCount(obj[41]==null?0:new Integer(String.valueOf(obj[41])));
		dto.setHsbkxlCount(obj[42]==null?0:new Integer(String.valueOf(obj[42])));
		dto.setHsdzxlCount(obj[43]==null?0:new Integer(String.valueOf(obj[43])));
		dto.setHsqtxlCount(obj[44]==null?0:new Integer(String.valueOf(obj[44])));
		dto.setHsnlCount(obj[45]==null?"0":String.valueOf(obj[45]));
		dto.setHsSex1Count(obj[46]==null?0:new Integer(String.valueOf(obj[46])));
		dto.setHsSex2Count(obj[47]==null?0:new Integer(String.valueOf(obj[47])));
		dto.setTotalCount(obj[48]==null?0:new Integer(String.valueOf(obj[48])));
		//dto.setRemark(obj[49]==null?0:new Integer(String.valueOf(obj[49])));
		return dto;
		
	}
}

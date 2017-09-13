package com.yh.hr.report.queryhelper;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;

import com.yh.hr.report.dto.MatronStatisticsDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 科主任及护士长系列人员汇总queryhelper
 * @author liul
 * @create 2016-03-07
 */
public class MatronStatisticsQueryHelper {
	/**
	 * 得到所有科室
	 * @param unitKind
	 * @return
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static List<MatronStatisticsDTO> getOrg() throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select jcw.waed_type,juo.org_oid,juo.org_name from yhc_ut_org juo,yhc_cf_ward jcw where juo.org_oid = jcw.dept_oid(+)");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
			List<MatronStatisticsDTO> list = new ArrayList<MatronStatisticsDTO>(); 
				for(int i =0;i<objects.size();i++){
					MatronStatisticsDTO dto = new MatronStatisticsDTO();
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
	 * 根据科室查询统计数据
	 */
	public static MatronStatisticsDTO getMatronCountByOrg(MatronStatisticsDTO dto) throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		//科主任 正
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_3+"') as kzrzwzCount, ");
		//科主任 正 姓名
		sql.append(" (select REPLACE(wmsys.wm_concat(to_char(t.name)),',',',') from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_3+"') as kzrzwzxmCount, ");
		//科主任 副
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_4+"') as kzrzwfCount, ");
		//科主任 副 姓名
		sql.append(" (select REPLACE(wmsys.wm_concat(to_char(t.name)),',',',') from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_4+"') as kzrzwfxmCount, ");
		//科主任 正高
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as kzrzczgCount, ");
		//科主任 副高
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as kzrzcfgCount, ");
		//科主任 中级
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as kzrzczjCount, ");
		//科主任 初级
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.prof_tech_level in('"+DicConstants.YHRS0047_434+"'，'"+DicConstants.YHRS0047_434+"')) as kzrzccjCount, ");
		//科主任 博士
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_10+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_10+"')) as kzrxlbsCount, ");
		//科主任 硕士
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_11+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_11+"')) as kzrxlssCount, ");
		//科主任 本科
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_2+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_2+"')) as kzrxlbkCount, ");
		//科主任 大专
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_3+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_3+"')) as kzrxldzCount, ");
		//科主任 合计
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where 1=1) as kzrhjCount, ");
		//科主任 男
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.sex_code = '"+DicConstants.YHRS0001_1+"') as kzrSex1Count, ");
		//科主任 女
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.sex_code = '"+DicConstants.YHRS0001_2+"') as kzrSex2Count, ");
		//科主任 平均年龄
		sql.append(" (select ROUND(avg(FLOOR((now()-t.birthday)/365.25)),1) from ");
		buildSql(sql, DicConstants.YHRS0126_3+","+DicConstants.YHRS0126_4, dto);
		sql.append(" where t.birthday is not null) as kzrnlCount, ");
		//护士长 正
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_5+"') as hszzwzCount, ");
		//护士长 正 姓名
		sql.append(" (select REPLACE(wmsys.wm_concat(to_char(t.name)),',',',') from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_5+"') as hszzwzxmCount, ");
		//护士长 副
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_6+"') as hszzwfCount, ");
		//护士长 副 姓名
		sql.append(" (select REPLACE(wmsys.wm_concat(to_char(t.name)),',',',') from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.duty_name='"+DicConstants.YHRS0126_6+"') as hszzwfxmCount, ");
		//护士长 正高
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_411+"') as hszzczgCount, ");
		//护士长 副高
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_412+"') as hszzcfgCount, ");
		//护士长 中级
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.prof_tech_level ='"+DicConstants.YHRS0047_420+"') as hszzczjCount, ");
		//护士长 初级
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.prof_tech_level in('"+DicConstants.YHRS0047_434+"','"+DicConstants.YHRS0047_435+"')) as hszzccjCount, ");
		//护士长 博士
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_10+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_10+"')) as hszxlbsCount, ");
		//护士长 硕士
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_11+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_11+"')) as hszxlssCount, ");
		//护士长 本科
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_2+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_2+"')) as hszxlbkCount, ");
		//护士长 大专
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where (t.ft_education_level_code ='"+DicConstants.YHRS0039_3+"' or t.oj_education_level_code='"+DicConstants.YHRS0039_3+"')) as hszxldzCount, ");
		//护士长 合计
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where 1=1) as hszhjCount, ");
		//护士长 男
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.sex_code = '"+DicConstants.YHRS0001_1+"') as hszSex1Count, ");
		//护士长 女
		sql.append(" (select count(1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.sex_code = '"+DicConstants.YHRS0001_2+"') as hszSex2Count, ");
		//护士长 平均年龄
		sql.append(" (select ROUND(avg(FLOOR((now()-t.birthday)/365.25)),1) from ");
		buildSql(sql, DicConstants.YHRS0126_5+","+DicConstants.YHRS0126_6, dto);
		sql.append(" where t.birthday is not null) as hsznlCount ");
		sql.append(" from dual");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
					Object[] obj =objects.get(0);
					objToDto(obj,dto);
		}else{
			dto.setKzrnlCount("0");
			dto.setHsznlCount("0");
		}
		return dto;
	}
	private static StringBuffer buildSql(StringBuffer sql,String dutyName,MatronStatisticsDTO dto ){
		sql.append("(select distinct jpdi.dept_oid,jppi.name,jpdi.duty_name,jppa.his_position_type,jppa.prof_tech_level,jppa.ft_education_level_code,jppa.oj_education_level_code,jppi.sex_code,jppi.birthday from yhc_pb_duty_info jpdi "); 
        sql.append(" left join yhc_ut_org juo on jpdi.dept_oid = juo.org_oid");
        sql.append(" left join yhc_pb_person_info jppi on jpdi.person_oid = jppi.person_oid");
        sql.append(" left join yhc_pb_person_attach jppa on jpdi.person_oid = jppa.person_oid");
        sql.append(" where jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"')");
        //and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_4+"','"+DicConstants.YHRS0113_7+"')
        sql.append(" and jpdi.duty_status = '"+DicConstants.YHRS0026_001+"' ");
		sql.append(" and juo.org_oid = '"+dto.getDeptOid()+"' ");
		sql.append(" and jpdi.duty_name in ("+dutyName+")) t");//科主任（负责人）或护士长 
		return sql;
		
	}
	/**
	 * obj转dto
	 * @param obj
	 * @param dto
	 * @return
	 * @throws DataAccessException
	 */
	private static MatronStatisticsDTO objToDto(Object[] obj,MatronStatisticsDTO dto) throws DataAccessException{
		dto.setKzrzwzCount(obj[0]==null?0:new Integer(String.valueOf(obj[0])));
		dto.setKzrzwzxmCount(obj[1]==null?"":String.valueOf(obj[1]));
		dto.setKzrzwfCount(obj[2]==null?0:new Integer(String.valueOf(obj[2])));
		dto.setKzrzwfxmCount(obj[3]==null?"":String.valueOf(obj[3]));
		dto.setKzrzczgCount(obj[4]==null?0:new Integer(String.valueOf(obj[4])));
		dto.setKzrzcfgCount(obj[5]==null?0:new Integer(String.valueOf(obj[5])));
		dto.setKzrzczjCount(obj[6]==null?0:new Integer(String.valueOf(obj[6])));
		dto.setKzrzccjCount(obj[7]==null?0:new Integer(String.valueOf(obj[7])));
		dto.setKzrxlbsCount(obj[8]==null?0:new Integer(String.valueOf(obj[8])));
		dto.setKzrxlssCount(obj[9]==null?0:new Integer(String.valueOf(obj[9])));
		dto.setKzrxlbkCount(obj[10]==null?0:new Integer(String.valueOf(obj[10])));
		dto.setKzrxldzCount(obj[11]==null?0:new Integer(String.valueOf(obj[11])));
		dto.setKzrhjCount(obj[12]==null?0:new Integer(String.valueOf(obj[12])));
		dto.setKzrSex1Count(obj[13]==null?0:new Integer(String.valueOf(obj[13])));
		dto.setKzrSex2Count(obj[14]==null?0:new Integer(String.valueOf(obj[14])));
		dto.setKzrnlCount(obj[15]==null?"0":String.valueOf(obj[15]));
		dto.setHszzwzCount(obj[16]==null?0:new Integer(String.valueOf(obj[16])));
		dto.setHszzwzxmCount(obj[17]==null?"":String.valueOf(obj[17]));
		dto.setHszzwfCount(obj[18]==null?0:new Integer(String.valueOf(obj[18])));
		dto.setHszzwfxmCount(obj[19]==null?"":String.valueOf(obj[19]));
		dto.setHszzczgCount(obj[20]==null?0:new Integer(String.valueOf(obj[20])));
		dto.setHszzcfgCount(obj[21]==null?0:new Integer(String.valueOf(obj[21])));
		dto.setHszzczjCount(obj[22]==null?0:new Integer(String.valueOf(obj[22])));
		dto.setHszzccjCount(obj[23]==null?0:new Integer(String.valueOf(obj[23])));
		dto.setHszxlbsCount(obj[24]==null?0:new Integer(String.valueOf(obj[24])));
		dto.setHszxlssCount(obj[25]==null?0:new Integer(String.valueOf(obj[25])));
		dto.setHszxlbkCount(obj[26]==null?0:new Integer(String.valueOf(obj[26])));
		dto.setHszxldzCount(obj[27]==null?0:new Integer(String.valueOf(obj[27])));
		dto.setHszhjCount(obj[28]==null?0:new Integer(String.valueOf(obj[28])));
		dto.setHszSex1Count(obj[29]==null?0:new Integer(String.valueOf(obj[29])));
		dto.setHszSex2Count(obj[30]==null?0:new Integer(String.valueOf(obj[30])));
		dto.setHsznlCount(obj[31]==null?"0":String.valueOf(obj[31]));
		return dto;
		
	}
}

package com.yh.hr.report.queryhelper;


import java.util.ArrayList;
import java.util.List;

import com.yh.hr.report.dto.TechnicalTitleAgeStatisticsDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 卫生技术人员依据职称等级汇总的年龄、性别架构表queryhelper
 * @author liul
 * @create 2016-03-07
 */
public class TechnicalTitleAgeStatisticsQueryHelper {
	/**
	 * 得到所有科室
	 * @param unitKind
	 * @return
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static List<TechnicalTitleAgeStatisticsDTO> getPositionLevel() throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append("select jdi.dic_item_code,jdi.dic_item_name from yha_dic_item jdi,yha_dic_type jdt where jdi.dic_type_oid = jdt.dic_type_oid and jdt.dic_type_code = '"+DicConstants.YHRS0047+"'");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
			List<TechnicalTitleAgeStatisticsDTO> list = new ArrayList<TechnicalTitleAgeStatisticsDTO>(); 
				for(int i =0;i<objects.size();i++){
					TechnicalTitleAgeStatisticsDTO dto = new TechnicalTitleAgeStatisticsDTO();
					Object[] obj =objects.get(i);
					dto.setPositionLevelCode(obj[0] == null ? null : String.valueOf(obj[0]));
					dto.setPositionLevelName(obj[1] == null ? null : String.valueOf(obj[1]));
					list.add(dto);
				}
			return list;
		}
		return null;
	}
	/**
	 * 根据科室查询统计数据
	 */
	public static TechnicalTitleAgeStatisticsDTO getPositionLevelCountByPositionLevel(TechnicalTitleAgeStatisticsDTO dto,String flag) throws DataAccessException, DataAccessFailureException{
		StringBuffer sql = new StringBuffer();
		sql.append("select  ");
		//25岁以下
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age<=25 )as age1,");
		//26-30岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=26 and t.age<=30 )as age2,");
		//31-35岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=31 and t.age<=35 )as age3,");
		//36-40岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=36 and t.age<=40 )as age4,");
		//41-45岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=41 and t.age<=45 )as age5,");
		//46-50岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=46 and t.age<=50 )as age6,");
		//51-55岁
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=51 and t.age<=55 )as age7,");
		//56岁以上
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.age>=56)as age8,");
		//男
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.sex_code='"+DicConstants.YHRS0001_1+"')as sex1,");
		//女
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(" where t.sex_code='"+DicConstants.YHRS0001_2+"')as sex2,");
		//合计
		sql.append(" (select count(1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(")as total,");
		
		//平均年龄（岁）
		sql.append(" (select ROUND(avg(t.age),1) from ");
		buildSql(sql,dto.getPositionLevelCode(),flag);
		sql.append(")as avgAge,");
		//总人数
		sql.append(" (select count(1) from yhc_pb_person_info jppi,yhc_pb_person_attach jppa where ");
		sql.append(" jppi.person_oid = jppa.person_oid and jppi.birthday is not null and jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"') and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_4+"','"+DicConstants.YHRS0113_7+"') ");
		if(StringUtils.isNotEmpty(flag)&&flag.equals("true")){
			sql.append(" and jppa.prof_tech_level is null");
		}else{
			sql.append(" and jppa.prof_tech_level ='"+dto.getPositionLevelCode()+"' ");
		}
		sql.append(") as totalTotal");
		sql.append(" from dual");
		List<Object[]> objects = DaoUtil.findWithSQL(sql.toString());
		if(!CollectionUtils.isEmpty(objects)){
					Object[] obj =objects.get(0);
					objToDto(obj,dto);
		}else{
			dto.setBl(0D);
			dto.setAvgAge("0");
		}
		return dto;
	}
	private static StringBuffer buildSql(StringBuffer sql,String hisPositionLevel,String flag){
		sql.append(" (select FLOOR((now()-jppi.birthday)/365.25) as age,jppi.sex_code,jppi.person_oid from yhc_pb_person_info jppi,yhc_pb_person_attach jppa where ");
		sql.append(" jppi.person_oid = jppa.person_oid and jppi.birthday is not null and jppi.person_status in ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','"+DicConstants.YHRS0009_300+"') and jppi.person_type in ('"+DicConstants.YHRS0010_01+"','"+DicConstants.YHRS0010_02+"','"+DicConstants.YHRS0010_03+"','"+DicConstants.YHRS0010_04+"') and jppa.his_position_type in ('"+DicConstants.YHRS0113_1+"','"+DicConstants.YHRS0113_2+"','"+DicConstants.YHRS0113_3+"','"+DicConstants.YHRS0113_4+"','"+DicConstants.YHRS0113_7+"') ");
		if(StringUtils.isNotEmpty(flag)&&flag.equals("true")){
			sql.append(" and jppa.prof_tech_level is null");
		}else{
			sql.append(" and jppa.prof_tech_level ='"+hisPositionLevel+"' ");
		}
		sql.append(" ) t");
		return sql;
		
	}
	/**
	 * obj转dto
	 * @param obj
	 * @param dto
	 * @return
	 * @throws DataAccessException
	 */
	private static TechnicalTitleAgeStatisticsDTO objToDto(Object[] obj,TechnicalTitleAgeStatisticsDTO dto) throws DataAccessException{
		dto.setAge1(obj[0]==null?0:new Integer(String.valueOf(obj[0])));
		dto.setAge2(obj[1]==null?0:new Integer(String.valueOf(obj[1])));
		dto.setAge3(obj[2]==null?0:new Integer(String.valueOf(obj[2])));
		dto.setAge4(obj[3]==null?0:new Integer(String.valueOf(obj[3])));
		dto.setAge5(obj[4]==null?0:new Integer(String.valueOf(obj[4])));
		dto.setAge6(obj[5]==null?0:new Integer(String.valueOf(obj[5])));
		dto.setAge7(obj[6]==null?0:new Integer(String.valueOf(obj[6])));
		dto.setAge8(obj[7]==null?0:new Integer(String.valueOf(obj[7])));
		dto.setSex1(obj[8]==null?0:new Integer(String.valueOf(obj[8])));
		dto.setSex2(obj[9]==null?0:new Integer(String.valueOf(obj[9])));
		dto.setTotal(obj[10]==null?0:new Integer(String.valueOf(obj[10])));
		dto.setBl((obj[12]==null||new Integer(String.valueOf(obj[12]))==0)?0D:100*dto.getTotal()/new Double(String.valueOf(obj[12])));
		dto.setAvgAge(obj[11]==null?"0":String.valueOf(obj[11]));
		return dto;
		
	}
}

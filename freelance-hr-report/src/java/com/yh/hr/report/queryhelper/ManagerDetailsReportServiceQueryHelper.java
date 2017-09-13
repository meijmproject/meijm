package com.yh.hr.report.queryhelper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.hr.report.dto.ManagerDetailsReportDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

public class ManagerDetailsReportServiceQueryHelper {
	
	/**
	 * 获取医院领导的人员名单
	 * @param ttb
	 * @return 
	 */
	public static List<ManagerDetailsReportDTO> getTopmanagerDetails(TableTagBean ttb) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from (select pi.*, u.unit_name,o.org_name " );
		sql.append("          from yhc_ut_unit u, yhc_pb_person_info pi " );
		sql.append("         left join yhc_ut_org o on pi.HIRE_DEPT_OID = o.org_oid" );
		sql.append("         where pi.unit_oid = u.unit_oid" );
		
		//当前单位oid
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitOid"))) {
			sql.append(" and pi.unit_Oid = ").append(ttb.getCondition().get("unitOid"));
		}
		
		//当前查询的科室
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgOid"))) {
			String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByOid(ttb.getCondition().get("orgOid"));
			sql.append(" and pi.HIRE_DEPT_OID in(").append(orgOidString).append(")");
		}
		

		//查询这些中哪些人是院长,副院长
		sql.append(" and pi.person_oid in ( ");
		sql.append(" select d.person_oid from YHC_PB_SY_GW_EMPLOY_INFO d where 1=1 ");
		sql.append(" and d.positioning_status = '"+DicConstants.YHRS0026_001+"' ");
		sql.append(" and d.duty_attribute in ("+DicConstants.YHRS0028_10+","+DicConstants.YHRS0028_11+") ");
		sql.append(" ) ");
                
		sql.append("        ) p" );
		sql.append("  left join yhc_pb_person_attach pa on (p.person_oid = pa.person_oid)" );
		
		sql.append("  where 1=1 " );
		//人员类别不能是委培人员
		sql.append(" and p.person_type not in ('"+DicConstants.YHRS0010_05+"') and p.person_type is not null "  );
		//人员状态为一般在职，长期病休，保留工作关系的人员
		sql.append(" and p.person_status in ('"+DicConstants.YHRS0009_110+"', '"+DicConstants.YHRS0009_120+"', '"+DicConstants.YHRS0009_130+"', '"+DicConstants.YHRS0009_300+"')" );
		
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select p.person_oid,");
		lsql.append("       pa.HIS_POSITION_NAME, ");//岗位
		lsql.append("       p.name," );//姓名
		lsql.append("       p.birthday, ");//年龄(先获取生日,然后再计算年龄)
		lsql.append("       pa.ft_education_level_code, "); //学历
		lsql.append("       pa.PROF_TECH_NAME ");//职称
		
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by p.person_oid").toString(), params, ttb.getPage(), ttb.getPageSize());

		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], ManagerDetailsReportDTO>() {
			

			public ManagerDetailsReportDTO getValue(Object[] src) throws ServiceException {
				ManagerDetailsReportDTO dto = new ManagerDetailsReportDTO();
				dto.setPersonOid(DataConverUtils.toLong(src[0]));
				dto.setHisPositionName(DataConverUtils.toString(src[1]));//岗位
				dto.setName(DataConverUtils.toString(src[2]));//姓名
				int age;
				try {
					age = getAge(DataConverUtils.toDate(src[3]));
				} catch (Exception e) {
					age = 0;
					e.printStackTrace();
				}
				dto.setAge(String.valueOf(age));//年龄(先获取生日,然后再计算年龄)
				dto.setFtEducationLevelCode(DataConverUtils.toString(src[4])==null? "":DicHelper.viewName(DicConstants.YHRS0039, DataConverUtils.toString(src[4])));//学历
				String profTechName = DicHelper.viewName(DicConstants.YHRS0117, DataConverUtils.toString(src[5]));
				if(DataConverUtils.toString(src[5])!=null&&profTechName==null){
					profTechName = DataConverUtils.toString(src[5]);
				}
				dto.setProfTechName(profTechName);//职称
				return dto;
			}});
	}
	
	/**
	 * 获取医院中层管理人员(各科室负责人)
	 * @throws ServiceException 
	 */
	public static List<ManagerDetailsReportDTO> getMidLevelManager(TableTagBean ttb) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from (select pi.*, u.unit_name,o.org_name " );
		sql.append("          from yhc_ut_unit u, yhc_pb_person_info pi " );
		sql.append("         left join yhc_ut_org o on pi.HIRE_DEPT_OID = o.org_oid" );
		sql.append("         where pi.unit_oid = u.unit_oid" );
		
		//当前单位oid
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitOid"))) {
			sql.append(" and pi.unit_Oid = ").append(ttb.getCondition().get("unitOid"));
		}
		
		//当前查询的科室
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgType"))) {
			String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByType(ttb.getCondition().get("orgType"));
			sql.append(" and pi.HIRE_DEPT_OID in(").append(orgOidString).append(")");
		}


		//查询这些中哪些人是科室的负责人(包括科长,副科长,负责人)
		sql.append(" and pi.person_oid in ( ");
		sql.append(" select d.person_oid from yhc_pb_duty_info d where 1=1 ");
		sql.append(" and d.duty_status = '"+DicConstants.YHRS0026_001+"' ");
		sql.append(" and d.duty_name in ('"+DicConstants.YHRS0126_1+"','"+DicConstants.YHRS0126_2+"','"+DicConstants.YHRS0126_5+"') ");
		sql.append(" ) ");
                
		sql.append("        ) p" );
		sql.append("  left join yhc_pb_person_attach pa on (p.person_oid = pa.person_oid)" );
		
		sql.append("  where 1=1 " );
		
		//人员类别不能是委培人员
		sql.append(" and p.person_type not in ('"+DicConstants.YHRS0010_05+"') and p.person_type is not null "  );
		//人员状态为一般在职，长期病休，保留工作关系的人员
		sql.append(" and p.person_status in ('"+DicConstants.YHRS0009_110+"', '"+DicConstants.YHRS0009_120+"', '"+DicConstants.YHRS0009_130+"', '"+DicConstants.YHRS0009_300+"')" );
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select p.person_oid,");
		lsql.append("       pa.HIS_POSITION_NAME, ");//岗位
		lsql.append("       p.name," );//姓名
		lsql.append("       p.birthday, ");//年龄(先获取生日,然后再计算年龄)
		lsql.append("       pa.ft_education_level_code, "); //学历
		lsql.append("       pa.PROF_TECH_NAME ");//职称
		
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by p.person_oid").toString(), params, ttb.getPage(), ttb.getPageSize());
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], ManagerDetailsReportDTO>() {

			public ManagerDetailsReportDTO getValue(Object[] src) throws ServiceException {
				ManagerDetailsReportDTO dto = new ManagerDetailsReportDTO();
				
				dto.setPersonOid(DataConverUtils.toLong(src[0]));
				dto.setHisPositionName(DataConverUtils.toString(src[1]));//岗位
				dto.setName(DataConverUtils.toString(src[2]));//姓名
				int age;
				try {
					age = getAge(DataConverUtils.toDate(src[3]));
				} catch (Exception e) {
					age = 0;
					e.printStackTrace();
				}
				dto.setAge(String.valueOf(age));//年龄(先获取生日,然后再计算年龄)
				dto.setFtEducationLevelCode(DataConverUtils.toString(src[4])==null? "":DicHelper.viewName(DicConstants.YHRS0039, DataConverUtils.toString(src[4])));//学历
				String profTechName = DicHelper.viewName(DicConstants.YHRS0117, DataConverUtils.toString(src[5]));
				if(DataConverUtils.toString(src[5])!=null&&profTechName==null){
					profTechName = DataConverUtils.toString(src[5]);
				}
				dto.setProfTechName(profTechName);//职称

				return dto;
			}});
	}
	
	
	
	//由出生日期获得年龄  
    public static  int getAge(Date birthDay) throws Exception {  
        if(birthDay==null){//如果生日为空的话
        	return 0;
        }
    	Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }  
	

}

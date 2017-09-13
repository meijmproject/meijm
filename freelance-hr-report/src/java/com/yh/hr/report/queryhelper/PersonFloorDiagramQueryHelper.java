package com.yh.hr.report.queryhelper;

import java.util.HashMap;
import java.util.Map;

import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.hr.report.utils.Constants;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 全院人员框架图查询服务类
 * @author chenp
 * 2017-3-2
 */
public class PersonFloorDiagramQueryHelper {
   
	/**
	 * 按照病区类别和岗位类别查询各科室人数
	 * @param waedType
	 * @param positionType
	 * @param flag(若为‘1’则统计研究人员，若为‘66’则统计药剂科人员,若为‘7’则统计行政后勤人员 为空 则统计除开这几类人员的其他人员)
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static int getOfficePersonNumByCond(String waedType,
			String positionType, String flag) throws DataAccessFailureException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)" );
		sql.append(" from yhc_pb_person_info jppi  where 1=1  " );
		//人员类别不能是委培人员
		sql.append(" and jppi.person_type not in ('"+DicConstants.YHRS0010_05+"') and jppi.person_type is not null "  );
		//人员状态为一般在职，长期病休，保留工作关系的人员
		sql.append(" and jppi.person_status in ('110', '120', '130', '300')" );
		sql.append(" and jppi.person_oid in(" );
		sql.append(" select jppa.PERSON_OID from yhc_pb_person_attach jppa " );
		sql.append(" where jppa.his_position_type = '"+positionType+"') ");
		
		if(Constants.YJRY.equals(flag))
		{
			
		}
		else if(Constants.HSPSZHP_ORG_TYPE_7.equals(flag)||Constants.HSPSZHP_ORG_TYPE_66.equals(flag))
		{
			String orgOidSql = "select group_concat(t.org_oid) from YHC_UT_ORG t where t.org_type in ("+flag+" )";
			String orgOidStr = DaoUtil.uniqueResultWithSQL(orgOidSql);
			String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByOid(orgOidStr);
			sql.append(" and jppi.HIRE_DEPT_OID in(").append(orgOidString).append(")");
		}
		else
		{
			sql.append(" and jppi.hire_dept_oid in " );
			sql.append("(select jcw.DEPT_OID  from yhc_cf_ward jcw" );
			sql.append(" where jcw.waed_type= '"+waedType+"') ");
		}
		return DaoUtil.countWithSQLByCondition(sql.toString(), params);
	}

}

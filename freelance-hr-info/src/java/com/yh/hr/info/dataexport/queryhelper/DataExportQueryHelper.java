package com.yh.hr.info.dataexport.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

public class DataExportQueryHelper {


	/**
	 * 联表查询部门
	 * @param field
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<Object> findDept(String field) throws DataAccessFailureException {
		
		    Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		String sqlField = " pi."+field;
		sql.append(" select uo.org_name");
		sql.append(" from yhc_pb_person_info pi");
		sql.append(" left join yhc_ut_org uo");
		sql.append(" on uo.org_oid = ");
		sql.append(sqlField);
		List<Object> list = DaoUtil.listWithSQLByCondition(sql.toString(), params, 0, 0);
		return list;
	}

	/**
	 * 联表查询单位
	 * @param field
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<Object> findUnit(String field) throws DataAccessFailureException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		String sqlField = " pi."+field;
		sql.append(" select uu.unit_name");
		sql.append(" from yhc_pb_person_info pi");
		sql.append(" left join YHC_UT_UNIT uu");
		sql.append(" on uu.unit_oid = ");
		sql.append(sqlField);
		List<Object> list = DaoUtil.listWithSQLByCondition(sql.toString(), params, 0, 0);
		return list;
	}

	/**
	 * 通过数据字典转码获取值
	 * @param field
	 * @param transFlag
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<Object> findByDic(String field, String transFlag) throws DataAccessFailureException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		String sqlField = " pi."+field;
		sql.append("select rel.view_name from yhc_pb_person_info api left join");
		sql.append(" (select pi.person_oid ,dic.view_name");
		sql.append(" from (select di.dic_item_code, di.view_name");
		sql.append(" from YHA_DIC_TYPE dt");
		sql.append(" left join yha_dic_item di");
		sql.append(" on dt.dic_type_oid = di.dic_type_oid");
		sql.append(" where dt.dic_type_code = :dicCode) dic,");
		sql.append(" yhc_pb_person_info pi");
		sql.append(" where dic.dic_item_code = ");
		sql.append(sqlField);
		sql.append(" ) rel");
		sql.append(" on api.person_oid=rel.person_oid");
		sql.append(" order by api.person_oid");
		params.put("dicCode", transFlag);
		List<Object> list = DaoUtil.listWithSQLByCondition(sql.toString(), params, 0, 0);
		return list;
	}

	/**
	 * 从人员表中查询值
	 * @param field
	 * @param transFlag
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<Object> findByPersonInfo(String field) throws DataAccessFailureException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		
		String sqlField = " pi."+field;
		
		sql.append(" select " );
		sql.append(sqlField);
		sql.append(" from yhc_pb_person_info pi" );
		sql.append(" where 1=1 " );
		List<Object> list = DaoUtil.listWithSQLByCondition(sql.toString(), params, 0, 0);
		return list;
	}
	
}

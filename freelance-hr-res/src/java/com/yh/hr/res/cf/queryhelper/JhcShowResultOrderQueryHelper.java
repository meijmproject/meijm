package com.yh.hr.res.cf.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class JhcShowResultOrderQueryHelper {

	/**
	 * 查询用户保存的排序
	 * @param userId
	 * @param functionCode
	 * @return
	 * @throws ServiceException 
	 */
	public static List<Map<String, String>> find(String userId, String functionCode) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select jcsrs.RESULT_OID," +
				"jcsrs.FUNCTION_CODE," +
				"jcsrs.LABEL_NAME," +
				"jcsrs.LABEL_VALUE," +
				"jcsrs.LABEL_WIDTH," +
				"jcsro.RESULT_ORDER," +
				"jcsro.FIELD_ORDER," +
				"jcsrs.DATABASE_FIELD " +
				" from YHC_CF_SHOW_RESULT_ORDER jcsro, YHC_CF_SHOW_RESULT_SET jcsrs " +
				" where 1=1 and jcsro.RESULT_OID=jcsrs.RESULT_OID" +
				" and jcsro.user_Id = '"+userId+"' " +
				" and jcsrs.function_Code = '"+functionCode+"' order by jcsro.FIELD_ORDER");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return build(list);
	}
	
	private static List<Map<String,String>> build(List<Object[]> list) {
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		for(Object[] obj: list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("resultOid", obj[0]!=null?obj[0].toString():"");
			map.put("functionCode", obj[1]!=null?obj[1].toString():"");
			map.put("header", obj[2]!=null?obj[2].toString():"");
			map.put("field", obj[3]!=null?obj[3].toString():"");
			map.put("width", obj[4]!=null?obj[4].toString():"");
			map.put("sort", obj[5]!=null?obj[5].toString():"");
			map.put("index", obj[6]!=null?obj[6].toString():"");
			map.put("databaseField", obj[7]!=null?obj[7].toString():"");
			resultList.add(map);
		}
		return resultList;
	}
	
	/**
	 * 根据resultOid，userId删除
	 * @param dto
	 * @throws ServiceException 
	 */
	public static void deleteByUserAndResult(String userId, String functionCode) throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("delete JhcCfShowResultOrder jcsro where jcsro.userId = '"+userId+"' " +
				" and resultOid in (select resultOid from JhcCfShowResultSet where functionCode='"+functionCode+"'))");
		DaoUtil.executeUpdate(hql.toString());
	}

}

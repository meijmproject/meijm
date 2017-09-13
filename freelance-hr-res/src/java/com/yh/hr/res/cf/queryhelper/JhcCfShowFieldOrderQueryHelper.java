package com.yh.hr.res.cf.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowFieldOrderDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

public class JhcCfShowFieldOrderQueryHelper {

	/**
	 * 查找用户设置的表格列
	 * @param userId
	 * @param functionCode
	 * @return
	 * @throws ServiceException 
	 * @throws DataAccessFailureException 
	 */
	public static List<Map<String,String>> find(String userId,String functionCode) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select jcsrs.RESULT_OID," +
				"jcsrs.FUNCTION_CODE," +
				"jcsrs.LABEL_NAME," +
				"jcsrs.LABEL_VALUE," +
				"jcsrs.LABEL_WIDTH," +
				"jcsfo.FIELD_ORDER," +
				"jcsfo.IS_SHOW," +
				"jcsrs.DATABASE_FIELD " +
				" from YHC_CF_SHOW_FIELD_ORDER jcsfo, YHC_CF_SHOW_RESULT_SET jcsrs " +
				" where 1=1 and jcsfo.RESULT_OID=jcsrs.RESULT_OID" +
				" and jcsfo.user_Id = '"+userId+"' " +
				" and jcsrs.function_Code = '"+functionCode+"' order by jcsfo.FIELD_ORDER");
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
			map.put("index", obj[5]!=null?obj[5].toString():"");
			map.put("isShow", obj[6]!=null?obj[6].toString():"");
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
	public static void deleteByUserAndResult(JhcCfShowFieldOrderDTO dto) throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("delete JhcCfShowFieldOrder jcsfo" +
				" where jcsfo.userId = '"+dto.getUserId()+"' " +
				" and jcsfo.resultOid = '"+dto.getResultOid()+"' ");
		DaoUtil.executeUpdate(hql.toString());
	}

}

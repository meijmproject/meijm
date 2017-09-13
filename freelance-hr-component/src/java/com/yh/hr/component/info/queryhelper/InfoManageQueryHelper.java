package com.yh.hr.component.info.queryhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.cf.dto.JhcCfQueryConditionDTO;
import com.yh.hr.res.cf.dto.JhcCfQuerySymbolDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class InfoManageQueryHelper {

	public static List<JhcCfQueryConditionDTO> listInfoCondition(TableTagBean ttb) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select jcqc.condition_name," +
				"jcqc.condition_code," +
				"jcqc.condition_type," +
				"jcqc.dic_type," +
				"jcqc.database_field," +
				"jcqc.query_condition_oid" +
				" from yhc_cf_query_condition jcqc where 1=1 ");
		String functionCode = ttb.getCondition().getAsStringEmptyNull("functionCode");
        if (functionCode != null){
        	sql.append(" and jcqc.FUNCTION_CODE = '"+functionCode+"'");
        }
        sql.append(" order by order_no");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<JhcCfQueryConditionDTO> items = new ArrayList<JhcCfQueryConditionDTO>();
			
			for (Object[] objs : list) 
			{
				JhcCfQueryConditionDTO dto = new JhcCfQueryConditionDTO();
				dto.setConditionName(objs[0] == null ? null : objs[0].toString());
				dto.setConditionCode(objs[1] == null ? null : objs[1].toString());
				dto.setConditionType(objs[2] == null ? null : objs[2].toString());
				dto.setDicType(objs[3] == null ? null : objs[3].toString());
				dto.setDatabaseField(objs[4] == null ? null : objs[4].toString());
				dto.setQueryConditionOid(objs[5] == null ? null : Long.valueOf(objs[5].toString()));
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	/**
	 * 查询符号数组
	 * @param queryConditionOid
	 * @return
	 * @throws ServiceException 
	 */
	public static List<JhcCfQuerySymbolDTO> listSymbolByCondition(Long queryConditionOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select jcqs.SYMBOL_OID," +
				"jcqs.SYMBOL_NAME," +
				"jcqs.SYMBOL_VALUE" +
				" from yhc_cf_query_symbol jcqs,yhc_cf_query_condition_sym jcqcs" +
				" where jcqs.symbol_oid=jcqcs.symbol_oid" +
				" and jcqcs.query_condition_oid = '"+queryConditionOid+"'");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<JhcCfQuerySymbolDTO> items = new ArrayList<JhcCfQuerySymbolDTO>();
			for (Object[] objs : list) {
				JhcCfQuerySymbolDTO dto = new JhcCfQuerySymbolDTO();
				dto.setSymbolOid(objs[0] == null ? null : Long.valueOf(objs[0].toString()));
				dto.setSymbolName(objs[1] == null ? null : objs[1].toString());
				dto.setSymbolValue(objs[2] == null ? null : objs[2].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}

}

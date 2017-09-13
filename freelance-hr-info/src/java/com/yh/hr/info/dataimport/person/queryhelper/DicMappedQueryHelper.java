package com.yh.hr.info.dataimport.person.queryhelper;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class DicMappedQueryHelper {
	/*
	 * 查询出字典未映射的代办数量
	 */
	public static List<JSONObject> listNoDicMapped() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT jicd.TEMPLATE_COLL_NAME,(select COUNT(*) FROM yhc_im_dic_item_mapping jidim where jidtm.DIC_TYPE_MAPPING_OID = jidim.DIC_TYPE_MAPPING_OID AND jidim.IS_CREATE_MAPPING = 0 ),jidtm.DIC_TYPE_CODE,jidtm.DIC_TYPE_MAPPING_OID");
		sql.append(" FROM yhc_im_check_definition jicd, yhc_im_dic_type_mapping jidtm ");
		sql.append("  WHERE jicd.CHECK_TYPE = '4' AND jicd.DATABASE_COLUMN_CODE = jidtm.DATABASE_COLUMN_CODE AND jicd.EFFECTIVE_FLAG = '1' ");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		if(CollectionUtils.isNotEmpty(list)){
			for(Object[] obj : list){
				JSONObject json = new JSONObject();
				json.put("name",obj[0]==null?null:obj[0].toString());
				json.put("count",obj[1]==null?null:obj[1].toString());
				json.put("dicTypeCode",obj[2]==null?null:obj[2].toString());
				json.put("dicTypeMappingOid",obj[3]==null?null:obj[3].toString());
				listJson.add(json);
			}
		}
		return listJson;
	}
}

package com.yh.hr.info.bizdocument.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class BizDocumentQueryHelper {

	public static List<JSONObject> listBizDocument(TableTagBean ttb) throws ServiceException{

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();
		buildSQL(ttb,sql,params);

		StringBuilder lsql = new StringBuilder();

		//人员基本信息
		lsql.append(" select unf.REF_DESC     	");
		lsql.append("       ,ufd.FILE_NAME   	");
		lsql.append("       ,ufd.CREATED_BY_NAME");
		lsql.append("       ,ufd.CREATED_DATE   ");
		lsql.append("       ,ufd.FILE_OID   ");

		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) from(").append(lsql).append(sql).append(") t").toString(), params));
		}

		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder().append(lsql).append(sql).append(" order by ufd.CREATED_DATE desc").toString(), params, ttb.getPage(), ttb.getPageSize());

		return buildJSON(list);
	}
	
	
	//构建json对象
	private static List<JSONObject> buildJSON(List<Object[]> list) throws ServiceException
	{
		List<JSONObject> dtoList = new ArrayList<JSONObject>();
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)){return dtoList;}
		for (int i = 0; i < list.size(); i++)
		{
			Object[] task = list.get(i);

			String refDesc = task[0] == null ? null:task[0].toString();
			String fileName = task[1] == null ? null: task[1].toString();
			String createBy = task[2] == null ? null:task[2].toString();
			String createDate = task[3] == null ? null:task[3].toString();
			String fileOid = task[4] == null ? null: task[4].toString();
			JSONObject obj = new JSONObject();

			obj.put("refDesc",refDesc);
			obj.put("fileName",fileName);
			obj.put("createBy",createBy);
			obj.put("createDate",createDate);
			obj.put("fileOid",fileOid);
			dtoList.add(obj);
		}
		return dtoList;
	}

	//构建列表查询语句
	public static void buildSQL(TableTagBean ttb, StringBuilder sql, Map<String, Object> params) throws ServiceException {
		sql.append(" from yha_upload_file uf,yha_upload_file_detail ufd,yha_upload_node_ref unf where 1 = 1 " );
		sql.append(" and uf.REF_CODE=unf.REF_CODE and ufd.UPLOAD_FILE_OID=uf.UPLOAD_FILE_OID and unf.UPLOAD_NODE_CODE ='bizdocument' and  unf.REF_CODE != 'M22160'" );
		
		//业务类型
		if (StringUtils.isNotEmpty(ttb.getCondition().get("itemCode"))) {
			sql.append(" and unf.ref_code ='").append(ttb.getCondition().get("itemCode")).append("'");
		}
		//文档名称
		if (StringUtils.isNotEmpty(ttb.getCondition().get("fileName"))) {
			sql.append(" and ufd.file_name like '%").append(ttb.getCondition().get("fileName")).append("%'");
		}
	}

}

package com.yh.hr.component.gb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * @author cheny 2017-03-28
 */
public class GbDescriptionQueryHelper {
	// 获取页面列表数据
	public static List<JSONObject> listGbDescription(TableTagBean ttb) throws ServiceException {

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();
		buildSQL(ttb, sql, params);
		StringBuilder lsql = new StringBuilder();
		lsql.append("select jgd.unit_oid,");
		lsql.append("       jgd.post_name,");
		lsql.append("       jgd.post_type,");
		lsql.append("       jgd.post_level,");
		lsql.append("       jgd.post_duty,");
		lsql.append("       jgd.post_condition,");
		lsql.append("       jgd.post_assigment,");
		lsql.append("       jgd.assessment_criteria,");
		lsql.append("       juu.unit_name,");
		lsql.append("       jgd.yhg_gb_description_oid");
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) from(").append(lsql).append(sql).append(") t").toString(), params));
		}
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder().append("select * from(").append(lsql).append(sql).append(") t order by post_level").toString(), params, ttb.getPage(), ttb.getPageSize());

		return buildJSON(list);
	}

	// 构建json对象
	private static List<JSONObject> buildJSON(List<Object[]> list) throws ServiceException {
		List<JSONObject> dtoList = new ArrayList<JSONObject>();
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return dtoList;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] task = list.get(i);

			Long unitOid = task[0] == null ? null:Long.valueOf(task[0].toString());
			String postName = task[1] == null ? null:task[1].toString();
			String postType = task[2] == null ? null: task[2].toString();
			String postLevel = task[3] == null ? null:task[3].toString();
			String postDuty = task[4] == null ? null:task[4].toString();
			String postCondition = task[5] == null ? null:task[5].toString();
			String postAssigment = task[6] == null ? null:task[6].toString();
			String assessmentCriteria = task[7] == null ? null:task[7].toString();
			String unitName = task[8] == null ? null:task[8].toString();
			Long jhgGbDescriptionOid = task[9] == null ? null:Long.valueOf(task[9].toString());
			
			
			JSONObject obj = new JSONObject();

			obj.put("unitOid",unitOid);
			obj.put("postName",postName);
			obj.put("postType",postType);
			obj.put("postLevel",postLevel);
			obj.put("postTypeName", DicHelper.viewName(DicConstants.YHRS0022,postType));
			obj.put("postLevelName", DicHelper.viewName(DicConstants.YHRS0023,postLevel));
			obj.put("postDuty",postDuty);
			obj.put("postCondition",postCondition);
			obj.put("postAssigment",postAssigment);
			obj.put("assessmentCriteria",assessmentCriteria);
			obj.put("unitName",unitName);
			obj.put("jhgGbDescriptionOid",jhgGbDescriptionOid);
			dtoList.add(obj);
		}
		return dtoList;
	}

	// 构建列表查询语句
	public static void buildSQL(TableTagBean ttb, StringBuilder sql, Map<String, Object> params) throws ServiceException {
		sql.append(" from yhg_gb_description jgd, yhc_ut_unit juu");
		sql.append("  where juu.unit_oid = jgd.unit_oid");
		if (StringUtils.isNotEmpty(ttb.getCondition().get("postType"))) {
			sql.append(" and jgd.post_type = '").append(ttb.getCondition().get("postType")+ "'");
		}
		if (StringUtils.isNotEmpty(ttb.getCondition().get("postLevel"))) {
			sql.append(" and jgd.post_level ='").append(ttb.getCondition().get("postLevel")).append("'");
		}
		if (StringUtils.isNotEmpty(ttb.getCondition().get("postName"))) {
			sql.append(" and jgd.post_name like '%").append(ttb.getCondition().get("postName")).append("%'");
		}
	}
	 public static UtUnitDTO findUnit() throws ServiceException {
			StringBuffer sql = new StringBuffer();
			sql.append(" select juu.unit_name,juu.unit_oid from yhc_ut_unit juu ");
			List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
			if (CollectionUtils.isNotEmpty(list)) 
			{
				
				for (Object[] objs : list) 
				{
					UtUnitDTO dto = new UtUnitDTO();
					dto.setUnitName(objs[0] == null ? null : objs[0].toString());
					dto.setUnitOid(objs[1] == null ? null : Long.valueOf(objs[1].toString()));
					return dto;
				}
			}
			return null;
		}
}

package com.yh.hr.component.gb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.component.gb.bo.GBPlan;
import com.yh.hr.component.gb.dto.GBPlanDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class GBPlanQueryHelper {

	public static List<GBPlanDTO> listInfoCondition(TableTagBean ttb) throws ServiceException {
		StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        buildHQL(ttb.getCondition(), hql, hqlParams);
        List<GBPlan> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
        List<GBPlanDTO> dtoList = new ArrayList<GBPlanDTO>();
		if(!CollectionUtils.isEmpty(list))
		{
			for(GBPlan bo : list)
			{
				GBPlanDTO dto = new GBPlanDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return dtoList;
	}

	private static void buildHQL(StringMap condition, StringBuilder hql,
			HashMap<String, Object> hqlParams) {
		hql.append(" from GBPlan ");
	}

	/**
	 * 通过单位oid删除
	 * @param l
	 */
	public static void deleteByUnitOid(Long unitOid) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		hql.append("delete from GBPlan where unitOid = "+unitOid);
		DaoUtil.executeUpdate(hql.toString());
	}

	@SuppressWarnings("unchecked")
	public static List<JSONObject> listGBPlanWorking() throws ServiceException{
		StringBuilder sql =  new StringBuilder();
		JSONArray arr = new JSONArray();
		sql.append(" SELECT jps.POSITION_TYPE,jps.POSITION_LEVEL,count(*) FROM yhc_pb_sy_gw_employ_info jps where jps.POSITIONING_STATUS='001' GROUP  BY jps.POSITION_TYPE,jps.POSITION_LEVEL");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if(CollectionUtils.isNotEmpty(list)){
			for(Object[] obj:list){
				JSONObject json = new JSONObject();
				json.put("level",obj[1]);
				json.put("count",obj[2]);
				arr.add(json);
			}
		}
		return arr;
	}
	
	public static int listGBPlanTwoWorking() throws ServiceException{
		StringBuilder sql =  new StringBuilder();
		sql.append("SELECT gei.person_oid,COUNT(*) FROM yhc_pb_sy_gw_employ_info gei where gei.POSITIONING_STATUS='001' GROUP BY gei.person_oid HAVING count(*)>1");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return list.size();
	}

	public static JSONObject listGBPlanWorkings() throws ServiceException {
		StringBuilder sql =  new StringBuilder();
		sql.append(" SELECT jps.POSITION_TYPE,jps.POSITION_LEVEL,count(*) FROM yhc_pb_sy_gw_employ_info jps where jps.POSITIONING_STATUS='001' GROUP  BY jps.POSITION_TYPE,jps.POSITION_LEVEL");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		JSONObject json = new JSONObject();
		if(CollectionUtils.isNotEmpty(list)){
			for(Object[] obj:list){
				json.put(obj[1],obj[2]);
			}
		}
		return json;
	}

}

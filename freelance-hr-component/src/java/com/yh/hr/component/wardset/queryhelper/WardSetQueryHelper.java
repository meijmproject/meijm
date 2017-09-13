package com.yh.hr.component.wardset.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.wardset.dto.CfWardDto;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class WardSetQueryHelper {

	/**
	 * 查询病区表列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<CfWardDto> find(TableTagBean ttb) throws ServiceException {
		StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        buildHQL(ttb.getCondition(), hql, hqlParams);
        String order = ttb.getOrderBy();
        if (order != null) {
            if (ttb.getAsc()) {
                hql.append(" order by " + order + " asc");
            } else {
                hql.append(" order by " + order + " desc");
            }
        }
        List<CfWardDto> list = BeanHelper.copyProperties(DaoUtil.listByCondition("select c "+hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize()),CfWardDto.class);
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*)"+hql+"", hqlParams));
        return list;
	}
	
	/**
	 * 构建hql
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
	public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
		hql.append(" from CfWard c,UtOrg u where 1=1 and c.deptOid=u.orgOid ");
		String deptName = params.getAsStringEmptyNull("searchDeptName");
        String deptOid = params.getAsStringEmptyNull("deptOid");
        String waedType = params.getAsStringEmptyNull("waedType");
        if (deptName != null){
        	hql.append(" and u.orgName like :deptName");
            hqlParams.put("deptName", "%"+deptName+"%");
        }
        if (deptOid != null){
    		hql.append(" and c.deptOid = :deptOid");
            hqlParams.put("deptOid", Long.valueOf(deptOid));
        }
        if (waedType != null){
            hql.append(" and c.waedType = :waedType");
            hqlParams.put("waedType", waedType);
        }
	}
	/**
	 * 获取《医院各病区卫技人员配备情况一览表》的数据
	 * @throws ServiceException
	 */
	public static List<Map<String,String>> findWardCollection() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.dept_oid," +
				"a.org_name," +
				"max(a.bed_num) bed_num," +
				"sum(case when jpygei.his_position_type=1 then 1 else 0 end) as yisheng," +
				"sum(case when jpygei.his_position_type=2 then 1 else 0 end) as jishi," +
				"sum(case when jpygei.his_position_type=3 then 1 else 0 end) as yaoshi," +
				"sum(case when jpygei.his_position_type=4 then 1 else 0 end) as hushi" +
				" from (select" +
				" jcw.dept_oid," +
				" juo.org_name," +
				" jcw.bed_num," +
				" jppi.person_oid from YHC_UT_ORG juo,YHC_CF_WARD jcw" +
				//" left join YHC_UT_ORG juo on juo.org_oid=jcw.dept_oid" +
				" left join yhc_pb_person_info jppi on jppi.HIRE_DEPT_OID=jcw.dept_oid and jppi.person_status in " +
				" ('"+DicConstants.YHRS0009_110+"','"+DicConstants.YHRS0009_120+"','"+DicConstants.YHRS0009_130+"','300') " +
				"  where jcw.WAED_TYPE = '"+DicConstants.YHRS0125_5+"'"+" and juo.org_oid=jcw.dept_oid) a" +
				" left join yhc_pb_yn_gw_employ_info jpygei" +
				" on a.person_oid=jpygei.person_oid and jpygei.his_position_status = '001'" +
				" group by a.dept_oid,a.org_name");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return mappingResult(list);
	}
	
	/**
	 * 查询结果标识化
	 * @param list
	 * @return
	 */
	public static List<Map<String,String>> mappingResult(List<Object[]> list) {
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		for(Object[] obj: list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("deptOid", obj[0]!=null?obj[0].toString():"");
			map.put("deptName", obj[1]!=null?obj[1].toString():"");
			map.put("bedNum", obj[2]!=null?obj[2].toString():"");
			map.put("yisheng", obj[3]!=null?obj[3].toString():"");
			map.put("jishi", obj[4]!=null?obj[4].toString():"");
			map.put("yaoshi", obj[5]!=null?obj[5].toString():"");
			map.put("hushi", obj[6]!=null?obj[6].toString():"");
			resultList.add(map);
		}
		return resultList;
	}
}

/**
 * 
 */
package com.yh.admin.oplog.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;


/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/26
 */

public class OpLogQueryHelper {

	public static OpLogDTO getLastOpLog(String userId, String systemCode, String functionCode) throws ServiceException {
		
		if (StringUtils.isEmpty(userId)) return null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select log_oid,");
		sql.append("       user_id,");
		sql.append("       user_name,");
		sql.append("       system_code,");
		sql.append("       system_name,");
		sql.append("       DATE_FORMAT(log_date,'%Y-%m-%d  %H:%i:%s'),");
		sql.append("       ip_address,");
		sql.append("       function_code,");
		sql.append("       function_name");
		sql.append("  from yhb_op_log l");
		sql.append("  where user_id = '").append(userId).append("' ");
		
		if (StringUtils.isNotEmpty(systemCode)) {
			sql.append("  and system_code = '").append(systemCode).append("' ");
		}
		if (StringUtils.isNotEmpty(functionCode)) {
			sql.append("  and function_Code = '").append(functionCode).append("' ");
		}
		
		sql.append("   order by system_code,function_Code, log_date desc ");

		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		
		if (CollectionUtils.isEmpty(list)) return null;
		
		Object[] objs = list.get(0);
		
		OpLogDTO dto = new OpLogDTO();
		
		dto.setLogOid(DataConverUtils.toLong(objs[0]));
		dto.setUserId(DataConverUtils.toString(objs[1]));
		dto.setUserName(DataConverUtils.toString(objs[2]));
		dto.setSystemCode(DataConverUtils.toString(objs[3]));
		dto.setSystemName(DataConverUtils.toString(objs[4]));
		dto.setLogDate(DataConverUtils.toDate(objs[5]));
		dto.setIpAddress(DataConverUtils.toString(objs[6]));
		dto.setFunctionCode(DataConverUtils.toString(objs[7]));
		dto.setFunctionName(DataConverUtils.toString(objs[8]));
		
		return dto;
	}

	public static List<OpLogDTO> listOpLog(TableTagBean ttb) throws ServiceException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String endDate =ttb.getCondition().get("endDate");
		String beginDate =ttb.getCondition().get("beginDate");
		StringBuilder sql = new StringBuilder();
		sql.append(" from OpLog o where 1=1 ");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("userName"))) {
			sql.append(" and o.userName like '%")
			.append(ttb.getCondition().get("userName")).append("%' ");
		}
		if (StringUtils.isNotEmpty(beginDate)) {
			sql.append(" and o.logDate >= str_to_date('").append(beginDate).append("' ,'%Y-%m-%d')");
//			sql.append(" and o.logDate >=:beginDate ");
//			params.put("beginDate", DateUtil.parse(beginDate, DateUtil.DATE_PATTERN_DEFAULT));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			sql.append(" and o.logDate <= str_to_date('").append(endDate).append("' ,'%Y-%m-%d')+1");
//			sql.append(" and o.logDate <:endDate ");
//			params.put("endDate", DateUtil.parse(endDate, DateUtil.DATE_PATTERN_DEFAULT));2017-12-12
		}
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countByCondition(
					new StringBuilder().append("select count(*) ").append(sql)
							.toString(), params));
		}
		
		return BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(sql)
						.append(" order by o.logDate desc").toString(), params,
				ttb.getPage(), ttb.getPageSize()), OpLogDTO.class);
	}
}

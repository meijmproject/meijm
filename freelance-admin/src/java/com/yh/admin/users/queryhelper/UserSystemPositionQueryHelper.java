/**
 * 
 */
package com.yh.admin.users.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.admin.bo.UserSystemPosition;
import com.yh.admin.dto.UserPositionInfoDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.NumberUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */

public class UserSystemPositionQueryHelper {
	
	/**
	 * 用户岗位
	 * @param userId
	 * @param systemPositionOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static UserSystemPosition get(String userId, Long systemPositionOid) throws DataAccessFailureException {
		return DaoUtil.uniqueResult("from UserSystemPosition usp where usp.userId = ? and usp.systemPositionOid = ?", userId, systemPositionOid);
	}
	
	/**
	 * 根据用户id得到用户岗位列表
	 * @param userId
	 * @return
	 * @throws ServiceException 
	 */
	public static List<UserSystemPositionDTO> listByUserId(String userId) throws ServiceException {
		
		StringBuilder hql=new StringBuilder();
		hql.append("from UserSystemPosition usp where usp.userId = '"+userId+"'");
        //得到列表
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString()),UserSystemPositionDTO.class);
	
	}
	
	
	/**
	 * 用户岗位添加的岗位信息查询(用户已经拥有的岗位不做查询)
	 * 
	 * */
	public static List<UserPositionInfoDTO> findAllUsersPosition(TableTagBean ttb)throws ServiceException{
		
		String userId= ttb.getCondition().get("userId");
		StringBuilder sql=new StringBuilder();
		if(StringUtils.isNotEmpty(userId)){
			sql.append("select sp.system_position_oid, ")
					.append("sp.system_position_name, ")
					.append(" sp.system_position_desc, ")
					.append(" r.role_name as function_role , ")
					.append(" rr.role_name as data_role")
					.append(" from YHB_SYSTEM_POSITION sp ")
					.append(" left join YHB_ROLES r on r.role_id = sp.data_role_id ")
					.append("  left join YHB_ROLES rr  on rr.role_id = sp.function_role_id  where sp.system_position_oid  in ")
					.append(" (select jus.system_position_oid from YHB_USER_SP jus where jus.user_id <> ")
					.append(" ('").append(userId.trim()).append("'))");
			if (StringUtils.isNotEmpty(ttb.getCondition().get("systemPositionName"))) {
				sql.append(" and sp.system_position_name like  "+"'%"+ttb.getCondition().get("systemPositionName")+"%'");
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) from (").append(sql).append(")").toString(), params));
		}
		StringBuilder lsql = new StringBuilder();
		lsql.append(" select  t.system_position_oid, ")
			.append(" t.system_position_name, ")
			.append("  t.system_position_desc, ")
			.append(" t.function_role, ")
			.append("  t.data_role from (");
		
		//分页查询
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by sp.system_position_oid asc ) t").toString(), params, ttb.getPage(), ttb.getPageSize());
		if(!CollectionUtils.isEmpty(list)){
			return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UserPositionInfoDTO>(){
				public UserPositionInfoDTO getValue(Object[] src) throws ServiceException {
					UserPositionInfoDTO dto = new UserPositionInfoDTO();
					dto.setSystemPositionOid(DataConverUtils.toLong(src[0]));
					dto.setSystemPositionName(DataConverUtils.toString(src[1]));
					dto.setSystemPositionDesc(DataConverUtils.toString(src[2]));
					dto.setDataRoleName(DataConverUtils.toString(src[3]));
					dto.setFunctionRoleName(DataConverUtils.toString(src[4]));
					return dto;
					}
			});
		}
		return null;
	}
	
	/**
	 * 解除岗位上所有用户的对应关系
	 * @param systemPositionOid
	 * @throws DataAccessFailureException
	 */
	public static void deleteBySystemPositionOid(Long systemPositionOid) throws DataAccessFailureException  {
		if (NumberUtils.isNullOrZero(systemPositionOid)) {
			return;
		}
		
		DaoUtil.executeUpdate("delete from UserSystemPosition usp where usp.systemPositionOid = " + systemPositionOid);
	}
}	

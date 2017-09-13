package com.yh.admin.position.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.admin.auth.queryhelper.UserOrgAuthQueryHelper;
import com.yh.admin.bo.SystemPosition;
import com.yh.admin.bo.UserSystemPosition;
import com.yh.admin.bo.Users;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.dto.SystemPositionDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.users.queryhelper.UserAgentQueryHelper;
import com.yh.admin.users.queryhelper.UserSystemPositionQueryHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringUtil;

public class SystemPositionQueryHelper {
	public static List<UserSystemPositionDTO> findSysPositionList(TableTagBean ttb) throws ServiceException{
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" from YHB_SYSTEM_POSITION sp");
		sql.append(" left join YHB_ROLES r on r.role_id = sp.data_role_id");
		sql.append(" left join YHB_ROLES rr on rr.role_id = sp.function_role_id");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("systemPositionName"))) {
			String systemPositionName=ttb.getCondition().get("systemPositionName");
			sql.append(" where system_position_name like '%").append(systemPositionName).append("%'");
		}
		
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) ").append(sql).toString(), params));
		}
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select sp.system_position_name,");
		lsql.append(" sp.system_position_desc," );
		lsql.append(" trim(rr.role_name) as rrn," );
		lsql.append(" trim(r.role_name) as r, " );
		lsql.append(" sp.system_position_oid " );


		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by sp.system_position_oid asc ").toString(), params, ttb.getPage(), ttb.getPageSize());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UserSystemPositionDTO>() {

			public UserSystemPositionDTO getValue(Object[] src) throws ServiceException {
				UserSystemPositionDTO dto = new UserSystemPositionDTO();
				dto.setSystemPositionName(DataConverUtils.toString(src[0]));
				dto.setSystemPositionDesc(DataConverUtils.toString(src[1]));
				dto.setFunctionRoleName(DataConverUtils.toString(src[2]));
				dto.setDataRoleName(DataConverUtils.toString(src[3]));
				dto.setSystemPositionOid(DataConverUtils.toLong(src[4]));
				return dto;
			}});
	}

	public static SystemPosition findSysPosition(Long systemPositionOid) throws ServiceException {
		return DaoUtil.get(SystemPosition.class, systemPositionOid);
	}

	public static List<UsersDTO> findUserBySpId(TableTagBean ttb) throws ServiceException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" from YHB_USERS ju, yhb_user_sp us, YHB_SYSTEM_POSITION sp");
		sql.append(" where sp.system_position_oid = us.system_position_oid");
		sql.append(" and ju.user_id = us.user_id");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("systemPositionOid"))) {
			String systemPositionOid=ttb.getCondition().get("systemPositionOid");
			sql.append(" and sp.system_position_oid =").append(Long.valueOf(systemPositionOid));
		}
		
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) ").append(sql).toString(), params));
		}
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select ju.user_id, ju.user_name,ju.unit_id,ju.unit_name, us.effective_date,us.expired_date,ju.user_oid");


		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by sp.system_position_oid asc ").toString(), params, ttb.getPage(), ttb.getPageSize());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UsersDTO>() {

			public UsersDTO getValue(Object[] obj) throws ServiceException {
				UsersDTO dto = new UsersDTO();
				dto.setUserId(DataConverUtils.toString(obj[0]));
				dto.setUserName(DataConverUtils.toString(obj[1]));
				dto.setUnitId(DataConverUtils.toLong(obj[2]));
				dto.setUnitName(DataConverUtils.toString(obj[3]));
				dto.setEffectiveDate(DataConverUtils.toDate(obj[4]));
				dto.setExpiredDate(DataConverUtils.toDate(obj[5]));
				dto.setUserOid(DataConverUtils.toLong(obj[6]));
				return dto;
			}});
	}

	public static Users findUserById(Long userOid) throws ServiceException {
		return DaoUtil.get(Users.class, userOid);
	}

	public static void deletePositionUser(Long userOid) throws ServiceException {
		Users users= DaoUtil.get(Users.class, userOid);
		if(users!=null){
		String userId=users.getUserId();
		List<UserSystemPosition> userSystemPositionlist=DaoUtil.find(" from  UserSystemPosition usp" +
				" where usp.userId = ?",userId);
			if(!CollectionUtils.isEmpty(userSystemPositionlist)){
				for(UserSystemPosition usp: userSystemPositionlist){
					usp.delete();
				}
			}
		}
		
	}

	public static List<UsersDTO> findUsersInfo(TableTagBean ttb) throws ServiceException{

		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" from YHB_USERS j where not exists( select 1 from  yhb_user_sp us where us.user_id = j.user_id ");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("systemPositionOid"))) {
			String systemPositionOid=ttb.getCondition().get("systemPositionOid");
			sql.append(" and us.system_position_oid =").append(Long.valueOf(systemPositionOid));
		}
		sql.append(")");
		if(StringUtils.isNotEmpty(ttb.getCondition().get("userId"))){
			sql.append(" and j.user_id like '%"+ttb.getCondition().get("userId")+"%'");
		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("userName"))){
			sql.append(" and j.user_name like '%"+ttb.getCondition().get("userName")+"%'");
		}
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) ").append(sql).toString(), params));
		}
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select j.USER_OID,j.USER_ID,j.USER_NAME,j.UNIT_ID,j.UNIT_NAME,j.EFFECTIVE_DT,j.EXPIRED_DATE");


		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by j.user_oid asc ").toString(), params, ttb.getPage(), ttb.getPageSize());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UsersDTO>() {

			public UsersDTO getValue(Object[] obj) throws ServiceException {
				UsersDTO dto = new UsersDTO();
				dto.setUserId(DataConverUtils.toString(obj[1]));
				dto.setUserName(DataConverUtils.toString(obj[2]));
				dto.setUnitId(DataConverUtils.toLong(obj[3]));
				dto.setUnitName(DataConverUtils.toString(obj[4]));
				dto.setEffectiveDate(DataConverUtils.toDate(obj[5]));
				dto.setExpiredDate(DataConverUtils.toDate(obj[6]));
				dto.setUserOid(DataConverUtils.toLong(obj[0]));
				return dto;
			}});
	}

	public static SystemPositionDTO goViewPoistionRole(Long systemPositionOid) throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select jsp.system_position_oid,jsp.system_position_name,jsp.system_position_desc,trim(jr.role_name) as functionRoleName,");
		sql.append(" trim(jrr.role_name) as dataRoleName from YHB_SYSTEM_POSITION jsp 	left join yhb_roles jr on jr.role_id = jsp.function_role_id ");
		sql.append(" left join yhb_roles jrr on jrr.role_id = jsp.data_role_id");
		sql.append(" where jsp.system_position_oid = ?");
		List<Object[]> list=DaoUtil.findWithSQL(sql.toString(), systemPositionOid);
		List<SystemPositionDTO> systemPositionDTOList=new ArrayList<SystemPositionDTO>();
		SystemPositionDTO systemPositionDTO=new SystemPositionDTO();
		for(Object[] obj:list){
			systemPositionDTO.setSystemPositionOid(DataConverUtils.toLong(obj[0]));
			systemPositionDTO.setSystemPositionName(DataConverUtils.toString(obj[1]));
			systemPositionDTO.setSystemPositionDesc(DataConverUtils.toString(obj[2]));
			systemPositionDTO.setDataRoleName(DataConverUtils.toString(obj[4]));
			systemPositionDTO.setFunctionRoleName(DataConverUtils.toString(obj[3]));
			systemPositionDTOList.add(systemPositionDTO);
		}
		return systemPositionDTOList.get(0);
	}

	public static List<RolesDTO> findRoleByType(TableTagBean ttb) throws ServiceException{
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" from yhb_roles j");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("roleType"))) {
			String roleType=ttb.getCondition().get("roleType");
			sql.append(" where j.role_type =").append(Long.valueOf(roleType));
		}
		if (StringUtils.isNotEmpty(ttb.getCondition().get("roleName"))) {
			String roleName=ttb.getCondition().get("roleName");
			sql.append(" and j.role_name like '").append(StringUtil.wrapPercent(roleName)).append("'");
		}
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) ").append(sql).toString(), params));
		}
		
		StringBuilder lsql = new StringBuilder();
		lsql.append("select j.role_id,j.role_name,j.role_desc");


		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(sql).append(" order by j.role_id asc ").toString(), params, ttb.getPage(), ttb.getPageSize());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], RolesDTO>() {

			public RolesDTO getValue(Object[] obj) throws ServiceException {
				RolesDTO dto = new RolesDTO();
				dto.setRoleId(DataConverUtils.toLong(obj[0]));
				dto.setRoleName(DataConverUtils.toString(obj[1]));
				dto.setRoleDesc(DataConverUtils.toString(obj[2]));
				return dto;
			}});
	}

	public static List<UserSystemPosition> findUspByPositionOid(
			Long systemPositionOid) throws ServiceException {
		List<UserSystemPosition> list=new ArrayList<UserSystemPosition>();
		if(systemPositionOid!=null){
			list=DaoUtil.find(" from UserSystemPosition usp where usp.systemPositionOid=? ", systemPositionOid);
		}
		
		return list;
	}

	public static void deleteSystemPoistion(Long systemPositionOid) throws ServiceException {
		SystemPosition systemPosition=DaoUtil.get(SystemPosition.class, systemPositionOid);
		if(systemPosition!=null){
			UserSystemPositionQueryHelper.deleteBySystemPositionOid(systemPositionOid);
			UserAgentQueryHelper.deleteBySystemPositionOid(systemPositionOid);
			UserOrgAuthQueryHelper.deleteBySystemPositionOid(systemPositionOid);
			systemPosition.delete();
		}
	}
	/**
	 * 检查创建岗位时重名
	 * @param systemPositionName
	 * */
	public static boolean checkSPositionByName(String systemPositionName) throws ServiceException{
		List<SystemPosition> systemPositionlist= DaoUtil.find(" from SystemPosition sp where sp.systemPositionName =?",systemPositionName.trim());
		if(CollectionUtils.isEmpty(systemPositionlist)){
			return true;
		}
		return false;
	}
	
	public static boolean checkPositionByName(String systemPositionName,Long systemPositionOid) throws ServiceException{
		List<SystemPosition> systemPositionlist= DaoUtil.find(" from SystemPosition sp where sp.systemPositionName ='"+systemPositionName.trim()+"' and sp.systemPositionOid !="+systemPositionOid);
		if(CollectionUtils.isEmpty(systemPositionlist)){
			return true;
		}
		return false;
	}

	public static SystemPosition getSystemPositionByName (String systemPositionName) throws ServiceException{
		List<SystemPosition> systemPositionlist=DaoUtil.find(" from SystemPosition sp where sp.systemPositionName =?",systemPositionName.trim());
		SystemPosition systemPosition=new SystemPosition();
		if(!CollectionUtils.isEmpty(systemPositionlist)){
			systemPosition=systemPositionlist.get(0);
		}
		return systemPosition;
	}
	
	/**
	 * 根据用户ID和岗位ID查询用户岗位信息
	 * @param userId
	 * @param systemPositionOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static UserSystemPosition findUserSystemPosition(String userId,Long systemPositionOid)throws DataAccessFailureException
	{
		List<UserSystemPosition> userSystemPositionlist=DaoUtil.findByNamed(" from  UserSystemPosition usp where usp.userId =:userId and usp.systemPositionOid=:systemPositionOid", new String[]{"userId","systemPositionOid"}, new Object[]{userId,systemPositionOid});
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(userSystemPositionlist))
		{
			return userSystemPositionlist.get(0);
		}
		return null;
	}
	
	
	
	/**
	 * 根据用户id和岗位ID删除用户关联岗位信息
	 * @param userOid
	 * @param systemPositionOid
	 */
	public static void deletePositionUserByUserOidAndPositionOid(Long userOid,
			Long systemPositionOid) throws DataAccessFailureException
	{
		Users users= DaoUtil.get(Users.class, userOid);
		if(users!=null)
		{
			String userId=users.getUserId();
			
			UserSystemPosition userSystemPosition = findUserSystemPosition(userId, systemPositionOid);
			if(null != userSystemPosition)
			{
				userSystemPosition.delete();
			}
		}
	}
}



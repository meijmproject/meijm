package com.yh.admin.position.service;

import java.util.Date;
import java.util.List;

import com.yh.admin.bo.SystemPosition;
import com.yh.admin.bo.UserSystemPosition;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.users.queryhelper.UsersQueryHelper;
import com.yh.admin.util.AuthConstants;
import org.springframework.beans.BeanUtils;

import com.yh.admin.bo.Users;
import com.yh.admin.dto.SystemPositionDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.position.queryhelper.SystemPositionQueryHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/23
 */
public class SystemPositionService {
	public List<UserSystemPositionDTO> findSysPositionList(TableTagBean ttb) throws ServiceException {
		return SystemPositionQueryHelper.findSysPositionList(ttb);
	}

	public SystemPosition findSysPosition(Long systemPositionOid) throws ServiceException {
		return SystemPositionQueryHelper.findSysPosition(systemPositionOid);
	}

	public List<UsersDTO> findUserBySpId(TableTagBean ttb) throws ServiceException {
		return SystemPositionQueryHelper.findUserBySpId(ttb);
	}

	public boolean update(SystemPositionDTO systemPositionDTO) throws ServiceException{
		
		if(SystemPositionQueryHelper.checkPositionByName(systemPositionDTO.getSystemPositionName(),systemPositionDTO.getSystemPositionOid())){
			SystemPosition systemPosition = DaoUtil.get(SystemPosition.class, systemPositionDTO.getSystemPositionOid());
			if (systemPosition != null) {
				//BeanHelper.copyProperties(systemPositionDTO, systemPosition, new String[]{"createdByCode","createdByName","createdDate"});
				systemPosition.setSystemPositionDesc(systemPositionDTO.getSystemPositionDesc());
				systemPosition.setSystemPositionName(systemPositionDTO.getSystemPositionName());
				systemPosition.setUpdatedByCode(UserContext.getLoginUserID());
				systemPosition.setUpdatedByName(UserContext.getLoginUserName());
				systemPosition.setUpdatedDate(DateUtil.now());
				systemPosition.update();
			}
			return true;
		}
		return false;
	}

	public boolean addSystemPosition(SystemPositionDTO systemPositionDTO,String systemPositionName)throws ServiceException {
		
		if(SystemPositionQueryHelper.checkSPositionByName(systemPositionName)){
			//将dto转换成为po
			SystemPosition systemPosition = BeanHelper.copyProperties(systemPositionDTO,SystemPosition.class);
			 //得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//当前日期
			Date now = new Date();
			//设置新增人信息和修改人信息
			systemPosition.setCreatedByCode(userId);
			systemPosition.setCreatedByName(userName);
			systemPosition.setCreatedDate(now);
			systemPosition.setUpdatedByCode(userId);
			systemPosition.setUpdatedByName(userName);
			systemPosition.setUpdatedDate(now);
			//保存
			systemPosition.save();
			return true;
		}
		return false;	
	}

	public void deletePositionUser(Long userOid) throws ServiceException  {
		SystemPositionQueryHelper.deletePositionUser(userOid);
	}

	public void addPositionUser(String userId, Long systemPositionOid)throws ServiceException {
		UserSystemPosition userSystemPosition=new UserSystemPosition();
		userSystemPosition.setUserId(userId);
		userSystemPosition.setSystemPositionOid(systemPositionOid);
		 //得到操作人信息
		String userid = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		userSystemPosition.setCreatedByCode(userid);
		userSystemPosition.setCreatedByName(userName);
		userSystemPosition.setCreatedDate(now);
		userSystemPosition.setUpdatedByCode(userId);
		userSystemPosition.setUpdatedByName(userName);
		userSystemPosition.setUpdatedDate(now);
		userSystemPosition.setEffectiveDate(now);
		userSystemPosition.setExpiredDate(DateUtil.addYear(now, 90));
		userSystemPosition.save();
		
	}

	public Users findUserById(Long userOid) throws ServiceException {
		return SystemPositionQueryHelper.findUserById(userOid);
	}

	public void updateUser(UsersDTO usersDTO)throws ServiceException {

		Users users = DaoUtil.get(Users.class, usersDTO.getUserOid());
		if (users != null) {
			UserSystemPosition userSystemPosition = SystemPositionQueryHelper.findUserSystemPosition(users.getUserId(), usersDTO.getSystemPositionOid());
			if(null != userSystemPosition)
			{
				userSystemPosition.setEffectiveDate(usersDTO.getEffectiveDate());
				userSystemPosition.setExpiredDate(usersDTO.getExpiredDate());
				userSystemPosition.setUpdatedByCode(UserContext.getLoginUserID());
				userSystemPosition.setUpdatedByName(UserContext.getLoginUserName());
				userSystemPosition.setUpdatedDate(DateUtil.now());
				userSystemPosition.update();
			}
		}
		
	}

	public List<UsersDTO> findUsersInfo(TableTagBean ttb) throws ServiceException {
		return SystemPositionQueryHelper.findUsersInfo(ttb);

	}

	public SystemPositionDTO goViewPoistionRole(Long systemPositionOid) throws ServiceException {
		return SystemPositionQueryHelper.goViewPoistionRole(systemPositionOid);
	}

	public List<RolesDTO> findRoleByType(TableTagBean ttb) throws ServiceException {
		return SystemPositionQueryHelper.findRoleByType(ttb);
	}

	public void updatePositionUser(Long roleId, Long roleType,
			Long systemPositionOid) throws ServiceException {
		SystemPosition systemPosition=DaoUtil.get(SystemPosition.class, systemPositionOid);
		if(systemPosition!=null){
			if(AuthConstants.ROLE_TYPE_DATA.equals(String.valueOf(roleType))){
				systemPosition.setDataRoleId(roleId);
			}else if(AuthConstants.ROLE_TYPE_FUNCTION.equals(String.valueOf(roleType))){
				systemPosition.setFunctionRoleId(roleId);
			}
			systemPosition.update();
		}
	}

	public void deleteSystemPoistion(Long systemPositionOid) throws ServiceException {
		SystemPositionQueryHelper.deleteSystemPoistion(systemPositionOid);
		
	}

	public SystemPositionDTO getSystemPositionByName(String systemPositionName) throws ServiceException{
		SystemPosition systemPosition=SystemPositionQueryHelper.getSystemPositionByName(systemPositionName);
		return BeanHelper.copyProperties(systemPosition,SystemPositionDTO.class);
		
	}

	/**
	 * 根据用户id和岗位ID删除用户关联岗位信息
	 * @param userOid
	 * @param systemPositionOid
	 */
	public void deletePositionUserByUserOidAndPositionOid(Long userOid,
			Long systemPositionOid) throws ServiceException
	{
		SystemPositionQueryHelper.deletePositionUserByUserOidAndPositionOid(userOid,systemPositionOid);
	}
	
	/**
	 * 根据用户id和岗位id查询用户岗位信息
	 * @param userId
	 * @param systemPositionOid
	 * @return
	 * @throws ServiceException
	 */
	public UserSystemPositionDTO findUserSystemPositionDTO(String userId,Long systemPositionOid) throws ServiceException
	{
		UserSystemPosition userSystemPosition = SystemPositionQueryHelper.findUserSystemPosition(userId, systemPositionOid);
		if(null != userSystemPosition)
		{
			UserSystemPositionDTO dto = new UserSystemPositionDTO();
			BeanUtils.copyProperties(userSystemPosition, dto);
			return dto;
		}
		return null;
	}
	
	/**
	 * 替换用户岗位关系
	 * @param userId
	 * @param oldSystemPositionOid
	 * @param newSystemPositionOid
	 */
	public void updateUserSystemPosition(String userId,Long oldSystemPositionOid, Long newSystemPositionOid) throws ServiceException {
		UsersDTO usersDTO = UsersQueryHelper.findByUserId(userId);
		if (usersDTO != null) {
			UserSystemPosition userSystemPosition = SystemPositionQueryHelper.findUserSystemPosition(userId, oldSystemPositionOid);
			if(null != userSystemPosition)
			{
				UserSystemPosition bo = new UserSystemPosition();
				BeanHelper.copyProperties(userSystemPosition, bo);
				userSystemPosition.delete();
				bo.setSystemPositionOid(newSystemPositionOid);
				bo.setEffectiveDate(usersDTO.getEffectiveDate());
				bo.setExpiredDate(usersDTO.getExpiredDate());
				bo.setUpdatedByCode(UserContext.getLoginUserID());
				bo.setUpdatedByName(UserContext.getLoginUserName());
				bo.setUpdatedDate(DateUtil.now());
				bo.save();
			}
		}
	}
}

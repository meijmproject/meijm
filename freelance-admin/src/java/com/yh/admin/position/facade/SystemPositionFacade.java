package com.yh.admin.position.facade;

import java.util.List;

import com.yh.admin.bo.SystemPosition;
import com.yh.admin.bo.Users;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.dto.SystemPositionDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.position.service.SystemPositionService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/23
 */
public class SystemPositionFacade {

	private SystemPositionService systemPositionService;

	public void setSystemPositionService(
			SystemPositionService systemPositionService) {
		this.systemPositionService = systemPositionService;
	}

	public List<UserSystemPositionDTO> findSysPositionList(TableTagBean ttb)
			throws ServiceException {
		return systemPositionService.findSysPositionList(ttb);
	}

	public SystemPosition findSysPosition(Long systemPositionOid) throws ServiceException {
		return systemPositionService.findSysPosition(systemPositionOid);
	}

	public List<UsersDTO> findUserBySpId(TableTagBean ttb) throws ServiceException {
		return systemPositionService.findUserBySpId(ttb);
	}

	public boolean updateSystemPoistion(SystemPositionDTO systemPositionDTO) throws ServiceException{
		return systemPositionService.update(BeanHelper.copyProperties(systemPositionDTO, SystemPositionDTO.class));
	}

	public boolean addSystemPosition(SystemPositionDTO systemPositionDTO,String systemPositionName) throws ServiceException{
		return systemPositionService.addSystemPosition(BeanHelper.copyProperties(systemPositionDTO, SystemPositionDTO.class),systemPositionName);
	}

	public void deletePositionUser(Long userOid) throws ServiceException{
		systemPositionService.deletePositionUser(userOid);
	}

	public void addPositionUser(String userId, Long systemPositionOid) throws ServiceException{
		systemPositionService.addPositionUser(userId,systemPositionOid);
	}

	public Users findUserById(Long userOid)throws ServiceException {
		return systemPositionService.findUserById(userOid);
	}

	public void updateUser(UsersDTO usersDTO) throws ServiceException {
		systemPositionService.updateUser(BeanHelper.copyProperties(usersDTO, UsersDTO.class));
	}

	public List<UsersDTO> findUsersInfo(TableTagBean ttb) throws ServiceException {
		return systemPositionService.findUsersInfo(ttb);
	}

	public SystemPositionDTO viewPoistionRole(Long systemPositionOid) throws ServiceException {
		return systemPositionService.goViewPoistionRole(systemPositionOid);
	}

	public List<RolesDTO> findRoleByType(TableTagBean ttb) throws ServiceException {
		return systemPositionService.findRoleByType(ttb);
	}

	public void updatePositionUser(Long roleId, Long roleType, Long systemPositionOid) throws ServiceException {
		systemPositionService.updatePositionUser(roleId,roleType,systemPositionOid);
	}

	public void deleteSystemPoistion(Long systemPositionOid) throws ServiceException{
		systemPositionService.deleteSystemPoistion(systemPositionOid);
	}

	public SystemPositionDTO getSystemPositionByName(String systemPositionName) throws ServiceException{
		return systemPositionService.getSystemPositionByName(systemPositionName);
	}
	
	/**
	 * 根据用户id和岗位ID删除用户关联岗位信息
	 * @param userOid
	 * @param systemPositionOid
	 * @throws ServiceException
	 */
	public void deletePositionUserByUserOidAndPositionOid(Long userOid,Long systemPositionOid) throws ServiceException
	{
		systemPositionService.deletePositionUserByUserOidAndPositionOid(userOid,systemPositionOid);
	}
	
	/**
	 * 根据用户ID和岗位ID查询用户岗位信息
	 * @param userId
	 * @param systemPositionOid
	 * @return
	 * @throws ServiceException
	 */
	public UserSystemPositionDTO findUserSystemPositionDTO(String userId,Long systemPositionOid)throws ServiceException
	{
		return systemPositionService.findUserSystemPositionDTO(userId, systemPositionOid);
	}
}

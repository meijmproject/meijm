/**
 * 
 */
package com.yh.admin.users.facade;

import java.util.List;

import com.yh.admin.dto.ResourcesDTO;
import com.yh.admin.dto.SubSystemDTO;
import com.yh.admin.dto.UserPositionInfoDTO;
import com.yh.admin.dto.UserRelationDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.res.service.ResourceService;
import com.yh.admin.sao.person.SaoPersonInfoService;
import com.yh.admin.sao.person.dto.SaoAdminPersonDTO;
import com.yh.admin.sao.unit.SaoOrgInfoService;
import com.yh.admin.sao.unit.SaoUnitInfoService;
import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.admin.sao.unit.dto.SaoAdminUnitDTO;
import com.yh.admin.users.service.UsersService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/11
 */

public class UsersFacade {

	private UsersService usersService;
	private ResourceService resourceService;
	private SaoUnitInfoService saoUnitInfoService;
	private SaoOrgInfoService saoOrgInfoService;
	private SaoPersonInfoService saoPersonInfoService;
	
	
	public void setSaoOrgInfoService(SaoOrgInfoService saoOrgInfoService) {
		this.saoOrgInfoService = saoOrgInfoService;
	}
	public void setSaoUnitInfoService(SaoUnitInfoService saoUnitInfoService) {
		this.saoUnitInfoService = saoUnitInfoService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setSaoPersonInfoService(SaoPersonInfoService saoPersonInfoService) {
		this.saoPersonInfoService = saoPersonInfoService;
	}
	
	
	/**
	 * 通过登录名和密码查询账号信息
	 * @param userCode
	 * @param pw
	 * @return
	 * @throws ServiceException
	 */
	public UsersDTO checkUserPswd(String userCode, String pw)
			throws ServiceException {
		return usersService.checkUserPswd(userCode, pw);
	}

	public boolean checkUserRight(String userId, String systemCode)
			throws ServiceException {
		return usersService.checkUserRight(userId, systemCode) != null;
	}

	public UsersDTO findUsers(String userCode) throws ServiceException {
		return usersService.findUsers(userCode);
	}

	/**
	 * 用户有权限的资源
	 * 
	 * @param userCode
	 * @return
	 */
	public List<ResourcesDTO> listUserResources(String userId)
			throws ServiceException {
		return resourceService.listUserResources(userId);
	}

	/**
	 * 系统全部资源
	 * 
	 * @param systemCode
	 * @return
	 * @throws ServiceException
	 */
	public List<ResourcesDTO> listResources(String systemCode)
			throws ServiceException {
		return resourceService.listResources(systemCode);
	}
	/**
	 * 查询所有用户信息
	 * 
	 * */
	public List<UsersDTO> listUsersInfo(TableTagBean ttb)throws ServiceException{
		
		return usersService.listUsersInfo(ttb);
		}
	/**
	 * 根据userOid查询用户信息
	 * */
	public UsersDTO getUserByOid(String userOid)throws ServiceException{
		return usersService.getUserByOid(userOid);
	}
	/**
	 * 查询当前登录的用户信息
	 * */
	public UsersDTO findLoginUserByUserId()throws ServiceException{
		return usersService.findLoginUserByUserId();
	}
	
	/**
	 * 根据userID查询用户信息
	 * */
	public UsersDTO findUsersByUserId(String userId)throws ServiceException{
		return usersService.findUsersByUserId(userId);
	}
	
	/**
     * 修改用户信息
     * @param UsersDTO
     * @throws ServiceException
     */
    public void updateUsersInfo(UsersDTO usersDto) throws ServiceException
    {
    	usersService.updateUsersInfo(BeanHelper.copyProperties(usersDto,UsersDTO.class));
    }
    /**
     * 新增用户信息
     * @param UsersDTO
     * @throws ServiceException
     */
    public void createUsersInfo(UsersDTO usersDto) throws ServiceException
    {
    	usersService.createUsersInfo(usersDto);
    }
    /**
     * 检查用户ID是否存在
     * @param usersId
     * @throws ServiceException
     * */
    public boolean checkUserByUsersID(String usersId) throws ServiceException
    {
    	return  usersService.checkUserByUsersID(usersId);
    }
    
    /**
     * 系统信息查询
     * */
    public List<SubSystemDTO> findSubSystemsInfo()throws ServiceException{
    	return usersService.findSubSystemsInfo();
    }
    /**
     * 删除用户信息
     * @param usersOid
	 * @param refType
     * */
     public void deleteUsers(String userOid,String refType)throws ServiceException{
     	usersService.deleteUsers(userOid,refType);
     }
     /**
      * 查找单位列表信息
      * */
     public List<SaoAdminUnitDTO> findUserUnitList(TableTagBean ttb) throws ServiceException {
    	 return saoUnitInfoService.listByCondition(ttb);
     }
     /**
      * 查询用户下的岗位信息
      * @param userId
      * */
     public List<UserPositionInfoDTO> findUsersPosition(String userId,String systemPositionOid) throws ServiceException {
    	 return usersService.findUsersPosition(userId,systemPositionOid);
     }
    /**
     * 查询所有岗位信息查询（除了用户已拥有的）
     * */
     public List<UserPositionInfoDTO> findAllUsersPosition(TableTagBean ttb) throws ServiceException {
    	 return usersService.findAllUsersPosition(ttb);
     }
     /**
      * 用户岗位添加
      * */
     public void addUsersPosition (String systemPositionOids,String userId) throws ServiceException {
    	 	usersService.addUsersPosition(systemPositionOids,userId);
     }
     /**
      * 
      * removeUsersPosition
      * */
     public void deleteUsersPosition (String systemPositionOid,String userId) throws ServiceException {
 	 	usersService.deleteUsersPosition(systemPositionOid,userId);
  }
     /**
      * 用户岗位信息修改
      * */
     public void updateUsersPosition(UserSystemPositionDTO usPosition) throws ServiceException
     {
     	usersService.updateUsersPosition(usPosition);
     }
     /**
      * 用户已有的岗位信息查询
      * */
     public UserSystemPositionDTO findUSPositionBy(String systemPositionOid,String userId) throws ServiceException
     {
    	 return usersService.findUSPositionBy(systemPositionOid,userId);
     }
     /**
      *查询单位信息
      * */
     public  List<SaoAdminUnitDTO> findUsersUnitList(TableTagBean ttb) throws ServiceException{
    	 return saoUnitInfoService.listByCondition(ttb);
     }
     /**
      * 查询单位名称
      * @param unitOid
      * */
     public String getUnitNameByUnitID(Long unitOid) throws ServiceException{
    	 return saoUnitInfoService.getUnitName(unitOid);
     }
     public String  getUnitOrgName(Long orgOid) throws ServiceException{
    	 return saoOrgInfoService.getOrgName(orgOid);
     }
     
     /**
      * 查询单位下的内设机构
     * @param unitOid
     * @return
     * @throws ServiceException
     */
    public List<SaoAdminOrgDTO>  listUnitOrg(Long unitOid) throws ServiceException{
    	 return saoOrgInfoService.findUnitOrg(unitOid);
     }
    
    /**
     * 重置密码
     * @param userOid
     * @throws ServiceException
     */
	public void updatePasswordForReset(String userOid) throws ServiceException{
		usersService.reSetPs(userOid);
		
	}
     
	/**
	 * 通过用户ID和来源类型查询用户信息关系表
	 * @param userId
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByUserIdAndRefType(String userId,String refType) throws ServiceException {
		return usersService.getUserRelationDTOByUserIdAndRefType(userId,refType);
	}
	
	/**
	 * 通过用户ID查询用户信息关系表
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByUserId(String userId) throws ServiceException {
		return usersService.getUserRelationDTOByUserId(userId);
	}
	
	/**
	 * 创建用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void createUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		usersService.createUserRelation(userRelationDTO);
	}

	/**
	 * 修改用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void updateUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		usersService.updateUserRelation(userRelationDTO);
	}

	/**
	 * 删除用户信息关系表
	 * @param userId
	 * @param refType
	 * @throws ServiceException
	 */
	public void deleteUserRelation(String userId,String refType) throws ServiceException {
		usersService.deleteUserRelation(userId,refType);
	}
	
	/**
	 * 通过姓名模糊查询人员基础信息列表
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<SaoAdminPersonDTO> listPbPersonInfoByName(String name) throws ServiceException {
		return saoPersonInfoService.listPbPersonInfoByName(name);
	}
	
	/**
	 * 创建或者修改用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void createOrUpdateUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		usersService.createOrUpdateUserRelation(userRelationDTO);
	}
	
	/**
	 * 通过来源OID和来源类型查询用户信息关系表
	 * @param refOid
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByRefOidAndRefType(Long refOid,String refType) throws ServiceException {
		return usersService.getUserRelationDTOByRefOidAndRefType(refOid, refType);
	}
}

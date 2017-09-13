/**
 * 
 */
package com.yh.admin.util;

import com.yh.admin.roles.facade.RoleDataAuthFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yh.platform.core.util.SpringBeanUtil;



/**
 * 新启线程更新用户单位权限
 * @author xuhj
 *
 */
public class UserUnitAuthThread extends Thread  
{
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 用户ID，用户关联岗位时使用，可为空
	 */
	private String userId;
	
	/**
	 * 数据角色ID，数据角色重新授权时使用，可为空
	 */
	private Long roleId;
	
	/**
	 * 岗位ID，岗位重新挂数据角色时使用，可为空
	 */
	private Long systemPositionOid ;
	
	/**
	 * 三个参数仅需传其一，也可以全部不传，解释如下<br>
	 * 用户ID，用户关联岗位时传值<br>
	 * 数据角色ID，数据角色重新授权时传值<br>
	 * 岗位ID，岗位重新挂数据角色时传值<br>
	 * 所有都不传，则更新所有人员的所有单位数据权限
	 * @param userId
	 * @param roleId 
	 * @param systemPositionOid 
	 */
	public UserUnitAuthThread(String userId, Long roleId, Long systemPositionOid)
	{
		this.userId=userId;
		this.roleId=roleId;
		this.systemPositionOid=systemPositionOid;
	}
	
	public void run()
	{
		try
		{
			((RoleDataAuthFacade) SpringBeanUtil.getBean("roleDataAuthFacade")).updateUserAuth(userId, roleId, systemPositionOid);
		}
		catch (Exception e)
		{
			log.info("更新用户单位权限失败！"+e);
		}
	}
}

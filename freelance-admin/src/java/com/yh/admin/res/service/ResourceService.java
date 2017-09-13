/**
 * 
 */
package com.yh.admin.res.service;

import java.util.List;

import com.yh.admin.dto.ResourcesDTO;
import com.yh.admin.res.queryhelper.ResourceQueryHelper;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */

public class ResourceService {

	/**
	 * 系统全部资源
	 * @param systemCode
	 * @return
	 * @throws ServiceException 
	 */
	public List<ResourcesDTO> listResources(String systemCode) throws ServiceException {
		return ResourceQueryHelper.listResources(systemCode);
	}

	/**
	 * 用户有权限的资源
	 * @param userId
	 * @return
	 */
	public List<ResourcesDTO> listUserResources(String userId) throws ServiceException {
		return ResourceQueryHelper.listUserResources(userId);
	}

}

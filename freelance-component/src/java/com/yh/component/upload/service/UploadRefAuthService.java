/**
 * 
 */
package com.yh.component.upload.service;

import java.util.List;

import com.yh.component.upload.bo.UploadRefAuth;
import com.yh.component.upload.queryhelper.UploadRefAuthQueryHelper;
import com.yh.platform.core.exception.ServiceException;

/**
 * 角色权限
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadRefAuthService {

	/**
	 * 获取角色对应的权限配置
	 * @param refRoleCode
	 * @param refCode
	 * @return
	 * @throws ServiceException 
	 */
	public List<UploadRefAuth> findUploadRefAuth(String refRoleCode, String refCode) throws ServiceException {
		
		return UploadRefAuthQueryHelper.findUploadRefAuth(refRoleCode, refCode);
	}

}

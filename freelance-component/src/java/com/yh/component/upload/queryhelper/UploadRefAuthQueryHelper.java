/**
 * 
 */
package com.yh.component.upload.queryhelper;

import java.util.List;

import com.yh.component.upload.bo.UploadRefAuth;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadRefAuthQueryHelper {

	/**
	 * 获取角色对应的权限配置
	 * @param refRoleCode
	 * @param refCode
	 * @return
	 * @throws ServiceException 
	 */
	public static List<UploadRefAuth> findUploadRefAuth(String refRoleCode, String refCode) throws ServiceException {
		
		return DaoUtil.find("from UploadRefAuth ura where ura.refRoleCode = ? and ura.refCode = ?", refRoleCode, refCode);
	}

}

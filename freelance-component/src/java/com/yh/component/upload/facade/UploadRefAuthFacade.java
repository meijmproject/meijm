/**
 * 
 */
package com.yh.component.upload.facade;

import java.util.List;

import com.yh.component.upload.dto.UploadRefAuthDTO;
import com.yh.component.upload.service.UploadRefAuthService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadRefAuthFacade {

	private UploadRefAuthService uploadRefAuthService;
	
	public void setUploadRefAuthService(UploadRefAuthService uploadRefAuthService) {
		this.uploadRefAuthService = uploadRefAuthService;
	}
	
	/**
	 * 获取角色对应的权限配置
	 * @param refRoleCode
	 * @param refCode
	 * @return
	 * @throws ServiceException
	 */
	public List<UploadRefAuthDTO> findUploadRefAuth(String refRoleCode, String refCode) throws ServiceException {
		
		return BeanHelper.copyProperties(uploadRefAuthService.findUploadRefAuth(refRoleCode, refCode), UploadRefAuthDTO.class);
	}

}

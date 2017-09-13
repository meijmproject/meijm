/**
 * 
 */
package com.yh.component.upload.facade;

import java.io.File;

import com.yh.component.upload.bo.UploadNodeRef;
import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.component.upload.service.UploadNodeRefService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadNodeRefFacade {

	private UploadNodeRefService uploadNodeRefService;
	
	public void setUploadNodeRefService(UploadNodeRefService uploadNodeRefService) {
		this.uploadNodeRefService = uploadNodeRefService;
	}
	
	/**
	 * 获取指定来源的存储目录（按“[path]/年月日/时”分目录）
	 * @param refCode
	 * @return 
	 * @throws ServiceException
	 */
	public File getRefUploadDir(String refCode) throws ServiceException {
		
		return uploadNodeRefService.getRefUploadDir(refCode);
	}

	public UploadNodeRefDTO get(String refCode) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(UploadNodeRef.class, refCode), UploadNodeRefDTO.class);
	}

}

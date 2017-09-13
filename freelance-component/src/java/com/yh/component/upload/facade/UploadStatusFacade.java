/**
 * 
 */
package com.yh.component.upload.facade;

import com.yh.component.upload.bo.UploadStatus;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 文件上传状态
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadStatusFacade {

	/**
	 * 获取文件上传状态
	 * @param uuid
	 * @return
	 * @throws ServiceException
	 */
	public UploadStatusDTO get(String uuid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(UploadStatus.class, uuid), UploadStatusDTO.class);
	}

	public void saveOrUpdate(UploadStatusDTO uploadStatus) throws ServiceException {
		BeanHelper.copyProperties(uploadStatus, UploadStatus.class).saveOrUpdate();
	}

}

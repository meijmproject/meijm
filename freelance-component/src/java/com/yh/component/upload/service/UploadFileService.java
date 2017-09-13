/**
 * 
 */
package com.yh.component.upload.service;

import com.yh.component.upload.bo.UploadFile;
import com.yh.component.upload.queryhelper.UploadFileQueryHelper;
import com.yh.platform.core.exception.ServiceException;

/**
 * 上传文件Service
 * @author	zhangqp
 * @version	1.0,	16/11/10
 */

public class UploadFileService {

	/**
	 * @param refCode
	 * @param refOid
	 * @return
	 * @throws ServiceException 
	 */
	public UploadFile findByCodeOid(String refCode, Long refOid) throws ServiceException {
		
		return UploadFileQueryHelper.findByCodeOid(refCode, refOid);
	}

}

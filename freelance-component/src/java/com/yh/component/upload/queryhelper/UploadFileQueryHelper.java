/**
 * 
 */
package com.yh.component.upload.queryhelper;

import com.yh.component.upload.bo.UploadFile;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/10
 */

public class UploadFileQueryHelper {

	/**
	 * @param refCode
	 * @param refOid
	 * @return
	 * @throws ServiceException 
	 */
	public static UploadFile findByCodeOid(String refCode, Long refOid) throws ServiceException {
		
		return DaoUtil.uniqueResult("from UploadFile uf where uf.refCode = ? and refOid = ?", refCode, refOid);
	}

}

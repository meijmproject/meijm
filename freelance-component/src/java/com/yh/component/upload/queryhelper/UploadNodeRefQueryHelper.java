/**
 * 
 */
package com.yh.component.upload.queryhelper;

import java.util.List;

import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	chenjl
 * @version	1.0,	17/06/06
 */

public class UploadNodeRefQueryHelper {

	/**
	 * 查询业务事项列表
	 * @param uploadNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<UploadNodeRefDTO> listBizBusiness(String uploadNodeCode) throws ServiceException{
		return DaoUtil.find("from UploadNodeRef unf where unf.uploadNodeCode = ? and unf.refCode != 'M22160'", uploadNodeCode);
	}

}

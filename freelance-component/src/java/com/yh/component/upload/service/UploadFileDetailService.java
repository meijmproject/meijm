/**
 * 
 */
package com.yh.component.upload.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.queryhelper.UploadFileDetailQueryHelper;
import com.yh.platform.core.exception.ServiceException;

import net.sf.json.JSONObject;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/10
 */

public class UploadFileDetailService {

	/**
	 * 查询文件列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listUploadFile(TableTagBean ttb) throws ServiceException {

		return UploadFileDetailQueryHelper.listUploadFile(ttb);
	}
}

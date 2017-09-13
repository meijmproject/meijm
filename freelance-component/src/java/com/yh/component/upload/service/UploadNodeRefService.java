/**
 * 
 */
package com.yh.component.upload.service;

import java.io.File;
import java.util.List;

import com.yh.component.upload.bo.UploadNodeRef;
import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.component.upload.queryhelper.UploadNodeRefQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 * 文件来源Service
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UploadNodeRefService {

	/**
	 * 获取指定来源的存储目录（按“[path]/年月日/时”分目录）
	 * @param refCode
	 * @return
	 * @throws ServiceException 
	 */
	public File getRefUploadDir(String refCode) throws ServiceException {
		
		UploadNodeRef uploadNodeRef = DaoUtil.get(UploadNodeRef.class, refCode);
		
		if(uploadNodeRef == null) {
			throw new ServiceException(null, "未指定的文件来源代码！refCode="+refCode);
		}
		
		StringBuilder dir = new StringBuilder();
		
		dir.append(uploadNodeRef.getPath()).append(DateUtil.nowString("/yyyyMMdd/HH"));
		
		File path = new File(dir.toString());
		
		path.mkdirs();
		
		return path;
	}

	/**
	 * 查询列表
	 * @param uploadNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public List<UploadNodeRefDTO> listBizBusiness(String uploadNodeCode) throws ServiceException{
		return UploadNodeRefQueryHelper.listBizBusiness(uploadNodeCode);
	}
}

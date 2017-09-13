/**
 * 
 */
package com.yh.component.upload.facade;

import java.io.File;
import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.bo.UploadFile;
import com.yh.component.upload.bo.UploadFileDetail;
import com.yh.component.upload.bo.UploadStatus;
import com.yh.component.upload.dto.UploadFileDetailDTO;
import com.yh.component.upload.queryhelper.UploadFileDetailQueryHelper;
import com.yh.component.upload.service.UploadFileDetailService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/11/09
 */

public class UploadFileDetailFacade {
	private UploadFileDetailService uploadFileDetailService;

	public void setUploadFileDetailService(UploadFileDetailService uploadFileDetailService) {
		this.uploadFileDetailService = uploadFileDetailService;
	}

	/**
	 * 查询文件列表
	 * 
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listUploadFile(TableTagBean ttb) throws ServiceException {

		return uploadFileDetailService.listUploadFile(ttb);
	}

	/**
	 * 获取文件信息
	 * @param fileOid
	 * @return
	 * @throws ServiceException
	 */
	public UploadFileDetailDTO get(Long fileOid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(UploadFileDetail.class, fileOid), UploadFileDetailDTO.class);
	}

	/**
	 * 删除文件
	 * @param fileOid
	 * @throws ServiceException
	 */
	public void delete(Long fileOid) throws ServiceException {
		UploadFileDetail uploadFileDetail = DaoUtil.get(UploadFileDetail.class, fileOid);
		
		if (uploadFileDetail != null) {
			UploadStatus uploadStatus = DaoUtil.get(UploadStatus.class, uploadFileDetail.getFileNameTarget());
			if(uploadStatus != null) {
				uploadStatus.delete();
			}
			uploadFileDetail.delete();
			List<UploadFileDetail> list= UploadFileDetailQueryHelper.listUploadFileDetail(uploadFileDetail.getUploadFileOid());
			if(CollectionUtils.isEmpty(list)){
				UploadFile uploadFile = DaoUtil.get(UploadFile.class, uploadFileDetail.getUploadFileOid());
				if(null!=uploadFile){
					uploadFile.delete();
				}
			}
			
			new File(uploadFileDetail.getPath()).delete();
		}
	}
}

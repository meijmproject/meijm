package com.yh.hr.info.uploadphoto.service;

import java.util.List;

import com.yh.hr.info.uploadphoto.dto.UploadPhotoDTO;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * 上传文件接口
 * @author chenjl
 * @date 2017-03-25
 * @version 1.0
 */
public interface UploadPhotoService {

	/**
	 * 查询上传文件列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	List<JSONObject> listUploadPhoto(TableTagBean ttb) throws ServiceException;

	/**
	 * 删除文件
	 * @param photoOid
	 * @throws ServiceException
	 */
	void delete(Long photoOid) throws ServiceException;

	/**
	 * 获取上传文件信息
	 * @param photoOid
	 * @return
	 * @throws ServiceException
	 */
	UploadPhotoDTO get(Long photoOid) throws ServiceException;

}

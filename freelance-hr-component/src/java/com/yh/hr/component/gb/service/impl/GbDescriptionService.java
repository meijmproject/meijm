package com.yh.hr.component.gb.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.gb.dto.GbDescriptionDTO;
import com.yh.platform.core.exception.ServiceException;

public interface GbDescriptionService {

	List<JSONObject> listGbDescription(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 新增岗位说明信息
	 * @param gbDescriptionDTO
	 * @throws ServiceException
	 */
	public void createGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException;
	
	/**
	 * 获取岗位说明信息
	 * @param jhgGbDescriptionOid
	 * @return
	 * @throws ServiceException
	 */
	public GbDescriptionDTO findGbDescriptionById(Long jhgGbDescriptionOid) throws ServiceException;
	
	/**
	 * 修改岗位说明信息
	 * @param gbDescriptionDTO
	 * @throws ServiceException
	 */
	public void updateGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException;
	
	/**
	 * 删除岗位说明信息
	 * @param jhgGbDescriptionOid
	 * @throws ServiceException
	 */
	public void deleteGbDescription(Long gbDescriptionOid) throws ServiceException;
	
}

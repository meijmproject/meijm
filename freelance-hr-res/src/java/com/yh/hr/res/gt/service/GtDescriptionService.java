package com.yh.hr.res.gt.service;

import java.util.List;

import com.yh.hr.res.gt.bo.GtDescription;
import com.yh.hr.res.gt.dto.GtDescriptionDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位说明service（业务）
 * @author huangyj
 */
public interface GtDescriptionService {

	/**
	 * 新增岗位说明信息
	 * @param gtDescriptionDTO
	 * @throws ServiceException
	 */
	public void createGtDescription(GtDescriptionDTO gtDescriptionDTO) throws ServiceException;
	
	/**
	 * 获取岗位说明信息
	 * @param jhgGtDescriptionOid
	 * @return
	 * @throws ServiceException
	 */
	public GtDescriptionDTO findGtDescriptionById(Long jhgGtDescriptionOid) throws ServiceException;
	
	/**
	 * 更新岗位说明信息
	 * @param gtDescriptionDTO
	 * @throws ServiceException
	 */
	public void updateGtDescription(GtDescriptionDTO gtDescriptionDTO) throws ServiceException;
	
	/**
	 * 删除岗位说明信息
	 * @param jhgGtDescriptionOid
	 * @throws ServiceException
	 */
	public void deleteGtDescription(Long jhgGtDescriptionOid) throws ServiceException;
	/**
	 * 获取岗位说明信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public GtDescription findGtDescriptionByUnitOid(Long unitOid) throws ServiceException;
	/**获取岗位说明列表
	 * @param jhgGtDescriptionOid
	 * @return
	 * @throws ServiceException
	 */
	public List<GtDescriptionDTO> listGtDescriptionById(Long unitOid,Long taskOid) throws ServiceException;
}

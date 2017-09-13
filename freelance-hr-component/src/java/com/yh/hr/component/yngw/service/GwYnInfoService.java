package com.yh.hr.component.yngw.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.yngw.dto.GwYnInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface GwYnInfoService {
	
	/**
	 * 新增院内岗位信息
	 * @param gwYnInfoDTO
	 * @throws ServiceException
	 */
	public void create(GwYnInfoDTO gwYnInfoDTO) throws ServiceException;
	
	/**
	 * 删除院内岗位信息
	 * @param positionOid
	 * @throws ServiceException
	 */
	public void delete(java.lang.Long positionOid) throws ServiceException;
	
	/**
	 * 根据院内岗位id查找院内岗位信息
	 * @param positionOid
	 * @return GwYnInfoDTO
	 * @throws ServiceException
	 */
	public GwYnInfoDTO get(java.lang.Long positionOid) throws ServiceException;
	
	/**
	 * 列出多条院内岗位信息TableTagBean
	 * @param ttb
	 * @return List<GwYnInfoDTO>
	 * @throws ServiceException
	 */
	public List<GwYnInfoDTO> find(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 更新&修改院内岗位信息
	 * @param gwYnInfoDTO
	 * @throws ServiceException
	 */
	public void update(GwYnInfoDTO gwYnInfoDTO) throws ServiceException;
	
	/**
	 * 列出所有的院内岗位信息
	 * @return List<GwYnInfoDTO>
	 * @throws ServiceException
	 */
	public List<GwYnInfoDTO> listAllGwYnInfo() throws ServiceException;

	/**
	 * 获取岗位名称信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<GwYnInfoDTO> listPositionName(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 确认传入的岗位名称(大类)是否已经创建
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean positionNameParentNodeIsExist(String positionNameDl) throws ServiceException;
	
	/**
	 * 根据岗位类别查询岗位信息
	 * @param positionType
	 * @return
	 * @throws ServiceException
	 */
	public List<GwYnInfoDTO> findPositionInfoByPositionType(String positionType)throws ServiceException;
}

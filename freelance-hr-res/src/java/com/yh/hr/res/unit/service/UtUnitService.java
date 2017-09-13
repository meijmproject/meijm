/**
 * 
 */
package com.yh.hr.res.unit.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringMap;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public interface UtUnitService {

	/**
	 * 查找已授权单位的详细信息
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitListByAuth(List<String> authorizationOids, StringMap params) throws ServiceException;
	
	/**
	 * 查找已授权单位的详细信息
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitList(StringMap params) throws ServiceException;
	
	/**
	 * 查找已授权单位的详细信息
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public UtUnitDTO get(Long unitOid) throws ServiceException;
	
	/**
	 * 查找单位名称，用于显示单位名称（单位不存在，返回null）
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public String getUnitName(Long unitOid) throws ServiceException;

	/**
	 * 查找单位
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> listByCondition(TableTagBean ttb) throws ServiceException;

	/**
	 * 新增机构信息
	 * @param utUnitDTO
	 * @return
	 * @throws ServiceException
	 */
	public void create(UtUnitDTO utUnitDTO) throws ServiceException;
	
	/**
	 * 修改机构信息
	 * @param utUnitDTO
	 * @return
	 * @throws ServiceException
	 */
	public void update(UtUnitDTO utUnitDTO) throws ServiceException;
	
	/**
	 * 查找单位性质（单位不存在，返回null）
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public String getUnitKind(Long unitOid) throws ServiceException;
	
	
	/**
	 * 查找主管单位
	 * @param unitOid
	 * @return UtUnitDTO
	 * @throws ServiceException
	 */
	public UtUnitDTO getAdminUnit(Long unitOid) throws ServiceException;
	
	/**
	 * 查找主管单位集合
	 * @param unitOids
	 * @return List<Long> adminUnitOids
	 * @throws ServiceException
	 */
	public List<Long> findAdminUnitOid(List<Long> unitOids) throws ServiceException;
    /**
     * 查询整个系统的单位  （只有一个）
     * @param unitOid
     * @return
     */
	public List<UtUnitDTO> findUnitInfo()throws ServiceException;
	
	/**
	 * 根据科id查询所属单位
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public UtUnitDTO getUnitInfoByOrgOid(Long orgOid)throws ServiceException;

}

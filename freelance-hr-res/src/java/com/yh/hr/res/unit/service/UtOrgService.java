/**
 * 
 */
package com.yh.hr.res.unit.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

/**
 * 内设机构信息
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public interface UtOrgService {

	/**
	 * 获取指定内设机构信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public UtOrgDTO get(Long orgOid) throws ServiceException;
	
	/**
	 * 获取内设机构名称
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public String getOrgName(Long orgOid) throws ServiceException;
	
	/**
	 * 查找单位下的所有内设机构
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> findUnitOrg(Long unitOid) throws ServiceException;

	/**
	 * 新增内设机构
	 * @param utOrgDTO
	 * @throws ServiceException
	 */
	public void create(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 修改内设机构
	 * @param utOrgDTO
	 * @throws ServiceException
	 */
	public void update(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 删除内设机构
	 * @param utOrgDTO
	 * @throws ServiceException
	 */
	public void delete(Long orgOid) throws ServiceException;

	/**
	 * 查找内设机构
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> listByCondition(TableTagBean ttb) throws ServiceException;
	
	/**
     * 通过科室名称查询科室信息
     * @param orgName
     * @return
     * @throws DataAccessFailureException
     */
    public UtOrgDTO findUtOrgDTOByOrgName(String orgName) throws ServiceException;
	
}

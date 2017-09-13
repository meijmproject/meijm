/**
 * 
 */
package com.yh.admin.sao.unit;

import java.util.List;

import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * Admin 模块，内设机构请求接口
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public interface SaoOrgInfoService {

	/**
	 * 获取指定单位信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public SaoAdminOrgDTO get(Long orgOid) throws ServiceException;
	
	/**
	 * 获取单位名称
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
	public List<SaoAdminOrgDTO> findUnitOrg(Long unitOid) throws ServiceException;
	/**
	 * 条件查找内设机构
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<SaoAdminOrgDTO> listByCondition(TableTagBean ttb) throws ServiceException;
	
}

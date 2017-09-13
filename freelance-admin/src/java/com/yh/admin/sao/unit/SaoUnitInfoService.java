/**
 * 
 */
package com.yh.admin.sao.unit;

import java.util.List;

import com.yh.admin.sao.unit.dto.SaoAdminUnitDTO;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * Admin 模块，单位请求接口
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */

public interface SaoUnitInfoService {

	/**
	 * 获取指定单位信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public SaoAdminUnitDTO get(Long unitOid) throws ServiceException;
	
	/**
	 * 获取单位名称
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public String getUnitName(Long unitOid) throws ServiceException;
	
	/**
	 * 条件查找单位
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<SaoAdminUnitDTO> listByCondition(TableTagBean ttb) throws ServiceException;
	
}

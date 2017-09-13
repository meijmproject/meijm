/**
 * 
 */
package com.yh.admin.sao.item;

import java.util.List;

import com.yh.admin.dto.MtMenuDto;
import com.yh.platform.core.exception.ServiceException;

/**
 * 外部事项环节查询Service
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */
public interface SaoItemNodeService {

	public List<MtMenuDto> findItem(String parentMenuCode,String menuType) throws ServiceException;

	public boolean checkByRoleId(String roleId, String menuCode) throws ServiceException;
	
	
}

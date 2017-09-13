/**
 * 
 */
package com.yh.hr.select.unit.service.check;

import com.yh.hr.select.unit.dto.UnitSelectDTO;
import com.yh.platform.core.exception.ServiceException;


public interface UnitSelectCheckService {

	/**
	 * 待办事项查询（返回结果为空，表示验证通过）
	 * @param itemCode
	 * @param itemCodeNode
	 * @param person
	 * @return
	 * @throws ServiceException
	 */
	public String check(String itemCode, String itemCodeNode, UnitSelectDTO person) throws ServiceException;
	
}

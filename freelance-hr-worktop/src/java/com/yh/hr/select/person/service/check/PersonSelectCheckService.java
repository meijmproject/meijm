/**
 * 
 */
package com.yh.hr.select.person.service.check;

import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public interface PersonSelectCheckService {

	/**
	 * 待办事项查询（返回结果为空，表示验证通过）
	 * @param itemCode
	 * @param itemCodeNode
	 * @param person
	 * @return
	 * @throws ServiceException
	 */
	public String check(String itemCode, String itemCodeNode, PersonSelectDTO person) throws ServiceException;
	
}

/**
 * 
 */
package com.yh.hr.select.person.service.check.impl;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.hr.select.person.queryhelper.PersonSelectQueryHelper;
import com.yh.hr.select.person.service.check.PersonSelectCheckService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员待办事项验证
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class PersonDoingItemsCheckServiceImpl implements PersonSelectCheckService {

	/* (non-Javadoc)
	 * @see PersonSelectCheckService#check(java.lang.String, java.lang.String, PersonSelectDTO)
	 */
	public String check(String itemCode, String itemCodeNode, PersonSelectDTO person) throws ServiceException {
		StringBuffer nameStr= new StringBuffer();
		List<String> doingItemNameList = PersonSelectQueryHelper.check(person.getPersonOid());
		if(CollectionUtils.isNotEmpty(doingItemNameList)){
			for(String str:doingItemNameList){
				nameStr.append("该人员正在办理“" + str+"”业务；");
			}
		}
		return nameStr.toString() ;
	}

}

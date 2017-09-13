/**
 * 
 */
package com.yh.hr.select.person.service.check.impl;

import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.hr.select.person.service.check.PersonSelectCheckService;
import com.yh.hr.worktop.util.TaskStatusConstants;
import org.apache.commons.lang.StringUtils;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员是否完成校核验证
 * @author wangjie
 * @date 2016-12-12 
 * @version 1.0 
 */
public class PersonIsVerifiedCheckServiceImpl implements PersonSelectCheckService {

	/* (non-Javadoc)
	 * @see PersonSelectCheckService#check(java.lang.String, java.lang.String, PersonSelectDTO)
	 */
	public String check(String itemCode, String itemCodeNode, PersonSelectDTO person) throws ServiceException {
		String isVerified=person.getIsVerified();
		if(TaskStatusConstants.MIGRATION.equals(person.getCreatedByCode())){
			if(StringUtils.isNotEmpty(isVerified)){
				if(DicConstants.YHRS0003_1.equals(isVerified)){
					return "";
				}else{
					return "该人员信息没有通过校核，不能办理业务！";
				}
			}else{
				return "该人员信息没有进行校核，不能办理业务！";
			}
		}
		return "";
		
	}
}

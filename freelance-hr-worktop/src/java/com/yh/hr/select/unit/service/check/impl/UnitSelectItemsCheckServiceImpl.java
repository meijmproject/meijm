/**
 * 
 */
package com.yh.hr.select.unit.service.check.impl;

import java.util.List;

import com.yh.hr.select.unit.dto.UnitSelectDTO;
import com.yh.hr.select.unit.queryhelper.UnitSelectQueryHelper;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.select.unit.service.check.UnitSelectCheckService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员待办事项验证
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class UnitSelectItemsCheckServiceImpl implements UnitSelectCheckService {
	
	/*
	 * (non-Javadoc)
	 * @see UnitSelectCheckService#check(java.lang.String, java.lang.String, UnitSelectDTO)
	 */
	public String check(String itemCode, String itemCodeNode, UnitSelectDTO unit) throws ServiceException {
		StringBuffer nameStr= new StringBuffer();
		List<String> doingItemNameList = UnitSelectQueryHelper.check(unit.getUnitOid());
		if(CollectionUtils.isNotEmpty(doingItemNameList)){
			for(String str:doingItemNameList){
				nameStr.append("该单位正在办理“" + str+"”业务；");
			}
		}
		return nameStr.toString() ;
	}

}

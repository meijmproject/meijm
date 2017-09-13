package com.yh.hr.info.dataimport.person.service;

import java.util.Map;

import com.yh.platform.core.exception.ServiceException;

public interface ImportPersonService {

	void importPersonGroupByOrg(Map<String, Object> map, String unitOid, String orgOid) throws ServiceException;

}

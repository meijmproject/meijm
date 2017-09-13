package com.yh.hr.res.cf.service.impl;

import java.util.List;

import com.yh.hr.res.cf.dto.JhcCfShowResultSetDTO;
import com.yh.hr.res.cf.queryhelper.JhcCfShowResultSetQueryHelper;
import com.yh.hr.res.cf.service.JhcCfShowResultSetService;
import com.yh.platform.core.exception.ServiceException;

public class JhcCfShowResultSetServiceImpl implements JhcCfShowResultSetService {

	public List<JhcCfShowResultSetDTO> find(String functionCode) throws ServiceException {
		return JhcCfShowResultSetQueryHelper.find(functionCode);
	}

}

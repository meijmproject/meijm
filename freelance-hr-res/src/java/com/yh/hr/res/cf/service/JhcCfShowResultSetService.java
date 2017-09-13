package com.yh.hr.res.cf.service;

import java.util.List;

import com.yh.hr.res.cf.dto.JhcCfShowResultSetDTO;
import com.yh.platform.core.exception.ServiceException;

public interface JhcCfShowResultSetService {

	/**
	 * 通过functionCode查找表格列
	 * @param functionCode
	 * @return
	 * @throws ServiceException
	 */
	List<JhcCfShowResultSetDTO> find(String functionCode) throws ServiceException;

}

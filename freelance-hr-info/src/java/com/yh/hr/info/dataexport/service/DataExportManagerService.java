package com.yh.hr.info.dataexport.service;

import java.util.List;

import com.yh.platform.core.exception.DataAccessFailureException;

public interface DataExportManagerService {

	/**
	 * 按传入的字段字符串查询人员信息
	 * @param fields   字段字符串
	 * @return 
	 * @throws DataAccessFailureException 
	 */
	public List<List<Object>> listDataExportPersonInfo(List<String> fieldList, List<String> transFlagList) throws DataAccessFailureException;

}

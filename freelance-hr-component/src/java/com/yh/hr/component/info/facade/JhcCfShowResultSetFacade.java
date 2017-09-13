package com.yh.hr.component.info.facade;

import java.util.List;

import com.yh.hr.res.cf.dto.JhcCfShowResultSetDTO;
import com.yh.hr.res.cf.service.JhcCfShowResultSetService;
import com.yh.platform.core.exception.ServiceException;

public class JhcCfShowResultSetFacade {

	private JhcCfShowResultSetService jhcCfShowResultSetService;
	
	public void setJhcCfShowResultSetService(JhcCfShowResultSetService jhcCfShowResultSetService) {
		this.jhcCfShowResultSetService = jhcCfShowResultSetService;
	}
	
	/**
	 * 通过functionCode查找表格列
	 * @param functionCode
	 * @return
	 * @throws ServiceException
	 */
	public List<JhcCfShowResultSetDTO> findColumns(String functionCode) throws ServiceException {
		return jhcCfShowResultSetService.find(functionCode);
	}
}
